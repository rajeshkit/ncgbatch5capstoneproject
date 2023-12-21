package com.altimetrik.schedule.controller;

import com.altimetrik.route.model.Route;
import com.altimetrik.schedule.model.Schedule;
import com.altimetrik.schedule.model.ScheduleRequest;
import com.altimetrik.schedule.service.ScheduleService;
import com.altimetrik.train.model.Train;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.time.LocalDateTime;
import java.util.Arrays;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ScheduleController.class)
class ScheduleControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private ScheduleService scheduleService;
    @Autowired
    private ObjectMapper objectMapper;

    LocalDateTime departure=LocalDateTime.of(2023, 12, 25, 12, 35, 45);
    LocalDateTime arrival=LocalDateTime.of(2023, 12, 25, 12, 30, 45);
    ScheduleRequest scheduleRequest1=ScheduleRequest.builder().arrivalDateTime(arrival).departureDateTime(departure).trainNumber(1234).routeID(1).build();
    ScheduleRequest scheduleRequest2=ScheduleRequest.builder().arrivalDateTime(arrival).departureDateTime(departure).trainNumber(5678).routeID(2).build();
    Train train=Train.builder().trainNo(1234).trainName("JKLM").totalKms(700).acCoaches(10).acTotalSeats(720).sleeperCoaches(10).sleeperTotalSeats(720).generalCoaches(2).generalTotalSeats(144).build();
    Route route=Route.builder().routeId(1).trainNo(1234).source("SK").destination("HYD").totalKm(700).build();
    Schedule savedSchedule1=Schedule.builder().scheduleId(1000).arrivalDateTime(scheduleRequest1.getArrivalDateTime()).departureDateTime(scheduleRequest1.getDepartureDateTime()).train(train).route(route).build();
    Schedule savedSchedule2=Schedule.builder().scheduleId(1001).arrivalDateTime(scheduleRequest2.getArrivalDateTime()).departureDateTime(scheduleRequest2.getDepartureDateTime()).train(train).route(route).build();


    @Test
    void addSchedule() throws Exception {
       Mockito.when(scheduleService.addSchedule(scheduleRequest1)).thenReturn(savedSchedule1);
        mockMvc.perform(post("/schedule-api/schedule")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writerWithDefaultPrettyPrinter()
                                .writeValueAsString(scheduleRequest1)))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.scheduleId").value(1000));
    }

    @Test
    void viewAllSchedules() throws Exception {
       Mockito.when(scheduleService.viewAllSchedules()).thenReturn(Arrays.asList(savedSchedule1,savedSchedule2));
        mockMvc.perform(get("/schedule-api/schedule"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2));

    }

    @Test
    void getScheduleById() throws Exception {
        Mockito.when(scheduleService.getScheduleById(1000)).thenReturn(savedSchedule1);
        mockMvc.perform(get("/schedule-api/schedule/{id}",1000))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.scheduleId").value(1000));

    }

    @Test
    void updateSchedule() throws Exception {
        Mockito.when(scheduleService.getScheduleById(1000)).thenReturn(savedSchedule1);
        Mockito.when(scheduleService.updateSchedule(savedSchedule1)).thenReturn(savedSchedule1);
        mockMvc.perform(put("/schedule-api/schedule")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writerWithDefaultPrettyPrinter()
                                .writeValueAsString(savedSchedule1)))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.scheduleId").value(1000));

    }

    @Test
    void deleteScheduleById() throws Exception {
        Mockito.when(scheduleService.getScheduleById(1000)).thenReturn(savedSchedule1);
        Mockito.when(scheduleService.deleteScheduleById(1000)).thenReturn("Schedule Deleted Successfully");
        mockMvc.perform(delete("/schedule-api/schedule/{id}",1000))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("Schedule Deleted Successfully"));
    }

    @Test
    void getScheduleByTrainNo() throws Exception {
        Mockito.when(scheduleService.getScheduleByTrainNo(1234)).thenReturn(Arrays.asList(savedSchedule1,savedSchedule2));
        mockMvc.perform(get("/schedule-api/train/{trainNumber}",1234))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2));
    }
}