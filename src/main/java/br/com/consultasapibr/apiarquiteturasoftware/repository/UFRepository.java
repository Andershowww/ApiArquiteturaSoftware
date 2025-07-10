package br.com.consultasapibr.apiarquiteturasoftware.repository;

import org.springframework.stereotype.Component;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.consultasapibr.apiarquiteturasoftware.model.UF;

import java.util.Optional;

@Component
@Repository
public interface UFRepository extends JpaRepository<UF, Integer> {
    
    Optional<UF> findByUfSigla(String uf);   
}