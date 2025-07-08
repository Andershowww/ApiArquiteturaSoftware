package br.com.consultasapibr.apiarquiteturasoftware.uf.repository;

import java.util.Optional;

import br.com.consultasapibr.apiarquiteturasoftware.uf.model.UF;

public interface IUFRepository  {
    Optional<UF> findByufSigla(String ufSigla);

}
