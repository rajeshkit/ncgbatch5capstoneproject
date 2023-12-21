package com.altimetrik.routemicroservice.controller;

import com.altimetrik.routemicroservice.exceptions.RouteIdNotExistsException;
import com.altimetrik.routemicroservice.model.Route;
import com.altimetrik.routemicroservice.service.RouteService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class RouteControllerTests {

    @Mock
    private RouteService routeService;

    @InjectMocks
    private RouteController routeController;

    @Test
    void addRoute() {
        Route routeToAdd = new Route("R0001", "SourceCity", "DestinationCity", 200);

        Mockito.when(routeService.addRoute(routeToAdd)).thenReturn(routeToAdd);

        ResponseEntity<?> responseEntity = routeController.addRoute(routeToAdd, Mockito.mock(BindingResult.class));

        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
        assertEquals(routeToAdd, responseEntity.getBody());
    }

    @Test
    void addRouteWithValidationErrors() {
        // Mocking validation errors
        BindingResult bindingResult = Mockito.mock(BindingResult.class);
        Mockito.when(bindingResult.hasErrors()).thenReturn(true);

        // Creating controller instance
        RouteController controller = new RouteController();

        // Invoking the method with validation errors
        ResponseEntity<?> responseEntity = controller.addRoute(Mockito.mock(Route.class), bindingResult);

        // Asserting that it returns a Bad Request status
        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
    }

    @Test
    void getAllRoutes() {
        Route route1 = new Route("R0001", "SourceCity1", "DestinationCity1", 200);
        Route route2 = new Route("R0002", "SourceCity2", "DestinationCity2", 300);

        Mockito.when(routeService.getAllRoutes()).thenReturn(Arrays.asList(route1, route2));

        ResponseEntity<List<Route>> responseEntity = routeController.getAllRoutes();

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(2, Objects.requireNonNull(responseEntity.getBody()).size());
    }

    @Test
    void getAllRoutesWithNoRoutes() {
        Mockito.when(routeService.getAllRoutes()).thenReturn(List.of());

        ResponseEntity<List<Route>> responseEntity = routeController.getAllRoutes();

        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
    }

    @Test
    void getRouteById() throws RouteIdNotExistsException {
        String routeId = "R0001";
        Route routeToFind = new Route(routeId, "SourceCity", "DestinationCity", 200);

        Mockito.when(routeService.getRouteById(routeId)).thenReturn(routeToFind);

        ResponseEntity<?> responseEntity = routeController.getRouteById(routeId);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(routeToFind, responseEntity.getBody());
    }

    @Test
    void getRouteByIdNotFound() throws RouteIdNotExistsException {
        String nonExistentRouteId = "NonExistentRouteId";

        Mockito.when(routeService.getRouteById(nonExistentRouteId)).thenThrow(new RouteIdNotExistsException(""));

        ResponseEntity<?> responseEntity = routeController.getRouteById(nonExistentRouteId);

        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
    }

    @Test
    void updateRoute() throws RouteIdNotExistsException {
        Route routeToUpdate = new Route("R0001", "UpdatedSourceCity", "UpdatedDestinationCity", 300);

        Mockito.when(routeService.updateRoute(routeToUpdate)).thenReturn(routeToUpdate);

        ResponseEntity<?> responseEntity = routeController.updateRoute(routeToUpdate, Mockito.mock(BindingResult.class));

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(routeToUpdate, responseEntity.getBody());
    }

    @Test
    void updateRouteNotFound() throws RouteIdNotExistsException {
        Route nonExistentRoute = new Route("NonExistentRouteId", "UpdatedSourceCity", "UpdatedDestinationCity", 300);

        Mockito.when(routeService.updateRoute(nonExistentRoute)).thenThrow(new RouteIdNotExistsException(""));

        ResponseEntity<?> responseEntity = routeController.updateRoute(nonExistentRoute, Mockito.mock(BindingResult.class));

        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
    }

    @Test
    void deleteRouteById() throws RouteIdNotExistsException {
        String routeIdToDelete = "R0001";

        Mockito.when(routeService.deleteRouteById(routeIdToDelete)).thenReturn("Route deleted successfully");

        ResponseEntity<String> responseEntity = routeController.deleteRouteById(routeIdToDelete);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals("Route with ID R0001 deleted successfully.", responseEntity.getBody());
    }

    @Test
    void deleteRouteByIdNotFound() throws RouteIdNotExistsException {
        String nonExistentRouteId = "NonExistentRouteId";

        Mockito.when(routeService.deleteRouteById(nonExistentRouteId)).thenThrow(new RouteIdNotExistsException(""));

        ResponseEntity<String> responseEntity = routeController.deleteRouteById(nonExistentRouteId);

        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
    }
}