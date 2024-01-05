package com.trainbooking.trainmicroservices.service;

import com.trainbooking.trainmicroservices.exception.TrainNumberNotExistException;
import com.trainbooking.trainmicroservices.model.Train;
import com.trainbooking.trainmicroservices.repository.TrainRepository;
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
    private TrainServiceImpl trainServiceImpl;

    @Test
    void addTrain() {
        Train train1 = Train.builder()
                .trainNumber(171717).trainName("Vande Bharat").totalKms(700)
                .acCoaches(6).acCoachTotalSeats(300)
                .sleeperCoaches(4).sleeperCoachesTotalSeats(200)
                .generalCoaches(5).generalCoachesTotalSeats(500).build();

        Mockito.when(trainRepository.save(train1)).thenReturn(train1);
        assertEquals(train1,trainServiceImpl.addTrain(train1));
    }

    @Test
    void getAllTrainDetails() {
        Train train1 = Train.builder()
                .trainNumber(171717).trainName("Vande Bharat").totalKms(700)
                .acCoaches(6).acCoachTotalSeats(300)
                .sleeperCoaches(4).sleeperCoachesTotalSeats(200)
                .generalCoaches(5).generalCoachesTotalSeats(500).build();

        Train train2 = Train.builder()
                .trainNumber(181818).trainName("Bullet Train").totalKms(1700)
                .acCoaches(4).acCoachTotalSeats(200)
                .sleeperCoaches(2).sleeperCoachesTotalSeats(200)
                .generalCoaches(6).generalCoachesTotalSeats(600).build();

        Mockito.when(trainRepository.findAll()).thenReturn(Arrays.asList(train1,train2));
        assertEquals(2,trainServiceImpl.getAllTrainDetails().size());
    }

    @Test
    void getTrainByTrainNumber() throws TrainNumberNotExistException {
        Train train1 = Train.builder()
                .trainNumber(171717).trainName("Vande Bharat").totalKms(700)
                .acCoaches(6).acCoachTotalSeats(300)
                .sleeperCoaches(4).sleeperCoachesTotalSeats(200)
                .generalCoaches(5).generalCoachesTotalSeats(500).build();

        Mockito.when(trainRepository.findById(171717)).thenReturn(Optional.of(train1));
        assertEquals(171717,trainServiceImpl.getTrainByTrainNumber(171717).getTrainNumber());

    }

    @Test
    void getTrainByTrainNumberWithException() {
        Mockito.when(trainRepository.findById(171711)).thenReturn(Optional.empty());
        assertThrows(TrainNumberNotExistException.class, ()-> trainServiceImpl.getTrainByTrainNumber(171711));
    }


}
