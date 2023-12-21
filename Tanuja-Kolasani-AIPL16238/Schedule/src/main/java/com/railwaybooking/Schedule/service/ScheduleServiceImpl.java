package com.railwaybooking.Schedule.service;

import com.railwaybooking.Route.exception.RouteIdNotFoundException;
import com.railwaybooking.Route.model.RouteInfo;
import com.railwaybooking.Schedule.exception.ScheduleNotFoundException;
import com.railwaybooking.Schedule.model.NewScheduleInfo;
import com.railwaybooking.Schedule.model.Schedule;
import com.railwaybooking.Schedule.repository.ScheduleRepository;
import com.railwaybooking.Train.exception.TrainNumberNotExistException;
import com.railwaybooking.Train.model.TrainInfo;
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
    Logger l= LoggerFactory.getLogger(ScheduleServiceImpl.class);
    @Override
    public Schedule addSchedule(NewScheduleInfo schedule)  {
        ResponseEntity<TrainInfo> trainEntity = restTemplate.getForEntity("http://localhost:9081/trainInfo-api/trainInfo/" + schedule.getTrainNumber(), TrainInfo.class);
        ResponseEntity<RouteInfo> routeEntity = restTemplate.getForEntity("http://localhost:9082/routeInfo-api/routeInfo/" + schedule.getRouteId(), RouteInfo.class);
        l.info("Train  status: {} ", trainEntity.getStatusCode());
        l.info("Route  status: {} ", routeEntity.getStatusCode());
        TrainInfo trainInfo = trainEntity.getBody();
        RouteInfo routeInfo = routeEntity.getBody();
        if (trainInfo != null && routeInfo != null) {
            Schedule schedule1=Schedule.builder().departureDateTime(schedule.getDepartureDateTime()).arrivalDateTime(schedule.getArrivalDateTime()).trainInfo(trainInfo).routeInfo(routeInfo).build();
            return scheduleRepository.save(schedule1);
        }
        throw new HttpClientErrorException(HttpStatus.NOT_FOUND);
    }

    @Override
    public List<Schedule> getSchedule() {
        return scheduleRepository.findAll();
    }

    @Override
    public Schedule getScheduleById(long scheduleId) throws ScheduleNotFoundException {
        Optional<Schedule> schedule=scheduleRepository.findById(scheduleId);
        if(schedule.isEmpty()){
            throw new ScheduleNotFoundException("No Schedule found with the Id");
        }
        return schedule.get();
    }

    @Override
    public Schedule updateSchedule(Schedule schedule) throws ScheduleNotFoundException {
        if(getScheduleById(schedule.getScheduleId())!=null){
            return scheduleRepository.save(schedule);
        }
        return null;
    }

    @Override
    public String deleteScheduleById(long scheduleId) throws ScheduleNotFoundException {
        String message="Schedule Id not found";
        Schedule scheduleInfo=getScheduleById(scheduleId);
        if(scheduleInfo!=null){
            scheduleRepository.deleteById(scheduleId);
            message="Schedule deleted successfully";
            return message;
        }
        return message;
    }

    @Override
    public List<Schedule> getScheduleInfoByTrainNumber(Long trainNumber) {
        return scheduleRepository.findByTrainInfo_TrainNumber(trainNumber);
    }
}
