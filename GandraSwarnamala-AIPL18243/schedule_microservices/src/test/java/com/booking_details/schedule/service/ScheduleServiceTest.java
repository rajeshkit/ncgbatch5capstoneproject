package com.booking_details.schedule.service;

import com.booking_details.route.exception.RouteIdNotFoundException;
import com.booking_details.route.model.RouteModel;
import com.booking_details.schedule.exception.ScheduleNotFoundException;
import com.booking_details.schedule.model.ScheduleModel;
import com.booking_details.schedule.model.ScheduleRequest;
import com.booking_details.schedule.repository.ScheduleRepository;
import com.booking_details.train.exception.TrainIdNotFoundException;
import com.booking_details.train.model.TrainModel;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
class ScheduleServiceTest {
    @Mock
    private ScheduleRepository scheduleRepository;
    @Mock
    private RestTemplate restTemplate;
    @InjectMocks
    private ScheduleServiceImpl scheduleService;
    private static ScheduleModel scheduleModel1;
    private static ScheduleModel scheduleModel2;
    private static List<ScheduleModel> scheduleModelList;

    private static TrainModel trainModel1;

    private static RouteModel routeModel1;

    private static ScheduleRequest scheduleRequest;

    @BeforeAll
    static void setupModels(){

        trainModel1 = new TrainModel(123,"testTrain",34.5,3,35,4,45,1,10);
        routeModel1 = new RouteModel(17,"chennai","hyderabad",23.87);
        scheduleRequest = new ScheduleRequest(29,LocalDateTime.of(2023,12,11,12,00,00), LocalDateTime.of(2023,12,11,8,00,00),123,17);
        scheduleModel1 = new ScheduleModel(29, LocalDateTime.of(2023,12,11,12,00,00), LocalDateTime.of(2023,12,11,8,00,00),trainModel1,routeModel1);
        scheduleModel2 = new ScheduleModel(59, LocalDateTime.of(2023,11,10,14,00,00), LocalDateTime.of(2023,11,10,21,00,00),trainModel1,routeModel1);
        scheduleModelList = new ArrayList<>();
        scheduleModelList.add(scheduleModel1);
        scheduleModelList.add(scheduleModel2);

    }

    @Test
    void addScheduleDetailsTest() throws TrainIdNotFoundException, RouteIdNotFoundException {
        Mockito.when(scheduleRepository.save(scheduleModel1)).thenReturn(scheduleModel1);
        Mockito.when(restTemplate.getForObject("http://localhost:8081/train-microservice/train/123", TrainModel.class)).thenReturn(trainModel1);
        Mockito.when(restTemplate.getForObject("http://localhost:8082/route-microservice/route/17", RouteModel.class)).thenReturn(routeModel1);

        Assertions.assertEquals(scheduleModel1, scheduleService.addScheduleDetails(scheduleRequest));

        Mockito.when(restTemplate.getForObject("http://localhost:8081/train-microservice/train/123", TrainModel.class)).thenReturn(null);
        Assertions.assertThrows(TrainIdNotFoundException.class,() -> scheduleService.addScheduleDetails(scheduleRequest));

        Mockito.when(restTemplate.getForObject("http://localhost:8081/train-microservice/train/123", TrainModel.class)).thenReturn(trainModel1);
        Mockito.when(restTemplate.getForObject("http://localhost:8082/route-microservice/route/17", RouteModel.class)).thenReturn(null);
        Assertions.assertThrows(RouteIdNotFoundException.class,() -> scheduleService.addScheduleDetails(scheduleRequest));

    }

    @Test
    void getScheduleDetailsTest(){
        Mockito.when(scheduleRepository.findAll()).thenReturn(scheduleModelList);
        Assertions.assertEquals(scheduleModelList,scheduleService.getScheduleDetails());
    }

    @Test
    void getUpdateDeleteScheduleDetailsById() throws ScheduleNotFoundException, TrainIdNotFoundException, RouteIdNotFoundException {
        Optional<ScheduleModel> optional = Optional.of(scheduleModel1);
        Mockito.when(scheduleRepository.findById(29L)).thenReturn(optional);
        Mockito.when(scheduleRepository.findById(59L)).thenReturn(Optional.empty());
        Mockito.when(scheduleRepository.save(scheduleModel1)).thenReturn(scheduleModel1);
        Mockito.when(restTemplate.getForObject("http://localhost:8081/train-microservice/train/123", TrainModel.class)).thenReturn(trainModel1);
        Mockito.when(restTemplate.getForObject("http://localhost:8082/route-microservice/route/17", RouteModel.class)).thenReturn(routeModel1);

        Assertions.assertEquals(scheduleModel1,scheduleService.getScheduleDetailsById(29));
        Assertions.assertThrows(ScheduleNotFoundException.class,() -> scheduleService.getScheduleDetailsById(59));

        Assertions.assertEquals(scheduleModel1,scheduleService.updateScheduleDetails(scheduleRequest));

        Assertions.assertEquals("Schedule details deleted succesfully...",scheduleService.deleteScheduleDetails(29));

    }

    @Test
    void getScheduleDetailsByTrainIdTest(){
        Mockito.when(scheduleRepository.findByTrain_TrainNumber(123)).thenReturn(scheduleModel1);
        Assertions.assertEquals(scheduleModel1,scheduleService.getScheduleDetailsByTrainId(123L));
    }

}
