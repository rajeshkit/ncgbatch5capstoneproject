package com.altimetrik.trainschedule.service;

import com.altimetrik.trainschedule.exception.RouteIdNotFoundException;
import com.altimetrik.trainschedule.exception.ScheduleIdNotFoundException;
import com.altimetrik.trainschedule.exception.TrainNumberNotFoundException;
import com.altimetrik.trainschedule.modle.*;
import com.altimetrik.trainschedule.repository.RouteRepository;
import com.altimetrik.trainschedule.repository.ScheduleRepository;
import com.altimetrik.trainschedule.repository.TrainRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;


@Service
public class ScheduleServiceImp implements ScheduleService {
    @Autowired
    private ScheduleRepository scheduleRepository;
    private static final Logger logger= LoggerFactory.getLogger(ScheduleServiceImp.class);
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



    @Override
    public Schedule addSchedule(NewSchedule newschedule) throws TrainNumberNotFoundException, RouteIdNotFoundException {
        logger.info("Recieved a request to add the schedule:{}",newschedule);
        String url = "http://localhost:8181/train-api/" + "train/" + newschedule.getTrainNumber();
        Train train1 = restTemplate.getForObject(url, Train.class);
        String url1 = "http://localhost:8080/route-api" + "/route/" + newschedule.getRouteId();
        Route route1 = restTemplate.getForObject(url1, Route.class);
        if (train == null) {
            throw new TrainNumberNotFoundException("Train with ID " + newschedule.getTrainNumber() + " not found");
        }
        else {
            train.setTrainNumber(train1.getTrainNumber());
            train.setTrainName(train1.getTrainName());
            train.setAcCoaches(train1.getAcCoaches());
            train.setAcCoachTotalSeats(train1.getAcCoachTotalSeats());
            train.setSleeperCoaches(train1.getSleeperCoaches());
            train.setSleeperCoachTotalSeats(train1.getSleeperCoachTotalSeats());
            train.setGeneralCoaches(train1.getGeneralCoaches());
            train.setGeneralCoachTotalSeats(train1.getGeneralCoachTotalSeats());
            train.setTotalKms(train1.getTotalKms());

        }
        if (route == null) {
            throw new RouteIdNotFoundException("Route with ID " + newschedule.getRouteId() + " not found");
        }else {
            route.setRouteId(route1.getRouteId());
            route.setSource(route1.getSource());
            route.setDestination(route1.getDestination());
            route.setTotalKms(route1.getTotalKms());

        }
        logger.info("Fetched the train information:{}",train);
        logger.info("Fetched the route information:{}",route);

        schedule = Schedule.builder()
                .train(train1)
                .arrivalDateTime(newschedule.getArrivalDateTime())
                .departureDateTime(newschedule.getDepartureDateTime())
                .route(route1)
                .build();
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
    public Schedule getScheduleById(int scheduleId) throws ScheduleIdNotFoundException {
        logger.info("Getting schedule by ID: {}", scheduleId);
        Optional<Schedule> sc = scheduleRepository.findById(scheduleId);
        if(sc.isEmpty()){
            logger.error("Schedule with ID {} does not exist", scheduleId);
            throw new ScheduleIdNotFoundException("Schedule Id does not exist in the db!!");
        }
        Schedule schedule = sc.get();
        logger.info("Returning schedule: {}", schedule);
        return schedule;
    }

    @Override
    public Schedule updateSchedule(Schedule schedule) throws ScheduleIdNotFoundException {
        logger.info("Received request to update schedule: {}", schedule);
        if (getScheduleById(schedule.getScheduleId()) != null) {
            Schedule updatedSchedule = scheduleRepository.save(schedule);
            logger.info("Schedule updated successfully: {}", updatedSchedule);
            return updatedSchedule;
        }
        return null;
    }

    @Override
    public String deleteScheduleById(int scheduleId) throws ScheduleIdNotFoundException {
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
