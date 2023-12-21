package com.finalproject.routemicroservice;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.finalproject.routemicroservice.controller.RouteController;
import com.finalproject.routemicroservice.model.Route;
import com.finalproject.routemicroservice.service.RouteService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class RouteControllerTesting {

    private MockMvc mockMvc;

    @Mock
    private RouteService routeService;

    @InjectMocks
    private RouteController routeController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(routeController).build();
    }

    @Test
    public void testAddRoute() throws Exception {
        Route route = Route.builder()
                .totalkms(1000)
                .routeId(2)
                .source("chennai")
                .destination("mumbai")
                .build();

        when(routeService.addRoute(any(Route.class))).thenReturn(route);

        mockMvc.perform(post("/route-restapi/route")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(route)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.routeId").exists());

    }




    @Test
    public void testGetAllRoutes() throws Exception {

        Route route1 = Route.builder()
                .routeId(2)
                .totalkms(1000)
                .source("chennai")
                .destination("mumbai")
                .build();
        Route route2 = Route.builder()
                .routeId(3)
                .totalkms(2000)
                .source("surat")
                .destination("delhi")
                .build();
        List<Route> routes = Arrays.asList(route1, route2);

        when(routeService.getAllRoutes()).thenReturn(routes);

        mockMvc.perform(get("/route-restapi/route"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$.length()").value(2));
    }

    @Test
    public void testGetRouteById() throws Exception {
        int routeId = 1;
        Route route = Route.builder()
                .routeId(2)
                .totalkms(1000)
                .source("chennai")
                .destination("mumbai")
                .build();
        Optional<Route> optionalRoute = Optional.of(route);

        when(routeService.getRouteById(routeId)).thenReturn(optionalRoute);

        mockMvc.perform(get("/route-restapi/route/{routeId}", routeId))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.routeId").value(2));
    }

    @Test
    public void testUpdateRoute() throws Exception {
        Route route = Route.builder()
                .routeId(2)
                .totalkms(1000)
                .source("chennai")
                .destination("mumbai")
                .build();

        when(routeService.updateRoute(any(Route.class))).thenReturn(route);

        mockMvc.perform(put("/route-restapi/route")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(route)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.routeId").exists());
    }


}

