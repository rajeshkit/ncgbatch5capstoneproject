package com.altimetrik.routemicroservice.controller;
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

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


//To Test the methods of TrainController class.
@WebMvcTest(RouteController.class)
class RouteControllerTest {

    @Autowired
    private MockMvc mockMvc ;

    @MockBean
    private RouteService routeService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void addRouteDetail() throws Exception {
        Route r1 = Route.builder()
                .routeId(1).routeSource("Jaipur").routeDestination("Delhi").totalKm(1490).build();

        Mockito.when(routeService.addRouteDetail(r1)).thenReturn(r1);

        mockMvc.perform(MockMvcRequestBuilders.post("/route-api/route")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writerWithDefaultPrettyPrinter()
                        .writeValueAsString(r1)))
                        .andDo(MockMvcResultHandlers.print())
                        .andExpect(MockMvcResultMatchers.status().isOk())
                        .andExpect(MockMvcResultMatchers.jsonPath("$.routeId").exists());
    }

    @Test
    void getRouteById() throws Exception
    {
        int routeId = 1;
        Route r1 = Route.builder().routeId(routeId).routeSource("Jaipur").routeDestination("Darjeeling").totalKm(1490).build();
        Mockito.when(routeService.getRouteById(routeId)).thenReturn(r1);

        mockMvc.perform(MockMvcRequestBuilders.get("/route-api/route/{id}", routeId))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.routeId").value(routeId));
    }

    @Test
    void updateRouteById() throws Exception
    {
        Route r1 = Route.builder().routeId(1).routeSource("Jaipur").routeDestination("Darjeeling").totalKm(1490).build();
        Mockito.when(routeService.updateRouteById(r1)).thenReturn(r1);

        mockMvc.perform(MockMvcRequestBuilders.put("/route-api/route")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writerWithDefaultPrettyPrinter()
                        .writeValueAsString(r1)))
                        .andDo(MockMvcResultHandlers.print())
                        .andExpect(MockMvcResultMatchers.status().isOk())
                        .andExpect(MockMvcResultMatchers.jsonPath("$.routeId").exists());
    }

    @Test
    void getAllRouteDetails() throws Exception {
        Route r1 = Route.builder().routeId(1).routeSource("Delhi").routeDestination("Cochin").totalKm(1490).build();
        Route r2 = Route.builder().routeId(2).routeSource("Jaipur").routeDestination("Kolkata").totalKm(1490).build();

        Mockito.when(routeService.getAllRouteDetails()).thenReturn(Arrays.asList(r1,r2));

        mockMvc.perform(MockMvcRequestBuilders.get("/route-api/route"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.length()").value(2));
    }

    @Test
    void deleteRouteById() throws Exception {
        int routeId = 1;
        Mockito.when(routeService.deleteRouteById(routeId)).thenReturn("Route deleted successfully.");

        mockMvc.perform(MockMvcRequestBuilders.delete("/route-api/route/{id}", routeId))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("Route deleted successfully."));
    }
}