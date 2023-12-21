package com.altimetrik.train.service;

import com.altimetrik.train.exception.TrainNumberNotExistException;
import com.altimetrik.train.model.Train;
import com.altimetrik.train.repository.TrainRepository;
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
    void addTrain() {

        Train tr1 = Train.builder()
                .trainNumber(111)
                .trainName("Test-tr1")
                .totalKms(999)
                .acCoaches(11)
                .acCoachTotalSeats(333)
                .sleeperCoaches(11)
                .sleeperCoachTotalSeats(333)
                .generalCoaches(11)
                .generalCoachTotalSeats(333)
                .build();

        Mockito.when(trainRepository.save(tr1)).thenReturn(tr1);
        assertEquals(tr1, trainServiceImpl.addTrain(tr1));

    }

    @Test
    void getAllTrains() {

        Train tr1 = Train.builder()
                .trainNumber(111)
                .trainName("Test-tr1")
                .totalKms(999)
                .acCoaches(11)
                .acCoachTotalSeats(333)
                .sleeperCoaches(11)
                .sleeperCoachTotalSeats(333)
                .generalCoaches(11)
                .generalCoachTotalSeats(333)
                .build();

        Train tr2 = Train.builder()
                .trainNumber(111).trainName("Test-tr2")
                .totalKms(888)
                .acCoaches(22)
                .acCoachTotalSeats(444)
                .sleeperCoaches(22)
                .sleeperCoachTotalSeats(444)
                .generalCoaches(22)
                .generalCoachTotalSeats(334)
                .build();

        Mockito.when(trainRepository.findAll()).thenReturn(Arrays.asList(tr1, tr2));
        assertEquals(2, trainServiceImpl.getAllTrains().size());
    }

    @Test
    void getTrainByTrainNumber() throws TrainNumberNotExistException {

        Train tr1 = Train.builder()
                .trainNumber(111)
                .trainName("Test-tr1")
                .totalKms(999)
                .acCoaches(11)
                .acCoachTotalSeats(333)
                .sleeperCoaches(11)
                .sleeperCoachTotalSeats(333)
                .generalCoaches(11)
                .generalCoachTotalSeats(333)
                .build();

        Mockito.when(trainRepository.findById(111)).thenReturn(Optional.of(tr1));
        assertEquals(111, trainServiceImpl.getTrainByTrainNumber(111).getTrainNumber());

    }

    @Test
    void getTrainByIdWithException() {

        Train tr1 = Train.builder()
                .trainNumber(111)
                .trainName("Test-tr1")
                .totalKms(999)
                .acCoaches(11)
                .acCoachTotalSeats(333)
                .sleeperCoaches(11)
                .sleeperCoachTotalSeats(333)
                .generalCoaches(11)
                .generalCoachTotalSeats(333)
                .build();

        Mockito.when(trainRepository.findById(222)).thenReturn(Optional.empty());
        assertThrows(TrainNumberNotExistException.class, () -> {
            trainServiceImpl.getTrainByTrainNumber(222);
        });
    }

    @Test
    void updateTrain() throws Exception{

        Train tr1 = Train.builder()
                .trainNumber(111)
                .trainName("Test-tr2")
                .totalKms(999)
                .acCoaches(11)
                .acCoachTotalSeats(333)
                .sleeperCoaches(11)
                .sleeperCoachTotalSeats(333)
                .generalCoaches(11)
                .generalCoachTotalSeats(333)
                .build();

        Mockito.when(trainRepository.findById(111)).thenReturn(Optional.of(tr1));
        Mockito.when(trainRepository.save(tr1)).thenReturn(tr1);
        assertEquals(tr1, trainServiceImpl.updateTrain(tr1));
    }
}