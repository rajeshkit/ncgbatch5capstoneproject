package com.altimetrik.route.routerestapi.service;

import com.altimetrik.route.routerestapi.exception.RouteNumberNotFoundException;
import com.altimetrik.route.routerestapi.model.Route;
import com.altimetrik.route.routerestapi.repository.RouteRepository;
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
class RouteServiceImplTest {

    @Mock
    private RouteRepository routeRepository;

    @InjectMocks
    private RouteServiceImpl routeServiceImpl;

    @Test
    void addRoute() {
        Route route = Route.builder().routeId("1").source("Chennai").destination("Nashik").totalKmsDistance(1620).build();

        Mockito.when(routeRepository.save(route)).thenReturn(route);
        assertEquals(route, routeServiceImpl.addRoute(route));

    }

    @Test
    void getAllRoute() {

        Route firstRoute = Route.builder().routeId("1").source("Chennai").destination("Nashik").totalKmsDistance(1620).build();
        Route secondRoute = Route.builder().routeId("2").source("Pune").destination("Banglore").totalKmsDistance(650).build();

        Mockito.when(routeRepository.findAll()).thenReturn(Arrays.asList(firstRoute, secondRoute));
        assertEquals(2, routeServiceImpl.getAllRoute().size());
    }

    @Test
    void getRouteById() throws RouteNumberNotFoundException {
        Route route = Route.builder().routeId("1").source("Nashik").destination("Pune").totalKmsDistance(225).build();

        Mockito.when(routeRepository.findById(route.getRouteId())).thenReturn(Optional.of(route));
        assertEquals(route, routeServiceImpl.getRouteById(route.getRouteId()));
    }

    @Test
    void updateRoute() {
        Route route = Route.builder().routeId("2").source("Delhi").destination("Haridwar").totalKmsDistance(1200).build();

        Mockito.lenient().when(routeRepository.save(route)).thenReturn(route);
        //assertEquals(route,routeServiceImpl.updateRoute(route,route.getRouteId()));
        assertThrows(RouteNumberNotFoundException.class, () -> {
            routeServiceImpl.updateRoute(route, route.getRouteId());
        });
    }

    @Test
    void deleteRouteByID() {
        Route route = Route.builder().routeId("2").source("Delhi").destination("Kedarnath").totalKmsDistance(1756).build();

        Mockito.lenient().doNothing().when(routeRepository).deleteById(route.getRouteId());
        //assertEquals(route,routeServiceImpl.updateRoute(route,route.getRouteId()));
        assertThrows(RouteNumberNotFoundException.class, () -> {
            routeServiceImpl.deleteRouteByID(route.getRouteId());
        });
    }
}