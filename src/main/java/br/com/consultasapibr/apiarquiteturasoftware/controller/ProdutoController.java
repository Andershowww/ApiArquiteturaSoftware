package br.com.consultasapibr.apiarquiteturasoftware.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.consultasapibr.apiarquiteturasoftware.dto.ProdutoCadastroDTO;
import br.com.consultasapibr.apiarquiteturasoftware.dto.ProdutoDTO;
import br.com.consultasapibr.apiarquiteturasoftware.model.Produto;
import br.com.consultasapibr.apiarquiteturasoftware.service.ProdutoService;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {

    private final ProdutoService service;

    public ProdutoController(ProdutoService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<?> cadastrar(@RequestBody ProdutoCadastroDTO produtoCadastroDTO) {
        try {
            Produto produto = service.cadastrarProduto(produtoCadastroDTO);
            return ResponseEntity.status(201).body(produto);
        } catch (Exception e) {
            Map<String, String> erro = new HashMap<>();
            erro.put("mensagem", "Erro ao criar um produto");
            erro.put("detalhe", e.getMessage());
            return ResponseEntity.status(400).body(erro);
        }
    }

    @GetMapping
    public ResponseEntity<List<ProdutoDTO>> listarTodos() {
        List<ProdutoDTO> produtos = service.listarTodos();
        return ResponseEntity.ok(produtos);
    }
}
