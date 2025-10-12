package br.com.gestaoproducao.api.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.gestaoproducao.api.model.EnderecoFornecedor;
import br.com.gestaoproducao.api.model.Fornecedor;

public interface EnderecoFornecedorRepository extends JpaRepository<EnderecoFornecedor, Integer> {
        
    Optional<EnderecoFornecedor> findByFornecedor(Fornecedor fornecedor);
}
