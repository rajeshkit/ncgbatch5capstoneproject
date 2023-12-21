package com.Booking.route.controller;

import com.Booking.route.customexception.RouteNotFindException;
import com.Booking.route.model.RouteResources;
import com.Booking.route.service.RouteService;
import com.fasterxml.jackson.databind.ObjectMapper;
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
public class RouteControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private RouteService routeService;
    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void addRoute() throws Exception {
        RouteResources r=RouteResources.builder().routeId(100L).source("rajasthan").destination("mumbai").totalkms(500.0).build();
        Mockito.when(routeService.addRoute(r)).thenReturn(r);
        mockMvc.perform(post("/route-api/Route_Table")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writerWithDefaultPrettyPrinter()
                                .writeValueAsString(r)))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.routeId").value(100));

    }

    @Test
    void getRoute() throws Exception {
        RouteResources r1=RouteResources.builder().routeId(100L).source("rajasthan").destination("mumbai").totalkms(500.0).build();
        RouteResources r2=RouteResources.builder().routeId(1234L).source("gujarath").destination("hyd").totalkms(350.0).build();
        Mockito.when(routeService.getRoute()).thenReturn(Arrays.asList(r1,r2));
        mockMvc.perform(get("/route-api/Route_Table"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2));
    }

    @Test
    void getRouteById() throws Exception, RouteNotFindException {
        RouteResources r=RouteResources.builder().routeId(100L).source("rajasthan").destination("mumbai").totalkms(500.0).build();
        Mockito.when(routeService.getRouteById(100L)).thenReturn(r);
        mockMvc.perform(get("/route-api/Route_Table/{id}",100))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.routeId").value(100));
    }
    @Test
    void getRouteByIdWithException() throws Exception, RouteNotFindException {
        RouteResources r = RouteResources.builder().routeId(100L).source("rajasthan").destination("mumbai").totalkms(500.0).build();
        Mockito.when(routeService.getRouteById(100L)).thenThrow(new RouteNotFindException("No Route Find"));
        mockMvc.perform(get("/route-api/Route_Table/{id}", 100))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isNotFound());
    }

    @Test
    void updateRoute() throws Exception, RouteNotFindException {
        RouteResources r=RouteResources.builder().routeId(100L).source("rajasthan").destination("mumbai").totalkms(500.0).build();
        Mockito.when(routeService.updateRoute(r)).thenReturn(r);
        mockMvc.perform(put("/route-api/Route_Table")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writerWithDefaultPrettyPrinter()
                                .writeValueAsString(r)))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.routeId").value(100L));
    }
    @Test
    void updateRouteWithException() throws Exception, RouteNotFindException {
        RouteResources r=RouteResources.builder().routeId(100L).source("rajasthan").destination("mumbai").totalkms(500.0).build();
        Mockito.when(routeService.updateRoute(r)).thenThrow(new RouteNotFindException("No Route Found"));
        mockMvc.perform(put("/route-api/Route_Table")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writerWithDefaultPrettyPrinter()
                                .writeValueAsString(r)))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.message").value("No Route Found"));
    }

    @Test
    void deleteRoute() throws Exception, RouteNotFindException {
        RouteResources r=RouteResources.builder().routeId(100L).source("rajasthan").destination("mumbai").totalkms(500.0).build();
        Mockito.when(routeService.deleteRoute(100L)).thenReturn("Route Deleted Successfully");
        mockMvc.perform(delete("/route-api/Route_Table/{id}",100))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("Route Deleted Successfully"));
    }

    @Test
    void deleteRouteWithException() throws Exception, RouteNotFindException {
        RouteResources r=RouteResources.builder().routeId(100L).source("rajasthan").destination("mumbai").totalkms(500.0).build();
        Mockito.when(routeService.deleteRoute(100L)).thenThrow(new RouteNotFindException("Route Not found"));
        mockMvc.perform(delete("/route-api/Route_Table/{id}",100))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isNotFound());
    }
}
