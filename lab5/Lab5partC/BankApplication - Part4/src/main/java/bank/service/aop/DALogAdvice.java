package bank.service.aop;

import bank.logging.ILogger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class DALogAdvice {
    private ILogger logger;

    public DALogAdvice(ILogger logger) {
        this.logger = logger;
    }

    @Before("execution(* bank.dao.*.*(..))")
    public void logDAOCall(JoinPoint joinPoint) {
        logger.log("[DAO] Call to: " + joinPoint.getSignature().toShortString());
    }
}