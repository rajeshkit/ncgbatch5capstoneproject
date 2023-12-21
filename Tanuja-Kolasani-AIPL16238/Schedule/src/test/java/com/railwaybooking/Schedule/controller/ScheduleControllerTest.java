package com.railwaybooking.Schedule.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.railwaybooking.Route.model.RouteInfo;
import com.railwaybooking.Schedule.model.NewScheduleInfo;
import com.railwaybooking.Schedule.model.Schedule;
import com.railwaybooking.Schedule.service.ScheduleService;
import com.railwaybooking.Train.model.TrainInfo;
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
    void addSchedule() throws Exception{
        LocalDateTime arrivaldateTime = LocalDateTime.of(2023, 12, 22, 10, 45, 30);
        LocalDateTime departuredateTime = LocalDateTime.of(2023, 12, 23, 05, 25, 45);
        NewScheduleInfo info=NewScheduleInfo.builder().arrivalDateTime(arrivaldateTime).departureDateTime(departuredateTime).trainNumber(123L).routeId(1L).build();
        TrainInfo t= TrainInfo.builder().trainNumber(76542).trainName("Sarkar").totalKms(200).acCoaches(8).acCoachTotalSeats(80).sleeperCoaches(6).sleeperCoachTotalSeats(50).generalCoaches(4).generalCoachTotalSeats(80).build();
        RouteInfo r=RouteInfo.builder().routeId(123).source("Delhi").destination("Jaipur").totalKms(500).build();
        Schedule savedSchedule=Schedule.builder().scheduleId(1000L).arrivalDateTime(info.getArrivalDateTime()).departureDateTime(info.getDepartureDateTime()).trainInfo(t).routeInfo(r).build();
        Mockito.when(scheduleService.addSchedule(info)).thenReturn(savedSchedule);
        mockMvc.perform(post("/scheduleInfo-api/scheduleInfo")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writerWithDefaultPrettyPrinter()
                                .writeValueAsString(info)))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.scheduleId").value(1000L));
    }
    @Test
    void getSchedule() throws Exception{
        LocalDateTime arrivaldateTime = LocalDateTime.of(2023, 12, 22, 10, 45, 30);
        LocalDateTime departuredateTime = LocalDateTime.of(2023, 12, 23, 05, 25, 45);
        NewScheduleInfo resource1=NewScheduleInfo.builder().arrivalDateTime(arrivaldateTime).departureDateTime(departuredateTime).trainNumber(123).routeId(1).build();
        NewScheduleInfo resource2=NewScheduleInfo.builder().arrivalDateTime(arrivaldateTime).departureDateTime(departuredateTime).trainNumber(456).routeId(2).build();
        TrainInfo t= TrainInfo.builder().trainNumber(76542).trainName("Sarkar").totalKms(200).acCoaches(8).acCoachTotalSeats(80).sleeperCoaches(6).sleeperCoachTotalSeats(50).generalCoaches(4).generalCoachTotalSeats(80).build();
        RouteInfo r=RouteInfo.builder().routeId(123).source("Delhi").destination("Jaipur").totalKms(500).build();
        Schedule savedSchedule1=Schedule.builder().scheduleId(1000L).arrivalDateTime(resource1.getArrivalDateTime()).departureDateTime(resource1.getDepartureDateTime()).trainInfo(t).routeInfo(r).build();
        Schedule savedSchedule2=Schedule.builder().scheduleId(1001L).arrivalDateTime(resource2.getArrivalDateTime()).departureDateTime(resource2.getDepartureDateTime()).trainInfo(t).routeInfo(r).build();
        Mockito.when(scheduleService.getSchedule()).thenReturn(Arrays.asList(savedSchedule1,savedSchedule2));
        mockMvc.perform(get("/scheduleInfo-api/scheduleInfo"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2));
    }
    @Test
    void getScheduleById() throws Exception {
        LocalDateTime arrivaldateTime = LocalDateTime.of(2023, 12, 22, 10, 45, 30);
        LocalDateTime departuredateTime = LocalDateTime.of(2023, 12, 23, 05, 25, 45);
        NewScheduleInfo resource=NewScheduleInfo.builder().arrivalDateTime(arrivaldateTime).departureDateTime(departuredateTime).trainNumber(123).routeId(1).build();
        TrainInfo t= TrainInfo.builder().trainNumber(76542).trainName("Sarkar").totalKms(200).acCoaches(8).acCoachTotalSeats(180).sleeperCoaches(6).sleeperCoachTotalSeats(50).generalCoaches(4).generalCoachTotalSeats(80).build();
        RouteInfo r=RouteInfo.builder().routeId(123).source("Delhi").destination("Jaipur").totalKms(500).build();
        Schedule savedSchedule=Schedule.builder().scheduleId(1000L).arrivalDateTime(resource.getArrivalDateTime()).departureDateTime(resource.getDepartureDateTime()).trainInfo(t).routeInfo(r).build();
        Mockito.when(scheduleService.getScheduleById(1000L)).thenReturn(savedSchedule);
        mockMvc.perform(get("/scheduleInfo-api/scheduleInfo/{id}",1000L))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.scheduleId").value(1000L));
    }
    @Test
    void updateSchedule() throws Exception {
        LocalDateTime arrivaldateTime = LocalDateTime.of(2023, 12, 22, 10, 45, 30);
        LocalDateTime departuredateTime = LocalDateTime.of(2023, 12, 23, 05, 25, 45);
        NewScheduleInfo resource=NewScheduleInfo.builder().arrivalDateTime(arrivaldateTime).departureDateTime(departuredateTime).trainNumber(123).routeId(1).build();
        TrainInfo t= TrainInfo.builder().trainNumber(76542).trainName("Sarkar").totalKms(200).acCoaches(8).acCoachTotalSeats(80).sleeperCoaches(6).sleeperCoachTotalSeats(50).generalCoaches(4).generalCoachTotalSeats(80).build();
        RouteInfo r=RouteInfo.builder().routeId(123).source("Delhi").destination("Jaipur").totalKms(500).build();
        Schedule savedSchedule=Schedule.builder().scheduleId(1000L).arrivalDateTime(resource.getArrivalDateTime()).departureDateTime(resource.getDepartureDateTime()).trainInfo(t).routeInfo(r).build();
        Mockito.when(scheduleService.getScheduleById(1000L)).thenReturn(savedSchedule);
        Mockito.when(scheduleService.updateSchedule(savedSchedule)).thenReturn(savedSchedule);
        mockMvc.perform(put("/scheduleInfo-api/scheduleInfo")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writerWithDefaultPrettyPrinter()
                                .writeValueAsString(savedSchedule)))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.scheduleId").value(1000L));
    }
    @Test
    void deleteScheduleById() throws Exception {
        LocalDateTime arrivaldateTime = LocalDateTime.of(2023, 12, 22, 10, 45, 30);
        LocalDateTime departuredateTime = LocalDateTime.of(2023, 12, 23, 05, 25, 45);
        NewScheduleInfo resource=NewScheduleInfo.builder().arrivalDateTime(arrivaldateTime).departureDateTime(departuredateTime).trainNumber(123).routeId(1).build();
        TrainInfo t= TrainInfo.builder().trainNumber(76542).trainName("Sarkar").totalKms(200).acCoaches(8).acCoachTotalSeats(80).sleeperCoaches(6).sleeperCoachTotalSeats(50).generalCoaches(4).generalCoachTotalSeats(80).build();
        RouteInfo r=RouteInfo.builder().routeId(123).source("Delhi").destination("Jaipur").totalKms(500).build();
        Schedule savedSchedule=Schedule.builder().scheduleId(1000L).arrivalDateTime(resource.getArrivalDateTime()).departureDateTime(resource.getDepartureDateTime()).trainInfo(t).routeInfo(r).build();
        Mockito.when(scheduleService.getScheduleById(1000L)).thenReturn(savedSchedule);
        Mockito.when(scheduleService.deleteScheduleById(1000L)).thenReturn("Schedule Deleted Successfully");
        mockMvc.perform(delete("/scheduleInfo-api/scheduleInfo/{id}",1000L))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("Schedule Deleted Successfully"));
    }
    @Test
    void getScheduleInfoByTrainNumber() throws Exception {
        LocalDateTime arrivaldateTime = LocalDateTime.of(2023, 12, 22, 10, 45, 30);
        LocalDateTime departuredateTime = LocalDateTime.of(2023, 12, 23, 05, 25, 45);
        NewScheduleInfo resource=NewScheduleInfo.builder().arrivalDateTime(arrivaldateTime).departureDateTime(departuredateTime).trainNumber(123).routeId(1).build();
        TrainInfo t= TrainInfo.builder().trainNumber(76542).trainName("Sarkar").totalKms(200).acCoaches(8).acCoachTotalSeats(80).sleeperCoaches(6).sleeperCoachTotalSeats(50).generalCoaches(4).generalCoachTotalSeats(80).build();
        RouteInfo r=RouteInfo.builder().routeId(123).source("Delhi").destination("Jaipur").totalKms(500).build();
        Schedule savedSchedule1=Schedule.builder().scheduleId(1000L).arrivalDateTime(resource.getArrivalDateTime()).departureDateTime(resource.getDepartureDateTime()).trainInfo(t).routeInfo(r).build();
        Schedule savedSchedule2=Schedule.builder().scheduleId(1001L).arrivalDateTime(resource.getArrivalDateTime()).departureDateTime(resource.getDepartureDateTime()).trainInfo(t).routeInfo(r).build();
        Mockito.when(scheduleService.getScheduleInfoByTrainNumber(123L)).thenReturn(Arrays.asList(savedSchedule1,savedSchedule2));
        mockMvc.perform(get("/scheduleInfo-api/trainInfo/{trainNumber}",123L))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2));
    }

}
