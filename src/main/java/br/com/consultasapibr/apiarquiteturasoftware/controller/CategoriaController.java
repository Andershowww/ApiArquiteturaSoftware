package br.com.consultasapibr.apiarquiteturasoftware.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.consultasapibr.apiarquiteturasoftware.dto.CategoriaDTO;
import br.com.consultasapibr.apiarquiteturasoftware.service.CategoriaService;


@RestController
@RequestMapping("/categorias")
public class CategoriaController {

    private final CategoriaService service;

    // ✅ Construtor correto
    public CategoriaController(CategoriaService service) {
        this.service = service;
    }

    // ✅ O Principal pode ser recebido aqui se quiser saber quem está autenticado
    @GetMapping
    public ResponseEntity<List<CategoriaDTO>> listarCategorias() {
        // Exemplo: obter email do usuário autenticado (vindo do JWT)
    
        List<CategoriaDTO> categorias = service.listarCategorias();
        return ResponseEntity.ok(categorias);
    }
}
