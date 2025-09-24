package br.com.consultasapibr.apiarquiteturasoftware.service;

import java.util.List;

import org.springframework.stereotype.Service;

import br.com.consultasapibr.apiarquiteturasoftware.dto.ProdutoCadastroDTO;
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

    public Produto cadastrarProduto(ProdutoCadastroDTO dto) {

        Produto produto = new Produto();
        produto.setNome(dto.getNome());
        produto.setPreco(dto.getPreco());
        produto.setDescricao(dto.getDescricao());
        produto.setEstoqueMinimo(dto.getEstoqueMinimo());
        produto.setEstoqueMaximo(dto.getEstoqueMaximo());
        produto.setEstoqueAtual(dto.getEstoqueAtual());
        // Gambiarra para gerar o código antes de salvar
        produto.setCodigo();

        Categoria categoria = categoriaRepository.findById(dto.getCategoriaId())
                .orElseThrow(() -> new IllegalArgumentException("Categoria não encontrada"));
        produto.setCategoria(categoria);
        
        var salvo = produtoRepository.save(produto);
        produto.setCodigo();
        return produtoRepository.save(salvo);
    }

    public List<ProdutoDTO> listarTodos() {
        List<ProdutoDTO> produtos = produtoRepository.findAll()
                .stream()
                .map(produto -> {
                    ProdutoDTO dto = new ProdutoDTO();
                    dto.setId(produto.getId());
                    dto.setCodigo(produto.getCodigo());
                    dto.setNome(produto.getNome());
                    dto.setPreco(produto.getPreco());
                    dto.setDescricao(produto.getDescricao());
                    dto.setEstoqueMaximo(produto.getEstoqueMaximo());
                    dto.setEstoqueMinimo(produto.getEstoqueMinimo());
                    dto.setEstoqueAtual(produto.getEstoqueAtual());
                    dto.setCategoriaId(produto.getCategoria().getId());
                    dto.setCategoriaNome(produto.getCategoria().getNome());
                    return dto;
                }).toList();

        return produtos;
    }
}
