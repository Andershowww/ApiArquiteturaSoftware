package repository;

import java.util.ArrayList;
import java.util.List;

import model.Fornecedor;

public class FornecedorRepository {
    private final List<Fornecedor> lista = new ArrayList<>();

    public void salvar(Fornecedor f) {
        lista.add(f);
    }

    public List<Fornecedor> listarTodos() {
        return lista;
    }
}
