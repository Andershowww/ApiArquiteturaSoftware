package br.com.consultasapibr.apiarquiteturasoftware.endereco_fornecedor.repository;

import br.com.consultasapibr.apiarquiteturasoftware.endereco_fornecedor.model.EnderecoFornecedor;
import br.com.consultasapibr.apiarquiteturasoftware.fornecedor.model.Fornecedor;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface IEnderecoFornecedorRepository extends JpaRepository<EnderecoFornecedor, Integer> {
        
    Optional<EnderecoFornecedor> findByFornecedor(Fornecedor fornecedor);
}
