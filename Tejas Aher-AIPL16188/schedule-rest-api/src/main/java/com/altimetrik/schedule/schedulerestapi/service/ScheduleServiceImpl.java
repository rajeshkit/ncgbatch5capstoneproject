package com.altimetrik.schedule.schedulerestapi.service;

import com.altimetrik.schedule.schedulerestapi.exception.RouteIdNotFoundException;
import com.altimetrik.schedule.schedulerestapi.exception.ScheduleIdNotFoundException;
import com.altimetrik.schedule.schedulerestapi.exception.TrainIdNotFoundException;
import com.altimetrik.schedule.schedulerestapi.model.NewScheduleRequest;
import com.altimetrik.schedule.schedulerestapi.model.Route;
import com.altimetrik.schedule.schedulerestapi.model.Schedule;
import com.altimetrik.schedule.schedulerestapi.model.Train;
import com.altimetrik.schedule.schedulerestapi.repository.RouteRepository;
import com.altimetrik.schedule.schedulerestapi.repository.ScheduleRepository;
import com.altimetrik.schedule.schedulerestapi.repository.TrainRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
public class ScheduleServiceImpl implements ScheduleService {


    Logger log = LoggerFactory.getLogger(ScheduleServiceImpl.class);
    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private ScheduleRepository scheduleRepository;
    @Autowired
    private TrainRepository trainRepository;
    @Autowired
    private RouteRepository routeRepository;

    @Override
    public Schedule addScheduleRequest(NewScheduleRequest newScheduleRequest) throws TrainIdNotFoundException, RouteIdNotFoundException {
        int scheduleID = new Random().nextInt(13561632) + 90192;

        ResponseEntity<Train> trainResponseEntity = restTemplate.getForEntity("http://TRAIN-RESTAPI/train-api/train/" + newScheduleRequest.getTrainId(), Train.class);
        Train train = trainResponseEntity.getBody();
        if (train == null) {
            throw new TrainIdNotFoundException("TrainId " + train.getTrainNumber() + " Not Found");
        }
        log.info("Train Response status {}", trainResponseEntity.getStatusCode());

        ResponseEntity<Route> routeResponseEntity = restTemplate.getForEntity("http://ROUTE-RESTAPI/route-api/route/" + newScheduleRequest.getRouteId(), Route.class);
        Route route = routeResponseEntity.getBody();
        if (route == null) {
            throw new RouteIdNotFoundException("RouteId " + route.getRouteId() + " Not Found");
        }
        log.info("Route Response status {}", routeResponseEntity.getStatusCode());
        Schedule schedule = Schedule.builder().scheduleId(String.valueOf(scheduleID)).arrivalDateTime(newScheduleRequest.getArrivalDateTime()).departureDateTime(newScheduleRequest.getDepartureDateTime()).train(train).route(route).build();

        trainRepository.save(train);
        routeRepository.save(route);
        scheduleRepository.save(schedule);

        return schedule;
    }

    @Override
    public List<Schedule> getAllSchedule() {
        return scheduleRepository.findAll();
    }

    @Override
    public Schedule getScheduleById(String scheduleId) throws ScheduleIdNotFoundException {
        Optional<Schedule> optionalSchedule = scheduleRepository.findById(scheduleId);
        optionalSchedule.orElseThrow(() -> new ScheduleIdNotFoundException("Schedule Id " + scheduleId + " Not Found"));
        if (optionalSchedule.isPresent()) {
            return optionalSchedule.get();
        }
        return null;
    }

    @Override
    public Schedule updateSchedule(NewScheduleRequest newScheduleRequest, String scheduleId) throws ScheduleIdNotFoundException {

        Schedule newSchedule;
        ResponseEntity<Train> trainResponseEntity = restTemplate.getForEntity("http://TRAIN-RESTAPI/train-api/train/" + newScheduleRequest.getTrainId(), Train.class);
        Train train = trainResponseEntity.getBody();
        ResponseEntity<Route> routeResponseEntity = restTemplate.getForEntity("http://ROUTE-RESTAPI/route-api/route/" + newScheduleRequest.getRouteId(), Route.class);
        Route route = routeResponseEntity.getBody();

        Schedule previousSchedule = getScheduleById(scheduleId);
        newSchedule = previousSchedule;

        if (previousSchedule != null) {

            newSchedule.setArrivalDateTime(newScheduleRequest.getArrivalDateTime());
            newSchedule.setDepartureDateTime(newScheduleRequest.getDepartureDateTime());
            newSchedule.setTrain(train);
            newSchedule.setRoute(route);

            trainRepository.save(train);
            routeRepository.save(route);
            scheduleRepository.save(newSchedule);

        }
        return newSchedule;
    }

    @Override
    public String deleteById(String scheduleId) throws ScheduleIdNotFoundException {
        Schedule schedule = getScheduleById(scheduleId);
        if (schedule != null) {
            scheduleRepository.deleteById(scheduleId);
            return "Schedule having id " + scheduleId + "deleted Successfully";
        } else {
            return "Schedule Id Not Found";
        }
    }
}
