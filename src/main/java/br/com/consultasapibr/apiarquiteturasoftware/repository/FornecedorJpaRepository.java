package br.com.consultasapibr.apiarquiteturasoftware.repository;

import br.com.consultasapibr.apiarquiteturasoftware.model.Fornecedor;
import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@Profile("prod")
public interface FornecedorJpaRepository extends JpaRepository<Fornecedor, Integer> {
    boolean existsByCnpj(String cnpj);
    Optional<Fornecedor> findByCnpj(String cnpj);
}
