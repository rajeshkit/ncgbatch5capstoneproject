package com.altimetrik.trainschedule.controller;

import com.altimetrik.trainschedule.model.Schedule;
import com.altimetrik.trainschedule.service.ScheduleService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

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
    void getAllSchedule() throws Exception {
        Schedule s1 = Schedule.builder()
                .scheduleId("100")
                .departureDateTime(LocalDateTime.now())
                .arrivalDateTime(LocalDateTime.now().plusHours(3)).trainId("f2")
                .routeId("r3").build();
        Schedule s2 = Schedule.builder()
                .scheduleId("101")
                .departureDateTime(LocalDateTime.now())
                .arrivalDateTime(LocalDateTime.now().plusHours(3)).trainId("m2")
                .routeId("g2").build();
        Mockito.when(scheduleService.getAllSchedule()).thenReturn(Arrays.asList(s1, s2));

        mockMvc.perform(get("/schedule-api/schedule"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2)) // Corrected value to match the expected size of the array
                .andExpect(jsonPath("$[0].scheduleId").value("100"))
                .andExpect(jsonPath("$[1].scheduleId").value("101"));
    }

}



