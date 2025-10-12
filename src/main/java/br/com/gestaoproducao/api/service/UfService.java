package br.com.gestaoproducao.api.service;


import java.util.List;

import org.springframework.stereotype.Service;

import br.com.gestaoproducao.api.exception.ResourceNotFoundException;
import br.com.gestaoproducao.api.model.Uf;
import br.com.gestaoproducao.api.repository.UfRepository;
@Service
public class UfService {
    private final UfRepository ufRepository ;

    public UfService(UfRepository ufRepository) {
        this.ufRepository = ufRepository;
    }

    public Uf salvar(Uf uf) {
        return ufRepository.save(uf);
    }

    public List<Uf> listarTodos() {
        return ufRepository.findAll();
    }

    public Uf buscarPorSigla(String sigla) {
        return ufRepository.findByUfSigla(sigla)
                .orElseThrow(() -> new ResourceNotFoundException("UF não encontrada"));
    }
}
