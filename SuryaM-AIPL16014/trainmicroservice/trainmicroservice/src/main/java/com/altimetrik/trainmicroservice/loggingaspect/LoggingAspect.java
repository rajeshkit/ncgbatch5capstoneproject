package com.altimetrik.trainmicroservice.loggingaspect;

import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {

    private static final Logger logger = LoggerFactory.getLogger(LoggingAspect.class);

    @Before("execution(public * com.altimetrik.trainmicroservice.controller.TrainController.getAllTrains())")
    public void logBeforeGetAllTrains(){
        logger.info("Retrieving complete train details");
    }

    @AfterReturning("execution(public * com.altimetrik.trainmicroservice.controller.TrainController.getAllTrains())")
    public void logAfterGetAllTrains(){
        logger.info("Successfully retrieved all train details");
    }

    @AfterThrowing("execution(public * com.altimetrik.trainmicroservice.controller.TrainController.getAllTrains())")
    public void logExceptionForGetAllTrains(){
        logger.info("There seems to be a problem in fetching train details");
    }

    @Before("execution(public * com.altimetrik.trainmicroservice.controller.TrainController.addTrain(..))")
    public void logBeforeAddTrain(){
        logger.info("Retrieving complete train details as a request for adding train");
    }

    @AfterReturning("execution(public * com.altimetrik.trainmicroservice.controller.TrainController.addTrain(..))")
    public void logAfterAddTrain(){
        logger.info("Successfully added train along with its details");
    }

    @AfterThrowing("execution(public * com.altimetrik.trainmicroservice.controller.TrainController.addTrain(..))")
    public void logExceptionForAddTrain(){
        logger.info("There seems to be a problem in adding train details");
    }


    @Before("execution(public * com.altimetrik.trainmicroservice.controller.TrainController.updateTrain(..))")
    public void logBeforeUpdateTrain(){
        logger.info("Retrieving complete train details as a request for updating the existing train");
    }

    @AfterReturning("execution(public * com.altimetrik.trainmicroservice.controller.TrainController.updateTrain(..))")
    public void logAfterUpdateTrain(){
        logger.info("Successfully updated train details");
    }

    @AfterThrowing("execution(public * com.altimetrik.trainmicroservice.controller.TrainController.updateTrain(..))")
    public void logExceptionForUpdateTrain(){
        logger.info("There seems to be a problem in updating train details");
    }


    @Before("execution(public * com.altimetrik.trainmicroservice.controller.TrainController.getTrainByNumber(..))")
    public void logBeforeGetTrainByNumber(){
        logger.info("Retrieving train Number to see train details");
    }

    @AfterReturning("execution(public * com.altimetrik.trainmicroservice.controller.TrainController.getTrainByNumber(..))")
    public void logAfterGetTrainByNumber(){
        logger.info("Successfully retrieved train details using train Number");
    }

    @AfterThrowing("execution(public * com.altimetrik.trainmicroservice.controller.TrainController.getTrainByNumber(..))")
    public void logExceptionForGetTrainByNumber(){
        logger.info("There seems to be a problem in retrieving train details using train Number");
    }

    @Before("execution(public * com.altimetrik.trainmicroservice.controller.TrainController.deleteTrainByNumber(..))")
    public void logBeforeDeleteTrainByNumber(){
        logger.info("Retrieving train Number to delete train");
    }

    @AfterReturning("execution(public * com.altimetrik.trainmicroservice.controller.TrainController.deleteTrainByNumber(..))")
    public void logAfterDeleteTrainByNumber(){
        logger.info("Successfully deleted");
    }

    @AfterThrowing("execution(public * com.altimetrik.trainmicroservice.controller.TrainController.deleteTrainByNumber(..))")
    public void logExceptionForDeleteTrainByNumber(){
        logger.info("There seems to be a problem in deleting train");
    }
}
