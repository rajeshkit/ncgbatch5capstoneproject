package com.booking_details.schedule.controller;

import com.booking_details.route.model.RouteModel;
import com.booking_details.schedule.model.ScheduleModel;
import com.booking_details.schedule.model.ScheduleRequest;
import com.booking_details.schedule.service.ScheduleService;
import com.booking_details.train.model.TrainModel;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.core.StringContains.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ScheduleController.class)
//@ComponentScan(basePackages = {"com.booking_details.schedule.controller"})
class ScheduleControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private ScheduleService scheduleService;
    @Autowired
    private ObjectMapper objectMapper;

    private static ScheduleModel scheduleModel1;
    private static ScheduleModel scheduleModel2;
    private static List<ScheduleModel> scheduleModelList;

    private static TrainModel trainModel1;

    private static RouteModel routeModel1;

    private static ScheduleRequest scheduleRequest;

    @BeforeAll
    static void setupModels(){

        trainModel1 = new TrainModel(123,"testTrain",34.5,3,35,4,45,1,10);
        routeModel1 = new RouteModel(17,"chennai","hyderabad",23.87);
        scheduleRequest = new ScheduleRequest(29, LocalDateTime.of(2023,12,11,12,00,00), LocalDateTime.of(2023,12,11,8,00,00),123,17);
        scheduleModel1 = new ScheduleModel(29, LocalDateTime.of(2023,12,11,12,00,00), LocalDateTime.of(2023,12,11,8,00,00),trainModel1,routeModel1);
        scheduleModel2 = new ScheduleModel(59, LocalDateTime.of(2023,11,10,14,00,00), LocalDateTime.of(2023,11,10,21,00,00),trainModel1,routeModel1);
        scheduleModelList = new ArrayList<>();
        scheduleModelList.add(scheduleModel1);
        scheduleModelList.add(scheduleModel2);

    }

    @Test
    void addScheduleDetailsTest() throws Exception {
        Mockito.when(scheduleService.addScheduleDetails(scheduleRequest)).thenReturn(scheduleModel1);
        mockMvc.perform(post("/schedule-microservice/schedule")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writerWithDefaultPrettyPrinter()
                                .writeValueAsString(scheduleRequest)))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.scheduleId").value(29));
    }

    @Test
    void getScheduleDetailsTest() throws Exception {
        Mockito.when(scheduleService.getScheduleDetails()).thenReturn(scheduleModelList);
        mockMvc.perform(get("/schedule-microservice/schedule"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2));
    }

    @Test
    void getScheduleDetailsByIdTest() throws Exception{
        Mockito.when(scheduleService.getScheduleDetailsById(29L)).thenReturn(scheduleModel1);
        mockMvc.perform(get("/schedule-microservice/schedule/{id}",29))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.scheduleId").value(29));
    }

    @Test
    void updateScheduleDetailsTest() throws Exception{
        Mockito.when(scheduleService.updateScheduleDetails(scheduleRequest)).thenReturn(scheduleModel1);
        mockMvc.perform(put("/schedule-microservice/schedule")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writerWithDefaultPrettyPrinter()
                                .writeValueAsString(scheduleRequest)))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.train.trainNumber").value(123));
    }

    @Test
    void deleteScheduleDetailsTest() throws Exception{
        Mockito.when(scheduleService.deleteScheduleDetails(29L)).thenReturn("Deleted..");
        mockMvc.perform(delete("/schedule-microservice/schedule/{id}",29))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Deleted..")));
    }

    @Test
    void getScheduleDetailsByTrainIdTest() throws Exception{
        Mockito.when(scheduleService.getScheduleDetailsByTrainId(123L)).thenReturn(scheduleModel1);
        mockMvc.perform(get("/schedule-microservice//train/{trainNumber}",123))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.train.trainNumber").value(123));

    }
}
