package com.schedule.schedulemicroservice.service;

import com.route.routemicroservice.model.Route;
import com.schedule.schedulemicroservice.exception.*;
import com.schedule.schedulemicroservice.model.Schedule;
import com.schedule.schedulemicroservice.model.ScheduleRequest;
import com.schedule.schedulemicroservice.repository.ScheduleRepository;
import com.train.trainmicroservice.model.Train;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.sql.Timestamp;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ScheduleServiceImpl implements ScheduleService {
    private static final Logger logger = LoggerFactory.getLogger(ScheduleServiceImpl.class);

    @Autowired
    private ScheduleRepository scheduleRepository;

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public Schedule addSchedule(ScheduleRequest scheduleRequest) {
        logger.info("Adding a new schedule...");

        Schedule schedule = convertToSchedule(scheduleRequest);

        if (scheduleRequest.getTrainNumber() <= 0 || scheduleRequest.getRouteId() <= 0) {
            logger.error("Invalid Train ID and route ID. Please enter valid details.");
            throw new InvalidScheduleRequestException("Invalid Train ID and route ID. Please enter valid details.");
        }

        validateDateTimeRange(scheduleRequest.getDepartureDateTime(), scheduleRequest.getArrivalDateTime());

        List<Schedule> overlappingSchedules = verifyOverlapping(scheduleRequest);

        if (!overlappingSchedules.isEmpty()) {
            logger.error("Overlap schedules detected.");
            throw new OverlappingScheduleException("Overlap schedules detected.");
        }

        logger.info("Saving schedule to the database.");
        return scheduleRepository.save(schedule);
    }

    @Override
    public List<Schedule> getAllSchedules() {
        logger.info("Getting all schedules...");
        return scheduleRepository.findAll();
    }

    @Override
    public Schedule getScheduleById(int scheduleId) {
        logger.info("Getting schedule by ID: {}", scheduleId);
        return scheduleRepository.findById(scheduleId).orElseThrow(() ->
                new ScheduleNotFoundException("Schedule not found with ID: " + scheduleId));
    }

    @Override
    public Schedule updateSchedule(int scheduleId, ScheduleRequest scheduleRequest) {
        logger.info("Updating schedule with ID: {}", scheduleId);
        Schedule existingSchedule = getScheduleById(scheduleId);

        existingSchedule.setDepartureDateTime(scheduleRequest.getDepartureDateTime());
        existingSchedule.setArrivalDateTime(scheduleRequest.getArrivalDateTime());
        existingSchedule.setTrain(restTemplate.getForObject("http://localhost:8080/train/{trainNumber}", Train.class, scheduleRequest.getTrainNumber()));
        existingSchedule.setRoute(restTemplate.getForObject("http://localhost:8181/route/{routeId}", Route.class, scheduleRequest.getRouteId()));

        logger.info("Saving updated schedule to the database.");
        return scheduleRepository.save(existingSchedule);
    }

    @Override
    public void deleteSchedule(int scheduleId) {
        logger.info("Deleting schedule with ID: {}", scheduleId);
        if (!scheduleRepository.existsById(scheduleId)) {
            logger.error("Schedule with ID {} not found. Deletion failed.", scheduleId);
            throw new ScheduleOperationException("Schedule with ID " + scheduleId + " not found. Deletion failed.");
        }

        logger.info("Deleting schedule from the database.");
        scheduleRepository.deleteById(scheduleId);
    }
    //converting the new Schedule to schedule object
    private Schedule convertToSchedule(ScheduleRequest scheduleRequest){
        Train train =restTemplate.getForObject("http://localhost:8080/train/{trainNumber}",Train.class, scheduleRequest.getTrainNumber());
        Route route=restTemplate.getForObject("http://localhost:8181/route/{routeId}",Route.class,scheduleRequest.getRouteId());
        Schedule schedule=new Schedule();
        schedule.setDepartureDateTime(scheduleRequest.getDepartureDateTime());
        schedule.setArrivalDateTime(scheduleRequest.getArrivalDateTime());
        schedule.setTrain(train);
        schedule.setRoute(route);
        return schedule;
    }

    //validation of Arrival date and departure Dates
    public void validateDateTimeRange(Timestamp departureDateTime,Timestamp arrivalDateTime){
        if(arrivalDateTime.before(departureDateTime)){
            throw new InvalidDateTimeException("Arrival date can not be before departure date.");
        }
    }

    //overlapping means schedule A has 10:00 to 2:00 and schedule B has 1:00 to 3:00
    // then it's showing overlap timings of same train
    private List<Schedule> verifyOverlapping(ScheduleRequest scheduleRequest) {
        List<Schedule> allSchedules=scheduleRepository.findAll();
        return allSchedules.stream().filter(schedule ->
                isOverlapping(schedule, scheduleRequest.getDepartureDateTime(),
                        scheduleRequest.getArrivalDateTime()))
                .collect(Collectors.toList());
    }

    private boolean isOverlapping(Schedule schedule, Timestamp start, Timestamp end) {
        return (schedule.getDepartureDateTime().before(end) && schedule.getArrivalDateTime().after(start))
                || (schedule.getDepartureDateTime().before(end) && schedule.getArrivalDateTime().after(end))
                || (schedule.getDepartureDateTime().before(start) && schedule.getArrivalDateTime().after(start))
                || (schedule.getDepartureDateTime().before(start) && schedule.getArrivalDateTime().after(end));
    }
    }

