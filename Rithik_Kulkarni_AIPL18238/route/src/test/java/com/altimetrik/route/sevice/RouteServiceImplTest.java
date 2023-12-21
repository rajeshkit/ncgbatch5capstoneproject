package com.altimetrik.route.sevice;

import com.altimetrik.route.exception.RouteIdNotExistException;
import com.altimetrik.route.model.Route;
import com.altimetrik.route.repository.RouteRepository;
import org.junit.jupiter.api.Assertions;
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
class RouteServiceImplTest {

    @Mock
    private RouteRepository routeRepository;

    @InjectMocks
    private RouteServiceImpl routeService;

    @Test
    void addRoute() {
        Route rt1 = Route.builder()
                .routeId(111)
                .source("Test-S1")
                .destination("Test-D1")
                .totalKms(999)
                .build();

        Mockito.when(routeRepository.save(rt1)).thenReturn(rt1);
        Assertions.assertEquals(rt1, routeService.addRoute(rt1));
    }

    @Test
    void getAllRoutes() {

        Route rt1 = Route.builder()
                .routeId(111)
                .source("Test-S1")
                .destination("Test-D1")
                .totalKms(999)
                .build();

        Route rt2 = Route.builder()
                .routeId(222)
                .source("Test-S2")
                .destination("Test-D2")
                .totalKms(888)
                .build();

        Mockito.when(routeRepository.findAll()).thenReturn(Arrays.asList(rt1, rt2));
        assertEquals(2, routeService.getAllRoutes().size());
    }

    @Test
    void getRouteByRouteId() throws Exception{

        Route rt1 = Route.builder()
                .routeId(111)
                .source("Test-S1")
                .destination("Test-D1")
                .totalKms(999)
                .build();

        Mockito.when(routeRepository.findById(111)).thenReturn(Optional.of(rt1));
        Assertions.assertEquals(111, routeService.getRouteByRouteId(111).getRouteId());

    }

    @Test
    void getRouteByRouteIdWithException() {

        Route rt1 = Route.builder()
                .routeId(111)
                .source("Test-S1")
                .destination("Test-D1")
                .totalKms(999)
                .build();

        Mockito.when(routeRepository.findById(222)).thenReturn(Optional.empty());
        assertThrows(RouteIdNotExistException.class, () -> {
            routeService.getRouteByRouteId(222);
        });
    }

    @Test
    void updateRoute() throws Exception {

        Route rt1 = Route.builder()
                .routeId(111)
                .source("Test-S1")
                .destination("Test-D1")
                .totalKms(999)
                .build();

        Mockito.when(routeRepository.findById(111)).thenReturn(Optional.of(rt1));
        Mockito.when(routeRepository.save(rt1)).thenReturn(rt1);
        Assertions.assertEquals(rt1, routeService.updateRoute(rt1));
    }
}