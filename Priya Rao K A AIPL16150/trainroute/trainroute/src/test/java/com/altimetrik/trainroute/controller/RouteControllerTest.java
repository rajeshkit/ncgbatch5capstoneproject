package com.altimetrik.trainroute.controller;
import com.altimetrik.trainroute.model.Route;
import com.altimetrik.trainroute.service.RouteService;
import com.altimetrik.trainroute.service.RouteServiceImpl;
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
import java.util.Arrays;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(RouteController.class)
class RouteControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private RouteService routeService;
    @Autowired
    private ObjectMapper objectMapper;



    @Test
    void addRoute() throws Exception {
        Route r1 = Route.builder()
                .routeId(100).Source("Los Angeles")
                .Destination("New York").totalKilometers(34543).build();
        Mockito.when(routeService.addRoute(r1)).thenReturn(r1);
        mockMvc.perform(post("/route-api/route")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writerWithDefaultPrettyPrinter()
                                .writeValueAsString(r1)))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.routeId").value(100));

    }

    @Test
    void getAllRoutes() throws Exception {
        Route r1 = Route.builder()
                .routeId(100).Source("Los Angeles")
                .Destination("New York").totalKilometers(34543).build();
        Route r2 = Route.builder()
                .routeId(101).Source("Kashmir")
                .Destination("KanyaKumari").totalKilometers(6867).build();
        Mockito.when(routeService.getAllRoute()).thenReturn(Arrays.asList(r1,r2));
        mockMvc.perform(get("/route-api/route"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2));
    }

    @Test
    void getRouteId() {
    }

    @Test
    void updateRoute() {
    }

    @Test
    void deleteRouteId() {
    }
}