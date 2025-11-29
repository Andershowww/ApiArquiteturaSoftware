package br.com.gestaoproducao.api.aspect;
import br.com.gestaoproducao.api.config.RoleProtected;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class RoleApect {

    @Before("@annotation(roleProtected)")
    public void verificarPermissao(JoinPoint joinPoint, RoleProtected roleProtected) {

        // Perfis necessários para acessar o método
        String[] perfisPermitidos = roleProtected.value();

        String perfilUsuario = obterPerfilDoUsuarioSimulado();

        boolean autorizado = false;
        for (String perfil : perfisPermitidos) {
            if (perfil.equalsIgnoreCase(perfilUsuario)) {
                autorizado = true;
                break;
            }
        }

        if (!autorizado) {
            throw new RuntimeException("Acesso negado para o perfil: " + perfilUsuario);
        }

        System.out.println("🔐 Acesso concedido ao método: " + joinPoint.getSignature().getName());
    }

    private String obterPerfilDoUsuarioSimulado() {
        // Aqui você simula o perfil
        // Pode trocar manualmente para testar
        return "GERENTE";
    }
}




