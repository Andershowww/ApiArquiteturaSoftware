package br.com.consultasapibr.apiarquiteturasoftware.repository;

import br.com.consultasapibr.apiarquiteturasoftware.model.UF;

import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
@Profile("prod")
public interface UFJpaRepository extends JpaRepository<UF, Integer> {
    Optional<UF> findByufSigla(String ufSigla);
}
