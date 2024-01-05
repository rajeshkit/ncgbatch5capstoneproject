package com.trainbooking.schedulemicroservices.service;

import com.trainbooking.schedulemicroservices.exception.ScheduleIdNotExistException;
import com.trainbooking.schedulemicroservices.model.Route;
import com.trainbooking.schedulemicroservices.model.Schedule;
import com.trainbooking.schedulemicroservices.model.ScheduleRequest;
import com.trainbooking.schedulemicroservices.model.Train;
import com.trainbooking.schedulemicroservices.repository.RouteRepository;
import com.trainbooking.schedulemicroservices.repository.ScheduleRepository;
import com.trainbooking.schedulemicroservices.repository.TrainRepository;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import org.slf4j.Logger;

import java.util.List;
import java.util.Optional;

@Service
public class ScheduleServiceImpl implements ScheduleService {

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    ScheduleRepository scheduleRepository;

    @Autowired
    TrainRepository trainRepository;

    @Autowired
    RouteRepository routeRepository;

    Logger logger = LoggerFactory.getLogger(ScheduleServiceImpl.class);

    public Schedule addNewScheduleRequest(ScheduleRequest scheduleRequest){
        int trainNumber = scheduleRequest.getTrainNumber();

        //Executing train-microservice
        Train trainResponseObject = restTemplate.getForObject("http://localhost:8080/train-api/train/" + trainNumber, Train.class);
        logger.info("Response received from train {}", trainResponseObject);
        if (trainResponseObject != null) {
            trainRepository.save(trainResponseObject);
        }

        int routeId = scheduleRequest.getRouteId();

        //Executing route-microservice
        Route routeResponseObject = restTemplate.getForObject("http://localhost:8181/route-api/route/" + routeId, Route.class);
        logger.info("Response received from route {}", routeResponseObject);
        if (routeResponseObject != null) {
            routeRepository.save(routeResponseObject);
        }

        scheduleRepository.save(scheduleRequest);
        return Schedule.builder()
                .scheduleId(scheduleRequest.getScheduleId())
                .arrivalDateTime(scheduleRequest.getArrivalDateTime())
                .departureDateTime(scheduleRequest.getDepartureDateTime())
                .train(trainResponseObject)
                .route(routeResponseObject).build();
    }

    public List<ScheduleRequest> getAllScheduleDetails(){
        List<ScheduleRequest> allScheduls = scheduleRepository.findAll();
        logger.info("All Schedules are {}", allScheduls);
        return allScheduls;
    }

    public ScheduleRequest getScheduleByScheduleId(int scheduleId) throws ScheduleIdNotExistException {
        Optional<ScheduleRequest> scheduleRequest = scheduleRepository.findById(scheduleId);
        if(scheduleRequest.isPresent()){
            return scheduleRequest.get();
        } else {
            throw new ScheduleIdNotExistException("Schedule Id Not Exist !!");
        }
    }

    public ScheduleRequest updateScheduleDetails(ScheduleRequest scheduleRequest) throws ScheduleIdNotExistException {
        if(getScheduleByScheduleId(scheduleRequest.getScheduleId())!=null){
            ScheduleRequest scheduleRequest1 = scheduleRepository.save(scheduleRequest);
            logger.info("Schedule Details Updated Successfully");
            return scheduleRequest1;
        } else {
            throw new ScheduleIdNotExistException("Schedule Id Not Exist !");
        }
    }

    public String deleteScheduleByScheduleId(int scheduleId) throws ScheduleIdNotExistException {
        if(getScheduleByScheduleId(scheduleId)!=null){
            scheduleRepository.deleteById(scheduleId);
            logger.info("Schedule Deleted Successfully !!!");
            return "Route with RouteId : " + scheduleId + " Deleted Successfully !!!";
        } else {
            throw new ScheduleIdNotExistException("Schedule Id Not Exist !");
        }

    }

}
