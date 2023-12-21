package com.altimetrik.schedule.service;

import com.altimetrik.schedule.model.RouteS;
import com.altimetrik.schedule.model.Schedule;
import com.altimetrik.schedule.model.ScheduleRequest;
import com.altimetrik.schedule.model.TrainS;
import com.altimetrik.schedule.repository.ScheduleRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.client.RestTemplate;
import java.util.Date;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
 class ScheduleServiceTest {
    @Mock
    private ScheduleRepository scheduleRepository;
    @InjectMocks
    private ScheduleServiceImp scheduleServiceImp;
    @Mock
    private RestTemplate restTemplate;


        @Test
        void addSchedule() {

            ScheduleRequest scheduleRequest = new ScheduleRequest();
            scheduleRequest.setTrainId(12);
            scheduleRequest.setRouteId(46);
            scheduleRequest.setDepartureDateTime(new Date());
            scheduleRequest.setArrivalDateTime(new Date());

            TrainS mockTrain = new TrainS();
            mockTrain.setTrainNumber(12);
            mockTrain.setTrainName("MockTrain");
            mockTrain.setAcCoaches(4);
            mockTrain.setAcCoachTotalSeats(200);
            mockTrain.setSleeperCoaches(2);
            mockTrain.setSleeperCoachTotalSeats(300);
            mockTrain.setGeneralCoaches(2);
            mockTrain.setGeneralCoacheTotalSeats(300);
            mockTrain.setTotalKms(600);


            RouteS mockRoute = new RouteS();
            mockRoute.setRouteId(46);
            mockRoute.setSource("chennai");
            mockRoute.setDestination("dindigul");
            mockRoute.setTotalKms(600);



            when(restTemplate.getForObject(eq("http://localhost:8181/train/12"), eq(TrainS.class))).thenReturn(mockTrain);
            when(restTemplate.getForObject(eq("http://localhost:8383/route/46"), eq(RouteS.class))).thenReturn(mockRoute);


            Schedule result = scheduleServiceImp.addSchedule(scheduleRequest);

            assertEquals(1, result.getScheduleId());
            assertEquals("TrainS(trainNumber=12, trainName=MockTrain, totalKms=600, acCoaches=4, acCoachTotalSeats=200, sleeperCoaches=2, sleeperCoachTotalSeats=300, generalCoaches=2, generalCoacheTotalSeats=300)", result.getTrain());
            assertEquals("RouteS(routeId=46, source=chennai, destination=dindigul, totalKms=600)", result.getRoute());

        }
    }




