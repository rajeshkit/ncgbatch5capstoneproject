package com.trainbooking.routemicroservices.service;


import com.trainbooking.routemicroservices.exception.RouteIdNotExistException;
import com.trainbooking.routemicroservices.model.Route;
import com.trainbooking.routemicroservices.repository.RouteRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
class RouteServiceTest {

    @Mock
    RouteRepository routeRepository;

    @InjectMocks
    RouteServiceImpl routeServiceImpl;

    @Test
    void addRoute() {
        Route route = Route.builder()
                .routeId(123).source("NED").destination("CSMT").totalKms(690).build();

        Mockito.when(routeRepository.save(route)).thenReturn(route);
        assertEquals(route,routeServiceImpl.addRoute(route));
    }

    @Test
    void getAllRoutes() {
        Route route1 = Route.builder()
                .routeId(123).source("NED").destination("CSMT").totalKms(690).build();
        Route route2 = Route.builder()
                .routeId(1234).source("NANDED").destination("PUNE").totalKms(445).build();
        
        Mockito.when(routeRepository.findAll()).thenReturn(Arrays.asList(route1,route2));
        assertEquals(2,routeServiceImpl.getAllRoutes().size());
    }

    @Test
    void getRouteByRouteId() throws RouteIdNotExistException {
        Route route1 = Route.builder()
                .routeId(123).source("NED").destination("CSMT").totalKms(690).build();
        Mockito.when(routeRepository.findById(123)).thenReturn(Optional.of(route1));
        assertEquals(123,routeServiceImpl.getRouteByRouteId(123).getRouteId());
    }

    @Test
    void getRouteByRouteIdWithException() {
        Mockito.when(routeRepository.findById(123)).thenReturn(Optional.empty());
        assertThrows(RouteIdNotExistException.class, ()-> routeServiceImpl.getRouteByRouteId(123));
    }
}
