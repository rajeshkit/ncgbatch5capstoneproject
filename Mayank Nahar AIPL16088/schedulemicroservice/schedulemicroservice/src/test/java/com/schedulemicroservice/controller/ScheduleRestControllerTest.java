package com.schedulemicroservice.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.schedulemicroservice.exception.ScheduleIdNotExistException;
import com.schedulemicroservice.model.Route;
import com.schedulemicroservice.model.Schedule;
import com.schedulemicroservice.model.ScheduleRequest;
import com.schedulemicroservice.model.Train;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import java.util.Arrays;
import java.util.Optional;

import static java.lang.reflect.Array.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ScheduleRestController.class)
class ScheduleRestControllerTest {
    @MockBean
    private ScheduleRestController scheduleRestController;
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;


    @Test
    void addScheduleDetails() throws Exception {

        ScheduleRequest scheduleRequest = ScheduleRequest.builder()
                .arrivalDateTime("12-12-2023 10:10:00")
                .departureDateTime("12-12-2023 10:12:00")
                .routeId(1)
                .trainId(1)
                .build();
        Schedule schedule = Schedule.builder()
                .scheduleId(1)
                .arrivalDateTime("12-12-2023 10:10:00")
                .departureDateTime("12-12-2023 10:12:00")
                .train(new Train())
                .route(new Route()).build();

        Mockito.when(scheduleRestController.addScheduleDetails(scheduleRequest)).thenReturn(schedule);
        mockMvc.perform(post("/schedule-api/schedule")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writerWithDefaultPrettyPrinter()
                                .writeValueAsString(scheduleRequest)))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.scheduleId").value(1));
    }


    @Test
    void getAllScheduleRequest() throws Exception {
        ScheduleRequest scheduleRequest1 = ScheduleRequest.builder()
                .scheduleId(1)
                .trainId(12991)
                .arrivalDateTime("12-12-2023 10:10:00")
                .departureDateTime("12-12-2023 10:12:00")
                .routeId(1).build();

        ScheduleRequest scheduleRequest2 = ScheduleRequest.builder()
                .scheduleId(2)
                .trainId(12992)
                .arrivalDateTime("12-12-2023 10:10:00")
                .departureDateTime("12-12-2023 10:12:00")
                .routeId(123).build();

        Mockito.when(scheduleRestController.getAllScheduleRequest()).thenReturn(Arrays.asList(scheduleRequest1, scheduleRequest2));
        mockMvc.perform(get("/schedule-api/schedule"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2));
    }


    @Test
    void getScheduleRequestById() throws Exception {
        ScheduleRequest scheduleRequest1 = ScheduleRequest.builder()
                .scheduleId(2)
                .trainId(12992)
                .arrivalDateTime("12-12-2023 10:10:00")
                .departureDateTime("12-12-2023 10:12:00")
                .routeId(123).build();
            Mockito.when(scheduleRestController.getScheduleRequestById(2)).thenReturn(scheduleRequest1);
            mockMvc.perform(get("/schedule-api/schedule/1")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(objectMapper.writerWithDefaultPrettyPrinter()
                                    .writeValueAsString(scheduleRequest1)))
                    .andDo(MockMvcResultHandlers.print())
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.scheduleId").value(2));
        }

    }


