package br.com.consultasapibr.apiarquiteturasoftware.repository;

import br.com.consultasapibr.apiarquiteturasoftware.model.EnderecoFornecedor;
import br.com.consultasapibr.apiarquiteturasoftware.model.Fornecedor;

import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@Profile("prod")
public interface EnderecoFornecedorRepositoryJpa extends JpaRepository<EnderecoFornecedor, Integer> {
    Optional<EnderecoFornecedor> findByFornecedor(Fornecedor fornecedor);
}
