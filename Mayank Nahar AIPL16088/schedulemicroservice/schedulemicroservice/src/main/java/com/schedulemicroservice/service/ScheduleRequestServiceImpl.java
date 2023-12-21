package com.schedulemicroservice.service;

import com.schedulemicroservice.exception.ScheduleIdNotExistException;
import com.schedulemicroservice.model.Route;
import com.schedulemicroservice.model.Schedule;
import com.schedulemicroservice.model.ScheduleRequest;
import com.schedulemicroservice.model.Train;
import com.schedulemicroservice.repository.ScheduleRequestRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j // used for logging
public class ScheduleRequestServiceImpl implements ScheduleRequestService {
    @Autowired
    ScheduleRequestRepository scheduleRequestRepository;
    @Autowired
    RestTemplate restTemplate;

    @Override
    public Schedule addScheduleDetails(ScheduleRequest scheduleRequest) {
        scheduleRequestRepository.save(scheduleRequest);
        int trainId = scheduleRequest.getTrainId();
        int routeId = scheduleRequest.getRouteId();

        Train trainRestTemplateForObject = restTemplate.getForObject("http://localhost:8181/train-api/train/" + trainId, Train.class);
        log.info("Train detail fetch successfully {}", trainRestTemplateForObject);
        Route routeRestTemplateForObject = restTemplate.getForObject("http://localhost:8182/route-api/route/" + routeId, Route.class);
        log.info("Route detail fetch successfully {}", routeRestTemplateForObject);

        return Schedule.builder()
                .scheduleId(scheduleRequest.getScheduleId())
                .arrivalDateTime(scheduleRequest.getArrivalDateTime())
                .departureDateTime(scheduleRequest.getDepartureDateTime())
                .route(routeRestTemplateForObject)
                .train(trainRestTemplateForObject).build();
    }
    @Override
    public List<ScheduleRequest> getAllScheduleRequest() {

        return scheduleRequestRepository.findAll();
    }
    @Override
    public ScheduleRequest getScheduleRequestById(int scheduleId) throws ScheduleIdNotExistException {
        Optional<ScheduleRequest> scheduleRequestOptional= scheduleRequestRepository.findById(scheduleId);
        if(scheduleRequestOptional.isEmpty()){
            log.info("Schedule Id Not Fetch");
            throw new ScheduleIdNotExistException("Schedule Id Not Found In Database Please Check Details");
        }
        log.info("Schedule By Id Successfully Fetch");
        return scheduleRequestOptional.get();
    }
    @Override
    public ScheduleRequest updateScheduleRequest(ScheduleRequest scheduleRequest) throws ScheduleIdNotExistException {
        if(getScheduleRequestById(scheduleRequest.getScheduleId())!=null){
            log.info("Schedule Request Update Successfully");
            return scheduleRequestRepository.save(scheduleRequest);
        }
        return null;

    }
    @Override
    public String deleteScheduleRequestById(int scheduleId) throws ScheduleIdNotExistException {
        String msg="Schedule Id Not Exist";
        ScheduleRequest scheduleRequest=getScheduleRequestById(scheduleId);
        if(scheduleRequest!=null){
            scheduleRequestRepository.deleteById(scheduleId);
            msg="Schedule Request Deleted";
            log.info("Schedule Request Deleted Successfully");
            return msg;
        }
        return msg;
    }



}








