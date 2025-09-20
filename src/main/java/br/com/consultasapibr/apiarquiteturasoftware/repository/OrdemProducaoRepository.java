package br.com.consultasapibr.apiarquiteturasoftware.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.consultasapibr.apiarquiteturasoftware.model.OrdemProducao;

public interface OrdemProducaoRepository extends JpaRepository<OrdemProducao, Integer> {
    boolean existsById(int id);
}