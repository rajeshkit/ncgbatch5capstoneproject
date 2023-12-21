package com.finalproject.schedule.service;

import com.finalproject.schedule.exception.ScheduleNotFoundException;
import com.finalproject.schedule.model.RouteResponse;
import com.finalproject.schedule.model.Schedule;
import com.finalproject.schedule.model.ScheduleRequest;
import com.finalproject.schedule.model.TrainResponse;
import com.finalproject.schedule.repository.ScheduleRepository;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;


@Service
public class ScheduleServiceImplementation implements ScheduleService{

    @Autowired
    private RestTemplate restTemplate ;

    @Autowired
    private ScheduleRepository scheduleRepository ;

    Logger logger = LoggerFactory.getLogger(ScheduleServiceImplementation.class);

    @Override
    public Schedule addSchedule(ScheduleRequest scheduleRequest){
        int trainNumber = scheduleRequest.getTrainNumber();

        TrainResponse trainResponseObj = restTemplate.getForObject("http://localhost:8081/railways-api/railways/" +trainNumber,TrainResponse.class);
        logger.info("Data is received from the railway{}",trainResponseObj);


        int routeId =  scheduleRequest.getRouteId();

        RouteResponse routeResponseobj = restTemplate.getForObject("http://localhost:8082/route-restapi/route/" +routeId,RouteResponse.class);
        logger.info("Data is received from the route{}",routeResponseobj);


        Schedule schedule = Schedule.builder()
                .scheduleId(scheduleRequest.getScheduleId())
                .arrivalDateTime(scheduleRequest.getArrivalDateTime())
                .departureDateTime(scheduleRequest.getDepartureDateTime())
                .train(trainResponseObj)
                .route(routeResponseobj).build();

        scheduleRepository.save(scheduleRequest);
        return schedule;

    }

    public ScheduleRequest getScheduleById(int scheduleId){
        Optional<ScheduleRequest> scheduleResponse = scheduleRepository.findById(scheduleId);
        return scheduleResponse.orElse(null);
    }



    public List<ScheduleRequest> getSchedule(){
        List<ScheduleRequest> allScheduls = scheduleRepository.findAll();
        logger.info("All Schedules are {}  ", allScheduls);
        return allScheduls;
    }

    public ScheduleRequest getScheduleByScheduleId(int scheduleId) throws ScheduleNotFoundException {
        Optional<ScheduleRequest> scheduleRequest = scheduleRepository.findById(scheduleId);
        if(scheduleRequest.isPresent()){
            return scheduleRequest.get();
        } else {
            throw new ScheduleNotFoundException();
        }
    }


    public String deleteSchedule(int scheduleId) throws  ScheduleNotFoundException {
        if(getScheduleByScheduleId(scheduleId)!=null){
            scheduleRepository.deleteById(scheduleId);
            logger.info("Schedule Deleted Successfully !!!");
            return "Route with RouteId : " + scheduleId + " Deleted Successfully !!!";
        } else {
            throw new  ScheduleNotFoundException();
        }

    }


}
