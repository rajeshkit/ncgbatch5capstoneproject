package com.booking_details.train.service;

import com.booking_details.train.exception.TrainIdNotFoundException;
import com.booking_details.train.model.TrainModel;
import com.booking_details.train.repository.TrainRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
class TrainServiceTest {
    @Mock
    private TrainRepository trainRepository;

    @InjectMocks
    private TrainServiceImpl trainService;

    private static TrainModel trainModel1;
    private static TrainModel trainModel2;

    private static List<TrainModel> trainModelList;

    @BeforeAll
    static void setupModels(){
        trainModel1 = new TrainModel(123,"testTrain",34.5,3,35,4,45,1,10);
        trainModel2 = new TrainModel(234,"testTrain2",54.5,3,35,4,45,1,10);

        trainModelList = new ArrayList<>();
        trainModelList.add(trainModel1);
        trainModelList.add(trainModel2);

    }

    @Test
    void addTrainDetailsTest(){
        Mockito.when(trainRepository.save(trainModel1)).thenReturn(trainModel1);
        Assertions.assertEquals(trainModel1,trainService.addTrainDetails(trainModel1));
    }
   @Test
    void getAllTrainDetailsTest(){
       Mockito.when(trainRepository.findAll()).thenReturn(trainModelList);
       Assertions.assertEquals(234, trainService.getAllTrainDetails().get(1).getTrainNumber());
   }

   @Test
   void getAllTrainDetailsByIdTest() throws TrainIdNotFoundException {
       Optional<TrainModel> optional = Optional.of(trainModel1);
       Mockito.when(trainRepository.findById(123L)).thenReturn(optional);
       Mockito.when(trainRepository.findById(234L)).thenReturn(Optional.empty());
       Assertions.assertEquals(trainModel1,trainService.getAllTrainDetailsById(123L));
       Assertions.assertThrows(TrainIdNotFoundException.class,() -> trainService.getAllTrainDetailsById(234L));
   }

    @Test
    void updateTrainDetailsTest() throws TrainIdNotFoundException {
        Optional<TrainModel> optional = Optional.of(trainModel1);
        Mockito.when(trainRepository.findById(123L)).thenReturn(optional);
        Mockito.when(trainRepository.findById(234L)).thenReturn(Optional.empty());
        Mockito.when(trainRepository.save(trainModel1)).thenReturn(trainModel1);
        Assertions.assertEquals(trainModel1,trainService.updateTrainDetails(trainModel1));
        Assertions.assertThrows(TrainIdNotFoundException.class,() -> trainService.updateTrainDetails(trainModel2));
    }
    @Test
    void deleteTrainDetailsById() throws TrainIdNotFoundException {
        Optional<TrainModel> optional = Optional.of(trainModel1);
        Mockito.when(trainRepository.findById(123L)).thenReturn(optional);
        Mockito.when(trainRepository.findById(234L)).thenReturn(Optional.empty());
        Assertions.assertEquals("Train details of the train number 123 has been successfully deleted!!!!",trainService.deleteTrainDetailsById(123L));
        Assertions.assertThrows(TrainIdNotFoundException.class,() -> trainService.deleteTrainDetailsById(234L));
    }

}
