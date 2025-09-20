package br.com.consultasapibr.apiarquiteturasoftware.service;

import java.util.List;

import org.springframework.stereotype.Service;

import br.com.consultasapibr.apiarquiteturasoftware.dto.ProdutoDTO;
import br.com.consultasapibr.apiarquiteturasoftware.model.Categoria;
import br.com.consultasapibr.apiarquiteturasoftware.model.Produto;
import br.com.consultasapibr.apiarquiteturasoftware.repository.CategoriaRepository;
import br.com.consultasapibr.apiarquiteturasoftware.repository.OrdemProducaoRepository;
import br.com.consultasapibr.apiarquiteturasoftware.repository.ProdutoRepository;

@Service
public class ProdutoService {

    private final ProdutoRepository produtoRepository;
    private final CategoriaRepository categoriaRepository;

    public ProdutoService(ProdutoRepository produtoRepository, CategoriaRepository categoriaRepository, OrdemProducaoRepository ordemProducaoRepository) {
        this.produtoRepository = produtoRepository;
        this.categoriaRepository = categoriaRepository;
    }

    public Produto cadastrarProduto(ProdutoDTO dto) {

        Produto produto = new Produto();

        produto.setNome(dto.getNome());
        produto.setPreco(dto.getPreco());
        produto.setDescricao(dto.getDescricao());
        produto.setEstoqueMinimo(dto.getEstoqueMinimo());
        produto.setEstoqueMaximo(dto.getEstoqueMaximo());
        produto.setEstoqueAtual(dto.getEstoqueAtual());

        if (produto.getEstoqueAtual() > produto.getEstoqueMaximo()) {
            throw new IllegalArgumentException("Estoque atual não pode ser maior que o estoque máximo");
        }

        if (produto.getEstoqueAtual() < produto.getEstoqueMinimo()) {
            throw new IllegalArgumentException("Estoque atual não pode ser menor que o estoque mínimo");
        }

        if (produto.getEstoqueMinimo() > produto.getEstoqueMaximo()) {
            throw new IllegalArgumentException("Estoque mínimo não pode ser maior que o estoque máximo");
        }

        Categoria categoria = categoriaRepository.findById(dto.getCategoriaId())
                .orElseThrow(() -> new IllegalArgumentException("Categoria não encontrada"));
        produto.setCategoria(categoria);

        return produtoRepository.save(produto);
    }

    public List<ProdutoDTO> listarTodos() {
        List<ProdutoDTO> produtos = produtoRepository.findAll()
                .stream()
                .map(produto -> {
                    ProdutoDTO dto = new ProdutoDTO();
                    dto.setNome(produto.getNome());
                    dto.setPreco(produto.getPreco());
                    dto.setEstoqueMaximo(produto.getEstoqueMaximo());
                    dto.setEstoqueMinimo(produto.getEstoqueMinimo());
                    dto.setEstoqueAtual(produto.getEstoqueAtual());
                    dto.setCategoriaId(produto.getCategoria().getId());
                    return dto;
                }).toList();

        return produtos;
    }

    public void atualizarEstoque(Integer id, Integer quantidade) {
        var produto = this.produtoRepository.findById(id).orElseThrow(() -> 
        new IllegalArgumentException("Produto não encontrado"));

        var estoque = produto.getEstoqueAtual() + quantidade;

        if (estoque > produto.getEstoqueMaximo()) {
            return;
        }

        produto.setEstoqueAtual(produto.getEstoqueAtual() + quantidade);
        this.produtoRepository.save(produto);
    }
}
