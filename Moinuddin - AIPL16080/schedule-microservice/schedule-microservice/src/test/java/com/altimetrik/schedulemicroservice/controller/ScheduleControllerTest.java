package com.altimetrik.schedulemicroservice.controller;

import com.altimetrik.schedulemicroservice.exceptions.ScheduleIdNotExistsException;
import com.altimetrik.schedulemicroservice.exceptions.RouteNotExistsException;
import com.altimetrik.schedulemicroservice.exceptions.TrainIdNotExistsException;
import com.altimetrik.schedulemicroservice.model.Schedule;
import com.altimetrik.schedulemicroservice.service.ScheduleService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


class ScheduleControllerTest {

    @Mock
    private ScheduleService scheduleService;

    @InjectMocks
    private ScheduleController scheduleController;

    public ScheduleControllerTest() {
        MockitoAnnotations.openMocks(this);
    }


    @Test
    void addScheduleSuccess() throws Exception {
        // Given
        Schedule schedule = new Schedule();
        Mockito.when(scheduleService.addSchedule(schedule)).thenReturn(schedule);

        // When
        ResponseEntity<?> responseEntity = scheduleController.addSchedule(schedule, Mockito.mock(BindingResult.class));

        // Then
        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
        assertEquals(schedule, responseEntity.getBody());
    }

    @Test
    void addScheduleIdNotFound() throws Exception {
        // Given
        Schedule schedule = new Schedule();
        Mockito.when(scheduleService.addSchedule(schedule)).thenThrow(new ScheduleIdNotExistsException("Schedule not found"));

        // When
        ResponseEntity<?> responseEntity = scheduleController.addSchedule(schedule, Mockito.mock(BindingResult.class));

        // Then
        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
        assertEquals("Cannot update. Schedule with ID null not found.", responseEntity.getBody());
    }

    @Test
    void addScheduleTrainNotFound() throws Exception {
        // Given
        Schedule schedule = new Schedule();
        Mockito.when(scheduleService.addSchedule(schedule)).thenThrow(new TrainIdNotExistsException("Train not found"));

        // When
        ResponseEntity<?> responseEntity = scheduleController.addSchedule(schedule, Mockito.mock(BindingResult.class));

        // Then
        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
        assertEquals("Cannot update. Train with ID null not found.", responseEntity.getBody());
    }

    @Test
    void addScheduleRouteNotFound() throws Exception {
        // Given
        Schedule schedule = new Schedule();
        Mockito.when(scheduleService.addSchedule(schedule)).thenThrow(new RouteNotExistsException("Route not found"));

        // When
        ResponseEntity<?> responseEntity = scheduleController.addSchedule(schedule, Mockito.mock(BindingResult.class));

        // Then
        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
        assertEquals("Cannot update. Route with ID null not found.", responseEntity.getBody());
    }


    @Test
    void getAllSchedulesSuccess() {
        // Given
        List<Schedule> schedules = Arrays.asList(new Schedule(), new Schedule());
        Mockito.when(scheduleService.getAllSchedules()).thenReturn(schedules);

        // When
        ResponseEntity<List<Schedule>> responseEntity = scheduleController.getAllSchedules();

        // Then
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(schedules, responseEntity.getBody());
    }

    @Test
    void getScheduleByIdSuccess() {
        // Given
        String scheduleId = "S001";
        Schedule schedule = new Schedule();
        Mockito.when(scheduleService.getScheduleById(scheduleId)).thenReturn(schedule);

        // When
        ResponseEntity<?> responseEntity = scheduleController.getScheduleById(scheduleId);

        // Then
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(schedule, responseEntity.getBody());
    }

    @Test
    void getScheduleByIdNotFound() {
        // Given
        String scheduleId = "S001";
        Mockito.when(scheduleService.getScheduleById(scheduleId)).thenThrow(new ScheduleIdNotExistsException("Schedule not found"));

        // When
        ResponseEntity<?> responseEntity = scheduleController.getScheduleById(scheduleId);

        // Then
        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
        assertEquals("Schedule with ID S001 not found.", responseEntity.getBody());
    }
    @Test
    void deleteScheduleByIdSuccess() {
        // Given
        String scheduleId = "S001";
        Mockito.when(scheduleService.deleteScheduleById(scheduleId)).thenReturn("Schedule deleted successfully");

        // When
        ResponseEntity<String> responseEntity = scheduleController.deleteScheduleById(scheduleId);

        // Then
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals("Schedule with ID S001 deleted successfully.", responseEntity.getBody());
    }


    @Test
    void deleteScheduleByIdNotFound() {
        // Given
        String scheduleId = "S001";
        Mockito.doThrow(new ScheduleIdNotExistsException("Schedule not found")).when(scheduleService).deleteScheduleById(scheduleId);

        // When
        ResponseEntity<String> responseEntity = scheduleController.deleteScheduleById(scheduleId);

        // Then
        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
        assertEquals("Cannot delete. Schedule with ID S001 not found.", responseEntity.getBody());
    }

    @Test
    void updateScheduleSuccess() throws Exception {
        // Given
        Schedule schedule = new Schedule();
        Mockito.when(scheduleService.updateSchedule(schedule)).thenReturn(schedule);

        // When
        ResponseEntity<?> responseEntity = scheduleController.updateSchedule(schedule, Mockito.mock(BindingResult.class));

        // Then
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(schedule, responseEntity.getBody());
    }

    @Test
    void updateScheduleNotFound() throws Exception {
        // Given
        Schedule schedule = new Schedule();
        Mockito.when(scheduleService.updateSchedule(schedule)).thenThrow(new ScheduleIdNotExistsException("Schedule not found"));

        // When
        ResponseEntity<?> responseEntity = scheduleController.updateSchedule(schedule, Mockito.mock(BindingResult.class));

        // Then
        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
        assertEquals("Cannot update. Schedule with ID null not found.", responseEntity.getBody());
    }

    @Test
    void updateScheduleTrainNotFound() throws Exception {
        // Given
        Schedule schedule = new Schedule();
        Mockito.when(scheduleService.updateSchedule(schedule)).thenThrow(new TrainIdNotExistsException("Train not found"));

        // When
        ResponseEntity<?> responseEntity = scheduleController.updateSchedule(schedule, Mockito.mock(BindingResult.class));

        // Then
        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
        assertEquals("Cannot update. Train with ID null not found.", responseEntity.getBody());
    }

    @Test
    void updateScheduleRouteNotFound() throws Exception {
        // Given
        Schedule schedule = new Schedule();
        Mockito.when(scheduleService.updateSchedule(schedule)).thenThrow(new RouteNotExistsException("Route not found"));

        // When
        ResponseEntity<?> responseEntity = scheduleController.updateSchedule(schedule, Mockito.mock(BindingResult.class));

        // Then
        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
        assertEquals("Cannot update. Route with ID null not found.", responseEntity.getBody());
    }


}
