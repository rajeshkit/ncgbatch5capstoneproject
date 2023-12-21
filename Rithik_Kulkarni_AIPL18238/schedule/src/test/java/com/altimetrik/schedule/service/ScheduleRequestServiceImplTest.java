package com.altimetrik.schedule.service;

import com.altimetrik.schedule.exception.ScheduleIdNotExistException;
import com.altimetrik.schedule.model.Route;
import com.altimetrik.schedule.model.Schedule;
import com.altimetrik.schedule.model.ScheduleRequest;
import com.altimetrik.schedule.model.Train;
import com.altimetrik.schedule.repository.ScheduleRequestRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


@ExtendWith(MockitoExtension.class)
class ScheduleRequestServiceImplTest {

    @Mock
    private ScheduleRequestRepository scheduleRequestRepository;

    @Mock
    private RestTemplate restTemplate;


    @InjectMocks
    private ScheduleRequestServiceImpl scheduleRequestService;
    
    public LocalDateTime dateTime = LocalDateTime.now();


    @Test
    void addSchedule() {

        ScheduleRequest sr1 = ScheduleRequest.builder()
                .scheduleId(111)
                .departureDateTime(dateTime)
                .arrivalDateTime(dateTime)
                .trainNumber(222)
                .routeId(333)
                .build();

        Train tr1 = Train.builder()
                .trainNumber(222)
                .trainName("Test-tr1")
                .totalKms(999)
                .acCoaches(11)
                .acCoachTotalSeats(333)
                .sleeperCoaches(11)
                .sleeperCoachTotalSeats(333)
                .generalCoaches(11)
                .generalCoachTotalSeats(333)
                .build();

        Route rt1 = Route.builder()
                .routeId(333)
                .source("Test-S1")
                .destination("Test-D1")
                .totalKms(999)
                .build();

        Schedule s1 = Schedule.builder()
                .scheduleId(111)
                .departureDateTime(dateTime)
                .arrivalDateTime(dateTime)
                .train(tr1)
                .route(rt1)
                .build();

        Mockito.when(restTemplate.getForObject("http://localhost:8282/train-api/train/222", Train.class))
                .thenReturn(tr1);
        Mockito.when(restTemplate.getForObject("http://localhost:8181/route-api/route/333", Route.class))
                .thenReturn(rt1);
        Mockito.when(scheduleRequestRepository.save(sr1)).thenReturn(sr1);
        Assertions.assertEquals(s1, scheduleRequestService.addSchedule(sr1));
    }

    @Test
    void getAllScheduleRequests() {

        ScheduleRequest sr1 = ScheduleRequest.builder()
                .scheduleId(111)
                .departureDateTime(dateTime)
                .arrivalDateTime(dateTime)
                .trainNumber(222)
                .routeId(333)
                .build();

        Train tr1 = Train.builder()
                .trainNumber(222)
                .trainName("Test-tr1")
                .totalKms(999)
                .acCoaches(11)
                .acCoachTotalSeats(333)
                .sleeperCoaches(11)
                .sleeperCoachTotalSeats(333)
                .generalCoaches(11)
                .generalCoachTotalSeats(333)
                .build();

        Route rt1 = Route.builder()
                .routeId(333)
                .source("Test-S1")
                .destination("Test-D1")
                .totalKms(999)
                .build();

        Schedule s1 = Schedule.builder()
                .scheduleId(111)
                .departureDateTime(dateTime)
                .arrivalDateTime(dateTime)
                .train(tr1)
                .route(rt1)
                .build();
        Mockito.when(restTemplate.getForObject("http://localhost:8282/train-api/train/222", Train.class))
                .thenReturn(tr1);
        Mockito.when(restTemplate.getForObject("http://localhost:8181/route-api/route/333", Route.class))
                .thenReturn(rt1);
        Mockito.when(scheduleRequestRepository.findAll()).thenReturn(Arrays.asList(sr1, sr1));
        assertEquals(2, scheduleRequestService.getAllScheduleRequests().size());
    }

    @Test
    void getScheduleRequestByScheduleId() throws ScheduleIdNotExistException {

        ScheduleRequest sr1 = ScheduleRequest.builder()
                .scheduleId(111)
                .departureDateTime(dateTime)
                .arrivalDateTime(dateTime)
                .trainNumber(222)
                .routeId(333)
                .build();

        Train tr1 = Train.builder()
                .trainNumber(222)
                .trainName("Test-tr1")
                .totalKms(999)
                .acCoaches(11)
                .acCoachTotalSeats(333)
                .sleeperCoaches(11)
                .sleeperCoachTotalSeats(333)
                .generalCoaches(11)
                .generalCoachTotalSeats(333)
                .build();

        Route rt1 = Route.builder()
                .routeId(333)
                .source("Test-S1")
                .destination("Test-D1")
                .totalKms(999)
                .build();

        Schedule s1 = Schedule.builder()
                .scheduleId(111)
                .departureDateTime(dateTime)
                .arrivalDateTime(dateTime)
                .train(tr1)
                .route(rt1)
                .build();

        Mockito.when(restTemplate.getForObject("http://localhost:8282/train-api/train/222", Train.class))
                .thenReturn(tr1);
        Mockito.when(restTemplate.getForObject("http://localhost:8181/route-api/route/333", Route.class))
                .thenReturn(rt1);
        Mockito.when(scheduleRequestRepository.findById(111)).thenReturn(Optional.of(sr1));
        Assertions.assertEquals(111, scheduleRequestService.getScheduleRequestByScheduleId(111).getScheduleId());
    }

    @Test
    void getScheduleRequestByScheduleIdWithException() {

        ScheduleRequest sr1 = ScheduleRequest.builder()
                .scheduleId(111)
                .departureDateTime(dateTime)
                .arrivalDateTime(dateTime)
                .trainNumber(222)
                .routeId(333)
                .build();

        Train tr1 = Train.builder()
                .trainNumber(222)
                .trainName("Test-tr1")
                .totalKms(999)
                .acCoaches(11)
                .acCoachTotalSeats(333)
                .sleeperCoaches(11)
                .sleeperCoachTotalSeats(333)
                .generalCoaches(11)
                .generalCoachTotalSeats(333)
                .build();

        Route rt1 = Route.builder()
                .routeId(333)
                .source("Test-S1")
                .destination("Test-D1")
                .totalKms(999)
                .build();

        Schedule s1 = Schedule.builder()
                .scheduleId(111)
                .departureDateTime(dateTime)
                .arrivalDateTime(dateTime)
                .train(tr1)
                .route(rt1)
                .build();

        Mockito.when(scheduleRequestRepository.findById(555)).thenReturn(Optional.empty());
        assertThrows(ScheduleIdNotExistException.class, () -> {
            scheduleRequestService.getScheduleRequestByScheduleId(555);
        });
    }

    @Test
    void updateScheduleRequest() throws ScheduleIdNotExistException {

        ScheduleRequest sr1 = ScheduleRequest.builder()
                .scheduleId(111)
                .departureDateTime(dateTime)
                .arrivalDateTime(dateTime)
                .trainNumber(222)
                .routeId(333)
                .build();

        Mockito.when(scheduleRequestRepository.findById(111)).thenReturn(Optional.of(sr1));
        Mockito.when(scheduleRequestRepository.save(sr1)).thenReturn(sr1);
        Assertions.assertEquals(sr1, scheduleRequestService.updateScheduleRequest(sr1));


    }
}