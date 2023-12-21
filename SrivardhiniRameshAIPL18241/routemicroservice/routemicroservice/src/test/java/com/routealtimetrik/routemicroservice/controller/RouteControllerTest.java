package com.routealtimetrik.routemicroservice.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.routealtimetrik.routemicroservice.model.Route;
import com.routealtimetrik.routemicroservice.service.RouteService;
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

public class RouteControllerTest {

    private MockMvc mockMvc;

    @Mock
    private RouteService routeService;

    @InjectMocks
    private RouteController routeController;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(routeController).build();
    }

    @Test
    void addRoute() throws Exception {
        Route route = createSampleRoute();
        when(routeService.addRoute(any(Route.class))).thenReturn(route);

        mockMvc.perform(post("/route-api/route")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(route)))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.routeId").value(route.getRouteId()));
    }

    @Test
    void getAllRoutes() throws Exception {
        when(routeService.getAllRoutes()).thenReturn(List.of(createSampleRoute()));

        mockMvc.perform(get("/route-api/route"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].routeId").value(100));
    }

    @Test
    void getRouteById() throws Exception {
        Route route = createSampleRoute();
        when(routeService.getRouteById(100)).thenReturn(route);

        mockMvc.perform(get("/route-api/route/100"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.routeId").value(100));
    }

    @Test
    void updateRoute() throws Exception {
        Route route = createSampleRoute();
        when(routeService.updateRoute(any(Route.class))).thenReturn(route);

        mockMvc.perform(put("/route-api/route")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(route)))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.routeId").value(route.getRouteId()));
    }

    @Test
    void deleteRouteById() throws Exception {
        when(routeService.deleteRouteById(100)).thenReturn("Route deleted successfully");

        mockMvc.perform(delete("/route-api/route/100"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(content().string("Route deleted successfully"));
    }

    private Route createSampleRoute() {
        return Route.builder()
                .routeId(100)
                .source("SourceA")
                .destination("DestinationB")
                .totalKms(150.5f)
                .build();
    }
}
