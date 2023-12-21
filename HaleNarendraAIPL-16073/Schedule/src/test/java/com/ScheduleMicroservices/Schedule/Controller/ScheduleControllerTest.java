package com.ScheduleMicroservices.Schedule.Controller;

import com.RouteMicroservices.Route.model.RouteResources;
import com.ScheduleMicroservices.Schedule.model.NewScheduleResources;
import com.ScheduleMicroservices.Schedule.model.ScheduleResources;
import com.ScheduleMicroservices.Schedule.service.ScheduleService;
import com.TrainBookingSystem.Train.model.TrainResources;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ScheduleController.class)
class ScheduleControllerTest {
    @MockBean
    private ScheduleService scheduleService;
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;


    @Test
    void addSchedule() throws Exception {
        ScheduleResources scheduleResources = ScheduleResources.builder().scheduleId(67988L).arrivalDateTime(Timestamp.valueOf("2019-02-06 11:32:02")).departureDateTime(Timestamp.valueOf("2019-02-06 11:32:02")).trainId(123L).routeId(1L).build();
        TrainResources trainResponse = TrainResources.builder().trainNumber(103L).trainName("gfsgjh").totalKms(12.2).acCoaches(5).acCoachTotalSeats(20)
                .sleeperCoaches(5).sleeperCoachTotalSeats(50).generalCoaches(5).generalCoachesTotalSeats(50).build();

        RouteResources routeResponse = RouteResources.builder().routeId(12L).source("Hyderabad").destination("Pune").totalKms(12.4).build();
        NewScheduleResources newScheduleResources = NewScheduleResources.builder().scheduleId(67988L).arrivalDateTime(scheduleResources.getArrivalDateTime())
                .departureDateTime(scheduleResources.getDepartureDateTime()).trainResources(trainResponse).routeResources(routeResponse).build();


        Mockito.when(scheduleService.addScheduleResources(scheduleResources)).thenReturn(newScheduleResources);
        mockMvc.perform(post("/schedule-api/schedule")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writerWithDefaultPrettyPrinter()
                                .writeValueAsString(scheduleResources)))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.scheduleId").value(67988L));
    }

    @Test
    void findDetailByTrainId() throws Exception {
        ScheduleResources scheduleResources = ScheduleResources.builder().scheduleId(67988L).arrivalDateTime(Timestamp.valueOf("2019-02-06 11:32:02")).departureDateTime(Timestamp.valueOf("2019-02-06 11:32:02")).trainId(123L).routeId(1L).build();
        TrainResources trainResponse = TrainResources.builder().trainNumber(103L).trainName("gfsgjh").totalKms(12.2).acCoaches(5).acCoachTotalSeats(20)
                .sleeperCoaches(5).sleeperCoachTotalSeats(50).generalCoaches(5).generalCoachesTotalSeats(50).build();

        RouteResources routeResponse = RouteResources.builder().routeId(12L).source("Hyderabad").destination("Pune").totalKms(12.4).build();
        NewScheduleResources newScheduleResources = NewScheduleResources.builder().scheduleId(67988L).arrivalDateTime(scheduleResources.getArrivalDateTime())
                .departureDateTime(scheduleResources.getDepartureDateTime()).trainResources(trainResponse).routeResources(routeResponse).build();


        ScheduleResources scheduleResources1 = ScheduleResources.builder().scheduleId(3458L).arrivalDateTime(Timestamp.valueOf("2019-02-06 11:32:02")).departureDateTime(Timestamp.valueOf("2019-02-06 11:32:02")).trainId(123L).routeId(1L).build();
        TrainResources trainResponse1 = TrainResources.builder().trainNumber(104L).trainName("gfsgjh").totalKms(12.2).acCoaches(5).acCoachTotalSeats(20)
                .sleeperCoaches(5).sleeperCoachTotalSeats(50).generalCoaches(5).generalCoachesTotalSeats(50).build();

        RouteResources routeResponse1 = RouteResources.builder().routeId(92L).source("Hyderabad").destination("Pune").totalKms(12.4).build();
        NewScheduleResources newScheduleResources1 = NewScheduleResources.builder().scheduleId(67988L).arrivalDateTime(scheduleResources1.getArrivalDateTime())
                .departureDateTime(scheduleResources1.getDepartureDateTime()).trainResources(trainResponse1).routeResources(routeResponse1).build();

        Mockito.when(scheduleService.findDetailByTrainId(104L)).thenReturn(Arrays.asList(newScheduleResources,newScheduleResources1));
        mockMvc.perform(get("/schedule-api/train/{id}",104L))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2));
    }

    @Test
    void getScheduleById() throws Exception {
        ScheduleResources scheduleResources = ScheduleResources.builder().scheduleId(67988L).arrivalDateTime(Timestamp.valueOf("2019-02-06 11:32:02")).departureDateTime(Timestamp.valueOf("2019-02-06 11:32:02")).trainId(123L).routeId(1L).build();
        TrainResources trainResponse = TrainResources.builder().trainNumber(103L).trainName("gfsgjh").totalKms(12.2).acCoaches(5).acCoachTotalSeats(20)
                .sleeperCoaches(5).sleeperCoachTotalSeats(50).generalCoaches(5).generalCoachesTotalSeats(50).build();

        RouteResources routeResponse = RouteResources.builder().routeId(12L).source("Hyderabad").destination("Pune").totalKms(12.4).build();
        NewScheduleResources newScheduleResources = NewScheduleResources.builder().scheduleId(67988L).arrivalDateTime(scheduleResources.getArrivalDateTime())
                .departureDateTime(scheduleResources.getDepartureDateTime()).trainResources(trainResponse).routeResources(routeResponse).build();

        Mockito.when(scheduleService.getScheduleById(67988L)).thenReturn(newScheduleResources);
        mockMvc.perform(get("/schedule-api/schedule/{Id}",67988L))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.scheduleId").value(67988L));
    }

    @Test
    void updateScheduleDetail() throws Exception {

        ScheduleResources scheduleResources = ScheduleResources.builder().scheduleId(67988L).arrivalDateTime(Timestamp.valueOf("2019-02-06 11:32:02")).departureDateTime(Timestamp.valueOf("2019-02-06 11:32:02")).trainId(123L).routeId(1L).build();
        TrainResources trainResponse = TrainResources.builder().trainNumber(103L).trainName("gfsgjh").totalKms(12.2).acCoaches(5).acCoachTotalSeats(20)
                .sleeperCoaches(5).sleeperCoachTotalSeats(50).generalCoaches(5).generalCoachesTotalSeats(50).build();

        RouteResources routeResponse = RouteResources.builder().routeId(12L).source("Hyderabad").destination("Pune").totalKms(12.4).build();
        NewScheduleResources newScheduleResources = NewScheduleResources.builder().scheduleId(67988L).arrivalDateTime(scheduleResources.getArrivalDateTime())
                .departureDateTime(scheduleResources.getDepartureDateTime()).trainResources(trainResponse).routeResources(routeResponse).build();

        Mockito.when(scheduleService.updateScheduleDetail(newScheduleResources)).thenReturn(newScheduleResources);
        mockMvc.perform(put("/schedule-api/schedule")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writerWithDefaultPrettyPrinter()
                                .writeValueAsString(newScheduleResources)))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.scheduleId").value(67988L));
    }

    @Test
    void allScheduleDetail() throws Exception {
        ScheduleResources scheduleResources = ScheduleResources.builder().scheduleId(67988L).arrivalDateTime(Timestamp.valueOf("2019-02-06 11:32:02")).departureDateTime(Timestamp.valueOf("2019-02-06 11:32:02")).trainId(123L).routeId(1L).build();
        TrainResources trainResponse = TrainResources.builder().trainNumber(103L).trainName("gfsgjh").totalKms(12.2).acCoaches(5).acCoachTotalSeats(20)
                .sleeperCoaches(5).sleeperCoachTotalSeats(50).generalCoaches(5).generalCoachesTotalSeats(50).build();

        RouteResources routeResponse = RouteResources.builder().routeId(12L).source("Hyderabad").destination("Pune").totalKms(12.4).build();
        NewScheduleResources newScheduleResources = NewScheduleResources.builder().scheduleId(67988L).arrivalDateTime(scheduleResources.getArrivalDateTime())
                .departureDateTime(scheduleResources.getDepartureDateTime()).trainResources(trainResponse).routeResources(routeResponse).build();


        ScheduleResources scheduleResources1 = ScheduleResources.builder().scheduleId(3458L).arrivalDateTime(Timestamp.valueOf("2019-02-06 11:32:02")).departureDateTime(Timestamp.valueOf("2019-02-06 11:32:02")).trainId(123L).routeId(1L).build();
        TrainResources trainResponse1 = TrainResources.builder().trainNumber(104L).trainName("gfsgjh").totalKms(12.2).acCoaches(5).acCoachTotalSeats(20)
                .sleeperCoaches(5).sleeperCoachTotalSeats(50).generalCoaches(5).generalCoachesTotalSeats(50).build();

        RouteResources routeResponse1 = RouteResources.builder().routeId(92L).source("Hyderabad").destination("Pune").totalKms(12.4).build();
        NewScheduleResources newScheduleResources1 = NewScheduleResources.builder().scheduleId(67988L).arrivalDateTime(scheduleResources1.getArrivalDateTime())
                .departureDateTime(scheduleResources1.getDepartureDateTime()).trainResources(trainResponse1).routeResources(routeResponse1).build();

        Mockito.when(scheduleService.getAllScheduleDetail()).thenReturn(Arrays.asList(newScheduleResources,newScheduleResources1));
        mockMvc.perform(get("/schedule-api/schedule"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2));

    }

    @Test
    void deleteScheduleById() throws Exception {
        ScheduleResources scheduleResources = ScheduleResources.builder().scheduleId(67988L).arrivalDateTime(Timestamp.valueOf("2019-02-06 11:32:02")).departureDateTime(Timestamp.valueOf("2019-02-06 11:32:02")).trainId(123L).routeId(1L).build();
        TrainResources trainResponse = TrainResources.builder().trainNumber(103L).trainName("gfsgjh").totalKms(12.2).acCoaches(5).acCoachTotalSeats(20)
                .sleeperCoaches(5).sleeperCoachTotalSeats(50).generalCoaches(5).generalCoachesTotalSeats(50).build();

        RouteResources routeResponse = RouteResources.builder().routeId(12L).source("Hyderabad").destination("Pune").totalKms(12.4).build();
        NewScheduleResources newScheduleResources = NewScheduleResources.builder().scheduleId(67988L).arrivalDateTime(scheduleResources.getArrivalDateTime())
                .departureDateTime(scheduleResources.getDepartureDateTime()).trainResources(trainResponse).routeResources(routeResponse).build();


        Mockito.when(scheduleService.deleteScheduleById(67988L)).thenReturn("Schedule Deleted Successfully");
        mockMvc.perform(delete("/schedule-api/schedule/{Id}",67988L))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Schedule Deleted Successfully")));
    }
}