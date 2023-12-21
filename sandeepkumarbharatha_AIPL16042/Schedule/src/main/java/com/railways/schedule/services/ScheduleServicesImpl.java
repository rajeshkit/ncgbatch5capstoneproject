package com.railways.schedule.services;

import com.railways.route.model.Route;
import com.railways.schedule.exceptions.ScheduleIdNotFoundException;
import com.railways.schedule.model.Schedule;
import com.railways.schedule.model.ScheduleDemo;
import com.railways.schedule.repository.ScheduleRepository;
import com.railways.train.model.Train;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;

@Service
public class ScheduleServicesImpl implements ScheduleServices {
    @Autowired
    private ScheduleRepository scheduleRepository;
    @Autowired
    private RestTemplate restTemplate;
    Logger log = LoggerFactory.getLogger(ScheduleServicesImpl.class);

    @Override
    public Schedule insertScheduleDetails(ScheduleDemo scheduleDemo) {
        ResponseEntity<Train> trainEntity = restTemplate.getForEntity("http://localhost:8080/train-api/traindetails/" + scheduleDemo.getTrainNumber(), Train.class);
        ResponseEntity<Route> routeEntity = restTemplate.getForEntity("http://localhost:8081/route-api/route/" + scheduleDemo.getRouteId(), Route.class);
        log.info("Train  status: {} ", trainEntity.getStatusCode());
        log.info("Route  status: {} ", routeEntity.getStatusCode());
        Train trainrest = trainEntity.getBody();
        Route routerest = routeEntity.getBody();
        //if (scheduleDemo.getTrainNumber().equals(trainrest.getTrainNumber())&&scheduleDemo.getRouteId().equals(routerest.getRouteId())) {
        if (trainrest != null && routerest != null) {
            Schedule schedule = Schedule.builder().scheduleId(scheduleDemo.getScheduleId1()).arrivalDateTime(scheduleDemo.getArrivalDateTime()).departureDateTime(scheduleDemo.getDepartureDateTime()).train(trainrest).route(routerest).build();
            return scheduleRepository.save(schedule);
        }
        return null;
    }


    @Override
    public List<Schedule> getAllScheduleDetails() {
        return scheduleRepository.findAll();
    }

    @Override
    public Schedule getScheduleById(long routeIdToSearch) throws ScheduleIdNotFoundException {
        Optional<Schedule> id = scheduleRepository.findById(routeIdToSearch);
        if (id.isEmpty()) {
            throw new ScheduleIdNotFoundException("Schedule  ID not found");
        }
        return id.get();
    }

    @Override
    public Schedule updateScheduleById(Schedule schedule) throws ScheduleIdNotFoundException {
        if (getScheduleById(schedule.getScheduleId()) == null) {

            throw new ScheduleIdNotFoundException("Schedule ID not found");
        }
        return scheduleRepository.save(schedule);
    }

    @Override
    public String deleteScheduleById(long scheduleIdToDelete) throws ScheduleIdNotFoundException {
        if (getScheduleById(scheduleIdToDelete) != null) {
            scheduleRepository.deleteById(scheduleIdToDelete);
            return "schedule id deleted having id=" + scheduleIdToDelete;
        }
        throw new ScheduleIdNotFoundException("Schedule ID not found");
    }

}
