package com.schedulemicroservice.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.schedulemicroservice.model.Route;
import com.schedulemicroservice.model.Schedule;
import com.schedulemicroservice.model.ScheduleRequest;
import com.schedulemicroservice.model.Train;
import com.schedulemicroservice.service.ScheduleService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.time.LocalDateTime;
import java.util.Arrays;

@WebMvcTest(ScheduleController.class)
class ScheduleControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private ScheduleService scheduleService;
    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void testCreateSchedule() throws Exception {

        Train train = Train.builder().trainNumber(1).trainName("Rajasthani Expressss").totalKms(1490)
                .acCoaches(3).acCoachTotalSeats(120)
                .sleeperCoaches(5).sleeperCoachTotalSeats(200)
                .generalCoaches(7).generalCoachTotalSeats(280).build();

        Route route = Route.builder().routeId(1).source("Jaipur").destination("Darjeeling").totalKms(1490).build();

        ScheduleRequest scheduleRequest = ScheduleRequest.builder()
                .departureDateTime(LocalDateTime.parse("2023-01-01T12:00:00"))
                .arrivalDateTime(LocalDateTime.parse("2023-01-01T18:00:00"))
                .trainNumber(1)
                .routeId(1)
                .build();

        Schedule mockSchedule = Schedule.builder()
                .scheduleId(152)
                .departureDateTime(LocalDateTime.parse("2023-01-01T12:00:00"))
                .arrivalDateTime(LocalDateTime.parse("2023-01-01T18:00:00"))
                .train(train)
                .route(route)
                .build();

        Mockito.when(scheduleService.createSchedule(scheduleRequest)).thenReturn(mockSchedule);

        mockMvc.perform(MockMvcRequestBuilders.post("/schedule-train/schedule")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(scheduleRequest)))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.scheduleId").value(152))
                .andExpect(MockMvcResultMatchers.jsonPath("$.departureDateTime").value("2023-01-01T12:00:00"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.arrivalDateTime").value("2023-01-01T18:00:00"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.train.trainNumber").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$.route.routeId").value(1));
    }

    @Test
    void testGetAllSchedulesDetail() throws Exception {
        Schedule schedule1 = Schedule.builder().scheduleId(1).departureDateTime(LocalDateTime.parse("2023-01-01T12:00:00")).arrivalDateTime(LocalDateTime.parse("2023-01-01T18:00:00")).build();
        Schedule schedule2 = Schedule.builder().scheduleId(2).departureDateTime(LocalDateTime.parse("2023-01-02T12:00:00")).arrivalDateTime(LocalDateTime.parse("2023-01-02T18:00:00")).build();

        Mockito.when(scheduleService.getAllSchedules()).thenReturn(Arrays.asList(schedule1, schedule2));

        mockMvc.perform(MockMvcRequestBuilders.get("/schedule-train/schedule"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.length()").value(2));
    }

    @Test
    void testGetScheduleDetailsById() throws Exception {
        Schedule schedule = Schedule.builder().scheduleId(1).departureDateTime(LocalDateTime.parse("2023-01-01T12:00:00")).arrivalDateTime(LocalDateTime.parse("2023-01-01T18:00:00")).build();

        Mockito.when(scheduleService.getScheduleDetailsById(1)).thenReturn(schedule);

        mockMvc.perform(MockMvcRequestBuilders.get("/schedule-train/{id}", 1))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.scheduleId").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$.departureDateTime").value("2023-01-01T12:00:00"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.arrivalDateTime").value("2023-01-01T18:00:00"));
    }

    @Test
    void testUpdateScheduleDetails() throws Exception {
        ScheduleRequest scheduleRequest = ScheduleRequest.builder()
                .departureDateTime(LocalDateTime.parse("2023-01-01T12:00:00"))
                .arrivalDateTime(LocalDateTime.parse("2023-01-01T18:00:00"))
                .trainNumber(1)
                .routeId(1)
                .build();

        Schedule updatedSchedule = Schedule.builder()
                .scheduleId(1)
                .departureDateTime(LocalDateTime.parse("2023-01-01T14:00:00"))
                .arrivalDateTime(LocalDateTime.parse("2023-01-01T20:00:00"))
                .train(Train.builder().trainNumber(1).build())
                .route(Route.builder().routeId(1).build())
                .build();

        Mockito.when(scheduleService.updateScheduleDetails(1, scheduleRequest)).thenReturn(updatedSchedule);

        mockMvc.perform(MockMvcRequestBuilders.put("/schedule-train/schedule/{id}", 1)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(scheduleRequest)))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.scheduleId").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$.departureDateTime").value("2023-01-01T14:00:00"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.arrivalDateTime").value("2023-01-01T20:00:00"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.train.trainNumber").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$.route.routeId").value(1));
    }

    @Test
    void testDeleteScheduleDetails() throws Exception {
        Mockito.when(scheduleService.deleteScheduleDetails(1)).thenReturn("Schedule deleted successfully");

        mockMvc.perform(MockMvcRequestBuilders.delete("/schedule-train/{id}", 1))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("Schedule deleted successfully"));
    }
}