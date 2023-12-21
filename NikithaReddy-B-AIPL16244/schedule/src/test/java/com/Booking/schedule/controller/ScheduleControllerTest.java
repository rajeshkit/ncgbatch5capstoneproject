package com.Booking.schedule.controller;

import com.Booking.route.customexception.RouteNotFindException;
import com.Booking.route.model.RouteResources;
import com.Booking.schedule.model.Schedule;
import com.Booking.schedule.model.ScheduleResources;
import com.Booking.schedule.service.ScheduleService;
import com.Booking.train.model.TrainResources;
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

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
@WebMvcTest(ScheduleController.class)
public class ScheduleControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private ScheduleService scheduleService;
    @Autowired
    private ObjectMapper objectMapper;
    @Test
    void addSchedule() throws Exception, RouteNotFindException {
        LocalDateTime arrivaldateTime = LocalDateTime.of(2023, 12, 25, 12, 30, 45);
        LocalDateTime departuredateTime = LocalDateTime.of(2023, 12, 25, 12, 35, 45);
        Schedule resource=Schedule.builder().arrivalDateTime(arrivaldateTime).departureDateTime(departuredateTime).trainNumber(199L).RouteId(100L).build();
        TrainResources t= TrainResources.builder().trainNumber(199L).trainName("kachigudaexpress").totalkms(111.0).acCoaches(10).acCoachTotalSeats(300).sleepercoaching(65).sleeperCoachTotalSeats(90).generalCoaches(40).generalCoachesTotalSeats(10000).build();
        RouteResources r=RouteResources.builder().routeId(100L).source("rajasthan").destination("mumbai").totalkms(500.0).build();
        ScheduleResources savedSchedule=ScheduleResources.builder().scheduleId(1000L).arrivalDateTime(resource.getArrivalDateTime()).departureDateTime(resource.getDepartureDateTime()).train(t).route(r).build();
        Mockito.when(scheduleService.addSchedule(resource)).thenReturn(savedSchedule);
        mockMvc.perform(post("/schedule-api/schedule_table")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writerWithDefaultPrettyPrinter()
                                .writeValueAsString(resource)))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.scheduleId").value(1000L));
    }
    @Test
    void getSchedule() throws Exception {
        LocalDateTime arrivaldateTime = LocalDateTime.of(2023, 12, 25, 12, 30, 45);
        LocalDateTime departuredateTime = LocalDateTime.of(2023, 12, 25, 12, 35, 45);
        Schedule resource1=Schedule.builder().arrivalDateTime(arrivaldateTime).departureDateTime(departuredateTime).trainNumber(199L).RouteId(100L).build();
        Schedule resource2=Schedule.builder().arrivalDateTime(arrivaldateTime).departureDateTime(departuredateTime).trainNumber(12345L).RouteId(1234L).build();
        TrainResources t= TrainResources.builder().trainNumber(199L).trainName("kachigudaexpress").totalkms(111.0).acCoaches(10).acCoachTotalSeats(300).sleepercoaching(65).sleeperCoachTotalSeats(90).generalCoaches(40).generalCoachesTotalSeats(10000).build();
        RouteResources r=RouteResources.builder().routeId(100L).source("rajasthan").destination("mumbai").totalkms(500.0).build();
        ScheduleResources savedSchedule1=ScheduleResources.builder().scheduleId(1000L).arrivalDateTime(resource1.getArrivalDateTime()).departureDateTime(resource1.getDepartureDateTime()).train(t).route(r).build();
        ScheduleResources savedSchedule2=ScheduleResources.builder().scheduleId(1001L).arrivalDateTime(resource2.getArrivalDateTime()).departureDateTime(resource2.getDepartureDateTime()).train(t).route(r).build();
        Mockito.when(scheduleService.getSchedule()).thenReturn(Arrays.asList(savedSchedule1,savedSchedule2));
        mockMvc.perform(get("/schedule-api/schedule_table"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2));
    }
    @Test
    void getScheduleById() throws Exception {
        LocalDateTime arrivaldateTime = LocalDateTime.of(2023, 12, 25, 12, 30, 45);
        LocalDateTime departuredateTime = LocalDateTime.of(2023, 12, 25, 12, 35, 45);
        Schedule resource=Schedule.builder().arrivalDateTime(arrivaldateTime).departureDateTime(departuredateTime).trainNumber(199L).RouteId(100L).build();
        TrainResources t= TrainResources.builder().trainNumber(199L).trainName("kachigudaexpress").totalkms(111.0).acCoaches(10).acCoachTotalSeats(300).sleepercoaching(65).sleeperCoachTotalSeats(90).generalCoaches(40).generalCoachesTotalSeats(10000).build();
        RouteResources r=RouteResources.builder().routeId(100L).source("rajasthan").destination("mumbai").totalkms(500.0).build();
        ScheduleResources savedSchedule=ScheduleResources.builder().scheduleId(1000L).arrivalDateTime(resource.getArrivalDateTime()).departureDateTime(resource.getDepartureDateTime()).train(t).route(r).build();
        Mockito.when(scheduleService.getScheduleById(1000L)).thenReturn(savedSchedule);
        mockMvc.perform(get("/schedule-api/schedule_table/{id}",1000L))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.scheduleId").value(1000L));
    }
    @Test
    void updateSchedule() throws Exception {
        LocalDateTime arrivaldateTime = LocalDateTime.of(2023, 12, 25, 12, 30, 45);
        LocalDateTime departuredateTime = LocalDateTime.of(2023, 12, 25, 12, 35, 45);
        Schedule resource=Schedule.builder().arrivalDateTime(arrivaldateTime).departureDateTime(departuredateTime).trainNumber(199L).RouteId(100L).build();
        TrainResources t= TrainResources.builder().trainNumber(199L).trainName("kachigudaexpress").totalkms(111.0).acCoaches(10).acCoachTotalSeats(300).sleepercoaching(65).sleeperCoachTotalSeats(90).generalCoaches(40).generalCoachesTotalSeats(10000).build();
        RouteResources r=RouteResources.builder().routeId(100L).source("rajasthan").destination("mumbai").totalkms(500.0).build();
        ScheduleResources savedSchedule=ScheduleResources.builder().scheduleId(1000L).arrivalDateTime(resource.getArrivalDateTime()).departureDateTime(resource.getDepartureDateTime()).train(t).route(r).build();
        Mockito.when(scheduleService.getScheduleById(1000L)).thenReturn(savedSchedule);
        Mockito.when(scheduleService.updateSchedule(savedSchedule)).thenReturn(savedSchedule);
        mockMvc.perform(put("/schedule-api/schedule_table")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writerWithDefaultPrettyPrinter()
                                .writeValueAsString(savedSchedule)))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.scheduleId").value(1000L));
    }

    @Test
    void deleteSchedule() throws Exception {
        LocalDateTime arrivaldateTime = LocalDateTime.of(2023, 12, 25, 12, 30, 45);
        LocalDateTime departuredateTime = LocalDateTime.of(2023, 12, 25, 12, 35, 45);
        Schedule resource=Schedule.builder().arrivalDateTime(arrivaldateTime).departureDateTime(departuredateTime).trainNumber(199L).RouteId(100L).build();
        TrainResources t= TrainResources.builder().trainNumber(199L).trainName("kachigudaexpress").totalkms(111.0).acCoaches(10).acCoachTotalSeats(300).sleepercoaching(65).sleeperCoachTotalSeats(90).generalCoaches(40).generalCoachesTotalSeats(10000).build();
        RouteResources r=RouteResources.builder().routeId(100L).source("rajasthan").destination("mumbai").totalkms(500.0).build();
        ScheduleResources savedSchedule=ScheduleResources.builder().scheduleId(1000L).arrivalDateTime(resource.getArrivalDateTime()).departureDateTime(resource.getDepartureDateTime()).train(t).route(r).build();
        Mockito.when(scheduleService.getScheduleById(1000L)).thenReturn(savedSchedule);
        Mockito.when(scheduleService.deleteschedule(1000L)).thenReturn("Schedule Deleted Successfully");
        mockMvc.perform(delete("/schedule-api/schedule_table/{id}",1000L))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("Schedule Deleted Successfully"));
    }

    @Test
    void getSchedulesByTrainNumber() throws Exception {
        LocalDateTime arrivaldateTime = LocalDateTime.of(2023, 12, 25, 12, 30, 45);
        LocalDateTime departuredateTime = LocalDateTime.of(2023, 12, 25, 12, 35, 45);
        Schedule resource=Schedule.builder().arrivalDateTime(arrivaldateTime).departureDateTime(departuredateTime).trainNumber(12345L).RouteId(100L).build();
        TrainResources t= TrainResources.builder().trainNumber(199L).trainName("kachigudaexpress").totalkms(111.0).acCoaches(10).acCoachTotalSeats(300).sleepercoaching(65).sleeperCoachTotalSeats(90).generalCoaches(40).generalCoachesTotalSeats(10000).build();
        RouteResources r=RouteResources.builder().routeId(100L).source("rajasthan").destination("mumbai").totalkms(500.0).build();
        ScheduleResources savedSchedule1=ScheduleResources.builder().scheduleId(1000L).arrivalDateTime(resource.getArrivalDateTime()).departureDateTime(resource.getDepartureDateTime()).train(t).route(r).build();
        ScheduleResources savedSchedule2=ScheduleResources.builder().scheduleId(1001L).arrivalDateTime(resource.getArrivalDateTime()).departureDateTime(resource.getDepartureDateTime()).train(t).route(r).build();
        Mockito.when(scheduleService.getSchedulesBytrainNumber(199L)).thenReturn(Arrays.asList(savedSchedule1,savedSchedule2));
        mockMvc.perform(get("/schedule-api/train/{trainNumber}",199L))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2));
    }
}
