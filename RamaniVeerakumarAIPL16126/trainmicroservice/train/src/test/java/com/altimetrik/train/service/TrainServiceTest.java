package com.altimetrik.train.service;
import com.altimetrik.train.exception.TrainIdNotExistsException;
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
 class TrainServiceTest{
    @Mock
    private TrainRepository trainRepository;
    @InjectMocks
    private TrainServiceImp trainServiceImp;
    @Test
    void addTrain() {
        Train train2 = Train.builder().trainNumber(100).trainName("express two").totalKms(600)
                .acCoaches(6).acCoachTotalSeats(60).sleeperCoaches(9).sleeperCoachTotalSeats(150)
                .generalCoaches(8).generalCoacheTotalSeats(160).build();
        Mockito.when(trainRepository.save(train2)).thenReturn(train2);
        assertEquals(train2,trainServiceImp.addTrain(train2));
    }
    @Test
    void getAllTrains() {
        Train train = Train.builder().trainNumber(100).trainName("express two").totalKms(600)
                .acCoaches(6).acCoachTotalSeats(60).sleeperCoaches(9).sleeperCoachTotalSeats(150)
                .generalCoaches(8).generalCoacheTotalSeats(160).build();
        Train train2 = Train.builder().trainNumber(101).trainName("express one").totalKms(500)
                .acCoaches(5).acCoachTotalSeats(50).sleeperCoaches(8).sleeperCoachTotalSeats(200)
                .generalCoaches(8).generalCoacheTotalSeats(160).build();
        Mockito.when(trainRepository.findAll()).thenReturn(Arrays.asList(train,train2));
        assertEquals(2,trainServiceImp.getAllTrains().size());
    }
    @Test
    void getTrainByNumber() throws TrainIdNotExistsException {
        Train train = Train.builder().trainNumber(100).trainName("express two").totalKms(600)
                .acCoaches(6).acCoachTotalSeats(60).sleeperCoaches(9).sleeperCoachTotalSeats(150)
                .generalCoaches(8).generalCoacheTotalSeats(160).build();

        Mockito.when(trainRepository.findById(100)).thenReturn(Optional.of(train));
        assertEquals(100,trainServiceImp.getTrainByNumber(100).getTrainNumber());
    }
    @Test
    void getTrainByNumberWithException()  {
        Train train = Train.builder().trainNumber(100).trainName("express two").totalKms(600)
                .acCoaches(6).acCoachTotalSeats(60).sleeperCoaches(9).sleeperCoachTotalSeats(150)
                .generalCoaches(8).generalCoacheTotalSeats(160).build();
        Mockito.when(trainRepository.findById(400)).thenReturn(Optional.empty());
        assertThrows(TrainIdNotExistsException.class,
                ()->{trainServiceImp.getTrainByNumber(400);});

    }
    @Test
    void updateTrain() throws TrainIdNotExistsException {

        Train train = Train.builder()
                .trainNumber(10)
                .trainName("thuthukudi express")
                .totalKms(600)
                .acCoaches(20)
                .acCoachTotalSeats(30)
                .sleeperCoaches(7)
                .sleeperCoachTotalSeats(15)
                .generalCoaches(30)
                .generalCoacheTotalSeats(70).build();


        Train updateTrain = Train.builder()
                .trainNumber(10)
                .trainName("chennai express")
                .acCoaches(16)
                .acCoachTotalSeats(21)
                .sleeperCoaches(8)
                .sleeperCoachTotalSeats(13)
                .generalCoaches(20)
                .generalCoacheTotalSeats(25).build();

        Mockito.when(trainRepository.findById(10)).thenReturn(Optional.of(train));
        Mockito.when(trainRepository.save(updateTrain)).thenReturn(updateTrain);
        assertEquals(updateTrain,trainServiceImp.updateTrain(updateTrain));

    }

    @Test
    void deleteTrainByNumber() throws TrainIdNotExistsException {

        Train t= Train.builder()
                .trainNumber(15)
                .trainName("chennai Express")
                .totalKms(600)
                .acCoaches(14)
                .acCoachTotalSeats(25)
                .sleeperCoaches(18)
                .sleeperCoachTotalSeats(32)
                .generalCoaches(10)
                .generalCoacheTotalSeats(20).build();

        Mockito.when(trainRepository.findById(15)).thenReturn(Optional.of(t));
        assertEquals("Train deleted successfully",trainServiceImp.deleteTrainByNumber(15));
    }


}
