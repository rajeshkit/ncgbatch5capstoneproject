package com.schedule.schedulemicroservice.serviceTest;


import com.schedule.schedulemicroservice.entity.NewScheduleRequest;
import com.schedule.schedulemicroservice.entity.Route;
import com.schedule.schedulemicroservice.entity.Schedule;
import com.schedule.schedulemicroservice.entity.Train;
import com.schedule.schedulemicroservice.repository.RouteRepository;
import com.schedule.schedulemicroservice.repository.ScheduleRepository;
import com.schedule.schedulemicroservice.repository.TrainRepository;
import com.schedule.schedulemicroservice.service.ScheduleService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;

@ExtendWith(MockitoExtension.class)
@DataJpaTest

class ScheduleServiceTest {


    @Mock
    ScheduleRepository scheduleRepository;

    @Mock
    RouteRepository routeRepository;

    @Mock
    TrainRepository trainRepository;

    @Mock
    RestTemplate restTemplate;

    @InjectMocks
    ScheduleService scheduleService;


    private static final String TRAIN_BASE_URL = "http://localhost:8080/train";
    private static final String ROUTE_BASE_URL = "http://localhost:8081/route";

  /*  @Test
    void getAllDetailsTest(){
        Schedule schedule = new Schedule();
        NewScheduleRequest request = new NewScheduleRequest();
        // Setting data using setters
        request.setDepartureDateTime(LocalDateTime.now());
        request.setArrivalDateTime(LocalDateTime.now().plusHours(2));
        request.setTrainNumber(123);
        request.setRouteId(456);
        String url = TRAIN_BASE_URL + "/findTrain?trainNumber=" + request.getTrainNumber();
        Train train = new Train();
        train.setTrainNumber(123);
        train.setAcCoaches(12);
        Mockito.when(restTemplate.getForObject(url,Train.class)).thenReturn(train);
        trainRepository.save(train);
        String routeUrl = ROUTE_BASE_URL + "/findRoute?routeId=" + request.getRouteId();
        Route route = new Route();
        route.setSource("CityA");
        route.setDestination("CityB");
        Mockito.when(restTemplate.getForObject(routeUrl,Route.class)).thenReturn(route);
        routeRepository.save(route);
        schedule.setDepartureDateTime(LocalDateTime.now());
        schedule.setArrivalDateTime(LocalDateTime.now().plusHours(2));
        Mockito.when(scheduleRepository.save(schedule)).thenReturn(schedule);
        Schedule schedule1=scheduleService.getAllDetails(request);


    }*/



}
