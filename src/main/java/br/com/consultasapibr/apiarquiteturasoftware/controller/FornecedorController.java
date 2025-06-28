package br.com.consultasapibr.apiarquiteturasoftware.controller;

import br.com.consultasapibr.apiarquiteturasoftware.dto.FornecedorDTO;
import br.com.consultasapibr.apiarquiteturasoftware.model.Fornecedor;
import br.com.consultasapibr.apiarquiteturasoftware.service.FornecedorService;
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
    public ResponseEntity<Fornecedor> cadastrar(@RequestBody FornecedorDTO dto) {
        // Aqui, deixa a exceção propagar. O GlobalExceptionHandler tratará a resposta.
        Fornecedor fornecedor = service.cadastrarFornecedor(dto.getCnpj());
        return ResponseEntity.status(201).body(fornecedor);
    }

    // @GetMapping
    // public ResponseEntity<List<Fornecedor>> listar() {
    //     List<Fornecedor> lista = (List<Fornecedor>) service.listarTodos();
    //     return ResponseEntity.ok(lista);
    // }
}
