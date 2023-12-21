package com.trainaltimetrik.trainmicroservice.service;

import com.trainaltimetrik.trainmicroservice.exception.TrainNumberAlreadyExistException;
import com.trainaltimetrik.trainmicroservice.exception.TrainNumberNotExistException;
import com.trainaltimetrik.trainmicroservice.model.Train;
import com.trainaltimetrik.trainmicroservice.repository.TrainRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TrainServiceImplTest {

    @Mock
    private TrainRepository trainRepository;

    @InjectMocks
    private TrainServiceImpl trainService;

    @Test
    void addTrain() throws TrainNumberAlreadyExistException {
        Train train = Train.builder().trainNumber(1).trainName("Express").build();
        when(trainRepository.save(any(Train.class))).thenReturn(train);

        Train result = trainService.addTrain(train);

        assertNotNull(result);
        assertEquals(1, result.getTrainNumber());
        assertEquals("Express", result.getTrainName());
    }

    @Test
    void getAllTrain() {
        Train train1 = Train.builder().trainNumber(1).trainName("Express1").build();
        Train train2 = Train.builder().trainNumber(2).trainName("Express2").build();
        when(trainRepository.findAll()).thenReturn(Arrays.asList(train1, train2));

        List<Train> result = trainService.getAllTrain();

        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals(1, result.get(0).getTrainNumber());
        assertEquals("Express1", result.get(0).getTrainName());
        assertEquals(2, result.get(1).getTrainNumber());
        assertEquals("Express2", result.get(1).getTrainName());
    }

    @Test
    void getTrainByNumber() throws TrainNumberNotExistException {
        Train train = Train.builder().trainNumber(1).trainName("Express").build();
        when(trainRepository.findById(1)).thenReturn(Optional.of(train));

        Train result = trainService.getTrainByNumber(1);

        assertNotNull(result);
        assertEquals(1, result.getTrainNumber());
        assertEquals("Express", result.getTrainName());
    }

    @Test
    void getTrainByNumber_ThrowsException() {
        when(trainRepository.findById(1)).thenReturn(Optional.empty());

        assertThrows(TrainNumberNotExistException.class, () -> trainService.getTrainByNumber(1));
    }

    @Test
    void updateTrain() throws TrainNumberNotExistException {
        Train train = Train.builder().trainNumber(1).trainName("Express").build();
        when(trainRepository.save(any(Train.class))).thenReturn(train);
        when(trainRepository.findById(1)).thenReturn(Optional.of(train));

        Train result = trainService.updateTrain(train);

        assertNotNull(result);
        assertEquals(1, result.getTrainNumber());
        assertEquals("Express", result.getTrainName());
    }


    @Test
    void deleteTrainByNumber() throws TrainNumberNotExistException {
        int trainNumber = 123;
        Train mockTrain = new Train();
        when(trainRepository.findById(trainNumber)).thenReturn(java.util.Optional.of(mockTrain));
        String result = trainService.deleteTrainByNumber(trainNumber);
        assertEquals("Train Has Been Deleted 123", result);
        verify(trainRepository, times(1)).deleteById(trainNumber);
    }

}


