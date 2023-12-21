package com.altimetrik.Route.service;

import com.altimetrik.Route.exception.RouteIdNotExistsException;
import com.altimetrik.Route.model.Route;
import com.altimetrik.Route.repository.RouteRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import static org.mockito.Mockito.*;

public class RouteServiceImplTest {

    @Mock
    private RouteRepository routeRepository;

    @InjectMocks
    private RouteServiceImpl routeService;

    @BeforeEach
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testAddRoute() {
        Route routeToAdd = new Route(); // Create a Route object with necessary data for addition
        when(routeRepository.save(routeToAdd)).thenReturn(routeToAdd);

        Route addedRoute = routeService.addRoute(routeToAdd);

        Assertions.assertNotNull(addedRoute);
    }

    @Test
    public void testGetAllRoutes() {
        List<Route> routeList = new ArrayList<>();
        when(routeRepository.findAll()).thenReturn(routeList);

        List<Route> retrievedRoutes = routeService.getAllRoutes();

        Assertions.assertEquals(routeList, retrievedRoutes);

    }

    @Test
    public void testGetRouteById_ExistingId() throws RouteIdNotExistsException {
        int routeId = 1;
        Route route = new Route();
        route.setRouteId(routeId);
        when(routeRepository.findById(routeId)).thenReturn(Optional.of(route));

        Route retrievedRoute = routeService.getRouteById(routeId);

        Assertions.assertEquals(route, retrievedRoute);
    }

    @Test
    public void testGetRouteById_NonExistingId() {
        int routeId = 1;
        when(routeRepository.findById(routeId)).thenReturn(Optional.empty());

        Assertions.assertThrows(RouteIdNotExistsException.class, () -> routeService.getRouteById(routeId));

    }

    @Test
    public void testUpdateRoute() throws RouteIdNotExistsException {
        Route routeToUpdate = new Route();
        routeToUpdate.setRouteId(1);
        when(routeRepository.save(routeToUpdate)).thenReturn(routeToUpdate);
        when(routeRepository.findById(routeToUpdate.getRouteId())).thenReturn(Optional.of(routeToUpdate));

        Route updatedRoute = routeService.updateRoute(routeToUpdate);

        Assertions.assertNotNull(updatedRoute);

    }

    @Test
    public void testDeleteRouteById_ExistingId() throws RouteIdNotExistsException {
        int routeId = 1;
        Route routeToDelete = new Route();
        routeToDelete.setRouteId(routeId);
        when(routeRepository.findById(routeId)).thenReturn(Optional.of(routeToDelete));

        String deletionMessage = routeService.deleteRouteById(routeId);

        Assertions.assertEquals("Route deleted successfully", deletionMessage);
    }


    }
