package com.route.routemicroservice;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.route.routemicroservice.controller.RouteController;
import com.route.routemicroservice.model.Route;
import com.route.routemicroservice.repository.RouteRepository;
import com.route.routemicroservice.service.RouteService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Arrays;
import java.util.Optional;

import static org.mockito.Mockito.when;


@WebMvcTest(RouteController.class)
class RouteMicroserviceApplicationTests {
	@MockBean
	private RouteService routeService;

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper objectMapper;

	@Test
	void contextLoads() {
	}

	@Test
	public void testAddRoute() throws Exception {
		Route route = new Route(1, "SourceA", "DestinationB", 500);
		when(routeService.addRoute(route)).thenReturn(route);

		mockMvc.perform(MockMvcRequestBuilders.post("/route")
						.contentType(MediaType.APPLICATION_JSON)
						.content(objectMapper.writeValueAsString(route)))
				.andDo(MockMvcResultHandlers.print())
				.andExpect(MockMvcResultMatchers.status().isCreated())
				.andExpect(MockMvcResultMatchers.jsonPath("$.routeId").exists());
	}

	@Test
	public void testGetAllRoutes() throws Exception {
		Route route1 = new Route(1, "SourceA", "DestinationB", 500);
		Route route2 = new Route(2, "SourceX", "DestinationY", 700);
		when(routeService.getAllRoutes()).thenReturn(Arrays.asList(route1, route2));

		mockMvc.perform(MockMvcRequestBuilders.get("/route"))
				.andDo(MockMvcResultHandlers.print())
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$.length()").value(2));
	}

	@Test
	public void testGetRouteDetailsById() throws Exception {
		int routeId = 1;
		Route route = new Route(routeId, "SourceA", "DestinationB", 500);
		when(routeService.getRouteByRouteId(routeId)).thenReturn(route);

		mockMvc.perform(MockMvcRequestBuilders.get("/route/{id}", routeId))
				.andDo(MockMvcResultHandlers.print())
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$.routeId").value(routeId));
		}
	@Test
	public void testUpdateRoute() throws Exception {
		Route route =new Route (4, "SourceG", "DestinationH", 1500);

				Mockito.when(routeService.updateRoute(route)).thenReturn(route);

		mockMvc.perform(MockMvcRequestBuilders.put("/route")
						.contentType(MediaType.APPLICATION_JSON)
						.content(objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(route)))
				.andDo(MockMvcResultHandlers.print())
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$.routeId").exists());
	}


	// Similar tests for update and delete methods can be added
		@Test
		public void testDeleteRoute() throws Exception {
			int routeIdToDelete =46556; // provide a routeId to delete
					mockMvc.perform(MockMvcRequestBuilders.delete("/route/{id}", routeIdToDelete))
							.andDo(MockMvcResultHandlers.print())
							.andExpect(MockMvcResultMatchers.status().isNoContent());
		}


}
