package exapmle.com.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Component;

import java.util.Map;

//import static jdk.nashorn.internal.codegen.Compiler.LOG;

@Component
@Aspect
public class LoggingAspect {
    private Map<Class<?>, Integer> counter;

    public LoggingAspect(Map<Class<?>, Integer> counter) {
        this.counter = counter;
    }


    @AfterReturning("allLogEventMethods()")
    public void calculateStats(JoinPoint joinPoint) {
        Class<?> type = joinPoint.getTarget().getClass();
        if (!counter.containsKey(type)) {
            counter.put(type, 0);
        }
        counter.put(type, counter.get(type) + 1);
    }


    @Pointcut("execution(* *.logEvent(..))")
    public void allLogEventMethods() {
    }

    /*@Pointcut("allLogEventMethods() && within(*.*File*Logger)")
    private void logEventInsideFileLoggers(){}*/


    @Before("allLogEventMethods()")
    public void logBefore(JoinPoint joinPoint) {
        System.out.println("Before: " + joinPoint.getTarget().getClass().getSimpleName() + " " +
                joinPoint.getSignature().getName());
    }

    @AfterReturning(pointcut = "allLogEventMethods()", returning = "retVal")
    public void logAfter(Object retVal) {
        System.out.println("Returned value: " + retVal);
    }

    @AfterThrowing(pointcut = "allLogEventMethods()", throwing = "ex")
    public void logAfterThrow(Throwable ex) {
        System.out.println("Throw: " + ex);
    }

    public String printStats(){
        StringBuilder str = new StringBuilder();
        for(Class<?> type: counter.keySet()){
            str.append(type.getCanonicalName() + " - " + counter.get(type) + "\n");
        }
        return str.toString();
    }
}
