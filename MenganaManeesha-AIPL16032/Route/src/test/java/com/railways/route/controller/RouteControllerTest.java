package com.railways.route.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.railways.route.exception.RouteIdExistsException;
import com.railways.route.exception.RouteNotFindException;
import com.railways.route.model.Route;
import com.railways.route.service.RouteService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Arrays;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
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
    void addRoute() throws Exception, RouteIdExistsException {
        Route r=Route.builder().routeId(1).source("HYD").destination("BNG").totalKms(500).build();
        Mockito.when(routeService.addRoute(r)).thenReturn(r);
        mockMvc.perform(post("/route-api/route")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writerWithDefaultPrettyPrinter()
                                .writeValueAsString(r)))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.routeId").value(1));

    }

    @Test
    void getRoute() throws Exception {
        Route r1=Route.builder().routeId(1).source("HYD").destination("BNG").totalKms(500).build();
        Route r2=Route.builder().routeId(2).source("BNG").destination("KNR").totalKms(800).build();
        Mockito.when(routeService.getRoute()).thenReturn(Arrays.asList(r1,r2));
        mockMvc.perform(get("/route-api/route"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2));
    }

    @Test
    void getRouteById() throws Exception {
        Route r=Route.builder().routeId(1).source("HYD").destination("BNG").totalKms(500).build();
        Mockito.when(routeService.getRouteById(1L)).thenReturn(r);
        mockMvc.perform(get("/route-api/route/{id}",1))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.routeId").value(1));
    }
    @Test
    void getRouteByIdWithException() throws Exception {
        Route r = Route.builder().routeId(1).source("HYD").destination("BNG").totalKms(500).build();
        Mockito.when(routeService.getRouteById(1L)).thenThrow(new RouteNotFindException("No Route Found"));
        mockMvc.perform(get("/route-api/route/{id}", 1))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.message").value("No Route Found"));

    }

    @Test
    void updateRoute() throws Exception {
        Route r=Route.builder().routeId(1).source("HYD").destination("BNG").totalKms(500).build();
        Mockito.when(routeService.updateRoute(r)).thenReturn(r);
        mockMvc.perform(put("/route-api/route")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writerWithDefaultPrettyPrinter()
                                .writeValueAsString(r)))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.routeId").value(1));
    }
    @Test
    void updateRouteWithException() throws Exception {
       Route r=Route.builder().routeId(1).source("HYD").destination("BNG").totalKms(500).build();
        Mockito.when(routeService.updateRoute(r)).thenThrow(new RouteNotFindException("Route not found"));
        mockMvc.perform(put("/route-api/route")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writerWithDefaultPrettyPrinter()
                                .writeValueAsString(r)))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.message").value("Route not found"));
    }

    @Test
    void deleteRoute() throws Exception {
       Route r=Route.builder().routeId(1).source("HYD").destination("BNG").totalKms(500).build();
        Mockito.when(routeService.deleteRoute(1L)).thenReturn("Route Deleted Successfully");
        mockMvc.perform(delete("/route-api/route/{id}",1))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("Route Deleted Successfully"));
    }

    @Test
    void deleteRouteWithException() throws Exception
    {
        Route r=Route.builder().routeId(1).source("HYD").destination("BNG").totalKms(500).build();
        Mockito.when(routeService.deleteRoute(2L)).thenThrow(new RouteNotFindException("Route Not found"));
        mockMvc.perform(delete("/route-api/route/{id}",2L))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.message").value("Route Not found"));
    }
}

