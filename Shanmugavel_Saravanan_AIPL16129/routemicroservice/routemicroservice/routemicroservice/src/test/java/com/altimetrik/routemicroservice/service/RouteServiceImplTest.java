package com.altimetrik.routemicroservice.service;

import com.altimetrik.routemicroservice.exception.RouteIdAlreadyExists;
import com.altimetrik.routemicroservice.exception.RouteNotFoundException;
import com.altimetrik.routemicroservice.model.Route;
import com.altimetrik.routemicroservice.repository.RouteRepository;
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
import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
class RouteServiceImplTest {

    @Mock
    private RouteRepository routeRepository;

    @InjectMocks
    private RouteServiceImpl routeService;

    @Test
    void addRoute() throws RouteIdAlreadyExists {
        Route route = new Route();
        route.setRouteId(1);
        route.setSource("Source");
        route.setDestination("Destination");
        route.setTotalKms(100.0);

        Mockito.when(routeRepository.save(any(Route.class))).thenReturn(route);

        assertEquals(route, routeService.addRoute(route));
    }

    @Test
    void getAllRoutes() {
        Route route1 = new Route();
        route1.setRouteId(1);
        route1.setSource("Source1");
        route1.setDestination("Destination1");
        route1.setTotalKms(100.0);

        Route route2 = new Route();
        route2.setRouteId(2);
        route2.setSource("Source2");
        route2.setDestination("Destination2");
        route2.setTotalKms(150.0);

        Mockito.when(routeRepository.findAll()).thenReturn(Arrays.asList(route1, route2));

        List<Route> routes = routeService.getAllRoutes();
        assertEquals(2, routes.size());
    }

    @Test
    void getRouteById() throws RouteNotFoundException {
        Route route = new Route();
        route.setRouteId(1);
        route.setSource("Source");
        route.setDestination("Destination");
        route.setTotalKms(100.0);

        Mockito.when(routeRepository.findById(1)).thenReturn(Optional.of(route));

        assertEquals(route, routeService.getRouteById(1));
    }

    @Test
    void getRouteByIdWithException() {
        Mockito.when(routeRepository.findById(100)).thenReturn(Optional.empty());

        assertThrows(RouteNotFoundException.class, () -> routeService.getRouteById(100));
    }

    @Test
    void updateRoute() throws RouteNotFoundException {
        Route existingRoute = new Route();
        existingRoute.setRouteId(1);
        existingRoute.setSource("Source");
        existingRoute.setDestination("Destination");
        existingRoute.setTotalKms(100.0);

        Mockito.when(routeRepository.findById(1)).thenReturn(Optional.of(existingRoute));
        Mockito.when(routeRepository.save(any(Route.class))).thenReturn(existingRoute);

        Route updatedRoute = routeService.updateRoute(existingRoute);

        assertNotNull(updatedRoute);
        assertEquals("Source", updatedRoute.getSource());
    }

    @Test
    void updateRouteNotFound() throws RouteNotFoundException {
        // Stub the behavior when the route with ID 0 is not found
        Mockito.when(routeRepository.findById(0)).thenReturn(Optional.empty());

        // Invoke the service method that should throw RouteNotFoundException
        assertThrows(RouteNotFoundException.class, () -> routeService.updateRoute(new Route()));
    }




    @Test
    void deleteRouteById() throws RouteNotFoundException {
        Route route = new Route();
        route.setRouteId(1);
        route.setSource("Source");
        route.setDestination("Destination");
        route.setTotalKms(100.0);

        Mockito.when(routeRepository.findById(1)).thenReturn(Optional.of(route));

        String result = routeService.deleteRouteById(1);
        assertEquals("Route deleted successfully", result);
    }

    @Test
    void deleteRouteByIdWithException() {
        Mockito.when(routeRepository.findById(100)).thenReturn(Optional.empty());

        assertThrows(RouteNotFoundException.class, () -> routeService.deleteRouteById(100));
    }
}

