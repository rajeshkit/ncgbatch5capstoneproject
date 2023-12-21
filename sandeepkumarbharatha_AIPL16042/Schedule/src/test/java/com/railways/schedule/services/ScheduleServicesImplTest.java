package com.railways.schedule.services;

import com.railways.route.model.Route;
import com.railways.schedule.exceptions.ScheduleIdNotFoundException;
import com.railways.schedule.model.Schedule;
import com.railways.schedule.model.ScheduleDemo;
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

import java.sql.Timestamp;
import java.util.Arrays;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class ScheduleServicesImplTest {
    @Mock
    private ScheduleRepository scheduleRepository;
    @InjectMocks
    private ScheduleServicesImpl scheduleServicesImpl;
    @Mock
    private RestTemplate restTemplate;

    @Test
    void insertScheduleDetails() {
        Train train = Train.builder().trainNumber(1234).trainName("satapddhi express")
                .totalKms(450).acCoaches(2).acCoachTotalSeats(12)
                .sleeperCoaches(5).sleeperCoachTotalSeats(10)
                .generalCoaches(9).generalCoacheTotalSeats(300).build();
        Route route = Route.builder()
                .routeId(1234).source("hyd").destination("tpty").totalKms(700).build();
        Schedule schedule = Schedule.builder().scheduleId(1234L)
                .departureDateTime(Timestamp.valueOf("2024-11-11 08:11:12"))
                .arrivalDateTime(Timestamp.valueOf("2023-11-12 04:54:08"))
                .train(train).route(route).build();

        ScheduleDemo scheduleDemo = ScheduleDemo.builder().scheduleId1(1234L).routeId(12345L).trainNumber(4567L).arrivalDateTime(Timestamp.valueOf("2024-11-11 08:11:12")).departureDateTime(Timestamp.valueOf("2023-11-12 04:54:08")).build();
        Mockito.when(restTemplate.getForEntity(any(String.class), eq(Train.class))).thenReturn(new ResponseEntity<>(train, HttpStatus.OK));
        Mockito.when(restTemplate.getForEntity(any(String.class), eq(Route.class))).thenReturn(new ResponseEntity<>(route, HttpStatus.OK));
        Mockito.when(scheduleRepository.save(any(Schedule.class))).thenReturn(schedule);
        assertEquals(schedule, scheduleServicesImpl.insertScheduleDetails(scheduleDemo));
    }


    @Test
    void getAllScheduleDetails() {
        Train train = Train.builder().trainNumber(1234).trainName("satapddhi express")
                .totalKms(450).acCoaches(2).acCoachTotalSeats(12)
                .sleeperCoaches(5).sleeperCoachTotalSeats(10)
                .generalCoaches(9).generalCoacheTotalSeats(300).build();
        Route route = Route.builder()
                .routeId(1234).source("hyd").destination("tpty").totalKms(700).build();
        Schedule schedule = Schedule.builder().scheduleId(1234L)
                .departureDateTime(Timestamp.valueOf("2024-11-11 08:11:12"))
                .arrivalDateTime(Timestamp.valueOf("2023-11-12 04:54:08"))
                .train(train).route(route).build();
        Train train1 = Train.builder().trainNumber(1234)
                .totalKms(450).acCoaches(2).acCoachTotalSeats(12)
                .sleeperCoaches(5).sleeperCoachTotalSeats(10)
                .generalCoaches(9).generalCoacheTotalSeats(300).build();
        Route route1 = Route.builder()
                .routeId(1234).source("hyd").destination("tpty").totalKms(700).build();
        Schedule schedule1 = Schedule.builder().scheduleId(1234L)
                .departureDateTime(Timestamp.valueOf("2024-11-11 08:11:12"))
                .arrivalDateTime(Timestamp.valueOf("2023-11-12 04:54:08"))
                .train(train1).route(route1).build();
        Mockito.when(scheduleRepository.findAll()).thenReturn(Arrays.asList(schedule1, schedule));
        assertEquals(2, scheduleServicesImpl.getAllScheduleDetails().size());

    }

    @Test
    void getScheduleById() throws ScheduleIdNotFoundException {
        Train train = Train.builder().trainNumber(1234).trainName("satapddhi express")
                .totalKms(450).acCoaches(2).acCoachTotalSeats(12)
                .sleeperCoaches(5).sleeperCoachTotalSeats(10)
                .generalCoaches(9).generalCoacheTotalSeats(300).build();
        Route route = Route.builder()
                .routeId(1234).source("hyd").destination("tpty").totalKms(700).build();
        Schedule schedule = Schedule.builder().scheduleId(1234L)
                .departureDateTime(Timestamp.valueOf("2024-11-11 08:11:12"))
                .arrivalDateTime(Timestamp.valueOf("2023-11-12 04:54:08"))
                .train(train).route(route).build();
        Mockito.when(scheduleRepository.findById(schedule.getScheduleId())).thenReturn(Optional.of(schedule));
        assertEquals(schedule, scheduleServicesImpl.getScheduleById(schedule.getScheduleId()));

    }

    @Test
    void getScheduleByIdWithException() {
        Train train = Train.builder().trainNumber(1234).trainName("satapddhi express")
                .totalKms(450).acCoaches(2).acCoachTotalSeats(12)
                .sleeperCoaches(5).sleeperCoachTotalSeats(10)
                .generalCoaches(9).generalCoacheTotalSeats(300).build();
        Route route = Route.builder()
                .routeId(1234).source("hyd").destination("tpty").totalKms(700).build();
        Schedule schedule = Schedule.builder().scheduleId(1234L)
                .departureDateTime(Timestamp.valueOf("2024-11-11 08:11:12"))
                .arrivalDateTime(Timestamp.valueOf("2023-11-12 04:54:08"))
                .train(train).route(route).build();
        Mockito.when(scheduleRepository.findById(schedule.getScheduleId())).thenReturn(Optional.empty());
        assertThrows(ScheduleIdNotFoundException.class, () -> {
            scheduleServicesImpl.getScheduleById(schedule.getScheduleId());
        });

    }

    @Test
    void updateScheduleById() throws ScheduleIdNotFoundException {
        Train train = Train.builder().trainNumber(1234).trainName("satapddhi express")
                .totalKms(450).acCoaches(2).acCoachTotalSeats(12)
                .sleeperCoaches(5).sleeperCoachTotalSeats(10)
                .generalCoaches(9).generalCoacheTotalSeats(300).build();
        Route route = Route.builder()
                .routeId(1234).source("hyd").destination("tpty").totalKms(700).build();
        Schedule schedule = Schedule.builder().scheduleId(1234L)
                .departureDateTime(Timestamp.valueOf("2024-11-11 08:11:12"))
                .arrivalDateTime(Timestamp.valueOf("2023-11-12 04:54:08"))
                .train(train).route(route).build();
        Mockito.when(scheduleRepository.findById(schedule.getScheduleId())).thenReturn(Optional.of(schedule));
        Mockito.when(scheduleRepository.save(schedule)).thenReturn(schedule);
        assertEquals(schedule, scheduleServicesImpl.updateScheduleById(schedule));

    }

    @Test
    void updateScheduleByIdWithException() {
        Train train = Train.builder().trainNumber(1234).trainName("satapddhi express")
                .totalKms(450).acCoaches(2).acCoachTotalSeats(12)
                .sleeperCoaches(5).sleeperCoachTotalSeats(10)
                .generalCoaches(9).generalCoacheTotalSeats(300).build();
        Route route = Route.builder()
                .routeId(1234).source("hyd").destination("tpty").totalKms(700).build();
        Schedule schedule = Schedule.builder().scheduleId(1234L)
                .departureDateTime(Timestamp.valueOf("2024-11-11 08:11:12"))
                .arrivalDateTime(Timestamp.valueOf("2023-11-12 04:54:08"))
                .train(train).route(route).build();
        Mockito.when(scheduleRepository.findById(schedule.getScheduleId())).thenReturn(Optional.empty());
        assertThrows(ScheduleIdNotFoundException.class, () -> {
            scheduleServicesImpl.updateScheduleById(schedule);
        });

    }

    @Test
    void deleteScheduleById() throws ScheduleIdNotFoundException {
        Train train = Train.builder().trainNumber(1234).trainName("satapddhi express")
                .totalKms(450).acCoaches(2).acCoachTotalSeats(12)
                .sleeperCoaches(5).sleeperCoachTotalSeats(10)
                .generalCoaches(9).generalCoacheTotalSeats(300).build();
        Route route = Route.builder()
                .routeId(1234).source("hyd").destination("tpty").totalKms(700).build();
        Schedule schedule = Schedule.builder().scheduleId(1234L)
                .departureDateTime(Timestamp.valueOf("2024-11-11 08:11:12"))
                .arrivalDateTime(Timestamp.valueOf("2023-11-12 04:54:08"))
                .train(train).route(route).build();
        Mockito.when(scheduleRepository.findById(schedule.getScheduleId())).thenReturn(Optional.of(schedule));
        assertEquals("schedule id deleted having id=" + schedule.getScheduleId(), scheduleServicesImpl.deleteScheduleById(schedule.getScheduleId()));
    }

    @Test
    void deleteScheduleByIdWithException() {
        Train train = Train.builder().trainNumber(1234).trainName("satapddhi express")
                .totalKms(450).acCoaches(2).acCoachTotalSeats(12)
                .sleeperCoaches(5).sleeperCoachTotalSeats(10)
                .generalCoaches(9).generalCoacheTotalSeats(300).build();
        Route route = Route.builder()
                .routeId(1234).source("hyd").destination("tpty").totalKms(700).build();
        Schedule schedule = Schedule.builder().scheduleId(1234L)
                .departureDateTime(Timestamp.valueOf("2024-11-11 08:11:12"))
                .arrivalDateTime(Timestamp.valueOf("2023-11-12 04:54:08"))
                .train(train).route(route).build();
        Mockito.when(scheduleRepository.findById(schedule.getScheduleId())).thenReturn(Optional.empty());
        assertThrows(ScheduleIdNotFoundException.class, () -> {
            scheduleServicesImpl.deleteScheduleById(schedule.getScheduleId());
        });

    }
}