package com.altimetrik.schedulemicroservice.service;

import com.altimetrik.schedulemicroservice.exceptions.RouteNotExistsException;
import com.altimetrik.schedulemicroservice.exceptions.ScheduleIdNotExistsException;
import com.altimetrik.schedulemicroservice.exceptions.TrainIdNotExistsException;
import com.altimetrik.schedulemicroservice.model.Schedule;
import com.altimetrik.schedulemicroservice.repository.ScheduleRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import org.springframework.web.reactive.function.client.WebClient;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ScheduleServiceImplementationTest {

    @Mock
    private ScheduleRepository scheduleRepository;

    @Mock
    private WebClient.Builder webClientBuilder;

    @InjectMocks
    private ScheduleServiceImplementation scheduleService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }


    @Test
    void getAllSchedules() {
        // Given
        List<Schedule> schedules = new ArrayList<>();
        schedules.add(new Schedule());
        when(scheduleRepository.findAll()).thenReturn(schedules);

        // When
        List<Schedule> result = scheduleService.getAllSchedules();

        // Then
        assertEquals(schedules, result);
        verify(scheduleRepository, times(1)).findAll();
    }

    @Test
    void getScheduleById() {
        // Given
        String scheduleId = "S001";
        Schedule schedule = new Schedule();
        when(scheduleRepository.findById(scheduleId)).thenReturn(Optional.of(schedule));

        // When
        Schedule result = scheduleService.getScheduleById(scheduleId);

        // Then
        assertEquals(schedule, result);
        verify(scheduleRepository, times(1)).findById(scheduleId);
    }

    @Test
    void getScheduleByIdNotFound() {
        // Given
        String scheduleId = "S001";
        when(scheduleRepository.findById(scheduleId)).thenReturn(Optional.empty());

        // When / Then
        assertThrows(ScheduleIdNotExistsException.class, () -> scheduleService.getScheduleById(scheduleId));
    }

    @Test
    void deleteScheduleById() {
        // Given
        String scheduleId = "S001";
        Schedule schedule = new Schedule();
        when(scheduleRepository.findById(scheduleId)).thenReturn(Optional.of(schedule));

        // When
        String result = scheduleService.deleteScheduleById(scheduleId);

        // Then
        assertEquals("Schedule deleted successfully", result);
        verify(scheduleRepository, times(1)).deleteById(scheduleId);
    }

    @Test
    void deleteScheduleByIdNotFound() {
        // Given
        String scheduleId = "S001";
        when(scheduleRepository.findById(scheduleId)).thenReturn(Optional.empty());

        // When / Then
        assertThrows(ScheduleIdNotExistsException.class, () -> scheduleService.deleteScheduleById(scheduleId));
    }


    @Test
    void updateSchedule() throws RouteNotExistsException, TrainIdNotExistsException {
        // Given
        String scheduleId = "S001";
        Schedule existingSchedule = new Schedule();
        when(scheduleRepository.findById(scheduleId)).thenReturn(Optional.of(existingSchedule));

        Schedule updatedSchedule = new Schedule();
        updatedSchedule.setScheduleId(scheduleId);
        when(scheduleRepository.save(updatedSchedule)).thenReturn(updatedSchedule);

        // When
        Schedule result = scheduleService.updateSchedule(updatedSchedule);

        // Then
        assertEquals(updatedSchedule, result);
        verify(scheduleRepository, times(1)).findById(scheduleId);
        verify(scheduleRepository, times(1)).save(updatedSchedule);
    }

    @Test
    void updateScheduleNotFound() {
        // Given
        String scheduleId = "S001";
        Schedule updatedSchedule = new Schedule();
        updatedSchedule.setScheduleId(scheduleId);

        when(scheduleRepository.findById(scheduleId)).thenReturn(Optional.empty());

        // When / Then
        assertThrows(ScheduleIdNotExistsException.class, () -> scheduleService.updateSchedule(updatedSchedule));
    }





}
