package com.railwaybooking.Schedule.service;

import com.railwaybooking.Route.model.RouteInfo;
import com.railwaybooking.Schedule.exception.ScheduleNotFoundException;
import com.railwaybooking.Schedule.model.NewScheduleInfo;
import com.railwaybooking.Schedule.model.Schedule;
import com.railwaybooking.Schedule.repository.ScheduleRepository;
import com.railwaybooking.Train.model.TrainInfo;
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
public class ScheduleServiceImplTest {
    @Mock
    ScheduleRepository scheduleRepository;
    @Mock
    RestTemplate restTemplate;
    @InjectMocks
    ScheduleServiceImpl scheduleServiceImpl;
    @Test
    void addSchedule() {

        LocalDateTime arrivaldateTime = LocalDateTime.of(2023, 12, 22, 10, 45, 30);
        LocalDateTime departuredateTime = LocalDateTime.of(2023, 12, 23, 05, 25, 45);
        TrainInfo t = TrainInfo.builder().trainNumber(76542).trainName("Sarkar").totalKms(200).acCoaches(8).acCoachTotalSeats(80).sleeperCoaches(6).sleeperCoachTotalSeats(50).generalCoaches(4).generalCoachTotalSeats(80).build();
        RouteInfo r = RouteInfo.builder().routeId(123).source("Delhi").destination("Jaipur").totalKms(500).build();
        NewScheduleInfo resource = NewScheduleInfo.builder().arrivalDateTime(arrivaldateTime).departureDateTime(departuredateTime).trainNumber(76542).routeId(123).build();
        Schedule savedSchedule = Schedule.builder().scheduleId(1000L).arrivalDateTime(resource.getArrivalDateTime()).departureDateTime(resource.getDepartureDateTime()).trainInfo(t).routeInfo(r).build();
        Mockito.when(restTemplate.getForEntity(any(String.class), eq(TrainInfo.class))).thenReturn(new ResponseEntity<>(t, HttpStatus.OK));
        Mockito.when(restTemplate.getForEntity(any(String.class), eq(RouteInfo.class))).thenReturn(new ResponseEntity<>(r, HttpStatus.OK));
        Mockito.when(scheduleRepository.save(any(Schedule.class))).thenReturn(savedSchedule);
        assertEquals(savedSchedule, scheduleServiceImpl.addSchedule(resource));
    }

