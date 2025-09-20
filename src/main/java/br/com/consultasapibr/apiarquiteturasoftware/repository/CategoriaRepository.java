package br.com.consultasapibr.apiarquiteturasoftware.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.consultasapibr.apiarquiteturasoftware.model.Categoria;

public interface CategoriaRepository extends JpaRepository<Categoria, Integer> {
    boolean existsById(int id);
}