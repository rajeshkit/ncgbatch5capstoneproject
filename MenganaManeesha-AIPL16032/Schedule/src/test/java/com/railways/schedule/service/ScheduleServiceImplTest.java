package com.railways.schedule.service;

import com.railways.route.model.Route;
import com.railways.schedule.exception.ScheduleNotFind;
import com.railways.schedule.model.NewScheduleResource;
import com.railways.schedule.model.Schedule;
import com.railways.schedule.repository.ScheduleRepository;
import com.railways.train.model.Train;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class ScheduleServiceImplTest {
    @Mock
    ScheduleRepository scheduleRepository;
    @Mock
    RestTemplate restTemplate;
    @InjectMocks
    ScheduleServiceImpl scheduleServiceImpl;

    LocalDateTime arrivaldateTime = LocalDateTime.of(2023, 12, 25, 12, 30, 45);
    LocalDateTime departuredateTime = LocalDateTime.of(2023, 12, 25, 12, 35, 45);
    Train t = Train.builder().trainNumber(123).trainName("ABC").totalKms(100).acCoaches(10).acCoachTotalSeats(100).sleeperCoaches(5).sleeperCoachTotalSeats(50).generalCoaches(8).generalCoachTotalSeats(80).build();
    Route r = Route.builder().routeId(1).source("HYD").destination("BNG").totalKms(500).build();
    NewScheduleResource resource = NewScheduleResource.builder().arrivalDateTime(arrivaldateTime).departureDateTime(departuredateTime).trainNumber(123).routeId(1).build();
    Schedule savedSchedule = Schedule.builder().scheduleId(1000L).arrivalDateTime(resource.getArrivalDateTime()).departureDateTime(resource.getDepartureDateTime()).train(t).route(r).build();

    @Test
    void addSchedule() {
        Mockito.when(restTemplate.getForEntity(any(String.class), eq(Train.class))).thenReturn(new ResponseEntity<>(t, HttpStatus.OK));
        Mockito.when(restTemplate.getForEntity(any(String.class), eq(Route.class))).thenReturn(new ResponseEntity<>(r, HttpStatus.OK));
        Mockito.when(scheduleRepository.save(any(Schedule.class))).thenReturn(savedSchedule);
        assertEquals(savedSchedule, scheduleServiceImpl.addSchedule(resource));
    }

    @Test
    void getSchedule() {
        NewScheduleResource resource2 = NewScheduleResource.builder().arrivalDateTime(arrivaldateTime).departureDateTime(departuredateTime).trainNumber(456).routeId(2).build();
        Schedule savedSchedule2 = Schedule.builder().scheduleId(1001L).arrivalDateTime(resource2.getArrivalDateTime()).departureDateTime(resource2.getDepartureDateTime()).train(t).route(r).build();
        Mockito.when(scheduleRepository.findAll()).thenReturn(Arrays.asList(savedSchedule, savedSchedule2));
        assertEquals(2, scheduleServiceImpl.getSchedule().size());
    }

    @Test
    void getScheduleById() throws ScheduleNotFind {
        Mockito.when(scheduleRepository.findById(1000L)).thenReturn(Optional.of(savedSchedule));
        assertEquals(savedSchedule, scheduleServiceImpl.getScheduleById(1000L));
    }

    @Test
    void updateSchedule() throws ScheduleNotFind {
        Mockito.when(scheduleRepository.findById(1000L)).thenReturn(Optional.of(savedSchedule));
        Mockito.when(scheduleRepository.save(savedSchedule)).thenReturn(savedSchedule);
        assertEquals(savedSchedule, scheduleServiceImpl.updateSchedule(savedSchedule));
    }

    @Test
    void deleteschedule() throws ScheduleNotFind {
        Mockito.when(scheduleRepository.findById(1000L)).thenReturn(Optional.of(savedSchedule));
        assertEquals("Schedule deleted succesfully", scheduleServiceImpl.deleteschedule(1000L));
    }

    @Test
    void getSchedulesByTrainNumber() {
        Schedule savedSchedule2 = Schedule.builder().scheduleId(1001L).arrivalDateTime(resource.getArrivalDateTime()).departureDateTime(resource.getDepartureDateTime()).train(t).route(r).build();
        Mockito.when(scheduleRepository.findByTrain_TrainNumber(123L)).thenReturn(Arrays.asList(savedSchedule, savedSchedule2));
        assertEquals(Arrays.asList(savedSchedule, savedSchedule2), scheduleServiceImpl.getSchedulesByTrainNumber(123L));
    }
}