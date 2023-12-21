package com.booking.train.service;

import com.booking.train.exception.TrainNumberNotExistsException;
import com.booking.train.model.TrainResources;
import com.booking.train.repository.TrainRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.exceptions.misusing.PotentialStubbingProblem;
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
    void addTrainResources() {
        TrainResources trainResources=TrainResources.builder()
                .trainNumber(17644l).trainName("Circar").totalKms(780.0)
                .acCoaches(3).acCoachTotalSeats(168).generalCoaches(1).generalCoachesTotalSeats(60)
                .sleeperCoaches(10).sleeperCoachTotalSeats(600).build();
        Mockito.when(trainRepository.save(trainResources)).thenReturn(trainResources);
        assertEquals(trainResources, trainServiceImpl.addTrainResources(trainResources));
    }

    @Test
    void getAllTrainResources() {
        TrainResources trainResources=TrainResources.builder()
                .trainNumber(17644l).trainName("Circar").totalKms(780.0)
                .acCoaches(3).acCoachTotalSeats(168).generalCoaches(1).generalCoachesTotalSeats(60)
                .sleeperCoaches(10).sleeperCoachTotalSeats(600).build();

        TrainResources trainResources1=TrainResources.builder()
                .trainNumber(12737l).trainName("Gowthami").totalKms(550.0)
                .acCoaches(3).acCoachTotalSeats(168).generalCoaches(1).generalCoachesTotalSeats(60)
                .sleeperCoaches(10).sleeperCoachTotalSeats(600).build();

        Mockito.when(trainRepository.findAll()).thenReturn(Arrays.asList(trainResources,trainResources1));
        assertEquals(2, trainServiceImpl.getAllTrainResources().size());
    }

    @Test
    void getAllTrainResourcesById() throws TrainNumberNotExistsException {
        TrainResources trainResources=TrainResources.builder()
                .trainNumber(17644l).trainName("Circar").totalKms(780.0)
                .acCoaches(3).acCoachTotalSeats(168).generalCoaches(1).generalCoachesTotalSeats(60)
                .sleeperCoaches(10).sleeperCoachTotalSeats(600).build();

        Mockito.when(trainRepository.findById(17644l)).thenReturn(Optional.ofNullable(trainResources));
        assertEquals(trainResources, trainServiceImpl.getAllTrainResourcesById(trainResources.getTrainNumber()));
    }

    @Test
    void getAllTrainResourcesByIdWithException() {
        TrainResources trainResources=TrainResources.builder()
                .trainNumber(1l).trainName("Circar").totalKms(12.2)
                .acCoaches(1).acCoachTotalSeats(56).generalCoaches(1).generalCoachesTotalSeats(60)
                .sleeperCoaches(1).sleeperCoachTotalSeats(60).build();

        Mockito.when(trainRepository.findById(1l)).thenReturn(Optional.empty());

        assertThrows(TrainNumberNotExistsException.class,
                ()->{trainServiceImpl.getAllTrainResourcesById(trainResources.getTrainNumber());});
    }

    @Test
    void updateTrainResource() throws TrainNumberNotExistsException {
        TrainResources trainResources=TrainResources.builder()
                .trainNumber(17644l).trainName("Circar").totalKms(780.0)
                .acCoaches(3).acCoachTotalSeats(168).generalCoaches(1).generalCoachesTotalSeats(60)
                .sleeperCoaches(10).sleeperCoachTotalSeats(600).build();

        Mockito.when(trainRepository.findById(17644l)).thenReturn(Optional.ofNullable(trainResources));
        Mockito.when(trainRepository.save(trainResources)).thenReturn(trainResources);
        assertEquals(trainResources,trainServiceImpl.updateTrainResource(trainResources));
    }

    @Test
    void updateTrainResourceWithException(){
        TrainResources trainResources=TrainResources.builder()
                .trainNumber(1l).trainName("Circar").totalKms(12.2)
                .acCoaches(1).acCoachTotalSeats(56).generalCoaches(1).generalCoachesTotalSeats(60)
                .sleeperCoaches(1).sleeperCoachTotalSeats(60).build();

        Mockito.when(trainRepository.findById(1l)).thenReturn(Optional.empty());
        assertThrows(TrainNumberNotExistsException.class,
                ()->{trainServiceImpl.updateTrainResource(trainResources);});
    }

    @Test
    void deleteTrainResourceByTrainNumber() throws TrainNumberNotExistsException {
        TrainResources trainResources=TrainResources.builder()
                .trainNumber(17644l).trainName("Circar").totalKms(780.0)
                .acCoaches(3).acCoachTotalSeats(168).generalCoaches(1).generalCoachesTotalSeats(60)
                .sleeperCoaches(10).sleeperCoachTotalSeats(600).build();

        Mockito.when(trainRepository.findById(trainResources.getTrainNumber())).thenReturn(Optional.of(trainResources));
        assertEquals("Train Resource deleted by this Train Number successfully", trainServiceImpl.deleteTrainResourceByTrainNumber(trainResources.getTrainNumber()));
    }

    @Test
    void deleteTrainResourceByTrainNumberWithException() {
        TrainResources trainResources=TrainResources.builder()
                .trainNumber(17644l).trainName("Circar").totalKms(780.0)
                .acCoaches(3).acCoachTotalSeats(168).generalCoaches(1).generalCoachesTotalSeats(60)
                .sleeperCoaches(10).sleeperCoachTotalSeats(600).build();

        Mockito.when(trainRepository.findById(17644l)).thenReturn(Optional.empty());
        assertThrows(TrainNumberNotExistsException.class,
                ()->{trainServiceImpl.deleteTrainResourceByTrainNumber(trainResources.getTrainNumber());});
    }
}