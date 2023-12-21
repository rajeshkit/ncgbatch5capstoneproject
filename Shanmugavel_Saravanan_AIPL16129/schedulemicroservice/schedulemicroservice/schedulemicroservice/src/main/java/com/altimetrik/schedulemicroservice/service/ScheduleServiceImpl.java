package com.altimetrik.schedulemicroservice.service;

import com.altimetrik.schedulemicroservice.model.NewScheduleRequest;
import com.altimetrik.schedulemicroservice.model.Route;
import com.altimetrik.schedulemicroservice.model.Schedule;
import com.altimetrik.schedulemicroservice.model.Train;
import com.altimetrik.schedulemicroservice.repository.ScheduleRepository;
import com.altimetrik.schedulemicroservice.exception.ScheduleNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;

@Service
public class ScheduleServiceImpl implements ScheduleService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ScheduleServiceImpl.class);

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private ScheduleRepository scheduleRepository;

    @Override
    public Schedule createSchedule(NewScheduleRequest newScheduleRequest) {
        LOGGER.info("Creating a new schedule...");

        Train train = restTemplate.getForObject("http://localhost:8080/train-api/train/{trainId}", Train.class, newScheduleRequest.getTrainId());
        Route route = restTemplate.getForObject("http://localhost:8181/route-api/route/{routeId}", Route.class, newScheduleRequest.getRouteId());

        Schedule schedule = addSchedule(newScheduleRequest, train, route);
        Schedule savedSchedule = scheduleRepository.save(schedule);

        LOGGER.info("Schedule created successfully. Schedule ID: {}", savedSchedule.getScheduleId());
        return savedSchedule;
    }

    private Schedule addSchedule(NewScheduleRequest newScheduleRequest, Train train, Route route) {
        LOGGER.debug("Adding schedule details...");

        Schedule schedule = new Schedule();
        schedule.setDepartureDateTime(newScheduleRequest.getDepartureDateTime());
        schedule.setArrivalDateTime(newScheduleRequest.getArrivalDateTime());
        schedule.setTrain(train.toString());
        schedule.setRoute(route.toString());

        LOGGER.debug("Schedule details added successfully.");
        return schedule;
    }

    @Override
    public List<Schedule> getAllSchedules() {
        LOGGER.info("Fetching all schedules...");
        List<Schedule> schedules = scheduleRepository.findAll();
        LOGGER.info("Fetched {} schedules.", schedules.size());
        return schedules;
    }

    @Override
    public Schedule getScheduleById(int scheduleId) throws ScheduleNotFoundException {
        LOGGER.info("Fetching schedule by ID: {}", scheduleId);

        Optional<Schedule> scheduleOptional = scheduleRepository.findById(scheduleId);
        if (scheduleOptional.isEmpty()) {
            LOGGER.warn("Schedule with ID {} not found in the database.", scheduleId);
            throw new ScheduleNotFoundException("Schedule with ID " + scheduleId + " not found in the database.");
        }

        Schedule schedule = scheduleOptional.get();
        LOGGER.info("Fetched schedule successfully. Schedule details: {}", schedule);
        return schedule;
    }

    @Override
    public Schedule updateSchedule(Schedule updatedSchedule) throws ScheduleNotFoundException {
        LOGGER.info("Updating schedule with ID: {}", updatedSchedule.getScheduleId());

        Schedule schedule = getScheduleById(updatedSchedule.getScheduleId());

        if (schedule != null) {
            schedule.setDepartureDateTime(updatedSchedule.getDepartureDateTime());
            schedule.setArrivalDateTime(updatedSchedule.getArrivalDateTime());
            Schedule savedSchedule = scheduleRepository.save(schedule);

            LOGGER.info("Schedule updated successfully. Updated schedule details: {}", savedSchedule);
            return savedSchedule;
        }

        LOGGER.warn("Unable to update schedule. Schedule with ID {} not found.", updatedSchedule.getScheduleId());
        return null;
    }

    @Override
    public String deleteScheduleById(int scheduleId) throws ScheduleNotFoundException {
        LOGGER.info("Deleting schedule with ID: {}", scheduleId);

        String message = "Schedule with ID " + scheduleId + " does not exist to delete";
        Schedule schedule = getScheduleById(scheduleId);

        if (schedule != null) {
            scheduleRepository.deleteById(scheduleId);
            message = "Schedule deleted successfully";
            LOGGER.info("Schedule deleted successfully. Schedule ID: {}", scheduleId);
        }

        return message;
    }
}
