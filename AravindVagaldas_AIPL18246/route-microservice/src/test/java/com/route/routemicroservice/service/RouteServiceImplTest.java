package com.route.routemicroservice.service;

import com.route.routemicroservice.exception.RouteIdNotExistException;
import com.route.routemicroservice.model.Route;
import com.route.routemicroservice.repository.RouteRepository;
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
    private RouteServiceImpl routeServiceImpl;

    @Test
    void addRoute() {
        Route route=Route.builder().routeId(905).source("chennai")
                .destination("hyderabad").totalKms(800).build();
        Mockito.when(routeRepository.save(route)).thenReturn(route);
        assertEquals(route,routeServiceImpl.addRoute(route));

    }

    @Test
    void getAllRoutes() {
        Route route=Route.builder().routeId(905).source("chennai")
                .destination("hyderabad").totalKms(800).build();
        Route route1=Route.builder().routeId(910).source("Hyderabad")
                .destination("Delhi").totalKms(1400).build();

        Mockito.when(routeRepository.findAll()).thenReturn(Arrays.asList(route,route1));
        assertEquals(2,routeServiceImpl.getAllRoutes().size());
    }

    @Test
    void getRouteByRouteId() throws RouteIdNotExistException{
        Route route=Route.builder().routeId(905).source("chennai")
                .destination("hyderabad").totalKms(800).build();

        Mockito.when(routeRepository.findById(1008)).thenReturn(Optional.of(route));
        assertEquals(905,routeServiceImpl.getRouteByRouteId(1008).getRouteId());

    }

    @Test
    void getRouteByRouteIdWithException() {
        Route route=Route.builder().routeId(905).source("chennai")
                .destination("hyderabad").totalKms(800).build();

        Mockito.when(routeRepository.findById(906)).thenReturn(Optional.empty());
        assertThrows(RouteIdNotExistException.class,
                ()->routeServiceImpl.getRouteByRouteId(906));

    }

    @Test
    void updateRoute() throws RouteIdNotExistException {
        Route route=Route.builder().routeId(905).source("chennai")
                .destination("hyderabad").totalKms(800).build();
        Mockito.when(routeRepository.findById(905)).thenReturn(Optional.of(route));
        Mockito.when(routeRepository.save(route)).thenReturn(route);
        assertEquals(905,routeServiceImpl.updateRoute(route).getRouteId());
    }

    @Test
    void updateRouteWithException() {
        Route route=Route.builder().routeId(905).source("chennai")
                .destination("hyderabad").totalKms(800).build();
        Mockito.when(routeRepository.findById(908)).thenReturn(Optional.empty());
        assertThrows(RouteIdNotExistException.class,
                ()->routeServiceImpl.getRouteByRouteId(908));
    }

    @Test
    void deleteRouteByRouteId()throws RouteIdNotExistException {
        Route route=Route.builder().routeId(905).source("chennai")
                .destination("hyderabad").totalKms(800).build();
        Mockito.when(routeRepository.findById(905)).thenReturn(Optional.of(route));
        assertEquals("Route deleted successfully",routeServiceImpl.deleteRouteByRouteId(905));

    }
    @Test
    void deleteRouteByRouteIdWithException() {
        Route route=Route.builder().routeId(905).source("chennai")
                .destination("hyderabad").totalKms(800).build();
        Mockito.when(routeRepository.findById(910)).thenReturn(Optional.empty());
        assertThrows(RouteIdNotExistException.class,
                ()->routeServiceImpl.deleteRouteByRouteId(910));
    }
}