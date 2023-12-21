package com.altimetrik.schedule.controller;

import com.altimetrik.schedule.exception.ScheduleIdNotExistsException;
import com.altimetrik.schedule.model.NewScheduleRequest;
import com.altimetrik.schedule.model.Schedule;
import com.altimetrik.schedule.service.ScheduleService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class ScheduleControllerTest {

    @Mock
    private ScheduleService scheduleService;

    @InjectMocks
    private ScheduleController scheduleController;

    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(scheduleController).build();
    }

    @Test
    void testAddSchedule() throws Exception {
        when(scheduleService.addSchedule(any())).thenReturn(new Schedule());

        NewScheduleRequest newScheduleRequest = new NewScheduleRequest();
        String requestJson = new ObjectMapper().writeValueAsString(newScheduleRequest);

        ResultActions resultActions = mockMvc.perform(post("/schedule-api/schedule")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestJson));

        resultActions.andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.scheduleId").exists());

        verify(scheduleService, times(1)).addSchedule(any());
    }

    @Test
    void testGetAllSchedules() throws Exception {

        List<Schedule> schedules = new ArrayList<>();
        when(scheduleService.getAllSchedules()).thenReturn(schedules);

        mockMvc.perform(get("/schedule-api/schedule"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$").isArray());

        verify(scheduleService, times(1)).getAllSchedules();
    }

    @Test
    void testGetScheduleById() throws Exception {

        when(scheduleService.getScheduleById(anyInt())).thenReturn(new Schedule());

        mockMvc.perform(get("/schedule-api/schedule/1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.scheduleId").exists());

        verify(scheduleService, times(1)).getScheduleById(anyInt());
    }

    @Test
    void testUpdateSchedule() throws Exception {

        when(scheduleService.updateSchedule(any())).thenReturn(new Schedule());

        Schedule schedule = new Schedule();

        String requestJson = new ObjectMapper().writeValueAsString(schedule);

        mockMvc.perform(put("/schedule-api/schedule")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestJson))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.scheduleId").exists());

        verify(scheduleService, times(1)).updateSchedule(any());
    }

    @Test
    void testDeleteSchedule() throws Exception {

        when(scheduleService.deleteSchedule(anyInt())).thenReturn("Schedule Deleted Successfully");

        mockMvc.perform(delete("/schedule-api/schedule/1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.message").value("Schedule Deleted Successfully"));

        verify(scheduleService, times(1)).deleteSchedule(anyInt());
    }
}
