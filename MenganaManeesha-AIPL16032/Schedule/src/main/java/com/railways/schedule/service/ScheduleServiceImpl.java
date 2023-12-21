package com.railways.schedule.service;

import com.railways.route.model.Route;
import com.railways.schedule.exception.ScheduleNotFind;
import com.railways.schedule.model.NewScheduleResource;
import com.railways.schedule.model.Schedule;
import com.railways.schedule.repository.ScheduleRepository;
import com.railways.train.model.Train;
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
public class ScheduleServiceImpl implements ScheduleService {
    @Autowired
    private ScheduleRepository scheduleRepository;
    @Autowired
    private RestTemplate restTemplate;
    Logger logger = LoggerFactory.getLogger(ScheduleServiceImpl.class);

    @Override
    public Schedule addSchedule(NewScheduleResource schedule) {
        ResponseEntity<Train> trainResponseEntity = restTemplate.getForEntity("http://localhost:8080/train-api/train/" +schedule.getTrainNumber(), Train.class);
        ResponseEntity<Route> routeResponseEntity = restTemplate.getForEntity("http://localhost:8888/route-api/route/" + schedule.getRouteId(),  Route.class);
        logger.info("Train Resources status: {} ",trainResponseEntity.getStatusCode());
        logger.info("Train Resources status: {} ",trainResponseEntity.getStatusCode());
        Train trainResponseBody=trainResponseEntity.getBody();
        Route routeResponseBody=routeResponseEntity.getBody();
        if (trainResponseBody != null && routeResponseBody != null) {
            Schedule schedule1 = Schedule.builder().departureDateTime(schedule.getDepartureDateTime()).arrivalDateTime(schedule.getArrivalDateTime()).train(trainResponseBody).route(routeResponseBody).build();
            return scheduleRepository.save(schedule1);
        }
        throw new HttpClientErrorException(HttpStatus.NOT_FOUND);
    }


    @Override
    public List<Schedule> getSchedule() {
        return scheduleRepository.findAll();
    }

    @Override
    public Schedule getScheduleById(long scheduleId) throws ScheduleNotFind {
        Optional<Schedule> schedule = scheduleRepository.findById(scheduleId);
        if (schedule.isEmpty()) {
            throw new ScheduleNotFind("No schedule With the selected id");
        }
        return schedule.get();
    }

    @Override
    public Schedule updateSchedule(Schedule schedule) throws ScheduleNotFind {
        if (getScheduleById(schedule.getScheduleId()) != null) {
            return scheduleRepository.save(schedule);
        }
        return null;
    }

    @Override
    public String deleteschedule(long scheduleId) throws ScheduleNotFind {
        String message = "Schedule Id doesn't Exist";
        Schedule schedule = getScheduleById(scheduleId);
        if (schedule != null) {
            scheduleRepository.deleteById(scheduleId);
            message = "Schedule deleted succesfully";
            return message;
        }
        return message;
    }
    @Override
    public List<Schedule> getSchedulesByTrainNumber(Long trainNumber) {
        return scheduleRepository.findByTrain_TrainNumber(trainNumber);
    }
}



