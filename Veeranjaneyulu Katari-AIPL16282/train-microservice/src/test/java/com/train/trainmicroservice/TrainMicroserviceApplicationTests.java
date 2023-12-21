package com.train.trainmicroservice;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.train.trainmicroservice.controller.TrainController;
import com.train.trainmicroservice.model.Train;
import com.train.trainmicroservice.service.TrainService;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;


import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.anyInt;

@WebMvcTest(TrainController.class)
public class TrainMicroserviceApplicationTests {

	@MockBean
	private TrainService trainService;

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper objectMapper;

	@Test
	void contextLoads() {
	}

	@Test
	public void testAddTrain_ValidTrain() throws Exception {
		Train train = Train.builder()
				.trainNumber(1)
				.trainName("Rajadhani Express")
				.totalKms(1500.5)
				.acCoaches(3)
				.acCoachTotalSeats(120)
				.sleeperCoaches(5)
				.sleeperCoachTotalSeats(200)
				.generalCoaches(7)
				.generalCoachesTotalSeats(280)
				.build();

		Mockito.when(trainService.addTrain(train)).thenReturn(train);

		mockMvc.perform(MockMvcRequestBuilders.post("/train")
						.contentType(MediaType.APPLICATION_JSON)
						.content(objectMapper.writerWithDefaultPrettyPrinter()
								.writeValueAsString(train)))
				.andDo(MockMvcResultHandlers.print())
				.andExpect(MockMvcResultMatchers.status().isCreated())
				.andExpect(MockMvcResultMatchers.jsonPath("$.trainNumber").exists());
	}

	@Test
	public void testAddTrain_InvalidTrain() throws Exception {
		// Provide a train object with invalid attributes (e.g., negative values, insufficient acCoaches)
		Train train = Train.builder()
				.trainNumber(1)
				.trainName("Invalid Train")
				.totalKms(-100.0)
				.acCoaches(1) // Invalid, should be at least 2
				.acCoachTotalSeats(50)
				.sleeperCoaches(2)
				.sleeperCoachTotalSeats(100)
				.generalCoaches(3)
				.generalCoachesTotalSeats(120)
				.build();

		mockMvc.perform(MockMvcRequestBuilders.post("/train")
						.contentType(MediaType.APPLICATION_JSON)
						.content(objectMapper.writerWithDefaultPrettyPrinter()
								.writeValueAsString(train)))
				.andDo(MockMvcResultHandlers.print())
				.andExpect(MockMvcResultMatchers.status().isBadRequest());
	}
	@Test
	public void testUpdateTrainWithInvalidTrainNumber() throws Exception {
		int invalidTrainNumber = 999;
		Train invalidTrain = Train.builder().trainNumber(invalidTrainNumber).build();

		Mockito.when(trainService.updateTrain(invalidTrain)).thenReturn(null);

		mockMvc.perform(MockMvcRequestBuilders.put("/train")
						.contentType(MediaType.APPLICATION_JSON)
						.content(objectMapper.writeValueAsString(invalidTrain)))
				.andDo(MockMvcResultHandlers.print())
				.andExpect(MockMvcResultMatchers.status().isNotFound());
	}

	@Test
	public void testUpdateTrainWithInvalidData() throws Exception {
		int trainNumber = 1;
		Train invalidDataTrain = Train.builder().trainNumber(trainNumber).totalKms(-100).build();

		Mockito.when(trainService.updateTrain(invalidDataTrain)).thenReturn(null);

		mockMvc.perform(MockMvcRequestBuilders.put("/train")
						.contentType(MediaType.APPLICATION_JSON)
						.content(objectMapper.writeValueAsString(invalidDataTrain)))
				.andDo(MockMvcResultHandlers.print())
				.andExpect(MockMvcResultMatchers.status().isNotFound());
	}

	@Test
	public void testDeleteTrainWithInvalidTrainNumber() throws Exception {
        int invalidTrainNumber = 12345; // Provide the invalid train number

// Mocking the behavior of deleteTrainByTrainNo for an invalid train number
        Mockito.doNothing().when(trainService).deleteTrainByTrainNo(invalidTrainNumber);

// Perform the actual delete operation and expect a 404 status
        mockMvc.perform(MockMvcRequestBuilders.delete("/train/{trainNumber}", invalidTrainNumber))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk());}


        @Test
	public void testGetTrainByInvalidTrainNumber() throws Exception {
		int invalidTrainNumber = -1;

		mockMvc.perform(MockMvcRequestBuilders.get("/train/{id}", invalidTrainNumber))
				.andDo(MockMvcResultHandlers.print())
				.andExpect(MockMvcResultMatchers.status().isNotFound());
	}

//	@Test
//	public void testGetAllTrainsEmptyList() throws Exception {
//		Mockito.when(trainService.getAllTrains()).thenReturn(Collections.emptyList());
//
//		// Perform the GET request
//		mockMvc.perform(MockMvcRequestBuilders.get("/train"))
//				.andDo(MockMvcResultHandlers.print())
//				.andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
//				.andExpect(MockMvcResultMatchers.jsonPath("$").isArray())
//				.andExpect(MockMvcResultMatchers.jsonPath("$.length()").value(0))
//				.andExpect(MockMvcResultMatchers.status().isNotFound());
//    }

	@Test
	public void testValidationErrorsInCreateTrain() throws Exception {
		Train invalidTrain = Train.builder().build(); // Invalid train with missing data

		mockMvc.perform(MockMvcRequestBuilders.post("/train")
						.contentType(MediaType.APPLICATION_JSON)
						.content(objectMapper.writeValueAsString(invalidTrain)))
				.andDo(MockMvcResultHandlers.print())
				.andExpect(MockMvcResultMatchers.status().isBadRequest());
	}

	@Test
	public void testDeleteTrain() throws Exception {
		int trainNumberToDelete = 123;
		Train trainToDelete = Train.builder().trainNumber(trainNumberToDelete).build();

		// Mocking the behavior of the deleteTrain method
		Mockito.doNothing().when(trainService).deleteTrainByTrainNo(anyInt());

		mockMvc.perform(MockMvcRequestBuilders.delete("/train/{trainNumber}", trainNumberToDelete)
						.contentType(MediaType.APPLICATION_JSON)
						.content(objectMapper.writeValueAsString(trainToDelete)))
				.andDo(MockMvcResultHandlers.print())
				.andExpect(MockMvcResultMatchers.status().isOk());
	}


	// Add more test cases as needed

}

