package schedulermicroservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import schedulermicroservice.exception.RouteIdDoesNotExistException;
import schedulermicroservice.model.Route;
import schedulermicroservice.model.Scheduler;
import schedulermicroservice.model.SchedulerRequest;
import schedulermicroservice.model.Train;
import schedulermicroservice.repository.RouteRepository;
import schedulermicroservice.repository.SchedulerAddRepository;
import schedulermicroservice.repository.SchedulerRepository;
import schedulermicroservice.repository.TrainRepository;

import java.util.List;

@Service
public class SchedulerServiceImp implements SchedulerService{
    @Autowired
    private SchedulerAddRepository schedulerAddRepository;
    @Autowired
    private SchedulerRepository schedulerRepository;
    @Autowired
    private TrainRepository trainRepository;
    @Autowired
    private RouteRepository routeRepository;

    @Override
    public SchedulerRequest addScheduleRequest(SchedulerRequest scheduler) {
         return schedulerAddRepository.save(scheduler);
    }

    @Override
    public List<SchedulerRequest> getAllScheduleRequests() {
        return schedulerAddRepository.findAll();
    }

    @Override
    public Train getAllTrains(int trainNo) {
        String apiurl="http://localhost:8080/train-api/train/"+trainNo;
        RestTemplate restTemplate=new RestTemplate();
        return restTemplate.getForEntity(apiurl, Train.class).getBody();
    }
    @Override
    public Route getAllRoutes(int routeId) {
        String apiurl="http://localhost:8181/train-api/route/"+routeId;
        RestTemplate restTemplate=new RestTemplate();
        return restTemplate.getForEntity(apiurl, Route.class).getBody();
    }

    @Override
    public Scheduler addSchedule(SchedulerRequest schedulerRequest, Train train, int trainNo, Route route, int routeId) {
       Scheduler sched=new Scheduler();
        if ((train.getTrainNumber()==trainNo) && (route.getRouteId()==routeId)){
            sched.setRoute(route.toString());
            sched.setTrain(train.toString());
            sched.setScheduleId(schedulerRequest.getScheduleId());
            sched.setArrivalDateTime(schedulerRequest.getArrivalDateTime());
            sched.setDepartureDateTime(schedulerRequest.getDepartureDateTime());
        }
        else throw new RouteIdDoesNotExistException("Train or route invalid");
        return schedulerRepository.save(sched);
    }

    @Override
    public Train addTrain(Train train) {
        return trainRepository.save(train);
    }

    @Override
    public Route addRoute(Route route) {
        return routeRepository.save(route);
    }


}
