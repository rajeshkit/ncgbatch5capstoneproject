package com.altimetrik.trainmicroservice;

import com.altimetrik.trainmicroservice.exceptions.TrainIdNotExistsException;
import com.altimetrik.trainmicroservice.model.Train;
import com.altimetrik.trainmicroservice.repository.TrainRepository;
import com.altimetrik.trainmicroservice.service.TrainServiceImplementation;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class TrainServiceImplementationTest {

	@Mock
	private TrainRepository trainRepository;

	@InjectMocks
	private TrainServiceImplementation trainService;

	@Test
	void addTrain() {
		// Given
		Train train = new Train();
		when(trainRepository.save(train)).thenReturn(train);

		// When
		Train result = trainService.addTrain(train);

		// Then
		assertEquals(train, result);
		verify(trainRepository, times(1)).save(train);
	}

	@Test
	void getAllTrains() {
		// Given
		List<Train> trainList = new ArrayList<>();
		when(trainRepository.findAll()).thenReturn(trainList);

		// When
		List<Train> result = trainService.getAllTrains();

		// Then
		assertEquals(trainList, result);
		verify(trainRepository, times(1)).findAll();
	}

	@Test
	void getTrainByNumber() throws TrainIdNotExistsException {
		// Given
		String trainNumber = "T0001";
		Train train = new Train();
		when(trainRepository.findById(trainNumber)).thenReturn(Optional.of(train));

		// When
		Train result = trainService.getTrainByNumber(trainNumber);

		// Then
		assertEquals(train, result);
		verify(trainRepository, times(1)).findById(trainNumber);
	}

	@Test
	void getTrainByNumberTrainNotExists() {
		// Given
		String trainNumber = "T0001";
		when(trainRepository.findById(trainNumber)).thenReturn(Optional.empty());

		// When / Then
		assertThrows(TrainIdNotExistsException.class, () -> trainService.getTrainByNumber(trainNumber));
		verify(trainRepository, times(1)).findById(trainNumber);
	}

	@Test
	void updateTrain() throws TrainIdNotExistsException {
		// Given
		Train train = new Train();
		train.setTrainNumber("T0001");
		when(trainRepository.existsById(train.getTrainNumber())).thenReturn(true);
		when(trainRepository.save(train)).thenReturn(train);

		// When
		Train result = trainService.updateTrain(train);

		// Then
		assertEquals(train, result);
		verify(trainRepository, times(1)).existsById(train.getTrainNumber());
		verify(trainRepository, times(1)).save(train);
	}

	@Test
	void updateTrainTrainNotExists() {
		// Given
		Train train = new Train();
		train.setTrainNumber("T0001");
		when(trainRepository.existsById(train.getTrainNumber())).thenReturn(false);

		// When / Then
		assertThrows(TrainIdNotExistsException.class, () -> trainService.updateTrain(train));
		verify(trainRepository, times(1)).existsById(train.getTrainNumber());
		verify(trainRepository, never()).save(train);
	}

	@Test
	void deleteTrainByNumber() throws TrainIdNotExistsException {
		// Given
		String trainNumber = "T0001";
		Train train = new Train();
		when(trainRepository.findById(trainNumber)).thenReturn(Optional.of(train));

		// When
		String result = trainService.deleteTrainByNumber(trainNumber);

		// Then
		assertEquals("Train deleted successfully", result);
		verify(trainRepository, times(1)).findById(trainNumber);
		verify(trainRepository, times(1)).deleteById(trainNumber);
	}

	@Test
	void deleteTrainByNumberTrainNotExists() {
		// Given
		String trainNumber = "T0001";
		when(trainRepository.findById(trainNumber)).thenReturn(Optional.empty());

		// When / Then
		assertThrows(TrainIdNotExistsException.class, () -> trainService.deleteTrainByNumber(trainNumber));
		verify(trainRepository, times(1)).findById(trainNumber);
		verify(trainRepository, never()).deleteById(trainNumber);
	}
}
