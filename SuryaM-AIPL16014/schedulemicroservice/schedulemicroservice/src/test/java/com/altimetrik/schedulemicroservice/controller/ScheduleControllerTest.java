package com.altimetrik.schedulemicroservice.controller;

import com.altimetrik.schedulemicroservice.model.NewScheduleRequest;
import com.altimetrik.schedulemicroservice.model.Route;
import com.altimetrik.schedulemicroservice.model.Schedule;
import com.altimetrik.schedulemicroservice.model.Train;
import com.altimetrik.schedulemicroservice.service.ScheduleService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import java.util.Arrays;
import java.util.Date;

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

    @Test
    void addSchedule() throws Exception {

        NewScheduleRequest newScheduleRequest = new NewScheduleRequest();
        newScheduleRequest.setDepartureDateTime(new Date(new Date().getTime() + 24 * 60 * 60 * 1000));
        newScheduleRequest.setArrivalDateTime(new Date(new Date().getTime()+ 24 + 24 * 60 * 60 * 1000));
        newScheduleRequest.setTrainNumber(12345);
        newScheduleRequest.setRouteId(23456);

        Train t= Train.builder()
                .trainNumber(100)
                .trainName("Super Express")
                .totalKms(200)
                .acCoaches(15)
                .acCoachTotalSeats(20)
                .sleeperCoaches(8)
                .sleeperCoachTotalSeats(12)
                .generalCoaches(20)
                .generalCoachTotalSeats(28).build();


        Route r= Route.builder()
                .routeId(100)
                .Source("Chennai")
                .Destination("Bangalore")
                .totalKms(200).build();

        Schedule schedule = Schedule.builder()
                .scheduleId(34567)
                .departureDateTime(newScheduleRequest.getDepartureDateTime())
                .arrivalDateTime(newScheduleRequest.getArrivalDateTime())
                .train(t.toString())
                .route(r.toString())
                .build();

        Mockito.when(scheduleService.addSchedule(Mockito.any(NewScheduleRequest.class))).thenReturn(schedule);

        mockMvc.perform(post("/schedule-api/schedule")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(newScheduleRequest)))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.scheduleId").value(34567));
    }

    @Test
    void getAllSchedule() throws Exception {

        Schedule schedule1 = Schedule.builder()
                .scheduleId(34567)
                .departureDateTime(new Date(new Date().getTime() + 24 * 60 * 60 * 1000))
                .arrivalDateTime(new Date(new Date().getTime() + 24 + 24 * 60 * 60 * 1000))
                .train("Test Train 1")
                .route("Test Route 1")
                .build();

        Schedule schedule2 = Schedule.builder()
                .scheduleId(34568)
                .departureDateTime(new Date(new Date().getTime() + 24 * 60 * 60 * 1000))
                .arrivalDateTime(new Date(new Date().getTime() + 24 + 24 * 60 * 60 * 1000))
                .train("Test Train 2")
                .route("Test Route 2")
                .build();

        Mockito.when(scheduleService.getAllSchedule()).thenReturn(Arrays.asList(schedule1, schedule2));

        mockMvc.perform(get("/schedule-api/schedule/"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2));
    }

    @Test
    void getScheduleById() throws Exception {

        Schedule schedule = Schedule.builder()
                .scheduleId(34567)
                .departureDateTime(new Date())
                .arrivalDateTime(new Date())
                .train("Test Train 1")
                .route("Test Route 1")
                .build();

        Mockito.when(scheduleService.getScheduleById(34567)).thenReturn(schedule);

        mockMvc.perform(get("/schedule-api/schedule/{id}", 34567))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.scheduleId").value(34567));
    }

    @Test
    void updateSchedule() throws Exception {


        Schedule schedule = Schedule.builder()
                .scheduleId(34567)
                .train("Updated Train")
                .departureDateTime(new Date(new Date().getTime() + 24 * 60 * 60 * 1000))
                .arrivalDateTime(new Date(new Date().getTime() + 24 + 24 * 60 * 60 * 1000))
                .route("Updated Route")
                .build();

        Mockito.when(scheduleService.updateSchedule(Mockito.any(Schedule.class))).thenReturn(schedule);

        mockMvc.perform(put("/schedule-api/schedule")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(schedule)))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.train").value("Updated Train"));
    }


    @Test
    void deleteScheduleById() throws Exception {

        int scheduleId = 34567;

        Mockito.when(scheduleService.deleteScheduleById(scheduleId)).thenReturn("Schedule deleted successfully");

        mockMvc.perform(delete("/schedule-api/schedule/{id}", scheduleId))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").value("Schedule deleted successfully"));
    }
}
