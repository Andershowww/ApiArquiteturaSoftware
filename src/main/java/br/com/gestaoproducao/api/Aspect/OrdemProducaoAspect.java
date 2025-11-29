package br.com.gestaoproducao.api.aspect;


import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class OrdemProducaoAspect {
    @Before("execution(* br.com.gestaoproducao.api.service.*.gerarOrdemProducao(..))")
    public void logAntes(JoinPoint joinPoint) {
        Object[] args = joinPoint.getArgs();
        System.out.println("Gerando ordem de produção com DTO: " + args[0]);
    }
}