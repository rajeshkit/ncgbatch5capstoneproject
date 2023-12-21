package com.RouteMicroservices.Route.Services;

import com.RouteMicroservices.Route.Exception.RouteIdNotFoundException;
import com.RouteMicroservices.Route.Repository.RouteResourcesRepository;
import com.RouteMicroservices.Route.model.RouteResources;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;


@ExtendWith(MockitoExtension.class)
class RouteServiceImplTest {


    @InjectMocks
    private RouteServiceImpl routeServiceImpl;
    @Mock
    private RouteResourcesRepository routeResourcesRepository;

    @Test
    void addRouteResources() {
        RouteResources t1=RouteResources.builder().routeId(123L).source("Hyderabad").destination("Pune").totalKms(12.4).build();
        Mockito.when(routeResourcesRepository.save(t1)).thenReturn(t1);
        assertEquals(t1,routeServiceImpl.addRouteResources(t1));
    }

    @Test
    void getAllRouteDetail() {
        RouteResources t1=RouteResources.builder().routeId(123L).source("Hyderabad").destination("Pune").totalKms(12.4).build();
        RouteResources t2=RouteResources.builder().routeId(873L).source("Chennai").destination("Pune").totalKms(12.4).build();
        Mockito.when(routeResourcesRepository.findAll()).thenReturn(Arrays.asList(t1,t2));
        assertEquals(2,routeServiceImpl.getAllRouteDetail().size());
    }

    @Test
    void getRouteById() throws RouteIdNotFoundException {
        RouteResources t1=RouteResources.builder().routeId(123L).source("Hyderabad").destination("Pune").totalKms(12.4).build();
        Mockito.when(routeResourcesRepository.findById(123L)).thenReturn(Optional.of(t1));
        assertEquals(123L,routeServiceImpl.getRouteById(123L).getRouteId());
    }

    @Test
    void updateRouteDetail() throws RouteIdNotFoundException {
        RouteResources t1=RouteResources.builder().routeId(123L).source("Hyderabad").destination("Pune").totalKms(12.4).build();
        Mockito.when(routeResourcesRepository.save(t1)).thenReturn(t1);
        assertEquals(t1,routeServiceImpl.addRouteResources(t1));
    }

    @Test
    void deleteRouteById() throws RouteIdNotFoundException {
        RouteResources t1=RouteResources.builder().routeId(123L).source("Hyderabad").destination("Pune").totalKms(12.4).build();
        Mockito.when(routeResourcesRepository.findById(123L)).thenReturn(Optional.of(t1));
        assertEquals("Route detail deleted successfully",routeServiceImpl.deleteRouteById(123L));
    }
}