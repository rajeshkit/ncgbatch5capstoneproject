package com.altimetrik.SCHEDULE.service;

//import com.altimetrik.SCHEDULE.exception.RouteNotFoundException;
//import com.altimetrik.SCHEDULE.exception.ScheduleIdNotExistsException;
//import com.altimetrik.SCHEDULE.exception.TrainNotFoundException;
//import com.altimetrik.SCHEDULE.model.Route;
//import com.altimetrik.SCHEDULE.model.Schedule;
//import com.altimetrik.SCHEDULE.model.ScheduleRequest;
//import com.altimetrik.SCHEDULE.model.Train;
//import com.altimetrik.SCHEDULE.repository.RouteRepository;
//import com.altimetrik.SCHEDULE.repository.ScheduleRepository;
//import com.altimetrik.SCHEDULE.repository.TrainRepository;
//import org.junit.jupiter.api.Assertions;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.MockitoAnnotations;
//import org.springframework.web.client.RestTemplate;
//
//import java.time.LocalDateTime;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Optional;
//
//import static javax.management.Query.eq;
////import static jdk.internal.org.objectweb.asm.util.CheckClassAdapter.verify;
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.mockito.ArgumentMatchers.anyString;
//import static org.mockito.Mockito.when;



import com.altimetrik.SCHEDULE.exception.RouteNotFoundException;
import com.altimetrik.SCHEDULE.exception.ScheduleIdNotExistsException;
import com.altimetrik.SCHEDULE.exception.TrainNotFoundException;
import com.altimetrik.SCHEDULE.model.Route;
import com.altimetrik.SCHEDULE.model.Schedule;
import com.altimetrik.SCHEDULE.model.ScheduleRequest;
import com.altimetrik.SCHEDULE.model.Train;
import com.altimetrik.SCHEDULE.repository.RouteRepository;
import com.altimetrik.SCHEDULE.repository.ScheduleRepository;
import com.altimetrik.SCHEDULE.repository.TrainRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class ScheduleServiceImplTest {
    @Mock
    private Train train;

    @Mock
    private Route route;
    @Mock
    private ScheduleRepository scheduleRepository;
    @Mock
    private RestTemplate restTemplate;
    @Mock
    private TrainRepository trainRepository;

    @Mock
    private RouteRepository routeRepository;
    @InjectMocks
    private ScheduleServiceImpl scheduleServiceImpl;



    @InjectMocks
    private ScheduleServiceImpl scheduleService;

    @BeforeEach
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testAddSchedule() throws TrainNotFoundException, RouteNotFoundException {
        Train mockTrain = Train.builder()
                .trainId(1)
                .trainName("wadeyar Express")
                .acCoaches(15)
                .acCoachTotalSeats(100)
                .sleeperCoaches(20)
                .sleeperCoachTotalSeats(120)
                .generalCoaches(20)
                .generalCoachTotalSeats(100)
                .totalKms(150)
                .build();
        when(restTemplate.getForObject(anyString(), eq(Train.class))).thenReturn(mockTrain);
        Route mockRoute = Route.builder()
                .routeId(1)
                .Source("Mysuru")
                .Destination("Bengaluru")
                .totalKms(150)
                .build();
        when(restTemplate.getForObject(anyString(), eq(Route.class))).thenReturn(mockRoute);
        ScheduleRequest scheduleInput = ScheduleRequest.builder()
                .trainId(1)
                .routeId(1)
                .departureDateTime(LocalDateTime.now())
                .arrivalDateTime(LocalDateTime.now())
                .build();
        Schedule schedule = Schedule.builder()
                .train(mockTrain)
                .route(mockRoute)
                .departureDateTime(LocalDateTime.now())
                .arrivalDateTime((LocalDateTime.now()))
                .build();
        when(trainRepository.save(any())).thenReturn(mockTrain);
        when(routeRepository.save(any())).thenReturn(mockRoute);
        when(scheduleRepository.save(any())).thenReturn(schedule);
        Schedule result = scheduleServiceImpl.createSchedule(scheduleInput);
        verify(restTemplate, times(2)).getForObject(anyString(), any(Class.class));
        verify(trainRepository).save(any(Train.class));
        verify(routeRepository).save(any(Route.class));
        verify(scheduleRepository).save(any(Schedule.class));
        assertEquals(mockTrain.getTrainId(), result.getTrain().getTrainId());
        assertEquals(mockRoute.getRouteId(), result.getRoute().getRouteId());
    }










    @Test
    public void testUpdateSchedule() throws ScheduleIdNotExistsException {
        Schedule scheduleToUpdate = new Schedule();
        scheduleToUpdate.setScheduleId(1);
        when(scheduleRepository.save(scheduleToUpdate)).thenReturn(scheduleToUpdate);
        when(scheduleRepository.findById(scheduleToUpdate.getScheduleId())).thenReturn(Optional.of(scheduleToUpdate));

        Schedule updatedRoute = scheduleService.updateSchedule(scheduleToUpdate);

        Assertions.assertNotNull(scheduleToUpdate);

    }
    @Test
    public void testGetAllSchedules() {
        List<Schedule> scheduleList = new ArrayList<>();
        when(scheduleRepository.findAll()).thenReturn(scheduleList);

        List<Schedule> retrievedSchedules = scheduleService.getAllSchedules();

        assertEquals(scheduleList, retrievedSchedules);

    }
    @Test
    public void testGetScheduleById_ExistingId() throws ScheduleIdNotExistsException {
        int sId = 1;
        Schedule ScheduleToget = new Schedule();
        ScheduleToget.setScheduleId(sId);
        when(scheduleRepository.findById(sId)).thenReturn(Optional.of(ScheduleToget));

        Schedule retrievedRoute = scheduleService.getScheduleById(sId);

        assertEquals(ScheduleToget, retrievedRoute);
    }

    @Test
    public void testGetScheduleById_NonExistingId() {
        int sId = 1;
        when(scheduleRepository.findById(sId)).thenReturn(Optional.empty());

        Assertions.assertThrows(ScheduleIdNotExistsException.class, () -> scheduleService.getScheduleById(sId));

    }
    @Test
    public void testDeleteScheduleById_ExistingId() throws ScheduleIdNotExistsException {
        int sId = 1;
        Schedule ScheduleToDelete = new Schedule();
        ScheduleToDelete.setScheduleId(sId);
        when(scheduleRepository.findById(sId)).thenReturn(Optional.of(ScheduleToDelete));

        String deletionMessage = scheduleService.deleteScheduleById(sId);

        assertEquals("Schedule successfully deleted", deletionMessage);
    }
}
