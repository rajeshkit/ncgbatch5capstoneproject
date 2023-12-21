package com.railways.route.service;

import com.railways.route.exception.RouteIdExistsException;
import com.railways.route.exception.RouteNotFindException;
import com.railways.route.model.Route;
import com.railways.route.repository.RouteRepository;
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
    RouteRepository routeRepository;
    @InjectMocks
    RouteServiceImpl routeServiceImpl;

    @Test
    void addRoute() throws RouteIdExistsException {
        Route r=Route.builder().routeId(1).source("HYD").destination("BNG").totalKms(500).build();
        Mockito.when(routeRepository.findById(1L)).thenReturn(Optional.empty());
        Mockito.when(routeRepository.save(r)).thenReturn(r);
        assertEquals(r,routeServiceImpl.addRoute(r));
    }

    @Test
    void addRouteWithException() throws RouteIdExistsException {
        Route r=Route.builder().routeId(1).source("HYD").destination("BNG").totalKms(500).build();
        Mockito.when(routeRepository.findById(1L)).thenReturn(Optional.of(r));
        assertThrows(RouteIdExistsException.class,()->{routeServiceImpl.addRoute(r);});
    }

    @Test
    void getRoute() {
        Route r1=Route.builder().routeId(1).source("HYD").destination("BNG").totalKms(500).build();
        Route r2=Route.builder().routeId(2).source("BNG").destination("KNR").totalKms(800).build();
        Mockito.when(routeRepository.findAll()).thenReturn(Arrays.asList(r1,r2));
        assertEquals(2,routeServiceImpl.getRoute().size());
    }

    @Test
    void getRouteById() throws RouteNotFindException {
        Route r=Route.builder().routeId(1).source("HYD").destination("BNG").totalKms(500).build();
        Mockito.when(routeRepository.findById(1L)).thenReturn(Optional.of(r));
        assertEquals(r,routeServiceImpl.getRouteById(1L));
    }

    @Test
    void getRouteByIdWithException() throws RouteNotFindException {
        Route r=Route.builder().routeId(1).source("HYD").destination("BNG").totalKms(500).build();
        Mockito.when(routeRepository.findById(500L)).thenReturn(Optional.empty());
        assertThrows(RouteNotFindException.class,()-> {routeServiceImpl.getRouteById(500L);});
    }

    @Test
    void updateRoute() throws RouteNotFindException {
        Route r=Route.builder().routeId(1).source("HYD").destination("BNG").totalKms(500).build();
        Mockito.when(routeRepository.findById(1L)).thenReturn(Optional.of(r));
        Mockito.when(routeRepository.save(r)).thenReturn(r);
        assertEquals(r,routeServiceImpl.updateRoute(r));
    }
    @Test
    void updateRouteWithException() throws RouteNotFindException {
        Route r=Route.builder().routeId(1).source("HYD").destination("BNG").totalKms(500).build();
        Mockito.when(routeRepository.findById(1L)).thenReturn(Optional.empty());
        assertThrows(RouteNotFindException.class,()->{routeServiceImpl.updateRoute(r);});
    }

    @Test
    void deleteRoute() throws RouteNotFindException {
        Route r=Route.builder().routeId(1).source("HYD").destination("BNG").totalKms(500).build();
        Mockito.when(routeRepository.findById(1L)).thenReturn(Optional.of(r));
        assertEquals("Route deleted successfully",routeServiceImpl.deleteRoute(1L));
    }
    @Test
    void deleteTrainByIdWithException() throws RouteNotFindException {
        Route r=Route.builder().routeId(1).source("HYD").destination("BNG").totalKms(500).build();
        Mockito.when(routeRepository.findById(2L)).thenReturn(Optional.empty());
        assertThrows(RouteNotFindException.class,()->{routeServiceImpl.deleteRoute(2L);});
    }
}