package com.altimetrik.train.service;
import com.altimetrik.train.exception.TrainAlreadyExistsException;
import com.altimetrik.train.exception.TrainNotExistsException;
import com.altimetrik.train.model.Train;
import com.altimetrik.train.repository.TrainRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
@SpringBootTest
public class TrainServiceImplTest {
    @Mock
    private TrainRepository trainRepository;

    @InjectMocks
    private TrainServiceImpl trainService;

    @Test
    public void testGetAllTrains() {

        List<Train> mockTrains = new ArrayList<>();
        Mockito.when(trainRepository.findAll()).thenReturn(mockTrains);

        List<Train> result = trainService.getAllTrains();

        assertNotNull(result);
        assertEquals(0, result.size());
    }

    @Test
    public void testGetTrainByNum() throws TrainNotExistsException {
        int trainNum = 16591;
        Train mockTrain = new Train();
        Mockito.when(trainRepository.findById(trainNum)).thenReturn(Optional.of(mockTrain));

        Train result = trainService.getTrainByNum(trainNum);

        assertNotNull(result);
        assertEquals(mockTrain, result);
    }

    @Test
    public void testAddTrain() throws TrainAlreadyExistsException {
        Train newTrain = new Train();
        Mockito.when(trainRepository.save(any(Train.class))).thenReturn(newTrain);

        Train result = trainService.addTrain(newTrain);

        assertNotNull(result);
        assertEquals(newTrain, result);
    }

    @Test
    public void testUpdateTrain() throws TrainNotExistsException {

        int existingTrainNum = 16591;
        Train existingTrain = new Train();

        Mockito.when(trainRepository.save(any(Train.class))).thenReturn(existingTrain);
        Mockito.when(trainRepository.findById(existingTrain.getTrainNum())).thenReturn(Optional.of(existingTrain));

        Train result = trainService.updateTrain(existingTrain);

        assertNotNull(result);
        assertEquals(existingTrain, result);
    }

    @Test
    public void testDeleteTrain() throws TrainNotExistsException {

        int trainNum = 16591;
        Train existingTrain = new Train();
        Mockito.when(trainRepository.findById(trainNum)).thenReturn(Optional.of(existingTrain));

        String result = trainService.deleteTrain(trainNum);

        assertEquals("Train deleted successfully", result);
        Mockito.verify(trainRepository, Mockito.times(1)).deleteById(eq(trainNum));
    }
}
