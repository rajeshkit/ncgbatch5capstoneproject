package com.altimetrik.trainroute.controller;


import com.altimetrik.trainroute.exception.NoSuchElementException;
import com.altimetrik.trainroute.modle.Route;
import com.altimetrik.trainroute.service.RouteService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(RouteControllre.class)
public class RouteControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private RouteService routeService;
    @Autowired
    private ObjectMapper objectMapper;



    @Test
    void addRoute() throws Exception {
        Route route = Route.builder()
                .routeId(2)
                .source("Bangalore")
                .destination("channi")
                .totalKms(100)
                .build();

        when(routeService.addRoute(route)).thenReturn(route);

        mockMvc.perform(post("/route-api/route").contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(route))).andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.routeId").value(2));;
    }

    @Test
    void getAllRoutes() {
        List<Route> routeList = Arrays.asList(
                Route.builder().routeId(100).source("mysore").destination("pune").build(),
                Route.builder().routeId(105).source("MP").destination("Delhi").build()
        );

        when(routeService.getAllRoute()).thenReturn(routeList);

        List<Route> retrievedRoutes = routeService.getAllRoute();

        assertEquals(routeList.size(), retrievedRoutes.size());
    }

    @Test
    void getRouteById() throws NoSuchElementException {
        int routeId = 101;
        Route route = Route.builder()
                .routeId(routeId)
                .source("Source")
                .destination("Destination")
                .build();

        when(routeService.getRouteById(routeId)).thenReturn((route));

        Route retrievedRoute = routeService.getRouteById(routeId);

        assertEquals(route, retrievedRoute);
    }

    @Test
    void updateRoute() throws NoSuchElementException {
        Route routeToUpdate = Route.builder()
                .routeId(100)
                .source("Updated Source")
                .destination("Updated Destination")
                .totalKms(500)
                .build();

        when(routeService.updateRoute(routeToUpdate)).thenReturn((routeToUpdate));

        Route updatedRoute = routeService.updateRoute(routeToUpdate);

        assertEquals(routeToUpdate, updatedRoute);
    }

    @Test
    void deleteRouteById() throws NoSuchElementException {
        int routeIdToDelete = 101;

        when(routeService.deleteRouteById(routeIdToDelete)).thenReturn("deleted data successfully");

        String deletionMessage = routeService.deleteRouteById(routeIdToDelete);

        assertEquals("deleted data successfully", deletionMessage);
    }
}
