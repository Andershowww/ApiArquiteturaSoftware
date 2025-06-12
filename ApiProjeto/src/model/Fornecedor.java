package model;

public class Fornecedor {

    private String cnpj;
    private String razaoSocial;
    private String nomeFantasia;
    private String logradouro;
    private String municipio;
    private String uf;

    // Construtor, getters e setters
    public Fornecedor(String cnpj, String razaoSocial, String nomeFantasia,
                      String logradouro, String municipio, String uf) {
        this.cnpj = cnpj;
        this.razaoSocial = razaoSocial;
        this.nomeFantasia = nomeFantasia;
        this.logradouro = logradouro;
        this.municipio = municipio;
        this.uf = uf;
    }

    // Getters
    public String getCnpj() { return cnpj; }
    public String getRazaoSocial() { return razaoSocial; }
    public String getNomeFantasia() { return nomeFantasia; }
    public String getLogradouro() { return logradouro; }
    public String getMunicipio() { return municipio; }
    public String getUf() { return uf; }
}
