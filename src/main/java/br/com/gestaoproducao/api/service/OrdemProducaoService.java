package br.com.gestaoproducao.api.service;

import java.time.LocalDate;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import br.com.gestaoproducao.api.dto.OrdemProducaoDTO;
import br.com.gestaoproducao.api.model.OrdemProducao;
import br.com.gestaoproducao.api.model.Produto;
import br.com.gestaoproducao.api.repository.OrdemProducaoRepository;
import br.com.gestaoproducao.api.repository.ProdutoRepository;



@Service
public class OrdemProducaoService {

    private final ProdutoRepository produtoRepository;
    private final OrdemProducaoRepository ordemProducaoRepository;

    private final ProdutoService produtoService;

    public OrdemProducaoService(ProdutoRepository produtoRepository, OrdemProducaoRepository ordemProducaoRepository, ProdutoService produtoService) {
        this.produtoRepository = produtoRepository;
        this.ordemProducaoRepository = ordemProducaoRepository;
        this.produtoService = produtoService;
    }

    public OrdemProducao gerarOrdemProducao(OrdemProducaoDTO dto) {
        OrdemProducao ordemProducao = new OrdemProducao();

        Produto produto = produtoRepository.findById(dto.getProdutoId())
                .orElseThrow(() -> new IllegalArgumentException("Produto não encontrado"));

        ordemProducao.setProduto(produto);
        ordemProducao.setDataEmissao(dto.getDataEmissao());
        ordemProducao.setDataPrevisao(dto.getDataPrevisao());
        ordemProducao.setObservacao(dto.getObservacao());
        ordemProducao.setQuantidade(dto.getQuantidade());

        return ordemProducaoRepository.save(ordemProducao);

    }

    @Scheduled(cron = "0 20 21 * * ?", zone = "America/Sao_Paulo")
    public void atualizarQuantidadeOrdemProducao() {
        System.out.println("Atualizando os produtos conforme as ordens de produção");
        var ordens = ordemProducaoRepository.findAll();
        for (OrdemProducao ordem : ordens) {
            if (ordem.getDataPrevisao().isEqual(LocalDate.now())) {
                this.produtoService.atualizarProdutoQuantidade(ordem.getProduto().getId(), ordem.getQuantidade());
            }
        }
    }
}
