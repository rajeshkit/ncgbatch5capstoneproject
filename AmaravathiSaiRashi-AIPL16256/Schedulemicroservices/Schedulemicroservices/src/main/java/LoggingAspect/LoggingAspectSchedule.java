package LoggingAspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspectSchedule {
    private static final Logger logger = LoggerFactory.getLogger(LoggingAspectSchedule.class);

    @Before("execution(* com.train.trainbooking.service.schedule.*.*(..))")
    public void logBeforeScheduleServiceMethod(JoinPoint joinPoint) {
        logger.info("Calling Schedule service method: {}", joinPoint.getSignature().toShortString());
    }
}
