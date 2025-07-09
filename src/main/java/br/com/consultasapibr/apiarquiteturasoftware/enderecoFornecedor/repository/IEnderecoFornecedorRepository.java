package br.com.consultasapibr.apiarquiteturasoftware.enderecoFornecedor.repository;

import br.com.consultasapibr.apiarquiteturasoftware.enderecoFornecedor.model.EnderecoFornecedor;
import br.com.consultasapibr.apiarquiteturasoftware.fornecedor.model.Fornecedor;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface IEnderecoFornecedorRepository extends JpaRepository<EnderecoFornecedor, Integer> {
        
    Optional<EnderecoFornecedor> findByFornecedor(Fornecedor fornecedor);
}
