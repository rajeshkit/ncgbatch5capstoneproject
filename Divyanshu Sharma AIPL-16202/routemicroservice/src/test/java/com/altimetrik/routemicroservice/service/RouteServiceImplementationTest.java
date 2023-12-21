package com.altimetrik.routemicroservice.service;

import com.altimetrik.routemicroservice.exception.RouteIdNotExistException;
import com.altimetrik.routemicroservice.model.Route;
import com.altimetrik.routemicroservice.repository.RouteRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class RouteServiceImplementationTest {

    @Mock
    private RouteRepository routeRepository;

    @InjectMocks
    private  RouteServiceImplementation routeServiceImplementation;

    @Test
    void addRouteDetail()
    {
        Route r1 = Route.builder()
                .routeId(123).routeSource("Jaipur").routeDestination("Delhi").totalKm(690).build();

        Mockito.when(routeRepository.save(r1)).thenReturn(r1);
        assertEquals(r1,routeServiceImplementation.addRouteDetail(r1));

    }

    @Test
    void getRouteById() throws RouteIdNotExistException {
        Route route1 = Route.builder()
                .routeId(123).routeSource("Jaipur").routeDestination("Delhi").totalKm(690).build();

        Mockito.when(routeRepository.findById(123)).thenReturn(Optional.of(route1));
        assertEquals(123,routeServiceImplementation.getRouteById(123).getRouteId());
    }

    @Test
    void getAllRouteDetails()
    {
        Route route1 = Route.builder()
                .routeId(123).routeSource("Jaipur").routeDestination("Delhi").totalKm(690).build();
        Route route2 = Route.builder()
                .routeId(1234).routeSource("Baran").routeDestination("Jaipur").totalKm(445).build();

        Mockito.when(routeRepository.findAll()).thenReturn(Arrays.asList(route1,route2));
        assertEquals(2,routeServiceImplementation.getAllRouteDetails().size());
    }
    @Test
    void getRouteByIdWithException() throws RouteIdNotExistException {
        Route route1 = Route.builder()
                .routeId(123).routeSource("Jaipur").routeDestination("Delhi").totalKm(690).build();

        Mockito.when(routeRepository.findById(123)).thenReturn(Optional.of(route1));
        assertEquals(123, routeServiceImplementation.getRouteById(123).getRouteId());
    }
}