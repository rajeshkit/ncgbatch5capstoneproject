package com.altimetrik.routemicroservices.controller;

import com.altimetrik.routemicroservices.model.Route;
import com.altimetrik.routemicroservices.service.RouteService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Nested
@WebMvcTest(RouteController.class)
class RouteControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private RouteService routeService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void testAddRoute() throws Exception {
        Route routeToAdd = Route.builder().source("City A").destination("City B").totalKms(100.0).build();

        when(routeService.addRoute(Mockito.any(Route.class))).thenReturn(routeToAdd);

        mockMvc.perform(post("/routes-api/routes").contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(routeToAdd))).andDo(MockMvcResultHandlers.print()).andExpect(status().isOk()).andExpect(jsonPath("$.source").value("City A")).andExpect(jsonPath("$.destination").value("City B")).andExpect(jsonPath("$.totalKms").value(100.0));
    }

    @Test
    void testGetAllRoutes() throws Exception {
        List<Route> routeList = List.of();

        when(routeService.getAllRoutes()).thenReturn(routeList);

        mockMvc.perform(get("/routes-api/routes")).andExpect(status().isOk()).andExpect(jsonPath("$", hasSize(routeList.size())));
    }

    @Test
    void testGetRouteByRouteId() throws Exception {
        Route existingRoute = Route.builder().routeId(1).source("City A").destination("City B").totalKms(100.0).build();

        when(routeService.getRouteById(1)).thenReturn(existingRoute);

        mockMvc.perform(get("/routes-api/routes/1")).andExpect(status().isOk()).andExpect(jsonPath("$.source").value("City A")).andExpect(jsonPath("$.destination").value("City B")).andExpect(jsonPath("$.totalKms").value(100.0));
    }

    @Test
    void testUpdateRoute() throws Exception {
        Route routeToUpdate = Route.builder().routeId(1).source("Updated City A").destination("Updated City B").totalKms(120.0).build();

        when(routeService.updateRoute(Mockito.any(Route.class))).thenReturn(routeToUpdate);

        mockMvc.perform(put("/routes-api/routes").contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(routeToUpdate))).andDo(MockMvcResultHandlers.print()).andExpect(status().isOk()).andExpect(jsonPath("$.routeId").value(1)).andExpect(jsonPath("$.source").value("Updated City A")).andExpect(jsonPath("$.destination").value("Updated City B")).andExpect(jsonPath("$.totalKms").value(120.0));
    }

    @Test
    void testDeleteRouteByRouteId() throws Exception {
        mockMvc.perform(delete("/routes-api/routes/1")).andExpect(status().isOk());
    }
}

