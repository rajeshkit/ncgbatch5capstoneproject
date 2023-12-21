package com.altimetrik.schedule.service;

import com.altimetrik.schedule.exception.ScheduleIdNotExistsException;
import com.altimetrik.schedule.model.NewScheduleRequest;
import com.altimetrik.schedule.model.Route;
import com.altimetrik.schedule.model.Schedule;
import com.altimetrik.schedule.model.Train;
import com.altimetrik.schedule.repository.ScheduleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

@Service
public class ScheduleServiceImpl implements ScheduleService {

    private static final Logger LOGGER = Logger.getLogger(ScheduleServiceImpl.class.getName());

    @Autowired
    private ScheduleRepository scheduleRepository;

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public Schedule addSchedule(NewScheduleRequest newScheduleRequest) {
        LOGGER.log(Level.INFO, "Adding new schedule with request: {0}", newScheduleRequest);

        Train train = restTemplate.getForObject("http://localhost:8081/train-api/train/{num}",
                Train.class, newScheduleRequest.getTrainNum());
        Route route = restTemplate.getForObject("http://localhost:8082/route-api/route/{id}",
                Route.class, newScheduleRequest.getRouteId());

        Schedule schedule = Schedule.builder()
                .arrDateTime(newScheduleRequest.getArrDateTime())
                .depDateTime(newScheduleRequest.getDepDateTime())
                .train(train.toString())
                .route(route.toString())
                .build();

        Schedule savedSchedule = scheduleRepository.save(schedule);

        LOGGER.log(Level.INFO, "Schedule added successfully with ID: {0}", savedSchedule.getScheduleId());

        return savedSchedule;
    }

    @Override
    public List<Schedule> getAllSchedules() {
        LOGGER.log(Level.INFO, "Fetching all schedules");
        return scheduleRepository.findAll();
    }

    @Override
    public Schedule getScheduleById(int scheduleId) throws ScheduleIdNotExistsException {
        LOGGER.log(Level.INFO, "Fetching schedule by ID: {0}", scheduleId);
        Optional<Schedule> s = scheduleRepository.findById(scheduleId);
        if (s.isEmpty()) {
            LOGGER.log(Level.WARNING, "Schedule not found with ID: {0}", scheduleId);
            throw new ScheduleIdNotExistsException("Schedule doesn't exist");
        }
        return s.get();
    }

    @Override
    public Schedule updateSchedule(Schedule schedule) throws ScheduleIdNotExistsException {
        LOGGER.log(Level.INFO, "Updating schedule with ID: {0}", schedule.getScheduleId());
        if (getScheduleById(schedule.getScheduleId()) != null) {
            return scheduleRepository.save(schedule);
        }
        return null;
    }

    @Override
    public String deleteSchedule(int scheduleId) throws ScheduleIdNotExistsException {
        LOGGER.log(Level.INFO, "Deleting schedule with ID: {0}", scheduleId);
        String message = "Schedule doesn't exist to delete";
        Schedule s = getScheduleById(scheduleId);
        if (s != null) {
            scheduleRepository.deleteById(scheduleId);
            message = "Schedule Deleted Successfully";
            LOGGER.log(Level.INFO, "Schedule deleted successfully with ID: {0}", scheduleId);
            return message;
        }
        return message;
    }
}
