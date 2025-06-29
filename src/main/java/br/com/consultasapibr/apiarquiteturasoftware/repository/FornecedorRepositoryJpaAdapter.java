package br.com.consultasapibr.apiarquiteturasoftware.repository;

import br.com.consultasapibr.apiarquiteturasoftware.model.Fornecedor;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@Profile("prod")
public class FornecedorRepositoryJpaAdapter implements IFornecedorRepository {

    private final FornecedorJpaRepository jpaRepository;

    public FornecedorRepositoryJpaAdapter(FornecedorJpaRepository jpaRepository) {
        this.jpaRepository = jpaRepository;
    }

    @Override
    public Fornecedor save(Fornecedor fornecedor) {
        return jpaRepository.save(fornecedor);
    }

    @Override
    public boolean existsByCnpj(String cnpj) {
        return jpaRepository.existsByCnpj(cnpj);
    }

    @Override
    public List<Fornecedor> findAll() {
        return jpaRepository.findAll();
    }

    @Override
    public Optional<Fornecedor> findByCnpj(String cnpj) {
        return jpaRepository.findByCnpj(cnpj);
    }
}
