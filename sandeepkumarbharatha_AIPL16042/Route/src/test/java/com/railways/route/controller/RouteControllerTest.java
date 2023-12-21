package com.railways.route.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.railways.route.exceptions.RouteIdNotFound;
import com.railways.route.model.Route;
import com.railways.route.services.RouteServices;
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
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(RouteController.class)
class RouteControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private RouteServices routeServices;  //overrides RouteserviceImpl

    @Autowired
    private ObjectMapper objectMapper;  //converts java objects to json

    @InjectMocks
    private RouteController RouteController;

    @Test
    void addRoute() throws Exception {
        Route route = Route.builder().routeId(1234).source("hyd").destination("tpty").totalKms(700).build();
        Mockito.when(routeServices.addRouteDeatils(route)).thenReturn(route);
        mockMvc.perform(post("/route-api/route")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writerWithDefaultPrettyPrinter()
                                .writeValueAsString(route)))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.routeId").value(route.getRouteId()));


    }

    @Test
    void getAllRoutes() throws Exception {
        Route route = Route.builder()
                .routeId(1234).source("hyd").destination("tpty").totalKms(700).build();
        Route route1 = Route.builder()
                .routeId(12345).source("nlg").destination("hyd").totalKms(1700).build();
        Mockito.when(routeServices.getAllRoutes()).thenReturn(Arrays.asList(route, route1));
        assertEquals(2, routeServices.getAllRoutes().size());
        mockMvc.perform(get("/route-api/route"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2));
    }

    @Test
    void getRouteById() throws RouteIdNotFound, Exception {
        Route route = Route.builder()
                .routeId(1234).source("hyd").destination("tpty").totalKms(700).build();
        Mockito.when(routeServices.getRouteById(route.getRouteId())).thenReturn(route);
        mockMvc.perform(get("/route-api/route/{id}", route.getRouteId())
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.routeId").value(route.getRouteId()));

    }

    @Test
    void getRouteByIdWithException() throws Exception {
        Route route = Route.builder()
                .routeId(1234).source("hyd").destination("tpty").totalKms(700).build();
        Mockito.when(routeServices.getRouteById(route.getRouteId())).thenThrow(new RouteIdNotFound("Route ID not found"));
        mockMvc.perform(get("/route-api/route/{Id}", route.getRouteId()))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.message").value("Route ID not found"));
    }

    @Test
    void updateByRouteId() throws Exception {
        ;
        Route route = Route.builder()
                .routeId(1234).source("hyd").destination("tpty").totalKms(700).build();
        Mockito.when(routeServices.updateByRouteId(route)).thenReturn(route);
        mockMvc.perform(put("/route-api/route")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writerWithDefaultPrettyPrinter()
                                .writeValueAsString(route)))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
         .andExpect(jsonPath("$.routeId").value(route.getRouteId()));
    }

    @Test
    void updateByRouteIdWithException() throws Exception {
        Route route = Route.builder()
                .routeId(1234).source("hyd").destination("tpty").totalKms(700).build();
        Mockito.when(routeServices.updateByRouteId(route)).thenThrow(new RouteIdNotFound("Route id not found"));
        mockMvc.perform(put("/route-api/route")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writerWithDefaultPrettyPrinter()
                        .writeValueAsString(route)))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.message").value("Route id not found"));
    }

    @Test
    void deleteByRouteId() throws Exception {
        Route route = Route.builder()
                .routeId(1234).source("hyd").destination("tpty").totalKms(700).build();
        Mockito.when(routeServices.deleteByRouteId(route.getRouteId())).thenReturn(String.valueOf(Optional.empty()));
        mockMvc.perform(delete("/route-api/route/{id}", route.getRouteId())
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk());
    }

    @Test
    void deleteByRouteIdWithException() throws Exception {
        Route route = Route.builder()
                .routeId(1234).source("hyd").destination("tpty").totalKms(700).build();
        Mockito.when(routeServices.deleteByRouteId(route.getRouteId())).thenThrow(new RouteIdNotFound("Route id not found"));
        mockMvc.perform(delete("/route-api/route/{Id}",route.getRouteId()))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.message").value("Route id not found"));
    }
}