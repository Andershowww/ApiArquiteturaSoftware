package br.com.gestaoproducao.api.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.gestaoproducao.api.dto.CategoriaDTO;
import br.com.gestaoproducao.api.service.CategoriaService;


@RestController
@RequestMapping("/categorias")
public class CategoriaController {

    private final CategoriaService service;

    public CategoriaController(CategoriaService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<CategoriaDTO>> listarCategorias() {    
        List<CategoriaDTO> categorias = service.listarCategorias();
        return ResponseEntity.ok(categorias);
    }
}
