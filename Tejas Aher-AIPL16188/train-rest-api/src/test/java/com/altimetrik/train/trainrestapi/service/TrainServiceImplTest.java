package com.altimetrik.train.trainrestapi.service;

import com.altimetrik.train.trainrestapi.exception.TrainIDNotExistsException;
import com.altimetrik.train.trainrestapi.model.Train;
import com.altimetrik.train.trainrestapi.repository.TrainRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
class TrainServiceImplTest {

    @Mock
    private TrainRepository trainRepository;

    @InjectMocks
    private TrainServiceImpl trainService;

    @Test
    void addTrain() {
        Train train = Train.builder().trainNumber("100").trainName("BangloreExpress").acCoaches("Available").acCoachesTotalSeats(200).generalCoaches("Available").generalCoachesTotalSeats(420).build();
        Mockito.when(trainRepository.save(train)).thenReturn(train);
        assertEquals(train, trainService.addTrain(train));
    }

    @Test
    void getAllTrains() {

        Train train1 = Train.builder().trainNumber("1").trainName("Allahabadi-Express").acCoaches("Available").acCoachesTotalSeats(120).generalCoaches("Available").generalCoachesTotalSeats(240).build();
        Train train2 = Train.builder().trainNumber("2").trainName("Palghar-Express").acCoaches("Available").acCoachesTotalSeats(110).generalCoaches("Available").generalCoachesTotalSeats(220).build();

        Mockito.when(trainRepository.findAll()).thenReturn(Arrays.asList(train1, train2));
        assertEquals(2, trainService.getAllTrains().size());
    }

    @Test
    void getTrainByTrainID() throws TrainIDNotExistsException {
        Train train1 = Train.builder().trainNumber("1").trainName("Ramshej-Express").acCoaches("Available").acCoachesTotalSeats(100).generalCoaches("Available").generalCoachesTotalSeats(240).build();

        Mockito.when(trainRepository.findById(train1.getTrainNumber())).thenReturn(Optional.of(train1));
        assertEquals("1", trainService.getTrainByTrainID(train1.getTrainNumber()).getTrainNumber());

    }

    @Test
    void updateTrain() throws TrainIDNotExistsException{
        Train train1 = Train.builder().trainNumber("1").trainName("KedarnathShahi").acCoaches("Available").acCoachesTotalSeats(1000).generalCoaches("Available").generalCoachesTotalSeats(2200).build();

        Mockito.lenient().when(trainRepository.save(train1)).thenReturn(train1);
        assertThrows(TrainIDNotExistsException.class, () -> {
            trainService.updateTrain(train1, "1");
        });
    }

    @Test
    void deleteTrainByTrainID() throws TrainIDNotExistsException{
        Train train = Train.builder().trainNumber("100").trainName("Shatabdi Express")
                .acCoaches("Available").acCoachesTotalSeats(200).generalCoaches("Available").generalCoachesTotalSeats(420).build();
        String expectedResult = "Train with ID 100 deleted successfully";
        //Mockito.when(trainRepository.deleteById("100")).thenReturn(expectedResult);
        Mockito.lenient().doNothing().when(trainRepository).deleteById("100");
        assertThrows(TrainIDNotExistsException.class, () -> {
            trainService.deleteTrainByTrainID("100");
        });
    }
}