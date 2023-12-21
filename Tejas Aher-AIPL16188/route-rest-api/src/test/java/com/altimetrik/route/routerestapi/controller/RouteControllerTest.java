package com.altimetrik.route.routerestapi.controller;

import com.altimetrik.route.routerestapi.model.Route;
import com.altimetrik.route.routerestapi.service.RouteService;
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

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(RouteController.class)
class RouteControllerTest {

    @MockBean
    private RouteService routeServiceImpl;
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;


    @Test
    void addRoute() throws Exception {

        Route route = Route.builder().routeId("1").source("Nashik").destination("Pune").totalKmsDistance(225).build();

        String jsonRoute = objectMapper.writeValueAsString(route);

        mockMvc.perform(MockMvcRequestBuilders.post("http://localhost:8086/route-api/route").contentType(MediaType.APPLICATION_JSON).content(jsonRoute)).andDo(MockMvcResultHandlers.print()) // Print the request and response for debugging
                .andExpect(MockMvcResultMatchers.status().isCreated()).andExpect(MockMvcResultMatchers.header().string("Location", "http://localhost:8086/route-api/route/1"));
    }

    @Test
    void getAllRoute() throws Exception {

        Route route = Route.builder().routeId("1").source("Nashik").destination("Pune").totalKmsDistance(225).build();

        Route nextRoute = Route.builder().routeId("2").source("Pune").destination("banglore").totalKmsDistance(786).build();

        String jsonTrain = objectMapper.writeValueAsString(route);
        Mockito.when(routeServiceImpl.getAllRoute()).thenReturn(Arrays.asList(route, nextRoute));

        mockMvc.perform(get("/route-api/route")).andDo(MockMvcResultHandlers.print()).andExpect(status().isOk()).andExpect(jsonPath("$.length()").value(2));
    }

    @Test
    void getRouteById() throws Exception {

        Route route = Route.builder().routeId("1").source("Nashik").destination("Pune").totalKmsDistance(225).build();

        Mockito.when(routeServiceImpl.getRouteById(route.getRouteId())).thenReturn(route);

        mockMvc.perform(get("/route-api/route/{id}", route.getRouteId())).andDo(MockMvcResultHandlers.print()).andExpect(status().isOk()).andExpect(jsonPath("$.routeId").value(route.getRouteId()));

    }

    @Test
    void updateRoute() throws Exception {
        Route route = Route.builder().routeId("1").source("Banglore").destination("Pune").totalKmsDistance(786).build();

        Mockito.when(routeServiceImpl.updateRoute(route, route.getRouteId())).thenReturn(route);

        mockMvc.perform(put("/route-api/route/{id}", route.getRouteId()).contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(route))).andDo(MockMvcResultHandlers.print()).andExpect(status().isOk());

    }

    @Test
    void deleteRouteByID() throws Exception {

        Route route = Route.builder().routeId("100").source("Nashik").destination("Lonavala").totalKmsDistance(250).build();

        Mockito.when(routeServiceImpl.deleteRouteByID(route.getRouteId())).thenReturn(String.valueOf(route));

        mockMvc.perform(delete("/route-api/route/{id}", route.getRouteId())).andDo(MockMvcResultHandlers.print()).andExpect(status().isOk());
    }
}