package com.schedule.schedulemicroservice.service;

import com.route.routemicroservice.model.Route;
import com.schedule.schedulemicroservice.exception.*;
import com.schedule.schedulemicroservice.model.Schedule;
import com.schedule.schedulemicroservice.model.ScheduleRequest;
import com.schedule.schedulemicroservice.repository.ScheduleRepository;
import com.train.trainmicroservice.model.Train;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ScheduleServiceImpl implements ScheduleService{
    @Autowired
    private ScheduleRepository scheduleRepository;
    @Autowired
    private RestTemplate restTemplate;


    @Override
    public Schedule addSchedule(ScheduleRequest scheduleRequest) {
        Schedule schedule=convertToSchedule(scheduleRequest);
        if(scheduleRequest.getTrainNumber()<=0 || scheduleRequest.getRouteId()<=0){
            throw new InvalidScheduleRequestException("Invalid schedule request.Train ID and route ID are required..!");
        }
        validateDateTimeRange(scheduleRequest.getDepartureDateTime(),scheduleRequest.getArrivalDateTime());
        if(!verifyOverlapping(scheduleRequest).isEmpty()){
            throw new OverlappingScheduleException("Overlap Schedules Detected..!");
        }
        return scheduleRepository.save(schedule);


    }

    @Override
    public List<Schedule> getAllSchedules() {
        return scheduleRepository.findAll();

    }

    @Override
    public Schedule getScheduleById(int scheduleId) {
        return scheduleRepository.findById(scheduleId).orElseThrow(()->new ScheduleNotFoundException("Schedule not found with id: "+scheduleId));

    }

    @Override
    public Schedule updateSchedule(int scheduleId, ScheduleRequest scheduleRequest) {
        Schedule existingSchedule=getScheduleById(scheduleId);
        existingSchedule.setDepartureDateTime(scheduleRequest.getDepartureDateTime());
        existingSchedule.setArrivalDateTime(scheduleRequest.getArrivalDateTime());
        existingSchedule.setTrain(restTemplate.getForObject("http://localhost:8080/train/"+scheduleRequest.getTrainNumber(),Train.class, ));
        existingSchedule.setRoute(restTemplate.getForObject("http://localhost:8181/route/{routeId}",Route.class,scheduleRequest.getRouteId()));
        return scheduleRepository.save(existingSchedule);
    }

    @Override
    public void deleteSchedule(int scheduleId) {
        //Schedule schedule=getScheduleById(scheduleId);
        if(!scheduleRepository.existsById(scheduleId)){
            throw new ScheduleOperationException("Schedule with ID "+scheduleId+" not found..! Deletion failed..!");
        }
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
    public void validateDateTimeRange(Date departureDateTime,Date arrivalDateTime){
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

    private boolean isOverlapping(Schedule schedule,Date start,Date end) {
        return (schedule.getDepartureDateTime().before(end) && schedule.getArrivalDateTime().after(start))
                || (schedule.getDepartureDateTime().before(end) && schedule.getArrivalDateTime().after(end))
                || (schedule.getDepartureDateTime().before(start) && schedule.getArrivalDateTime().after(start))
                || (schedule.getDepartureDateTime().before(start) && schedule.getArrivalDateTime().after(end));
    }
    }

