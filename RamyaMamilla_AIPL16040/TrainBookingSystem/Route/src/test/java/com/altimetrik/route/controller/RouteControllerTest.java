package com.altimetrik.route.controller;

import com.altimetrik.route.exception.RouteIDNotFoundException;
import com.altimetrik.route.model.Route;
import com.altimetrik.route.service.RouteService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import java.util.Arrays;

import static org.hamcrest.core.StringContains.containsString;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(RouteController.class)
class RouteControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private RouteService routeService;
    @Autowired
    private ObjectMapper objectMapper;
    @InjectMocks
    private RouteController routeController;

    Route r1=Route.builder().routeId(100)
            .trainNo(10001).totalKm(700)
            .source("HYD").destination("SK").build();
    Route r2=Route.builder().routeId(101)
            .trainNo(10002).totalKm(800)
            .source("SK").destination("HYD").build();

    @Test
    void addRoute() throws Exception {
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
    void viewAllRoutes() throws Exception {
        Mockito.when(routeService.viewAllRoutes()).thenReturn(Arrays.asList(r1,r2));
        assertEquals(2,routeService.viewAllRoutes().size());
        mockMvc.perform(get("/route-api/route"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2));
    }

    @Test
    void getRouteById() throws Exception {
        Mockito.when(routeService.getRouteById(101)).thenReturn(r2);
        mockMvc.perform(get("/route-api/route/{id}",101))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.routeId").value(101));
    }

    @Test
    void getRouteByIdWithException() throws Exception {
        Mockito.when(routeService.getRouteById(101)).thenThrow(new RouteIDNotFoundException("Route ID Not Found!"));
        mockMvc.perform(get("/route-api/route/{id}",101))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.message").value("Route ID Not Found!"));
    }

    @Test
    void updateRouteById() throws Exception {
        Mockito.when(routeService.updateRoute(r2)).thenReturn(r2);
        mockMvc.perform(put("/route-api/route")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writerWithDefaultPrettyPrinter()
                                .writeValueAsString(r2)))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.routeId").value(101));
    }

    @Test
    void updateRouteByIdWithException() throws Exception {
        Mockito.when(routeService.updateRoute(r2)).thenThrow(new RouteIDNotFoundException("Route ID Not Found!"));
        mockMvc.perform(put("/route-api/route")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writerWithDefaultPrettyPrinter()
                                .writeValueAsString(r2)))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.message").value("Route ID Not Found!"));
    }

    @Test
    void deleteRouteById() throws Exception {
        Mockito.when(routeService.deleteRouteById(101)).thenReturn("Route Details Deleted Successfully!");
        mockMvc.perform(delete("/route-api/route/{id}",101))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Route Details Deleted Successfully!")));
    }

    @Test
    void deleteRouteByIdWithException() throws Exception {
        Mockito.when(routeService.deleteRouteById(101)).thenThrow(new RouteIDNotFoundException("Route ID Not Found!"));
        mockMvc.perform(delete("/route-api/route/{id}",101))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.message").value("Route ID Not Found!"));
    }
}