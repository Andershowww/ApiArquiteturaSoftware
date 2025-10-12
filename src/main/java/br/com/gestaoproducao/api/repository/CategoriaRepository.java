package br.com.gestaoproducao.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.gestaoproducao.api.model.Categoria;


public interface CategoriaRepository extends JpaRepository<Categoria, Integer> {
    boolean existsById(int id);
}