package com.altimetrik.trainschedule.service;

import com.altimetrik.trainschedule.exception.RouteIdNotExistsException;
import com.altimetrik.trainschedule.exception.ScheduleIdNotExistsException;
import com.altimetrik.trainschedule.exception.TrainNoNotExistsException;
import com.altimetrik.trainschedule.model.Schedule;
import com.altimetrik.trainschedule.repository.ScheduleRepository;
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
    public Schedule addSchedule(Schedule schedule) throws TrainNoNotExistsException, RouteIdNotExistsException {
        try {
            validateRouteAndTrain(schedule.getRouteId(), schedule.getTrainId());
            logger.info("Adding new schedule: {}", schedule);
            return scheduleRepository.save(schedule);
        } catch (Exception e) {
            logger.error("Error adding schedule: {}", e.getMessage(), e);
            throw e;
        }
    }

    @Override
    public List<Schedule> getAllSchedule() {
        logger.info("Fetching all schedules");
        return scheduleRepository.findAll();
    }

    @Override
    public Schedule getScheduleById(String scheduleId) throws ScheduleIdNotExistsException {
        try {
            return scheduleRepository.findById(scheduleId)
                    .orElseThrow(() -> new ScheduleIdNotExistsException("Schedule with ID " + scheduleId + " does not exist in the database."));
        } catch (Exception e) {
            logger.error("Error getting schedule by ID: {}", e.getMessage(), e);
            throw e;
        }
    }

    @Override
    public Schedule updateSchedule(Schedule schedule) throws ScheduleIdNotExistsException, TrainNoNotExistsException, RouteIdNotExistsException {
        try {
            getScheduleById(schedule.getScheduleId());
            validateRouteAndTrain(schedule.getRouteId(), schedule.getTrainId());
            logger.info("Updating schedule: {}", schedule);
            return scheduleRepository.save(schedule);
        } catch (Exception e) {
            logger.error("Error updating schedule: {}", e.getMessage(), e);
            throw e;
        }
    }

    @Override
    public String deleteScheduleById(String scheduleId) throws ScheduleIdNotExistsException {
        try {
            String message = "Schedule does not exist to delete";
            Schedule schedule = getScheduleById(scheduleId);
            if (schedule != null) {
                scheduleRepository.deleteById(scheduleId);
                message = "Schedule deleted successfully";
            }
            return message;
        } catch (Exception e) {
            logger.error("Error deleting schedule by ID: {}", e.getMessage(), e);
            throw e;
        }
    }

    private void validateRouteAndTrain(String routeId, String trainNumber) throws RouteIdNotExistsException, TrainNoNotExistsException, TrainNoNotExistsException {
        try {
            if (!checkEntityExistence("http://localhost:8080/train-api/train/{trainNo}", trainNumber)) {
                throw new TrainNoNotExistsException("No Train Exists with that ID");
            }
        } catch (Exception e) {
            logger.error("Error checking train existence: {}", e.getMessage(), e);
            throw new TrainNoNotExistsException("Error checking train existence");
        }
        try {
            if (!checkEntityExistence("http://localhost:8181/route-api/route/{routeId}", routeId)) {
                throw new RouteIdNotExistsException("No Route Exists with that ID");
            }
        } catch (Exception e) {
            logger.error("Error checking route existence: {}", e.getMessage(), e);
            throw new RouteIdNotExistsException("Error checking route existence");
        }
    }

    private boolean checkEntityExistence(String url, String entityId) {
        try {
            RestTemplate restTemplate = new RestTemplate();
            ResponseEntity<Void> responseEntity = restTemplate.getForEntity(url, Void.class, entityId);
            return responseEntity.getStatusCode().is2xxSuccessful();
        } catch (Exception e) {
            logger.error("Error checking entity existence: {}", e.getMessage(), e);
            throw new RuntimeException("Error checking entity existence", e);
        }
    }
}
