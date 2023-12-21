package com.altimetrik.schedule.controller;

import com.altimetrik.schedule.contoller.ScheduleController;
import com.altimetrik.schedule.model.Schedule;
import com.altimetrik.schedule.model.ScheduleRequest;
import com.altimetrik.schedule.service.ScheduleService;
import com.altimetrik.schedule.service.ScheduleServiceImp;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(ScheduleController.class)
class ScheduleControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private ScheduleServiceImp scheduleServiceImp;

    @Test
    void addSchedule() throws Exception {

        ScheduleRequest scheduleRequest = ScheduleRequest.builder().trainId(12).routeId(14).build();

        Schedule mockSchedule = Schedule.builder().scheduleId(1).build();
        when(scheduleServiceImp.addSchedule(any(ScheduleRequest.class))).thenReturn(mockSchedule);
            mockMvc.perform(post("/schedule/schedule")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(objectMapper.writeValueAsString(scheduleRequest)))
                    .andExpect(status().isOk())
                    .andReturn();
    }

}































