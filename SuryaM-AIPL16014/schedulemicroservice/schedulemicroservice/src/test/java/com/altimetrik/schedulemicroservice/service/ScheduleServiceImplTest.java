package com.altimetrik.schedulemicroservice.service;

import com.altimetrik.schedulemicroservice.exception.ScheduleIdNotExistsException;
import com.altimetrik.schedulemicroservice.model.NewScheduleRequest;
import com.altimetrik.schedulemicroservice.model.Route;
import com.altimetrik.schedulemicroservice.model.Schedule;
import com.altimetrik.schedulemicroservice.model.Train;
import com.altimetrik.schedulemicroservice.repository.ScheduleRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.Date;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
@ExtendWith(MockitoExtension.class)
class ScheduleServiceImplTest {

    @Mock
    private RestTemplate restTemplate;
    @Mock
    private ScheduleRepository scheduleRepository;
    @InjectMocks
    private ScheduleServiceImpl scheduleServiceImpl;

    @Test
    void addSchedule() {

        NewScheduleRequest newScheduleRequest = new NewScheduleRequest();
        newScheduleRequest.setTrainNumber(123);
        newScheduleRequest.setRouteId(456);
        newScheduleRequest.setDepartureDateTime(new Date());
        newScheduleRequest.setArrivalDateTime(new Date());

        Train mockTrain = new Train();
        mockTrain.setTrainNumber(123);
        mockTrain.setTrainName("MockTrain");

        Route mockRoute = new Route();
        mockRoute.setRouteId(456);
        mockRoute.setSource("Source");
        mockRoute.setDestination("Destination");
        mockRoute.setTotalKms(100.0);

        Mockito.when(restTemplate.getForObject("http://localhost:8080/train-api/train/{trainNumber}",
                Train.class, 123)).thenReturn(mockTrain);

        Mockito.when(restTemplate.getForObject("http://localhost:8081/route-api/route/{routeId}",
                Route.class, 456)).thenReturn(mockRoute);

        Schedule mockSchedule = new Schedule();
        Mockito.when(scheduleRepository.save(Mockito.any(Schedule.class))).thenReturn(mockSchedule);

        Schedule result = scheduleServiceImpl.addSchedule(newScheduleRequest);

        assertEquals(mockSchedule, result);
    }

    @Test
    void getAllSchedule() {

        Train t= Train.builder()
                .trainNumber(100)
                .trainName("Super Express")
                .totalKms(200)
                .acCoaches(15)
                .acCoachTotalSeats(20)
                .sleeperCoaches(8)
                .sleeperCoachTotalSeats(12)
                .generalCoaches(20)
                .generalCoachTotalSeats(28).build();

        Route r= Route.builder()
                .routeId(100)
                .Source("Chennai")
                .Destination("Bangalore")
                .totalKms(200).build();

        Schedule s1 = Schedule.builder()
                .scheduleId(34567)
                .departureDateTime(new Date())
                .arrivalDateTime(new Date())
                .train(t.toString())
                .route(r.toString())
                .build();

        Schedule s2 = Schedule.builder()
                .scheduleId(34568)
                .departureDateTime(new Date())
                .arrivalDateTime(new Date())
                .train("Test Train 2")
                .route("Test Route 2")
                .build();

        Mockito.when(scheduleRepository.findAll()).thenReturn(Arrays.asList(s1,s2));
        assertEquals(2,scheduleServiceImpl.getAllSchedule().size());
    }

    @Test
    void getScheduleById() throws ScheduleIdNotExistsException {

        Schedule schedule = Schedule.builder()
                .scheduleId(34567)
                .departureDateTime(new Date())
                .arrivalDateTime(new Date())
                .train("Test Train 1")
                .route("Test Route 1")
                .build();

        Mockito.when(scheduleRepository.findById(34567)).thenReturn(Optional.of(schedule));
        assertEquals(34567, scheduleServiceImpl
                .getScheduleById(34567)
                .getScheduleId());
    }

    @Test
    void updateSchedule() throws ScheduleIdNotExistsException {

        int scheduleId = 1;
        Date originalDepartureDateTime = new Date();
        Date originalArrivalDateTime = new Date();

        Schedule existingSchedule = new Schedule();
        existingSchedule.setScheduleId(scheduleId);
        existingSchedule.setDepartureDateTime(originalDepartureDateTime);
        existingSchedule.setArrivalDateTime(originalArrivalDateTime);

        Schedule updatedSchedule = new Schedule();
        updatedSchedule.setScheduleId(scheduleId);
        Date newDepartureDateTime = new Date();
        Date newArrivalDateTime = new Date();
        updatedSchedule.setDepartureDateTime(newDepartureDateTime);
        updatedSchedule.setArrivalDateTime(newArrivalDateTime);

        Mockito.when(scheduleRepository.findById(scheduleId)).thenReturn(Optional.of(existingSchedule));
        Mockito.when(scheduleRepository.save(existingSchedule)).thenReturn(existingSchedule);

        Schedule result = scheduleServiceImpl.updateSchedule(updatedSchedule);

        assertEquals(scheduleId, result.getScheduleId());
        assertEquals(newDepartureDateTime, result.getDepartureDateTime());
        assertEquals(newArrivalDateTime, result.getArrivalDateTime());
    }

    @Test
    void updateScheduleWithNonExistingScheduleId() {
        // Mock data
        int nonExistingScheduleId = 1;

        Schedule updatedSchedule = new Schedule();
        updatedSchedule.setScheduleId(nonExistingScheduleId);
        updatedSchedule.setDepartureDateTime(new Date());
        updatedSchedule.setArrivalDateTime(new Date());

        Mockito.when(scheduleRepository.findById(nonExistingScheduleId)).thenReturn(Optional.empty());

        assertThrows(ScheduleIdNotExistsException.class,
                () -> scheduleServiceImpl.updateSchedule(updatedSchedule));
    }

    @Test
    void deleteScheduleById() {

        int nonExistingScheduleId = 1;

        Mockito.when(scheduleRepository.findById(nonExistingScheduleId)).thenReturn(Optional.empty());
        assertThrows(ScheduleIdNotExistsException.class,
                () -> scheduleServiceImpl.deleteScheduleById(nonExistingScheduleId));
    }
}