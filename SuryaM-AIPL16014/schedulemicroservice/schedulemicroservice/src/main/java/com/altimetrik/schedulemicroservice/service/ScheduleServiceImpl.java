package com.altimetrik.schedulemicroservice.service;

import com.altimetrik.schedulemicroservice.exception.ScheduleIdNotExistsException;
import com.altimetrik.schedulemicroservice.model.NewScheduleRequest;
import com.altimetrik.schedulemicroservice.model.Route;
import com.altimetrik.schedulemicroservice.model.Schedule;
import com.altimetrik.schedulemicroservice.model.Train;
import com.altimetrik.schedulemicroservice.repository.ScheduleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class ScheduleServiceImpl implements ScheduleService{
    @Autowired
    private RestTemplate restTemplate;
    @Value("${schedule.microservice.base-url}")
    private String scheduleMicroserviceBaseUrl;
    public void printBaseUrl() {
        System.out.println("Schedule Microservice Base URL: " + scheduleMicroserviceBaseUrl);
    }
    @Autowired
    private ScheduleRepository scheduleRepository;
    @Override
    public Schedule addSchedule(NewScheduleRequest newScheduleRequest){

        Train train = restTemplate.getForObject("http://localhost:8080/train-api/train/{trainNumber}",
                Train.class, newScheduleRequest.getTrainNumber());

        Route route = restTemplate.getForObject("http://localhost:8081/route-api/route/{routeId}",
                Route.class, newScheduleRequest.getRouteId());

        Schedule schedule = new Schedule();

        schedule.setDepartureDateTime(newScheduleRequest.getDepartureDateTime());
        schedule.setArrivalDateTime(newScheduleRequest.getArrivalDateTime());
        schedule.setTrain(train.toString());
        schedule.setRoute(route.toString());

        return scheduleRepository.save(schedule);

    }

    @Override
    public Schedule updateSchedule(Schedule updatedSchedule) throws ScheduleIdNotExistsException {

        Schedule existingSchedule = scheduleRepository.findById(updatedSchedule.getScheduleId())
                .orElseThrow(() -> new ScheduleIdNotExistsException("Schedule with ID " + updatedSchedule.getScheduleId() + " not found"));

        existingSchedule.setArrivalDateTime(updatedSchedule.getArrivalDateTime());
        existingSchedule.setDepartureDateTime(updatedSchedule.getDepartureDateTime());

        return scheduleRepository.save(existingSchedule);
    }
    @Override
    public List<Schedule> getAllSchedule() {
        return scheduleRepository.findAll();
    }
    @Override
    public Schedule getScheduleById(int scheduleId) throws ScheduleIdNotExistsException {

        return scheduleRepository.findById(scheduleId)
                .orElseThrow(() -> new ScheduleIdNotExistsException("Schedule with ID " + scheduleId + " not found"));

    }
    @Override
    public String deleteScheduleById(int scheduleId) throws ScheduleIdNotExistsException {

        return scheduleRepository.findById(scheduleId)
                .map(existingSchedule -> {
                    scheduleRepository.deleteById(scheduleId);
                    return "Schedule Deleted successfully";
                })
                .orElseThrow(() -> new ScheduleIdNotExistsException("Schedule with ID " + scheduleId + " not found"));
    }
}
