package com.altimetrik.trainroute.service;

import com.altimetrik.trainroute.exception.RouteIdNotExistsException;
import com.altimetrik.trainroute.exception.RouteIdNotExistsException;
import com.altimetrik.trainroute.model.Route;
import com.altimetrik.trainroute.model.Route;
import com.altimetrik.trainroute.repository.RouteRepository;
import com.altimetrik.trainroute.repository.RouteRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;
import java.util.Date;
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
        Route r1 = Route.builder()
                .routeId(100).Source("Los Angeles")
                .Destination("New York").totalKilometers(34543).build();
        Mockito.when(routeRepository.save(r1)).thenReturn(r1);
        assertEquals(r1,routeServiceImpl.addRoute(r1));
    }

    @Test
    void getAllRoutes() {
        Route r1 = Route.builder()
                .routeId(100).Source("Los Angeles")
                .Destination("New York").totalKilometers(34543).build();
        Route r2 = Route.builder()
                .routeId(101).Source("Kashmir")
                .Destination("Kanyakumari").totalKilometers(6867).build();
        Mockito.when(routeRepository.findAll()).thenReturn(Arrays.asList(r1,r2));
        assertEquals(2,routeServiceImpl.getAllRoute().size());
    }

    @Test
    void getRouteById() throws RouteIdNotExistsException {
        Route r1 = Route.builder()
                .routeId(100).Source(" Los Angeles")
                .Destination("New York").totalKilometers(34543).build();

        Mockito.when(routeRepository.findById(100)).thenReturn(Optional.of(r1));
        assertEquals(100,routeServiceImpl.getRouteById(100).getRouteId());
    }
    @Test
    void getRouteByIdWithException()  {
        Route r1 = Route.builder()
                .routeId(100).Source("Los Angeles")
                .Destination("New York").totalKilometers(34543).build();
        Mockito.when(routeRepository.findById(400)).thenReturn(Optional.empty());
        assertThrows(RouteIdNotExistsException.class,
                ()->{routeServiceImpl.getRouteById(400);});
    }

    @Test
    void updateRoute() {
    }

    @Test
    void deleteRouteId() {
    }
}