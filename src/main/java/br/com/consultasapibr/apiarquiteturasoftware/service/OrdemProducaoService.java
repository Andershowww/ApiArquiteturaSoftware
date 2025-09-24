package br.com.consultasapibr.apiarquiteturasoftware.service;

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

    public OrdemProducaoService(ProdutoRepository produtoRepository, OrdemProducaoRepository ordemProducaoRepository, ProdutoService produtoService) {
        this.produtoRepository = produtoRepository;
        this.ordemProducaoRepository = ordemProducaoRepository;
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
}
