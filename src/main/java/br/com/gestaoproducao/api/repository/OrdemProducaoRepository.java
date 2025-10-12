package br.com.gestaoproducao.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.gestaoproducao.api.model.OrdemProducao;


public interface OrdemProducaoRepository extends JpaRepository<OrdemProducao, Integer> {
    boolean existsById(int id);
}