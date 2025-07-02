package br.com.consultasapibr.apiarquiteturasoftware.controller;

import br.com.consultasapibr.apiarquiteturasoftware.dto.FornecedorConsultaApiDTO;
import br.com.consultasapibr.apiarquiteturasoftware.model.Fornecedor;
import br.com.consultasapibr.apiarquiteturasoftware.service.FornecedorService;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/fornecedores")
public class FornecedorController {

    private FornecedorService service;

    public FornecedorController(FornecedorService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<Fornecedor> cadastrar(@RequestBody FornecedorConsultaApiDTO fornecedorDTO) {
        Fornecedor fornecedor = service.cadastrarFornecedor(fornecedorDTO);
        return ResponseEntity.status(201).body(fornecedor);
    }

    @GetMapping("/consulta-cnpj")
    public ResponseEntity<FornecedorConsultaApiDTO> buscaCnpjApiBrasil(@RequestParam String cnpj) {
        FornecedorConsultaApiDTO fornecedor = service.buscaCnpj(cnpj);
        return ResponseEntity.ok(fornecedor);
    }

    @GetMapping("/lista-fornecedores")
    public ResponseEntity<List<FornecedorConsultaApiDTO>>buscaTodosFornecedores() {
        List<FornecedorConsultaApiDTO> dtos = service.listarTodos().stream()
        .map(FornecedorConsultaApiDTO::new)
        .toList();
    return ResponseEntity.ok(dtos);
    }
}
