package com.schedule.schedulemicroservice;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.route.routemicroservice.model.Route;
import com.schedule.schedulemicroservice.controller.ScheduleController;
import com.schedule.schedulemicroservice.model.Schedule;
import com.schedule.schedulemicroservice.service.ScheduleService;
import com.train.trainmicroservice.model.Train;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Arrays;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ScheduleController.class)
class ScheduleMicroserviceApplicationTests {

	@MockBean
	private ScheduleService scheduleService;

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper objectMapper;


	@Test
	void contextLoads() {
		
	}

	@Test
	public void testGetAllSchedules() throws Exception {
		// Mock data
		Schedule schedule1 = new Schedule();
		Schedule schedule2 = new Schedule();
		when(scheduleService.getAllSchedules()).thenReturn(Arrays.asList(schedule1, schedule2));

		// Perform the GET request
		mockMvc.perform(MockMvcRequestBuilders.get("/schedule"))
				.andDo(MockMvcResultHandlers.print())
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$.length()").value(2));
	}

	@Test
	void testGetScheduleById() throws Exception {
		int scheduleId = 1;
		Schedule.ScheduleBuilder builder = Schedule.builder();
		builder.scheduleId(scheduleId);
		builder.departureDateTime(Timestamp.valueOf(LocalDateTime.now()));
		builder.arrivalDateTime(Timestamp.valueOf(LocalDateTime.now()));
		builder.train(new Train());
		builder.route(new Route());
		Schedule schedule = builder
				.build();

		when(scheduleService.getScheduleById(scheduleId)).thenReturn(schedule);

		mockMvc.perform(MockMvcRequestBuilders.get("/schedule/{scheduleId}", scheduleId))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.scheduleId").value(scheduleId));
	}

	@Test
	public void testDeleteSchedule() throws Exception {
		int scheduleIdToDelete = 1; // provide a scheduleId to delete

		// Mock the behavior of the service
		Mockito.doNothing().when(scheduleService).deleteSchedule(scheduleIdToDelete);

		// Perform the DELETE request
		mockMvc.perform(MockMvcRequestBuilders.delete("/schedule/{id}", scheduleIdToDelete))
				.andDo(MockMvcResultHandlers.print())
				.andExpect(MockMvcResultMatchers.status().isNoContent());
	}

	// Add more test cases as needed

}

