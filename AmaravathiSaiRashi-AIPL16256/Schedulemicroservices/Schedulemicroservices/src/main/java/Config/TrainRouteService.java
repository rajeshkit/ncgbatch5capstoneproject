package Config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.RouteMatcher;
import org.springframework.web.client.RestTemplate;

@Service
public class TrainRouteService {

    private RestTemplate restTemplate = new RestTemplate();

    @Autowired
    public TrainRouteService() {
        this.restTemplate = restTemplate;
    }

    public TrainRouteService getTrainById(long trainId) {
        String trainServiceUrl = "http://example.com/api/trains" + trainId;
        return restTemplate.getForObject(trainServiceUrl, TrainRouteService.class);
    }

    public RouteMatcher.Route getRouteById(long routeId) {
        String routeServiceUrl = "http://example.com/api/routes/" + routeId;
        return restTemplate.getForObject(routeServiceUrl, RouteMatcher.Route.class);
    }

    public void setTrainId(long l) {
    }

    public void setTrainName(String express) {
    }

    public Object getTrainDetailsById(long l) {
        return null;
    }

    public Object getRouteDetailsById(long l) {
        return null;
    }
}

