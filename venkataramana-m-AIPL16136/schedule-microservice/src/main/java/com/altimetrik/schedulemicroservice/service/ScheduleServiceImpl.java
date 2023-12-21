package com.altimetrik.schedulemicroservice.service;

import com.altimetrik.schedulemicroservice.exception.InvalidDateTimeFormatException;
import com.altimetrik.schedulemicroservice.exception.ScheduleIdAlreadyExistsException;
import com.altimetrik.schedulemicroservice.exception.ScheduleIdNotExistsException;
import com.altimetrik.schedulemicroservice.model.NewScheduleRequest;
import com.altimetrik.schedulemicroservice.model.Route;
import com.altimetrik.schedulemicroservice.model.Schedule;
import com.altimetrik.schedulemicroservice.model.Train;
import com.altimetrik.schedulemicroservice.repository.ScheduleRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

@Service
public class ScheduleServiceImpl implements ScheduleService {

    private static final Logger logger = LoggerFactory.getLogger(ScheduleServiceImpl.class);

    @Autowired
    private ScheduleRepository scheduleRepository;

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public Schedule addSchedule(NewScheduleRequest newScheduleRequest) throws ScheduleIdNotExistsException, InvalidDateTimeFormatException, ScheduleIdAlreadyExistsException {
        if (newScheduleRequest.getDepartureDateTime().isEmpty()) {
            throw new InvalidDateTimeFormatException("Departure Date and Time is required");
        }
        if (newScheduleRequest.getArrivalDateTime().isEmpty()) {
            throw new InvalidDateTimeFormatException("Arrival Date and Time is required");
        }

        // Fetch train data
        Train train = restTemplate.getForObject(
                "http://localhost:4013/train-api/train/{trainId}",
                Train.class,
                newScheduleRequest.getTrainNumber()
        );

        if (train == null) {
            throw new ScheduleIdNotExistsException("Train not found with number: " + newScheduleRequest.getTrainNumber());
        }

        // Fetch route data
        Route route = restTemplate.getForObject(
                "http://localhost:8181/routes-api/routes/{routeId}",
                Route.class,
                newScheduleRequest.getRouteId()
        );

        if (route == null) {
            throw new ScheduleIdNotExistsException("Route not found with ID: " + newScheduleRequest.getRouteId());
        }

        Schedule schedule = new Schedule();
        schedule.setDepartureDateTime(Timestamp.valueOf(newScheduleRequest.getDepartureDateTime()));
        schedule.setArrivalDateTime(Timestamp.valueOf(newScheduleRequest.getArrivalDateTime()));
        schedule.setTrain(train.toString());
        schedule.setRoute(route.toString());

        if (scheduleRepository.existsById(schedule.getScheduleId())) {
            throw new ScheduleIdAlreadyExistsException("Schedule with ID " + schedule.getScheduleId() + " already exists.");
        }
        Schedule savedSchedule = scheduleRepository.save(schedule);

        if (savedSchedule != null) {
            logger.info("Schedule added successfully with ID: {}", savedSchedule.getScheduleId());
            return savedSchedule;
        } else {
            logger.error("Error occurred while adding a schedule.");
            throw new ScheduleIdNotExistsException("Error occurred while adding a schedule.");
        }
    }



    @Override
    public Schedule getScheduleById(long scheduleId) throws ScheduleIdNotExistsException {
        Optional<Schedule> optionalSchedule = scheduleRepository.findById(scheduleId);

        if (optionalSchedule.isPresent()) {
            logger.info("Schedule found with ID: {}", scheduleId);
            return optionalSchedule.get();
        } else {
            logger.error("Schedule not found with ID: {}", scheduleId);
            throw new ScheduleIdNotExistsException("Schedule not found with ID: " + scheduleId);
        }
    }

    @Override
    public List<Schedule> getAllSchedules() {
        List<Schedule> schedules = scheduleRepository.findAll();
        logger.info("Retrieved {} schedules.", schedules.size());
        return schedules;
    }
    @Override
    public Schedule updateSchedule(long scheduleId, NewScheduleRequest updatedSchedule) throws ScheduleIdNotExistsException {
        Optional<Schedule> optionalSchedule = scheduleRepository.findById(scheduleId);

        if (optionalSchedule.isPresent()) {
            Schedule existingSchedule = optionalSchedule.get();
            existingSchedule.setDepartureDateTime(Timestamp.valueOf(updatedSchedule.getDepartureDateTime()));
            existingSchedule.setArrivalDateTime(Timestamp.valueOf(updatedSchedule.getArrivalDateTime()));
            existingSchedule.setTrain(String.valueOf(restTemplate.getForObject(
                    "http://localhost:4013/train-api/train/{trainId}",
                    Train.class,
                    updatedSchedule.getTrainNumber()
            )));
            existingSchedule.setRoute(String.valueOf(restTemplate.getForObject(
                    "http://localhost:8181/routes-api/routes/{routeId}",
                    Route.class,
                    updatedSchedule.getRouteId()
            )));

            Schedule updatedScheduleResult = scheduleRepository.save(existingSchedule);
            logger.info("Schedule updated successfully with ID: {}", scheduleId);
            return updatedScheduleResult;
        } else {
            logger.error("Schedule not found with ID: {}", scheduleId);
            throw new ScheduleIdNotExistsException("Schedule not found with ID: " + scheduleId);
        }
    }

    @Override
    public String deleteScheduleById(long scheduleId) throws ScheduleIdNotExistsException {
        Optional<Schedule> optionalSchedule = scheduleRepository.findById(scheduleId);

        if (optionalSchedule.isPresent()) {
            scheduleRepository.deleteById(scheduleId);
            logger.info("Schedule with ID {} deleted successfully.", scheduleId);
            return "Schedule with ID " + scheduleId + " deleted successfully.";
        } else {
            logger.error("Schedule not found with ID: {}", scheduleId);
            throw new ScheduleIdNotExistsException("Schedule not found with ID: " + scheduleId);
        }
    }
}
