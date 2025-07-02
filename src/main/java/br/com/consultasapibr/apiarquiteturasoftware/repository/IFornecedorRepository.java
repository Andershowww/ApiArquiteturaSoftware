package br.com.consultasapibr.apiarquiteturasoftware.repository;

import br.com.consultasapibr.apiarquiteturasoftware.model.Fornecedor;

import java.util.List;
import java.util.Optional;

public interface IFornecedorRepository {
    Fornecedor save(Fornecedor fornecedor);

    boolean existsByCnpj(String cnpj);

    List<Fornecedor> findAll();

    Optional<Fornecedor> findByCnpj(String cnpj);
}
 