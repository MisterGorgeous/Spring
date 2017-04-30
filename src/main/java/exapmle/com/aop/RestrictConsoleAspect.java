package exapmle.com.aop;

import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@Aspect
public class RestrictConsoleAspect {
   /* @Pointcut("execution(*.*ConsoleEventLogger.logEvent(..))")
    public void allLogEventMethods() {
    }
   @Around("execution()")

*/
}
