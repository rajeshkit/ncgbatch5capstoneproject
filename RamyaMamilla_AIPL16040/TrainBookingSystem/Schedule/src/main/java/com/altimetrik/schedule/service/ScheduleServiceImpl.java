package com.altimetrik.schedule.service;

import com.altimetrik.route.model.Route;
import com.altimetrik.schedule.exception.ScheduleIDNotFoundException;
import com.altimetrik.schedule.model.Schedule;
import com.altimetrik.schedule.model.ScheduleRequest;
import com.altimetrik.schedule.repository.ScheduleRepository;
import com.altimetrik.train.model.Train;
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

    private ScheduleRepository scheduleRepository;
    private RestTemplate restTemplate;

    @Autowired
    public ScheduleServiceImpl(ScheduleRepository scheduleRepository, RestTemplate restTemplate) {
        this.scheduleRepository = scheduleRepository;
        this.restTemplate = restTemplate;
    }

    Logger logger = LoggerFactory.getLogger(ScheduleServiceImpl.class);
    
    @Override
    public Schedule addSchedule(ScheduleRequest scheduleRequest) {
        int trainNo= scheduleRequest.getTrainNumber();
        int routeIdg= scheduleRequest.getRouteID();
        ResponseEntity<Train> trainEntity =restTemplate.getForEntity("http://localhost:8082/train-api/train/"+trainNo, Train.class);
        ResponseEntity<Route> routeEntity =restTemplate.getForEntity("http://localhost:8081/route-api/route/"+routeIdg,Route.class);
        logger.info("Train Resources status: {} ",trainEntity.getStatusCode());
        logger.info("Route Resources status: {} ",routeEntity.getStatusCode());
        Train trainResponse=trainEntity.getBody();
        Route routeResponse=routeEntity.getBody();
        if(trainResponse!=null && (routeResponse!=null)){
                Schedule schedule1=Schedule.builder()
                        .train(trainResponse)
                        .route(routeResponse)
                        .departureDateTime(scheduleRequest.getDepartureDateTime())
                        .arrivalDateTime(scheduleRequest.getArrivalDateTime())
                        .build();
                return scheduleRepository.save(schedule1);

        }throw new HttpClientErrorException(HttpStatus.NOT_FOUND);
    }

    @Override
    public List<Schedule> viewAllSchedules() {
        return scheduleRepository.findAll();
    }

    @Override
    public Schedule getScheduleById(int scheduleId) throws ScheduleIDNotFoundException {
        Optional<Schedule> s1=scheduleRepository.findById(scheduleId);
        if (s1.isEmpty()){
            throw new ScheduleIDNotFoundException("Schedule ID not found");
        }
        return s1.get();
    }

    @Override
    public Schedule updateSchedule(Schedule schedule) throws ScheduleIDNotFoundException {
        if (getScheduleById(schedule.getScheduleId())!=null){
            return scheduleRepository.save(schedule);
        }
        return null;
    }

    @Override
    public String deleteScheduleById(int scheduleId) throws ScheduleIDNotFoundException {
        Schedule s=getScheduleById(scheduleId);
        if (s!=null){
            scheduleRepository.deleteById(scheduleId);
            return "Schedule Deleted Successfully";
        }
        return "There is no Schedule with the Specified ID";
    }
    @Override
    public List<Schedule> getScheduleByTrainNo(int trainNum){
        return scheduleRepository.findByTrain_trainNo(trainNum);
    }
}
