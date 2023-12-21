package com.altimetrik.route.controller;

import com.altimetrik.route.exception.RouteAlreadyExistsException;
import com.altimetrik.route.exception.RouteIdNotExistsException;
import com.altimetrik.route.model.Route;
import com.altimetrik.route.service.RouteService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;

@SpringBootTest
public class RouteControllerTest {

    @Mock
    private RouteService routeService;

    @InjectMocks
    private RouteController routeController;

    @Test
    public void testGetAllRoutes() {

        List<Route> mockRoutes = new ArrayList<>();
        Mockito.when(routeService.getAllRoutes()).thenReturn(mockRoutes);

        List<Route> result = routeController.getAllRoutes();

        assertNotNull(result);
        assertEquals(0, result.size()); // Assuming the mocked list is empty
    }

    @Test
    public void testGetRouteById() throws RouteIdNotExistsException {
        int routeId = 4620;
        Route mockRoute = new Route();
        Mockito.when(routeService.getRouteById(routeId)).thenReturn(mockRoute);

        Route result = routeController.getRouteById(routeId);

        assertNotNull(result);
        assertEquals(mockRoute, result);
    }

    @Test
    public void testAddRoute() throws RouteAlreadyExistsException {
        Route newRoute = new Route();
        Mockito.when(routeService.addRoute(any(Route.class))).thenReturn(newRoute);

        Route result = routeController.addRoute(newRoute);

        assertNotNull(result);
        assertEquals(newRoute, result);
    }

    @Test
    public void testUpdateRoute() throws RouteIdNotExistsException {

        Route existingRoute = new Route();
        Mockito.when(routeService.updateRoute(any(Route.class))).thenReturn(existingRoute);

        Route result = routeController.updateRoute(existingRoute);

        assertNotNull(result);
        assertEquals(existingRoute, result);
    }

    @Test
    public void testDeleteRoute() throws RouteIdNotExistsException {

        int routeId = 4620;
        Mockito.when(routeService.deleteRoute(routeId)).thenReturn("Route deleted successfully");

        String result = routeController.deleteRoute(routeId);

        assertEquals("Route deleted successfully", result);
    }
}
