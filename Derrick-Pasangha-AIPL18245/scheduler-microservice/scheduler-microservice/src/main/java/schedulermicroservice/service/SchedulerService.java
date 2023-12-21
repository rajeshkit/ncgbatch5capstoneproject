package schedulermicroservice.service;
import schedulermicroservice.model.Route;
import schedulermicroservice.model.Scheduler;
import schedulermicroservice.model.SchedulerRequest;
import schedulermicroservice.model.Train;

import java.util.List;

public interface SchedulerService {
    SchedulerRequest addScheduleRequest(SchedulerRequest scheduler);
    List<SchedulerRequest> getAllScheduleRequests();

    Train getAllTrains(int trainNo);
    Route getAllRoutes(int routeId);

    Scheduler addSchedule(SchedulerRequest schedulerRequest, Train train, int trainNo, Route route, int routeId);

    Train addTrain(Train train);
    Route addRoute(Route route);
}
