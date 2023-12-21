package com.altimetrik.schedulemicroservice.controller;

import com.altimetrik.schedulemicroservice.exception.ScheduleIdNotExistException;
import com.altimetrik.schedulemicroservice.model.Route;
import com.altimetrik.schedulemicroservice.model.Schedule;
import com.altimetrik.schedulemicroservice.model.ScheduleRequest;
import com.altimetrik.schedulemicroservice.model.Train;
import com.altimetrik.schedulemicroservice.service.ScheduleService;
import com.fasterxml.jackson.core.JsonProcessingException;
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

import java.util.Arrays;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ScheduleController.class)
class ScheduleControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    ScheduleService scheduleService;

    @Autowired
    ObjectMapper objectMapper;

    @Test
    void addScheduleRequest() throws Exception {
        ScheduleRequest scheduleRequest = ScheduleRequest.builder()
                .scheduleId(0).trainId(19208).arrivalDateTime(new Date())
                .departureDateTime(new Date()).routeId(1).build();

        Schedule schedule = Schedule.builder()
                .scheduleId(0).arrivalDateTime(new Date())
                .departureDateTime(new Date())
                .train(new Train())
                .route(new Route()).build();

        Mockito.when(scheduleService.addScheduleRequest(scheduleRequest)).thenReturn(schedule);
        mockMvc.perform(post("/schedule-api/schedule")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(scheduleRequest)))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.scheduleId").value(0));
    }

    @Test
    void getAllScheduleDetails() throws Exception {
        ScheduleRequest scheduleRequest1 = ScheduleRequest.builder()
                .scheduleId(0).trainId(19208).arrivalDateTime(new Date())
                .departureDateTime(new Date()).routeId(1).build();

        ScheduleRequest scheduleRequest2 = ScheduleRequest.builder()
                .scheduleId(1).trainId(191919).arrivalDateTime(new Date())
                .departureDateTime(new Date()).routeId(2).build();

        Mockito.when(scheduleService.getAllScheduleDetails()).thenReturn(Arrays.asList(scheduleRequest1, scheduleRequest2));
        mockMvc.perform(get("/schedule-api/schedule"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2));
    }

    @Test
    void getScheduleById() throws Exception {
        ScheduleRequest scheduleRequest = ScheduleRequest.builder()
                .scheduleId(0).trainId(19208).arrivalDateTime(new Date())
                .departureDateTime(new Date()).routeId(0).build();

        Mockito.when(scheduleService.getScheduleById(0)).thenReturn(scheduleRequest);
        mockMvc.perform(get("/schedule-api/schedule/0")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(scheduleRequest)))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.scheduleId").value(0));
    }

    @Test
    void updateScheduleDetails() throws Exception {
        ScheduleRequest scheduleRequest = ScheduleRequest.builder()
                .scheduleId(0).trainId(19208).arrivalDateTime(new Date())
                .departureDateTime(new Date()).routeId(1).build();

        Mockito.when(scheduleService.updateScheduleDetails(scheduleRequest)).thenReturn(scheduleRequest);
        mockMvc.perform(put("/schedule-api/schedule")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(scheduleRequest)))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.scheduleId").value(0));
    }

    @Test
    void deleteScheduleById() throws  Exception
    {
        Mockito.when(scheduleService.deleteScheduleById(1)).thenReturn("Schedule Details Deleted Successfully !!!");
        mockMvc.perform(MockMvcRequestBuilders.delete("/schedule-api/schedule/{scheduleId}", 1))
                .andExpect(status().isOk());
    }
}
