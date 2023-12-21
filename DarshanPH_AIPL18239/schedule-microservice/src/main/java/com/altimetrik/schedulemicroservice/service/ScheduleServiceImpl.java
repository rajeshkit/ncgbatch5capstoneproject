package com.altimetrik.schedulemicroservice.service;

import com.altimetrik.schedulemicroservice.exception.RouteNotExistsException;
import com.altimetrik.schedulemicroservice.exception.ScheduleIdNotExistsException;
import com.altimetrik.schedulemicroservice.exception.TrainNotExistsException;
import com.altimetrik.schedulemicroservice.model.*;
import com.altimetrik.schedulemicroservice.repository.RouteRepository;
import com.altimetrik.schedulemicroservice.repository.ScheduleRepository;
import com.altimetrik.schedulemicroservice.repository.TrainRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;

@Service
public class ScheduleServiceImpl  implements ScheduleService {
    @Autowired
    private ScheduleRepository scheduleRepository;
    private static final Logger logger= LoggerFactory.getLogger(ScheduleServiceImpl.class);
    @Autowired
    private TrainRepository trainRepository;
    @Autowired
    private RouteRepository routeRepository;
    @Autowired
    private Train train;
    @Autowired
    private Route route;
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private Schedule schedule;
    private String BASE_URI = "http://localhost:8080/Train-api/";
    private String BASE_URI_Route = "http://localhost:8181/route-api";

    @Override
    public Schedule addSchedule(ScheduleInput scheduleinput) throws TrainNotExistsException, RouteNotExistsException {
        logger.info("Recieved a request to add the schedule:{}",scheduleinput);
        String uri = BASE_URI + "train/" + scheduleinput.getTrainId();
        Train tr = restTemplate.getForObject(uri, Train.class);
        String uri1 = BASE_URI_Route + "/route/" + scheduleinput.getRouteId();
        Route rt = restTemplate.getForObject(uri1, Route.class);
        if (tr != null && rt != null) {
            train.setTrainId(tr.getTrainId());
            train.setTrainNumber(tr.getTrainNumber());
            train.setTrainName(tr.getTrainName());
            train.setAcCoaches(tr.getAcCoaches());
            train.setAcCoachTotalSeats(tr.getAcCoachTotalSeats());
            train.setSleeperCoaches(tr.getSleeperCoaches());
            train.setSleeperCoachTotalSeats(tr.getSleeperCoachTotalSeats());
            train.setGeneralCoaches(tr.getGeneralCoaches());
            train.setGeneralCoachTotalSeats(tr.getGeneralCoachTotalSeats());
            train.setTotalKms(tr.getTotalKms());
            route.setRouteId(rt.getRouteId());
            route.setSource(rt.getSource());
            route.setDestination(rt.getDestination());
            route.setTotalKms(rt.getTotalKms());
            schedule = Schedule.builder()
                    .train(tr)
                    .arrivalDateTime(scheduleinput.getArrivalDateTime())
                    .departureDateTime(scheduleinput.getDepartureDateTime())
                    .route(rt)
                    .build();
            logger.info("Fetched the train information:{}",train);
            logger.info("Fetched the route information:{}",route);
        }else if(tr==null){
            throw new TrainNotExistsException("Train Id does not exist in the Train Database");
        }else if(rt==null){
            throw new RouteNotExistsException("Route Id does not exist in the Route Database");
        }
        trainRepository.save(train);
        routeRepository.save(route);
        logger.info("Schedule added:{}",schedule);
        return scheduleRepository.save(schedule);

    }

    @Override
    public List<Schedule> getAllSchedule() {
        logger.info("Returning all the schedules");
        return scheduleRepository.findAll();
    }

    @Override
    public Schedule getScheduleById(int scheduleId) throws ScheduleIdNotExistsException {
        logger.info("Getting schedule by ID: {}", scheduleId);
        Optional<Schedule> sc = scheduleRepository.findById(scheduleId);
        if(sc.isEmpty()){
            logger.error("Schedule with ID {} does not exist", scheduleId);
            throw new ScheduleIdNotExistsException("Schedule Id does not exist in the db!!");
        }
        Schedule schedule = sc.get();
        logger.info("Returning schedule: {}", schedule);
        return schedule;
    }

    @Override
    public Schedule updateSchedule(Schedule schedule) throws ScheduleIdNotExistsException {
        logger.info("Received request to update schedule: {}", schedule);
        if (getScheduleById(schedule.getId()) != null) {
            Schedule updatedSchedule = scheduleRepository.save(schedule);
            logger.info("Schedule updated successfully: {}", updatedSchedule);
            return updatedSchedule;
        }
        return null;
    }

    @Override
    public String deleteSchedule(int scheduleId) throws ScheduleIdNotExistsException {
        logger.info("Received request to delete schedule by ID: {}", scheduleId);
        String message = "Schedule does not exist";
        if (getScheduleById(scheduleId) != null) {
            scheduleRepository.deleteById(scheduleId);
            message = "Schedule successfully deleted";
            logger.info(message);
        }else {
            logger.error("Schedule Id {} does not exist",scheduleId);
        }
        return message;
    }

}
