package com.altimetrik.SCHEDULE.service;

import com.altimetrik.SCHEDULE.exception.RouteNotFoundException;
import com.altimetrik.SCHEDULE.exception.ScheduleIdNotExistsException;
import com.altimetrik.SCHEDULE.exception.TrainNotFoundException;
import com.altimetrik.SCHEDULE.model.Route;
import com.altimetrik.SCHEDULE.model.Schedule;
import com.altimetrik.SCHEDULE.model.ScheduleRequest;
import com.altimetrik.SCHEDULE.model.Train;
import com.altimetrik.SCHEDULE.repository.RouteRepository;
import com.altimetrik.SCHEDULE.repository.ScheduleRepository;
import com.altimetrik.SCHEDULE.repository.TrainRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;

import static com.altimetrik.SCHEDULE.model.Schedule.*;

@Service
public class ScheduleServiceImpl implements ScheduleService{
    @Autowired
    private RestTemplate restTemplate;
    private static final Logger logger = LoggerFactory.getLogger(ScheduleServiceImpl.class);
    @Autowired
    private ScheduleRepository scheduleRepository;
    @Autowired
    private TrainRepository trainRepository;
    @Autowired
    private RouteRepository routeRepository;

    @Autowired
    private Train train;
    @Autowired
    private Route route;
    @Override
    public Schedule createSchedule(ScheduleRequest request) throws TrainNotFoundException, RouteNotFoundException {

        logger.info("Received a request to create schedule: {}", request);

        Train t1 = restTemplate.getForObject("http://localhost:8080/train-api/train/"+request.getTrainId(), Train.class );
        if (t1 == null) {
            throw new TrainNotFoundException("Train with ID " + request.getTrainId() + " not found");
        }
        else {

            train.setTrainId(t1.getTrainId());
            train.setTrainName(t1.getTrainName());
            train.setAcCoaches(t1.getAcCoaches());
            train.setAcCoachTotalSeats(t1.getAcCoachTotalSeats());
            train.setSleeperCoaches(t1.getSleeperCoaches());
            train.setSleeperCoachTotalSeats(t1.getSleeperCoachTotalSeats());
            train.setGeneralCoaches(t1.getGeneralCoaches());
            train.setGeneralCoachTotalSeats(t1.getGeneralCoachTotalSeats());
            train.setTotalKms(t1.getTotalKms());

        }
        logger.info("Fetched train information: {}", train);
        Route r1 = restTemplate.getForObject("http://localhost:8181/route-api/route/"+request.getRouteId(), Route.class);
        if (r1 == null) {
            throw new RouteNotFoundException("Route with ID " + request.getRouteId() + " not found");
        }
        else {
            route.setRouteId(r1.getRouteId());
            route.setSource(r1.getSource());
            route.setDestination(r1.getDestination());
            route.setTotalKms(r1.getTotalKms());
        }
        logger.info("Fetched route information: {}", route);

        Schedule schedule =  Schedule.builder()
                .departureDateTime(request.getDepartureDateTime())
                .arrivalDateTime(request.getArrivalDateTime())
                .train(t1)
                .route(r1)
                .build();

        trainRepository.save(train);
        routeRepository.save(route);
        scheduleRepository.save(schedule);

        logger.info("Schedule created: {}", schedule);
        return schedule;
    }
    @Override
    public List<Schedule> getAllSchedules() {
        logger.info("Returning all the schedules");
        return scheduleRepository.findAll();
    }

    @Override
    public Schedule getScheduleById(int scheduleId) throws ScheduleIdNotExistsException {
        Optional<Schedule> sc = scheduleRepository.findById(scheduleId);
        if(sc.isEmpty()){
            throw new ScheduleIdNotExistsException("Schedule Id does not exist in the db!!");
        }
        return sc.get();
    }

    @Override
    public Schedule updateSchedule(Schedule schedule) throws ScheduleIdNotExistsException {
        if (getScheduleById(schedule.getScheduleId()) != null) {
            return scheduleRepository.save(schedule);
        }
        return null;
    }

    @Override
    public String deleteScheduleById(int scheduleId) throws ScheduleIdNotExistsException {
        String message = "Schedule does not exist";
        try{
            if (getScheduleById(scheduleId) != null) {
                scheduleRepository.deleteById(scheduleId);
                message = "Schedule successfully deleted";
            }
        } catch (Exception e) {
            logger.error("Exception occurred while deleting schedule with ID {}: {}", scheduleId, e.getMessage());
            System.out.println("Id does not exist");
        }
        return message;
    }
}
