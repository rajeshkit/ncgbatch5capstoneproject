package com.altimetrik.trainroute.controller;

import com.altimetrik.trainroute.model.Route;
import com.altimetrik.trainroute.service.RouteService;
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
public class RouteControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private RouteService routeService;
    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void addRoute() throws Exception {
        Route r1 = Route.builder()
                .routeId("100").Source("Mysore").Destination("south korea").
                totalkms(1000).build();
        Mockito.when(routeService.addRoute(r1)).thenReturn(r1);
        mockMvc.perform(post("/route-api/route")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writerWithDefaultPrettyPrinter()
                                .writeValueAsString(r1)))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.routeId").value(100));
    }
    @Test
    void getAllRoutes() throws Exception {
        Route r1 = Route.builder()
                .routeId("100").Source("Mysore").Destination("south korea").
                totalkms(1000).build();
        Route r2 = Route.builder()
                .routeId("101").Source("Bangalore").Destination("Chennai").
                totalkms(1486).build();
        Mockito.when(routeService.getAllRoute()).thenReturn(Arrays.asList(r1,r2));
        mockMvc.perform(get("/route-api/route"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2));
    }


}
