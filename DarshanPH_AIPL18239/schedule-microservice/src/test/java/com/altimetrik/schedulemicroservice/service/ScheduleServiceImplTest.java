package com.altimetrik.schedulemicroservice.service;

import com.altimetrik.schedulemicroservice.exception.RouteNotExistsException;
import com.altimetrik.schedulemicroservice.exception.ScheduleIdNotExistsException;
import com.altimetrik.schedulemicroservice.exception.TrainNotExistsException;
import com.altimetrik.schedulemicroservice.model.Route;
import com.altimetrik.schedulemicroservice.model.Schedule;
import com.altimetrik.schedulemicroservice.model.ScheduleInput;
import com.altimetrik.schedulemicroservice.model.Train;
import com.altimetrik.schedulemicroservice.repository.RouteRepository;
import com.altimetrik.schedulemicroservice.repository.ScheduleRepository;
import com.altimetrik.schedulemicroservice.repository.TrainRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ScheduleServiceImplTest {
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

        @Test
        public void testAddSchedule() throws TrainNotExistsException, RouteNotExistsException {
            Train mockTrain = Train.builder()
                    .trainId(1)
                    .trainNumber(123)
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
                    .source("Mysuru")
                    .destination("Bengaluru")
                    .totalKms(150)
                    .build();
            when(restTemplate.getForObject(anyString(), eq(Route.class))).thenReturn(mockRoute);

            ScheduleInput scheduleInput = ScheduleInput.builder()
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

            Schedule result = scheduleServiceImpl.addSchedule(scheduleInput);
            verify(restTemplate, times(2)).getForObject(anyString(), any(Class.class));
            verify(trainRepository).save(any(Train.class));
            verify(routeRepository).save(any(Route.class));
            verify(scheduleRepository).save(any(Schedule.class));

            assertEquals(mockTrain.getTrainId(), result.getTrain().getTrainId());
            assertEquals(mockRoute.getRouteId(), result.getRoute().getRouteId());
        }

    @Test
    void testGetAllSchedule() {
        Schedule schedule1 = Schedule.builder().id(1).build();
        Schedule schedule2 = Schedule.builder().id(2).build();
        when(scheduleRepository.findAll()).thenReturn(Arrays.asList(schedule1,schedule2));
        List<Schedule> result = scheduleServiceImpl.getAllSchedule();
        assertEquals(2, result.size());
        assertEquals(1, result.get(0).getId());
    }

    @Test
    void testGetScheduleById() throws ScheduleIdNotExistsException {
        int scheduleId = 1;
        Schedule mockSchedule = Schedule.builder().id(scheduleId).build();
        Optional<Schedule> optionalSchedule = Optional.of(mockSchedule);
        when(scheduleRepository.findById(scheduleId)).thenReturn(optionalSchedule);
        Schedule result = scheduleServiceImpl.getScheduleById(scheduleId);
        assertNotNull(result);
        assertEquals(scheduleId, result.getId());
        verify(scheduleRepository, times(1)).findById(scheduleId);
    }

    @Test
    void testUpdateSchedule() throws ScheduleIdNotExistsException {
        int scheduleId = 1;
        Schedule existingSchedule = Schedule.builder().id(scheduleId).build();
        Schedule updatedSchedule = Schedule.builder().id(scheduleId).build();
        when(scheduleRepository.findById(scheduleId)).thenReturn(Optional.of(existingSchedule));
        when(scheduleRepository.save(updatedSchedule)).thenReturn(updatedSchedule);
        Schedule result = scheduleServiceImpl.updateSchedule(updatedSchedule);
        assertNotNull(result);
        assertEquals(scheduleId, result.getId());
        verify(scheduleRepository, times(1)).findById(scheduleId);
        verify(scheduleRepository, times(1)).save(updatedSchedule);
    }

    @Test
    void testDeleteSchedule() throws ScheduleIdNotExistsException {
        int scheduleId = 1;
        Schedule existingSchedule = Schedule.builder().id(scheduleId).build();
        when(scheduleRepository.findById(scheduleId)).thenReturn(Optional.of(existingSchedule));
        String result = scheduleServiceImpl.deleteSchedule(scheduleId);
        assertEquals("Schedule successfully deleted", result);
        verify(scheduleRepository, times(1)).findById(scheduleId);
        verify(scheduleRepository, times(1)).deleteById(scheduleId);
    }
}