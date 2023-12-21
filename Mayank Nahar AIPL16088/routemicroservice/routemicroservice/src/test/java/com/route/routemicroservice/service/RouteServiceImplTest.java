package com.route.routemicroservice.service;
import com.route.routemicroservice.exception.RouteIdNotFoundException;
import com.route.routemicroservice.model.Route;
import com.route.routemicroservice.repository.RouteRepository;
import org.junit.jupiter.api.Assertions;
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
    RouteRepository routeRepository;

    @InjectMocks
    RouteServiceImpl routeService;

    @Test
    void addRoute() {
        Route r1 = Route.builder()
                .routeId(12)
                .source("cor")
                .destination("jp")
                .totalKms(200)
                .build();
        Mockito.when(routeRepository.save(r1)).thenReturn(r1);
        Assertions.assertEquals(r1, routeService.addRoute(r1));
    }

    @Test
    void getAllRoute() {
        Route r1 = Route.builder()
                .routeId(12)
                .source("cor")
                .destination("jp")
                .totalKms(200)
                .build();
        Route r2 = Route.builder()
                .routeId(123)
                .source("jp")
                .destination("up")
                .totalKms(100)
                .build();
        Mockito.when(routeRepository.findAll()).thenReturn(Arrays.asList(r1, r2));
        assertEquals(2, routeService.getAllRoute().size());
    }

    @Test
    void getRouteById() throws RouteIdNotFoundException {
        Route r1 = Route.builder()
                .routeId(12)
                .source("cor")
                .destination("jp")
                .totalKms(200)
                .build();
        Mockito.when(routeRepository.findById(12)).thenReturn(Optional.of(r1));
        Assertions.assertEquals(12, routeService.getRouteById(12).getRouteId());

    }

    @Test
    void getRouteByIdWithException() {
        Route r1 = Route.builder()
                .routeId(12)
                .source("cor")
                .destination("jp")
                .totalKms(200)
                .build();
        Mockito.when(routeRepository.findById(23)).thenReturn(Optional.empty());
        assertThrows(RouteIdNotFoundException.class, () -> {
            routeService.getRouteById(23);
        });
    }
    @Test
    void updateRoute()  {
        Route r1 = Route.builder()
                .routeId(12)
                .source("cor")
                .destination("jp")
                .totalKms(200)
                .build();

        Mockito.when(routeRepository.save(r1)).thenReturn(r1);
        Assertions.assertEquals(r1, routeService.addRoute(r1));

    }

}
