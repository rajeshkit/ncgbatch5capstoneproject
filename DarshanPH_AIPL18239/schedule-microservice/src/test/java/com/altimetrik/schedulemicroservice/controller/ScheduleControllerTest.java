package com.altimetrik.schedulemicroservice.controller;

import com.altimetrik.schedulemicroservice.model.Schedule;
import com.altimetrik.schedulemicroservice.model.ScheduleInput;
import com.altimetrik.schedulemicroservice.service.ScheduleService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


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
        ScheduleInput schedule2 = ScheduleInput.builder()
                .id(1)
                .trainId(1)
                .routeId(1)
                .arrivalDateTime(LocalDateTime.now())
                .departureDateTime(LocalDateTime.now())
                .build();
        Schedule schedule = Schedule.builder().id(1).build();
        when(scheduleService.addSchedule(schedule2)).thenReturn(schedule);
        mockMvc.perform(post("/schedule-api/schedule")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(schedule2)))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1));
    }

    @Test
    void getAllSchedule() throws Exception {
        Schedule schedule1 = Schedule.builder()
                .id(1)
                .departureDateTime(LocalDateTime.now())
                .arrivalDateTime(LocalDateTime.now())
                .build();
        Schedule schedule2 = Schedule.builder().
                id(2)
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
    void getScheduleById() throws Exception {
        int scheduleId = 1;
        Schedule schedule = Schedule.builder()
                .id(scheduleId)
                .departureDateTime(LocalDateTime.now())
                .arrivalDateTime(LocalDateTime.now())
                .build();
        Mockito.when(scheduleService.getScheduleById(scheduleId)).thenReturn(schedule);
        mockMvc.perform(MockMvcRequestBuilders.get("/schedule-api/schedule/{id}", scheduleId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(scheduleId)))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(scheduleId));
    }

    @Test
    void updateSchedule() throws Exception {
        int scheduleId = 1;
        Schedule updatedSchedule = Schedule.builder()
                .id(scheduleId)
                .departureDateTime(LocalDateTime.now())
                .arrivalDateTime(LocalDateTime.now())
                .build();
        Mockito.when(scheduleService.updateSchedule(updatedSchedule)).thenReturn(updatedSchedule);
        mockMvc.perform(MockMvcRequestBuilders.put("/schedule-api/schedule")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updatedSchedule)))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(scheduleId));
    }

    @Test
    void deleteScheduleById() throws Exception {
        int scheduleId = 1;
        when(scheduleService.deleteSchedule(scheduleId)).thenReturn("Schedule successfully deleted");
        mockMvc.perform(delete("/schedule-api/schedule/{id}", scheduleId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("Schedule successfully deleted"));


    }
}