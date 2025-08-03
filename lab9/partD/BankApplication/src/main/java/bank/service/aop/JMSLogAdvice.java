package bank.service.aop;

import bank.logging.ILogger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class JMSLogAdvice {
    private ILogger logger;

    public JMSLogAdvice(ILogger logger) {
        this.logger = logger;
    }

    @After("execution(* bank.jms.JMSSender.sendJMSMessage(..))")
    public void logJMSMessage(JoinPoint joinPoint) {
        Object[] args = joinPoint.getArgs();
        if (args.length > 0) {
            logger.log("[JMS] Message sent: " + args[0]);
        }
    }
}