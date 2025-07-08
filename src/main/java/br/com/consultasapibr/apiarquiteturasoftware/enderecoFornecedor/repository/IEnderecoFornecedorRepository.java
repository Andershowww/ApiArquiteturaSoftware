package br.com.consultasapibr.apiarquiteturasoftware.enderecoFornecedor.repository;

import br.com.consultasapibr.apiarquiteturasoftware.enderecoFornecedor.model.EnderecoFornecedor;
import br.com.consultasapibr.apiarquiteturasoftware.fornecedor.model.Fornecedor;

import java.util.Optional;

public interface IEnderecoFornecedorRepository {
    EnderecoFornecedor save(EnderecoFornecedor endereco);
    
    Optional<EnderecoFornecedor> findByFornecedor(Fornecedor fornecedor);
}
