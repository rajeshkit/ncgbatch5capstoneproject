package com.altimetrik.route.service;

import com.altimetrik.route.exception.RouteAlreadyExistsException;
import com.altimetrik.route.exception.RouteIdNotExistsException;
import com.altimetrik.route.model.Route;
import com.altimetrik.route.repository.RouteRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;

@SpringBootTest
public class RouteServiceImplTest {

    @Mock
    private RouteRepository routeRepository;

    @InjectMocks
    private RouteServiceImpl routeService;

    @Test
    public void testGetAllRoutes() {
        List<Route> mockRoutes = new ArrayList<>();
        Mockito.when(routeRepository.findAll()).thenReturn(mockRoutes);

        List<Route> result = routeService.getAllRoutes();

        assertNotNull(result);
        assertEquals(0, result.size()); // Assuming the mocked list is empty
    }

    @Test
    public void testGetRouteById() throws RouteIdNotExistsException {
        int routeId = 4620;
        Route mockRoute = new Route();
        Mockito.when(routeRepository.findById(routeId)).thenReturn(Optional.of(mockRoute));

        Route result = routeService.getRouteById(routeId);

        assertNotNull(result);
        assertEquals(mockRoute, result);
    }

    @Test
    public void testAddRoute() throws RouteAlreadyExistsException {

        Route newRoute = new Route();
        Mockito.when(routeRepository.save(any(Route.class))).thenReturn(newRoute);

        Route result = routeService.addRoute(newRoute);

        assertNotNull(result);
        assertEquals(newRoute, result);
    }

    @Test
    public void testUpdateRoute() throws RouteIdNotExistsException {

        Route existingRoute = new Route();
        Mockito.when(routeRepository.save(any(Route.class))).thenReturn(existingRoute);
        Mockito.when(routeRepository.findById(existingRoute.getRouteId())).thenReturn(Optional.of(existingRoute));

        Route result = routeService.updateRoute(existingRoute);

        assertNotNull(result);
        assertEquals(existingRoute, result);
    }

    @Test
    public void testDeleteRoute() throws RouteIdNotExistsException {
        int routeId = 4620;
        Route existingRoute = new Route();
        Mockito.when(routeRepository.findById(routeId)).thenReturn(Optional.of(existingRoute));

        String result = routeService.deleteRoute(routeId);

        assertEquals("Route is deleted successfully", result);
    }
}
