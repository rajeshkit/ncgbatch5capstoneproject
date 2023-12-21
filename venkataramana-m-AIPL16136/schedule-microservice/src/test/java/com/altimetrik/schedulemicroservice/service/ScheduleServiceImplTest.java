package com.altimetrik.schedulemicroservice.service;

import com.altimetrik.schedulemicroservice.exception.ScheduleIdAlreadyExistsException;
import com.altimetrik.schedulemicroservice.exception.ScheduleIdNotExistsException;
import com.altimetrik.schedulemicroservice.model.NewScheduleRequest;
import com.altimetrik.schedulemicroservice.model.Route;
import com.altimetrik.schedulemicroservice.model.Schedule;
import com.altimetrik.schedulemicroservice.model.Train;
import com.altimetrik.schedulemicroservice.repository.ScheduleRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.client.RestTemplate;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ScheduleServiceImplTest {
    @Mock
    private ScheduleRepository scheduleRepository;

    @Mock
    private RestTemplate restTemplate;

    @InjectMocks
    private ScheduleServiceImpl scheduleService;

    @Test
    void testAddSchedule() throws ScheduleIdNotExistsException, ScheduleIdAlreadyExistsException {
        NewScheduleRequest newScheduleRequest = NewScheduleRequest.builder()
                .departureDateTime("2023-01-01 12:00:00")
                .arrivalDateTime("2023-01-01 15:00:00")
                .trainNumber(123)
                .routeId(456)
                .build();


        Train mockTrain = Train.builder().trainNumber(123).build();
        Route mockRoute = Route.builder().routeId(456).build();

        when(restTemplate.getForObject(anyString(), eq(Train.class), anyInt())).thenReturn(mockTrain);
        when(restTemplate.getForObject(anyString(), eq(Route.class), anyInt())).thenReturn(mockRoute);

        when(scheduleRepository.save(any(Schedule.class))).thenReturn(mock(Schedule.class));

        Schedule result = scheduleService.addSchedule(newScheduleRequest);

        assertNotNull(result);
        verify(restTemplate, times(1)).getForObject("http://localhost:4013/train-api/train/{trainId}", Train.class, 123);
        verify(restTemplate, times(1)).getForObject("http://localhost:8181/routes-api/routes/{routeId}", Route.class, 456);
        verify(scheduleRepository, times(1)).save(any(Schedule.class));
    }

    @Test
    void testGetScheduleById() throws ScheduleIdNotExistsException {
        long scheduleId = 1;
        Schedule mockSchedule = Schedule.builder().scheduleId(scheduleId).build();

        when(scheduleRepository.findById(scheduleId)).thenReturn(Optional.of(mockSchedule));

        Schedule result = scheduleService.getScheduleById(scheduleId);

        assertNotNull(result);
        assertEquals(scheduleId, result.getScheduleId());
        verify(scheduleRepository, times(1)).findById(scheduleId);
    }

    @Test
    void testGetAllSchedules() {
        Schedule mockSchedule = Schedule.builder().scheduleId(1).build();
        when(scheduleRepository.findAll()).thenReturn(Collections.singletonList(mockSchedule));

        List<Schedule> result = scheduleService.getAllSchedules();

        assertNotNull(result);
        assertEquals(1, result.size());
        verify(scheduleRepository, times(1)).findAll();
    }

    @Test
    void testUpdateSchedule() throws ScheduleIdNotExistsException {
        long scheduleId = 1;
        NewScheduleRequest updatedSchedule = NewScheduleRequest.builder()
                .departureDateTime("2023-01-01 12:00:00")
                .arrivalDateTime("2023-01-01 15:00:00")
                .trainNumber(123)
                .routeId(456)
                .build();
        Schedule mockExistingSchedule = Schedule.builder().scheduleId(scheduleId).build();
        when(scheduleRepository.findById(scheduleId)).thenReturn(Optional.of(mockExistingSchedule));
        when(restTemplate.getForObject(anyString(), eq(Train.class), eq(123))).thenReturn(Train.builder().trainNumber(123).build());
        when(restTemplate.getForObject(anyString(), eq(Route.class), eq(456))).thenReturn(Route.builder().routeId(456).build());
        when(scheduleRepository.save(any(Schedule.class))).thenAnswer(invocation -> {
            Schedule savedSchedule = invocation.getArgument(0);
            savedSchedule.setArrivalDateTime(Timestamp.valueOf(updatedSchedule.getArrivalDateTime()));
            return savedSchedule;
        });
        Schedule result = scheduleService.updateSchedule(scheduleId, updatedSchedule);
        assertNotNull(result);
        assertEquals("2023-01-01 15:00:00.0", result.getArrivalDateTime().toString());
        verify(scheduleRepository, times(1)).findById(scheduleId);
        verify(restTemplate, times(1)).getForObject("http://localhost:4013/train-api/train/{trainId}", Train.class, 123);
        verify(restTemplate, times(1)).getForObject("http://localhost:8181/routes-api/routes/{routeId}", Route.class, 456);
        verify(scheduleRepository, times(1)).save(any(Schedule.class));
    }

    @Test
    void testDeleteScheduleById() throws ScheduleIdNotExistsException {
        long scheduleId = 1;
        Schedule mockSchedule = Schedule.builder().scheduleId(scheduleId).build();

        when(scheduleRepository.findById(scheduleId)).thenReturn(Optional.of(mockSchedule));

        String result = scheduleService.deleteScheduleById(scheduleId);

        assertNotNull(result);
        assertEquals("Schedule with ID 1 deleted successfully.", result);
        verify(scheduleRepository, times(1)).findById(scheduleId);
        verify(scheduleRepository, times(1)).deleteById(scheduleId);
    }
}
