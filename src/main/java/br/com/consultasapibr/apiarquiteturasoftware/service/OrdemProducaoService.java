package br.com.consultasapibr.apiarquiteturasoftware.service;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

import br.com.consultasapibr.apiarquiteturasoftware.dto.OrdemProducaoDTO;
import br.com.consultasapibr.apiarquiteturasoftware.model.OrdemProducao;
import br.com.consultasapibr.apiarquiteturasoftware.model.Produto;
import br.com.consultasapibr.apiarquiteturasoftware.repository.OrdemProducaoRepository;
import br.com.consultasapibr.apiarquiteturasoftware.repository.ProdutoRepository;

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

    public void atualizarProducao() {
        var ordens = ordemProducaoRepository.findAll();
        LocalDateTime agora = LocalDateTime.now();

        for (var ordem : ordens) {
            LocalDateTime dataPrevisao = ordem.getDataPrevisao();

            if (dataPrevisao.isBefore(agora) == false) {
                Integer produtoId = ordem.getProduto().getId();
                Integer quantidade = ordem.getQuantidade();

                produtoService.atualizarEstoque(produtoId, quantidade);
            }
        }
    }
}
