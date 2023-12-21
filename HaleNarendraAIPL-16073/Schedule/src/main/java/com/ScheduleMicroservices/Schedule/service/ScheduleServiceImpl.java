package com.ScheduleMicroservices.Schedule.service;


import com.RouteMicroservices.Route.model.RouteResources;
import com.ScheduleMicroservices.Schedule.Repository.ScheduleResourcesRepository;
import com.ScheduleMicroservices.Schedule.exception.ScheduleIdNotFoundException;
import com.ScheduleMicroservices.Schedule.model.NewScheduleResources;
import com.ScheduleMicroservices.Schedule.model.ScheduleResources;
import com.TrainBookingSystem.Train.Exception.TrainIdNotFoundException;
import com.TrainBookingSystem.Train.model.TrainResources;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;

@Service
public class ScheduleServiceImpl implements ScheduleService {

    @Autowired
    private ScheduleResourcesRepository scheduleResourcesRepository;

    @Autowired
    private ModelMapper mapper;

    @PersistenceContext
    private EntityManager entityManager;
    @Autowired
    private RestTemplate restTemplate;


    Logger logger = LoggerFactory.getLogger(ScheduleServiceImpl.class);


    @Override
    public NewScheduleResources addScheduleResources(ScheduleResources scheduleResources) {
        String trainURL = "http://localhost:8082/train-api/train/" + scheduleResources.getTrainId();
        String routeURL = "http://localhost:8083/route-api/route/" + scheduleResources.getRouteId();
        ResponseEntity<TrainResources> trainResponseEntity = restTemplate.getForEntity(trainURL, TrainResources.class);
        ResponseEntity<RouteResources> routeResponseEntity = restTemplate.getForEntity(routeURL, RouteResources.class);
        logger.info("Train Resources status: {} ",trainResponseEntity.getStatusCode());
        logger.info("Train Resources status: {} ",trainResponseEntity.getStatusCode());


        TrainResources trainResponse=trainResponseEntity.getBody();
        RouteResources routeResponse=routeResponseEntity.getBody();

        if(routeResponse!=null && trainResponse!=null){

            NewScheduleResources newScheduleResources=NewScheduleResources.builder().scheduleId(scheduleResources.getScheduleId()).departureDateTime(scheduleResources.getDepartureDateTime())
                    .arrivalDateTime(scheduleResources.getArrivalDateTime()).trainResources(trainResponse).routeResources(routeResponse).build();
            return scheduleResourcesRepository.save(newScheduleResources);


        } else {
            return null;

        }

    }

    @Override
    public List<NewScheduleResources> findDetailByTrainId(Long trainId) throws TrainIdNotFoundException {

        List<NewScheduleResources> newScheduleResources=scheduleResourcesRepository.findByTrainResources_trainNumber(trainId);

        if(newScheduleResources.isEmpty()){
            throw new TrainIdNotFoundException("Train Id not found Exception!!!");
        }
        return newScheduleResources;

    }


    @Override
    public NewScheduleResources getScheduleById(Long scheduleId) throws ScheduleIdNotFoundException {

        Optional<NewScheduleResources> pro = scheduleResourcesRepository.findById(scheduleId);
        if (pro.isEmpty()) {
            throw new ScheduleIdNotFoundException("schedule Id not found Exception!!!");
        }
        return pro.get();
    }


    @Override
    public NewScheduleResources updateScheduleDetail(NewScheduleResources newScheduleResources) {

        return scheduleResourcesRepository.save(newScheduleResources);
    }


    @Override
    public List<NewScheduleResources> getAllScheduleDetail() {

        return scheduleResourcesRepository.findAll();
    }

    @Override
    public String deleteScheduleById(Long scheduleId) throws ScheduleIdNotFoundException {
        String message = "Scheduled detail Does not exists to delete";
        NewScheduleResources p = getScheduleById(scheduleId);
        if (p != null) {
            scheduleResourcesRepository.deleteById(scheduleId);
            message = "Scheduled detail deleted successfully";
            return message;
        }
        return message;
    }



}
