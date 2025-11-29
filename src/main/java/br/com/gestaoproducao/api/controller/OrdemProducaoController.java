package br.com.gestaoproducao.api.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.gestaoproducao.api.config.RoleProtected;
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
    @RoleProtected({"ADMIN", "GERENTE","ANALISTA"})
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
}
