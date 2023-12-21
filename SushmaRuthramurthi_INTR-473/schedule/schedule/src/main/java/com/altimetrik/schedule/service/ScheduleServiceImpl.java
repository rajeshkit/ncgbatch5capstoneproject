package com.altimetrik.schedule.service;

import com.altimetrik.schedule.Dto.Route;
import com.altimetrik.schedule.Dto.ScheduleResponse;
import com.altimetrik.schedule.Dto.Train;
import com.altimetrik.schedule.client.RouteClient;
import com.altimetrik.schedule.client.TrainClient;
import com.altimetrik.schedule.entity.Schedule;
import com.altimetrik.schedule.exception.InvalidRouteException;
import com.altimetrik.schedule.exception.InvalidTrainException;
import com.altimetrik.schedule.repository.ScheduleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ScheduleServiceImpl implements ScheduleService {

    private TrainClient trainClient;
    private RouteClient routeClient;
    private ScheduleRepository scheduleRepository;

    @Autowired
    public ScheduleServiceImpl(TrainClient trainClient, RouteClient routeClient, ScheduleRepository scheduleRepository) {
        this.trainClient = trainClient;
        this.routeClient = routeClient;
        this.scheduleRepository = scheduleRepository;
    }

    @Override
    public Schedule addSchedule(Schedule schedule) throws InvalidTrainException, InvalidRouteException {
        Train train = trainClient.getTrain(schedule.getTrainNumber());
        Route route = routeClient.getRouteById(schedule.getRouteId());
        if (train != null
                && route != null) {
            return scheduleRepository.save(schedule);
        } else {
            throw new RuntimeException("There is something went wrong");
        }
    }

    @Override
    public ScheduleResponse getScheduleById(int scheduleId) throws InvalidTrainException, InvalidRouteException {
        if (scheduleRepository.findById(scheduleId) != null) {
            Schedule schedule = scheduleRepository.findById(scheduleId).get();
            Train train = trainClient.getTrain(schedule.getTrainNumber());
            Route route = routeClient.getRouteById(schedule.getRouteId());
            ScheduleResponse scheduleResponse = new ScheduleResponse();
            scheduleResponse.setScheduleId(schedule.getScheduleId());
            scheduleResponse.setDepartureDateTime(schedule.getDepartureDateTime());
            scheduleResponse.setArrivalDateTime(schedule.getArrivalDateTime());
            scheduleResponse.setTrain(train);
            scheduleResponse.setRoute(route);
            return scheduleResponse;
        } else {
            throw new RuntimeException("There is something went wrong");
        }
    }

    @Override
    public List<ScheduleResponse> getAllSchedule() throws InvalidTrainException, InvalidRouteException {

        List<Schedule> schedule = scheduleRepository.findAll();
        List<ScheduleResponse> list = new ArrayList<>();
        schedule.forEach((s) -> {
                    try {
                        Train train = trainClient.getTrain(s.getTrainNumber());
                        Route route = routeClient.getRouteById(s.getRouteId());
                        ScheduleResponse scheduleResponse = new ScheduleResponse();
                        scheduleResponse.setScheduleId(s.getScheduleId());
                        scheduleResponse.setDepartureDateTime(s.getDepartureDateTime());
                        scheduleResponse.setArrivalDateTime(s.getArrivalDateTime());
                        scheduleResponse.setTrain(train);
                        scheduleResponse.setRoute(route);
                        list.add(scheduleResponse);
                    } catch (InvalidTrainException | InvalidRouteException ex) {
                        throw new RuntimeException(ex.getMessage());
                    }
                }
        );
        return list;
    }

    @Override
    public Schedule updateSchedule(Schedule schedule) throws InvalidTrainException, InvalidRouteException {
        Train train = trainClient.getTrain(schedule.getTrainNumber());
        Route route = routeClient.getRouteById(schedule.getRouteId());
        Schedule schedule1=scheduleRepository.findById(schedule.getScheduleId()).get();
        if (train != null
                && route != null && schedule1 != null) {
            return scheduleRepository.save(schedule);
        } else {
            throw new RuntimeException("There is something went wrong");
        }
    }

    @Override
    public String deleteSchedule(int scheduleId) {
        if(scheduleRepository.findById(scheduleId)!=null) {
            scheduleRepository.deleteById(scheduleId);
            return "Deleted Successfully";
        }else{
            throw new RuntimeException("Invalid ScheduleId!!");
        }
    }
}


