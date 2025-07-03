package br.com.consultasapibr.apiarquiteturasoftware.repository;


import br.com.consultasapibr.apiarquiteturasoftware.model.Fornecedor;
import br.com.consultasapibr.apiarquiteturasoftware.model.EnderecoFornecedor;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@Profile("prod")
public class EnderecoFornecedorRepositoryJpaAdapter implements IEnderecoFornecedorRepository {

    private final EnderecoFornecedorRepositoryJpa jpaRepository;

    public EnderecoFornecedorRepositoryJpaAdapter(EnderecoFornecedorRepositoryJpa jpaRepository) {
        this.jpaRepository = jpaRepository;
    }

    @Override
    public EnderecoFornecedor save(EnderecoFornecedor endereco) {
        return jpaRepository.save(endereco);
    }

    @Override
    public Optional<EnderecoFornecedor> findByFornecedor(Fornecedor fornecedor) {
        return jpaRepository.findByFornecedor(fornecedor);
    }
}
