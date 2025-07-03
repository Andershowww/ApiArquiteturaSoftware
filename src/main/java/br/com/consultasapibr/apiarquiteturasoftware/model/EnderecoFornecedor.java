package br.com.consultasapibr.apiarquiteturasoftware.model;


import jakarta.persistence.*;

@Entity
@Table(name = "EnderecoFornecedor")
public class EnderecoFornecedor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_endereco")
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_fornecedor", nullable = false)
    private Fornecedor fornecedor;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_uf", nullable = false)
    private UF uf;

    @Column(name = "logradouro", nullable = false, length = 50)
    private String logradouro;

    @Column(name = "numero", nullable = false, length = 10)
    private String numero;

    @Column(name = "bairro", nullable = false, length = 50)
    private String bairro;

    @Column(name = "municipio", nullable = false, length = 50)
    private String municipio;

    @Column(name = "complemento", nullable = false, length = 50)
    private String complemento;

    // 🔹 Construtor padrão (necessário para o JPA)
    public EnderecoFornecedor() {}

    // 🔹 Construtor completo (exceto id)
    public EnderecoFornecedor(Fornecedor fornecedor, UF uf, String logradouro, String numero,
                              String bairro, String municipio, String complemento) {
        this.fornecedor = fornecedor;
        this.uf = uf;
        this.logradouro = logradouro;
        this.numero = numero;
        this.bairro = bairro;
        this.municipio = municipio;
        this.complemento = complemento;
    }

    // 🔹 Getters e Setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Fornecedor getFornecedor() {
        return fornecedor;
    }

    public void setFornecedor(Fornecedor fornecedor) {
        this.fornecedor = fornecedor;
    }

    public UF getUf() {
        return uf;
    }

    public void setUf(UF uf) {
        this.uf = uf;
    }

    public String getLogradouro() {
        return logradouro;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getMunicipio() {
        return municipio;
    }

    public void setMunicipio(String municipio) {
        this.municipio = municipio;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }
}

