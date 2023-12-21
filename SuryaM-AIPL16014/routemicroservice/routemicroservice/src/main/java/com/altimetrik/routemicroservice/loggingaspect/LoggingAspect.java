package com.altimetrik.routemicroservice.loggingaspect;

import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {

    private static final Logger logger = LoggerFactory.getLogger(LoggingAspect.class);

    @Before("execution(public * com.altimetrik.routemicroservice.controller.RouteController.getAllRoute())")
    public void logBeforeGetAllRoute(){
        logger.info("Retrieving complete route details");
    }

    @AfterReturning("execution(public * com.altimetrik.routemicroservice.controller.RouteController.getAllRoute())")
    public void logAfterGetAllRoute(){
        logger.info("Successfully retrieved all route details");
    }

    @AfterThrowing("execution(public * com.altimetrik.routemicroservice.controller.RouteController.getAllRoute())")
    public void logExceptionForGetAllRoute(){
        logger.info("There seems to be a problem in fetching route details");
    }

    @Before("execution(public * com.altimetrik.routemicroservice.controller.RouteController.addRoute(..))")
    public void logBeforeAddRoute(){
        logger.info("Retrieving complete route details as a request for adding route");
    }

    @AfterReturning("execution(public * com.altimetrik.routemicroservice.controller.RouteController.addRoute(..))")
    public void logAfterAddRoute(){
        logger.info("Successfully added route along with its details");
    }

    @AfterThrowing("execution(public * com.altimetrik.routemicroservice.controller.RouteController.addRoute(..))")
    public void logExceptionForAddRoute(){
        logger.info("There seems to be a problem in adding route details");
    }

    @Before("execution(public * com.altimetrik.routemicroservice.controller.RouteController.updateRoute(..))")
    public void logBeforeUpdateRoute(){
        logger.info("Retrieving complete route details as a request for updating the existing route");
    }

    @AfterReturning("execution(public * com.altimetrik.routemicroservice.controller.RouteController.updateRoute(..))")
    public void logAfterUpdateRoute(){
        logger.info("Successfully updated route details");
    }

    @AfterThrowing("execution(public * com.altimetrik.routemicroservice.controller.RouteController.updateRoute(..))")
    public void logExceptionForUpdateRoute(){
        logger.info("There seems to be a problem in updating route details");
    }

    @Before("execution(public * com.altimetrik.routemicroservice.controller.RouteController.getRouteById(..))")
    public void logBeforeGetRouteById(){
        logger.info("Retrieving route details by ID");
    }

    @AfterReturning("execution(public * com.altimetrik.routemicroservice.controller.RouteController.getRouteById(..))")
    public void logAfterGetRouteById(){
        logger.info("Successfully retrieved route details by ID");
    }

    @AfterThrowing("execution(public * com.altimetrik.routemicroservice.controller.RouteController.getRouteById(..))")
    public void logExceptionForGetRouteById(){
        logger.info("There seems to be a problem in retrieving route details by ID");
    }

    @Before("execution(public * com.altimetrik.routemicroservice.controller.RouteController.deleteRouteById(..))")
    public void logBeforeDeleteRouteById(){
        logger.info("Deleting route by ID");
    }

    @AfterReturning("execution(public * com.altimetrik.routemicroservice.controller.RouteController.deleteRouteById(..))")
    public void logAfterDeleteRouteById(){
        logger.info("Successfully deleted route by ID");
    }

    @AfterThrowing("execution(public * com.altimetrik.routemicroservice.controller.RouteController.deleteRouteById(..))")
    public void logExceptionForDeleteRouteById(){
        logger.info("There seems to be a problem in deleting route by ID");
    }
}
