package com.schedule.schedulemicroservice.service;


import com.route.routemicroservice.model.Route;
import com.schedule.schedulemicroservice.exception.ScheduleIdNotExistsException;
import com.schedule.schedulemicroservice.model.Schedule;
import com.schedule.schedulemicroservice.model.ScheduleRequest;
import com.schedule.schedulemicroservice.repository.ScheduleRepository;
import com.train.trainmicroservice.exception.TrainNumberNotExistsException;
import com.train.trainmicroservice.model.Train;
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
    private ScheduleRepository scheduleRepository;
    @Autowired
    private RestTemplate restTemplate;
    Logger logger = LoggerFactory.getLogger(ScheduleServiceImpl.class);


    @Override
    public Schedule addSchedule(ScheduleRequest scheduleRequest) {

        ResponseEntity<Train> train = restTemplate.getForEntity("http://localhost:8282/train-api/train/" + scheduleRequest.getTrainNumber(), Train.class);
        ResponseEntity<Route> route = restTemplate.getForEntity("http://localhost:8383/route-api/route/" + scheduleRequest.getRouteId(), Route.class);
        logger.info("Train Resources status: {} ", train.getStatusCode());
        logger.info("Train Resources status: {} ", train.getStatusCode());
        Train trainResponseBody = train.getBody();
        Route routeResponseBody = route.getBody();

        if (trainResponseBody!= null && routeResponseBody != null) {
            Schedule schedule = Schedule.builder()
                    .departureDateTime(scheduleRequest.getDepartureDateTime()).arrivalDateTime(scheduleRequest.getArrivalDateTime()).train(trainResponseBody).route(routeResponseBody).build();
            return scheduleRepository.save(schedule);
        }
        throw new HttpClientErrorException(HttpStatus.NOT_FOUND);
    }


    public List<Schedule> getAllSchedules(){
        return scheduleRepository.findAll();
    }

    public Schedule getScheduleById(int scheduleId) {
        Optional<Schedule> schedule=scheduleRepository.findById(scheduleId);
        if(schedule.isEmpty()){
            throw new ScheduleIdNotExistsException("ScheduleId Not Exist");
        }
        return schedule.get();
    }

    public Schedule updateSchedule(Schedule schedule){
        return scheduleRepository.save(schedule);
    }

    public String deleteScheduleByScheduleId(int scheduleId){
        Schedule schedule=getScheduleById(scheduleId);
        if(schedule==null){
            throw new ScheduleIdNotExistsException("ScheduleId Not Exist");
        }
        scheduleRepository.deleteById(scheduleId);
        return "Schedule Deleted Successfully";
    }

    @Override
    public List<Schedule> getScheduleByTrainNo(int trainNumber) {

       List<Schedule> list= scheduleRepository.findByTrain_trainNumber(trainNumber);
       if(list.isEmpty())
       {
           throw new TrainNumberNotExistsException("Train Number Not Exist");
       }
       return list;
    }


}
