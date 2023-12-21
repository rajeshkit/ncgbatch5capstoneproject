package com.altimetrik.schedulemicroservice.loggingaspect;

import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {

    private static final Logger logger = LoggerFactory.getLogger(LoggingAspect.class);

    @Before("execution(public * com.altimetrik.schedulemicroservice.controller.ScheduleController.getAllSchedule())")
    public void logBeforeGetAllSchedule(){
        logger.info("Retrieving complete schedule details");
    }

    @AfterReturning("execution(public * com.altimetrik.schedulemicroservice.controller.ScheduleController.getAllSchedule())")
    public void logAfterGetAllSchedule(){
        logger.info("Successfully retrieved all schedule details");
    }

    @AfterThrowing("execution(public * com.altimetrik.schedulemicroservice.controller.ScheduleController.getAllSchedule())")
    public void logExceptionForGetAllSchedule(){
        logger.info("There seems to be a problem in fetching schedule details");
    }

    @Before("execution(public * com.altimetrik.schedulemicroservice.controller.ScheduleController.createNewSchedule(..))")
    public void logBeforeCreateNewSchedule(){
        logger.info("Creating a new schedule");
    }

    @AfterReturning("execution(public * com.altimetrik.schedulemicroservice.controller.ScheduleController.createNewSchedule(..))")
    public void logAfterCreateNewSchedule(){
        logger.info("Successfully created a new schedule");
    }

    @AfterThrowing("execution(public * com.altimetrik.schedulemicroservice.controller.ScheduleController.createNewSchedule(..))")
    public void logExceptionForCreateNewSchedule(){
        logger.info("There seems to be a problem in creating a new schedule");
    }

    @Before("execution(public * com.altimetrik.schedulemicroservice.controller.ScheduleController.updateSchedule(..))")
    public void logBeforeUpdateSchedule(){
        logger.info("Updating existing schedule");
    }

    @AfterReturning("execution(public * com.altimetrik.schedulemicroservice.controller.ScheduleController.updateSchedule(..))")
    public void logAfterUpdateSchedule(){
        logger.info("Successfully updated schedule details");
    }

    @AfterThrowing("execution(public * com.altimetrik.schedulemicroservice.controller.ScheduleController.updateSchedule(..))")
    public void logExceptionForUpdateSchedule(){
        logger.info("There seems to be a problem in updating schedule details");
    }

    @Before("execution(public * com.altimetrik.schedulemicroservice.controller.ScheduleController.getScheduleById(..))")
    public void logBeforeGetScheduleById(){
        logger.info("Retrieving schedule details by ID");
    }

    @AfterReturning("execution(public * com.altimetrik.schedulemicroservice.controller.ScheduleController.getScheduleById(..))")
    public void logAfterGetScheduleById(){
        logger.info("Successfully retrieved schedule details by ID");
    }

    @AfterThrowing("execution(public * com.altimetrik.schedulemicroservice.controller.ScheduleController.getScheduleById(..))")
    public void logExceptionForGetScheduleById(){
        logger.info("There seems to be a problem in retrieving schedule details by ID");
    }

    @Before("execution(public * com.altimetrik.schedulemicroservice.controller.ScheduleController.deleteScheduleById(..))")
    public void logBeforeDeleteScheduleById(){
        logger.info("Deleting schedule by ID");
    }

    @AfterReturning("execution(public * com.altimetrik.schedulemicroservice.controller.ScheduleController.deleteScheduleById(..))")
    public void logAfterDeleteScheduleById(){
        logger.info("Successfully deleted schedule by ID");
    }

    @AfterThrowing("execution(public * com.altimetrik.schedulemicroservice.controller.ScheduleController.deleteScheduleById(..))")
    public void logExceptionForDeleteScheduleById(){
        logger.info("There seems to be a problem in deleting schedule by ID");
    }
}
