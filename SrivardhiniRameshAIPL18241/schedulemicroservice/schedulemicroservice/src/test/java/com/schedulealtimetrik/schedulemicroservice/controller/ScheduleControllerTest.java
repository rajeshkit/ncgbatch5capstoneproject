package com.schedulealtimetrik.schedulemicroservice.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.schedulealtimetrik.schedulemicroservice.model.NewScheduleRequest;
import com.schedulealtimetrik.schedulemicroservice.model.Schedule;
import com.schedulealtimetrik.schedulemicroservice.service.ScheduleService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class ScheduleControllerTest {

    private MockMvc mockMvc;

    @Mock
    private ScheduleService scheduleService;

    @InjectMocks
    private ScheduleController scheduleController;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(scheduleController).build();
    }

    @Test
    void addSchedule() throws Exception {
        NewScheduleRequest newScheduleRequest = createSampleNewScheduleRequest();
        Schedule schedule = createSampleSchedule();
        when(scheduleService.addSchedule(any(NewScheduleRequest.class))).thenReturn(schedule);

        mockMvc.perform(post("/schedule-api/schedule")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(newScheduleRequest)))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.scheduleId").value(schedule.getScheduleId()));
    }

    @Test
    void getAllSchedules() throws Exception {
        when(scheduleService.getAllSchedules()).thenReturn(List.of(createSampleSchedule()));

        mockMvc.perform(get("/schedule-api/schedule"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].scheduleId").value(1));
    }

    @Test
    void getScheduleById() throws Exception {
        Schedule schedule = createSampleSchedule();
        when(scheduleService.getScheduleById(1)).thenReturn(schedule);

        mockMvc.perform(get("/schedule-api/schedule/1"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.scheduleId").value(1));
    }

    @Test
    void updateSchedule() throws Exception {
        Schedule schedule = createSampleSchedule();
        when(scheduleService.updateSchedule(any(Schedule.class))).thenReturn(schedule);

        mockMvc.perform(put("/schedule-api/schedule")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(schedule)))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.scheduleId").value(schedule.getScheduleId()));
    }

    @Test
    void deleteScheduleById() throws Exception {
        when(scheduleService.deleteScheduleById(1)).thenReturn("Schedule deleted successfully");

        mockMvc.perform(delete("/schedule-api/schedule/1"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(content().string("Schedule deleted successfully"));
    }

    private NewScheduleRequest createSampleNewScheduleRequest() {
        return new NewScheduleRequest("2023-12-15T12:00", "2023-12-15T18:00", 100, 1);
    }

    private Schedule createSampleSchedule() {
        return Schedule.builder()
                .scheduleId(1)
                .departureDateTime("2023-12-15T12:00")
                .arrivalDateTime("2023-12-15T18:00")
                .train("Train details")
                .route("Route details")
                .build();
    }
}
