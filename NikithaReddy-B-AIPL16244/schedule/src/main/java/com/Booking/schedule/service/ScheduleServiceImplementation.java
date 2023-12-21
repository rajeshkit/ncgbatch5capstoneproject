package com.Booking.schedule.service;

import com.Booking.route.customexception.RouteNotFindException;
import com.Booking.route.model.RouteResources;
import com.Booking.schedule.customexception.ScheduleNotFind;
import com.Booking.schedule.model.Schedule;
import com.Booking.schedule.model.ScheduleResources;
import com.Booking.schedule.repository.ScheduleRepository;
import com.Booking.train.customexception.TrainIdNotFoundException;
import com.Booking.train.model.TrainResources;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;
@Service
public class ScheduleServiceImplementation implements ScheduleService{
    @Autowired
    private ScheduleRepository scheduleRepository;
    @Autowired
    private RestTemplate restTemplate;
    Logger logging= LoggerFactory.getLogger(ScheduleServiceImplementation.class);
    @Override
    public ScheduleResources addSchedule(Schedule schedule) throws TrainIdNotFoundException, RouteNotFindException {
//        Long trainNumber = schedule.getTrainNumber();
//        Long routeId = schedule.getRouteId();
        ResponseEntity<TrainResources> trainResponseEntity = restTemplate.getForEntity("http://localhost:8181/train-api/Train_Table/" + schedule.getTrainNumber(), TrainResources.class);
        ResponseEntity<RouteResources> routeResponseEntity = restTemplate.getForEntity("http://localhost:8080/route-api/Route_Table/" + schedule.getRouteId(), RouteResources.class);
        logging.info("Train Resources status: {} ",trainResponseEntity.getStatusCode());
        logging.info("Train Resources status: {} ",trainResponseEntity.getStatusCode());
//        TrainResources t = restTemplate.getForObject("http://localhost:8181/train-api/Train_Table/" + schedule.getTrainNumber(), TrainResources.class);
//        RouteResources r = restTemplate.getForObject("http://localhost:8080/route-api/Route_Table/" + schedule.getRouteId(), RouteResources.class);
        TrainResources t=trainResponseEntity.getBody();
        RouteResources r=routeResponseEntity.getBody();
        if (t != null) {
            if (r != null) {
                ScheduleResources schedule1=ScheduleResources.builder().departureDateTime(schedule.getDepartureDateTime()).arrivalDateTime(schedule.getArrivalDateTime()).train(t).route(r).build();
                return scheduleRepository.save(schedule1);
            } else {
                throw new RouteNotFindException("No Route With that Id");
            }
        } else {
            throw new TrainIdNotFoundException("No Train with that number");
        }
    }
    @Override
    public List<ScheduleResources> getSchedule() {
        return scheduleRepository.findAll();
    }

    @Override
    public ScheduleResources getScheduleById(long scheduleId) throws ScheduleNotFind
    {
        Optional<ScheduleResources> scheduleResources = scheduleRepository.findById(scheduleId);
        if (scheduleResources.isEmpty()) {
            throw new ScheduleNotFind("No schedule With the selected id");
        }
        return scheduleResources.get();
    }


    @Override
    public ScheduleResources updateSchedule(ScheduleResources scheduleResources) throws ScheduleNotFind
    {
        if (getScheduleById(scheduleResources.getScheduleId()) != null) {
            return scheduleRepository.save(scheduleResources);
        }
        return null;
    }

    @Override
    public String deleteschedule(long scheduleId) throws ScheduleNotFind
    {
        String message = "Schedule Id doesn't Exist";
        ScheduleResources scheduleResources = getScheduleById(scheduleId);
        if (scheduleResources != null) {
            scheduleRepository.deleteById(scheduleId);
            message = "Schedule deleted succesfully";
            return message;
        }
        return message;
    }

    @Override
    public List<ScheduleResources> getSchedulesBytrainNumber(Long trainNumber)
    {
        return scheduleRepository.findByTrain_trainNumber(trainNumber);
    }
}
