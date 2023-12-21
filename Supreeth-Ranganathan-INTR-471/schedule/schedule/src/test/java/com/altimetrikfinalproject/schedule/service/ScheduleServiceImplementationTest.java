package com.altimetrikfinalproject.schedule.service;

import com.altimetrikfinalproject.schedule.entity.NewScheduleRequest;
import com.altimetrikfinalproject.schedule.entity.RouteResponse;
import com.altimetrikfinalproject.schedule.entity.ScheduleResponse;
import com.altimetrikfinalproject.schedule.entity.TrainResponse;
import com.altimetrikfinalproject.schedule.repository.SchedulerRepository;
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
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class ScheduleServiceImplementationTest {

    @Mock
    private RestTemplate restTemplate;

    @InjectMocks
    private ScheduleServiceImplementation scheduleService;

    @Mock
    private SchedulerRepository scheduleRepository;

    @Test
    void addNewScheduleRequest() throws ParseException {
        TrainResponse train = TrainResponse.builder().trainNumber(1).trainName("BangloreExpress").acCoaches(10).totalAcCoachSeats(200).generalCoaches(20).generalCoachesTotalSeats(420).build();

        RouteResponse route = RouteResponse.builder().routeId(1).source("Pune").destination("Bangalore").totalKms(786).build();

        Mockito.when(restTemplate.getForEntity(Mockito.eq("http://localhost:3030/train-api/train/1"), Mockito.eq(TrainResponse.class))).thenReturn(new ResponseEntity<>(train, HttpStatus.OK));


        Mockito.when(restTemplate.getForEntity(Mockito.eq("http://localhost:3031/route-api/route/1"), Mockito.eq(RouteResponse.class))).thenReturn(new ResponseEntity<>(route, HttpStatus.OK));

        NewScheduleRequest scheduleRequest = NewScheduleRequest.builder().arrivalDateTime(new Date("2023-12-19T15:00:00")).departureDateTime(new Date("2023-12-19T15:00:00")).routeId(route.getRouteId()).trainId(train.getTrainNumber()).build();
        ScheduleResponse result = scheduleService.addNewScheduleRequest(scheduleRequest);

        assertEquals(scheduleRequest.getDepartureDateTime(), result.getDepartureDateTime());
        assertEquals(scheduleRequest.getArrivalDateTime(), result.getArrivalDateTime());
        assertEquals(scheduleRequest.getTrainId(), result.getTrainId());
        assertEquals(scheduleRequest.getRouteId(), result.getRouteId());
        assertEquals(train, result.getTrainResponse());
        assertEquals(route, result.getRouteResponse());
        Mockito.verify(scheduleRepository, Mockito.times(1)).save((Mockito.any(NewScheduleRequest.class)));
    }
}