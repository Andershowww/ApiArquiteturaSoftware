package br.com.consultasapibr.apiarquiteturasoftware.enderecofornecedor.repository;

import br.com.consultasapibr.apiarquiteturasoftware.enderecofornecedor.model.EnderecoFornecedor;
import br.com.consultasapibr.apiarquiteturasoftware.fornecedor.model.Fornecedor;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface EnderecoFornecedorRepository extends JpaRepository<EnderecoFornecedor, Integer> {
        
    Optional<EnderecoFornecedor> findByFornecedor(Fornecedor fornecedor);
}
