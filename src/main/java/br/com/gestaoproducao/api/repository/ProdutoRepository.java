package br.com.gestaoproducao.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.gestaoproducao.api.model.Produto;


public interface ProdutoRepository extends JpaRepository<Produto, Integer> {
}