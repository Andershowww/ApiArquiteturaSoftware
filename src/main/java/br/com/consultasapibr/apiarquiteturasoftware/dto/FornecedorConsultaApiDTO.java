package br.com.consultasapibr.apiarquiteturasoftware.dto;

import br.com.consultasapibr.apiarquiteturasoftware.model.Fornecedor;

public class FornecedorConsultaApiDTO {
    private int id;
    private String cnpj;
    private String razaoSocial;
    private String nomeFantasia;
    private String logradouro;
    private String municipio;
    private String uf;

    public FornecedorConsultaApiDTO(String cnpj, String razaoSocial, String nomeFantasia,
                      String logradouro, String municipio, String uf) {
        this.cnpj = cnpj;
        this.razaoSocial = razaoSocial;
        this.nomeFantasia = nomeFantasia;
        this.logradouro = logradouro;
        this.municipio = municipio;
        this.uf = uf;
    }

    public FornecedorConsultaApiDTO(Fornecedor fornecedor) {
        this.id= fornecedor.getId();
        this.cnpj = fornecedor.getCnpj();
        this.razaoSocial = fornecedor.getRazaoSocial();
        this.nomeFantasia = fornecedor.getNomeFantasia();
        this.logradouro = fornecedor.getLogradouro();
        this.municipio = fornecedor.getMunicipio();
        this.uf = fornecedor.getUf() != null ? fornecedor.getUf().getUF() : null;
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

    public String getLogradouro() {
        return logradouro;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    public String getMunicipio() {
        return municipio;
    }

    public void setMunicipio(String municipio) {
        this.municipio = municipio;
    }

    public String getUF() {
        return uf;
    }

    public void setUF(String uf) {
        this.uf = uf;
    }
}