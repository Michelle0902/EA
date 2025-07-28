package bank.service.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.util.StopWatch;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class StopWatchAdvice {

    @Around("execution(* bank.service.*.*(..))")
    public Object methodTimer(ProceedingJoinPoint joinPoint) throws Throwable {
        StopWatch sw = new StopWatch();
        sw.start();
        Object result = joinPoint.proceed();
        sw.stop();
        System.out.println("[Service Timing] Method: " + joinPoint.getSignature().getName()
                + " took " + sw.getTotalTimeMillis() + " ms");
        return result;
    }
}