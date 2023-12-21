package com.altimetrikfinalproject.schedule.service;

import com.altimetrikfinalproject.schedule.entity.NewScheduleRequest;
import com.altimetrikfinalproject.schedule.entity.RouteResponse;
import com.altimetrikfinalproject.schedule.entity.ScheduleResponse;
import com.altimetrikfinalproject.schedule.entity.TrainResponse;
import com.altimetrikfinalproject.schedule.exception.NoScheduleFoundException;
import com.altimetrikfinalproject.schedule.exception.TrainIdDoesNotExistException;
import com.altimetrikfinalproject.schedule.repository.SchedulerRepository;
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
public class ScheduleServiceImplementation implements ScheduleService{
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private SchedulerRepository schedulerRepository;
    private Logger log= LoggerFactory.getLogger(ScheduleServiceImplementation.class);
    @Override
    public NewScheduleRequest addNewSchedule(NewScheduleRequest newScheduleRequest) {
        return schedulerRepository.save(newScheduleRequest);
    }

    @Override
    public List<NewScheduleRequest> getAllSchedule() {
            return schedulerRepository.findAll();
    }


    @Override
    public NewScheduleRequest updateSchedule(NewScheduleRequest newScheduleRequest) throws TrainIdDoesNotExistException {
        if(schedulerRepository.findById(newScheduleRequest.getTrainId()).isEmpty()){
            throw new TrainIdDoesNotExistException("The given train id does not exist", "The ID does not exist");
        }
        NewScheduleRequest newScheduleRequest1 = null;
        newScheduleRequest1.setArrivalDateTime(newScheduleRequest.getArrivalDateTime());
        newScheduleRequest1.setDepartureDateTime(newScheduleRequest.getDepartureDateTime());
        return schedulerRepository.save(newScheduleRequest1);
    }

    @Override
    public NewScheduleRequest deleteSchedule(int trainId) {
        schedulerRepository.deleteById(trainId);
        return null;
    }

    @Override
    public ScheduleResponse addNewScheduleRequest(NewScheduleRequest newScheduleRequest) {
        int train_id = newScheduleRequest.getTrainId();
        int route_id = newScheduleRequest.getRouteId();
        ResponseEntity<TrainResponse> trainResponseResponseEntity = restTemplate.getForEntity("http://localhost:3030/train-api/train/" + train_id, TrainResponse.class);
        TrainResponse trainResponse1 = trainResponseResponseEntity.getBody();
        log.info("train-microservice response : {} ",trainResponseResponseEntity.getStatusCode());
        ResponseEntity<RouteResponse> routeResponseResponseEntity = restTemplate.getForEntity("http://localhost:3031/route-api/route/"+route_id, RouteResponse.class);
        RouteResponse routeResponse1 = routeResponseResponseEntity.getBody();
        log.info("route-microservice response: {}", routeResponseResponseEntity.getStatusCode());
        schedulerRepository.save(newScheduleRequest);
        return ScheduleResponse.builder().trainId(train_id).routeId(route_id).arrivalDateTime(newScheduleRequest.getArrivalDateTime()).departureDateTime(newScheduleRequest.getDepartureDateTime()).routeResponse(routeResponse1).trainResponse(trainResponse1).build();

    }


    @Override
    public ScheduleResponse getScheduleByTrainID(int trainID, int scheduleId) {
        int train_id = trainID;
        ResponseEntity<TrainResponse> trainResponseResponseEntity = restTemplate.getForEntity("http://localhost:3030/train-api/train/" + train_id, TrainResponse.class);
        TrainResponse trainResponse = trainResponseResponseEntity.getBody();
        log.info("train-microservice response : {} ",trainResponseResponseEntity.getStatusCode());
        Optional<NewScheduleRequest> newScheduleRequest = schedulerRepository.findById(scheduleId);
        return ScheduleResponse.builder().trainId(newScheduleRequest.get().getTrainId()).routeId(newScheduleRequest.get().getRouteId()).trainResponse(trainResponse).departureDateTime(newScheduleRequest.get().getDepartureDateTime()).arrivalDateTime(newScheduleRequest.get().getArrivalDateTime()).build();
    }

    @Override
    public ScheduleResponse getScheduleByRouteID(int routeId,int scheduleId) throws NoScheduleFoundException {
        int route_id = routeId;
        ResponseEntity<RouteResponse> routeResponseResponseEntity = restTemplate.getForEntity("http://localhost:3031/route-api/route/"+route_id, RouteResponse.class);
        RouteResponse routeResponse = routeResponseResponseEntity.getBody();
        log.info("route-microservice response : {} ",routeResponseResponseEntity.getStatusCode());
        Optional<NewScheduleRequest> newScheduleRequest = schedulerRepository.findById(scheduleId);
        if(newScheduleRequest.isEmpty()){
            throw new NoScheduleFoundException();
        }
        return ScheduleResponse.builder().routeId(newScheduleRequest.get().getRouteId()).routeId(newScheduleRequest.get().getRouteId()).routeResponse(routeResponse).departureDateTime(newScheduleRequest.get().getDepartureDateTime()).arrivalDateTime(newScheduleRequest.get().getArrivalDateTime()).build();

    }



}
