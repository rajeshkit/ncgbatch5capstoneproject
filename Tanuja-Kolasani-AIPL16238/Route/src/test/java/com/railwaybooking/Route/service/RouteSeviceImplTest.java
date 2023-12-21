package com.railwaybooking.Route.service;

import com.railwaybooking.Route.exception.RouteIdNotFoundException;
import com.railwaybooking.Route.model.RouteInfo;
import com.railwaybooking.Route.repository.RouteRepository;
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
class RouteSeviceImplTest {
    @Mock
    RouteRepository routeRepository;
    @InjectMocks
    RouteServiceImpl routeServiceImpl;

    @Test
    void addRoute(){
        RouteInfo r=RouteInfo.builder().routeId(123).source("Delhi").destination("Jaipur").totalKms(500).build();
        Mockito.when(routeRepository.save(r)).thenReturn(r);
        assertEquals(r,routeServiceImpl.addRoute(r));
    }
    @Test
    void getAllRoutes(){
        RouteInfo r1=RouteInfo.builder().routeId(123).source("Delhi").destination("Jaipur").totalKms(500).build();
        RouteInfo r2=RouteInfo.builder().routeId(2).source("BNG").destination("KNR").totalKms(800).build();
        Mockito.when(routeRepository.findAll()).thenReturn(Arrays.asList(r1,r2));
        assertEquals(2,routeServiceImpl.getAllRoutes().size());
    }
    @Test
    void getRouteById() throws RouteIdNotFoundException{
        RouteInfo r=RouteInfo.builder().routeId(123).source("Delhi").destination("Jaipur").totalKms(500).build();
        Mockito.when(routeRepository.findById(123L)).thenReturn(Optional.of(r));
        assertEquals(r,routeServiceImpl.getRouteById(123L));
    }
    @Test
    void getRouteByIdWithException() throws RouteIdNotFoundException{
        RouteInfo r=RouteInfo.builder().routeId(123).source("Delhi").destination("Jaipur").totalKms(500).build();
        Mockito.when(routeRepository.findById(500L)).thenReturn(Optional.empty());
        assertThrows(RouteIdNotFoundException.class,()->{routeServiceImpl.getRouteById(500L);});
    }
    @Test
    void updateRouteInfo() throws RouteIdNotFoundException{
        RouteInfo r=RouteInfo.builder().routeId(123).source("Delhi").destination("Jaipur").totalKms(500).build();
        Mockito.when(routeRepository.findById(123L)).thenReturn(Optional.of(r));
        Mockito.when(routeRepository.save(r)).thenReturn(r);
        assertEquals(r,routeServiceImpl.updateRouteInfo(r));
    }
    @Test
    void deleteRouteById() throws RouteIdNotFoundException{
        RouteInfo r=RouteInfo.builder().routeId(123).source("Delhi").destination("Jaipur").totalKms(500).build();
        Mockito.when(routeRepository.findById(123L)).thenReturn(Optional.of(r));
        assertEquals("Route deleted Successfully",routeServiceImpl.deleteRouteById(123L));
    }
}
