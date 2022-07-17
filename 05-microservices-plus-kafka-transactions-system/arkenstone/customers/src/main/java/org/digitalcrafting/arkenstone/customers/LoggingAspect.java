package org.digitalcrafting.arkenstone.customers;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.*;
import java.util.stream.Stream;

@Slf4j
@Aspect
@Component
public class LoggingAspect {

    private final Gson GSON = new Gson();

    @Autowired
    private Environment environment;

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
        String requestUri = getRequestMapping(joinPoint);
        String calledClassAndMethod = joinPoint.getSignature().getDeclaringTypeName() + "::" + joinPoint.getSignature().getName();
        Map<String, String> parametersMap = getRequestParams(joinPoint);
        String parameters = paramsMapToString(parametersMap);

        logRequest(isFeignClient(joinPoint), requestUri, calledClassAndMethod, parameters);
    }

    @AfterReturning(value = "(beanAnnotatedWithController() || beanAnnotatedWithClient()) && everyMethod()", returning = "returnValue")
    public void logAfterRestCall(JoinPoint joinPoint, Object returnValue) {
        String requestUri = getRequestMapping(joinPoint);
        String calledClassAndMethod = joinPoint.getSignature().getDeclaringTypeName() + "::" + joinPoint.getSignature().getName();
        Map<String, String> parametersMap = getRequestParams(joinPoint);
        String parameters = paramsMapToString(parametersMap);
        String returnValueAsString = GSON.toJson(returnValue);

        logResponse(isFeignClient(joinPoint), requestUri, calledClassAndMethod, parameters, returnValueAsString);
    }

    @Before("beanAnnotatedWithMapper() && everyMethod()")
    public void logBeforeDBQuery(JoinPoint joinPoint) {
        String calledClassAndMethod = joinPoint.getSignature().getDeclaringTypeName() + "::" + joinPoint.getSignature().getName();
        Map<String, String> parametersMap = getRequestParams(joinPoint);
        String parameters = paramsMapToString(parametersMap);

        logDBQuery(calledClassAndMethod, parameters);
    }

    @AfterReturning(value = "beanAnnotatedWithMapper() && everyMethod()", returning = "returnValue")
    public void logAfterDBQuery(JoinPoint joinPoint, Object returnValue) {
        String calledClassAndMethod = joinPoint.getSignature().getDeclaringTypeName() + "::" + joinPoint.getSignature().getName();
        Map<String, String> parametersMap = getRequestParams(joinPoint);
        String parameters = paramsMapToString(parametersMap);
        String returnValueAsString = GSON.toJson(returnValue);

        logDBQueryResult(calledClassAndMethod, parameters, returnValueAsString);
    }

    private void logRequest(boolean isFeignClient, String uri, String method, String parameters) {
        log.info(
                "\n{} {\n\t\"endpoint\": {}, \n\t\"method\": {}, \n\t\"parameters\": {}\n}\n",
                isFeignClient ? "ClientRequest" : "ControllerRequest",
                uri,
                method,
                parameters
        );
    }

    private void logResponse(boolean isFeignClient, String uri, String method, String parameters, String value) {
        log.info(
                "\n{} {\n\t\"endpoint\": {}, \n\t\"method\" {}, \n\t\"parameters\": {}, \n\t\"response\": {}\n}\n",
                isFeignClient ? "ClientResponse" : "ControllerResponse",
                uri,
                method,
                parameters,
                value
        );
    }

    private void logDBQuery(String method, String parameters) {
        log.info(
                "\nDBQuery {\n\t\"method\": {}, \n\t\"parameters\": {}\n}\n",
                method,
                parameters
        );
    }

    private void logDBQueryResult(String method, String parameters, String value) {
        log.info(
                "\nDBQueryResult {\n\t\"method\": {}, \n\t\"parameters\": {}, \n\t\"result\": {}\n}\n",
                method,
                parameters,
                value
        );
    }

    private String getRequestMapping(JoinPoint joinPoint) {
        String uri = getRequestURI(joinPoint);
        return getMethodMapping(joinPoint, uri);
    }

    private String getRequestURI(JoinPoint joinPoint) {
        if (isFeignClient(joinPoint)) {
            Class<?> aClass = joinPoint.getSignature().getDeclaringType();
            FeignClient annotation = aClass.getAnnotation(FeignClient.class);
            return environment.resolvePlaceholders(annotation.url());
        } else {
            final StringBuffer requestULRBuffer = request.getRequestURL();
            final String queryString = request.getQueryString();
            if (queryString != null) {
                requestULRBuffer.append("?").append(queryString);
            }
            return requestULRBuffer.toString();
        }
    }

    private String getMethodMapping(JoinPoint joinPoint, String classUri) {
        Optional<Annotation> annotationOptional = getMethodRequestAnnotation(joinPoint);

        if (annotationOptional.isPresent()) {
            Annotation annotation = annotationOptional.get();

            if (annotation instanceof GetMapping) {
                return "GET " + classUri;
            } else if (annotation instanceof PostMapping) {
                return "POST " + classUri;
            } else if (annotation instanceof PutMapping) {
                return "PUT " + classUri;
            } else if (annotation instanceof DeleteMapping) {
                return "DELETE " + classUri;
            } else if (annotation instanceof PatchMapping) {
                return "PATCH " + classUri;
            } else if (annotation instanceof RequestMapping) {
                final String method = Arrays.stream(((RequestMapping) annotation).method()).map(Objects::toString).findFirst().orElse("");
                return method + " " + classUri + reduceRequestMapping(((RequestMapping) annotation).value());
            }
        }

        return "";
    }

    private Map<String, String> getRequestParams(JoinPoint joinPoint) {
        Map<String, String> parametersMap = new HashMap<>();
        Object[] args = joinPoint.getArgs();
        String[] paramNames = ((MethodSignature) joinPoint.getSignature()).getParameterNames();

        if (args != null && args.length > 0) {
            for (int i = 0; i < paramNames.length; i++) {
                parametersMap.put(paramNames[i], GSON.toJson(args[i]));
            }
        }
        return parametersMap;
    }

    private String paramsMapToString(Map<String, String> parametersMap) {
        if (parametersMap == null || parametersMap.isEmpty()) {
            return "";
        }

        JsonObject paramsRoot = new JsonObject();

        for (Map.Entry<String, String> entry : parametersMap.entrySet()) {
            paramsRoot.add(entry.getKey(), new JsonPrimitive(entry.getValue()));
        }

        return paramsRoot.toString();
    }

    private Optional<Annotation> getMethodRequestAnnotation(JoinPoint joinPoint) {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        Annotation[] methodAnnotations = method.getDeclaredAnnotations();
        return Arrays.stream(methodAnnotations)
                .filter(a -> a.annotationType().getAnnotation(RequestMapping.class) != null || a instanceof RequestMapping)
                .findFirst();
    }

    private boolean isFeignClient(JoinPoint joinPoint) {
        Class<?> aClass = joinPoint.getSignature().getDeclaringType();
        return aClass.isAnnotationPresent(FeignClient.class);
    }

    private String reduceRequestMapping(String[] mapping) {
        return Stream.of(mapping).reduce((s, s2) -> s + "/" + s2).orElse("");
    }
}
