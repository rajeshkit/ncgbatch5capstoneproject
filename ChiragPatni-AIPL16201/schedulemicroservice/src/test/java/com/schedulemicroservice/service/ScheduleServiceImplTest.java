package com.schedulemicroservice.service;

import com.schedulemicroservice.exception.RouteNotFoundException;
import com.schedulemicroservice.exception.ScheduleIdDoesNotExistException;
import com.schedulemicroservice.exception.TrainNotFoundException;
import com.schedulemicroservice.model.Route;
import com.schedulemicroservice.model.Schedule;
import com.schedulemicroservice.model.ScheduleRequest;
import com.schedulemicroservice.model.Train;
import com.schedulemicroservice.repository.ScheduleRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.client.RestTemplate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
class ScheduleServiceImplTest {
    @Mock
    private RestTemplate restTemplate;
    @Mock
    private ScheduleRepository scheduleRepository;
    @InjectMocks
    private ScheduleServiceImpl scheduleService;

    @Test
    void testCreateSchedule() throws TrainNotFoundException, RouteNotFoundException {

        ScheduleRequest request = ScheduleRequest.builder()
                .departureDateTime(LocalDateTime.parse("2023-01-01T12:00:00"))
                .arrivalDateTime(LocalDateTime.parse("2023-01-01T18:00:00"))
                .trainNumber(1)
                .routeId(1)
                .build();

        Train train = Train.builder().trainNumber(1).trainName("Rajasthani Expressss").totalKms(1490)
                .acCoaches(3).acCoachTotalSeats(120)
                .sleeperCoaches(5).sleeperCoachTotalSeats(200)
                .generalCoaches(7).generalCoachTotalSeats(280).build();

        Route route = Route.builder()
                .routeId(1)
                .source("Jaipur")
                .destination("Udaipur")
                .totalKms(500)
                .build();

        Mockito.when(restTemplate.getForObject("http://localhost:8080/train-api/1", Train.class)).thenReturn(train);
        Mockito.when(restTemplate.getForObject("http://localhost:8081/route-api/1", Route.class)).thenReturn(route);

        Schedule result = scheduleService.createSchedule(request);

        assertEquals(request.getDepartureDateTime(), result.getDepartureDateTime());
        assertEquals(request.getArrivalDateTime(), result.getArrivalDateTime());
        assertEquals(train, result.getTrain());
        assertEquals(route, result.getRoute());

        Mockito.verify(scheduleRepository, Mockito.times(1)).save(Mockito.any(Schedule.class));
    }

    @Test
    void testCreateScheduleTrainNotFound() {

        ScheduleRequest request = ScheduleRequest.builder()
                .departureDateTime(LocalDateTime.parse("2023-01-01T12:00:00"))
                .arrivalDateTime(LocalDateTime.parse("2023-01-01T18:00:00"))
                .trainNumber(1)
                .routeId(1)
                .build();

        Mockito.when(restTemplate.getForObject("http://localhost:8080/train-api/1", Train.class)).thenReturn(null);

        assertThrows(TrainNotFoundException.class, () -> scheduleService.createSchedule(request));
    }

    @Test
    void testCreateScheduleRouteNotFound() {
        ScheduleRequest request = ScheduleRequest.builder()
                .departureDateTime(LocalDateTime.parse("2023-01-01T12:00:00"))
                .arrivalDateTime(LocalDateTime.parse("2023-01-01T18:00:00"))
                .trainNumber(1)
                .routeId(1)
                .build();

        Train train = Train.builder().trainNumber(1).trainName("Rajasthani Expressss").totalKms(1490)
                .acCoaches(3).acCoachTotalSeats(120)
                .sleeperCoaches(5).sleeperCoachTotalSeats(200)
                .generalCoaches(7).generalCoachTotalSeats(280).build();

        Mockito.when(restTemplate.getForObject("http://localhost:8080/train-api/1", Train.class)).thenReturn(train);
        Mockito.when(restTemplate.getForObject("http://localhost:8081/route-api/1", Route.class)).thenReturn(null);

        assertThrows(RouteNotFoundException.class, () -> scheduleService.createSchedule(request));
    }

    @Test
    void testGetAllSchedules() {
        List<Schedule> mockSchedules = Arrays.asList(
                Schedule.builder().scheduleId(1).build(),
                Schedule.builder().scheduleId(2).build()
        );

        Mockito.when(scheduleRepository.findAll()).thenReturn(mockSchedules);
        Mockito.when(restTemplate.getForObject("http://localhost:8080/train-api/1", Train.class)).thenReturn(Train.builder().trainNumber(1).build());
        Mockito.when(restTemplate.getForObject("http://localhost:8081/route-api/1", Route.class)).thenReturn(Route.builder().routeId(1).build());

        List<Schedule> result = scheduleService.getAllSchedules();

        assertEquals(2, result.size());
    }

    @Test
    void testGetScheduleDetailsById() throws ScheduleIdDoesNotExistException {
        int scheduleId = 1;
        Schedule mockSchedule = Schedule.builder().scheduleId(scheduleId).build();

        Mockito.when(scheduleRepository.findById(scheduleId)).thenReturn(Optional.of(mockSchedule));
        Mockito.when(restTemplate.getForObject("http://localhost:8080/train-api/1", Train.class)).thenReturn(Train.builder().trainNumber(1).build());
        Mockito.when(restTemplate.getForObject("http://localhost:8081/route-api/1", Route.class)).thenReturn(Route.builder().routeId(1).build());

        Schedule result = scheduleService.getScheduleDetailsById(scheduleId);

        assertEquals(scheduleId, result.getScheduleId());
    }

    @Test
    void testGetScheduleDetailsByIdScheduleNotFound() {
        int scheduleId = 1;

        Mockito.when(scheduleRepository.findById(scheduleId)).thenReturn(Optional.empty());

        assertThrows(ScheduleIdDoesNotExistException.class, () -> scheduleService.getScheduleDetailsById(scheduleId));
    }

    @Test
    void testUpdateScheduleDetails() throws ScheduleIdDoesNotExistException {
        int scheduleId = 1;
        ScheduleRequest updatedScheduleRequest = ScheduleRequest.builder()
                .departureDateTime(LocalDateTime.parse("2023-01-01T14:00:00"))
                .arrivalDateTime(LocalDateTime.parse("2023-01-01T20:00:00"))
                .trainNumber(1)
                .routeId(1)
                .build();

        Schedule mockSchedule = Schedule.builder().scheduleId(scheduleId).build();

        Mockito.when(scheduleRepository.findById(scheduleId)).thenReturn(Optional.of(mockSchedule));
        Mockito.when(restTemplate.getForObject("http://localhost:8080/train-api/1", Train.class)).thenReturn(Train.builder().trainNumber(1).build());
        Mockito.when(restTemplate.getForObject("http://localhost:8081/route-api/1", Route.class)).thenReturn(Route.builder().routeId(1).build());

        Schedule result = scheduleService.updateScheduleDetails(scheduleId, updatedScheduleRequest);

        assertEquals(scheduleId, result.getScheduleId());
    }

    @Test
    void testDeleteScheduleDetails() throws ScheduleIdDoesNotExistException {
        int scheduleId = 1;
        Schedule mockSchedule = Schedule.builder().scheduleId(scheduleId).build();

        Mockito.when(scheduleRepository.findById(scheduleId)).thenReturn(Optional.of(mockSchedule));

        String result = scheduleService.deleteScheduleDetails(scheduleId);

        assertEquals("Schedule deleted successfully", result);
    }
}