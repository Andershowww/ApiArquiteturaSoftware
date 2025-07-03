package br.com.consultasapibr.apiarquiteturasoftware.mock;

import br.com.consultasapibr.apiarquiteturasoftware.model.EnderecoFornecedor;
import br.com.consultasapibr.apiarquiteturasoftware.model.Fornecedor;
import br.com.consultasapibr.apiarquiteturasoftware.repository.IEnderecoFornecedorRepository;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
@Profile("dev")
public class EnderecoFornecedorRepositoryMock implements IEnderecoFornecedorRepository {

    private final Map<Integer, EnderecoFornecedor> enderecoMap = new HashMap<>();

    @Override
    public EnderecoFornecedor save(EnderecoFornecedor endereco) {
        int id = enderecoMap.size() + 1;
        endereco.setId(id);
        enderecoMap.put(id, endereco);
        return endereco;
    }

    @Override
    public Optional<EnderecoFornecedor> findByFornecedor(Fornecedor fornecedor) {
        return enderecoMap.values().stream()
                .filter(e -> e.getFornecedor().equals(fornecedor))
                .findFirst();
    }
}
