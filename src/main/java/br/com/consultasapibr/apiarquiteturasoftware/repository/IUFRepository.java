package br.com.consultasapibr.apiarquiteturasoftware.repository;

import br.com.consultasapibr.apiarquiteturasoftware.model.UF;
import java.util.Optional;

public interface IUFRepository  {
    Optional<UF> findByufSigla(String ufSigla);

}
