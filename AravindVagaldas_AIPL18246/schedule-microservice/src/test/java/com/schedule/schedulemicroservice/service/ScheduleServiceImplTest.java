package com.schedule.schedulemicroservice.service;

import com.route.routemicroservice.model.Route;
import com.schedule.schedulemicroservice.model.Schedule;
import com.schedule.schedulemicroservice.model.ScheduleRequest;
import com.schedule.schedulemicroservice.repository.ScheduleRepository;
import com.train.trainmicroservice.model.Train;
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
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;


@ExtendWith(MockitoExtension.class)
class ScheduleServiceImplTest {
    @Mock
    private ScheduleRepository scheduleRepository;

    @Mock
    private RestTemplate restTemplate;

    @InjectMocks
    private ScheduleServiceImpl scheduleServiceimpl;

    String departureDateTimeString = "2023-12-25T15:30:00";
    String arrivalDateTimeString = "2023-12-27T08:45:00";
    DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME;
    LocalDateTime departureDateTime = LocalDateTime.parse(departureDateTimeString, formatter);
    LocalDateTime arrivalDateTime = LocalDateTime.parse(arrivalDateTimeString, formatter);

    Train mockTrain = Train.builder().trainNumber(1001).trainName("Mumbai Express").acCoaches(8).acCoachTotalSeats(280).sleeperCoaches(15).sleeperCoachTotalSeats(300).generalCoaches(40).generalCoachesTotalSeats(800).totalKms(1500).build();
    Route mockRoute = Route.builder().routeId(901).source("chennai").destination("hyderabad").totalKms(800).build();
    Schedule schedule = Schedule.builder().scheduleId(1000).route(mockRoute).train(mockTrain).departureDateTime(departureDateTime).arrivalDateTime(arrivalDateTime).build();


    @Test
    void addSchedule() {
        ScheduleRequest scheduleRequest = ScheduleRequest.builder().departureDateTime(departureDateTime).arrivalDateTime(arrivalDateTime).trainNumber(1001).routeId(901).build();

        Mockito.when(restTemplate.getForEntity(any(String.class), eq(Train.class))).thenReturn(new ResponseEntity<>(mockTrain, HttpStatus.OK));
        Mockito.when(restTemplate.getForEntity(any(String.class), eq(Route.class))).thenReturn(new ResponseEntity<>(mockRoute, HttpStatus.OK));

        Mockito.when(scheduleRepository.save(any(Schedule.class))).thenReturn(schedule);
        assertEquals(schedule, scheduleServiceimpl.addSchedule(scheduleRequest));

    }

    @Test
    void getAllSchedules() {
        Schedule schedule2 = Schedule.builder().route(mockRoute).train(mockTrain).departureDateTime(departureDateTime).arrivalDateTime(arrivalDateTime).build();

        Mockito.when(scheduleRepository.findAll()).thenReturn(Arrays.asList(schedule, schedule2));
        assertEquals(2, scheduleServiceimpl.getAllSchedules().size());

    }

    @Test
    void getScheduleById() {
        Mockito.when(scheduleRepository.findById(101)).thenReturn(Optional.of(schedule));
        assertEquals(schedule, scheduleServiceimpl.getScheduleById(101));
    }

    @Test
    void updateSchedule() {

        Schedule schedule = Schedule.builder().scheduleId(101).route(mockRoute).train(mockTrain).departureDateTime(departureDateTime).arrivalDateTime(arrivalDateTime).build();
        Mockito.when(scheduleRepository.save(schedule)).thenReturn(schedule);
        assertEquals(schedule, scheduleServiceimpl.updateSchedule(schedule));
    }

    @Test
    void deleteScheduleByScheduleId() {

        Mockito.when(scheduleRepository.findById(101)).thenReturn(Optional.of(schedule));
        assertEquals("Schedule Deleted Successfully", scheduleServiceimpl.deleteScheduleByScheduleId(101));

    }

    @Test
    void getScheduleByTrainNo() {

        Schedule schedule2 = Schedule.builder().scheduleId(101).route(mockRoute).train(mockTrain).departureDateTime(departureDateTime).arrivalDateTime(arrivalDateTime).build();

        Mockito.when(scheduleRepository.findByTrain_trainNumber(1001)).thenReturn(Arrays.asList(schedule, schedule2));
        assertEquals(2, scheduleServiceimpl.getScheduleByTrainNo(1001).size());

    }
}