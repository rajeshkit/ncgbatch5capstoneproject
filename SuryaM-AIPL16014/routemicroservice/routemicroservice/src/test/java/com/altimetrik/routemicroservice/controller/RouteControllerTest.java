package com.altimetrik.routemicroservice.controller;

import com.altimetrik.routemicroservice.model.Route;
import com.altimetrik.routemicroservice.service.RouteService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import java.util.Arrays;

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

    @Test
    void addRoute() throws Exception{

        Route r= Route.builder()
                .routeId(100)
                .Source("Chennai")
                .Destination("Bangalore")
                .totalKms(200).build();

        Mockito.when(routeService.addRoute(r)).thenReturn(r);
        mockMvc.perform(post("/route-api/route")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(r)))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.routeId").value(100));
    }

    @Test
    void getAllRoute() throws Exception {

        Route r1= Route.builder()
                .routeId(100)
                .Source("Chennai")
                .Destination("Bangalore")
                .totalKms(300).build();

        Route r2= Route.builder()
                .routeId(101)
                .Source("Karur")
                .Destination("Coimbatore")
                .totalKms(100).build();

        Mockito.when(routeService.getAllRoute()).thenReturn(Arrays.asList(r1,r2));
        mockMvc.perform(get("/route-api/route/"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2));
    }

    @Test
    void getRouteById() throws Exception {

        Route r= Route.builder()
                .routeId(100)
                .Source("Chennai")
                .Destination("Bangalore")
                .totalKms(200).build();

        Mockito.when(routeService.getRouteById(100)).thenReturn(r);

        mockMvc.perform(get("/route-api/route/{id}", 100))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.routeId").value(100));
    }

    @Test
    void updateRoute() throws Exception{

        Route r= Route.builder()
                .routeId(100)
                .Source("Chennai")
                .Destination("Bangalore")
                .totalKms(200).build();

        Mockito.when(routeService.updateRoute(r)).thenReturn(r);
        mockMvc.perform(put("/route-api/route/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(r)))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.routeId").value(100));
    }

    @Test
    void deleteRouteById() throws Exception{

        int routeId = 100;

        Mockito.when(routeService.deleteRouteById(routeId)).thenReturn("Route deleted successfully");
        mockMvc.perform(delete("/route-api/route/{id}", routeId))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(content().string("Route deleted successfully"));
    }
}