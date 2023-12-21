package com.booking.schedule.service;

import com.booking.route.exception.RouteIdNotExistsException;
import com.booking.route.model.RouteResources;
import com.booking.schedule.exception.ScheduleIdNotExistsException;
import com.booking.schedule.model.NewScheduleRequest;
import com.booking.schedule.model.Schedule;
import com.booking.schedule.repository.ScheduleRepository;
import com.booking.train.exception.TrainNumberNotExistsException;
import com.booking.train.model.TrainResources;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;

@Service
public class ScheduleServiceImpl implements ScheduleService{
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private ScheduleRepository scheduleRepository;

    Logger logger= LoggerFactory.getLogger(ScheduleServiceImpl.class);

    @Override
    public Schedule addScheduleResources(NewScheduleRequest newScheduleRequest) throws RouteIdNotExistsException, TrainNumberNotExistsException {
        String trainUrl = "http://localhost:8181/train-api/train/" + newScheduleRequest.getTrainNumber();
        String routeUrl = "http://localhost:8282/route-api/route/" + newScheduleRequest.getRouteId();

        ResponseEntity<TrainResources> trainResourcesResponseEntity = restTemplate.getForEntity(trainUrl, TrainResources.class);
        ResponseEntity<RouteResources> routeResourcesResponseEntity = restTemplate.getForEntity(routeUrl, RouteResources.class);
        logger.info("Train Resources status: {} ", trainResourcesResponseEntity.getStatusCode());
        logger.info("Route Resource status: {} ", routeResourcesResponseEntity.getStatusCode());

        TrainResources trainResponseBody=trainResourcesResponseEntity.getBody();
        RouteResources routeResponseBody=routeResourcesResponseEntity.getBody();

        if (trainResponseBody != null)
        {
            if(routeResponseBody!=null){
                Schedule schedule1=Schedule.builder().departureDateTime(newScheduleRequest.getDepartureDateTime())
                        .arrivalDateTime(newScheduleRequest.getArrivalDateTime()).train(trainResponseBody)
                        .route(routeResponseBody).build();
                return scheduleRepository.save(schedule1);
            }
        }throw new HttpClientErrorException(HttpStatus.NOT_FOUND);

    }

    @Override
    public Schedule getScheduleResourcesById(Long scheduleId) throws ScheduleIdNotExistsException {
        Optional<Schedule> pro= scheduleRepository.findById(scheduleId);
        if(pro.isEmpty())
        {
            throw new ScheduleIdNotExistsException("Schedule Id not exists in db..!");
        }
        return pro.get();
    }

    @Override
    public List<Schedule> getAllScheduleResources() {
        return scheduleRepository.findAll();
    }

    @Override
    public Schedule updateSchedule(Schedule schedule) throws ScheduleIdNotExistsException {
        if (getScheduleResourcesById(schedule.getScheduleId()) != null) {
            scheduleRepository.save(schedule);
        }
        return schedule;
    }

    @Override
    public String deleteScheduleById(Long scheduleId) throws ScheduleIdNotExistsException {
        String message="Train Number is not Available in Schedule Table";
        Schedule schedule=getScheduleResourcesById(scheduleId);
        if(schedule!=null)
        {
            scheduleRepository.deleteById(scheduleId);
            message="Train Resource is deleted Successfully in Schedule Table ";
            return message;
        }
        return  message;
    }



}
