package com.altimetrik.trainschedule.service;
import com.altimetrik.trainschedule.controller.ScheduleController;
import com.altimetrik.trainschedule.model.Route;
import com.altimetrik.trainschedule.model.Schedule;
import com.altimetrik.trainschedule.model.ScheduleRequest;
import com.altimetrik.trainschedule.model.Train;
import com.altimetrik.trainschedule.repository.ScheduleRepository;
import com.altimetrik.trainschedule.service.ScheduleService;
import com.altimetrik.trainschedule.service.ScheduleServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.client.RestTemplate;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
@ExtendWith(MockitoExtension.class)
class ScheduleServiceImplTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;
    @Mock
    private ScheduleRepository scheduleRepository;
    @InjectMocks
    private ScheduleServiceImpl scheduleServiceImpl;
    @Mock
    private RestTemplate restTemplate;
    @Test
    void addSchedule() {
        ScheduleRequest scheduleRequest = new ScheduleRequest();
        scheduleRequest.setTrainNumber(100);
        scheduleRequest.setRouteId(100);
        scheduleRequest.setDepartureDateTime(new Date());
        scheduleRequest.setArrivalDateTime(new Date());
        Train mockTrain = new Train();
        mockTrain.setTrainNumber(100);
        mockTrain.setTrainName("Vandhe Bharat");
        mockTrain.setAcCoaches(4);
        mockTrain.setSleeperCoaches(3);
        mockTrain.setGeneralCoaches(12);
        mockTrain.setTotalAcCoachSeats(23);
        mockTrain.setTotalSleeperCoachSeats(13);
        mockTrain.setTotalGeneralCoachSeats(3);
        mockTrain.setTotalKilometers(245);

        Route mockRoute = new Route();
        mockRoute.setRouteId(100);
        mockRoute.setSource("Bangalore");
        mockRoute.setDestination("Chikmagalur");
        mockRoute.setTotalKilometers(256);

        when(restTemplate.getForObject(eq("http://localhost:8181/train/100"), eq(Train.class))).thenReturn(mockTrain);
        when(restTemplate.getForObject(eq("http://localhost:8383/route/100"), eq(Route.class))).thenReturn(mockRoute);

        Schedule result = scheduleServiceImpl.addSchedule(scheduleRequest);
        assertEquals(1, result.getScheduleId());
        assertEquals(mockTrain.toString(), result.getTrain());
        assertEquals(mockRoute.toString(), result.getRoute());


    }

}