package com.railways.route.services;

import com.railways.route.exceptions.RouteIdNotFound;
import com.railways.route.model.Route;
import com.railways.route.respository.RouteRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
class RouteServicesImplTest {
    @Mock
    private RouteRepository routeRepository;
    @InjectMocks
    private RouteServicesImpl routeServicesImpl;

    @Test
    void addRouteDeatils() {
        Route route=Route.builder().routeId(1234).source("choutuppal").destination("hyderabad").totalKms(200).build();
        Mockito.when(routeRepository.save(route)).thenReturn(route);
        assertEquals(route,routeServicesImpl.addRouteDeatils(route));
    }

    @Test
    void getRouteById() throws RouteIdNotFound {
        Route route=Route.builder().routeId(1234).source("choutuppal").destination("hyderabad").totalKms(200).build();
        Mockito.when(routeRepository.findById(route.getRouteId())).thenReturn(Optional.ofNullable(route));
        assertEquals(route,routeServicesImpl.getRouteById(route.getRouteId()));
    }
    @Test
    void getRouteByIdWithException(){
        Route route=Route.builder().routeId(1234).source("choutuppal").destination("hyderabad").totalKms(200).build();
        Mockito.when(routeRepository.findById(route.getRouteId())).thenReturn(Optional.empty());
        assertThrows(RouteIdNotFound.class,()->{routeServicesImpl.getRouteById(1234L);
        });
    }

    @Test
    void getAllRoutes() {
        Route route=Route.builder().routeId(1234).source("choutuppal").destination("hyderabad").totalKms(200).build();
        Route route2=Route.builder().routeId(1234).source("choutuppal").destination("hyderabad").totalKms(200).build();
        Mockito.when(routeRepository.findAll()).thenReturn(Arrays.asList(route,route2));
        assertEquals(2,routeServicesImpl.getAllRoutes().size());
    }

    @Test
    void updateByRouteId() throws RouteIdNotFound {
        Route route=Route.builder().routeId(1234).source("choutuppal").destination("hyderabad").totalKms(200).build();
        Mockito.when(routeRepository.findById(route.getRouteId())).thenReturn(Optional.ofNullable(route));
        Mockito.when(routeRepository.save(route)).thenReturn(route);
        assertEquals(route, routeServicesImpl.updateByRouteId(route));
    }
    @Test
    void updateByRouteIdWithException(){
        Route route=Route.builder().routeId(1234).source("choutuppal").destination("hyderabad").totalKms(200).build();
        Mockito.when(routeRepository.findById(route.getRouteId())).thenReturn(Optional.empty());
        assertThrows(RouteIdNotFound.class,()->{routeServicesImpl.updateByRouteId(route);
        });
    }

    @Test
    void deleteByRouteId() throws RouteIdNotFound {
        Route route=Route.builder().routeId(1234).source("choutuppal").destination("hyderabad").totalKms(200).build();
        Mockito.when(routeRepository.findById(route.getRouteId())).thenReturn(Optional.of(route));
        assertEquals("details are sucessfuly deleted  "+route.getRouteId(),routeServicesImpl.deleteByRouteId(route.getRouteId()));
    }
@Test
void deleteByTrainNumberWithException() {
    Route route=Route.builder().routeId(1234).source("choutuppal").destination("hyderabad").totalKms(200).build();


    Mockito.when(routeRepository.findById(route.getRouteId())).thenReturn(Optional.empty());
    assertThrows(RouteIdNotFound.class,
            () -> {
                routeServicesImpl.deleteByRouteId(route.getRouteId());
            });
}
}