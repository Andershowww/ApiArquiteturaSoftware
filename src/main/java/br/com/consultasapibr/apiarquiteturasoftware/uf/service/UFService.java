package br.com.consultasapibr.apiarquiteturasoftware.uf.service;
import br.com.consultasapibr.apiarquiteturasoftware.uf.model.UF;
import br.com.consultasapibr.apiarquiteturasoftware.uf.repository.UFRepository;
import br.com.consultasapibr.apiarquiteturasoftware.exception.ResourceNotFoundException;

import java.util.List;

import org.springframework.stereotype.Service;
@Service
public class UFService {
    private final UFRepository ufRepository;

    public UFService(UFRepository ufRepository) {
        this.ufRepository = ufRepository;
    }

    public UF salvar(UF uf) {
        return ufRepository.save(uf);
    }

    public List<UF> listarTodos() {
        return ufRepository.findAll();
    }

    public UF buscarPorSigla(String sigla) {
        return ufRepository.findByUfSigla(sigla)
                .orElseThrow(() -> new ResourceNotFoundException("UF não encontrada"));
    }
}
