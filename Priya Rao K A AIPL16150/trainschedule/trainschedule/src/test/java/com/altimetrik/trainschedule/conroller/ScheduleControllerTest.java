package com.altimetrik.trainschedule.conroller;

import com.altimetrik.trainschedule.controller.ScheduleController;
import com.altimetrik.trainschedule.model.Schedule;
import com.altimetrik.trainschedule.model.ScheduleRequest;
import com.altimetrik.trainschedule.service.ScheduleService;
import com.altimetrik.trainschedule.service.ScheduleServiceImpl;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
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

        ScheduleRequest scheduleRequest = ScheduleRequest.builder().trainNumber(12).routeId(14).build();

        Schedule mockSchedule = Schedule.builder().scheduleId(1).build();
        when(scheduleService.addSchedule(any(ScheduleRequest.class))).thenReturn(mockSchedule);
        mockMvc.perform(post("/schedule-api/schedule")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(scheduleRequest)))
                .andExpect(status().isOk())
                .andReturn();
    }

}