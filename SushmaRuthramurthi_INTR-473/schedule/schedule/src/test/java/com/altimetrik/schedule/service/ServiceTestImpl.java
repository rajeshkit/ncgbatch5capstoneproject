package com.altimetrik.schedule.service;

import com.altimetrik.schedule.Dto.Route;
import com.altimetrik.schedule.Dto.ScheduleResponse;
import com.altimetrik.schedule.Dto.Train;
import com.altimetrik.schedule.Dto.TrainCoachResponse;
import com.altimetrik.schedule.client.RouteClient;
import com.altimetrik.schedule.client.TrainClient;
import com.altimetrik.schedule.entity.Schedule;
import com.altimetrik.schedule.exception.InvalidRouteException;
import com.altimetrik.schedule.exception.InvalidTrainException;
import com.altimetrik.schedule.repository.ScheduleRepository;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;


public class ServiceTestImpl {
    @Mock
    private TrainClient trainClient;
    @Mock
    private RouteClient routeClient;
    @Mock
    private ScheduleRepository scheduleRepository;
    @InjectMocks
    private ScheduleServiceImpl scheduleService;

    @Test
    public void addScheduleTest() throws InvalidTrainException, InvalidRouteException {
        Schedule schedule=getSchedule();
        Train train=getTrain();
        Route route=getRoute();
        trainClient=mock(TrainClient.class);
        routeClient=mock(RouteClient.class);
        scheduleRepository=mock(ScheduleRepository.class);
        when(trainClient.getTrain(anyInt())).thenReturn(train);
        when(routeClient.getRouteById(anyInt())).thenReturn(route);
        when(scheduleRepository.save(schedule)).thenReturn(schedule);
        scheduleService=new ScheduleServiceImpl(trainClient,routeClient,scheduleRepository);
        Schedule schedule1=scheduleService.addSchedule(schedule);
        Assert.assertEquals(schedule1.getScheduleId(),schedule.getScheduleId());
    }


    private Schedule getSchedule() {
        Schedule schedule = new Schedule();
        schedule.setScheduleId(1);
        schedule.setRouteId(2);
        schedule.setTrainNumber(3);
        schedule.setArrivalDateTime(new Date());
        schedule.setDepartureDateTime(new Date());
        return schedule;
    }

    private Train getTrain(){
        Train train=new Train();
        train.setTrainNumber(1);
        train.setTrainName("sapthagiri");
        train.setTrainCoaches(List.of (getTrainCoaches()));
        return  train;
    }
    private TrainCoachResponse getTrainCoaches() {
        TrainCoachResponse trainCoachResponse = new TrainCoachResponse();
        trainCoachResponse.setId(1);
        trainCoachResponse.setCoachsize(5);
        trainCoachResponse.setCoachtypeid(1);
        return trainCoachResponse;
    }
    private Route getRoute(){
        Route route=new Route();
        route.setRouteId(1);
        route.setSource("Chennai");
        route.setDestination("Bangalore");
        route.setTotalKms(150);
        return route;
    }

    @Test
    public void getScheduleByIdTest() throws InvalidTrainException, InvalidRouteException {
        Schedule schedule=getSchedule();
        Train train=getTrain();
        Route route=getRoute();
        trainClient=mock(TrainClient.class);
        routeClient=mock(RouteClient.class);
        scheduleRepository=mock(ScheduleRepository.class);
        when(trainClient.getTrain(anyInt())).thenReturn(train);
        when(routeClient.getRouteById(anyInt())).thenReturn(route);
        when(scheduleRepository.findById(schedule.getScheduleId())).thenReturn(Optional.of(schedule));
        scheduleService=new ScheduleServiceImpl(trainClient,routeClient,scheduleRepository);
        ScheduleResponse scheduleResponse=scheduleService.getScheduleById(schedule.getScheduleId());
        Assert.assertEquals(scheduleResponse.getScheduleId(),schedule.getScheduleId());
    }

    @Test
    public void updateScheduleTest() throws InvalidTrainException, InvalidRouteException {
        Schedule schedule=getSchedule();
        Train train=getTrain();
        Route route=getRoute();
        trainClient=mock(TrainClient.class);
        routeClient=mock(RouteClient.class);
        scheduleRepository=mock(ScheduleRepository.class);
        when(trainClient.getTrain(anyInt())).thenReturn(train);
        when(routeClient.getRouteById(anyInt())).thenReturn(route);
        when(scheduleRepository.findById(schedule.getScheduleId())).thenReturn(Optional.of(schedule));
        when(scheduleRepository.save(schedule)).thenReturn(schedule);
        scheduleService=new ScheduleServiceImpl(trainClient,routeClient,scheduleRepository);
        Schedule schedule1=scheduleService.updateSchedule(schedule);
        Assert.assertEquals(schedule1.getScheduleId(),schedule.getScheduleId());
    }

    @Test
    public void deleteScheduleTest() throws InvalidTrainException, InvalidRouteException {
        Schedule schedule=getSchedule();
        Train train=getTrain();
        Route route=getRoute();
        trainClient=mock(TrainClient.class);
        routeClient=mock(RouteClient.class);
        scheduleRepository=mock(ScheduleRepository.class);
        when(scheduleRepository.findById(any())).thenReturn(Optional.of(schedule));
        doNothing().when(scheduleRepository).deleteById(anyInt());
        scheduleService=new ScheduleServiceImpl(trainClient,routeClient,scheduleRepository);
        String schedule1=scheduleService.deleteSchedule(schedule.getScheduleId());
        Assert.assertEquals(schedule1,"Deleted Successfully");
    }

    @Test (expected = RuntimeException.class)
    public void deleteScheduleErrorTest() {
        Schedule schedule=getSchedule();
        Train train=getTrain();
        Route route=getRoute();
        trainClient=mock(TrainClient.class);
        routeClient=mock(RouteClient.class);
        scheduleRepository=mock(ScheduleRepository.class);
        when(scheduleRepository.findById(any())).thenReturn(null);
        doNothing().when(scheduleRepository).deleteById(anyInt());
        scheduleService=new ScheduleServiceImpl(trainClient,routeClient,scheduleRepository);
        String schedule1=scheduleService.deleteSchedule(schedule.getScheduleId());
    }
}