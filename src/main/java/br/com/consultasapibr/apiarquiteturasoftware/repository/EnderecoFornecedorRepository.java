package br.com.consultasapibr.apiarquiteturasoftware.repository;

import br.com.consultasapibr.apiarquiteturasoftware.model.EnderecoFornecedor;
import br.com.consultasapibr.apiarquiteturasoftware.model.Fornecedor;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface EnderecoFornecedorRepository extends JpaRepository<EnderecoFornecedor, Integer> {
        
    Optional<EnderecoFornecedor> findByFornecedor(Fornecedor fornecedor);
}
