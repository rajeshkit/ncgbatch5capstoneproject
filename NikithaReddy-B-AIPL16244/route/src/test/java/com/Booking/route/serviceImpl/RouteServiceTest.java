package com.Booking.route.serviceImpl;

import com.Booking.route.customexception.RouteNotFindException;
import com.Booking.route.model.RouteResources;
import com.Booking.route.repository.RouteRepository;
import com.Booking.route.service.RouteServiceImplementation;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
@ExtendWith(MockitoExtension.class)
public class RouteServiceTest {
    @Mock
    RouteRepository routeRepository;
    @InjectMocks
    RouteServiceImplementation routeServiceImpl;

    @Test
    void addRoute() {
        RouteResources r=RouteResources.builder().routeId(100L).source("rajasthan").destination("mumbai").totalkms(500.0).build();
        Mockito.when(routeRepository.save(r)).thenReturn(r);
        assertEquals(r,routeServiceImpl.addRoute(r));
    }
    @Test
    void getRoute() {
        RouteResources r1=RouteResources.builder().routeId(100L).source("rajasthan").destination("mumbai").totalkms(500.0).build();
        RouteResources r2=RouteResources.builder().routeId(1234L).source("gujarath").destination("hyd").totalkms(350.0).build();
        Mockito.when(routeRepository.findAll()).thenReturn(Arrays.asList(r1,r2));
        assertEquals(2,routeServiceImpl.getRoute().size());
    }

    @Test
    void getRouteById() throws RouteNotFindException {
        RouteResources r=RouteResources.builder().routeId(100L).source("rajasthan").destination("mumbai").totalkms(500.0).build();
        Mockito.when(routeRepository.findById(100L)).thenReturn(Optional.of(r));
        assertEquals(r,routeServiceImpl.getRouteById(100L));
    }

    @Test
    void getRouteByIdWithException() throws RouteNotFindException {
        RouteResources r=RouteResources.builder().routeId(100L).source("rajasthan").destination("mumbai").totalkms(500.0).build();
        Mockito.when(routeRepository.findById(500L)).thenReturn(Optional.empty());
        assertThrows(RouteNotFindException.class,()-> {routeServiceImpl.getRouteById(500L);});
    }

    @Test
    void updateRoute() throws RouteNotFindException {
        RouteResources r=RouteResources.builder().routeId(100L).source("rajasthan").destination("mumbai").totalkms(500.0).build();
        Mockito.when(routeRepository.findById(100L)).thenReturn(Optional.of(r));
        Mockito.when(routeRepository.save(r)).thenReturn(r);
        assertEquals(r,routeServiceImpl.updateRoute(r));
    }

    @Test
    void deleteRoute() throws RouteNotFindException {
        RouteResources r=RouteResources.builder().routeId(100L).source("rajasthan").destination("mumbai").totalkms(500.0).build();
        Mockito.when(routeRepository.findById(100L)).thenReturn(Optional.of(r));
        assertEquals("Route deleted succesfully",routeServiceImpl.deleteRoute(100L));
    }
}
