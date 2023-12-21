package com.schedulealtimetrik.schedulemicroservice.service;

import com.schedulealtimetrik.schedulemicroservice.exception.ScheduleIdAlreadyExistException;
import com.schedulealtimetrik.schedulemicroservice.exception.ScheduleIdNotExistException;
import com.schedulealtimetrik.schedulemicroservice.model.NewScheduleRequest;
import com.schedulealtimetrik.schedulemicroservice.model.Route;
import com.schedulealtimetrik.schedulemicroservice.model.Schedule;
import com.schedulealtimetrik.schedulemicroservice.model.Train;
import com.schedulealtimetrik.schedulemicroservice.repository.ScheduleRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;

@Service
public class ScheduleServiceImpl implements ScheduleService {

    private static final Logger logger = LoggerFactory.getLogger(ScheduleServiceImpl.class);

    @Autowired
    private final RestTemplate restTemplate;

    @Autowired
    private ScheduleRepository scheduleRepository;

    public ScheduleServiceImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public Schedule addSchedule(NewScheduleRequest newScheduleRequest) throws ScheduleIdAlreadyExistException {
        Train train = restTemplate.getForObject("http://localhost:8080/train-api/train/{trainNumber}",
                Train.class, newScheduleRequest.getTrainNumber());
        Route route = restTemplate.getForObject("http://localhost:8181/route-api/route/{routeId}",
                Route.class, newScheduleRequest.getRouteId());

        Schedule schedule = addSchedule(newScheduleRequest, train, route);

        if (scheduleRepository.existsById(train.getTrainNumber())) {
            logger.error("Train with ID {} already exists.", train.getTrainNumber());
            throw new ScheduleIdAlreadyExistException("Train with ID " + train.getTrainNumber() + " already exists.");
        }


        return scheduleRepository.save(schedule);
    }

    public Schedule addSchedule(NewScheduleRequest newScheduleRequest, Train train, Route route) {
        Schedule schedule = new Schedule();
        schedule.setDepartureDateTime(String.valueOf(newScheduleRequest.getDepartureDateTime()));
        schedule.setArrivalDateTime(String.valueOf(newScheduleRequest.getArrivalDateTime()));
        schedule.setTrain(train.toString());
        schedule.setRoute(route.toString());
        return schedule;
    }
    @Override
    public List<Schedule> getAllSchedules() {
        List<Schedule> schedules = scheduleRepository.findAll();

        if (schedules != null && !schedules.isEmpty()) {
            logger.info("Found {} schedules", schedules.size());
        } else {
            logger.error("No schedules found");
        }

        return schedules;
    }

    @Override
    public Schedule getScheduleById(int scheduleId) throws ScheduleIdNotExistException {
        Optional<Schedule> scheduleOptional = scheduleRepository.findById(scheduleId);

        if (scheduleOptional.isEmpty()) {
            logger.error("Schedule ID {} does not exist in the database.", scheduleId);
            throw new ScheduleIdNotExistException("Schedule ID Does Not Exist In The Database!!!! Check The Schedule ID");
        }

        return scheduleOptional.get();
    }

    @Override
    public Schedule updateSchedule(Schedule updatedSchedule) throws ScheduleIdNotExistException {
        Schedule schedule = getScheduleById(updatedSchedule.getScheduleId());

        if (schedule != null) {
            schedule.setDepartureDateTime(updatedSchedule.getDepartureDateTime());
            schedule.setArrivalDateTime(updatedSchedule.getArrivalDateTime());
            // Update train and route with new values
            schedule.setTrain(updatedSchedule.getTrain());
            schedule.setRoute(updatedSchedule.getRoute());
            return scheduleRepository.save(schedule);
        } else {
            logger.error("Error updating schedule: Schedule not found");
            throw new ScheduleIdNotExistException("Error updating schedule: Schedule not found");
        }
    }

    @Override
    public String deleteScheduleById(int scheduleId) throws ScheduleIdNotExistException {
        String message = "Schedule Does Not Exist";
        Schedule s = getScheduleById(scheduleId);

        if (s != null) {
            scheduleRepository.deleteById(scheduleId);
            logger.info("Schedule {} has been deleted", scheduleId);
            return "Schedule Has Been Deleted";
        } else {
            logger.error("Schedule deletion unsuccessful: Schedule not found");
            throw new ScheduleIdNotExistException(message);
        }
    }
}
