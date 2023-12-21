package controller;

import Model.Train;
import Repository.TrainRepository;
import Service.TrainService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
public class TrainControllertest {

    @Mock
    private TrainRepository trainRepository;

    @InjectMocks
    private TrainService trainService;

    @Test
    public void testGetTrainDetailsById() {
        // Mock data
        Train train = new Train();
        train.setTrainId(1L);
        train.setTrainName("Express");
        train.setTotalKms(100);
        when(trainRepository.findById(1L)).thenReturn(Optional.of(train));
        Train fetchedTrain = trainService.getTrainDetailsById(1L);

        assertEquals("Express", fetchedTrain.getTrainName());
        assertEquals(100, fetchedTrain.getTotalKms());

    }
}
