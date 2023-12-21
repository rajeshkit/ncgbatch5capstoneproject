package com.routemicroservice.service;

import com.routemicroservice.exception.RouteIdDoesNotExistException;
import com.routemicroservice.model.Route;
import com.routemicroservice.repository.RouteRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
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
    void testSaveRouteDetails() {
        Route route = Route.builder().routeId(1)
                .source("Jaipur")
                .destination("Darjeeling")
                .totalKms(1490).build();

        when(routeRepository.save(route)).thenReturn(route);

        Route savedRoute = routeService.saveRouteDetails(route);

        assertNotNull(savedRoute);
        assertEquals(route.getRouteId(), savedRoute.getRouteId());
        assertEquals(route.getSource(), savedRoute.getSource());
        assertEquals(route.getDestination(), savedRoute.getDestination());

        verify(routeRepository, times(1)).save(route);
    }

    @Test
    void testGetAllRoutesDetail() {
        Route route1 = Route.builder().routeId(1)
                .source("Delhi")
                .destination("Cochin")
                .totalKms(1490).build();
        Route route2 = Route.builder().routeId(2)
                .source("Jaipur")
                .destination("Kolkata")
                .totalKms(1490).build();

        when(routeRepository.findAll()).thenReturn(Arrays.asList(route1, route2));

        List<Route> routes = routeService.getAllRoutesDetail();

        assertNotNull(routes);
        assertEquals(2, routes.size());

        verify(routeRepository, times(1)).findAll();
    }

    @Test
    void testGetRouteDetailsById() throws RouteIdDoesNotExistException {

        Route route = Route.builder().routeId(1)
                .source("Jaipur")
                .destination("Darjeeling")
                .totalKms(1490).build();

        when(routeRepository.findById(1)).thenReturn(Optional.of(route));

        Route fetchedRoute = routeService.getRouteDetailsById(1);

        assertNotNull(fetchedRoute);
        assertEquals(route.getRouteId(), fetchedRoute.getRouteId());
        assertEquals(route.getSource(), fetchedRoute.getSource());
        assertEquals(route.getDestination(), fetchedRoute.getDestination());

        verify(routeRepository, times(1)).findById(1);
    }

    @Test
    void testGetRouteDetailsByIdRouteNotFound() {
        when(routeRepository.findById(1)).thenReturn(Optional.empty());

        assertThrows(RouteIdDoesNotExistException.class, () -> routeService.getRouteDetailsById(1));

        verify(routeRepository, times(1)).findById(1);
    }

    @Test
    void testUpdateRouteDetails() throws RouteIdDoesNotExistException {

        Route route = Route.builder().routeId(1)
                .source("Jaipur")
                .destination("Darjeeling")
                .totalKms(1490).build();

        when(routeRepository.findById(1)).thenReturn(Optional.of(route));
        when(routeRepository.save(route)).thenReturn(route);

        Route updatedRoute = routeService.updateRouteDetails(route);

        assertNotNull(updatedRoute);
        assertEquals(route.getRouteId(), updatedRoute.getRouteId());
        assertEquals(route.getSource(), updatedRoute.getSource());
        assertEquals(route.getDestination(), updatedRoute.getDestination());

        verify(routeRepository, times(1)).findById(1);
        verify(routeRepository, times(1)).save(route);
    }

    @Test
    void testUpdateRouteDetailsRouteNotFound() {
        Route route = Route.builder().routeId(1)
                .source("Jaipur")
                .destination("Darjeeling")
                .totalKms(1490).build();

        when(routeRepository.findById(1)).thenReturn(Optional.empty());

        assertThrows(RouteIdDoesNotExistException.class, () -> routeService.updateRouteDetails(route));

        verify(routeRepository, times(1)).findById(1);
        verify(routeRepository, never()).save(any());
    }

    @Test
    void testDeleteRouteDetails() throws RouteIdDoesNotExistException {
        Route route = Route.builder().routeId(1)
                .source("Jaipur")
                .destination("Darjeeling")
                .totalKms(1490).build();

        when(routeRepository.findById(1)).thenReturn(Optional.of(route));

        String message = routeService.deleteRouteDetails(1);

        assertEquals("Route deleted successfully.", message);

        verify(routeRepository, times(1)).findById(1);
        verify(routeRepository, times(1)).deleteById(1);
    }
}