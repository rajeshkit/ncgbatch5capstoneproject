package com.Booking.schedule.service;

import com.Booking.route.customexception.RouteNotFindException;
import com.Booking.route.model.RouteResources;
import com.Booking.schedule.customexception.ScheduleNotFind;
import com.Booking.schedule.model.Schedule;
import com.Booking.schedule.model.ScheduleResources;
import com.Booking.schedule.repository.ScheduleRepository;
import com.Booking.train.customexception.TrainIdNotFoundException;
import com.Booking.train.model.TrainResources;
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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
@ExtendWith(MockitoExtension.class)
public class ScheduleServiceTest {
    @Mock
    ScheduleRepository scheduleRepository;
    @Mock
    RestTemplate restTemplate;
    @InjectMocks
    ScheduleServiceImplementation scheduleServiceImpl;

    @Test
    void addSchedule() throws TrainIdNotFoundException, RouteNotFindException {
        LocalDateTime arrivaldateTime = LocalDateTime.of(2023, 12, 25, 12, 30, 45);
        LocalDateTime departuredateTime = LocalDateTime.of(2023, 12, 25, 12, 35, 45);
        Schedule resource=Schedule.builder().arrivalDateTime(arrivaldateTime).departureDateTime(departuredateTime).trainNumber(199L).RouteId(100L).build();
        TrainResources t= TrainResources.builder().trainNumber(199L).trainName("kachigudaexpress").totalkms(111.0).acCoaches(10).acCoachTotalSeats(300).sleepercoaching(65).sleeperCoachTotalSeats(90).generalCoaches(40).generalCoachesTotalSeats(10000).build();
        RouteResources r=RouteResources.builder().routeId(100L).source("rajasthan").destination("mumbai").totalkms(500.0).build();
        Mockito.when(restTemplate.getForEntity(any(String.class), eq(TrainResources.class))).thenReturn(new ResponseEntity<>(t, HttpStatus.OK));
        Mockito.when(restTemplate.getForEntity(any(String.class), eq(RouteResources.class))).thenReturn(new ResponseEntity<>(r,HttpStatus.OK));
        ScheduleResources savedSchedule=ScheduleResources.builder().scheduleId(1000L).arrivalDateTime(resource.getArrivalDateTime()).departureDateTime(resource.getDepartureDateTime()).train(t).route(r).build();
        Mockito.when(scheduleRepository.save(any(ScheduleResources.class))).thenReturn(savedSchedule);
        assertEquals(savedSchedule,scheduleServiceImpl.addSchedule(resource));
    }

    @Test
    void getSchedule() {
        LocalDateTime arrivaldateTime = LocalDateTime.of(2023, 12, 25, 12, 30, 45);
        LocalDateTime departuredateTime = LocalDateTime.of(2023, 12, 25, 12, 35, 45);
        Schedule resource1=Schedule.builder().arrivalDateTime(arrivaldateTime).departureDateTime(departuredateTime).trainNumber(199L).RouteId(100L).build();
        Schedule resource2=Schedule.builder().arrivalDateTime(arrivaldateTime).departureDateTime(departuredateTime).trainNumber(12345L).RouteId(1234L).build();
        TrainResources t= TrainResources.builder().trainNumber(199L).trainName("kachigudaexpress").totalkms(111.0).acCoaches(10).acCoachTotalSeats(300).sleepercoaching(65).sleeperCoachTotalSeats(90).generalCoaches(40).generalCoachesTotalSeats(10000).build();
        RouteResources r=RouteResources.builder().routeId(100L).source("rajasthan").destination("mumbai").totalkms(500.0).build();
        ScheduleResources savedSchedule1=ScheduleResources.builder().scheduleId(1000L).arrivalDateTime(resource1.getArrivalDateTime()).departureDateTime(resource1.getDepartureDateTime()).train(t).route(r).build();
        ScheduleResources savedSchedule2=ScheduleResources.builder().scheduleId(1001L).arrivalDateTime(resource2.getArrivalDateTime()).departureDateTime(resource2.getDepartureDateTime()).train(t).route(r).build();
        Mockito.when(scheduleRepository.findAll()).thenReturn(Arrays.asList(savedSchedule1,savedSchedule2));
        assertEquals(2,scheduleServiceImpl.getSchedule().size());
    }

