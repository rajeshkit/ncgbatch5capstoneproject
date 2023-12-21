package com.altimetrik.routemicroservice.service;

import com.altimetrik.routemicroservice.exception.RouteIdAlreadyExistsException;
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
    void addRoute() throws RouteIdNotExistsException, RouteIdAlreadyExistsException {

        Route r= Route.builder()
                .routeId(100)
                .Source("Chennai")
                .Destination("Bangalore")
                .totalKms(200).build();

        Mockito.when(routeRepository.save(r)).thenReturn(r);
        assertEquals(r,routeServiceImpl.addRoute(r));
    }
    @Test
    void addRouteWithException(){

        Route r= Route.builder()
                .routeId(100)
                .Source("Chennai")
                .Destination("Bangalore")
                .totalKms(200).build();

        Mockito.when(routeRepository.findById(101)).thenReturn(Optional.empty());
        assertThrows(RouteIdNotExistsException.class,
                ()->routeServiceImpl.getRouteById(101));
    }
    @Test
    void getAllRoute() {

        Route r1= Route.builder()
                .routeId(100)
                .Source("Chennai")
                .Destination("Bangalore")
                .totalKms(300).build();

        Route r2= Route.builder()
                .routeId(101)
                .Source("Bangalore")
                .Destination("Coimbatore")
                .totalKms(100).build();

        Mockito.when(routeRepository.findAll()).thenReturn(Arrays.asList(r1,r2));
        assertEquals(2,routeServiceImpl.getAllRoute().size());
    }

    @Test
    void getRouteById() throws RouteIdNotExistsException{

        Route r= Route.builder()
                .routeId(100)
                .Source("Chennai")
                .Destination("Bangalore")
                .totalKms(300).build();

        Mockito.when(routeRepository.findById(100)).thenReturn(Optional.of(r));
        assertEquals(100,routeServiceImpl
                .getRouteById(100)
                .getRouteId());
    }

    @Test
    void getRouteByIdWithException(){

        Route r= Route.builder()
                .routeId(100)
                .Source("Chennai")
                .Destination("Bangalore")
                .totalKms(300).build();

        Mockito.when(routeRepository.findById(101)).thenReturn(Optional.empty());
        assertThrows(RouteIdNotExistsException.class,
                ()->routeServiceImpl.getRouteById(101));
    }

    @Test
    void updateRoute() throws RouteIdNotExistsException {

        Route r= Route.builder()
                .routeId(100)
                .Source("Chennai")
                .Destination("Bangalore")
                .totalKms(300).build();

        Mockito.when(routeRepository.findById(100)).thenReturn(Optional.of(r));

        Route updatedRoute= Route.builder()
                .routeId(100)
                .Source("Karur")
                .Destination("Coimbatore")
                .totalKms(100).build();

        Mockito.when(routeRepository.save(updatedRoute)).thenReturn(updatedRoute);
        assertEquals(updatedRoute,routeServiceImpl.updateRoute(updatedRoute) );

    }

    @Test
    void deleteRouteById() throws RouteIdNotExistsException {

        Route r= Route.builder()
                .routeId(100)
                .Source("Chennai")
                .Destination("Bangalore")
                .totalKms(300).build();

        Mockito.when(routeRepository.findById(100)).thenReturn(Optional.of(r));
        assertEquals("Route Deleted successfully",routeServiceImpl.deleteRouteById(100));

    }
}