    @Test
    void getSchedule() {
        LocalDateTime arrivaldateTime = LocalDateTime.of(2023, 12, 22, 10, 45, 30);
        LocalDateTime departuredateTime = LocalDateTime.of(2023, 12, 23, 05, 25, 45);
        NewScheduleInfo resource1=NewScheduleInfo.builder().arrivalDateTime(arrivaldateTime).departureDateTime(departuredateTime).trainNumber(76542).routeId(123).build();
        NewScheduleInfo resource2=NewScheduleInfo.builder().arrivalDateTime(arrivaldateTime).departureDateTime(departuredateTime).trainNumber(12765).routeId(2).build();
        TrainInfo t= TrainInfo.builder().trainNumber(76542).trainName("Sarkar").totalKms(200).acCoaches(8).acCoachTotalSeats(80).sleeperCoaches(6).sleeperCoachTotalSeats(50).generalCoaches(4).generalCoachTotalSeats(80).build();
        RouteInfo r=RouteInfo.builder().routeId(123).source("Delhi").destination("Jaipur").totalKms(500).build();
        Schedule savedSchedule1=Schedule.builder().scheduleId(1000L).arrivalDateTime(resource1.getArrivalDateTime()).departureDateTime(resource1.getDepartureDateTime()).trainInfo(t).routeInfo(r).build();
        Schedule savedSchedule2=Schedule.builder().scheduleId(1001L).arrivalDateTime(resource2.getArrivalDateTime()).departureDateTime(resource2.getDepartureDateTime()).trainInfo(t).routeInfo(r).build();
        Mockito.when(scheduleRepository.findAll()).thenReturn(Arrays.asList(savedSchedule1,savedSchedule2));
        assertEquals(2,scheduleServiceImpl.getSchedule().size());
    }
    @Test
    void getScheduleById() throws ScheduleNotFoundException {
        LocalDateTime arrivaldateTime = LocalDateTime.of(2023, 12, 22, 10, 45, 30);
        LocalDateTime departuredateTime = LocalDateTime.of(2023, 12, 23, 05, 25, 45);
        NewScheduleInfo resource = NewScheduleInfo.builder().arrivalDateTime(arrivaldateTime).departureDateTime(departuredateTime).trainNumber(76542).routeId(123).build();
        Schedule savedSchedule=Schedule.builder().scheduleId(1000L).arrivalDateTime(resource.getArrivalDateTime()).departureDateTime(resource.getDepartureDateTime()).trainInfo(new TrainInfo()).routeInfo(new RouteInfo()).build();
        Mockito.when(scheduleRepository.findById(1000L)).thenReturn(Optional.of(savedSchedule));
        assertEquals(savedSchedule,scheduleServiceImpl.getScheduleById(1000L));
    }
    @Test
    void updateSchedule() throws ScheduleNotFoundException {
        LocalDateTime arrivaldateTime = LocalDateTime.of(2023, 12, 22, 10, 45, 30);
        LocalDateTime departuredateTime = LocalDateTime.of(2023, 12, 23, 05, 25, 45);
        NewScheduleInfo resource = NewScheduleInfo.builder().arrivalDateTime(arrivaldateTime).departureDateTime(departuredateTime).trainNumber(76542).routeId(123).build();
        Schedule savedSchedule=Schedule.builder().scheduleId(1000L).arrivalDateTime(resource.getArrivalDateTime()).departureDateTime(resource.getDepartureDateTime()).trainInfo(new TrainInfo()).routeInfo(new RouteInfo()).build();
        Mockito.when(scheduleRepository.findById(1000L)).thenReturn(Optional.of(savedSchedule));
        Mockito.when(scheduleRepository.save(savedSchedule)).thenReturn(savedSchedule);
        assertEquals(savedSchedule,scheduleServiceImpl.updateSchedule(savedSchedule));
    }
    @Test
    void deleteScheduleById() throws ScheduleNotFoundException {
        LocalDateTime arrivaldateTime = LocalDateTime.of(2023, 12, 22, 10, 45, 30);
        LocalDateTime departuredateTime = LocalDateTime.of(2023, 12, 23, 05, 25, 45);
        NewScheduleInfo resource = NewScheduleInfo.builder().arrivalDateTime(arrivaldateTime).departureDateTime(departuredateTime).trainNumber(76542).routeId(123).build();
        Schedule savedSchedule=Schedule.builder().scheduleId(1000L).arrivalDateTime(resource.getArrivalDateTime()).departureDateTime(resource.getDepartureDateTime()).trainInfo(new TrainInfo()).routeInfo(new RouteInfo()).build();
        Mockito.when(scheduleRepository.findById(1000L)).thenReturn(Optional.of(savedSchedule));
        assertEquals("Schedule deleted successfully",scheduleServiceImpl.deleteScheduleById(1000L));
    }
    @Test
    void getScheduleInfoByTrainNumber() {
        LocalDateTime arrivaldateTime = LocalDateTime.of(2023, 12, 22, 10, 45, 30);
        LocalDateTime departuredateTime = LocalDateTime.of(2023, 12, 23, 05, 25, 45);
        NewScheduleInfo resource = NewScheduleInfo.builder().arrivalDateTime(arrivaldateTime).departureDateTime(departuredateTime).trainNumber(76542L).routeId(123L).build();
        Schedule savedSchedule1=Schedule.builder().scheduleId(1000L).arrivalDateTime(resource.getArrivalDateTime()).departureDateTime(resource.getDepartureDateTime()).trainInfo(new TrainInfo()).routeInfo(new RouteInfo()).build();
        Schedule savedSchedule2=Schedule.builder().scheduleId(1001L).arrivalDateTime(resource.getArrivalDateTime()).departureDateTime(resource.getDepartureDateTime()).trainInfo(new TrainInfo()).routeInfo(new RouteInfo()).build();
        Mockito.when(scheduleRepository.findByTrainInfo_TrainNumber(76542L)).thenReturn(Arrays.asList(savedSchedule1,savedSchedule2));
        assertEquals(Arrays.asList(savedSchedule1,savedSchedule2),scheduleServiceImpl.getScheduleInfoByTrainNumber(76542L));
    }
}
