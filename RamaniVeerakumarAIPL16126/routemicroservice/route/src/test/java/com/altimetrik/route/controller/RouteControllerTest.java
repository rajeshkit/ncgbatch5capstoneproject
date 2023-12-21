package com.altimetrik.route.controller;

import com.altimetrik.route.contoller.RouteContoller;
import com.altimetrik.route.model.Route;
import com.altimetrik.route.service.RouteService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import com.fasterxml.jackson.databind.ObjectMapper;

import static org.mockito.Mockito.when;
import static org.springframework.mock.http.server.reactive.MockServerHttpRequest.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import java.util.Arrays;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(RouteContoller.class)
 class RouteControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private RouteService routeService;
    @Autowired
    private ObjectMapper objectMapper;
    @Test
    void addRoute() throws Exception {
        Route route = Route.builder().routeId(1).source("chennai").destination("bengaluru")
                .totalKms(200).build();
        Mockito.when(routeService.addRoute(route)).thenReturn(route);
        mockMvc.perform(post("/route/route")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writerWithDefaultPrettyPrinter()
                                .writeValueAsString(route)))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.routeId").value(1));
    }
    @Test
    void getAllRoutes() throws Exception {
        Route route = Route.builder().routeId(1).source("chennai").destination("bengaluru")
                .totalKms(200).build();
        Route route1 = Route.builder().routeId(2).source("dindigul").destination("bengaluru")
                .totalKms(400).build();
        Mockito.when(routeService.getAllRoutes()).thenReturn(Arrays.asList(route,route1));
        mockMvc.perform(get("/route/route"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2));
    }
    @Test
    void getRouteById() throws Exception {
        int routeId = 100;

        Route route = Route.builder()
                .routeId(routeId)
                .source("Dindigul")
                .destination("bengaluru")
                .totalKms(600)
                .build();

        when(routeService.getRouteById(routeId)).thenReturn(route);

        mockMvc.perform(get("/route/{routeId}",routeId))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.routeId").value(routeId))
                .andExpect(jsonPath("$.source").value("Dindigul"))
                .andExpect(jsonPath("$.destination").value("bengaluru"))
                .andExpect(jsonPath("$.totalKms").value(600));

    }

    @Test
    void updateRoute() throws Exception {
        Route updatedRoute = Route.builder().routeId(100).source("chennai").destination("madurai").totalKms(700)
                .build();

        Mockito.when(routeService.updateRoute(Mockito.any(Route.class))).thenReturn(updatedRoute);

        mockMvc.perform(MockMvcRequestBuilders.put("/route/route")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writerWithDefaultPrettyPrinter()
                                .writeValueAsString(updatedRoute)))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.source").value("chennai"))
                .andExpect(jsonPath("$.totalKms").value(700));
    }

    @Test
    void deleteRouteById() throws Exception {
        int routeId = 100;

        Mockito.when(routeService.deleteRouteById(routeId)).thenReturn("Route deleted successfully");

        mockMvc.perform(delete("/route/route/{id}",routeId))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("Route deleted successfully"));
    }
}

