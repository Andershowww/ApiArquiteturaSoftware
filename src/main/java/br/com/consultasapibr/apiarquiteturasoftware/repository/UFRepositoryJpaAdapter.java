package br.com.consultasapibr.apiarquiteturasoftware.repository;

import br.com.consultasapibr.apiarquiteturasoftware.model.UF;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@Profile("prod")
public class UFRepositoryJpaAdapter implements IUFRepository {

    private final UFJpaRepository ufRepository;

    public UFRepositoryJpaAdapter(UFJpaRepository ufRepository) {
        this.ufRepository = ufRepository;
    }

    @Override
    public Optional<UF> findByufSigla(String ufSigla) {
        return ufRepository.findByufSigla(ufSigla);
    }
}
