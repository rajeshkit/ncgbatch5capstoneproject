package com.altimetrik.schedule.service;

import com.altimetrik.schedule.exception.RouteNotExistsException;
import com.altimetrik.schedule.exception.ScheduleNotExistsException;
import com.altimetrik.schedule.exception.TrainNotExistsException;
import com.altimetrik.schedule.model.Schedule;
import com.altimetrik.schedule.repository.ScheduleRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

@Service
public class ScheduleServiceImpl implements ScheduleService {
    private static final Logger logger = LoggerFactory.getLogger(ScheduleServiceImpl.class);

    private final ScheduleRepository scheduleRepository;
    private final WebClient.Builder webClientBuilder;

    public ScheduleServiceImpl(ScheduleRepository scheduleRepository, WebClient.Builder webClientBuilder) {
        this.scheduleRepository = scheduleRepository;
        this.webClientBuilder = webClientBuilder;
    }


    @Override
    public Schedule addSchedule(Schedule schedule) {
        try {
            validateRouteAndTrain(schedule.getRouteId(), schedule.getTrainNo());
            logger.info("new schedule is adding: {}", schedule);
            return scheduleRepository.save(schedule);
        } catch (Exception e) {
            logger.error(" getting error in adding a new schedule: {}", e.getMessage(), e);
            throw e;
        } catch (RouteNotExistsException e) {
            throw new RuntimeException(e);
        } catch (TrainNotExistsException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Schedule> getAllSchedules() {
        logger.info("all schedules fetching");
        return scheduleRepository.findAll();
    }


    @Override
    public Schedule getScheduleById(int scheduleId) throws ScheduleNotExistsException {
        try {
            return scheduleRepository.findById(scheduleId)
                    .orElseThrow(() -> new ScheduleNotExistsException("Schedule with ID " + scheduleId + " does not exist in the database."));}
        catch (Exception e) {
            logger.error("getting schedule by this schedule ID: {}", e.getMessage(), e);
            throw e;
        }

    }

    @Override
    public Schedule updateSchedule(Schedule schedule) throws ScheduleNotExistsException, TrainNotExistsException {
        try {
            getScheduleById(schedule.getScheduleId());
            validateRouteAndTrain(schedule.getRouteId(), schedule.getTrainNo());
            logger.info("Updating the schedule: {}", schedule);
            return scheduleRepository.save(schedule);
        } catch (Exception e) {
            logger.error("Error in updating this schedule: {}", e.getMessage(), e);
            throw e;
        } catch (RouteNotExistsException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String deleteScheduleById(int scheduleId) throws ScheduleNotExistsException {
        try {
            String message = "Schedule does not exist to delete";
            Schedule schedule = getScheduleById(scheduleId);
            if (schedule != null) {
                scheduleRepository.deleteById(scheduleId);
                message = "Schedule deleted";
            }
            return message;
        } catch (Exception e) {
            logger.error("Error in deleting the schedule by this ID: {}", e.getMessage(), e);
            throw e;
        }
    }
    private void validateRouteAndTrain(int routeId, int trainNo) throws RouteNotExistsException, TrainNotExistsException {
        try {
            if (!checkEntityExistence("http://localhost:8080/train-api/train/{trainNo}", String.valueOf(trainNo))) {
                throw new TrainNotExistsException("No Train Exists with that ID");
            }
        } catch (Exception e) {
            logger.error("Error checking train existence: {}", e.getMessage(), e);
            throw new TrainNotExistsException("Error checking train existence");
        }
        try {
            if (!checkEntityExistence("http://localhost:8181/route-api/route/{routeId}", String.valueOf(routeId))) {
                throw new RouteNotExistsException("No Route Exists with that ID");
            }
        } catch (Exception e) {
            logger.error("Error checking route existence: {}", e.getMessage(), e);
            throw new RouteNotExistsException("Error checking route existence");
        }
    }

    private boolean checkEntityExistence(String url, String entityId) {
        try {
            RestTemplate restTemplate = new RestTemplate();
            ResponseEntity<Void> responseEntity = restTemplate.getForEntity(url, Void.class, entityId);
            return responseEntity.getStatusCode().is2xxSuccessful();
        } catch (Exception e) {
            logger.error("Checking error entity exists: {}", e.getMessage(), e);
            throw new RuntimeException("Checking error entity existence", e);
        }
    }
}