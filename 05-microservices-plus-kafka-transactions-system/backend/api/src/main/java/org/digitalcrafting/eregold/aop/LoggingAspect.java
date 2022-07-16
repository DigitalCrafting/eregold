package org.digitalcrafting.eregold.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@Aspect
@Component
public class LoggingAspect {

    @Autowired
    private HttpServletRequest request;

    @Pointcut("within(@org.springframework.web.bind.annotation.RestController *)")
    public void beanAnnotatedWithController() {

    }

    @Pointcut("within(@org.springframework.cloud.openfeign.FeignClient *)")
    public void beanAnnotatedWithClient() {

    }

    @Pointcut("within(@org.apache.ibatis.annotations.Mapper *)")
    public void beanAnnotatedWithMapper() {

    }

    @Pointcut("execution(* *(..))")
    public void everyMethod() {

    }

    @Before("(beanAnnotatedWithController() || beanAnnotatedWithClient()) && everyMethod()")
    public void logBeforeRestCall(JoinPoint joinPoint) {
        // TODO
    }

    @AfterReturning(value = "(beanAnnotatedWithController() || beanAnnotatedWithClient()) && everyMethod()", returning = "returnValue")
    public void logAfterRestCall(JoinPoint joinPoint, Object returnValue) {
        // TODO
    }

    @Before("beanAnnotatedWithMapper() && everyMethod()")
    public void logBeforeDBQuery(JoinPoint joinPoint) {
        // TODO
    }

    @AfterReturning(value = "beanAnnotatedWithMapper() && everyMethod()", returning = "returnValue")
    public void logAfterDBQuery(JoinPoint joinPoint, Object returnValue) {
        // TODO
    }
}
