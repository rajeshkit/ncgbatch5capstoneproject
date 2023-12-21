package com.route.routemicroservice.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.route.routemicroservice.exception.RouteIdNotExistException;
import com.route.routemicroservice.model.Route;
import com.route.routemicroservice.service.RouteService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.event.annotation.PrepareTestInstance;
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

    @Test
    void addRoute() throws Exception {
        Route route=Route.builder().routeId(905).source("chennai")
                .destination("hyderabad").totalKms(800).build();

        Mockito.when(routeService.addRoute(route)).thenReturn(route);
        mockMvc.perform(post("/route-api/route")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writerWithDefaultPrettyPrinter()
                                .writeValueAsString(route)))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.routeId").value(905));
    }

    @Test
    void getAllRoutes() throws Exception {
        Route route=Route.builder().routeId(905).source("chennai")
                .destination("hyderabad").totalKms(800).build();
        Route route1=Route.builder().routeId(910).source("Hyderabad")
                .destination("Delhi").totalKms(1400).build();
        Mockito.when(routeService.getAllRoutes()).thenReturn(Arrays.asList(route,route1));
        mockMvc.perform(get("/route-api/route"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2));

    }

    @Test
    void getRouteByRouteId() throws Exception {
        Route route=Route.builder().routeId(905).source("chennai")
                .destination("hyderabad").totalKms(800).build();
        Mockito.when(routeService.getRouteByRouteId(905)).thenReturn(route);
        mockMvc.perform(get("/route-api/route/{routeId}",905))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.routeId").value(905));

    }

    @Test
    void getRouteByRouteIdWithException() throws Exception {
        Route route=Route.builder().routeId(905).source("chennai")
                .destination("hyderabad").totalKms(800).build();
        Mockito.when(routeService.getRouteByRouteId(910)).thenThrow(new RouteIdNotExistException("RouteId Not Exist"));
        mockMvc.perform(get("/route-api/route/{routeId}",910))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.message").value("RouteId Not Exist"));
    }



    @Test
    void updateRoute() throws Exception {
        Route route=Route.builder().routeId(905).source("chennai")
                .destination("hyderabad").totalKms(800).build();
        Mockito.when(routeService.updateRoute(route)).thenReturn(route);
        mockMvc.perform(put("/route-api/route")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writerWithDefaultPrettyPrinter()
                                .writeValueAsString(route)))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.routeId").value(905));


    }
    @Test
    void updateRouteWithException() throws Exception {
        Route route=Route.builder().routeId(905).source("chennai")
                .destination("hyderabad").totalKms(800).build();
        Mockito.when(routeService.updateRoute(route)).thenThrow(new RouteIdNotExistException("RouteId Not Exist"));
        mockMvc.perform(put("/route-api/route")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writerWithDefaultPrettyPrinter()
                                .writeValueAsString(route)))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.message").value("RouteId Not Exist"));



    }

    @Test
    void deleteRouteByRouteId() throws Exception {
        Route route=Route.builder().routeId(905).source("chennai")
                .destination("hyderabad").totalKms(800).build();
        Mockito.when(routeService.deleteRouteByRouteId(905)).thenReturn("Route deleted successfully");
        mockMvc.perform(delete("/route-api/route/{routeId}",905))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Route deleted successfully")));

    }

    @Test
    void deleteRouteByRouteIdWithException() throws Exception {
        Route route=Route.builder().routeId(905).source("chennai")
                .destination("hyderabad").totalKms(800).build();
        Mockito.when(routeService.deleteRouteByRouteId(910)).thenThrow(new RouteIdNotExistException("RouteId Not Exist"));
        mockMvc.perform(delete("/route-api/route/{routeId}",910))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.message").value("RouteId Not Exist"));

    }
}