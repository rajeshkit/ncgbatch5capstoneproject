package com.routemicroservice.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.routemicroservice.model.Route;
import com.routemicroservice.service.RouteService;
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

@WebMvcTest(RouteController.class)
class RouteControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private RouteService routeService;
    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void testSaveRouteDetails() throws Exception {
        Route route = Route.builder().routeId(1).source("Jaipur").destination("Darjeeling").totalKms(1490).build();
        Mockito.when(routeService.saveRouteDetails(route)).thenReturn(route);

        mockMvc.perform(MockMvcRequestBuilders.post("/route-api/route")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writerWithDefaultPrettyPrinter()
                                .writeValueAsString(route)))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.routeId").exists());
    }

    @Test
    void testGetAllRoutesDetail() throws Exception {
        Route route1 = Route.builder().routeId(1).source("Delhi").destination("Cochin").totalKms(1490).build();
        Route route2 = Route.builder().routeId(2).source("Jaipur").destination("Kolkata").totalKms(1490).build();
        Mockito.when(routeService.getAllRoutesDetail()).thenReturn(Arrays.asList(route1,route2));

        mockMvc.perform(MockMvcRequestBuilders.get("/route-api/route"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.length()").value(2));
    }

    @Test
    void testGetRouteDetailsById() throws Exception {
        int routeId = 1;
        Route route = Route.builder().routeId(routeId)
                .source("Jaipur")
                .destination("Darjeeling")
                .totalKms(1490).build();
        Mockito.when(routeService.getRouteDetailsById(routeId)).thenReturn(route);

        mockMvc.perform(MockMvcRequestBuilders.get("/route-api/{id}", routeId))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.routeId").value(routeId));
    }

    @Test
    void testUpdateRouteDetails() throws Exception {
        Route route = Route.builder().routeId(1)
                .source("Jaipur")
                .destination("Darjeeling")
                .totalKms(1490).build();
        Mockito.when(routeService.updateRouteDetails(route)).thenReturn(route);

        mockMvc.perform(MockMvcRequestBuilders.put("/route-api/route")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writerWithDefaultPrettyPrinter()
                                .writeValueAsString(route)))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.routeId").exists());
    }

    @Test
    void testDeleteRouteDetails() throws Exception {
        int routeId = 1;
        Mockito.when(routeService.deleteRouteDetails(routeId)).thenReturn("Route deleted successfully.");

        mockMvc.perform(MockMvcRequestBuilders.delete("/route-api/{id}", routeId))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("Route deleted successfully."));
    }
}