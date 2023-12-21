package com.finalproject.schedule;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.finalproject.schedule.controller.ScheduleController;
import com.finalproject.schedule.model.Schedule;
import com.finalproject.schedule.model.ScheduleRequest;
import com.finalproject.schedule.service.ScheduleService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class ScheduleControllerTesting {

    @Mock
    private ScheduleService scheduleService;

    @InjectMocks
    private ScheduleController scheduleController;

    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(scheduleController).build();
    }

    @Test
    void testAddSchedule() throws Exception {
        ScheduleRequest scheduleRequest = ScheduleRequest.builder()
                .scheduleId(2)
                .arrivalDateTime("2024-11-21T18:19:45.027+00:00")
                .departureDateTime("2025-12-20T18:18:59.024+00:00")
                .trainNumber(31215)
                .routeId(4).build();

             Schedule schedule = new Schedule();
           when(scheduleService.addSchedule(any(ScheduleRequest.class))).thenReturn(schedule);
             mockMvc.perform(post("/schedule-restapi/schedule")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(scheduleRequest)))
                        .andExpect(status().isOk())
                         .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                       .andExpect(jsonPath("$.scheduleId").exists()); // Adjust this based on your response structure
    }


    @Test
    void testDeleteSchedule() throws Exception {
        int scheduleId = 3;

        when(scheduleService.deleteSchedule(scheduleId)).thenReturn("Schedule deleted successfully");
             mockMvc.perform(delete("/schedule-restapi/schedule/{scheduleId}", scheduleId))
                   .andExpect(status().isOk())
                      .andExpect(content().string("Schedule deleted successfully"));
    }
}
