package br.com.consultasapibr.apiarquiteturasoftware.dto;

import br.com.consultasapibr.apiarquiteturasoftware.model.EnderecoFornecedor;
import br.com.consultasapibr.apiarquiteturasoftware.model.Fornecedor;

public class FornecedorConsultaApiDTO {
    private int id;
    private String cnpj;
    private String razaoSocial;
    private String nomeFantasia;
    private String cnae;
    private EnderecoFornecedorDTO endereco;
    public FornecedorConsultaApiDTO() {
        // construtor padrão vazio
    }
    public FornecedorConsultaApiDTO(String cnpj, String razaoSocial, String nomeFantasia, String cnae, EnderecoFornecedorDTO endereco) {
        this.cnpj = cnpj;
        this.razaoSocial = razaoSocial;
        this.nomeFantasia = nomeFantasia;
        this.endereco = endereco;
        this.cnae=cnae;
    }

    public FornecedorConsultaApiDTO(Fornecedor fornecedor, EnderecoFornecedor enderecoEntity) {
        this.id = fornecedor.getId();
        this.cnpj = fornecedor.getCnpj();
        this.razaoSocial = fornecedor.getRazaoSocial();
        this.nomeFantasia = fornecedor.getNomeFantasia();
        this.cnae= fornecedor.getCnae();
        if (enderecoEntity != null) {
            this.endereco = new EnderecoFornecedorDTO(enderecoEntity);
        }
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getRazaoSocial() {
        return razaoSocial;
    }

    public void setRazaoSocial(String razaoSocial) {
        this.razaoSocial = razaoSocial;
    }

    public String getNomeFantasia() {
        return nomeFantasia;
    }

    public void setNomeFantasia(String nomeFantasia) {
        this.nomeFantasia = nomeFantasia;
    }

    public String getCnae() {
        return cnae;
    }

    public void setCnae(String cnae) {
        this.cnae = cnae;
    }

    public EnderecoFornecedorDTO getEndereco() {
        return endereco;
    }

    public void setEndereco(EnderecoFornecedorDTO endereco) {
        this.endereco = endereco;
    }
    
}
