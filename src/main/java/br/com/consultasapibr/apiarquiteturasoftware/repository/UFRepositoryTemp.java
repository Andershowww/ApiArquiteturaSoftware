package br.com.consultasapibr.apiarquiteturasoftware.repository;

import org.springframework.stereotype.Component;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.consultasapibr.apiarquiteturasoftware.model.UFTemp;

import java.util.Optional;

@Component
@Repository
public interface UFRepositoryTemp extends JpaRepository<UFTemp, Integer> {
    
    Optional<UFTemp> findByUfSigla(String uf);   
}