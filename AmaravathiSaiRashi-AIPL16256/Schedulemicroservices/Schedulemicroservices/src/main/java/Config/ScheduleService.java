package Config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ScheduleService {

    private final RestTemplate restTemplate;

    @Autowired
    public ScheduleService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public ScheduleService(TrainRouteService trainService, TrainRouteService routeService, RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public Object scheduleTrainRoute(long l, long l1) {
        return null;
    }
}
