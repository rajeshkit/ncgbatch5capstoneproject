package com.booking.route.controller;

import com.booking.route.exception.RouteIdNotExistsException;
import com.booking.route.model.RouteResources;
import com.booking.route.service.RouteService;
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

import static org.hamcrest.core.StringContains.containsString;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(RouteController.class)
class RouteControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    @MockBean
    private RouteService routeService;


    @Test
    void addRouteResources() throws Exception {
        RouteResources routeResources= RouteResources.builder()
                .routeId(1234l)
                .source("Kakinada").destination("Hyderabad")
                .totalKms(780.0).build();

        Mockito.when(routeService.addRouteResources(routeResources)).thenReturn(routeResources);

        mockMvc.perform(post("/route-api/route")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(objectMapper.writeValueAsString(routeResources)))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.routeId").value(1234l));

    }

    @Test
    void getAllRouteResources() throws Exception {
        RouteResources routeResources= RouteResources.builder()
                .routeId(1234l)
                .source("Kakinada").destination("Hyderabad")
                .totalKms(780.0).build();

        RouteResources routeResources1= RouteResources.builder()
                .routeId(1235l)
                .source("Hyderabad").destination("Kakinada")
                .totalKms(780.0).build();

        Mockito.when(routeService.getAllRouteResources()).thenReturn(Arrays.asList(routeResources,routeResources1));
        assertEquals(2,routeService.getAllRouteResources().size());
        mockMvc.perform(get("/route-api/route")
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2));
    }

    @Test
    void getRouteResourcesById() throws Exception {
        RouteResources routeResources= RouteResources.builder()
                .routeId(1234l)
                .source("Kakinada").destination("Hyderabad")
                .totalKms(780.0).build();

        Mockito.when(routeService.getAllRouteResourcesById(1234l)).thenReturn(routeResources);
        mockMvc.perform(get("/route-api/route/{id}",routeResources.getRouteId())
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(objectMapper.writeValueAsString(routeResources)))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.routeId").value(1234l));

    }

    @Test
    void getRouteResourcesByTrainNumberWithException() throws Exception {
        RouteResources routeResources= RouteResources.builder()
                .routeId(1234l)
                .source("Kakinada").destination("Hyderabad")
                .totalKms(780.0).build();

        Mockito.when(routeService.getAllRouteResourcesById(100l)).thenThrow(new RouteIdNotExistsException("Route Id Not Found in db table..!"));
        mockMvc.perform(get("/route-api/route/{routeId}",100l))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.message").value("Route Id Not Found in db table..!"));
    }


    @Test
    void updateRouteResource() throws Exception {
        RouteResources routeResources= RouteResources.builder()
                .routeId(1234l)
                .source("Kakinada").destination("Hyderabad")
                .totalKms(780.0).build();

        Mockito.when(routeService.updateRouteResource(routeResources)).thenReturn(routeResources);
        mockMvc.perform(put("/route-api/route")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writerWithDefaultPrettyPrinter()
                                .writeValueAsString(routeResources)))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.routeId").value(1234l));

    }

    @Test
    void updateTrainResourceWithException() throws Exception {
        RouteResources routeResources= RouteResources.builder()
                .routeId(1234l)
                .source("Kakinada").destination("Hyderabad")
                .totalKms(780.0).build();

        Mockito.when(routeService.updateRouteResource(routeResources)).thenThrow(new RouteIdNotExistsException("Route Id not found in db..!"));
        mockMvc.perform(put("/route-api/route")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writerWithDefaultPrettyPrinter()
                                .writeValueAsString(routeResources)))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.message").value("Route Id not found in db..!"));

    }

    @Test
    void deleteRouteResourceById() throws Exception {
        RouteResources routeResources= RouteResources.builder()
                .routeId(1234l)
                .source("Kakinada").destination("Hyderabad")
                .totalKms(780.0).build();

        Mockito.when(routeService.deleteRouteResourceById(100l)).thenReturn("Deleted Successfully Based on Route Id");
        mockMvc.perform(delete("/route-api/route/{routeId}",100l))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Deleted Successfully Based on Route Id")));

    }

    @Test
    void deleteRouteResourcesByRouteIdWithException() throws Exception {
        RouteResources routeResources= RouteResources.builder()
                .routeId(1234l)
                .source("Kakinada").destination("Hyderabad")
                .totalKms(780.0).build();

        Mockito.when(routeService.deleteRouteResourceById(1010l)).thenThrow(new RouteIdNotExistsException("Train Number not found in db..!"));
        mockMvc.perform(delete("/route-api/route/{routeId}",1010l))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.message").value("Train Number not found in db..!"));
    }

}