package com.altimetrik.schedule.service;

import com.altimetrik.schedule.exception.ScheduleIdNotExistsException;
import com.altimetrik.schedule.model.NewScheduleRequest;
import com.altimetrik.schedule.model.Route;
import com.altimetrik.schedule.model.Schedule;
import com.altimetrik.schedule.model.Train;
import com.altimetrik.schedule.repository.ScheduleRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ScheduleServiceImplTest {

    @Mock
    private ScheduleRepository scheduleRepository;

    @Mock
    private RestTemplate restTemplate;

    @InjectMocks
    private ScheduleServiceImpl scheduleService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testAddSchedule() {
        when(restTemplate.getForObject(anyString(), eq(Train.class))).thenReturn(new Train());
        when(restTemplate.getForObject(anyString(), eq(Route.class))).thenReturn(new Route());

        when(scheduleRepository.save(any())).thenReturn(new Schedule());

        NewScheduleRequest newScheduleRequest = new NewScheduleRequest();
        newScheduleRequest.setTrainNum(16591);
        newScheduleRequest.setRouteId(4620);

        Schedule result = scheduleService.addSchedule(newScheduleRequest);
        
        assertNotNull(result);
        verify(scheduleRepository, times(1)).save(any());
    }

    @Test
    void testGetAllSchedules() {
        List<Schedule> schedules = new ArrayList<>();
        when(scheduleRepository.findAll()).thenReturn(schedules);

        List<Schedule> result = scheduleService.getAllSchedules();
        assertNotNull(result);
        assertEquals(0, result.size());
        verify(scheduleRepository, times(1)).findAll();
    }

    @Test
    void testGetScheduleById() {
        when(scheduleRepository.findById(anyInt())).thenReturn(Optional.of(new Schedule()));

        assertDoesNotThrow(() -> scheduleService.getScheduleById(1));
        verify(scheduleRepository, times(1)).findById(anyInt());
    }

    @Test
    void testGetScheduleById_NotExists() {
        when(scheduleRepository.findById(anyInt())).thenReturn(Optional.empty());

        assertThrows(ScheduleIdNotExistsException.class, () -> scheduleService.getScheduleById(1));
        verify(scheduleRepository, times(1)).findById(anyInt());
    }

    @Test
    void testUpdateSchedule() {
        when(scheduleRepository.save(any())).thenReturn(new Schedule());

        when(scheduleRepository.findById(anyInt())).thenReturn(Optional.of(new Schedule()));

        Schedule schedule = new Schedule();
        schedule.setScheduleId(1);

        assertDoesNotThrow(() -> scheduleService.updateSchedule(schedule));
        verify(scheduleRepository, times(1)).save(any());
    }

    @Test
    void testUpdateSchedule_NotExists() {
        when(scheduleRepository.findById(anyInt())).thenReturn(Optional.empty());

        Schedule schedule = new Schedule();
        schedule.setScheduleId(1);

        assertThrows(ScheduleIdNotExistsException.class, () -> scheduleService.updateSchedule(schedule));
        verify(scheduleRepository, never()).save(any());
    }

    @Test
    void testDeleteSchedule() {
        when(scheduleRepository.findById(anyInt())).thenReturn(Optional.of(new Schedule()));

        assertDoesNotThrow(() -> assertEquals("Schedule Deleted Successfully", scheduleService.deleteSchedule(1)));
        verify(scheduleRepository, times(1)).deleteById(anyInt());
    }
    @Test
    void testDeleteSchedule_NotExists() {
        when(scheduleRepository.findById(anyInt())).thenReturn(Optional.empty());

        Schedule schedule = new Schedule();
        schedule.setScheduleId(1);

        assertThrows(ScheduleIdNotExistsException.class, () -> scheduleService.deleteSchedule(schedule.getScheduleId()));
        verify(scheduleRepository, never()).save(any());
    }

}
