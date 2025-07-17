package br.com.consultasapibr.apiarquiteturasoftware.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import br.com.consultasapibr.apiarquiteturasoftware.model.Fornecedor;

@Component
@Repository
public interface FornecedorRepository extends JpaRepository<Fornecedor, Integer> {
    boolean existsByCnpj(String cnpj);

    Optional<Fornecedor> findByCnpj(String cnpj);
}
 