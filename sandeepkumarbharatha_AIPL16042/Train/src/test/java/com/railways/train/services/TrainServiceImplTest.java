package com.railways.train.services;

import com.railways.train.exceptions.TrainNumberNotFound;
import com.railways.train.model.Train;
import com.railways.train.repository.TrainRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class TrainServiceImplTest {
    @Mock
    private TrainRepository trainRepository;
    @InjectMocks
    private TrainServiceImpl trainServiceImpl;

    @Test
    void addTrainDetails() {
        Train train = Train.builder().trainName("narayanadri").trainNumber(1234)
                .totalKms(450).acCoaches(2).acCoachTotalSeats(12)
                .sleeperCoaches(5).sleeperCoachTotalSeats(10)
                .generalCoaches(9).generalCoacheTotalSeats(300).build();
        Mockito.when(trainRepository.save(train)).thenReturn(train);
        assertEquals(train, trainServiceImpl.addTrainDetails(train));
    }

    @Test
    void getTrainByNumber() throws TrainNumberNotFound {
        Train train = Train.builder().trainName("narayanadri").trainNumber(1234)
                .totalKms(450).acCoaches(2).acCoachTotalSeats(12)
                .sleeperCoaches(5).sleeperCoachTotalSeats(10)
                .generalCoaches(9).generalCoacheTotalSeats(300).build();

        Mockito.when(trainRepository.findById(train.getTrainNumber())).thenReturn(Optional.of(train));
        assertEquals(train, trainServiceImpl.getTrainByNumber(train.getTrainNumber()));
    }

    @Test
    void getTrainByNumberWithException() {
        Train train = Train.builder().trainName("narayanadri").trainNumber(1234)
                .totalKms(450).acCoaches(2).acCoachTotalSeats(12)
                .sleeperCoaches(5).sleeperCoachTotalSeats(10)
                .generalCoaches(9).generalCoacheTotalSeats(300).build();
        Mockito.when(trainRepository.findById(train.getTrainNumber())).thenReturn(Optional.empty());
        assertThrows(TrainNumberNotFound.class,
                () -> {
                    trainServiceImpl.getTrainByNumber(1234L);
                });
    }
    @Test
    void getAllDetails() {
        Train train = Train.builder().trainName("narayanadri").trainNumber(1234)
                .totalKms(450).acCoaches(2).acCoachTotalSeats(12)
                .sleeperCoaches(5).sleeperCoachTotalSeats(10)
                .generalCoaches(9).generalCoacheTotalSeats(300).build();
        Train train1 = Train.builder().trainName("narayanadri").trainNumber(1234)
                .totalKms(450).acCoaches(2).acCoachTotalSeats(12)
                .sleeperCoaches(5).sleeperCoachTotalSeats(10)
                .generalCoaches(9).generalCoacheTotalSeats(300).build();
        Mockito.when(trainRepository.findAll()).thenReturn(Arrays.asList(train1,train));
        assertEquals(2,trainServiceImpl.getAllDetails().size());

    }
    @Test
    void updateTainDetails() throws TrainNumberNotFound {
        Train train = Train.builder().trainName("narayanadri").trainNumber(1234L)
                .totalKms(450).acCoaches(2).acCoachTotalSeats(12)
                .sleeperCoaches(5).sleeperCoachTotalSeats(10)
                .generalCoaches(9).generalCoacheTotalSeats(300).build();
        Mockito.when(trainRepository.findById(train.getTrainNumber())).thenReturn(Optional.ofNullable(train));
        Mockito.when(trainRepository.save(train)).thenReturn(train);
        assertEquals(train, trainServiceImpl.updateTainDetails(train));
    }

    @Test
    void updateTrainDeteailsWithException() {
        Train train = Train.builder().trainName("narayanadri").trainNumber(1234L)
                .totalKms(450).acCoaches(2).acCoachTotalSeats(12)
                .sleeperCoaches(5).sleeperCoachTotalSeats(10)
                .generalCoaches(9).generalCoacheTotalSeats(300).build();
        Mockito.when(trainRepository.findById(train.getTrainNumber())).thenReturn(Optional.empty());
        assertThrows(TrainNumberNotFound.class,
                () -> {
                    trainServiceImpl.updateTainDetails(train);
                });
    }


    @Test
    void deleteByTrainNumber() throws TrainNumberNotFound {
        Train train = Train.builder().trainName("narayanadri").trainNumber(1234L)
                .totalKms(450).acCoaches(2).acCoachTotalSeats(12)
                .sleeperCoaches(5).sleeperCoachTotalSeats(10)
                .generalCoaches(9).generalCoacheTotalSeats(300).build();
         Mockito.when(trainRepository.findById(train.getTrainNumber())).thenReturn(Optional.of(train));
         assertEquals("details are deleted sucessfully of train number"+train.getTrainNumber(),trainServiceImpl.deleteByTrainNumber(train.getTrainNumber()));
      }
    @Test
    void deleteByTrainNumberWithException() {
        Train train = Train.builder().trainName("narayanadri").trainNumber(1234L)
                .totalKms(450).acCoaches(2).acCoachTotalSeats(12)
                .sleeperCoaches(5).sleeperCoachTotalSeats(10)
                .generalCoaches(9).generalCoacheTotalSeats(300).build();

        Mockito.when(trainRepository.findById(train.getTrainNumber())).thenReturn(Optional.empty());
        assertThrows(TrainNumberNotFound.class,
                () -> {
                    trainServiceImpl.deleteByTrainNumber(train.getTrainNumber());
                });
    }

}