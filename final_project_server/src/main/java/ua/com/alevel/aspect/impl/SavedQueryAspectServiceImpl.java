package ua.com.alevel.aspect.impl;

import lombok.AllArgsConstructor;
import org.apache.commons.lang3.ArrayUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import ua.com.alevel.aspect.SavedQueryAspectService;
import ua.com.alevel.service.SavedQueryService;

@Aspect
@Component
@AllArgsConstructor
public class SavedQueryAspectServiceImpl implements SavedQueryAspectService {

    private final SavedQueryService savedQueryService;

    @Override
    @Pointcut("execution(* ua.com.alevel.service.impl.ProductSearchServiceImpl.searchProducts(..))")
    public void pointcut() { }

    @Override
    @Around(value = "pointcut()")
    public Object doAspect(ProceedingJoinPoint point) throws Throwable {
        Object[] params = point.getArgs();
        if (ArrayUtils.isNotEmpty(params)) {
            String searchText = (String) params[0];
            savedQueryService.save(searchText);
        }
        return point.proceed();
//        return Collections.emptyList();
    }
}
