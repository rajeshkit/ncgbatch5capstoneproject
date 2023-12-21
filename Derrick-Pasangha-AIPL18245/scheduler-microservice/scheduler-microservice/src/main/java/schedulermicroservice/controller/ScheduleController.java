package schedulermicroservice.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import schedulermicroservice.exception.RouteIdDoesNotExistException;
import schedulermicroservice.exception.TrainNoDoesNotExistException;
import schedulermicroservice.model.Route;
import schedulermicroservice.model.Scheduler;
import schedulermicroservice.model.SchedulerRequest;
import schedulermicroservice.model.Train;
import schedulermicroservice.service.SchedulerService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/train-api")
public class ScheduleController {
    static int trainNo;
    static int routeId;
    static SchedulerRequest request;
    private static final Logger logger = LoggerFactory.getLogger("logback");


    Scheduler scheduler=new Scheduler();

    SchedulerRequest schedulerRequest=new SchedulerRequest();
    Train train;
    Route route;
    @Autowired
    private SchedulerService schedulerService;
    @PostMapping(value="/scheduleRequest1")
    public SchedulerRequest addScheduleRequest(@RequestBody @Valid SchedulerRequest scheduler){
        logger.info("Adding schedule request details");
        trainNo= scheduler.getTrainNo();
        routeId=scheduler.getRouteId();
        request=schedulerService.addScheduleRequest(scheduler);
        return schedulerService.addScheduleRequest(scheduler);
    }

    @GetMapping(value="/scheduleRequest")
    public List<SchedulerRequest> getAllScheduleRequests(){
        logger.info("Printing all the schedule requests");
        return schedulerService.getAllScheduleRequests();
    }
    @GetMapping(value="/traindata2")
    public Train fetchTrainData() {
        logger.info("Fetching train data");
        train=schedulerService.getAllTrains(trainNo);
        if(train==null){
            throw new TrainNoDoesNotExistException("Train does not exist");
        }
        return schedulerService.getAllTrains(trainNo);
    }
    @GetMapping(value="/routedata3")
    public Route fetchRouteData() {
        logger.info("Fetching route data");
        route=schedulerService.getAllRoutes(routeId);
        if(route==null){
            throw new RouteIdDoesNotExistException("Route Id does not exist");
        }
        return schedulerService.getAllRoutes(routeId);
    }
    @PostMapping(value="/scheduler4")
    @Validated
    public Scheduler create(){
        logger.info("Creating the scheduler table with details");
       return schedulerService.addSchedule(request,train,trainNo,route,routeId);

    }

    @GetMapping(value="/scheduler/train5")
    public Train addTrain(){
        if(train==null){
            throw new TrainNoDoesNotExistException("Train does not exist");
        }
        return schedulerService.addTrain(train);
    }

    @GetMapping(value="/scheduler/route6")
    public Route addRoute(){
        if(route==null){
            throw new RouteIdDoesNotExistException("Route Id does not exist");
        }
        return schedulerService.addRoute(route);
    }

}
