package br.com.consultasapibr.apiarquiteturasoftware.repository;

import br.com.consultasapibr.apiarquiteturasoftware.model.UF;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UFRepository extends JpaRepository<UF, Integer> {
    Optional<UF> findByUf(String UF);

}
