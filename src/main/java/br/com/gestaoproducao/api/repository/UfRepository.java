package br.com.gestaoproducao.api.repository;

import org.springframework.stereotype.Component;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.gestaoproducao.api.model.Uf;

import java.util.Optional;

@Component
@Repository
public interface UfRepository extends JpaRepository<Uf, Integer> {
    
    Optional<Uf> findByUfSigla(String uf);   
}