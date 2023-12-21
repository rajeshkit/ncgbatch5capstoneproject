package com.ScheduleMicroservices.Schedule.Services;

import com.RouteMicroservices.Route.model.RouteResources;
import com.ScheduleMicroservices.Schedule.Repository.ScheduleResourcesRepository;
import com.ScheduleMicroservices.Schedule.exception.ScheduleIdNotFoundException;
import com.ScheduleMicroservices.Schedule.model.NewScheduleResources;
import com.ScheduleMicroservices.Schedule.model.ScheduleResources;
import com.ScheduleMicroservices.Schedule.service.ScheduleService;
import com.ScheduleMicroservices.Schedule.service.ScheduleServiceImpl;
import com.TrainBookingSystem.Train.Exception.TrainIdNotFoundException;
import com.TrainBookingSystem.Train.model.TrainResources;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.sql.Timestamp;
import java.util.Arrays;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;


@ExtendWith(MockitoExtension.class)
class ScheduleServiceImplTest {

    @Mock
    private ScheduleResourcesRepository scheduleResourcesRepository;

    @InjectMocks
    private ScheduleService scheduleService = new ScheduleServiceImpl();

    @Mock
    private RestTemplate restTemplate;


    @Test
    void addScheduleResources() {

        ScheduleResources scheduleResources = ScheduleResources.builder().scheduleId(67988L).arrivalDateTime(Timestamp.valueOf("2019-02-06 11:32:02")).departureDateTime(Timestamp.valueOf("2019-02-06 11:32:02")).trainId(123L).routeId(1L).build();
        TrainResources trainResponse = TrainResources.builder().trainNumber(103L).trainName("gfsgjh").totalKms(12.2).acCoaches(5).acCoachTotalSeats(20)
                .sleeperCoaches(5).sleeperCoachTotalSeats(50).generalCoaches(5).generalCoachesTotalSeats(50).build();

        RouteResources routeResponse = RouteResources.builder().routeId(12L).source("Hyderabad").destination("Pune").totalKms(12.4).build();

        Mockito.when(restTemplate.getForEntity(any(String.class), eq(TrainResources.class))).thenReturn(new ResponseEntity<>(trainResponse,HttpStatus.OK));
        Mockito.when(restTemplate.getForEntity(any(String.class), eq(RouteResources.class))).thenReturn(new ResponseEntity<>(routeResponse,HttpStatus.OK));

        NewScheduleResources newScheduleResources = NewScheduleResources.builder().scheduleId(67988L).arrivalDateTime(scheduleResources.getArrivalDateTime())
                .departureDateTime(scheduleResources.getDepartureDateTime()).trainResources(trainResponse).routeResources(routeResponse).build();


        Mockito.when(scheduleResourcesRepository.save(any(NewScheduleResources.class))).thenReturn(newScheduleResources);
        assertEquals(newScheduleResources, scheduleService.addScheduleResources(scheduleResources));

    }


    @Test
    void findDetailByTrainId() throws TrainIdNotFoundException {
        ScheduleResources scheduleResources = ScheduleResources.builder().scheduleId(67988L).arrivalDateTime(Timestamp.valueOf("2019-02-06 11:32:02")).departureDateTime(Timestamp.valueOf("2019-02-06 11:32:02")).trainId(123L).routeId(1L).build();
        TrainResources trainResponse = TrainResources.builder().trainNumber(103L).trainName("gfsgjh").totalKms(12.2).acCoaches(5).acCoachTotalSeats(20)
                .sleeperCoaches(5).sleeperCoachTotalSeats(50).generalCoaches(5).generalCoachesTotalSeats(50).build();

        RouteResources routeResponse = RouteResources.builder().routeId(12L).source("Hyderabad").destination("Pune").totalKms(12.4).build();

        NewScheduleResources newScheduleResources = NewScheduleResources.builder().scheduleId(67988L).arrivalDateTime(scheduleResources.getArrivalDateTime())
                .departureDateTime(scheduleResources.getDepartureDateTime()).trainResources(trainResponse).routeResources(routeResponse).build();
        Mockito.when(scheduleResourcesRepository.findByTrainResources_trainNumber(103L)).thenReturn(Arrays.asList(newScheduleResources));
        assertEquals(Arrays.asList(newScheduleResources), scheduleService.findDetailByTrainId(103L));

    }

    @Test
    void getScheduleById() throws ScheduleIdNotFoundException {
        ScheduleResources scheduleResources = ScheduleResources.builder().scheduleId(67988L).arrivalDateTime(Timestamp.valueOf("2019-02-06 11:32:02")).departureDateTime(Timestamp.valueOf("2019-02-06 11:32:02")).trainId(123L).routeId(1L).build();
        TrainResources trainResponse = TrainResources.builder().trainNumber(103L).trainName("gfsgjh").totalKms(12.2).acCoaches(5).acCoachTotalSeats(20)
                .sleeperCoaches(5).sleeperCoachTotalSeats(50).generalCoaches(5).generalCoachesTotalSeats(50).build();

        RouteResources routeResponse = RouteResources.builder().routeId(12L).source("Hyderabad").destination("Pune").totalKms(12.4).build();
        NewScheduleResources newScheduleResources = NewScheduleResources.builder().scheduleId(67988L).arrivalDateTime(scheduleResources.getArrivalDateTime())
                .departureDateTime(scheduleResources.getDepartureDateTime()).trainResources(trainResponse).routeResources(routeResponse).build();

        Mockito.when(scheduleResourcesRepository.findById(67988L)).thenReturn(Optional.ofNullable(newScheduleResources));
        assertEquals(newScheduleResources, scheduleService.getScheduleById(67988L));
    }

