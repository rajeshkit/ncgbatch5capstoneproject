package com.altimetrik.trainroute.service;

import com.altimetrik.trainroute.exception.NoSuchElementException;
import com.altimetrik.trainroute.modle.Route;
import com.altimetrik.trainroute.repository.RouteRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
@ExtendWith(MockitoExtension.class)

public class RouteServiceImpTest {
    @Mock
    private RouteService routeService;
    @Mock
    private RouteRepository routeRepository;
    @InjectMocks
    private RouteServiceImp routeServiceImp;

    @Test
    public void testGetAllRoutes() {
        Route route=Route.builder().routeId(1).source("dhgx").destination("jhg").build();
        Route route1=Route.builder().routeId(2).source("dhgxkj").destination("jhgjhg").build();
        List<Route> mockRoutes = new ArrayList<>();
        Mockito.when(routeRepository.findAll()).thenReturn(Arrays.asList(route,route1));

        List<Route> result = routeService.getAllRoute();

        assertNotNull(result);
        assertEquals(2, routeServiceImp.getAllRoute().size());
    }

    @Test
    public void testGetRouteById() throws Exception {
        Route route=Route.builder().routeId(1).source("dhgx").destination("jhg").build();
        int routeId = 4620;
        Route mockRoute = new Route();
        Mockito.when(routeRepository.findById(route.getRouteId())).thenReturn(Optional.of(route));

        Route result = routeService.getRouteById(routeId);

        assertEquals(1, routeServiceImp.getRouteById(1).getRouteId());
    }

    @Test
    void addRoute() {
        Route route=Route.builder()
                .routeId(1)
                .source("davangere")
                .destination("Bengaluru")
                .totalKms(200)
                .build();
        Mockito.when(routeRepository.save(route)).thenReturn(route);
        assertEquals(route,routeServiceImp.addRoute(route));
    }

    @Test
    void updateRoute() throws Exception {
        Route route=Route.builder()
                .routeId(1)
                .source("Davangere")
                .destination("Bengaluru")
                .totalKms(200)
                .build();
        Mockito.when(routeRepository.findById(route.getRouteId())).thenReturn(Optional.of(route));
        Mockito.when(routeRepository.save(route)).thenReturn(route);
        assertEquals(route,routeServiceImp.updateRoute(route));
    }

    @Test
    void deleteRouteById() throws Exception {
        Route r1=Route.builder()
                .routeId(1)
                .source("Davangere")
                .destination("Bengaluru")
                .totalKms(200)
                .build();
        Mockito.when(routeRepository.findById(r1.getRouteId())).thenReturn(Optional.of(r1));
        String result= routeServiceImp.deleteRouteById(r1.getRouteId());
        assertEquals("Route deleted successfully",result);
    }
}
