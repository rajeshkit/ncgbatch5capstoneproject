package com.routealtimetrik.routemicroservice.service;

import com.routealtimetrik.routemicroservice.exception.RouteIdAlreadyExistException;
import com.routealtimetrik.routemicroservice.exception.RouteIdNotExistException;
import com.routealtimetrik.routemicroservice.model.Route;
import com.routealtimetrik.routemicroservice.repository.RouteRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class RouteServiceImplTest {

    @Mock
    private RouteRepository routeRepository;

    @InjectMocks
    private RouteServiceImpl routeService;

    @Test
    void addRoute() throws RouteIdAlreadyExistException {
        Route route = Route.builder().routeId(1).source("SourceA").destination("DestinationB").totalKms(150.5f).build();
        when(routeRepository.save(any(Route.class))).thenReturn(route);

        Route result = routeService.addRoute(route);

        assertNotNull(result);
        assertEquals(1, result.getRouteId());
        assertEquals("SourceA", result.getSource());
        assertEquals("DestinationB", result.getDestination());
        assertEquals(150.5f, result.getTotalKms());
    }

    @Test
    void getAllRoutes() {
        Route route1 = Route.builder().routeId(1).source("SourceA").destination("DestinationB").totalKms(150.5f).build();
        Route route2 = Route.builder().routeId(2).source("SourceC").destination("DestinationD").totalKms(200.0f).build();
        when(routeRepository.findAll()).thenReturn(Arrays.asList(route1, route2));

        List<Route> result = routeService.getAllRoutes();

        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals(1, result.get(0).getRouteId());
        assertEquals("SourceA", result.get(0).getSource());
        assertEquals("DestinationB", result.get(0).getDestination());
        assertEquals(150.5f, result.get(0).getTotalKms());
        assertEquals(2, result.get(1).getRouteId());
        assertEquals("SourceC", result.get(1).getSource());
        assertEquals("DestinationD", result.get(1).getDestination());
        assertEquals(200.0f, result.get(1).getTotalKms());
    }

    @Test
    void getRouteById() throws RouteIdNotExistException {
        Route route = Route.builder().routeId(1).source("SourceA").destination("DestinationB").totalKms(150.5f).build();
        when(routeRepository.findById(1)).thenReturn(Optional.of(route));

        Route result = routeService.getRouteById(1);

        assertNotNull(result);
        assertEquals(1, result.getRouteId());
        assertEquals("SourceA", result.getSource());
        assertEquals("DestinationB", result.getDestination());
        assertEquals(150.5f, result.getTotalKms());
    }

    @Test
    void getRouteById_ThrowsException() {
        when(routeRepository.findById(1)).thenReturn(Optional.empty());

        assertThrows(RouteIdNotExistException.class, () -> routeService.getRouteById(1));
    }

    @Test
    void updateRoute() throws RouteIdNotExistException {
        Route route = Route.builder().routeId(1).source("SourceA").destination("DestinationB").totalKms(150.5f).build();
        when(routeRepository.save(any(Route.class))).thenReturn(route);
        when(routeRepository.findById(1)).thenReturn(Optional.of(route));

        Route result = routeService.updateRoute(route);

        assertNotNull(result);
        assertEquals(1, result.getRouteId());
        assertEquals("SourceA", result.getSource());
        assertEquals("DestinationB", result.getDestination());
        assertEquals(150.5f, result.getTotalKms());
    }

    @Test
    void deleteRouteById() throws RouteIdNotExistException {
        int routeId = 123;
        Route mockRoute = new Route();
        when(routeRepository.findById(routeId)).thenReturn(java.util.Optional.of(mockRoute));
        String result = routeService.deleteRouteById(routeId);
        assertEquals("Route Has Been Deleted 123", result);
        verify(routeRepository, times(1)).deleteById(routeId);
    }
}
