package br.com.gestaoproducao.api.service;

import java.util.List;

import org.springframework.stereotype.Service;

import br.com.gestaoproducao.api.dto.CategoriaDTO;
import br.com.gestaoproducao.api.repository.CategoriaRepository;

@Service
public class CategoriaService {
    private final CategoriaRepository categoriaRepository;

    public CategoriaService(CategoriaRepository categoriaRepository) {
        this.categoriaRepository = categoriaRepository;
    }

    public List<CategoriaDTO> listarCategorias() {
        List<CategoriaDTO> categorias = categoriaRepository.findAll()
                .stream()
                .map(categoria -> {
                    CategoriaDTO dto = new CategoriaDTO();
                    dto.setId(categoria.getId());
                    dto.setNome(categoria.getNome());
                    return dto;
                }).toList();
        return categorias;
    }

}
