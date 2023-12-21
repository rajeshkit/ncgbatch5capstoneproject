package com.altimetrik.schedule.service;

import com.altimetrik.schedule.exception.ScheduleIdNotExistException;
import com.altimetrik.schedule.model.Route;
import com.altimetrik.schedule.model.Schedule;
import com.altimetrik.schedule.model.ScheduleRequest;
import com.altimetrik.schedule.model.Train;
import com.altimetrik.schedule.repository.ScheduleRequestRepository;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
@Log
@Service
public class ScheduleRequestServiceImpl implements ScheduleRequestService{

    @Autowired
    private ScheduleRequestRepository scheduleRequestRepository;
    @Autowired
    private RestTemplate restTemplate;

    public Train getTrainByNumber(int trainNumber){

        String apiUrl = "http://localhost:8282/train-api/train/"+trainNumber;

        return restTemplate.getForObject(apiUrl,Train.class);

    }

    public Route getRouteById(int routeId){

        String apiUrl = "http://localhost:8181/route-api/route/"+routeId;

        return restTemplate.getForObject(apiUrl,Route.class);
    }

    @Override
    public Schedule addSchedule(ScheduleRequest scheduleRequest) {
        log.info("Inserting ScheduleRequest Docs with scheduleId: "+scheduleRequest.getScheduleId());
        scheduleRequestRepository.save(scheduleRequest);
        return Schedule.builder()
                .scheduleId(scheduleRequest.getScheduleId())
                .departureDateTime(scheduleRequest.getDepartureDateTime())
                .arrivalDateTime(scheduleRequest.getArrivalDateTime())
                .train(getTrainByNumber(scheduleRequest.getTrainNumber()))
                .route(getRouteById(scheduleRequest.getRouteId()))
                .build();
    }

    @Override
    public List<Schedule> getAllScheduleRequests() {
        System.out.println("Getting all Schedule and Schedule Requests.");
        log.info("Fetching all Schedule Request docs.");
        List<ScheduleRequest> scheduleRequestsObj = scheduleRequestRepository.findAll();
        List<Schedule> scheduleObj = new ArrayList<>();
        for (ScheduleRequest scheduleRequest:scheduleRequestsObj){
            scheduleObj.add(Schedule.builder()
                    .scheduleId(scheduleRequest.getScheduleId())
                    .departureDateTime(scheduleRequest.getDepartureDateTime())
                    .arrivalDateTime(scheduleRequest.getArrivalDateTime())
                    .train(getTrainByNumber(scheduleRequest.getTrainNumber()))
                    .route(getRouteById(scheduleRequest.getRouteId()))
                    .build());
        }
        return scheduleObj;
    }

    @Override
    public Schedule getScheduleRequestByScheduleId(int scheduleId) throws ScheduleIdNotExistException {
        Optional<ScheduleRequest> scheduleRequestObj = scheduleRequestRepository.findById(scheduleId);
        if(scheduleRequestObj.isPresent()){
            System.out.println("Getting Schedule Request with scheduleId.");
            log.info("Fetching Schedule Request docs with scheduleId: "+scheduleId);
            return Schedule.builder()
                    .scheduleId(scheduleRequestObj.get().getScheduleId())
                    .departureDateTime(scheduleRequestObj.get().getDepartureDateTime())
                    .arrivalDateTime(scheduleRequestObj.get().getArrivalDateTime())
                    .train(getTrainByNumber(scheduleRequestObj.get().getTrainNumber()))
                    .route(getRouteById(scheduleRequestObj.get().getRouteId()))
                    .build();
        }
        else {
            throw new ScheduleIdNotExistException("Schedule Id not Exist.");
        }

    }

    @Override
    public ScheduleRequest updateScheduleRequest(ScheduleRequest scheduleRequest) throws ScheduleIdNotExistException {
        if (getScheduleRequestByScheduleId(scheduleRequest.getScheduleId())!=null){
            System.out.println("Schedule Request Details Updated Successfully.");
            log.info("Updating Schedule Request docs with scheduleId: "+scheduleRequest.getScheduleId());
            return scheduleRequestRepository.save(scheduleRequest);
        }
        else {
            throw new ScheduleIdNotExistException("Schedule Id not Exist.");
        }
    }

    @Override
    public String deleteScheduleByScheduleId(int scheduleId) throws ScheduleIdNotExistException {
        log.info("Deleting Schedule Request docs with scheduleId: "+scheduleId);
        scheduleRequestRepository.deleteById(scheduleId);
        return "Schedule Deleted Successfully";
    }

}
