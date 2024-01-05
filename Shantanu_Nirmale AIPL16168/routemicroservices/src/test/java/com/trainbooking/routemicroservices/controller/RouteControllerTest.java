package com.trainbooking.routemicroservices.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.trainbooking.routemicroservices.model.Route;
import com.trainbooking.routemicroservices.service.RouteService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import java.util.Arrays;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(RouteController.class)
class RouteControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    RouteService routeService;

    @Autowired
    ObjectMapper objectMapper;

    @Test
    void addRoute() throws Exception {
        Route route = Route.builder()
                .routeId(123).source("NED").destination("CSMT").totalKms(690).build();

        Mockito.when(routeService.addRoute(route)).thenReturn(route);
        mockMvc.perform(post("/route-api/route")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(route)))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.routeId").value(123));
    }

    @Test
    void getAllRoutes() throws Exception{
        Route route1 = Route.builder()
                .routeId(123).source("NED").destination("CSMT").totalKms(690).build();
        Route route2 = Route.builder()
                .routeId(1234).source("NANDED").destination("PUNE").totalKms(445).build();

        Mockito.when(routeService.getAllRoutes()).thenReturn(Arrays.asList(route1,route2));
        mockMvc.perform(get("/route-api/route"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2));
    }

    @Test
    void getRouteByRouteId() throws  Exception{
        Route route1 = Route.builder()
                .routeId(123).source("NED").destination("CSMT").totalKms(690).build();

        Mockito.when(routeService.getRouteByRouteId(123)).thenReturn(route1);
        mockMvc.perform(get("/route-api/route/123")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(route1)))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.routeId").value(123));
    }

    @Test
    void deleteRouteByRouteId() throws Exception {
        Mockito.when(routeService.deleteRouteByRouteId(123)).thenReturn("Route Details Deleted Successfully !!!");
        mockMvc.perform(MockMvcRequestBuilders.delete("/route-api/route/{routeId}", 123))
                .andExpect(status().isOk());
    }

}
