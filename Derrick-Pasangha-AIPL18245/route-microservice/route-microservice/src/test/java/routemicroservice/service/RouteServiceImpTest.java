package routemicroservice.service;



import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import routemicroservice.exception.RouteIdDoesNotExistException;
import routemicroservice.model.Route;
import routemicroservice.repository.RouteRepository;


import java.util.Arrays;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(MockitoExtension.class)
class TrainServiceImplTest {

    @Mock
    private RouteRepository routeRepository;
    @InjectMocks
    private RouteServiceImp routeServiceImp;
    @Test
    void addRoute() {
        Route p1 = Route.builder()
                .routeId(10).Source("Bangalore").Destination("Chennai").totalKms(300).build();
        Mockito.when(routeRepository.save(p1)).thenReturn(p1);
        assertEquals(p1,routeServiceImp.addRoute(p1));
    }

    @Test
    void getAllRoutes() {
        Route p1 = Route.builder()
                .routeId(10).Source("Bangalore").Destination("Chennai").totalKms(300).build();
        Route p2 = Route.builder()
                .routeId(11).Source("Bangalore").Destination("Chennai").totalKms(400).build();
        Mockito.when(routeRepository.findAll()).thenReturn(Arrays.asList(p1,p2));
        assertEquals(2,routeServiceImp.getAllRoutes().size());
    }

    @Test
    void getRouteById() throws RouteIdDoesNotExistException {
        Route p1 = Route.builder()
                .routeId(10).Source("Bangalore").Destination("Chennai").totalKms(300).build();
        Mockito.when(routeRepository.findById(100)).thenReturn(Optional.of(p1));
        assertEquals(100,routeServiceImp.getRouteById(100).get().getRouteId());
    }

    @Test
    void updateRoute() {
    }

    @Test
    void deleteRouteById() {
    }
}
