package com.altimetrik.schedule.schedulerestapi.service;

import com.altimetrik.schedule.schedulerestapi.exception.RouteIdNotFoundException;
import com.altimetrik.schedule.schedulerestapi.exception.TrainIdNotFoundException;
import com.altimetrik.schedule.schedulerestapi.model.NewScheduleRequest;
import com.altimetrik.schedule.schedulerestapi.model.Route;
import com.altimetrik.schedule.schedulerestapi.model.Schedule;
import com.altimetrik.schedule.schedulerestapi.model.Train;
import com.altimetrik.schedule.schedulerestapi.repository.RouteRepository;
import com.altimetrik.schedule.schedulerestapi.repository.ScheduleRepository;
import com.altimetrik.schedule.schedulerestapi.repository.TrainRepository;
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
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
class ScheduleServiceImplTest {

    @Mock
    private RestTemplate restTemplate;

    @Mock
    private ScheduleRepository scheduleRepository;

    @Mock
    private TrainRepository trainRepository;

    @Mock
    private RouteRepository routeRepository;

    @InjectMocks
    private ScheduleService scheduleService = new ScheduleServiceImpl();

    @Test
    void addScheduleRequest() throws TrainIdNotFoundException, RouteIdNotFoundException, ParseException {
        Train train = Train.builder().trainNumber("100").trainName("BangloreExpress").acCoaches("Available").acCoachesTotalSeats(200).generalCoaches("Available").generalCoachesTotalSeats(420).build();

        Route route = Route.builder().routeId("1").source("Pune").destination("Bangalore").totalKmsDistance(786).build();

        Mockito.when(restTemplate.getForEntity(Mockito.eq("http://TRAIN-RESTAPI/train-api/train/100"), Mockito.eq(Train.class))).thenReturn(new ResponseEntity<>(train, HttpStatus.OK));

        Mockito.when(restTemplate.getForEntity(Mockito.eq("http://ROUTE-RESTAPI/route-api/route/1"), Mockito.eq(Route.class))).thenReturn(new ResponseEntity<>(route, HttpStatus.OK));

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        Date arrivalDateTime = dateFormat.parse("2023-12-19T10:00:00");
        Date departureDateTime = dateFormat.parse("2023-12-19T15:00:00");

        NewScheduleRequest newScheduleRequest = new NewScheduleRequest(departureDateTime, arrivalDateTime, "100", "1");
        Schedule result = scheduleService.addScheduleRequest(newScheduleRequest);


        assertEquals("100", result.getTrain().getTrainNumber());
        assertEquals("1", result.getRoute().getRouteId());

    }
}