package com.altimetrikfinalproject.schedule.controller;

import com.altimetrikfinalproject.schedule.entity.NewScheduleRequest;
import com.altimetrikfinalproject.schedule.entity.ScheduleResponse;
import com.altimetrikfinalproject.schedule.entity.TrainResponse;
import com.altimetrikfinalproject.schedule.exception.NoScheduleFoundException;
import com.altimetrikfinalproject.schedule.exception.RouteIdDoesNotExistException;
import com.altimetrikfinalproject.schedule.exception.TrainIdDoesNotExistException;
import com.altimetrikfinalproject.schedule.service.ScheduleService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/schedule-api")
public class ScheduleController {
    @Autowired
    private ScheduleService service;


//    @GetMapping(value = "/scheduler")
//    public List<NewScheduleRequest> getAllSchedules(){
//        return service.getAllSchedule();
//    }


    @GetMapping(value = "/scheduler1/{train_id}/{schedule_id}")
    public ScheduleResponse getScheduleByTrainID(@PathVariable int train_id, @PathVariable int schedule_id){
        return service.getScheduleByTrainID(train_id,schedule_id);
    }

    @GetMapping(value = "/scheduler/{route_id}/{schedule_id}")
    public ScheduleResponse getScheduleByRouteID(@PathVariable int route_id, @PathVariable int schedule_id) throws NoScheduleFoundException {
        return service.getScheduleByRouteID(route_id, schedule_id);
    }


    @PostMapping(value = "/scheduler")
    public ScheduleResponse addNewScheduleRequest(@RequestBody @Valid NewScheduleRequest newScheduleRequest){
        return service.addNewScheduleRequest(newScheduleRequest);
    }
    @GetMapping(value = "/scheduler")
    public List<NewScheduleRequest> getAllSchedules(){
        return service.getAllSchedule();
    }
    @PutMapping(value ="/scheduler")
    public NewScheduleRequest updateSchedule(@RequestBody @Valid NewScheduleRequest newScheduleRequest) throws TrainIdDoesNotExistException {
        return service.updateSchedule(newScheduleRequest);
    }
    @DeleteMapping(value = "/scheduler/{trainId}")
    public String deleteSchedule(@PathVariable("trainId") int trainId){
        service.deleteSchedule(trainId);
        return "The schedule has been deleted";
    }
}
