package com.TrainBookingSystem.Train.Services;

;
import com.TrainBookingSystem.Train.Exception.TrainIdNotFoundException;
import com.TrainBookingSystem.Train.Repository.TrainResourcesRepository;
import com.TrainBookingSystem.Train.model.TrainResources;
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

    @InjectMocks
    private TrainServiceImpl trainServiceImpl;
    @Mock
    private TrainResourcesRepository trainResourcesRepository;

    @Test
    void addTrainResources() {

        TrainResources t1= TrainResources.builder().trainNumber(12345L).trainName("gfsgjh").totalKms(12.2).acCoaches(5).acCoachTotalSeats(20)
                .sleeperCoaches(5).sleeperCoachTotalSeats(50).generalCoaches(5).generalCoachesTotalSeats(50).build();

        Mockito.when(trainResourcesRepository.save(t1)).thenReturn(t1);
        assertEquals(t1,trainServiceImpl.addTrainResources(t1));
    }

    @Test
    void getAllTrainDetail() {
        TrainResources t1=TrainResources.builder().trainNumber(12345L).trainName("gfsgjh").totalKms(12.2).acCoaches(5).acCoachTotalSeats(20)
                .sleeperCoaches(5).sleeperCoachTotalSeats(50).generalCoaches(5).generalCoachesTotalSeats(50).build();
        TrainResources t2=TrainResources.builder().trainNumber(76345L).trainName("tfthgjh").totalKms(12.2).acCoaches(5).acCoachTotalSeats(20)
                .sleeperCoaches(5).sleeperCoachTotalSeats(50).generalCoaches(5).generalCoachesTotalSeats(50).build();

        Mockito.when(trainResourcesRepository.findAll()).thenReturn(Arrays.asList(t1,t2));
        assertEquals(2,trainServiceImpl.getAllTrainDetail().size());
    }

    @Test
    void getTrainById() throws TrainIdNotFoundException {
        TrainResources t1=TrainResources.builder().trainNumber(12345L).trainName("gfsgjh").totalKms(12.2).acCoaches(5).acCoachTotalSeats(20)
                .sleeperCoaches(5).sleeperCoachTotalSeats(50).generalCoaches(5).generalCoachesTotalSeats(50).build();
        TrainResources t2=TrainResources.builder().trainNumber(76345L).trainName("tfthgjh").totalKms(12.2).acCoaches(5).acCoachTotalSeats(20)
                .sleeperCoaches(5).sleeperCoachTotalSeats(50).generalCoaches(5).generalCoachesTotalSeats(50).build();

        Mockito.when(trainResourcesRepository.findById(12345L)).thenReturn(Optional.of(t1));
        assertEquals(12345L,trainServiceImpl.getTrainById(12345L).getTrainNumber());
    }

    @Test
    void getProductByIdWithException()  {
        TrainResources t1=TrainResources.builder().trainNumber(12345L).trainName("gfsgjh").totalKms(12.2).acCoaches(5).acCoachTotalSeats(20)
                .sleeperCoaches(5).sleeperCoachTotalSeats(50).generalCoaches(5).generalCoachesTotalSeats(50).build();

        Mockito.when(trainResourcesRepository.findById(12345L)).thenReturn(Optional.empty());
        assertThrows(TrainIdNotFoundException.class,
                ()->{trainServiceImpl.getTrainById(12345L);});
    }

    @Test
    void updateTrainDetail() {
        TrainResources t1=TrainResources.builder().trainNumber(12345L).trainName("gfsgjh").totalKms(12.2).acCoaches(5).acCoachTotalSeats(20)
                .sleeperCoaches(5).sleeperCoachTotalSeats(50).generalCoaches(5).generalCoachesTotalSeats(50).build();

        Mockito.when(trainResourcesRepository.save(t1)).thenReturn(t1);
        assertEquals(t1,trainServiceImpl.addTrainResources(t1));

    }

    @Test
    void deleteTrainById() throws TrainIdNotFoundException {
        TrainResources t1=TrainResources.builder().trainNumber(12345L).trainName("gfsgjh").totalKms(12.2).acCoaches(5).acCoachTotalSeats(20)
                .sleeperCoaches(5).sleeperCoachTotalSeats(50).generalCoaches(5).generalCoachesTotalSeats(50).build();

        Mockito.when(trainResourcesRepository.findById(12345L)).thenReturn(Optional.of(t1));
        assertEquals("Train detail deleted successfully",trainServiceImpl.deleteTrainById(12345L));
    }
}