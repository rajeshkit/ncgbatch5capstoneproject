package com.altimetrik.trainschedule.service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.altimetrik.trainschedule.exception.ScheduleIdNotExistsException;
import com.altimetrik.trainschedule.model.Route;
import com.altimetrik.trainschedule.model.Schedule;
import com.altimetrik.trainschedule.model.ScheduleRequest;
import com.altimetrik.trainschedule.model.Train;
import com.altimetrik.trainschedule.repository.ScheduleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;

@Service
public class ScheduleServiceImpl implements ScheduleService {
    @Autowired
    private RestTemplate restTemplate;

    private static final Logger logger = LoggerFactory.getLogger(ScheduleServiceImpl.class);

    @Autowired
    private ScheduleRepository scheduleRepository; // Assuming you have a repository for Schedule

    @Override
    public Schedule addSchedule(ScheduleRequest scheduleRequest) {
      //  int trainNumber = scheduleRequest.getTrainNumber();
       // int routeId = scheduleRequest.getRouteId();

        Train train = restTemplate.getForObject("http://localhost:8181/train/"+scheduleRequest.getTrainNumber(),
                Train.class);
        logger.info(" train response : {}",train);
        Route route = restTemplate.getForObject("http://localhost:8282/route/"+scheduleRequest.getRouteId(),
                Route.class);

        logger.info("route response: {}",route);
        return scheduleRepository.save(Schedule.builder().scheduleId(1).train(String.valueOf(train)).route(String.valueOf(route))
                .departureDateTime(scheduleRequest.getDepartureDateTime()).arrivalDateTime(scheduleRequest.getArrivalDateTime())
                .build());

    }

    @Override
    public List<Schedule> getAllSchedule() {
        return scheduleRepository.findAll();
    }

    @Override
    public Schedule getScheduleById(int scheduleId) throws ScheduleIdNotExistsException {
        Optional<Schedule> optionalSchedule = scheduleRepository.findById(scheduleId);
        if (optionalSchedule.isPresent()) {
            return optionalSchedule.get();
        } else {
            throw new ScheduleIdNotExistsException("Schedule with ID " + scheduleId + " not found");
        }
    }

    @Override
    public Schedule updateSchedule(Schedule schedule) throws ScheduleIdNotExistsException {
        int scheduleId = schedule.getScheduleId();
        Optional<Schedule> existingSchedule = scheduleRepository.findById(scheduleId);
        if (existingSchedule.isPresent()) {
            return scheduleRepository.save(schedule);
        } else {
            throw new ScheduleIdNotExistsException("Schedule with ID " + scheduleId + " not found");
        }
    }

    @Override
    public String deleteScheduleById(int scheduleId) throws ScheduleIdNotExistsException {
        Optional<Schedule> optionalSchedule = scheduleRepository.findById(scheduleId);
        if (optionalSchedule.isPresent()) {
            scheduleRepository.deleteById(scheduleId);
            return "Schedule with ID " + scheduleId + " deleted successfully";
        } else {
            throw new ScheduleIdNotExistsException("Schedule with ID " + scheduleId + " not found");
        }
    }
}
