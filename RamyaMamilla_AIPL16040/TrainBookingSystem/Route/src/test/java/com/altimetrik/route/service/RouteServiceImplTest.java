package com.altimetrik.route.service;

import com.altimetrik.route.exception.RouteIDNotFoundException;
import com.altimetrik.route.model.Route;
import com.altimetrik.route.repository.RouteRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(MockitoExtension.class)
class RouteServiceImplTest {
    @Mock
    private RouteRepository routeRepository;
    @InjectMocks
    private RouteServiceImpl routeService;

    Route r1=Route.builder().routeId(100)
            .trainNo(10001).totalKm(700)
            .source("HYD").destination("SK").build();
    Route r2=Route.builder().routeId(101)
            .trainNo(10002).totalKm(800)
            .source("SK").destination("HYD").build();

    @Test
    void addRoute() {
        Mockito.when(routeRepository.save(r1)).thenReturn(r1);
        assertEquals(r1,routeService.addRoute(r1));
    }

    @Test
    void viewAllRoutes() {
        Mockito.when(routeRepository.findAll()).thenReturn(Arrays.asList(r1,r2));
        assertEquals(2,routeService.viewAllRoutes().size());
    }

    @Test
    void getRouteById() {
        Mockito.when(routeRepository.findById(101)).thenReturn(Optional.of(r2));
        assertEquals(101,routeService.getRouteById(101).getRouteId());

    }

    @Test
    void getRouteByIdWithException() {
        Mockito.when(routeRepository.findById(110)).thenReturn(Optional.empty());
        assertThrows(RouteIDNotFoundException.class,
                ()->routeService.getRouteById(110));
    }

    @Test
    void updateRoute() throws RouteIDNotFoundException {
        Mockito.when(routeRepository.findById(100)).thenReturn(Optional.of(r1));
        Mockito.when(routeRepository.save(r1)).thenReturn(r1);
        assertEquals(100,routeService.updateRoute(r1).getRouteId());
    }

    @Test
    void updateRouteWithException() {
        Mockito.when(routeRepository.findById(100)).thenReturn(Optional.empty());
        assertThrows(RouteIDNotFoundException.class,
                ()->routeService.updateRoute(r1));
    }

    @Test
    void deleteRouteById() throws RouteIDNotFoundException{
        Mockito.when(routeRepository.findById(100)).thenReturn(Optional.of(r1));
        assertEquals("Route Deleted Successfully for Route: "+ r1.getRouteId(),routeService.deleteRouteById(100));
    }

    @Test
    void deleteRouteByIdException() {
        Mockito.when(routeRepository.findById(100)).thenReturn(Optional.empty());
        assertThrows(RouteIDNotFoundException.class, ()->routeService.deleteRouteById(100));
    }
}