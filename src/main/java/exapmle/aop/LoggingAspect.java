package exapmle.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

import static jdk.nashorn.internal.codegen.Compiler.LOG;

@Aspect
public class LoggingAspect{
    @Pointcut("execution(* *.logEvent(..))")
    private void allLogEventMethods(){}

   /* @Pointcut("allLogEventMethods() && within(*.*File*Logger)")
    private void logEventInsideFileLoggers(){}
    */

    @Before("allLogEventMethods()")
    public void logBefore(JoinPoint joinPoint){
        System.out.println("Before: " + joinPoint.getTarget().getClass().getSimpleName() + " " +
                joinPoint.getSignature().getName());
    }

    @AfterReturning(pointcut = "allLogEventMethods()", returning = "retVal")
    public void logAfter(Object retVal){
        System.out.println("Returned value: " + retVal);
    }

    @AfterThrowing(pointcut = "allLogEventMethods()", throwing = "ex")
    public void logAfterThrow(Throwable ex){
        System.out.println("Throw: " + ex);
    }
}
