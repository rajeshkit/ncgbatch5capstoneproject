package com.altimetrik.routemicroservice.controller;

import com.altimetrik.routemicroservice.exception.RouteIdNotExistsException;
import com.altimetrik.routemicroservice.model.Route;
import com.altimetrik.routemicroservice.service.RouteService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
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
import java.util.Optional;

import static java.util.Optional.of;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
@WebMvcTest(RouteController.class)
class RouteControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    @MockBean
    private RouteService routeService;

    @Test
    void addRoute() throws Exception {
        Route r1=Route.builder()
                .routeId(1)
                .source("Mysuru")
                .destination("Bengaluru")
                .totalKms(150)
                .build();
        Mockito.when(routeService.addRoute(r1)).thenReturn(r1);
        mockMvc.perform(post("/route-api/route")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(r1)))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.routeId").value(1));
    }

    @Test
    void getAllRoute() throws Exception {
        Route r1=Route.builder()
                .routeId(1)
                .source("Mysuru")
                .destination("Bengaluru")
                .totalKms(150)
                .build();
        Route r2=Route.builder()
                .routeId(2)
                .source("Mysuru")
                .destination("Mulyangiri")
                .totalKms(190)
                .build();
        Mockito.when(routeService.getAllRoute()).thenReturn(Arrays.asList(r1,r2));
        mockMvc.perform(MockMvcRequestBuilders.get("/route-api/route"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2));
    }

    @Test
    void getRouteById() throws Exception {
        Route r1=Route.builder()
                .routeId(1)
                .source("Mysuru")
                .destination("Bengaluru")
                .totalKms(150)
                .build();
        Mockito.when(routeService.getRouteById(1)).thenReturn(r1);
        mockMvc.perform(MockMvcRequestBuilders.get("/route-api/route/{id}",1))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.routeId").value(1));
    }

    @Test
    void updateRoute() throws Exception {
        Route existingRoute=Route.builder()
                .routeId(1)
                .source("Mysuru")
                .destination("Mangalore")
                .totalKms(250)
                .build();
        Route updatedRoute=Route.builder()
                .routeId(1)
                .source("Mysuru")
                .destination("Bengaluru")
                .totalKms(150)
                .build();
        Mockito.when(routeService.getRouteById(updatedRoute.getRouteId())).thenReturn(existingRoute);
        Mockito.when(routeService.updateRoute(Mockito.any(Route.class))).thenReturn(updatedRoute);
        mockMvc.perform(MockMvcRequestBuilders.put("/route-api/route")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updatedRoute)))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.routeId").value(updatedRoute.getRouteId()));
    }

    @Test
    void deleteRouteByIdExistingId() throws Exception {
        int routeId = 1;
        Mockito.when(routeService.deleteRouteById(routeId)).thenReturn("Route deleted Successfully");
        mockMvc.perform(delete("/route-api/route/{id}", routeId))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("Route deleted Successfully"));
    }
    @Test
    void deleteRouteByIdNonExistingId() throws Exception {
        int routeId = 2;
        Mockito.when(routeService.deleteRouteById(routeId)).thenReturn("Route does not exist");
        mockMvc.perform(delete("/route-api/route/{id}",routeId))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("Route does not exist"));
    }
}