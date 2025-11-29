package br.com.gestaoproducao.api.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.AfterReturning;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {
    @Before("execution(* br.com.gestaoproducao.api.service.*.*(..))")
    public void antesDoMetodo(JoinPoint joinPoint) {
        System.out.println("Chamando método: " + joinPoint.getSignature().getName());
    }

    @AfterReturning(pointcut = "execution(* br.com.gestaoproducao.api.service.*.*(..))", returning = "result")
    public void aposRetornar(JoinPoint joinPoint, Object result) {
        System.out.println("Método concluído: " + joinPoint.getSignature().getName());
    }
}
