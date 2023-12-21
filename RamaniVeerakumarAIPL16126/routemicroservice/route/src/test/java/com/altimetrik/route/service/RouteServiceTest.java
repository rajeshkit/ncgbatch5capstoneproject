package com.altimetrik.route.service;

import com.altimetrik.route.exception.RouteIdDoNotExitsException;
import com.altimetrik.route.model.Route;
import com.altimetrik.route.repository.RouteRepository;
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
public class RouteServiceTest{
    @Mock
    private RouteRepository routeRepository;
    @InjectMocks
    private RouteServiceImp routeServiceImp;
    @Test
    void addRoutes() {
        Route route = Route.builder().routeId(1).source("chennai").destination("bengaluru")
                .totalKms(200).build();
        Mockito.when(routeRepository.save(route)).thenReturn(route);
        assertEquals(route,routeServiceImp.addRoute(route));
    }
    @Test
    void getAllRoutes() {
        Route route = Route.builder().routeId(1).source("chennai").destination("bengaluru")
                .totalKms(200).build();
        Route route1 = Route.builder().routeId(2).source("dindigul").destination("bengaluru")
                .totalKms(400).build();
        Mockito.when(routeRepository.findAll()).thenReturn(Arrays.asList(route,route1));
        assertEquals(2,routeServiceImp.getAllRoutes().size());
    }
    @Test
    void getRouteById() throws RouteIdDoNotExitsException {
        Route route = Route.builder().routeId(1).source("chennai").destination("bengaluru")
                .totalKms(200).build();

        Mockito.when(routeRepository.findById(100)).thenReturn(Optional.of(route));
        assertEquals(1,routeServiceImp.getRouteById(100).getRouteId());
    }
    @Test
    void getRouteByIdWithException()  {
        Route route = Route.builder().routeId(1).source("chennai").destination("bengaluru")
                .totalKms(200).build();
        Mockito.when(routeRepository.findById(400)).thenReturn(Optional.empty());
        assertThrows(RouteIdDoNotExitsException.class,
                ()->{routeServiceImp.getRouteById(400);});

    }
    @Test
    void updateTrain() throws RouteIdDoNotExitsException {

        Route route = Route.builder()
                .routeId(10)
                .source("thuthukudi")
                .destination("chennai")
                .totalKms(600)
                .build();


        Route updateRoute = Route.builder()
                .routeId(10)
                .source("chennai")
                .destination("thuthukudi")
                .totalKms(600)
                .build();

        Mockito.when(routeRepository.findById(10)).thenReturn(Optional.of(route));
        Mockito.when(routeRepository.save(updateRoute)).thenReturn(updateRoute);
        assertEquals(updateRoute,routeServiceImp.updateRoute(updateRoute));

    }

    @Test
    void deleteTrainByNumber() throws RouteIdDoNotExitsException {

        Route route= Route.builder()
                .routeId(10)
                .source("chennai")
                .destination("thuthukudi")
                .totalKms(600)
                .build();


        Mockito.when(routeRepository.findById(10)).thenReturn(Optional.of(route));
        assertEquals("Route deleted successfully",routeServiceImp.deleteRouteById(10));
    }


}

