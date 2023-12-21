package com.railwaybooking.Route.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.railwaybooking.Route.exception.RouteIdNotFoundException;
import com.railwaybooking.Route.model.RouteInfo;
import com.railwaybooking.Route.service.RouteService;
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
    void addRoute() throws Exception{
        RouteInfo r=RouteInfo.builder().routeId(123).source("Delhi").destination("Jaipur").totalKms(500).build();
        Mockito.when(routeService.addRoute(r)).thenReturn(r);
        mockMvc.perform(post("/routeInfo-api/routeInfo")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writerWithDefaultPrettyPrinter()
                                .writeValueAsString(r)))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.routeId").value(123));
    }
    @Test
    void getAllRoutes() throws Exception{
        RouteInfo r1=RouteInfo.builder().routeId(123).source("Delhi").destination("Jaipur").totalKms(500).build();
        RouteInfo r2=RouteInfo.builder().routeId(2).source("BNG").destination("KNR").totalKms(800).build();
        Mockito.when(routeService.getAllRoutes()).thenReturn(Arrays.asList(r1,r2));
        mockMvc.perform(get("/routeInfo-api/routeInfo"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2));

    }
    @Test
    void getRouteById() throws Exception{
        RouteInfo r= RouteInfo.builder().routeId(123).source("Delhi").destination("Jaipur").totalKms(500).build();
        Mockito.when(routeService.getRouteById(123L)).thenReturn(r);
        mockMvc.perform(get("/routeInfo-api/routeInfo/{id}",123))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.routeId").value(123));
    }
    @Test
    void getRouteByIdWithException() throws Exception{
        RouteInfo r= RouteInfo.builder().routeId(123).source("Delhi").destination("Jaipur").totalKms(500).build();
        Mockito.when(routeService.getRouteById(123L)).thenThrow(new RouteIdNotFoundException("No route Found"));
        mockMvc.perform(get("/routeInfo-api/routeInfo/{id}", 123))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isNotFound());
    }
    @Test
    void updateRouteInfo() throws Exception{
        RouteInfo r= RouteInfo.builder().routeId(123).source("Delhi").destination("Jaipur").totalKms(500).build();
        Mockito.when(routeService.updateRouteInfo(r)).thenReturn(r);
        mockMvc.perform(put("/routeInfo-api/routeInfo")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writerWithDefaultPrettyPrinter()
                                .writeValueAsString(r)))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.routeId").value(123));
    }
    @Test
    void updateRouteInfoWithException() throws Exception{
        RouteInfo r= RouteInfo.builder().routeId(123).source("Delhi").destination("Jaipur").totalKms(500).build();
        Mockito.when(routeService.updateRouteInfo(r)).thenThrow(new RouteIdNotFoundException("No route Found"));
        mockMvc.perform(put("/routeInfo-api/routeInfo")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writerWithDefaultPrettyPrinter()
                                .writeValueAsString(r)))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.message").value("No route Found"));
    }
    @Test
    void deleteRouteById() throws Exception{
        RouteInfo r= RouteInfo.builder().routeId(123).source("Delhi").destination("Jaipur").totalKms(500).build();
        Mockito.when(routeService.deleteRouteById(123L)).thenReturn("Route deleted Successfully");
        mockMvc.perform(delete("/routeInfo-api/routeInfo/{id}",123))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("Route deleted Successfully"));
    }
    @Test
    void deleteRouteByIdWithException() throws Exception{
        RouteInfo r= RouteInfo.builder().routeId(123).source("Delhi").destination("Jaipur").totalKms(500).build();
        Mockito.when(routeService.deleteRouteById(2L)).thenThrow(new RouteIdNotFoundException("Route not found"));
        mockMvc.perform(delete("/routeInfo-api/routeInfo/{id}",2L))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isNotFound());
    }
}
