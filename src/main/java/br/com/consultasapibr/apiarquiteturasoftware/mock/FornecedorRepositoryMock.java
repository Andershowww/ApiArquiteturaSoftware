package br.com.consultasapibr.apiarquiteturasoftware.mock;

import br.com.consultasapibr.apiarquiteturasoftware.model.Fornecedor;
import br.com.consultasapibr.apiarquiteturasoftware.repository.IFornecedorRepository;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CopyOnWriteArrayList;

@Component
@Profile("dev")
public class FornecedorRepositoryMock implements IFornecedorRepository {

    // Thread-safe list para evitar problemas em ambiente concorrente
    private final List<Fornecedor> fornecedores = new CopyOnWriteArrayList<>();

    @Override
    public Fornecedor save(Fornecedor fornecedor) {
        if (existsByCnpj(fornecedor.getCnpj())) {
            // Atualiza registro existente
            fornecedores.removeIf(f -> f.getCnpj().equals(fornecedor.getCnpj()));
        }
        fornecedores.add(fornecedor);
        return fornecedor;
    }

    @Override
    public boolean existsByCnpj(String cnpj) {
        return fornecedores.stream().anyMatch(f -> f.getCnpj().equals(cnpj));
    }

    @Override
    public List<Fornecedor> findAll() {
        return new ArrayList<>(fornecedores);
    }

    @Override
    public Optional<Fornecedor> findByCnpj(String cnpj) {
        return fornecedores.stream()
                .filter(f -> f.getCnpj().equals(cnpj))
                .findFirst();
    }
}
