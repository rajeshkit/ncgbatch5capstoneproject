package com.altimetrik.schedulemicroservice.ControllerTest;

import com.altimetrik.schedulemicroservice.controller.ScheduleController;
import com.altimetrik.schedulemicroservice.model.NewScheduleRequest;
import com.altimetrik.schedulemicroservice.model.Schedule;
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
    void createSchedule() throws Exception {
        NewScheduleRequest newScheduleRequest = new NewScheduleRequest();
        newScheduleRequest.setDepartureDateTime("2023-01-01T10:00:00");
        newScheduleRequest.setArrivalDateTime("2023-01-01T12:00:00");
        newScheduleRequest.setTrainId(1);
        newScheduleRequest.setRouteId(1);

        Schedule schedule = new Schedule();
        schedule.setScheduleId(1);

        Mockito.when(scheduleService.createSchedule(Mockito.any(NewScheduleRequest.class))).thenReturn(schedule);

        mockMvc.perform(post("/schedule-api/schedule")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(newScheduleRequest)))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isCreated())  // Update this line to expect 201 instead of 200
                .andExpect(jsonPath("$.scheduleId").value(1));
    }

    @Test
    void getScheduleById() throws Exception {
        int scheduleId = 1;
        Schedule schedule = new Schedule();
        schedule.setScheduleId(scheduleId);

        Mockito.when(scheduleService.getScheduleById(scheduleId)).thenReturn(schedule);

        mockMvc.perform(get("/schedule-api/schedule/{scheduleId}", scheduleId))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.scheduleId").value(scheduleId));
    }

    @Test
    void getAllSchedules() throws Exception {
        Schedule schedule1 = new Schedule();
        schedule1.setScheduleId(1);

        Schedule schedule2 = new Schedule();
        schedule2.setScheduleId(2);

        Mockito.when(scheduleService.getAllSchedules()).thenReturn(Arrays.asList(schedule1, schedule2));

        mockMvc.perform(get("/schedule-api/schedule"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2));
    }

    @Test
    void updateSchedule() throws Exception {
        Schedule updatedSchedule = new Schedule();
        updatedSchedule.setScheduleId(1);

        Mockito.when(scheduleService.updateSchedule(Mockito.any(Schedule.class))).thenReturn(updatedSchedule);

        mockMvc.perform(put("/schedule-api/schedule")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updatedSchedule)))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.scheduleId").value(1));
    }

    @Test
    void deleteScheduleById() throws Exception {
        int scheduleId = 1;
        Mockito.when(scheduleService.deleteScheduleById(scheduleId)).thenReturn("Schedule deleted successfully");

        mockMvc.perform(delete("/schedule-api/schedule/{id}", scheduleId))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").value("Schedule deleted successfully"));
    }
}
