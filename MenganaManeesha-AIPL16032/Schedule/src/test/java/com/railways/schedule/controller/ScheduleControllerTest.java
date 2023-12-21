package com.railways.schedule.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.railways.route.model.Route;
import com.railways.schedule.model.NewScheduleResource;
import com.railways.schedule.model.Schedule;
import com.railways.schedule.service.ScheduleService;
import com.railways.train.model.Train;
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
    LocalDateTime arrivaldateTime = LocalDateTime.of(2023, 12, 25, 12, 30, 45);
    LocalDateTime departuredateTime = LocalDateTime.of(2023, 12, 25, 12, 35, 45);
    NewScheduleResource resource=NewScheduleResource.builder().arrivalDateTime(arrivaldateTime).departureDateTime(departuredateTime).trainNumber(123).routeId(1).build();
    Train t= Train.builder().trainNumber(123).trainName("ABC").totalKms(100).acCoaches(10).acCoachTotalSeats(100).sleeperCoaches(5).sleeperCoachTotalSeats(50).generalCoaches(8).generalCoachTotalSeats(80).build();
    Route r=Route.builder().routeId(1).source("HYD").destination("BNG").totalKms(500).build();
    Schedule savedSchedule=Schedule.builder().scheduleId(1000L).arrivalDateTime(resource.getArrivalDateTime()).departureDateTime(resource.getDepartureDateTime()).train(t).route(r).build();


    @Test
    void addSchedule() throws Exception {
        Mockito.when(scheduleService.addSchedule(resource)).thenReturn(savedSchedule);
        mockMvc.perform(post("/schedule-api/schedule")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writerWithDefaultPrettyPrinter()
                                .writeValueAsString(resource)))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.scheduleId").value(1000L));
    }
    @Test
    void getSchedule() throws Exception {
        NewScheduleResource resource2=NewScheduleResource.builder().arrivalDateTime(arrivaldateTime).departureDateTime(departuredateTime).trainNumber(456).routeId(2).build();
        Schedule savedSchedule2=Schedule.builder().scheduleId(1001L).arrivalDateTime(resource2.getArrivalDateTime()).departureDateTime(resource2.getDepartureDateTime()).train(t).route(r).build();
        Mockito.when(scheduleService.getSchedule()).thenReturn(Arrays.asList(savedSchedule,savedSchedule2));
        mockMvc.perform(get("/schedule-api/schedule"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2));
    }
    @Test
    void getScheduleById() throws Exception {
        Mockito.when(scheduleService.getScheduleById(1000L)).thenReturn(savedSchedule);
        mockMvc.perform(get("/schedule-api/schedule/{id}",1000L))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.scheduleId").value(1000L));
    }
    @Test
    void updateSchedule() throws Exception {
        Mockito.when(scheduleService.getScheduleById(1000L)).thenReturn(savedSchedule);
        Mockito.when(scheduleService.updateSchedule(savedSchedule)).thenReturn(savedSchedule);
        mockMvc.perform(put("/schedule-api/schedule")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writerWithDefaultPrettyPrinter()
                                .writeValueAsString(savedSchedule)))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.scheduleId").value(1000L));
    }

    @Test
    void deleteSchedule() throws Exception {
        Mockito.when(scheduleService.getScheduleById(1000L)).thenReturn(savedSchedule);
        Mockito.when(scheduleService.deleteschedule(1000L)).thenReturn("Schedule Deleted Successfully");
        mockMvc.perform(delete("/schedule-api/schedule/{id}",1000L))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("Schedule Deleted Successfully"));
    }

    @Test
    void getSchedulesByTrainNumber() throws Exception {
        Schedule savedSchedule2=Schedule.builder().scheduleId(1001L).arrivalDateTime(resource.getArrivalDateTime()).departureDateTime(resource.getDepartureDateTime()).train(t).route(r).build();
        Mockito.when(scheduleService.getSchedulesByTrainNumber(123L)).thenReturn(Arrays.asList(savedSchedule,savedSchedule2));
        mockMvc.perform(get("/schedule-api/train/{trainNumber}",123L))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2));
    }
}