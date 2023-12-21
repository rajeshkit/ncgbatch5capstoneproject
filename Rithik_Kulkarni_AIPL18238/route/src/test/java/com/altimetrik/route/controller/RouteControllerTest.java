package com.altimetrik.route.controller;

import com.altimetrik.route.model.Route;
import com.altimetrik.route.sevice.RouteService;
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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
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
    void addRoute() throws Exception {

        Route rt1 = Route.builder()
                .routeId(111)
                .source("Test-S1")
                .destination("Test-D1")
                .totalKms(999)
                .build();

        Mockito.when(routeService.addRoute(rt1)).thenReturn(rt1);
        mockMvc.perform(post("/route-api/route")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(rt1)))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.routeId").value(111));

    }

    @Test
    void getAllRoutes() throws Exception {

        Route rt1 = Route.builder()
                .routeId(111)
                .source("Test-S1")
                .destination("Test-D1")
                .totalKms(999)
                .build();

        Route rt2 = Route.builder()
                .routeId(222)
                .source("Test-S2")
                .destination("Test-D2")
                .totalKms(888)
                .build();


        Mockito.when(routeService.getAllRoutes()).thenReturn(Arrays.asList(rt1, rt2));
        mockMvc.perform(get("/route-api/route"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2));
    }

    @Test
    void getRouteById() throws Exception{

        Route rt1 = Route.builder()
                .routeId(111)
                .source("Test-S1")
                .destination("Test-D1")
                .totalKms(999)
                .build();

        Mockito.when(routeService.getRouteByRouteId(111)).thenReturn(rt1);
        mockMvc.perform(get("/route-api/route/111")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(rt1)))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.routeId").value(111));
    }

    @Test
    void updateRoute() throws Exception{

        Route rt1 = Route.builder()
                .routeId(111)
                .source("Test-S1")
                .destination("Test-D1")
                .totalKms(999)
                .build();

        Route rt2 = Route.builder()
                .routeId(111)
                .source("Test-S2")
                .destination("Test-D2")
                .totalKms(888)
                .build();

        Mockito.when(routeService.updateRoute(rt1)).thenReturn(rt2);
        mockMvc.perform(put("/route-api/route")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(rt1)))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.routeId").value(111));

    }
}