package br.com.consultasapibr.apiarquiteturasoftware.repository;

import org.springframework.stereotype.Repository;
import java.util.ArrayList;
import java.util.List;
import br.com.consultasapibr.apiarquiteturasoftware.model.Fornecedor;

@Repository
public class FornecedorRepository {
    private final List<Fornecedor> lista = new ArrayList<>();

    public void salvar(Fornecedor f) {
        lista.add(f);
    }

    public List<Fornecedor> listarTodos() {
        return lista;
    }
}
