package com.booking_details.route.controller;

import com.booking_details.route.model.RouteModel;
import com.booking_details.route.service.RouteService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.core.StringContains.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(RouteController.class)
class RouteControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private RouteService routeService;
    @Autowired
    private ObjectMapper objectMapper;
    private static RouteModel routeModel1;
    private static RouteModel routeModel2;
    private static List<RouteModel> routeModelList;
    @BeforeAll
    static void setupModels(){
        routeModel1 = new RouteModel(17,"chennai","hyderabad",23.87);
        routeModel2 = new RouteModel(16,"mumbai","chennai",200.8);
        routeModelList = new ArrayList<>();
        routeModelList.add(routeModel1);
        routeModelList.add(routeModel2);
    }

    @Test
    void addRouteDetailsTest() throws Exception {
        Mockito.when(routeService.addRouteDetails(routeModel1)).thenReturn(routeModel1);
        mockMvc.perform(post("/route-microservice/route")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writerWithDefaultPrettyPrinter()
                                .writeValueAsString(routeModel1)))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.routeId").value(17));
    }

    @Test
    void getAllRouteDetailsTest() throws Exception {
        Mockito.when(routeService.getAllRouteDetails()).thenReturn(routeModelList);
        mockMvc.perform(get("/route-microservice/route"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2));
    }

    @Test
    void getAllRouteDetailsByIdTest() throws Exception{
        Mockito.when(routeService.getAllRouteDetailsById(17L)).thenReturn(routeModel1);
        mockMvc.perform(get("/route-microservice/route/{id}",17))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.routeId").value(17));
    }

    @Test
    void updateRouteDetailsTest() throws Exception{
        Mockito.when(routeService.updateRouteDetails(routeModel1)).thenReturn(routeModel1);
        mockMvc.perform(put("/route-microservice/route")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writerWithDefaultPrettyPrinter()
                                .writeValueAsString(routeModel1)))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.source").value("chennai"));
    }

    @Test
    void deleteRouteDetailsByIdTest() throws Exception{
        Mockito.when(routeService.deleteRouteDetailsById(17L)).thenReturn("Deleted..");
        mockMvc.perform(delete("/route-microservice/route/{id}",17))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Deleted..")));
    }
}
