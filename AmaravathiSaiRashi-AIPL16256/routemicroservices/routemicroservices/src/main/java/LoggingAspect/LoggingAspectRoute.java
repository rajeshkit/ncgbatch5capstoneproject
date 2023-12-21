package LoggingAspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspectRoute {
    private static final Logger logger = LoggerFactory.getLogger(LoggingAspectRoute.class);

    @Before("execution(* com.train.trainbooking.service.route.*.*(..))")
    public void logBeforeRouteServiceMethod(JoinPoint joinPoint) {
        logger.info("Calling Route service method: {}", joinPoint.getSignature().toShortString());
    }
}
