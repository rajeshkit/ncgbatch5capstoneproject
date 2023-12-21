package com.trainmicroservice.service;

import com.trainmicroservice.exception.TrainNumberDoesNotExistException;
import com.trainmicroservice.model.Train;
import com.trainmicroservice.repository.TrainRepository;
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
    void testSaveTrainDetails() {
        Train train = Train.builder().trainNumber(1).trainName("Rajasthani Expressss").totalKms(1490)
                .acCoaches(3).acCoachTotalSeats(120)
                .sleeperCoaches(5).sleeperCoachTotalSeats(200)
                .generalCoaches(7).generalCoachTotalSeats(280).build();

        when(trainRepository.save(train)).thenReturn(train);

        Train savedTrain = trainService.saveTrainDetails(train);

        assertNotNull(savedTrain);
        assertEquals(train.getTrainNumber(), savedTrain.getTrainNumber());
        assertEquals(train.getTrainName(), savedTrain.getTrainName());

        verify(trainRepository, times(1)).save(train);
    }

    @Test
    void testGetAllTrainsDetail() {
        Train train1 = Train.builder().trainNumber(1).trainName("Rajasthani Expressss").totalKms(1490)
                .acCoaches(3).acCoachTotalSeats(120)
                .sleeperCoaches(5).sleeperCoachTotalSeats(200)
                .generalCoaches(7).generalCoachTotalSeats(280).build();
        Train train2 = Train.builder().trainNumber(1).trainName("Haryanvi Expressss").totalKms(1000)
                .acCoaches(3).acCoachTotalSeats(120)
                .sleeperCoaches(5).sleeperCoachTotalSeats(200)
                .generalCoaches(7).generalCoachTotalSeats(280).build();

        when(trainRepository.findAll()).thenReturn(Arrays.asList(train1, train2));

        List<Train> trains = trainService.getAllTrainsDetail();

        assertNotNull(trains);
        assertEquals(2, trains.size());

        verify(trainRepository, times(1)).findAll();
    }

    @Test
    void testGetTrainDetailsById() throws TrainNumberDoesNotExistException {
        Train train = Train.builder().trainNumber(1).trainName("Rajasthani Expressss").totalKms(1490)
                .acCoaches(3).acCoachTotalSeats(120)
                .sleeperCoaches(5).sleeperCoachTotalSeats(200)
                .generalCoaches(7).generalCoachTotalSeats(280).build();

        when(trainRepository.findById(1)).thenReturn(Optional.of(train));

        Train fetchedTrain = trainService.getTrainDetailsById(1);

        assertNotNull(fetchedTrain);
        assertEquals(train.getTrainNumber(), fetchedTrain.getTrainNumber());
        assertEquals(train.getTrainName(), fetchedTrain.getTrainName());

        verify(trainRepository, times(1)).findById(1);
    }

    @Test
    void testGetTrainDetailsByIdTrainNotFound() {
        when(trainRepository.findById(1)).thenReturn(Optional.empty());

        assertThrows(TrainNumberDoesNotExistException.class, () -> trainService.getTrainDetailsById(1));

        verify(trainRepository, times(1)).findById(1);
    }

    @Test
    void testUpdateTrainDetails() throws TrainNumberDoesNotExistException {
        Train train = Train.builder().trainNumber(1).trainName("Rajasthani Expressss").totalKms(1490)
                .acCoaches(3).acCoachTotalSeats(120)
                .sleeperCoaches(5).sleeperCoachTotalSeats(200)
                .generalCoaches(7).generalCoachTotalSeats(280).build();

        when(trainRepository.findById(1)).thenReturn(Optional.of(train));
        when(trainRepository.save(train)).thenReturn(train);

        Train updatedTrain = trainService.updateTrainDetails(train);

        assertNotNull(updatedTrain);
        assertEquals(train.getTrainNumber(), updatedTrain.getTrainNumber());
        assertEquals(train.getTrainName(), updatedTrain.getTrainName());

        verify(trainRepository, times(1)).findById(1);
        verify(trainRepository, times(1)).save(train);
    }

    @Test
    void testUpdateTrainDetailsTrainNotFound() {
        Train train = Train.builder().trainNumber(1).trainName("Rajasthani Expressss").totalKms(1490)
                .acCoaches(3).acCoachTotalSeats(120)
                .sleeperCoaches(5).sleeperCoachTotalSeats(200)
                .generalCoaches(7).generalCoachTotalSeats(280).build();

        when(trainRepository.findById(1)).thenReturn(Optional.empty());

        assertThrows(TrainNumberDoesNotExistException.class, () -> trainService.updateTrainDetails(train));

        verify(trainRepository, times(1)).findById(1);
        verify(trainRepository, never()).save(any());
    }

    @Test
    void testDeleteTrainDetails() throws TrainNumberDoesNotExistException {
        Train train = Train.builder().trainNumber(1).trainName("Rajasthani Expressss").totalKms(1490)
                .acCoaches(3).acCoachTotalSeats(120)
                .sleeperCoaches(5).sleeperCoachTotalSeats(200)
                .generalCoaches(7).generalCoachTotalSeats(280).build();

        when(trainRepository.findById(1)).thenReturn(Optional.of(train));

        String message = trainService.deleteTrainDetails(1);

        assertEquals("Train deleted successfully", message);

        verify(trainRepository, times(1)).findById(1);
        verify(trainRepository, times(1)).deleteById(1);
    }
}