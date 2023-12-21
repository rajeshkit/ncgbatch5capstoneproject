package com.altimetrik.schedule.service;

import com.altimetrik.schedule.exception.ScheduleIdNotExitsException;
import com.altimetrik.schedule.model.RouteS;
import com.altimetrik.schedule.model.Schedule;
import com.altimetrik.schedule.model.ScheduleRequest;
import com.altimetrik.schedule.model.TrainS;
import com.altimetrik.schedule.repository.ScheduleRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.client.RestTemplate;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;


@Service
public class ScheduleServiceImp implements ScheduleService {
    private static final Logger logger = LoggerFactory.getLogger(ScheduleServiceImp.class);
    @Autowired
    private ScheduleRepository scheduleRepository;
    @Autowired
    private RestTemplate restTemplate;

    public Schedule getScheduleById(int scheduleId) throws ScheduleIdNotExitsException {
        logger.info("Getting Schedule by Id: {}", scheduleId);
        Optional<Schedule> pro = scheduleRepository.findById(scheduleId);

        if (pro.isEmpty()) {
            logger.warn("Schedule with Schedule Id {} not found", scheduleId);
            throw new ScheduleIdNotExitsException("Schedule with this Schedule Id not found");
        }

        Schedule schedule = pro.get();
        logger.info("Found Schedule: {}",schedule);
        return schedule;
    }

    @Override
    public Schedule addSchedule(ScheduleRequest scheduleRequest) {

        TrainS train = restTemplate.getForObject("http://localhost:8181/train/"+scheduleRequest.getTrainId(),
                TrainS.class);
        logger.info(" train response : {}",train);
        RouteS route = restTemplate.getForObject("http://localhost:8383/route/"+scheduleRequest.getRouteId(),
                RouteS.class);

        logger.info("route response: {}",route);
        return scheduleRepository.save(Schedule.builder().scheduleId(1).train(String.valueOf(train)).route(String.valueOf(route))
                .departureDateTime(scheduleRequest.getDepartureDateTime()).arrivalDateTime(scheduleRequest.getArrivalDateTime())
                .build());
    }
    @Override
    public List<Schedule> getAllSchedule() {
        logger.info("Getting all trains");
        List<Schedule> allSchedule=scheduleRepository.findAll();
        if (allSchedule.isEmpty()){
            logger.warn("No trains available");
            throw new NoSuchElementException("there is no trains available");
        }
        logger.info("Found {} trains",allSchedule.size());
        return allSchedule;
    }
    @Override
    public Schedule updateSchedule(@Validated Schedule schedule) throws ScheduleIdNotExitsException {
        logger.info("Updating schedule with Id: {}", schedule.getScheduleId());
        Schedule exitingSchedule=scheduleRepository.findById(schedule.getScheduleId()).orElse(null);
        if(exitingSchedule==null){
            logger.warn("schedule with schedule Id {} not found for update", schedule.getScheduleId());
            throw new ScheduleIdNotExitsException("schedule Id not found:"+schedule.getScheduleId());
        }
        Schedule train1=scheduleRepository.save(schedule);
        logger.info("schedule updated successfully: {}",train1);
        return train1;
    }
    @Override
    public String deleteScheduleById(int scheduleId) throws ScheduleIdNotExitsException {
        logger.info("Deleting Schedule with Id: {}", scheduleId);
        String message = "schedule Does not exists to delete";
        Schedule s = getScheduleById(scheduleId);
        if (s != null) {
            scheduleRepository.deleteById(scheduleId);
            message = "schedule deleted successfully";
            logger.info("schedule deleted successfully with Id: {}", scheduleId);
            return message;
        }
        logger.warn("schedule with Id {} not found for deletion", scheduleId);
        return message;
    }
}
