package com.altimetrik.route.service;

import com.altimetrik.route.exception.RouteNotExistsException;
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
class RouteServiceImplTest {

    @Mock
    private RouteRepository routeRepository;
    @InjectMocks
    private RouteServiceImpl routeServiceImpl;
    @Test
    void addRoute() {
        Route r1 = Route.builder()
                .routeId(100).Source("Los Angeles")
                .Destination("New York").totalKms(34543).build();
        Mockito.when(routeRepository.save(r1)).thenReturn(r1);
        assertEquals(r1,routeServiceImpl.addRoute(r1));
    }

    @Test
    void getAllroutes() {
        Route r1 = Route.builder()
                .routeId(100).Source("Los Angeles")
                .Destination("New York").totalKms(34543).build();
        Route r2 = Route.builder()
                .routeId(101).Source("Kashmir")
                .Destination("Kanyakumari").totalKms(6867).build();
        Mockito.when(routeRepository.findAll()).thenReturn(Arrays.asList(r1,r2));
        assertEquals(2,routeServiceImpl.getAllroute().size());
    }

    @Test
    void getRouteById() throws RouteNotExistsException {
        Route r1 = Route.builder()
                .routeId(100).Source(" Los Angeles")
                .Destination("New York").totalKms(34543).build();

        Mockito.when(routeRepository.findById(100)).thenReturn(Optional.of(r1));
        assertEquals(100,routeServiceImpl.getRouteById(100).getRouteId());
    }
    @Test
    void getRouteByIdWithException()  {
        Route r1 = Route.builder()
                .routeId(100).Source("Los Angeles")
                .Destination("New York").totalKms(34543).build();
        Mockito.when(routeRepository.findById(400)).thenReturn(Optional.empty());
        assertThrows(RouteNotExistsException.class,
                ()->{routeServiceImpl.getRouteById(400);});
    }

    @Test
    void updateRoute() {
    }

    @Test
    void deleteRouteId() {
    }
}