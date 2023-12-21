package com.altimetrik.routemicroservices.service;

import com.altimetrik.routemicroservices.exception.RouteIdAlreadyExistsException;
import com.altimetrik.routemicroservices.model.Route;
import com.altimetrik.routemicroservices.repository.RouteRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class RouteServiceImplTest {

    @Mock
    private RouteRepository routeRepository;

    @InjectMocks
    private RouteServiceImpl routeService;

    @Test
    void testAddRoute() throws RouteIdAlreadyExistsException {
        Route route = Route.builder().routeId(1).source("City A").destination("City B").totalKms(100.0).build();

        when(routeRepository.save(Mockito.any(Route.class))).thenReturn(route);

        Route addedRoute = routeService.addRoute(route);

        assertNotNull(addedRoute);
        assertEquals(route.getRouteId(), addedRoute.getRouteId());
    }

    @Test
    void testGetAllRoutes() {
        List<Route> routes = Arrays.asList(new Route(), new Route(), new Route());

        when(routeRepository.findAll()).thenReturn(routes);

        List<Route> resultRoutes = routeService.getAllRoutes();

        assertNotNull(resultRoutes);
        assertEquals(routes.size(), resultRoutes.size());
    }

    @Test
    void testGetRouteByRouteId() throws Exception {
        Route route = Route.builder().routeId(1).source("City A").destination("City B").totalKms(100.0).build();

        when(routeRepository.findById(1L)).thenReturn(Optional.of(route));

        Route resultRoute = routeService.getRouteById(1);

        assertNotNull(resultRoute);
        assertEquals(route.getRouteId(), resultRoute.getRouteId());
    }

    @Test
    void testUpdateRoute() throws Exception {
        Route route = Route.builder().routeId(1).source("City A").destination("City B").build();
        when(routeRepository.save(any(Route.class))).thenReturn(route);
        when(routeRepository.findById(1L)).thenReturn(Optional.of(route));
        Route result = routeService.updateRoute(route);
        assertNotNull(result);
        assertEquals(1, result.getRouteId());
        assertEquals("City A", result.getSource());
        assertEquals("City B", result.getDestination());
    }

    @Test
    void testDeleteRouteByRouteId() {
        long routeIdToDelete = 123;
        when(routeRepository.findById(routeIdToDelete)).thenThrow(new RuntimeException("Database error"));
        assertThrows(RuntimeException.class, () -> routeService.deleteRouteById(routeIdToDelete));
        verify(routeRepository, never()).deleteById(anyLong());
    }
}