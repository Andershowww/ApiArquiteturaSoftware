package br.com.gestaoproducao.api.Aspect;

import br.com.gestaoproducao.api.model.OrdemProducao;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Aspect
@Component
public class NotificacaoOrdemProducaoAspect {

    private static final Logger logger = LoggerFactory.getLogger(NotificacaoOrdemProducaoAspect.class);

    /**
     * Notificação após criar uma ordem de produção
     */
    @AfterReturning(
            pointcut = "execution(* br.com.gestaoproducao.api.service.OrdemProducaoService.gerarOrdemProducao(..))",
            returning = "ordem"
    )
    public void notificarCriacaoOrdem(OrdemProducao ordem) {
        if (ordem == null) {
            return;
        }

        String mensagemConsole = String.format(
                "[NOTIFICAÇÃO] Nova ordem/pedido criado. ID=%d, Produto=%s, Quantidade=%d, Previsão=%s",
                ordem.getId(),
                ordem.getProduto() != null ? ordem.getProduto().getNome() : "N/D",
                ordem.getQuantidade(),
                ordem.getDataPrevisao()
        );

        // Simula notificação no console
        System.out.println(mensagemConsole);

        // E também no log da aplicação
        logger.info(mensagemConsole);
    }

    /**
     * Notificação após atualizar uma ordem de produção
     * (quando você criar o método de atualização no service)
     */
    @AfterReturning(
            pointcut = "execution(* br.com.gestaoproducao.api.service.OrdemProducaoService.atualizar*(..))",
            returning = "ordem"
    )
    public void notificarAtualizacaoOrdem(OrdemProducao ordem) {
        if (ordem == null) {
            return;
        }

        String mensagemConsole = String.format(
                "[NOTIFICAÇÃO] Ordem/pedido atualizado. ID=%d, Produto=%s, Quantidade=%d, Previsão=%s",
                ordem.getId(),
                ordem.getProduto() != null ? ordem.getProduto().getNome() : "N/D",
                ordem.getQuantidade(),
                ordem.getDataPrevisao()
        );

        System.out.println(mensagemConsole);
        logger.info(mensagemConsole);
    }
}
