package com.Booking.train.service;

import com.Booking.train.customexception.TrainIdNotFoundException;
import com.Booking.train.model.TrainResources;
import com.Booking.train.repository.TrainRepository;
import com.Booking.train.services.TrainServiceImplement;
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
public class TrainServiceTest {
    @Mock
    private TrainRepository trainRepository;
    @InjectMocks
    private TrainServiceImplement trainServiceImplement;

    @Test
    void addTrain() {
        TrainResources t= TrainResources.builder().trainNumber(199L).trainName("kachigudaexpress").totalkms(111.0).acCoaches(10).acCoachTotalSeats(300).sleepercoaching(65).sleeperCoachTotalSeats(90).generalCoaches(40).generalCoachesTotalSeats(10000).build();
        Mockito.when(trainRepository.save(t)).thenReturn(t);
        assertEquals(t,trainServiceImplement.addTrain(t));
    }

    @Test
    void getAllTrainDetail() {
        TrainResources t1= TrainResources.builder().trainNumber(199L).trainName("kachigudaexpress").totalkms(111.0).acCoaches(10).acCoachTotalSeats(300).sleepercoaching(65).sleeperCoachTotalSeats(90).generalCoaches(40).generalCoachesTotalSeats(10000).build();
        TrainResources t2= TrainResources.builder().trainNumber(12345L).trainName("hydexpress").totalkms(134.0).acCoaches(35).acCoachTotalSeats(180).sleepercoaching(8).sleeperCoachTotalSeats(40).generalCoaches(8).generalCoachesTotalSeats(500).build();
        Mockito.when(trainRepository.findAll()).thenReturn(Arrays.asList(t1,t2));
        assertEquals(2,trainServiceImplement.getAllTrainDetail().size());
    }

    @Test
    void getTrainById() throws TrainIdNotFoundException {
        TrainResources t= TrainResources.builder().trainNumber(199L).trainName("kachigudaexpress").totalkms(111.0).acCoaches(10).acCoachTotalSeats(300).sleepercoaching(65).sleeperCoachTotalSeats(90).generalCoaches(40).generalCoachesTotalSeats(10000).build();
        Mockito.when(trainRepository.findById(199L)).thenReturn(Optional.of(t));
        assertEquals(t,trainServiceImplement.getTrainById(199L));
    }
    @Test
    void getTrainByIdWithException() throws TrainIdNotFoundException {
        TrainResources t= TrainResources.builder().trainNumber(199L).trainName("kachigudaexpress").totalkms(111.0).acCoaches(10).acCoachTotalSeats(300).sleepercoaching(65).sleeperCoachTotalSeats(90).generalCoaches(40).generalCoachesTotalSeats(10000).build();
        Mockito.when(trainRepository.findById(500L)).thenReturn(Optional.empty());
        assertThrows(TrainIdNotFoundException.class,()->{trainServiceImplement.getTrainById(500L);});
    }

    @Test
    void updateTrain() throws TrainIdNotFoundException {
        TrainResources t= TrainResources.builder().trainNumber(12345L).trainName("kachigudaexpress").totalkms(111.0).acCoaches(10).acCoachTotalSeats(300).sleepercoaching(65).sleeperCoachTotalSeats(90).generalCoaches(40).generalCoachesTotalSeats(10000).build();
        Mockito.when(trainRepository.findById(12345L)).thenReturn(Optional.of(t));
        Mockito.when(trainRepository.save(t)).thenReturn(t);
        assertEquals(t,trainServiceImplement.updateTrain(t));
    }
    @Test
    void updateTrainWithException() throws TrainIdNotFoundException {
        TrainResources t= TrainResources.builder().trainNumber(12345L).trainName("kachigudaexpress").totalkms(111.0).acCoaches(10).acCoachTotalSeats(300).sleepercoaching(65).sleeperCoachTotalSeats(90).generalCoaches(40).generalCoachesTotalSeats(10000).build();
        Mockito.when(trainRepository.findById(12345L)).thenReturn(Optional.empty());
        assertThrows(TrainIdNotFoundException.class,()->{trainServiceImplement.updateTrain(t);});
    }

    @Test
    void deleteTrainById() throws TrainIdNotFoundException {
        TrainResources t= TrainResources.builder().trainNumber(12345L).trainName("kachigudaexpress").totalkms(111.0).acCoaches(10).acCoachTotalSeats(300).sleepercoaching(65).sleeperCoachTotalSeats(90).generalCoaches(40).generalCoachesTotalSeats(10000).build();
        Mockito.when(trainRepository.findById(12345L)).thenReturn(Optional.of(t));
        assertEquals("Train detail deleted successfully",trainServiceImplement.deleteTrainById(12345L));
    }
    @Test
    void deleteTrainByIdWithException() throws TrainIdNotFoundException {
        TrainResources t= TrainResources.builder().trainNumber(12345L).trainName("kachigudaexpress").totalkms(111.0).acCoaches(10).acCoachTotalSeats(300).sleepercoaching(65).sleeperCoachTotalSeats(90).generalCoaches(40).generalCoachesTotalSeats(10000).build();
        Mockito.when(trainRepository.findById(500L)).thenReturn(Optional.empty());
        assertThrows(TrainIdNotFoundException.class,()->{trainServiceImplement.deleteTrainById(500L);});
    }
}
