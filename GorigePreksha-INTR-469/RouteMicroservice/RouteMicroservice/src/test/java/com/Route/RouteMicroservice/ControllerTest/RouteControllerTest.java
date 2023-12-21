package com.Route.RouteMicroservice.ControllerTest;

import com.Route.RouteMicroservice.controller.RouteController;
import com.Route.RouteMicroservice.entity.Route;
import com.Route.RouteMicroservice.service.RouteService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
public class RouteControllerTest {


    @Mock
    RouteService routeService;

    @InjectMocks
    RouteController routeController;


    @Test
    public void testAddRoute() {

        Route entity = new Route();
        entity.setRouteId(1);
        entity.setSource("CityA");
        entity.setDestination("CityB");
        entity.setTotalKms(100.5);
        Mockito.when(routeService.addRoute(entity)).thenReturn(entity);
        Route route=routeController.addRoute(entity);
        Assertions.assertEquals(1,route.getRouteId());

    }


    @Test
    public void testFindRoute() {
        Route entity = new Route();
        entity.setRouteId(1);
        entity.setSource("CityA");
        entity.setDestination("CityB");
        entity.setTotalKms(100.5);
        Mockito.when(routeService.findRoute(1)).thenReturn(entity);
        Route route=routeController.findRoute(1);
        Assertions.assertEquals(1,route.getRouteId());
    }
}
