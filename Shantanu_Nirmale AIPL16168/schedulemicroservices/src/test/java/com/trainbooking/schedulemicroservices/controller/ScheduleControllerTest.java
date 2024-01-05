package com.trainbooking.schedulemicroservices.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.trainbooking.schedulemicroservices.exception.ScheduleIdNotExistException;
import com.trainbooking.schedulemicroservices.model.Route;
import com.trainbooking.schedulemicroservices.model.Schedule;
import com.trainbooking.schedulemicroservices.model.ScheduleRequest;
import com.trainbooking.schedulemicroservices.model.Train;
import com.trainbooking.schedulemicroservices.service.ScheduleService;
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
    void addNewScheduleRequest() throws Exception {
        ScheduleRequest scheduleRequest = ScheduleRequest.builder()
                .scheduleId(999).trainNumber(171717).arrivalDateTime("2023-12-20T18:20:59.027+00:00")
                .departureDateTime("2023-12-20T18:30:59.027+00:00").routeId(111).build();

        Schedule schedule = Schedule.builder()
                .scheduleId(999).arrivalDateTime("2023-12-20T18:20:59.027+00:00")
                .departureDateTime("2023-12-20T18:30:59.027+00:00")
                .train(new Train())
                .route(new Route()).build();

        Mockito.when(scheduleService.addNewScheduleRequest(scheduleRequest)).thenReturn(schedule);
        mockMvc.perform(post("/schedule-api/schedule")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(scheduleRequest)))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.scheduleId").value(999));
    }

    @Test
    void getAllScheduleDetails() throws Exception {
        ScheduleRequest scheduleRequest1 = ScheduleRequest.builder()
                .scheduleId(999).trainNumber(171717).arrivalDateTime("2023-12-20T18:20:59.027+00:00")
                .departureDateTime("2023-12-20T18:30:59.027+00:00").routeId(111).build();

        ScheduleRequest scheduleRequest2 = ScheduleRequest.builder()
                .scheduleId(888).trainNumber(191919).arrivalDateTime("2023-12-21T18:20:59.027+00:00")
                .departureDateTime("2023-12-21T18:30:59.027+00:00").routeId(222).build();

        Mockito.when(scheduleService.getAllScheduleDetails()).thenReturn(Arrays.asList(scheduleRequest1, scheduleRequest2));
        mockMvc.perform(get("/schedule-api/schedule"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2));
    }

    @Test
    void getScheduleByScheduleId() throws Exception {
        ScheduleRequest scheduleRequest = ScheduleRequest.builder()
                .scheduleId(999).trainNumber(171717).arrivalDateTime("2023-12-20T18:20:59.027+00:00")
                .departureDateTime("2023-12-20T18:30:59.027+00:00").routeId(111).build();

        Mockito.when(scheduleService.getScheduleByScheduleId(999)).thenReturn(scheduleRequest);
        mockMvc.perform(get("/schedule-api/schedule/999")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(scheduleRequest)))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.scheduleId").value(999));
    }

    @Test
    void updateScheduleDetails() throws Exception {
        ScheduleRequest scheduleRequest = ScheduleRequest.builder()
                .scheduleId(999).trainNumber(171717).arrivalDateTime("2023-12-20T18:20:59.027+00:00")
                .departureDateTime("2023-12-20T18:30:59.027+00:00").routeId(111).build();

        Mockito.when(scheduleService.updateScheduleDetails(scheduleRequest)).thenReturn(scheduleRequest);
        mockMvc.perform(put("/schedule-api/schedule")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(scheduleRequest)))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.scheduleId").value(999));


    }

    @Test
    void deleteScheduleByScheduleId() throws Exception {
        Mockito.when(scheduleService.deleteScheduleByScheduleId(123)).thenReturn("Schedule Details Deleted Successfully !!!");
        mockMvc.perform(MockMvcRequestBuilders.delete("/schedule-api/schedule/{scheduleId}", 123))
                .andExpect(status().isOk());
    }
}