package com.altimetrik.route.service;

import com.altimetrik.route.entity.Route;
import com.altimetrik.route.repository.RouteRepository;
import com.altimetrik.route.service.RouteServiceImpl;

import org.junit.Assert;

import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;


import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ServiceTestImpl{
    @Mock
    private RouteRepository routeRepository;
    @InjectMocks
    private RouteServiceImpl routeServiceImpl;

    @Test
    public void addRouteTest(){
        Route route=getRoute();
        routeRepository=mock(RouteRepository.class);
        when(routeRepository.save(any())).thenReturn(route);
        routeServiceImpl=new RouteServiceImpl(routeRepository);
       Route route1=routeServiceImpl.addRoute(route);
        Assert.assertEquals(route.getRouteId(),route1.getRouteId());
    }

    private Route getRoute(){
        Route route=new Route();
        route.setRouteId(1);
        route.setSource("Chennai");
        route.setDestination("Bangalore");
        route.setTotalKms(250);
        return route;
    }
    @Test
    public void getRoutebyIdTest(){
        Route route=setRoute();
        routeRepository=mock(RouteRepository.class);
        when(routeRepository.findById(any())).thenReturn(Optional.of(route));
        routeServiceImpl=new RouteServiceImpl(routeRepository);
        Route route1=routeServiceImpl.getRouteById(route.getRouteId());
        Assert.assertEquals(route.getRouteId(),route1.getRouteId());

    }

    private Route setRoute(){
        Route route=new Route();
        route.setRouteId(1);
        route.setSource("Chennai");
        route.setDestination("Bangalore");
        route.setTotalKms(250);
        return route;
    }

}
