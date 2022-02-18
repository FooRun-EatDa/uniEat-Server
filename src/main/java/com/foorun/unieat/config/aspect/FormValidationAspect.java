package com.foorun.unieat.config.aspect;

import com.foorun.unieat.domain.Validatable;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Aspect
@Component
public class FormValidationAspect {
    /**
     * Service 클래스에서 메소드의 파라미터 타입이 {@link Validatable} 타입일 경우 validate 메소드를
     * 호출하는 Advice 메소드
     */
    @Around("@within(org.springframework.stereotype.Service) && @annotation(com.foorun.unieat.annotation.Validation)")
    public Object proceed(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        Arrays.stream(proceedingJoinPoint.getArgs())
                .filter(arg -> arg instanceof Validatable)
                .map(arg -> (Validatable) arg)
                .forEach(Validatable::validate);
        return proceedingJoinPoint.proceed();
    }
}
