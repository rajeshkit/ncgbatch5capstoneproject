package com.route.routemicroservice.controller;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.route.routemicroservice.model.Route;
import com.route.routemicroservice.service.RouteService;
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
        Route r1 = Route.builder()
                .routeId(12)
                .source("cor")
                .destination("jp")
                .totalKms(200)
                .build();

        Mockito.when(routeService.addRoute(r1)).thenReturn(r1);
        mockMvc.perform(post("/route-api/route")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(r1)))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.routeId").value(12));
    }


    @Test
    void getAllRoute() throws Exception {
        Route r1 = Route.builder()
                .routeId(12)
                .source("cor")
                .destination("jp")
                .totalKms(200)
                .build();

        Route r2 = Route.builder()
                .routeId(123)
                .source("cor")
                .destination("jp")
                .totalKms(200)
                .build();
        Mockito.when(routeService.getAllRoute()).thenReturn(Arrays.asList(r1, r2));
        mockMvc.perform(get("/route-api/route"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2));
    }

    @Test
    void getRouteById() throws Exception {
        Route r1 = Route.builder()
                .routeId(12)
                .source("cor")
                .destination("jp")
                .totalKms(200)
                .build();
        Mockito.when(routeService.getRouteById(12)).thenReturn(r1);
        mockMvc.perform(get("/route-api/route/12")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(r1)))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.routeId").value(12));
    }



}