package com.altimetrik.Train.service;

import com.altimetrik.Train.exception.TrainIdNotExistsException;
import com.altimetrik.Train.model.Train;
import com.altimetrik.Train.repository.TrainRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import static org.mockito.Mockito.*;

public class TrainServiceImplTest {

    @Mock
    private TrainRepository trainRepository;

    @InjectMocks
    private TrainServiceImpl trainService;

    @BeforeEach
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testAddTrain() {
        Train trainToAdd = new Train(); // Create a Train object with necessary data for addition
        when(trainRepository.save(trainToAdd)).thenReturn(trainToAdd);

        Train addedTrain = trainService.addTrain(trainToAdd);

        Assertions.assertNotNull(addedTrain);
        // Add assertions based on what is expected after adding a train
    }

    @Test
    public void testGetAllTrains() {
        List<Train> trainList = new ArrayList<>(); // Create a list of trains for testing
        when(trainRepository.findAll()).thenReturn(trainList);

        List<Train> retrievedTrains = trainService.getAllTrains();

        Assertions.assertEquals(trainList, retrievedTrains);
    }

    @Test
    public void testGetTrainById_ExistingId() throws TrainIdNotExistsException {
        int trainId = 1;
        Train train = new Train();
        train.setTrainId(trainId);
        when(trainRepository.findById(trainId)).thenReturn(Optional.of(train));

        Train retrievedTrain = trainService.getTrainById(trainId);

        Assertions.assertEquals(train, retrievedTrain);
    }

    @Test
    public void testGetTrainById_NonExistingId() {
        int trainId = 1;
        when(trainRepository.findById(trainId)).thenReturn(Optional.empty());

        Assertions.assertThrows(TrainIdNotExistsException.class, () -> trainService.getTrainById(trainId));
    }

    @Test
    public void testUpdateTrain() throws TrainIdNotExistsException {
        Train trainToUpdate = new Train(); // Create a Train object with necessary data for update
        trainToUpdate.setTrainId(1); // Assuming the ID exists
        when(trainRepository.save(trainToUpdate)).thenReturn(trainToUpdate);
        when(trainRepository.findById(trainToUpdate.getTrainId())).thenReturn(Optional.of(trainToUpdate));

        Train updatedTrain = trainService.updateTrain(trainToUpdate);

        Assertions.assertNotNull(updatedTrain);
    }

    @Test
    public void testDeleteTrainById_ExistingId() throws TrainIdNotExistsException {
        int trainId = 52;
        Train trainToDelete = new Train();
        trainToDelete.setTrainId(trainId);
        when(trainRepository.findById(trainId)).thenReturn(Optional.of(trainToDelete));

        String deletionMessage = trainService.deleteTrainById(trainId);

        Assertions.assertEquals("Train deleted successfully", deletionMessage);
    }

}
