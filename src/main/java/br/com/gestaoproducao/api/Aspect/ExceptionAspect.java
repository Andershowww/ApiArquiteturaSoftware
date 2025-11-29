package br.com.gestaoproducao.api.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.AfterThrowing;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class ExceptionAspect {

    @AfterThrowing(pointcut = "execution(* br.com.gestaoproducao.api.service.*.*(..))", throwing = "ex")
    public void tratarExcecoes(JoinPoint joinPoint, Throwable ex) {
        String metodo = joinPoint.getSignature().getName();

        System.err.println("Erro no método " + metodo);
        System.err.println("Tipo: " + ex.getClass().getSimpleName());
        System.err.println("Mensagem: " + ex.getMessage());
    }
}
