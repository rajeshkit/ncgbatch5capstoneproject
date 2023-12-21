package com.altimetrik.trainschedule.controller;


import com.altimetrik.trainschedule.controller.ScheduleController;
import com.altimetrik.trainschedule.modle.NewSchedule;
import com.altimetrik.trainschedule.modle.Schedule;
import com.altimetrik.trainschedule.service.ScheduleService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@WebMvcTest(ScheduleController.class)
class ScheduleControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    @MockBean
    private ScheduleService scheduleService;

    @Test
    void addSchedule() throws Exception {
        when(scheduleService.addSchedule(any())).thenReturn(new Schedule());

        NewSchedule newSchedule = new NewSchedule();
        String requestJson = new ObjectMapper().writeValueAsString(newSchedule);

        ResultActions resultActions = mockMvc.perform(post("/schedule-api/schedule")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestJson));

        resultActions.andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.scheduleId").exists());

        verify(scheduleService, times(1)).addSchedule(any());
    }

    @Test
    void getAllSchedule() throws Exception {
        Schedule schedule1 = Schedule.builder()
                .scheduleId(1)
                .departureDateTime(LocalDateTime.now())
                .arrivalDateTime(LocalDateTime.now())
                .build();
        Schedule schedule2 = Schedule.builder().
                scheduleId(2)
                .departureDateTime(LocalDateTime.now())
                .arrivalDateTime(LocalDateTime.now())
                .build();
        List<Schedule> schedules = Arrays.asList(schedule1, schedule2);
        when(scheduleService.getAllSchedule()).thenReturn(schedules);
        mockMvc.perform(MockMvcRequestBuilders.get("/schedule-api/schedule")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(schedule2)))
                .andExpect(status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andExpect(jsonPath("$.length()").value(2));
    }

    @Test
    void GetScheduleById() throws Exception {

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
    void deleteScheduleById() throws Exception {
        int scheduleId = 1;
        when(scheduleService.deleteScheduleById(scheduleId)).thenReturn("Schedule successfully deleted");
        mockMvc.perform(delete("/schedule-api/schedule/{id}", scheduleId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("Schedule successfully deleted"));


    }
}