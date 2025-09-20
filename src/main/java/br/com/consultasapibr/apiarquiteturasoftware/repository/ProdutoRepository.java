package br.com.consultasapibr.apiarquiteturasoftware.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.consultasapibr.apiarquiteturasoftware.model.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, Integer> {
}