    @Test
    void updateScheduleDetail() {

        ScheduleResources scheduleResources = ScheduleResources.builder().scheduleId(67988L).arrivalDateTime(Timestamp.valueOf("2019-02-06 11:32:02")).departureDateTime(Timestamp.valueOf("2019-02-06 11:32:02")).trainId(123L).routeId(1L).build();
        TrainResources trainResponse = TrainResources.builder().trainNumber(103L).trainName("gfsgjh").totalKms(12.2).acCoaches(5).acCoachTotalSeats(20)
                .sleeperCoaches(5).sleeperCoachTotalSeats(50).generalCoaches(5).generalCoachesTotalSeats(50).build();

        RouteResources routeResponse = RouteResources.builder().routeId(12L).source("Hyderabad").destination("Pune").totalKms(12.4).build();
        NewScheduleResources newScheduleResources = NewScheduleResources.builder().scheduleId(67988L).arrivalDateTime(scheduleResources.getArrivalDateTime())
                .departureDateTime(scheduleResources.getDepartureDateTime()).trainResources(trainResponse).routeResources(routeResponse).build();

        Mockito.when(scheduleResourcesRepository.save(any(NewScheduleResources.class))).thenReturn(newScheduleResources);
        assertEquals(newScheduleResources, scheduleService.updateScheduleDetail(newScheduleResources));

    }

    @Test
    void getAllScheduleDetail() {
        ScheduleResources scheduleResources = ScheduleResources.builder().scheduleId(67988L).arrivalDateTime(Timestamp.valueOf("2019-02-06 11:32:02")).departureDateTime(Timestamp.valueOf("2019-02-06 11:32:02")).trainId(123L).routeId(1L).build();
        TrainResources trainResponse = TrainResources.builder().trainNumber(103L).trainName("gfsgjh").totalKms(12.2).acCoaches(5).acCoachTotalSeats(20)
                .sleeperCoaches(5).sleeperCoachTotalSeats(50).generalCoaches(5).generalCoachesTotalSeats(50).build();

        RouteResources routeResponse = RouteResources.builder().routeId(12L).source("Hyderabad").destination("Pune").totalKms(12.4).build();
        NewScheduleResources newScheduleResources = NewScheduleResources.builder().scheduleId(67988L).arrivalDateTime(scheduleResources.getArrivalDateTime())
                .departureDateTime(scheduleResources.getDepartureDateTime()).trainResources(trainResponse).routeResources(routeResponse).build();

        ScheduleResources scheduleResources1 = ScheduleResources.builder().scheduleId(3458L).arrivalDateTime(Timestamp.valueOf("2019-02-06 11:32:02")).departureDateTime(Timestamp.valueOf("2019-02-06 11:32:02")).trainId(123L).routeId(1L).build();
        TrainResources trainResponse1 = TrainResources.builder().trainNumber(104L).trainName("gfsgjh").totalKms(12.2).acCoaches(5).acCoachTotalSeats(20)
                .sleeperCoaches(5).sleeperCoachTotalSeats(50).generalCoaches(5).generalCoachesTotalSeats(50).build();

        RouteResources routeResponse1 = RouteResources.builder().routeId(92L).source("Hyderabad").destination("Pune").totalKms(12.4).build();
        NewScheduleResources newScheduleResources1 = NewScheduleResources.builder().scheduleId(67988L).arrivalDateTime(scheduleResources.getArrivalDateTime())
                .departureDateTime(scheduleResources.getDepartureDateTime()).trainResources(trainResponse).routeResources(routeResponse).build();

        Mockito.when(scheduleResourcesRepository.findAll()).thenReturn(Arrays.asList(newScheduleResources,newScheduleResources1));
        assertEquals(Arrays.asList(newScheduleResources,newScheduleResources1), scheduleService.getAllScheduleDetail());

    }

    @Test
    void deleteScheduleById() throws ScheduleIdNotFoundException {
        ScheduleResources scheduleResources = ScheduleResources.builder().scheduleId(67988L).arrivalDateTime(Timestamp.valueOf("2019-02-06 11:32:02")).departureDateTime(Timestamp.valueOf("2019-02-06 11:32:02")).trainId(123L).routeId(1L).build();
        TrainResources trainResponse = TrainResources.builder().trainNumber(103L).trainName("gfsgjh").totalKms(12.2).acCoaches(5).acCoachTotalSeats(20)
                .sleeperCoaches(5).sleeperCoachTotalSeats(50).generalCoaches(5).generalCoachesTotalSeats(50).build();

        RouteResources routeResponse = RouteResources.builder().routeId(12L).source("Hyderabad").destination("Pune").totalKms(12.4).build();
        NewScheduleResources newScheduleResources = NewScheduleResources.builder().scheduleId(67988L).arrivalDateTime(scheduleResources.getArrivalDateTime())
                .departureDateTime(scheduleResources.getDepartureDateTime()).trainResources(trainResponse).routeResources(routeResponse).build();

        Mockito.when(scheduleResourcesRepository.findById(67988L)).thenReturn(Optional.of(newScheduleResources));
        assertEquals("Scheduled detail deleted successfully", scheduleService.deleteScheduleById(67988L));

    }
}
