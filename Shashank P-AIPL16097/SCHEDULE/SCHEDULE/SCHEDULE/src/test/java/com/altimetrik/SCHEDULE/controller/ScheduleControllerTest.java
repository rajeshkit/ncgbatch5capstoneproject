package com.altimetrik.SCHEDULE.controller;

import com.altimetrik.SCHEDULE.exception.ScheduleIdNotExistsException;
import com.altimetrik.SCHEDULE.model.Schedule;
import com.altimetrik.SCHEDULE.model.ScheduleRequest;
import com.altimetrik.SCHEDULE.service.ScheduleService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ScheduleController.class)

public class ScheduleControllerTest {


    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    @MockBean
    private ScheduleService scheduleService;

    @Test
    void createSchedule() throws Exception {
        ScheduleRequest schedule2 = ScheduleRequest.builder()
                .trainId(1)
                .routeId(1)
                .arrivalDateTime(LocalDateTime.now())
                .departureDateTime(LocalDateTime.now())
                .build();
        Schedule schedule = Schedule.builder().scheduleId(1).build();
        when(scheduleService.createSchedule(schedule2)).thenReturn(schedule);

        mockMvc.perform(post("/schedule-train")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(schedule2)))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.scheduleId").value(1));
    }


    @Test
    void  deleteScheduleById() throws ScheduleIdNotExistsException {
        int scheduleIdToDelete = 101;

        when(scheduleService.deleteScheduleById(scheduleIdToDelete)).thenReturn("deleted successfully");

        String deletionMessage = scheduleService.deleteScheduleById(scheduleIdToDelete);

        assertEquals("deleted successfully", deletionMessage);
    }

    @Test
    void getAllSchedules() {
        List<Schedule> routeList = Arrays.asList(
                Schedule.builder().scheduleId(101).departureDateTime(LocalDateTime.parse("2023-04-02T12:00:00")).arrivalDateTime(LocalDateTime.parse("2023-04-02T14:00:00")).build()
        );

        when(scheduleService.getAllSchedules()).thenReturn(routeList);

        List<Schedule> retrievedRoutes = scheduleService.getAllSchedules();

        assertEquals(routeList.size(), retrievedRoutes.size());
    }

    @Test

    void getScheduleById() throws ScheduleIdNotExistsException {
        int scheduleId = 101;
        Schedule schedule = Schedule.builder()
                .scheduleId(scheduleId)
                .departureDateTime(LocalDateTime.parse("2023-04-02T12:00:00"))
                .arrivalDateTime(LocalDateTime.parse("2023-04-02T14:00:00"))
                .build();

        when(scheduleService.getScheduleById(scheduleId)).thenReturn((schedule));

        Schedule retrievedRoute = scheduleService.getScheduleById(scheduleId);

        assertEquals(schedule, retrievedRoute);
    }

    @Test
    void updateSchedule() throws ScheduleIdNotExistsException {
        Schedule scheduleToUpdate = Schedule.builder()
                .scheduleId(100)
                .departureDateTime(LocalDateTime.parse("2023-04-02T12:00:00"))
                .arrivalDateTime(LocalDateTime.parse("2023-04-02T14:00:00"))
                .build();

        when(scheduleService.updateSchedule(scheduleToUpdate)).thenReturn((scheduleToUpdate));

        Schedule updatedRoute = scheduleService.updateSchedule(scheduleToUpdate);

        assertEquals(scheduleToUpdate, updatedRoute);
    }
}
