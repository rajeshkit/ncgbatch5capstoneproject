package com.altimetrik.routemicroservice.service;

import com.altimetrik.routemicroservice.exceptions.RouteIdNotExistsException;
import com.altimetrik.routemicroservice.model.Route;
import com.altimetrik.routemicroservice.repsitory.RouteRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyString;

@ExtendWith(MockitoExtension.class)
public class RouteServiceImplementationTests {

    @Mock
    private RouteRepository routeRepository;

    @InjectMocks
    private RouteServiceImplementation routeService;

    @Test
    void addRoute() {
        Route routeToAdd = new Route("R0001", "Bengaluru", "Chennai", 400);

        Mockito.when(routeRepository.save(routeToAdd)).thenReturn(routeToAdd);

        Route addedRoute = routeService.addRoute(routeToAdd);

        assertEquals(routeToAdd, addedRoute);
    }

    @Test
    void getAllRoutes() {
        Route route1 = new Route("R0002", "Tumkur", "Kadur", 200);
        Route route2 = new Route("R0003", "Birur", "Hassan", 300);

        Mockito.when(routeRepository.findAll()).thenReturn(Arrays.asList(route1, route2));

        List<Route> allRoutes = routeService.getAllRoutes();

        assertEquals(2, allRoutes.size());
    }

    @Test
    void getRouteById() throws RouteIdNotExistsException {
        Route routeToFind = new Route("R0004", "Hoysala", "Ambur", 500);

        Mockito.when(routeRepository.findById(routeToFind.getRouteId())).thenReturn(Optional.of(routeToFind));

        Route foundRoute = routeService.getRouteById(routeToFind.getRouteId());

        assertEquals(routeToFind, foundRoute);
    }

    @Test
    void getRouteByIdWithException() {
        Mockito.when(routeRepository.findById(anyString())).thenReturn(Optional.empty());

        assertThrows(RouteIdNotExistsException.class,
                () -> routeService.getRouteById("NonExistentRouteId"));
    }

    @Test
    void updateRoute() throws RouteIdNotExistsException {
        Route routeToUpdate = new Route("R0001", "Hassan", "Malleshwaram", 8);

        Mockito.when(routeRepository.existsById(routeToUpdate.getRouteId())).thenReturn(true);
        Mockito.when(routeRepository.save(routeToUpdate)).thenReturn(routeToUpdate);

        Route updatedRoute = routeService.updateRoute(routeToUpdate);

        assertEquals(routeToUpdate, updatedRoute);
    }

    @Test
    void updateRouteWithException() {
        Route routeToUpdate = new Route("X0001", "Patna", "Shivmogga", 560);

        Mockito.when(routeRepository.existsById(routeToUpdate.getRouteId())).thenReturn(false);

        assertThrows(RouteIdNotExistsException.class,
                () -> routeService.updateRoute(routeToUpdate));
    }

    @Test
    void deleteRouteById() throws RouteIdNotExistsException {
        String routeIdToDelete = "R0001";

        Mockito.when(routeRepository.existsById(routeIdToDelete)).thenReturn(true);

        String result = routeService.deleteRouteById(routeIdToDelete);

        assertEquals("Route deleted successfully", result);
        Mockito.verify(routeRepository, Mockito.times(1)).deleteById(routeIdToDelete);
    }

    @Test
    void deleteRouteByIdWithException() {
        String nonExistentRouteId = "X0002";

        Mockito.when(routeRepository.existsById(nonExistentRouteId)).thenReturn(false);

        assertThrows(RouteIdNotExistsException.class,
                () -> routeService.deleteRouteById(nonExistentRouteId));
    }
}