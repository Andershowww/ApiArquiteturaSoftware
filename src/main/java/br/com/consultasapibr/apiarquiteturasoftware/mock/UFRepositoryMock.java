package br.com.consultasapibr.apiarquiteturasoftware.mock;

import br.com.consultasapibr.apiarquiteturasoftware.model.UF;
import br.com.consultasapibr.apiarquiteturasoftware.repository.IUFRepository;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
@Profile("dev")
public class UFRepositoryMock implements IUFRepository {

    private final Map<String, UF> ufMap = new HashMap<>();

    public UFRepositoryMock() {
        ufMap.put("SP", new UF(1, "SP"));
        ufMap.put("RJ", new UF(2, "RJ"));
        // Adicione mais conforme necessário
    }

    @Override
    public Optional<UF> findByufSigla(String ufSigla) {
        return Optional.ofNullable(ufMap.get(ufSigla));
    }
}
