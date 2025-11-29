package br.com.gestaoproducao.api.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import br.com.gestaoproducao.api.dto.OrdemProducaoDTO;
import br.com.gestaoproducao.api.model.OrdemProducao;
import br.com.gestaoproducao.api.service.OrdemProducaoService;

@RestController
@RequestMapping("/OrdemProducao")
public class OrdemProducaoController {

    private final OrdemProducaoService service;

    public OrdemProducaoController(OrdemProducaoService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<?> gerarOrdemProducao(@RequestBody OrdemProducaoDTO ordemProducaoDTO) {
        try {
            OrdemProducao ordemProducao = service.gerarOrdemProducao(ordemProducaoDTO);
            return ResponseEntity.status(201).body(ordemProducao);
        } catch (Exception e) {
            Map<String, String> erro = new HashMap<>();
            erro.put("mensagem", "Erro ao gerar ordem de produção");
            erro.put("detalhe", e.getMessage());
            return ResponseEntity.status(400).body(erro);
        }
    }

    // 📌 Novo endpoint: Alterar ordem de produção
    @PutMapping("/{id}")
    public ResponseEntity<?> atualizarOrdemProducao(
            @PathVariable int id,
            @RequestBody OrdemProducaoDTO ordemProducaoDTO) {
        try {
            OrdemProducao ordemAtualizada = service.atualizarOrdemProducao(id, ordemProducaoDTO);
            return ResponseEntity.ok(ordemAtualizada);
        } catch (Exception e) {
            Map<String, String> erro = new HashMap<>();
            erro.put("mensagem", "Erro ao atualizar ordem de produção");
            erro.put("detalhe", e.getMessage());
            return ResponseEntity.status(400).body(erro);
        }
    }
}
