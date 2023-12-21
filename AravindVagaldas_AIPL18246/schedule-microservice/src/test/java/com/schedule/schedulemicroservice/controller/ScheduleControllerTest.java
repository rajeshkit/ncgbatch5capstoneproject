package com.schedule.schedulemicroservice.controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.route.routemicroservice.model.Route;
import com.schedule.schedulemicroservice.model.Schedule;
import com.schedule.schedulemicroservice.model.ScheduleRequest;
import com.schedule.schedulemicroservice.service.ScheduleService;
import com.train.trainmicroservice.model.Train;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;


import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Optional;


import static org.hamcrest.core.StringContains.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@WebMvcTest(ScheduleController.class)
class ScheduleControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private ScheduleService scheduleService;


    @Autowired
    private ObjectMapper objectMapper;
    @InjectMocks
    private ScheduleController scheduleController;

    String dt = "2024-12-25T15:30:00";
    String at = "2024-12-27T08:45:00";

    DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME;
    LocalDateTime departureDateTime = LocalDateTime.parse(dt, formatter);
    LocalDateTime arrivalDateTime = LocalDateTime.parse(at, formatter);
    ScheduleRequest scheduleRequest = ScheduleRequest.builder().departureDateTime(departureDateTime).arrivalDateTime(arrivalDateTime).trainNumber(1001).routeId(901).build();

    Train mockTrain = Train.builder().trainNumber(1001).trainName("Mumbai Express").acCoaches(8).acCoachTotalSeats(280).sleeperCoaches(15).sleeperCoachTotalSeats(300).generalCoaches(40).generalCoachesTotalSeats(800).totalKms(1500).build();
    Route mockRoute = Route.builder().routeId(901).source("chennai").destination("hyderabad").totalKms(800).build();

    Schedule schedule = Schedule.builder().scheduleId(101).route(mockRoute).train(mockTrain).departureDateTime(departureDateTime).arrivalDateTime(arrivalDateTime).build();


    @Test
    void addSchedule() throws Exception {
        Mockito.when(scheduleService.addSchedule(scheduleRequest)).thenReturn(schedule);
        mockMvc.perform(post("/schedule-api/schedule")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writerWithDefaultPrettyPrinter()
                                .writeValueAsString(scheduleRequest)))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.scheduleId").value(101));

    }

    @Test
    void getAllSchedules() throws Exception {

        Schedule schedule2 = Schedule.builder()
                .route(mockRoute)
                .train(mockTrain)
                .departureDateTime(departureDateTime).
                arrivalDateTime(arrivalDateTime).build();

        Mockito.when(scheduleService.getAllSchedules()).thenReturn(Arrays.asList(schedule, schedule2));
        mockMvc.perform(get("/schedule-api/schedule"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2));

    }

    @Test
    void getScheduleById() throws Exception {

        Mockito.when(scheduleService.getScheduleById(101)).thenReturn(schedule);
        mockMvc.perform(get("/schedule-api/schedule/{scheduleId}", 101))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.scheduleId").value(101));


    }

    @Test
    void updateSchedule() throws Exception {

        Mockito.when(scheduleService.updateSchedule(schedule)).thenReturn(schedule);
        mockMvc.perform(put("/schedule-api/schedule")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writerWithDefaultPrettyPrinter()
                                .writeValueAsString(schedule)))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.scheduleId").value(101));

    }

    @Test
    void deleteScheduleById() throws Exception {

        Mockito.when(scheduleService.deleteScheduleByScheduleId(101)).thenReturn("Schedule Deleted Successfully");
        mockMvc.perform(delete("/schedule-api/schedule/{scheduleId}", 101))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Schedule Deleted Successfully")));


    }

    @Test
    void getScheduleByTrainNo() throws Exception {

        Schedule schedule1 = Schedule.builder()
                .scheduleId(102)
                .route(mockRoute)
                .train(mockTrain)
                .departureDateTime(departureDateTime).
                arrivalDateTime(arrivalDateTime).build();

        Mockito.when(scheduleService.getScheduleByTrainNo(1001)).thenReturn(Arrays.asList(schedule, schedule1));
        mockMvc.perform(get("/schedule-api/schedule/train/{trainNumber}", 1001))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2));
    }
}