package routemicroservice.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import routemicroservice.exception.RouteIdDoesNotExistException;
import routemicroservice.model.Route;
import routemicroservice.repository.RouteRepository;

import java.util.List;
import java.util.Optional;
@Service
public class RouteServiceImp implements RouteService {
    private static final Logger logger = LoggerFactory.getLogger("logback");

    @Autowired
    RouteRepository routeRepository;
    @Override
    public Route addRoute(Route route) {return routeRepository.save(route);
    }

    @Override
    public List<Route> getAllRoutes() {
        return routeRepository.findAll();
    }

    @Override
    public Optional<Route> getRouteById(int routeId) {
        Optional<Route> ro=routeRepository.findById(routeId);
        if(ro.isEmpty()){
            logger.error("Route is invalid");
            throw new RouteIdDoesNotExistException("Route does not exist");
        }
        return ro;
    }

    @Override
    public Route updateRoute(Route route) {
        Optional<Route> rt = getRouteById(route.getRouteId());
        if (rt == null) {
            logger.error("Route is invalid");
            throw new RouteIdDoesNotExistException("Route Id does not exist");
        }
        return routeRepository.save(route);
    }

    @Override
    public String deleteRouteById(int routeId) {
        String message;
        Optional<Route> rt=getRouteById(routeId);
        if(rt==null)
        {
            logger.error("Route is invalid");
            throw new RouteIdDoesNotExistException("Route does not exist");

        }
        routeRepository.deleteById(routeId);
        message="Route deleted successfully";
        return message;
    }
}