    @Test
    void getScheduleById() throws ScheduleNotFind {
        LocalDateTime arrivaldateTime = LocalDateTime.of(2023, 12, 25, 12, 30, 45);
        LocalDateTime departuredateTime = LocalDateTime.of(2023, 12, 25, 12, 35, 45);
        Schedule resource = Schedule.builder().arrivalDateTime(arrivaldateTime).departureDateTime(departuredateTime).trainNumber(199L).RouteId(100L).build();
        ScheduleResources savedSchedule=ScheduleResources.builder().scheduleId(1000L).arrivalDateTime(resource.getArrivalDateTime()).departureDateTime(resource.getDepartureDateTime()).train(new TrainResources()).route(new RouteResources()).build();
        Mockito.when(scheduleRepository.findById(1000L)).thenReturn(Optional.of(savedSchedule));
        assertEquals(savedSchedule,scheduleServiceImpl.getScheduleById(1000L));
    }

    @Test
    void updateSchedule() throws ScheduleNotFind {
        LocalDateTime arrivaldateTime = LocalDateTime.of(2023, 12, 25, 12, 30, 45);
        LocalDateTime departuredateTime = LocalDateTime.of(2023, 12, 25, 12, 35, 45);
        Schedule resource = Schedule.builder().arrivalDateTime(arrivaldateTime).departureDateTime(departuredateTime).trainNumber(199L).RouteId(100L).build();
        ScheduleResources savedSchedule=ScheduleResources.builder().scheduleId(1000L).arrivalDateTime(resource.getArrivalDateTime()).departureDateTime(resource.getDepartureDateTime()).train(new TrainResources()).route(new RouteResources()).build();
        Mockito.when(scheduleRepository.findById(1000L)).thenReturn(Optional.of(savedSchedule));
        Mockito.when(scheduleRepository.save(savedSchedule)).thenReturn(savedSchedule);
        assertEquals(savedSchedule,scheduleServiceImpl.updateSchedule(savedSchedule));
    }

    @Test
    void deleteschedule() throws ScheduleNotFind {
        LocalDateTime arrivaldateTime = LocalDateTime.of(2023, 12, 25, 12, 30, 45);
        LocalDateTime departuredateTime = LocalDateTime.of(2023, 12, 25, 12, 35, 45);
        Schedule resource = Schedule.builder().arrivalDateTime(arrivaldateTime).departureDateTime(departuredateTime).trainNumber(199L).RouteId(100L).build();
        ScheduleResources savedSchedule=ScheduleResources.builder().scheduleId(1000L).arrivalDateTime(resource.getArrivalDateTime()).departureDateTime(resource.getDepartureDateTime()).train(new TrainResources()).route(new RouteResources()).build();
        Mockito.when(scheduleRepository.findById(1000L)).thenReturn(Optional.of(savedSchedule));
        assertEquals("Schedule deleted succesfully",scheduleServiceImpl.deleteschedule(1000L));
    }

    @Test
    void getSchedulesByTrainNumber() {
        LocalDateTime arrivaldateTime = LocalDateTime.of(2023, 12, 25, 12, 30, 45);
        LocalDateTime departuredateTime = LocalDateTime.of(2023, 12, 25, 12, 35, 45);
        Schedule resource = Schedule.builder().arrivalDateTime(arrivaldateTime).departureDateTime(departuredateTime).trainNumber(199L).RouteId(100L).build();
        ScheduleResources savedSchedule1=ScheduleResources.builder().scheduleId(1000L).arrivalDateTime(resource.getArrivalDateTime()).departureDateTime(resource.getDepartureDateTime()).train(new TrainResources()).route(new RouteResources()).build();
        ScheduleResources savedSchedule2=ScheduleResources.builder().scheduleId(1001L).arrivalDateTime(resource.getArrivalDateTime()).departureDateTime(resource.getDepartureDateTime()).train(new TrainResources()).route(new RouteResources()).build();
        Mockito.when(scheduleRepository.findByTrain_trainNumber(199L)).thenReturn(Arrays.asList(savedSchedule1,savedSchedule2));
        assertEquals(Arrays.asList(savedSchedule1,savedSchedule2),scheduleServiceImpl.getSchedulesBytrainNumber(199L));
    }
}
