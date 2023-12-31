package com.altimetrik.schedulemicroservice.service;

import com.altimetrik.schedulemicroservice.exception.RouteIdNotFoundException;
import com.altimetrik.schedulemicroservice.exception.TrainIdNotFoundException;
import com.altimetrik.schedulemicroservice.model.Route;
import com.altimetrik.schedulemicroservice.model.Schedule;
import com.altimetrik.schedulemicroservice.model.ScheduleRequest;
import com.altimetrik.schedulemicroservice.model.Train;
import com.altimetrik.schedulemicroservice.repository.RouteRepository;
import com.altimetrik.schedulemicroservice.repository.ScheduleRepository;
import com.altimetrik.schedulemicroservice.repository.TrainRepository;
import lombok.Data;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.text.ParseException;
import java.time.LocalDateTime;
import java.util.Date;


import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class ScheduleServiceImplTest {

    @Mock
    private RestTemplate restTemplate;

    @InjectMocks
    private ScheduleServiceImpl scheduleService;

    @InjectMocks
    private TrainRepository trainRepository;

    @Mock
    private RouteRepository routeRepository;

    @Mock
    private ScheduleRepository scheduleRepository;

    @Test
    void addNewScheduleRequest() throws ParseException, TrainIdNotFoundException, RouteIdNotFoundException {
        Train train = Train.builder().trainNumber("100").trainName("BangloreExpress").acCoaches("Available").acCoachesTotalSeats("200").generalCoaches("Available").generalCoachesTotalSeats("420").build();

        Route route = Route.builder().routeId("1").source("Pune").destination("Bangalore").totalKms("786").build();

        Mockito.when(restTemplate.getForEntity(Mockito.eq("http://TRAIN-SERVICE/train-microservice/train/100"), Mockito.eq(Train.class))).thenReturn(new ResponseEntity<>(train, HttpStatus.OK));


        Mockito.when(restTemplate.getForEntity(Mockito.eq("http://ROUTE-SERVICE/route-microservice/route/1"), Mockito.eq(Route.class))).thenReturn(new ResponseEntity<>(route, HttpStatus.OK));

        ScheduleRequest scheduleRequest = ScheduleRequest.builder().arrivalDateTime(new Date()).departureDateTime(new Date()).routeId(route.getRouteId()).trainNumber(train.getTrainNumber()).build();
        Schedule result = scheduleService.addNewScheduleRequest(scheduleRequest);

        assertEquals(scheduleRequest.getDepartureDateTime(), result.getDepartureDateTime());
        assertEquals(scheduleRequest.getArrivalDateTime(), result.getArrivalDateTime());
        assertEquals(train, result.getTrain());
        assertEquals(route, result.getRoute());
        Mockito.verify(scheduleRepository, Mockito.times(1)).save((Mockito.any(Schedule.class)));

    }
}