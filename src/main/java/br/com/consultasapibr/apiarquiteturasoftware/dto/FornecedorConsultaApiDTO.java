package br.com.consultasapibr.apiarquiteturasoftware.dto;

public class FornecedorConsultaApiDTO {
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