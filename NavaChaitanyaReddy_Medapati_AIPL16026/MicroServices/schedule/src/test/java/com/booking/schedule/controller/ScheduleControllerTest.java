package com.booking.schedule.controller;

import com.booking.route.model.RouteResources;
import com.booking.schedule.model.NewScheduleRequest;
import com.booking.schedule.model.Schedule;
import com.booking.schedule.service.ScheduleService;
import com.booking.train.model.TrainResources;
import com.fasterxml.jackson.databind.ObjectMapper;
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
import java.time.LocalDateTime;
import java.util.Arrays;

import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.hasSize;
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

    @Test
    void addScheduleResources() throws Exception {
        LocalDateTime departureTime= LocalDateTime.parse("2023-12-23T09:14:30");
        LocalDateTime arrivalTime= LocalDateTime.parse("2023-12-24T04:32:30");

        TrainResources trainResources=TrainResources.builder()
                .trainNumber(17644l).trainName("Circar").totalKms(780.0)
                .acCoaches(3).acCoachTotalSeats(168).generalCoaches(1).generalCoachesTotalSeats(60)
                .sleeperCoaches(10).sleeperCoachTotalSeats(600).build();

        RouteResources routeResources= RouteResources.builder()
                .routeId(1234l)
                .source("Kakinada").destination("Hyderabad")
                .totalKms(780.0).build();

        NewScheduleRequest newScheduleRequest= NewScheduleRequest.builder()
                .trainNumber(trainResources.getTrainNumber()).routeId(routeResources.getRouteId())
                .departureDateTime(Timestamp.valueOf(departureTime)).arrivalDateTime(Timestamp.valueOf(arrivalTime)).build();

        Schedule schedule= Schedule.builder().scheduleId(100l)
                .departureDateTime(Timestamp.valueOf(departureTime)).arrivalDateTime(Timestamp.valueOf(arrivalTime))
                .train(trainResources).route(routeResources).build();

        Mockito.when(scheduleService.addScheduleResources(newScheduleRequest)).thenReturn(schedule);

        mockMvc.perform(post("/schedule-api/schedule")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writerWithDefaultPrettyPrinter()
                                .writeValueAsString(newScheduleRequest)))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.scheduleId").value(100l));

    }

    @Test
    void getAllScheduleResources() throws Exception {
        LocalDateTime departureTime= LocalDateTime.parse("2023-12-21T09:14:30");
        LocalDateTime arrivalTime= LocalDateTime.parse("2023-12-22T04:32:30");

        TrainResources trainResources=TrainResources.builder()
                .trainNumber(17644l).trainName("Circar").totalKms(780.0)
                .acCoaches(3).acCoachTotalSeats(168).generalCoaches(1).generalCoachesTotalSeats(60)
                .sleeperCoaches(10).sleeperCoachTotalSeats(600).build();

        RouteResources routeResources= RouteResources.builder()
                .routeId(1234l)
                .source("Kakinada").destination("Hyderabad")
                .totalKms(780.0).build();

        Schedule schedule= Schedule.builder().scheduleId(100l)
                .departureDateTime(Timestamp.valueOf(departureTime)).arrivalDateTime(Timestamp.valueOf(arrivalTime))
                .train(trainResources).route(routeResources).build();

        Schedule schedule1= Schedule.builder().scheduleId(101l)
                .departureDateTime(Timestamp.valueOf(departureTime)).arrivalDateTime(Timestamp.valueOf(arrivalTime))
                .train(trainResources).route(routeResources).build();

        Mockito.when(scheduleService.getAllScheduleResources()).thenReturn(Arrays.asList(schedule,schedule1));
        mockMvc.perform(get("/schedule-api/schedule"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2));

    }

    @Test
    void getScheduleResourcesById() throws Exception {
        LocalDateTime departureTime= LocalDateTime.parse("2023-12-21T09:14:30");
        LocalDateTime arrivalTime= LocalDateTime.parse("2023-12-22T04:32:30");

        TrainResources trainResources=TrainResources.builder()
                .trainNumber(17644l).trainName("Circar").totalKms(780.0)
                .acCoaches(3).acCoachTotalSeats(168).generalCoaches(1).generalCoachesTotalSeats(60)
                .sleeperCoaches(10).sleeperCoachTotalSeats(600).build();

        RouteResources routeResources= RouteResources.builder()
                .routeId(1234l)
                .source("Kakinada").destination("Hyderabad")
                .totalKms(780.0).build();

        Schedule schedule= Schedule.builder().scheduleId(100l)
                .departureDateTime(Timestamp.valueOf(departureTime)).arrivalDateTime(Timestamp.valueOf(arrivalTime))
                .train(trainResources).route(routeResources).build();

        Mockito.when(scheduleService.getScheduleResourcesById(100l)).thenReturn(schedule);
        mockMvc.perform(get("/schedule-api/schedule/{scheduleId}",schedule.getScheduleId()))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.scheduleId").value(100l));

    }

    @Test
    void updateSchedule() throws Exception {
        LocalDateTime departureTime= LocalDateTime.parse("2023-12-21T09:14:30");
        LocalDateTime arrivalTime= LocalDateTime.parse("2023-12-22T04:32:30");

        TrainResources trainResources=TrainResources.builder()
                .trainNumber(17644l).trainName("Circar").totalKms(780.0)
                .acCoaches(3).acCoachTotalSeats(168).generalCoaches(1).generalCoachesTotalSeats(60)
                .sleeperCoaches(10).sleeperCoachTotalSeats(600).build();

        RouteResources routeResources= RouteResources.builder()
                .routeId(1234l)
                .source("Kakinada").destination("Hyderabad")
                .totalKms(780.0).build();

        Schedule schedule= Schedule.builder().scheduleId(100l)
                .departureDateTime(Timestamp.valueOf(departureTime)).arrivalDateTime(Timestamp.valueOf(arrivalTime))
                .train(trainResources).route(routeResources).build();

        Mockito.when(scheduleService.updateSchedule(schedule)).thenReturn(schedule);
        mockMvc.perform(put("/schedule-api/schedule")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writerWithDefaultPrettyPrinter()
                                .writeValueAsString(schedule)))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.scheduleId").value(100l));


    }

    @Test
    void deleteScheduleById() throws Exception {
        LocalDateTime departureTime= LocalDateTime.parse("2023-12-21T09:14:30");
        LocalDateTime arrivalTime= LocalDateTime.parse("2023-12-22T04:32:30");

        TrainResources trainResources=TrainResources.builder()
                .trainNumber(17644l).trainName("Circar").totalKms(780.0)
                .acCoaches(3).acCoachTotalSeats(168).generalCoaches(1).generalCoachesTotalSeats(60)
                .sleeperCoaches(10).sleeperCoachTotalSeats(600).build();

        RouteResources routeResources= RouteResources.builder()
                .routeId(1234l)
                .source("Kakinada").destination("Hyderabad")
                .totalKms(780.0).build();

        Schedule schedule= Schedule.builder().scheduleId(100l)
                .departureDateTime(Timestamp.valueOf(departureTime)).arrivalDateTime(Timestamp.valueOf(arrivalTime))
                .train(trainResources).route(routeResources).build();

        Mockito.when(scheduleService.deleteScheduleById(100l)).thenReturn("Schedule Deleted Successfully");
        mockMvc.perform(delete("/schedule-api/schedule/{scheduleId}",100l))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Schedule Deleted Successfully")));





    }
}