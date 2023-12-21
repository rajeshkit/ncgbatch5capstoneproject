package com.altimetrik.schedulemicroservice.service;
import com.altimetrik.schedulemicroservice.exception.ScheduleIdNotExistException;
import com.altimetrik.schedulemicroservice.model.Route;
import com.altimetrik.schedulemicroservice.model.Schedule;
import com.altimetrik.schedulemicroservice.model.ScheduleRequest;
import com.altimetrik.schedulemicroservice.model.Train;
import com.altimetrik.schedulemicroservice.respository.ScheduleRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;

@Service
public class ScheduleServiceImplementation implements ScheduleService {
    @Autowired
    RestTemplate restTemplate;
    @Autowired
    private ScheduleRepository scheduleRepository;
    private static final Logger logger = LoggerFactory.getLogger(ScheduleRequest.class);

    @Override
    public Schedule addScheduleRequest(ScheduleRequest scheduleRequest) {

        int trainNumber = scheduleRequest.getTrainId();
        int routeId = scheduleRequest.getRouteId();

        Train train = restTemplate.getForObject("http://localhost:8080/train-api/train/" + trainNumber, Train.class);
        Route route = restTemplate.getForObject("http://localhost:8081/route-api/route/" + routeId, Route.class);

        scheduleRepository.save(scheduleRequest);
        return Schedule.builder()
                .scheduleId(scheduleRequest.getScheduleId())
                .arrivalDateTime(scheduleRequest.getArrivalDateTime())
                .departureDateTime(scheduleRequest.getDepartureDateTime())
                .train(train)
                .route(route)
                .build();
    }

    @Override
    public List<ScheduleRequest> getAllScheduleDetails() {
        List<ScheduleRequest> allScheduls = scheduleRepository.findAll();
        logger.info("All Schedules are {}", allScheduls);
        return allScheduls;
    }

    @Override
    public ScheduleRequest getScheduleById(int scheduleId) throws ScheduleIdNotExistException {
        Optional<ScheduleRequest> scheduleRequest = scheduleRepository.findById(scheduleId);
        if (scheduleRequest.isPresent()) {
            return scheduleRequest.get();
        } else {
            throw new ScheduleIdNotExistException("Schedule Id Not Exist !!");
        }
    }

    @Override
    public ScheduleRequest updateScheduleDetails(ScheduleRequest scheduleRequest) throws ScheduleIdNotExistException {

        if (getScheduleById(scheduleRequest.getScheduleId()) != null) {
            ScheduleRequest scheduleRequest1 = (ScheduleRequest) scheduleRepository.save(scheduleRequest);
            logger.info("Schedule Details Updated Successfully");
            return scheduleRequest1;
        } else {
            throw new ScheduleIdNotExistException("Schedule Id Not Exist !");
        }
    }

    @Override
    public String deleteScheduleById(int scheduleId) throws ScheduleIdNotExistException {
        if (getScheduleById(scheduleId) != null) {
            scheduleRepository.deleteById(scheduleId);
            logger.info("Schedule Deleted Successfully !!!");
            return "Route with RouteId : " + scheduleId + " Deleted Successfully !!!";
        } else {
            throw new ScheduleIdNotExistException("Schedule Id Not Exist !");
        }
    }
}



