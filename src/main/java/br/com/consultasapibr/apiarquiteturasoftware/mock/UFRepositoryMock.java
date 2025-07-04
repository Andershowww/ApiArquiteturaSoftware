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
        ufMap.put("AC", new UF(1, "AC"));
        ufMap.put("AL", new UF(2, "AL"));
        ufMap.put("AP", new UF(3, "AP"));
        ufMap.put("AM", new UF(4, "AM"));
        ufMap.put("BA", new UF(5, "BA"));
        ufMap.put("CE", new UF(6, "CE"));
        ufMap.put("DF", new UF(7, "DF"));
        ufMap.put("ES", new UF(8, "ES"));
        ufMap.put("GO", new UF(9, "GO"));
        ufMap.put("MA", new UF(10, "MA"));
        ufMap.put("MT", new UF(11, "MT"));
        ufMap.put("MS", new UF(12, "MS"));
        ufMap.put("MG", new UF(13, "MG"));
        ufMap.put("PA", new UF(14, "PA"));
        ufMap.put("PB", new UF(15, "PB"));
        ufMap.put("PR", new UF(16, "PR"));
        ufMap.put("PE", new UF(17, "PE"));
        ufMap.put("PI", new UF(18, "PI"));
        ufMap.put("RJ", new UF(19, "RJ"));
        ufMap.put("RN", new UF(20, "RN"));
        ufMap.put("RS", new UF(21, "RS"));
        ufMap.put("RO", new UF(22, "RO"));
        ufMap.put("RR", new UF(23, "RR"));
        ufMap.put("SC", new UF(24, "SC"));
        ufMap.put("SP", new UF(25, "SP"));
        ufMap.put("SE", new UF(26, "SE"));
        ufMap.put("TO", new UF(27, "TO"));
    }

    @Override
    public Optional<UF> findByufSigla(String ufSigla) {
        return Optional.ofNullable(ufMap.get(ufSigla));
    }
}
