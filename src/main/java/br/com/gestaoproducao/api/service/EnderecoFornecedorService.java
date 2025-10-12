package br.com.gestaoproducao.api.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import br.com.gestaoproducao.api.model.EnderecoFornecedor;
import br.com.gestaoproducao.api.model.Fornecedor;
import br.com.gestaoproducao.api.repository.EnderecoFornecedorRepository;


@Service
public class EnderecoFornecedorService {

    private final EnderecoFornecedorRepository enderecoRepository;

    public EnderecoFornecedorService(EnderecoFornecedorRepository enderecoRepository) {
        this.enderecoRepository = enderecoRepository;
    }

    public EnderecoFornecedor salvar(EnderecoFornecedor endereco) {
        return enderecoRepository.save(endereco);
    }

    public Optional<EnderecoFornecedor> buscarPorFornecedor(Fornecedor fornecedor) {
        return enderecoRepository.findByFornecedor(fornecedor);
    }

    public Optional<EnderecoFornecedor> buscarPorId(Integer id) {
        return enderecoRepository.findById(id);
    }

    public void deletarPorId(Integer id) {
        enderecoRepository.deleteById(id);
    }
}
