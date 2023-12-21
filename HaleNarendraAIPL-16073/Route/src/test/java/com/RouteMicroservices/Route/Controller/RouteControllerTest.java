package com.RouteMicroservices.Route.Controller;

import com.RouteMicroservices.Route.Services.RouteService;
import com.RouteMicroservices.Route.model.RouteResources;
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

    @MockBean
    private RouteService routeService;
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;


    @Test
    void addRoute() throws Exception {

        RouteResources t1=RouteResources.builder().routeId(123L).source("Hyderabad").destination("Pune").totalKms(12.4).build();
        Mockito.when(routeService.addRouteResources(t1)).thenReturn(t1);
        mockMvc.perform(post("/route-api/route")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writerWithDefaultPrettyPrinter()
                                .writeValueAsString(t1)))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.routeId").value(123L));

    }

    @Test
    void allRouteDetail() throws Exception {

        RouteResources t1=RouteResources.builder().routeId(123L).source("Hyderabad").destination("Pune").totalKms(12.4).build();
        RouteResources t2=RouteResources.builder().routeId(873L).source("Chennai").destination("Pune").totalKms(12.4).build();

        Mockito.when(routeService.getAllRouteDetail()).thenReturn(Arrays.asList(t1,t2));

        mockMvc.perform(get("/route-api/route"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2));
    }

    @Test
    void getRouteById() throws Exception {
        RouteResources t1=RouteResources.builder().routeId(123L).source("Hyderabad").destination("Pune").totalKms(12.4).build();

        Mockito.when(routeService.getRouteById(123L)).thenReturn(t1);

        mockMvc.perform(get("/route-api/route/{id}",123L))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.routeId").value(123L));

    }

    @Test
    void updateRouteDetail() throws Exception {
        RouteResources t1=RouteResources.builder().routeId(123L).source("Hyderabad").destination("Pune").totalKms(12.4).build();

        Mockito.when(routeService.updateRouteDetail(t1)).thenReturn(t1);

        mockMvc.perform(put("/route-api/route")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writerWithDefaultPrettyPrinter()
                                .writeValueAsString(t1)))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.routeId").value(123L));
    }

    @Test
    void deleteRouteById() throws Exception {
        RouteResources t1=RouteResources.builder().routeId(123L).source("Hyderabad").destination("Pune").totalKms(12.4).build();

        Mockito.when(routeService.deleteRouteById(123L)).thenReturn(String.valueOf(t1));

        mockMvc.perform(delete("/route-api/route/{id}",123L))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk());
    }
}