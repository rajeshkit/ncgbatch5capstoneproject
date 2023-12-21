package com.schedulemicroservice.service;

import com.schedulemicroservice.exception.RouteNotFoundException;
import com.schedulemicroservice.exception.ScheduleIdDoesNotExistException;
import com.schedulemicroservice.exception.TrainNotFoundException;
import com.schedulemicroservice.model.Route;
import com.schedulemicroservice.model.Schedule;
import com.schedulemicroservice.model.ScheduleRequest;
import com.schedulemicroservice.model.Train;
import com.schedulemicroservice.repository.RouteRepository;
import com.schedulemicroservice.repository.ScheduleRepository;
import com.schedulemicroservice.repository.TrainRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java.util.List;
import java.util.Optional;

@Service
public class ScheduleServiceImpl implements ScheduleService{
    @Autowired
    private RestTemplate restTemplate;
    private static final Logger logger = LoggerFactory.getLogger(ScheduleServiceImpl.class);
    @Autowired
    private ScheduleRepository scheduleRepository;
    @Autowired
    private TrainRepository trainRepository;
    @Autowired
    private RouteRepository routeRepository;

    @Override
    public Schedule createSchedule(ScheduleRequest request) throws TrainNotFoundException, RouteNotFoundException {

        logger.info("Received a request to create schedule: {}", request);

        Train train = restTemplate.getForObject("http://localhost:8080/train-api/"+request.getTrainNumber(), Train.class );
        if (train == null) {
            throw new TrainNotFoundException("Train with ID " + request.getTrainNumber() + " not found.");
        }
        logger.info("Fetched train information: {}", train);
        Route route = restTemplate.getForObject("http://localhost:8081/route-api/"+request.getRouteId(), Route.class);
        if (route == null) {
            throw new RouteNotFoundException("Route with ID " + request.getRouteId() + "is not found");
        }
        logger.info("Fetched route information: {}", route);

        Schedule schedule =  Schedule.builder()
                .departureDateTime(request.getDepartureDateTime())
                .arrivalDateTime(request.getArrivalDateTime())
                .train(train)
                .route(route)
                .build();

        scheduleRepository.save(schedule);
        logger.info("Schedule created: {}", schedule);
        return schedule;
    }

    @Override
    public List<Schedule> getAllSchedules() {

        return scheduleRepository.findAll();
    }

    @Override
    public Schedule getScheduleDetailsById(int scheduleId) throws ScheduleIdDoesNotExistException {

        return scheduleRepository.findById(scheduleId).orElseThrow(() -> new ScheduleIdDoesNotExistException("No Schedule Found for this schedule id : " + scheduleId));
    }

    @Override
    public Schedule updateScheduleDetails(int scheduleId, ScheduleRequest scheduleRequest) throws ScheduleIdDoesNotExistException {

        Optional<Schedule> optionalSchedule = scheduleRepository.findById(scheduleId);
        Schedule schedule;
        if (optionalSchedule.isPresent()) {
            schedule = optionalSchedule.get();
            schedule.setDepartureDateTime(scheduleRequest.getDepartureDateTime());
            schedule.setArrivalDateTime(scheduleRequest.getArrivalDateTime());
            scheduleRepository.save(schedule);
        } else {
            throw new ScheduleIdDoesNotExistException("Schedule with ID " + scheduleId + " not found");
        }
        return schedule;
    }

    @Override
    public String deleteScheduleDetails(int scheduleId) throws ScheduleIdDoesNotExistException {

        Optional<Schedule> scheduleOptional = scheduleRepository.findById(scheduleId);
        if (scheduleOptional.isPresent()) {
            scheduleRepository.deleteById(scheduleId);
            return "Schedule deleted successfully";
        } else {
            throw new ScheduleIdDoesNotExistException("Schedule with ID " + scheduleId + " not found");
        }
    }
}