package br.com.consultasapibr.apiarquiteturasoftware.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import br.com.consultasapibr.apiarquiteturasoftware.model.EnderecoFornecedor;
import br.com.consultasapibr.apiarquiteturasoftware.model.Fornecedor;
import br.com.consultasapibr.apiarquiteturasoftware.repository.EnderecoFornecedorRepository;

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
