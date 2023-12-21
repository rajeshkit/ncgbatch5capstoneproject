package com.altimetrik.trainmicroservice.service;

import com.altimetrik.trainmicroservice.exceptions.TrainIdNotExistsException;
import com.altimetrik.trainmicroservice.model.Train;
import com.altimetrik.trainmicroservice.repository.TrainRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
class TrainServiceImplementationTest {

    @Mock
    private TrainRepository trainRepository;

    @InjectMocks
    private TrainServiceImplementation trainService;

    @Test
    void testAddTrain() {
        // Given
        Train train = Train.builder()
                .trainNumber("T1234")
                .trainName("Express")
                .totalDistance(500)
                .build();

        when(trainRepository.save(train)).thenReturn(train);

        // When
        Train result = trainService.addTrain(train);

        // Then
        assertEquals(train, result);
        verify(trainRepository, times(1)).save(train);
    }

    @Test
    void testGetAllTrains() {
        // Given
        List<Train> trains = new ArrayList<>();
        when(trainRepository.findAll()).thenReturn(trains);

        // When
        List<Train> result = trainService.getAllTrains();

        // Then
        assertEquals(trains, result);
        verify(trainRepository, times(1)).findAll();
    }

    @Test
    void testGetTrainByNumberSuccess() throws TrainIdNotExistsException {
        // Given
        String trainNumber = "T1234";
        Train train = Train.builder()
                .trainNumber(trainNumber)
                .trainName("Express")
                .totalDistance(500)
                .build();

        when(trainRepository.findById(trainNumber)).thenReturn(Optional.of(train));

        // When
        Train result = trainService.getTrainByNumber(trainNumber);

        // Then
        assertEquals(train, result);
        verify(trainRepository, times(1)).findById(trainNumber);
    }

    @Test
    void testGetTrainByNumberNotFound() {
        // Given
        String trainNumber = "T1234";
        when(trainRepository.findById(trainNumber)).thenReturn(Optional.empty());

        // When and Then
        assertThrows(TrainIdNotExistsException.class, () -> trainService.getTrainByNumber(trainNumber));
        verify(trainRepository, times(1)).findById(trainNumber);
    }

    @Test
    void testUpdateTrainSuccess() throws TrainIdNotExistsException {
        // Given
        String trainNumber = "T1234";
        Train train = Train.builder()
                .trainNumber(trainNumber)
                .trainName("Express")
                .totalDistance(500)
                .build();

        when(trainRepository.existsById(trainNumber)).thenReturn(true);
        when(trainRepository.save(train)).thenReturn(train);

        // When
        Train result = trainService.updateTrain(train);

        // Then
        assertEquals(train, result);
        verify(trainRepository, times(1)).existsById(trainNumber);
        verify(trainRepository, times(1)).save(train);
    }

    @Test
    void testUpdateTrainNotFound() {
        // Given
        String trainNumber = "T1234";
        Train train = Train.builder()
                .trainNumber(trainNumber)
                .trainName("Express")
                .totalDistance(500)
                .build();

        when(trainRepository.existsById(trainNumber)).thenReturn(false);

        // When and Then
        assertThrows(TrainIdNotExistsException.class, () -> trainService.updateTrain(train));
        verify(trainRepository, times(1)).existsById(trainNumber);
        verifyNoMoreInteractions(trainRepository); // No more interactions after the not found scenario
    }

    @Test
    void testDeleteTrainByNumberSuccess() throws TrainIdNotExistsException {
        // Given
        String trainNumber = "T1234";
        Train train = Train.builder()
                .trainNumber(trainNumber)
                .trainName("Express")
                .totalDistance(500)
                .build();

        when(trainRepository.findById(trainNumber)).thenReturn(Optional.of(train));

        // When
        String result = trainService.deleteTrainByNumber(trainNumber);

        // Then
        assertEquals("Train deleted successfully", result);
        verify(trainRepository, times(1)).findById(trainNumber);
        verify(trainRepository, times(1)).deleteById(trainNumber);
    }

    @Test
    void testDeleteTrainByNumberNotFound() {
        // Given
        String trainNumber = "T1234";
        when(trainRepository.findById(trainNumber)).thenReturn(Optional.empty());

        // When and Then
        assertThrows(TrainIdNotExistsException.class, () -> trainService.deleteTrainByNumber(trainNumber));
        verify(trainRepository, times(1)).findById(trainNumber);
        verifyNoMoreInteractions(trainRepository); // No more interactions after the not found scenario
    }
}
