package br.com.consultasapibr.apiarquiteturasoftware.enderecofornecedor.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import br.com.consultasapibr.apiarquiteturasoftware.enderecofornecedor.model.EnderecoFornecedor;
import br.com.consultasapibr.apiarquiteturasoftware.enderecofornecedor.repository.EnderecoFornecedorRepository;
import br.com.consultasapibr.apiarquiteturasoftware.fornecedor.model.Fornecedor;

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
