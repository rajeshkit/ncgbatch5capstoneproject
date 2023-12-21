package com.schedule.schedulemicroservice.controllerTest;

import com.schedule.schedulemicroservice.controller.ScheduleController;
import com.schedule.schedulemicroservice.entity.NewScheduleRequest;
import com.schedule.schedulemicroservice.entity.Route;
import com.schedule.schedulemicroservice.entity.Schedule;
import com.schedule.schedulemicroservice.entity.Train;
import com.schedule.schedulemicroservice.service.ScheduleService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
class ScheduleControllerTest {


    @Mock
    ScheduleService scheduleService;

    @InjectMocks
    ScheduleController scheduleController;


    @Test
    void testGetAllDetails(){

        Schedule schedule = new Schedule();

        // Setting data using setters
        schedule.setDepartureDateTime(LocalDateTime.now());
        schedule.setArrivalDateTime(LocalDateTime.now().plusHours(2));
        NewScheduleRequest request = new NewScheduleRequest();
        // Setting data using setters
        request.setDepartureDateTime(LocalDateTime.now());
        request.setArrivalDateTime(LocalDateTime.now().plusHours(2));
        request.setTrainNumber(123);
        request.setRouteId(456);
        Train train = new Train();
        train.setTrainNumber(123);
        train.setAcCoaches(12);

        Route route = new Route();
        route.setSource("CityA");
        route.setDestination("CityB");

        // Setting Train and Route to Schedule
        schedule.setTrain(train);
        schedule.setRoute(route);
        Mockito.when(scheduleService.getAllDetails(request)).thenReturn(schedule);
        Schedule schedule1=scheduleController.getAllDetails(request);
        Assertions.assertEquals("CityA",schedule.getRoute().getSource());
    }
}
