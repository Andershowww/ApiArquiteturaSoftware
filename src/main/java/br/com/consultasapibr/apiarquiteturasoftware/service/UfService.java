package br.com.consultasapibr.apiarquiteturasoftware.service;
import br.com.consultasapibr.apiarquiteturasoftware.exception.ResourceNotFoundException;
import br.com.consultasapibr.apiarquiteturasoftware.model.UFTemp;
import br.com.consultasapibr.apiarquiteturasoftware.repository.UFRepositoryTemp;

import java.util.List;

import org.springframework.stereotype.Service;
@Service
public class UfService {
    private final UFRepositoryTemp ufRepository ;

    public UfService(UFRepositoryTemp ufRepository) {
        this.ufRepository = ufRepository;
    }

    public UFTemp salvar(UFTemp uf) {
        return ufRepository.save(uf);
    }

    public List<UFTemp> listarTodos() {
        return ufRepository.findAll();
    }

    public UFTemp buscarPorSigla(String sigla) {
        return ufRepository.findByUfSigla(sigla)
                .orElseThrow(() -> new ResourceNotFoundException("UF não encontrada"));
    }
}
