package com.altimetrik.schedule.service;

import com.altimetrik.route.model.Route;
import com.altimetrik.schedule.exception.ScheduleIDNotFoundException;
import com.altimetrik.schedule.model.Schedule;
import com.altimetrik.schedule.model.ScheduleRequest;
import com.altimetrik.schedule.repository.ScheduleRepository;
import com.altimetrik.train.model.Train;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;

@ExtendWith(MockitoExtension.class)
class ScheduleServiceImplTest {
    @Mock
    ScheduleRepository scheduleRepository;
    @Mock
    RestTemplate restTemplate;
    @InjectMocks
    ScheduleServiceImpl scheduleServiceImpl;

    LocalDateTime departure=LocalDateTime.of(2023, 12, 25, 12, 35, 45);
    LocalDateTime arrival=LocalDateTime.of(2023, 12, 25, 12, 30, 45);
    ScheduleRequest scheduleRequest1=ScheduleRequest.builder().arrivalDateTime(arrival).departureDateTime(departure).trainNumber(1234).routeID(1).build();
    ScheduleRequest scheduleRequest2=ScheduleRequest.builder().arrivalDateTime(arrival).departureDateTime(departure).trainNumber(5678).routeID(2).build();
    Train train=Train.builder().trainNo(1234).trainName("JKLM").totalKms(700).acCoaches(10).acTotalSeats(720).sleeperCoaches(10).sleeperTotalSeats(720).generalCoaches(2).generalTotalSeats(144).build();
    Route route=Route.builder().routeId(1).trainNo(1234).source("SK").destination("HYD").totalKm(700).build();
    Schedule savedSchedule1=Schedule.builder().scheduleId(1000).arrivalDateTime(scheduleRequest1.getArrivalDateTime()).departureDateTime(scheduleRequest1.getDepartureDateTime()).train(train).route(route).build();
    Schedule savedSchedule2=Schedule.builder().scheduleId(1001).arrivalDateTime(scheduleRequest2.getArrivalDateTime()).departureDateTime(scheduleRequest2.getDepartureDateTime()).train(train).route(route).build();

    @Test
    void addSchedule() {
        Mockito.when(restTemplate.getForEntity(any(String.class),eq(Train.class))).thenReturn(new ResponseEntity<>(train, HttpStatus.OK));
        Mockito.when(restTemplate.getForEntity(any(String.class),eq(Route.class))).thenReturn(new ResponseEntity<>(route, HttpStatus.OK));
        Schedule savedSchedule=Schedule.builder().scheduleId(1000).arrivalDateTime(scheduleRequest1.getArrivalDateTime()).departureDateTime(scheduleRequest1.getDepartureDateTime()).train(train).route(route).build();
        Mockito.when(scheduleRepository.save(any(Schedule.class))).thenReturn(savedSchedule);
        assertEquals(savedSchedule,scheduleServiceImpl.addSchedule(scheduleRequest1));

    }

    @Test
    void viewAllSchedules() {
        Mockito.when(scheduleRepository.findAll()).thenReturn(Arrays.asList(savedSchedule1,savedSchedule2));
        assertEquals(2,scheduleServiceImpl.viewAllSchedules().size());
    }

    @Test
    void getScheduleById() throws ScheduleIDNotFoundException {
        Mockito.when(scheduleRepository.findById(1000)).thenReturn(Optional.of(savedSchedule1));
        assertEquals(savedSchedule1,scheduleServiceImpl.getScheduleById(1000));
    }

    @Test
    void updateSchedule() throws ScheduleIDNotFoundException {
        Mockito.when(scheduleRepository.findById(1000)).thenReturn(Optional.of(savedSchedule1));
        Mockito.when(scheduleRepository.save(savedSchedule1)).thenReturn(savedSchedule1);
        assertEquals(savedSchedule1,scheduleServiceImpl.updateSchedule(savedSchedule1));
    }

    @Test
    void deleteScheduleById() throws ScheduleIDNotFoundException {
        Mockito.when(scheduleRepository.findById(1000)).thenReturn(Optional.of(savedSchedule1));
        assertEquals("Schedule Deleted Successfully",scheduleServiceImpl.deleteScheduleById(1000));
    }

    @Test
    void getScheduleByTrainNo() {
        Mockito.when(scheduleRepository.findByTrain_trainNo(1234)).thenReturn(Arrays.asList(savedSchedule1,savedSchedule2));
        assertEquals(Arrays.asList(savedSchedule1,savedSchedule2),scheduleServiceImpl.getScheduleByTrainNo(1234));
    }
}