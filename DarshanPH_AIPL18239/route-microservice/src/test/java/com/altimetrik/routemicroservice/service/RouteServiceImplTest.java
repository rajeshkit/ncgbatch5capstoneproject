package com.altimetrik.routemicroservice.service;

import com.altimetrik.routemicroservice.exception.RouteIdNotExistsException;
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
class RouteServiceImplTest {
    @Mock
    private RouteRepository routeRepository;
    @InjectMocks
    private RouteServiceImpl routeServiceImpl;

    @Test
    void addRoute() {
        Route r1=Route.builder()
                .routeId(1)
                .source("davangere")
                .destination("Bengaluru")
                .totalKms(200)
                .build();
        Mockito.when(routeRepository.save(r1)).thenReturn(r1);
        assertEquals(r1,routeServiceImpl.addRoute(r1));
    }

    @Test
    void getAllRoute() {
        Route r1=Route.builder()
                .routeId(1)
                .source("Davangere")
                .destination("Bengaluru")
                .totalKms(200)
                .build();
        Route r2=Route.builder()
                .routeId(2)
                .source("shivmogga")
                .destination("mulyangiri")
                .totalKms(100)
                .build();
        Mockito.when(routeRepository.findAll()).thenReturn(Arrays.asList(r1,r2));
        assertEquals(2,routeServiceImpl.getAllRoute().size());
    }

    @Test
    void getRouteById() throws RouteIdNotExistsException {
        Route r1=Route.builder()
                .routeId(1)
                .source("Davangere")
                .destination("Bengaluru")
                .totalKms(200)
                .build();
        Mockito.when(routeRepository.findById(r1.getRouteId())).thenReturn(Optional.of(r1));
        assertEquals(1,routeServiceImpl.getRouteById(1).getRouteId());
    }

    @Test
    void updateRoute() throws RouteIdNotExistsException {
        Route r1=Route.builder()
                .routeId(1)
                .source("Davangere")
                .destination("Bengaluru")
                .totalKms(200)
                .build();
        Mockito.when(routeRepository.findById(r1.getRouteId())).thenReturn(Optional.of(r1));
        Mockito.when(routeRepository.save(r1)).thenReturn(r1);
        assertEquals(r1,routeServiceImpl.updateRoute(r1));
    }

    @Test
    void deleteRouteById() throws RouteIdNotExistsException {
        Route r1=Route.builder()
                .routeId(1)
                .source("Davangere")
                .destination("Bengaluru")
                .totalKms(200)
                .build();
        Mockito.when(routeRepository.findById(r1.getRouteId())).thenReturn(Optional.of(r1));
        String result= routeServiceImpl.deleteRouteById(r1.getRouteId());
        assertEquals("Route deleted successfully",result);
    }
}