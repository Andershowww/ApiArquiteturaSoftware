package br.com.consultasapibr.apiarquiteturasoftware.fornecedor.repository;

import java.util.List;
import java.util.Optional;

import br.com.consultasapibr.apiarquiteturasoftware.fornecedor.model.Fornecedor;

public interface IFornecedorRepository {
    Fornecedor save(Fornecedor fornecedor);

    boolean existsByCnpj(String cnpj);

    List<Fornecedor> findAll();

    Optional<Fornecedor> findByCnpj(String cnpj);
}
 