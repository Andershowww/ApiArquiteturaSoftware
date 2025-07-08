package br.com.consultasapibr.apiarquiteturasoftware.uf.repository;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import br.com.consultasapibr.apiarquiteturasoftware.repository.UFJpaRepository;
import br.com.consultasapibr.apiarquiteturasoftware.uf.model.UF;

import java.util.Optional;

@Component
@Profile("prod")
public class UFRepository implements IUFRepository {

    private final UFJpaRepository ufRepository;

    public UFRepository(UFJpaRepository ufRepository) {
        this.ufRepository = ufRepository;
    }

    @Override
    public Optional<UF> findByufSigla(String ufSigla) {
        return ufRepository.findByufSigla(ufSigla);
    }
}
