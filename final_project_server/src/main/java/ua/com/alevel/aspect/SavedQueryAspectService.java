package ua.com.alevel.aspect;

import org.aspectj.lang.ProceedingJoinPoint;

public interface SavedQueryAspectService {

    void pointcut();
    Object doAspect(ProceedingJoinPoint point) throws Throwable;
}
