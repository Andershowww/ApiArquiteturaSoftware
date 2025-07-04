package br.com.consultasapibr.apiarquiteturasoftware.repository;

import br.com.consultasapibr.apiarquiteturasoftware.model.Fornecedor;
import br.com.consultasapibr.apiarquiteturasoftware.model.EnderecoFornecedor;

import java.util.Optional;

public interface IEnderecoFornecedorRepository {
    EnderecoFornecedor save(EnderecoFornecedor endereco);
    
    Optional<EnderecoFornecedor> findByFornecedor(Fornecedor fornecedor);
}
