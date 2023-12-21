package com.route.routemicroservice.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.route.routemicroservice.model.Route;
import com.route.routemicroservice.service.RouteService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

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
                .routeId(1).source("Jaipur").destination("Delhi").totalKms(1490).build();
        Mockito.when(routeService.addRoute(r1)).thenReturn(r1);
        mockMvc.perform(MockMvcRequestBuilders.post("/route")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writerWithDefaultPrettyPrinter()
                                .writeValueAsString(r1)))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.routeId").exists());
    }

    @Test
    void getAllRoutes() throws Exception {
        Route r1 = Route.builder().routeId(1).source("Jaipur").destination("Delhi").totalKms(1490).build();
        Route r2 = Route.builder().routeId(2).source("Delhi").destination("Mumbai").totalKms(1200).build();

        Mockito.when(routeService.getAllRoutes()).thenReturn(Arrays.asList(r1, r2));

        mockMvc.perform(MockMvcRequestBuilders.get("/route")
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].routeId").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].routeId").value(2));
    }

    @Test
    void getRouteByRouteId() throws Exception {
        int routeId = 1;
        Route route = Route.builder().routeId(routeId).source("Jaipur").destination("Delhi").totalKms(1490).build();

        Mockito.when(routeService.getRouteByRouteId(routeId)).thenReturn(route);

        mockMvc.perform(MockMvcRequestBuilders.get("/route/{routeId}", routeId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.routeId").value(routeId))
                .andExpect(MockMvcResultMatchers.jsonPath("$.source").value("Jaipur"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.destination").value("Delhi"));
    }

    @Test
    void updateRoute() throws Exception {
        Route route = Route.builder().routeId(1).source("Jaipur").destination("Delhi").totalKms(1490).build();

        Mockito.when(routeService.updateRoute(route)).thenReturn(route);

        mockMvc.perform(MockMvcRequestBuilders.put("/route")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(route)))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.routeId").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$.source").value("Jaipur"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.destination").value("Delhi"));
    }

    @Test
    void deleteRouteByRouteId() throws Exception {
        int routeId = 1;

        mockMvc.perform(MockMvcRequestBuilders.delete("/route/{routeId}", routeId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk());

        Mockito.verify(routeService, Mockito.times(1)).deleteRouteByRouteId(routeId);
    }
}