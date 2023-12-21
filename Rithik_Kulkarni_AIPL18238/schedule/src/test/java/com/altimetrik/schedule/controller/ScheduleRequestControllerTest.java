package com.altimetrik.schedule.controller;

import com.altimetrik.schedule.model.Route;
import com.altimetrik.schedule.model.Schedule;
import com.altimetrik.schedule.model.ScheduleRequest;
import com.altimetrik.schedule.model.Train;
import com.altimetrik.schedule.service.ScheduleRequestService;
import org.junit.jupiter.api.Test;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import java.time.LocalDateTime;
import java.util.Arrays;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;



@WebMvcTest(ScheduleRequestController.class)
class ScheduleRequestControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ScheduleRequestService scheduleRequestService;

    @Autowired
    private ObjectMapper objectMapper;


    @Test
    void addSchedule() throws Exception {

        ScheduleRequest sr1 = ScheduleRequest.builder()
                .scheduleId(111)
                .departureDateTime(LocalDateTime.now())
                .arrivalDateTime(LocalDateTime.now())
                .trainNumber(222)
                .routeId(333)
                .build();

        Train tr1 = Train.builder()
                .trainNumber(222)
                .trainName("Test-tr1")
                .totalKms(999)
                .acCoaches(11)
                .acCoachTotalSeats(333)
                .sleeperCoaches(11)
                .sleeperCoachTotalSeats(333)
                .generalCoaches(11)
                .generalCoachTotalSeats(333)
                .build();

        Route rt1 = Route.builder()
                .routeId(333)
                .source("Test-S1")
                .destination("Test-D1")
                .totalKms(999)
                .build();

        Schedule s1 = Schedule.builder()
                .scheduleId(111)
                .departureDateTime(LocalDateTime.now())
                .arrivalDateTime(LocalDateTime.now())
                .train(tr1)
                .route(rt1)
                .build();

        Mockito.when(scheduleRequestService.addSchedule(sr1)).thenReturn(s1);
        mockMvc.perform(MockMvcRequestBuilders.post("/schedule-api/schedule")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(sr1)))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.scheduleId").value(111));
    }

    @Test
    void getAllSchedules() throws Exception{

        ScheduleRequest sr1 = ScheduleRequest.builder()
                .scheduleId(111)
                .departureDateTime(LocalDateTime.now())
                .arrivalDateTime(LocalDateTime.now())
                .trainNumber(222)
                .routeId(333)
                .build();

        Train tr1 = Train.builder()
                .trainNumber(222)
                .trainName("Test-tr1")
                .totalKms(999)
                .acCoaches(11)
                .acCoachTotalSeats(333)
                .sleeperCoaches(11)
                .sleeperCoachTotalSeats(333)
                .generalCoaches(11)
                .generalCoachTotalSeats(333)
                .build();

        Route rt1 = Route.builder()
                .routeId(333)
                .source("Test-S1")
                .destination("Test-D1")
                .totalKms(999)
                .build();

        Schedule s1 = Schedule.builder()
                .scheduleId(111)
                .departureDateTime(LocalDateTime.now())
                .arrivalDateTime(LocalDateTime.now())
                .train(tr1)
                .route(rt1)
                .build();

        Mockito.when(scheduleRequestService.getAllScheduleRequests()).thenReturn(Arrays.asList(s1,s1));
        mockMvc.perform(get("/schedule-api/schedule"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2));

    }

    @Test
    void getScheduleByScheduleId() throws Exception {

        ScheduleRequest sr1 = ScheduleRequest.builder()
                .scheduleId(111)
                .departureDateTime(LocalDateTime.now())
                .arrivalDateTime(LocalDateTime.now())
                .trainNumber(222)
                .routeId(333)
                .build();

        Train tr1 = Train.builder()
                .trainNumber(222)
                .trainName("Test-tr1")
                .totalKms(999)
                .acCoaches(11)
                .acCoachTotalSeats(333)
                .sleeperCoaches(11)
                .sleeperCoachTotalSeats(333)
                .generalCoaches(11)
                .generalCoachTotalSeats(333)
                .build();

        Route rt1 = Route.builder()
                .routeId(333)
                .source("Test-S1")
                .destination("Test-D1")
                .totalKms(999)
                .build();

        Schedule s1 = Schedule.builder()
                .scheduleId(111)
                .departureDateTime(LocalDateTime.now())
                .arrivalDateTime(LocalDateTime.now())
                .train(tr1)
                .route(rt1)
                .build();

        Mockito.when(scheduleRequestService.getScheduleRequestByScheduleId(111)).thenReturn(s1);
        mockMvc.perform(get("/schedule-api/schedule/111")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(s1)))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.scheduleId").value(111));
    }

    @Test
    void updateSchedule() throws Exception{

        ScheduleRequest sr1 = ScheduleRequest.builder()
                .scheduleId(111)
                .departureDateTime(LocalDateTime.now())
                .arrivalDateTime(LocalDateTime.now())
                .trainNumber(222)
                .routeId(333)
                .build();

        ScheduleRequest sr2 = ScheduleRequest.builder()
                .scheduleId(111)
                .departureDateTime(LocalDateTime.now())
                .arrivalDateTime(LocalDateTime.now())
                .trainNumber(444)
                .routeId(555)
                .build();

        Mockito.when(scheduleRequestService.updateScheduleRequest(sr1)).thenReturn(sr2);
        mockMvc.perform(put("/schedule-api/schedule")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(sr1)))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.scheduleId").value(111));
    }
}