package com.booking.schedule.service;

import com.booking.route.exception.RouteIdNotExistsException;
import com.booking.route.model.RouteResources;
import com.booking.schedule.exception.ScheduleIdNotExistsException;
import com.booking.schedule.model.NewScheduleRequest;
import com.booking.schedule.model.Schedule;
import com.booking.schedule.repository.ScheduleRepository;
import com.booking.train.exception.TrainNumberNotExistsException;
import com.booking.train.model.TrainResources;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Optional;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class ScheduleServiceImplTest {
    @Mock
    private ScheduleRepository scheduleRepository;

    @Mock
    private RestTemplate restTemplate;

    @InjectMocks
    private ScheduleServiceImpl scheduleServiceimpl;


    @Test
    void addScheduleResources() throws RouteIdNotExistsException, TrainNumberNotExistsException {
        LocalDateTime departureTime= LocalDateTime.parse("2023-12-21T09:14:30");
        LocalDateTime arrivalTime= LocalDateTime.parse("2023-12-22T04:32:30");

        TrainResources trainResources=TrainResources.builder()
                .trainNumber(17644l).trainName("Circar").totalKms(780.0)
                .acCoaches(3).acCoachTotalSeats(168).generalCoaches(1).generalCoachesTotalSeats(60)
                .sleeperCoaches(10).sleeperCoachTotalSeats(600).build();

        RouteResources routeResources= RouteResources.builder()
                .routeId(1234l)
                .source("Kakinada").destination("Hyderabad")
                .totalKms(780.0).build();

        NewScheduleRequest newScheduleRequest= NewScheduleRequest.builder()
                .trainNumber(trainResources.getTrainNumber()).routeId(routeResources.getRouteId())
                .departureDateTime(Timestamp.valueOf(departureTime)).arrivalDateTime(Timestamp.valueOf(arrivalTime)).build();

//        Mockito.when(restTemplate.getForObject(Mockito.anyString(), Mockito.eq(TrainResources.class))).thenReturn(trainResources);
//        Mockito.when(restTemplate.getForObject(Mockito.anyString(), Mockito.eq(RouteResources.class))).thenReturn(routeResources);

        Mockito.when(restTemplate.getForEntity(any(String.class), eq(TrainResources.class))).thenReturn(new ResponseEntity<>(trainResources,HttpStatus.OK));
        Mockito.when(restTemplate.getForEntity(any(String.class), eq(RouteResources.class))).thenReturn(new ResponseEntity<>(routeResources,HttpStatus.OK));


        Schedule schedule= Schedule.builder().scheduleId(100l)
                .departureDateTime(Timestamp.valueOf(departureTime)).arrivalDateTime(Timestamp.valueOf(arrivalTime))
                .train(trainResources).route(routeResources).build();


        Mockito.when(scheduleRepository.save(Mockito.any(Schedule.class))).thenReturn(schedule);

        assertEquals(schedule,scheduleServiceimpl.addScheduleResources(newScheduleRequest));

    }

    @Test
    void getScheduleResourcesById() throws ScheduleIdNotExistsException {
        LocalDateTime departureTime= LocalDateTime.parse("2023-12-21T09:14:30");
        LocalDateTime arrivalTime= LocalDateTime.parse("2023-12-22T04:32:30");

        TrainResources trainResources=TrainResources.builder()
                .trainNumber(17644l).trainName("Circar").totalKms(780.0)
                .acCoaches(3).acCoachTotalSeats(168).generalCoaches(1).generalCoachesTotalSeats(60)
                .sleeperCoaches(10).sleeperCoachTotalSeats(600).build();

        RouteResources routeResources= RouteResources.builder()
                .routeId(1234l)
                .source("Kakinada").destination("Hyderabad")
                .totalKms(780.0).build();

        Schedule schedule= Schedule.builder().scheduleId(100l)
                .departureDateTime(Timestamp.valueOf(departureTime)).arrivalDateTime(Timestamp.valueOf(arrivalTime))
                .train(trainResources).route(routeResources).build();

        Mockito.when(scheduleRepository.findById(100l)).thenReturn(Optional.ofNullable(schedule));

        assertEquals(schedule.getScheduleId(), scheduleServiceimpl.getScheduleResourcesById(schedule.getScheduleId()).getScheduleId());
    }

    @Test
    void getAllScheduleResources() {

        LocalDateTime departureTime= LocalDateTime.parse("2023-12-21T09:14:30");
        LocalDateTime arrivalTime= LocalDateTime.parse("2023-12-22T04:32:30");

        TrainResources trainResources=TrainResources.builder()
                .trainNumber(17644l).trainName("Circar").totalKms(780.0)
                .acCoaches(3).acCoachTotalSeats(168).generalCoaches(1).generalCoachesTotalSeats(60)
                .sleeperCoaches(10).sleeperCoachTotalSeats(600).build();

        RouteResources routeResources= RouteResources.builder()
                .routeId(1234l)
                .source("Kakinada").destination("Hyderabad")
                .totalKms(780.0).build();

        Schedule schedule= Schedule.builder().scheduleId(100l)
                .departureDateTime(Timestamp.valueOf(departureTime)).arrivalDateTime(Timestamp.valueOf(arrivalTime))
                .train(trainResources).route(routeResources).build();

        Schedule schedule1= Schedule.builder().scheduleId(101l)
                .departureDateTime(Timestamp.valueOf(departureTime)).arrivalDateTime(Timestamp.valueOf(arrivalTime))
                .train(trainResources).route(routeResources).build();

        Mockito.when(scheduleRepository.findAll()).thenReturn(Arrays.asList(schedule, schedule1));

        assertEquals(2, scheduleServiceimpl.getAllScheduleResources().size());

    }

    @Test
    void updateSchedule() throws ScheduleIdNotExistsException {
        LocalDateTime departureTime= LocalDateTime.parse("2023-12-21T09:14:30");
        LocalDateTime arrivalTime= LocalDateTime.parse("2023-12-22T04:32:30");

        TrainResources trainResources=TrainResources.builder()
                .trainNumber(17644l).trainName("Circar").totalKms(780.0)
                .acCoaches(3).acCoachTotalSeats(168).generalCoaches(1).generalCoachesTotalSeats(60)
                .sleeperCoaches(10).sleeperCoachTotalSeats(600).build();

        RouteResources routeResources= RouteResources.builder()
                .routeId(1234l)
                .source("Kakinada").destination("Hyderabad")
                .totalKms(780.0).build();

        Schedule schedule= Schedule.builder().scheduleId(100l)
                .departureDateTime(Timestamp.valueOf(departureTime)).arrivalDateTime(Timestamp.valueOf(arrivalTime))
                .train(trainResources).route(routeResources).build();

        Mockito.when(scheduleRepository.findById(schedule.getScheduleId())).thenReturn(Optional.ofNullable(schedule));
        Mockito.when(scheduleRepository.save(schedule)).thenReturn(schedule);

        assertEquals(schedule, scheduleServiceimpl.updateSchedule(schedule));

    }

    @Test
    void deleteScheduleById() throws ScheduleIdNotExistsException {
        LocalDateTime departureTime= LocalDateTime.parse("2023-12-21T09:14:30");
        LocalDateTime arrivalTime= LocalDateTime.parse("2023-12-22T04:32:30");

        TrainResources trainResources=TrainResources.builder()
                .trainNumber(17644l).trainName("Circar").totalKms(780.0)
                .acCoaches(3).acCoachTotalSeats(168).generalCoaches(1).generalCoachesTotalSeats(60)
                .sleeperCoaches(10).sleeperCoachTotalSeats(600).build();

        RouteResources routeResources= RouteResources.builder()
                .routeId(1234l)
                .source("Kakinada").destination("Hyderabad")
                .totalKms(780.0).build();

        Schedule schedule= Schedule.builder().scheduleId(100l)
                .departureDateTime(Timestamp.valueOf(departureTime)).arrivalDateTime(Timestamp.valueOf(arrivalTime))
                .train(trainResources).route(routeResources).build();

        Mockito.when(scheduleRepository.findById(schedule.getScheduleId())).thenReturn(Optional.of(schedule));
        assertEquals("Train Resource is deleted Successfully in Schedule Table ", scheduleServiceimpl.deleteScheduleById(schedule.getScheduleId()));
    }
}