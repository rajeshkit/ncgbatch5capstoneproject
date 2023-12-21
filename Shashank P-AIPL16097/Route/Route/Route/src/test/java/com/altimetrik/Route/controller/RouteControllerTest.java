package com.altimetrik.Route.controller;
import com.altimetrik.Route.exception.RouteIdNotExistsException;
import com.altimetrik.Route.model.Route;
import com.altimetrik.Route.repository.RouteRepository;
import com.altimetrik.Route.service.RouteService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import java.util.Arrays;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;

@WebMvcTest(RouteController.class)

public class RouteControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private RouteService routeService;
    @Mock
    private RouteRepository routeRepository;

    @Test
    void addRoute() throws Exception {
        Route route = Route.builder()
                .routeId(2)
                .Source("Source")
                .Destination("Destination")
                .totalKms(100)
                .build();

        when(routeService.addRoute(route)).thenReturn(route);

        mockMvc.perform(post("/route-api/route").contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(route))).andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.routeId").value(2));;
    }
    @Test
    void updateRoute() throws RouteIdNotExistsException {
        Route routeToUpdate = Route.builder()
                .routeId(100)
                .Source("Updated Source")
                .Destination("Updated Destination")
                .totalKms(500)
                .build();

        when(routeService.updateRoute(routeToUpdate)).thenReturn((routeToUpdate));

        Route updatedRoute = routeService.updateRoute(routeToUpdate);

        assertEquals(routeToUpdate, updatedRoute);
    }

    @Test
    void deleteRouteById() throws RouteIdNotExistsException {
        int routeIdToDelete = 101;

        when(routeService.deleteRouteById(routeIdToDelete)).thenReturn("deleted successfully");

        String deletionMessage = routeService.deleteRouteById(routeIdToDelete);

        assertEquals("deleted successfully", deletionMessage);
    }
    @Test
    void getAllRoutes() {
        List<Route> routeList = Arrays.asList(
                Route.builder().routeId(101).Source("Source 1").Destination("Destination 1").build(),
                Route.builder().routeId(102).Source("Source 2").Destination("Destination 2").build()
        );

        when(routeService.getAllRoutes()).thenReturn(routeList);

        List<Route> retrievedRoutes = routeService.getAllRoutes();

        assertEquals(routeList.size(), retrievedRoutes.size());
    }
    @Test
    void getRouteById() throws RouteIdNotExistsException {
        int routeId = 101;
        Route route = Route.builder()
                .routeId(routeId)
                .Source("Sample Source")
                .Destination("Sample Destination")
                .build();

        when(routeService.getRouteById(routeId)).thenReturn((route));

        Route retrievedRoute = routeService.getRouteById(routeId);

        assertEquals(route, retrievedRoute);
    }
}

