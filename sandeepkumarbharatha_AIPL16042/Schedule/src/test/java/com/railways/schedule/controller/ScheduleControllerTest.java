package com.railways.schedule.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.railways.route.model.Route;
import com.railways.schedule.exceptions.ScheduleIdNotFoundException;
import com.railways.schedule.model.Schedule;
import com.railways.schedule.model.ScheduleDemo;
import com.railways.schedule.services.ScheduleServices;
import com.railways.train.model.Train;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import java.sql.Timestamp;
import java.util.Arrays;

import static org.hamcrest.core.StringContains.containsString;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ScheduleController.class)
class ScheduleControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    @MockBean
    private ScheduleServices scheduleServices;
    @InjectMocks
    private ScheduleController scheduleController;

    @Test
    void insertScheduleDetails() throws Exception {
        Train train1 = Train.builder().trainNumber(1234).trainName("satapddhi express")
                .totalKms(450).acCoaches(2).acCoachTotalSeats(12)
                .sleeperCoaches(5).sleeperCoachTotalSeats(10)
                .generalCoaches(9).generalCoacheTotalSeats(300).build();
        Route route1 = Route.builder()
                .routeId(2345).source("hyd").destination("tpty").totalKms(700).build();
        Schedule schedule1 = Schedule.builder().scheduleId(12434L)
                .departureDateTime(Timestamp.valueOf("2024-11-11 08:11:12"))
                .arrivalDateTime(Timestamp.valueOf("2024-11-12 04:54:08"))
                .train(train1).route(route1).build();
        ScheduleDemo scheduleDemo = ScheduleDemo.builder().scheduleId1(12434L)
                .arrivalDateTime(Timestamp.valueOf("2024-11-11 08:11:12"))
                .arrivalDateTime(Timestamp.valueOf("2023-11-12 04:54:08"))
                .routeId(2345L).trainNumber(1234L).build();
        Mockito.when(scheduleServices.insertScheduleDetails(scheduleDemo)).thenReturn(schedule1);
        mockMvc.perform(post("/schedule/scheduledetails")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writerWithDefaultPrettyPrinter()
                                .writeValueAsString(schedule1)))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk());
    }

    @Test
    void getAllScheduleDetails() throws Exception {
        Train train1 = Train.builder().trainNumber(1234).trainName("satapddhi express")
                .totalKms(450).acCoaches(2).acCoachTotalSeats(12)
                .sleeperCoaches(5).sleeperCoachTotalSeats(10)
                .generalCoaches(9).generalCoacheTotalSeats(300).build();
        Route route1 = Route.builder()
                .routeId(1234).source("hyd").destination("tpty").totalKms(700).build();
        Schedule schedule1 = Schedule.builder().scheduleId(1234L)
                .departureDateTime(Timestamp.valueOf("2024-11-11 08:11:12"))
                .arrivalDateTime(Timestamp.valueOf("2024-11-12 04:54:08"))
                .train(train1).route(route1).build();

        Train train = Train.builder().trainNumber(1234).trainName("satapddhi express")
                .totalKms(450).acCoaches(2).acCoachTotalSeats(12)
                .sleeperCoaches(5).sleeperCoachTotalSeats(10)
                .generalCoaches(9).generalCoacheTotalSeats(300).build();
        Route route = Route.builder()
                .routeId(1234).source("hyd").destination("tpty").totalKms(700).build();
        Schedule schedule = Schedule.builder().scheduleId(1234L)
                .departureDateTime(Timestamp.valueOf("2024-11-11 08:11:12"))
                .arrivalDateTime(Timestamp.valueOf("2023-11-12 04:54:08"))
                .train(train).route(route).build();
        Mockito.when(scheduleServices.getAllScheduleDetails()).thenReturn(Arrays.asList(schedule, schedule1));
        assertEquals(2, scheduleServices.getAllScheduleDetails().size());
        mockMvc.perform(get("/schedule/scheduledetails")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writerWithDefaultPrettyPrinter()
                                .writeValueAsString(schedule)))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2));
    }

    @Test
    void getScheduleById() throws Exception {
        Train train = Train.builder().trainNumber(1234).trainName("satapddhi express")
                .totalKms(450).acCoaches(2).acCoachTotalSeats(12)
                .sleeperCoaches(5).sleeperCoachTotalSeats(10)
                .generalCoaches(9).generalCoacheTotalSeats(300).build();
        Route route = Route.builder()
                .routeId(1234).source("hyd").destination("tpty").totalKms(700).build();
        Schedule schedule = Schedule.builder().scheduleId(1234L)
                .departureDateTime(Timestamp.valueOf("2024-11-11 08:11:12"))
                .arrivalDateTime(Timestamp.valueOf("2023-11-12 04:54:08"))
                .train(train).route(route).build();
        Mockito.when(scheduleServices.getScheduleById(schedule.getScheduleId()))
                .thenReturn(schedule);
        mockMvc.perform(get("/schedule/scheduledetails/{id}", schedule.getScheduleId()))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.scheduleId").value(schedule.getScheduleId()));
    }

    @Test
    void getScheduleByIdWithException() throws Exception {
        Train train = Train.builder().trainNumber(1234).trainName("shatapdhi express")
                .totalKms(450).acCoaches(2).acCoachTotalSeats(12)
                .sleeperCoaches(5).sleeperCoachTotalSeats(10)
                .generalCoaches(9).generalCoacheTotalSeats(300).build();
        Route route = Route.builder()
                .routeId(1234).source("hyd").destination("tpty").totalKms(700).build();
        Schedule schedule = Schedule.builder().scheduleId(1234L)
                .departureDateTime(Timestamp.valueOf("2024-11-11 08:11:12"))
                .arrivalDateTime(Timestamp.valueOf("2023-11-12 04:54:08"))
                .train(train).route(route).build();
        Mockito.when(scheduleServices.getScheduleById(schedule.getScheduleId())).thenThrow(new ScheduleIdNotFoundException("schedule id not found"));
        mockMvc.perform(get("/schedule/scheduledetails/{id}", schedule.getScheduleId()))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.message").value("schedule id not found"));
    }

    @Test
    void updateScheduleById() throws Exception {
        Train train = Train.builder().trainNumber(1234L).trainName("satapddhi express")
                .totalKms(450).acCoaches(2).acCoachTotalSeats(12)
                .sleeperCoaches(5).sleeperCoachTotalSeats(10)
                .generalCoaches(9).generalCoacheTotalSeats(300).build();
        Route route = Route.builder()
                .routeId(1234L).source("hyd").destination("tpty").totalKms(700).build();
        Schedule schedule = Schedule.builder().scheduleId(1234L)
                .departureDateTime(Timestamp.valueOf("2024-11-11 08:11:12"))
                .arrivalDateTime(Timestamp.valueOf("2025-11-12 04:54:08"))
                .train(train).route(route).build();
        Mockito.when(scheduleServices.updateScheduleById(schedule)).thenReturn(schedule);
        mockMvc.perform(put("/schedule/scheduledetails")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writerWithDefaultPrettyPrinter()
                                .writeValueAsString(schedule)))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.scheduleId").value(schedule.getScheduleId()));


    }

    @Test
    void deleteScheduleById() throws Exception {
        Train train = Train.builder().trainNumber(1234).trainName("satapddhi express")
                .totalKms(450).acCoaches(2).acCoachTotalSeats(12)
                .sleeperCoaches(5).sleeperCoachTotalSeats(10)
                .generalCoaches(9).generalCoacheTotalSeats(300).build();
        Route route = Route.builder()
                .routeId(1234).source("hyd").destination("tpty").totalKms(700).build();
        Schedule schedule = Schedule.builder().scheduleId(1234L)
                .departureDateTime(Timestamp.valueOf("2024-11-11 08:11:12"))
                .arrivalDateTime(Timestamp.valueOf("2023-11-12 04:54:08"))
                .train(train).route(route).build();
        Mockito.when(scheduleServices.deleteScheduleById(schedule.getScheduleId())).thenReturn("schedule details deleted successfully");
        mockMvc.perform(delete("/schedule/scheduledetails/{id}", schedule.getScheduleId())
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("schedule details deleted successfully")));
    }

    @Test
    void deleteScheduleByIdWithException() throws Exception {
        Train train = Train.builder().trainNumber(1234).trainName("satapddhi express")
                .totalKms(450).acCoaches(2).acCoachTotalSeats(12)
                .sleeperCoaches(5).sleeperCoachTotalSeats(10)
                .generalCoaches(9).generalCoacheTotalSeats(300).build();
        Route route = Route.builder()
                .routeId(1234).source("hyd").destination("tpty").totalKms(700).build();
        Schedule schedule = Schedule.builder().scheduleId(1234L)
                .departureDateTime(Timestamp.valueOf("2024-11-11 08:11:12"))
                .arrivalDateTime(Timestamp.valueOf("2023-11-12 04:54:08"))
                .train(train).route(route).build();
        Mockito.when(scheduleServices.deleteScheduleById(schedule.getScheduleId())).thenThrow(new ScheduleIdNotFoundException("schedule id not found"));
        mockMvc.perform(delete("/schedule/scheduledetails/{id}", train.getTrainNumber()))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.message").value("schedule id not found"));

    }


}

