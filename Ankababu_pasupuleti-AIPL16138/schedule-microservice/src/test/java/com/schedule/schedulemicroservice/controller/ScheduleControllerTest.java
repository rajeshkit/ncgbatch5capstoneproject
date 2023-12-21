package com.schedule.schedulemicroservice.controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.route.routemicroservice.model.Route;
import com.schedule.schedulemicroservice.model.Schedule;
import com.schedule.schedulemicroservice.model.ScheduleRequest;
import com.schedule.schedulemicroservice.service.ScheduleService;
import com.train.trainmicroservice.model.Train;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.sql.Date;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@WebMvcTest(ScheduleController.class)
class ScheduleControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private ScheduleService scheduleService;
    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void addSchedule() throws Exception {
        ScheduleRequest scheduleRequest = ScheduleRequest.builder()
                .departureDateTime(Date.valueOf("2023-01-01"))
                .arrivalDateTime(Date.valueOf("2023-01-01"))
                .trainNumber(1)
                .routeId(1)
                .build();

        Schedule schedule = Schedule.builder()
                .scheduleId(1)
                .departureDateTime(Date.valueOf("2023-01-01"))
                .arrivalDateTime(Date.valueOf("2023-01-01"))
                .train(new Train())
                .route(new Route())
                .build();

        when(scheduleService.addSchedule(scheduleRequest)).thenReturn(schedule);

        mockMvc.perform(MockMvcRequestBuilders.post("/schedule")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(scheduleRequest)))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.scheduleId").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$.trainNumber").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$.routeId").value(1));
    }

    @Test
    void getAllSchedules() throws Exception {
        Schedule schedule1 = Schedule.builder().scheduleId(1).build();
        Schedule schedule2 = Schedule.builder().scheduleId(2).build();

        List<Schedule> schedules = Arrays.asList(schedule1, schedule2);

        when(scheduleService.getAllSchedules()).thenReturn(schedules);

        mockMvc.perform(MockMvcRequestBuilders.get("/schedule")
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].scheduleId").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].scheduleId").value(2));
    }

    @Test
    void getScheduleById() throws Exception {
        int scheduleId = 1;
        Schedule schedule = Schedule.builder().scheduleId(scheduleId).build();

        when(scheduleService.getScheduleById(scheduleId)).thenReturn(schedule);

        mockMvc.perform(MockMvcRequestBuilders.get("/schedule/{scheduleId}", scheduleId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.scheduleId").value(scheduleId));
    }

    @Test
    void updateSchedule() throws Exception {
        int scheduleId = 1;
        ScheduleRequest scheduleRequest = ScheduleRequest.builder().trainNumber(1).routeId(1).build();

        when(scheduleService.updateSchedule(eq(scheduleId), any(ScheduleRequest.class))).thenReturn(updatedSchedule);

        mockMvc.perform(MockMvcRequestBuilders.put("/schedule/{scheduleId}", scheduleId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(scheduleRequest)))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.scheduleId").value(scheduleId))
                .andExpect(MockMvcResultMatchers.jsonPath("$.trainNumber").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$.routeId").value(1));
    }

    @Test
    void deleteSchedule() throws Exception {
        int scheduleId = 1;

        mockMvc.perform(MockMvcRequestBuilders.delete("/schedule/{scheduleId}", scheduleId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isNoContent());

        verify(scheduleService, times(1)).deleteSchedule(scheduleId);
    }
}
