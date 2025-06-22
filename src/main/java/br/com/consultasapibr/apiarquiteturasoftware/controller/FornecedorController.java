package br.com.consultasapibr.apiarquiteturasoftware.controller;

import br.com.consultasapibr.apiarquiteturasoftware.dto.FornecedorDTO;
import br.com.consultasapibr.apiarquiteturasoftware.model.Fornecedor;
import br.com.consultasapibr.apiarquiteturasoftware.service.FornecedorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/fornecedores")
public class FornecedorController {

    @Autowired
    private FornecedorService service;

    @PostMapping
    public ResponseEntity<?> cadastrar(@RequestBody FornecedorDTO dto) {
        try {
            Fornecedor fornecedor = service.cadastrarFornecedor(dto.getCnpj());
            return ResponseEntity.status(201).body(fornecedor);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("{\"erro\":\"" + e.getMessage() + "\"}");
        }
    }

    @GetMapping
    public ResponseEntity<List<Fornecedor>> listar() {
        List<Fornecedor> lista = (List<Fornecedor>) service.listarTodos();
        return ResponseEntity.ok(lista);
    }
}
