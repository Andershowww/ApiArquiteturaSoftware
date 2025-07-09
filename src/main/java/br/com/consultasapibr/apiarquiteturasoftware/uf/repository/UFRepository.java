package br.com.consultasapibr.apiarquiteturasoftware.uf.repository;

import org.springframework.stereotype.Component;
import br.com.consultasapibr.apiarquiteturasoftware.uf.model.UF;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Component
@Repository
public interface UFRepository extends JpaRepository<UF, Integer> {
    
    Optional<UF> findByUfSigla(String uf);   
}