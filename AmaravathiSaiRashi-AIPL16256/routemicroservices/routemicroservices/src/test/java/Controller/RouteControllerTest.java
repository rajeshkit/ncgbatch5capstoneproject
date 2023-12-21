package Controller;

import Controller.RouteController;
import Model.Route;
import Service.RouteService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(RouteController.class)
public class RouteControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private RouteService routeService;

    @InjectMocks
    private RouteController routeController;

    @BeforeEach
    public void setUp() {

        Route route = new Route();
        route.setSource("Source");
        route.setDestination("Destination");
        route.setTotalKms(200);

        when(routeService.getRouteDetailsById(1L)).thenReturn(route);
    }

    @Test
    public void testGetRouteDetails() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/routes/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.source").value("Source"))
                .andExpect(jsonPath("$.destination").value("Destination"))
                .andExpect(jsonPath("$.totalKms").value(200));
    }
}
