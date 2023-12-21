package com.booking.route.service;

import com.booking.route.exception.RouteIdNotExistsException;
import com.booking.route.model.RouteResources;
import com.booking.route.repository.RouteRepository;
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
    void addRouteResources() {
        RouteResources routeResources= RouteResources.builder()
                .routeId(1234l)
                .source("Kakinada").destination("Hyderabad")
                .totalKms(780.0).build();

        Mockito.when(routeRepository.save(routeResources)).thenReturn(routeResources);
        assertEquals(routeResources, routeServiceImpl.addRouteResources(routeResources));
    }

    @Test
    void getAllRouteResources() {
        RouteResources routeResources= RouteResources.builder()
                .routeId(1234l)
                .source("Kakinada").destination("Hyderabad")
                .totalKms(780.0).build();

        RouteResources routeResources1= RouteResources.builder()
                .routeId(1235l)
                .source("Hyderabad").destination("Kakinada")
                .totalKms(780.0).build();

        Mockito.when(routeRepository.findAll()).thenReturn(Arrays.asList(routeResources,routeResources1));
        assertEquals(2, routeServiceImpl.getAllRouteResources().size());
    }

    @Test
    void getAllRouteResourcesById() throws RouteIdNotExistsException {
        RouteResources routeResources= RouteResources.builder()
                .routeId(1234l)
                .source("Kakinada").destination("Hyderabad")
                .totalKms(780.0).build();

        Mockito.when(routeRepository.findById(routeResources.getRouteId())).thenReturn(Optional.ofNullable(routeResources));
        assertEquals(routeResources, routeServiceImpl.getAllRouteResourcesById(routeResources.getRouteId()));
    }

    @Test
    void getAllRouteResourcesByIdWithException() {

        RouteResources routeResources= RouteResources.builder()
                .routeId(1234l)
                .source("Kakinada").destination("Hyderabad")
                .totalKms(780.0).build();

        Mockito.when(routeRepository.findById(400l)).thenReturn(Optional.empty());

        assertThrows(RouteIdNotExistsException.class,
                ()->{routeServiceImpl.getAllRouteResourcesById(400l);});
    }

    @Test
    void updateRouteResource() throws RouteIdNotExistsException {
        RouteResources routeResources= RouteResources.builder()
                .routeId(1234l)
                .source("Kakinada").destination("Hyderabad")
                .totalKms(780.0).build();

        Mockito.when(routeRepository.findById(1234l)).thenReturn(Optional.ofNullable(routeResources));
        Mockito.when(routeRepository.save(routeResources)).thenReturn(routeResources);
        assertEquals(routeResources,routeServiceImpl.updateRouteResource(routeResources));
    }

    @Test
    void updateTrainResourceWithException(){
        RouteResources routeResources= RouteResources.builder()
                .routeId(1234l)
                .source("Kakinada").destination("Hyderabad")
                .totalKms(780.0).build();

        Mockito.when(routeRepository.findById(1234l)).thenReturn(Optional.empty());
        assertThrows(RouteIdNotExistsException.class,
                ()->{routeServiceImpl.updateRouteResource(routeResources);});
    }

    @Test
    void deleteRouteResourceByTrainNumber() throws RouteIdNotExistsException {
        RouteResources routeResources= RouteResources.builder()
                .routeId(1234l)
                .source("Kakinada").destination("Hyderabad")
                .totalKms(780.0).build();

        Mockito.when(routeRepository.findById(routeResources.getRouteId())).thenReturn(Optional.of(routeResources));
        assertEquals("Route Resource deleted by this Route Id successfully", routeServiceImpl.deleteRouteResourceById(routeResources.getRouteId()));
    }


    @Test
    void deleteRouteResourceByIdWithException() {
        RouteResources routeResources= RouteResources.builder()
                .routeId(1234l)
                .source("Kakinada").destination("Hyderabad")
                .totalKms(780.0).build();

        Mockito.when(routeRepository.findById(1234l)).thenReturn(Optional.empty());
        //assertEquals(100,productServiceImpl.getProductById(100).getProductId());
        assertThrows(RouteIdNotExistsException.class,
                ()->{routeServiceImpl.deleteRouteResourceById(routeResources.getRouteId());});
    }
}