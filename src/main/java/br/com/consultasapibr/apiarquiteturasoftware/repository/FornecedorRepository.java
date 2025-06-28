package br.com.consultasapibr.apiarquiteturasoftware.repository;

import br.com.consultasapibr.apiarquiteturasoftware.model.Fornecedor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FornecedorRepository extends JpaRepository<Fornecedor, Integer> {
    boolean existsByCnpj(String cnpj);
}
