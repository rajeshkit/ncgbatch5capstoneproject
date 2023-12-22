package com.altimetrik.train.service;

import com.altimetrik.train.dto.TrainCoachResponse;
import com.altimetrik.train.dto.TrainResponse;
import com.altimetrik.train.entity.Train;
import com.altimetrik.train.entity.TrainCoaches;
import com.altimetrik.train.repository.TrainCoachesRepository;
import com.altimetrik.train.repository.TrainRepository;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.stubbing.OngoingStubbing;

import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


public class TrainServiceImplTest {

    @Mock
    private TrainRepository trainRepository;
    @Mock
    private TrainCoachesRepository trainCoachesRepository;
    @InjectMocks
    private TrainServiceImpl trainService;

    @Test
    void addTrainTest() {
        Train train = getTrain();
        trainRepository = mock(TrainRepository.class);
        trainCoachesRepository = mock(TrainCoachesRepository.class);
        when(trainRepository.save(any())).thenReturn(train);
        trainService = new TrainServiceImpl(trainRepository, trainCoachesRepository);
        TrainResponse trainResponse = trainService.addTrain(train);
        Assert.assertEquals(train.getTrainName(), trainResponse.getTrainName());
    }

    private TrainResponse getTrainResponse() {
        TrainResponse trainResponse = new TrainResponse();
        trainResponse.setTrainName("Garudathri");
        trainResponse.setTrainNumber(100);
        return trainResponse;
    }

    private Train getTrain() {
        Train train = new Train();
        train.setTrainName("Garudathri");
        train.setTrainNumber(100);
        return train;
    }
    @Test
    void addTrianCoachesTest(){
        TrainCoaches trainCoaches=setTrainCoaches();
        trainRepository = mock(TrainRepository.class);
        trainCoachesRepository = mock(TrainCoachesRepository.class);
        when(trainCoachesRepository.save(any())).thenReturn(trainCoaches);
        trainService =new TrainServiceImpl(trainRepository,trainCoachesRepository);
        List<TrainCoachResponse> trainCoachResponses=trainService.addTrainCoaches(1,trainCoaches);
        Assert.assertEquals(trainCoachResponses.get(0).getId(),trainCoaches.getId());

    }
    private TrainCoaches setTrainCoaches(){
        TrainCoaches trainCoaches=new TrainCoaches();
        trainCoaches.setId(1);
        trainCoaches.setCoachsize(5);
        trainCoaches.setTrainnumber(1);
        trainCoaches.setCoachtypeid(1);
        return trainCoaches;
    }
    private TrainCoachResponse getTrainCoachResponse(){
        TrainCoachResponse trainCoachResponse=new TrainCoachResponse();
        trainCoachResponse.setId(1);
        trainCoachResponse.setCoachtypeid(1);
        trainCoachResponse.setCoachsize(5);
        return trainCoachResponse;
    }
    @Test
    void getTrainDetialsTest(){
        int trainNumber=100;
        boolean includeCoaches=true;
        Train train=getTrain();
        trainRepository=mock(TrainRepository.class);
        trainCoachesRepository=mock(TrainCoachesRepository.class);
       when(trainRepository.findById(any())).thenReturn((Optional.of(train)));
        trainService =new TrainServiceImpl(trainRepository,trainCoachesRepository);
        TrainResponse trainResponse= trainService.getTrainDetials(trainNumber,includeCoaches);
        Assert.assertEquals(trainResponse.getTrainNumber(),train.getTrainNumber());
    }
    private TrainResponse getTrainResponsebyId() {
        TrainResponse trainResponse = new TrainResponse();
        trainResponse.setTrainName("Garudathri");
        trainResponse.setTrainNumber(100);
        trainResponse.setTrainCoaches(List.of(getTrainCoachResponse()));
        return trainResponse;
    }


}
