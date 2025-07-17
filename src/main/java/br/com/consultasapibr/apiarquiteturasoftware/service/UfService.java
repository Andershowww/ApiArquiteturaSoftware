package br.com.consultasapibr.apiarquiteturasoftware.service;
import br.com.consultasapibr.apiarquiteturasoftware.exception.ResourceNotFoundException;
import br.com.consultasapibr.apiarquiteturasoftware.model.Uf;
import br.com.consultasapibr.apiarquiteturasoftware.repository.UfRepository;

import java.util.List;

import org.springframework.stereotype.Service;
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
