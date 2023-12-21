package com.altimetrik.routemicroservice.controller;

import com.altimetrik.routemicroservice.exception.RouteNotFoundException;
import com.altimetrik.routemicroservice.model.Route;
import com.altimetrik.routemicroservice.service.RouteService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import java.util.Arrays;
import java.util.List;

import static org.springframework.test.web.client.match.MockRestRequestMatchers.content;
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
        Route route = new Route();
        route.setRouteId(1);
        route.setSource("Source");
        route.setDestination("Destination");
        route.setTotalKms(100.0);

        Mockito.when(routeService.addRoute(Mockito.any(Route.class))).thenReturn(route);

        mockMvc.perform(post("/route-api/route")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(route)))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.routeId").value(1));
    }

    @Test
    void getAllRoutes() throws Exception {
        Route route1 = new Route();
        route1.setRouteId(1);
        route1.setSource("Source1");
        route1.setDestination("Destination1");
        route1.setTotalKms(100.0);

        Route route2 = new Route();
        route2.setRouteId(2);
        route2.setSource("Source2");
        route2.setDestination("Destination2");
        route2.setTotalKms(150.0);

        Mockito.when(routeService.getAllRoutes()).thenReturn(Arrays.asList(route1, route2));

        mockMvc.perform(get("/route-api/route"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2));
    }

    @Test
    void getRouteById() throws Exception {
        Route route = new Route();
        route.setRouteId(1);
        route.setSource("Source");
        route.setDestination("Destination");
        route.setTotalKms(100.0);

        Mockito.when(routeService.getRouteById(1)).thenReturn(route);

        mockMvc.perform(get("/route-api/route/1"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.routeId").value(1));
    }

    @Test
    void getRouteByIdNotFound() throws Exception {
        Mockito.when(routeService.getRouteById(100)).thenThrow(new RouteNotFoundException("Route not found"));

        mockMvc.perform(get("/route-api/route/100"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isNotFound());
    }

    @Test
    void updateRoute() throws Exception {
        Route route = new Route();
        route.setRouteId(1);
        route.setSource("Source");
        route.setDestination("Destination");
        route.setTotalKms(100.0);

        Mockito.when(routeService.updateRoute(Mockito.any(Route.class))).thenReturn(route);

        mockMvc.perform(put("/route-api/route")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(route)))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.routeId").value(1));
    }

//
@Test
void updateRouteNotFound() throws Exception {
    Mockito.when(routeService.updateRoute(Mockito.any(Route.class))).thenReturn(null);

    mockMvc.perform(put("/route-api/route")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(new Route())))
            .andDo(MockMvcResultHandlers.print())
            .andExpect(status().isBadRequest()); // Update the expectation to isBadRequest()
}


    @Test
    void deleteRouteById() throws Exception {
        Mockito.when(routeService.deleteRouteById(1)).thenReturn("Route deleted successfully");

        mockMvc.perform(delete("/route-api/route/1"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").value("Route deleted successfully"));
    }

    @Test
    void deleteRouteByIdNotFound() throws Exception
    {
        Mockito.when(routeService.deleteRouteById(100)).thenThrow(new RouteNotFoundException("Route not found"));
        mockMvc.perform(delete("/route-api/route/100"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isNotFound());
    }
}
