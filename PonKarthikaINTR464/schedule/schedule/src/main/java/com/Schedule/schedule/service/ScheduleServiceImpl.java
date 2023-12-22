package com.Schedule.schedule.service;

import com.Schedule.schedule.model.Schedule;
import com.Schedule.schedule.repository.ScheduleRepository;
import lombok.Value;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;
@Service
public class ScheduleServiceImpl implements ScheduleService {
   // @Value("${http://localhost:8080/train-api/train}")
   // private String trainMicroserviceUrl;
    // @Value("${http://localhost:8081/route-api/route}")
   // private String routeMicroserviceUrl;
    @Autowired
    private ScheduleRepository scheduleRepository;
    private RestTemplate restTemplate;

    public void ScheduleService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public Schedule addSchedule(Schedule schedule) {
        return scheduleRepository.save(schedule);
        /*String trainUrl = trainMicroserviceUrl + "1" + schedule.getTrainId();
        ResponseEntity<Train> trainResponse = restTemplate.getForEntity(trainUrl, Train.class);
        Train train = trainResponse.getBody();*/
    }

    @Override
    public List<Schedule> getAllSchedules() {
        return scheduleRepository.findAll();

    }

    @Override
    public Optional<Schedule> getScheduleById(int scheduleId) {
        return scheduleRepository.findById(scheduleId);
    }

    @Override
    public Schedule updateSchedule(Schedule schedule) {
        if (getScheduleById(schedule.getScheduleId()).isPresent()) {
            return scheduleRepository.save(schedule);
        }
        return null;
    }

    @Override
    public String deleteScheduleById(int scheduleId) {
        String message = "couldn't delete the Schedule";
        Optional<Schedule> s = getScheduleById(scheduleId);
        if (s.isPresent()) {
            scheduleRepository.deleteById(scheduleId);
            message = "schedule deleted successfully";
            return message;
        }
        return message;
    }
}



