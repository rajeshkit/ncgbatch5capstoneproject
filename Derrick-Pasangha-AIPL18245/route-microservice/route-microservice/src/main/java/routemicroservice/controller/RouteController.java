package routemicroservice.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import routemicroservice.model.Route;
import routemicroservice.service.RouteService;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
@RestController
@RequestMapping("/train-api")
public class RouteController {
    private static final Logger logger = LoggerFactory.getLogger("logback");
    @Autowired
    private RouteService routeService;
    @PostMapping(value="/route")
    public Route addRoute(@RequestBody @Valid Route route){
        logger.info("Adding route details");
        return routeService.addRoute(route);
    }
    @GetMapping(value="/route")
    public List<Route> getAllRoutes(){
        logger.info("Printing all routes");
        return routeService.getAllRoutes();
    }
    @GetMapping(value="/route/{id}")
    public Optional<Route> getRouteById(@PathVariable("id") int routeId){
        return routeService.getRouteById(routeId);
    }
    @PutMapping(value="/route")
    public Route updateRoute(@RequestBody @Valid Route route){

        logger.info("Executing update of route");
        return routeService.updateRoute(route);
    }
    @DeleteMapping(value="/route/{id}")
    public String deleteRouteById(@PathVariable("id") int routeId){
        return routeService.deleteRouteById(routeId);
    }
}

