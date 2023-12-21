package com.altimetrik.trainbooking.service;

import com.altimetrik.trainbooking.Repository.TrainRepository;
import com.altimetrik.trainbooking.exception.NoSuchElementException;
import com.altimetrik.trainbooking.service.TrineServiceImp;
import com.altimetrik.trainbooking.modle.Train;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;
import java.util.Date;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(MockitoExtension.class)
public class TrineServiceImpTest {
    @Mock
    private TrainRepository trainRepository;
    @InjectMocks
    private TrineServiceImp trineServiceImp;
    @Test
    void addTrain() {
        Train t1 = Train.builder()
                .trainNumber(100).trainName("Water Bootle").acCoaches(23).totalKms(34).acCoachTotalSeats(76)
                .sleeperCoaches(78).sleeperCoachTotalSeats(678).generalCoaches(87).generalCoacheTotalSeats(67).build();
        Mockito.when(trainRepository.save(t1)).thenReturn(t1);
        assertEquals(t1,trineServiceImp.addTrain(t1));
    }

    @Test
    void getAllTrain() {
        Train t1 = Train.builder()
                .trainNumber(100).trainName("Water Bootle").acCoaches(23).totalKms(34).acCoachTotalSeats(76)
                .sleeperCoaches(78).sleeperCoachTotalSeats(678).generalCoaches(87).generalCoacheTotalSeats(67).build();
        Train t2 = Train.builder()
                .trainNumber(101).trainName("shatabhi Express").acCoaches(33).totalKms(34).acCoachTotalSeats(56)
                .sleeperCoaches(98).sleeperCoachTotalSeats(78).generalCoaches(77).generalCoacheTotalSeats(87).build();
        Mockito.when(trainRepository.findAll()).thenReturn(Arrays.asList(t1,t2));
        assertEquals(2,trineServiceImp.getAllTrain().size());
    }

    @Test
    void getProductByNumbre() throws NoSuchElementException {
        Train t1 = Train.builder()
                .trainNumber(100).trainName("Water Bootle").acCoaches(23).totalKms(34).acCoachTotalSeats(76)
                .sleeperCoaches(78).sleeperCoachTotalSeats(678).generalCoaches(87).generalCoacheTotalSeats(67).build();

        Mockito.when(trainRepository.findById(100)).thenReturn(Optional.of(t1));
        assertEquals(100,trineServiceImp.getTrainByNumber(100).getTrainNumber());
    }

    @Test
    void updateProduct() {
    }

    @Test
    void deleteProductById() {
    }
}
