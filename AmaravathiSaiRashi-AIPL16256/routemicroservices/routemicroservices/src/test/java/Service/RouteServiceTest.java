package Service;

import Model.Route;
import Repository.RouteRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
public class RouteServiceTest {

    @Mock
    private RouteRepository routeRepository;

    @InjectMocks
    private RouteService routeService;

    @Test
    public void testGetRouteDetailsById() {
        Route route = new Route();
        route.setSource("Source");
        route.setDestination("Destination");
        route.setTotalKms(200);

        when(routeRepository.findById(1L)).thenReturn(Optional.of(route));

        Route fetchedRoute = (Route) routeService.getRouteDetailsById(1L);

        assertEquals("Source", fetchedRoute.getSource());
        assertEquals("Destination", fetchedRoute.getDestination());
        assertEquals(200, fetchedRoute.getTotalKms());
    }
}
