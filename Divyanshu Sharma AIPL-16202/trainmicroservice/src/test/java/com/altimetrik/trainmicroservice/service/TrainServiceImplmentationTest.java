package com.altimetrik.trainmicroservice.service;
import com.altimetrik.trainmicroservice.exception.TrainNumberNotExistsException;
import com.altimetrik.trainmicroservice.model.Train;
import com.altimetrik.trainmicroservice.repository.TrainRepository;
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
class TrainServiceImplmentationTest {

    @Mock
    private TrainRepository trainRepository;

    @InjectMocks
    private TrainServiceImplmentation trainServiceImplmentation;

    @Test
    void addTrainDetail() {
        Train t1 = Train.builder()
                .trainNumber(19208).trainName("Kota-Hisar Superfast").totalKm(350)
                .acCoaches(6).acCoachesTotalSeats(300)
                .sleeperCoaches(4).sleeperCoachesTotalSeats(200)
                .generalCoaches(5).generalCoachesTotalSeats(500).build();

        Mockito.when(trainRepository.save(t1)).thenReturn(t1);
        assertEquals(t1, trainServiceImplmentation.addTrainDetail(t1));
    }

    @Test
    void getTrainByNumber() throws TrainNumberNotExistsException {
        Train t1 = Train.builder()
                .trainNumber(19208).trainName("Kota-Hisar Superfast").totalKm(350)
                .acCoaches(6).acCoachesTotalSeats(300)
                .sleeperCoaches(4).sleeperCoachesTotalSeats(200)
                .generalCoaches(5).generalCoachesTotalSeats(500).build();

        Mockito.when(trainRepository.findById(19208)).thenReturn(Optional.of(t1));
        assertEquals(19208, trainServiceImplmentation.getTrainByNumber(19208).getTrainNumber());
    }

    @Test
    void getAllTrainDetails() {
        Train t1 = Train.builder()
                .trainNumber(19208).trainName("Kota-Hisar Superfast").totalKm(350)
                .acCoaches(6).acCoachesTotalSeats(300)
                .sleeperCoaches(4).sleeperCoachesTotalSeats(200)
                .generalCoaches(5).generalCoachesTotalSeats(500).build();

        Train t2 = Train.builder()
                .trainNumber(18121).trainName("Rajdhani Express").totalKm(1000)
                .acCoaches(6).acCoachesTotalSeats(300)
                .sleeperCoaches(4).sleeperCoachesTotalSeats(200)
                .generalCoaches(5).generalCoachesTotalSeats(500).build();

        Mockito.when(trainRepository.findAll()).thenReturn(Arrays.asList(t1, t2));
        assertEquals(2, trainServiceImplmentation.getAllTrainDetails().size());

    }

    @Test
    void getTrainByNumberWithException() {
        Train t1 = Train.builder()
                .trainNumber(19208).trainName("Kota-Hisar Express").totalKm(700)
                .acCoaches(6).acCoachesTotalSeats(300)
                .sleeperCoaches(4).sleeperCoachesTotalSeats(200)
                .generalCoaches(5).generalCoachesTotalSeats(500).build();

        Mockito.when(trainRepository.findById(19208)).thenReturn(Optional.empty());
        assertThrows(TrainNumberNotExistsException.class, () -> {
            trainServiceImplmentation.getTrainByNumber(19208);
        });
    }
}


