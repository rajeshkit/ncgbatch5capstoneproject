package com.altimetrik.schedulemicroservice.controller;
import com.altimetrik.schedulemicroservice.model.NewScheduleRequest;
import com.altimetrik.schedulemicroservice.model.Route;
import com.altimetrik.schedulemicroservice.model.Schedule;
import com.altimetrik.schedulemicroservice.model.Train;
import com.altimetrik.schedulemicroservice.service.ScheduleService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import static org.assertj.core.internal.bytebuddy.matcher.ElementMatchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.mockito.Mockito.*;
@Nested
@WebMvcTest(ScheduleController.class)
class ScheduleControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    @MockBean
    private ScheduleService scheduleService;

    @Test
    void testGetScheduleById() throws Exception {
        long scheduleId = 1;
        Schedule schedule = Schedule.builder()
                .scheduleId(scheduleId)
                .departureDateTime(Timestamp.valueOf(LocalDateTime.now()))
                .arrivalDateTime(Timestamp.valueOf(LocalDateTime.now().plusHours(2)))
                .train(String.valueOf(new Train()))
                .route(String.valueOf(new Route()))
                .build();

        when(scheduleService.getScheduleById(scheduleId)).thenReturn(schedule);

        mockMvc.perform(MockMvcRequestBuilders.get("/schedule-api/schedule/{scheduleId}", scheduleId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.scheduleId").value(scheduleId));
    }

    @Test
    void testGetAllSchedules() throws Exception {
        Schedule schedule1 = Schedule.builder()
                .scheduleId(1)
                .departureDateTime(Timestamp.valueOf(LocalDateTime.now()))
                .arrivalDateTime(Timestamp.valueOf(LocalDateTime.now().plusHours(2)))
                .train(String.valueOf(new Train()))
                .route(String.valueOf(new Route()))
                .build();

        Schedule schedule2 = Schedule.builder()
                .scheduleId(2)
                .departureDateTime(Timestamp.valueOf(LocalDateTime.now().plusDays(1)))
                .arrivalDateTime(Timestamp.valueOf(LocalDateTime.now().plusDays(1).plusHours(2)))
                .train(String.valueOf(new Train()))
                .route(String.valueOf(new Route()))
                .build();

        List<Schedule> scheduleList = Arrays.asList(schedule1, schedule2);

        when(scheduleService.getAllSchedules()).thenReturn(scheduleList);

        mockMvc.perform(MockMvcRequestBuilders.get("/schedule-api/schedule"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$").isNotEmpty())
                .andExpect(jsonPath("$.length()").value(scheduleList.size()));
    }

    @Test
    void testDeleteScheduleById() throws Exception {
        long scheduleId = 1;
        mockMvc.perform(MockMvcRequestBuilders.delete("/schedule-api/schedule/{scheduleId}", scheduleId))
                .andExpect(status().isOk());
        verify(scheduleService, times(1)).deleteScheduleById(scheduleId);
    }


    @Test
    void testAddSchedule() throws Exception {
        NewScheduleRequest newScheduleRequest = NewScheduleRequest.builder()
                .departureDateTime("2023-01-01T12:00:00")
                .arrivalDateTime("2023-01-01T15:00:00")
                .trainNumber(123)
                .routeId(456)
                .build();
        Schedule addedSchedule = Schedule.builder()
                .scheduleId(1L)
                .departureDateTime(Timestamp.valueOf(LocalDateTime.parse(newScheduleRequest.getDepartureDateTime())))
                .arrivalDateTime(Timestamp.valueOf(LocalDateTime.parse(newScheduleRequest.getArrivalDateTime())))
                .train(null)
                .route(null)
                .build();
        when(scheduleService.addSchedule(any(NewScheduleRequest.class))).thenReturn(addedSchedule);
        mockMvc.perform(post("/schedule-api/schedule")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(newScheduleRequest)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.scheduleId").value(1L))
                .andExpect(jsonPath("$.arrivalDateTime").isNotEmpty());
        verify(scheduleService, times(1)).addSchedule(eq(newScheduleRequest));
    }

    @Test
    void testUpdateSchedule() throws Exception {
        long scheduleId = 1L;
        NewScheduleRequest updatedScheduleRequest = NewScheduleRequest.builder()
                .departureDateTime("2023-01-01T12:00:00")
                .arrivalDateTime("2023-01-01T15:00:00")
                .trainNumber(123)
                .routeId(456)
                .build();
        Schedule updatedSchedule = Schedule.builder()
                .scheduleId(scheduleId)
                .departureDateTime(Timestamp.valueOf(LocalDateTime.parse(updatedScheduleRequest.getDepartureDateTime())))
                .arrivalDateTime(Timestamp.valueOf(LocalDateTime.parse(updatedScheduleRequest.getArrivalDateTime())))
                .train(null)
                .route(null)
                .build();
        when(scheduleService.updateSchedule(eq(scheduleId), any(NewScheduleRequest.class))).thenReturn(updatedSchedule);
        mockMvc.perform(put("/schedule-api/schedule/{scheduleId}", scheduleId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updatedScheduleRequest)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.scheduleId").value(scheduleId))
                .andExpect(jsonPath("$.arrivalDateTime").isNotEmpty());


        verify(scheduleService, times(1)).updateSchedule(eq(scheduleId), eq(updatedScheduleRequest));
    }

}