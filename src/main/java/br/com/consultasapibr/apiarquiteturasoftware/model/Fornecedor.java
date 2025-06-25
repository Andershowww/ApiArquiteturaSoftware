package br.com.consultasapibr.apiarquiteturasoftware.model;
import jakarta.persistence.*;

@Entity
@Table(name = "Fornecedor")
public class Fornecedor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_fornecedor")
    private Integer id;

    @Column(name = "razao_social")
    private String razaoSocial;

    @Column(name = "nome_Fantasia")
    private String nomeFantasia;

    @Column(name = "logradouro")
    private String logradouro;

    @Column(name = "municipio")
    private String municipio;

    @ManyToOne
    @JoinColumn(name = "id_uf") // FK
    private UF uf;

    // Getters e setters
    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public String getRazaoSocial() { return razaoSocial; }
    public void setRazaoSocial(String razaoSocial) { this.razaoSocial = razaoSocial; }

    public String getNomeFantasia() { return nomeFantasia; }
    public void setNomeFantasia(String nomeFantasia) { this.nomeFantasia = nomeFantasia; }

    public String getLogradouro() { return logradouro; }
    public void setLogradouro(String logradouro) { this.logradouro = logradouro; }

    public String getMunicipio() { return municipio; }
    public void setMunicipio(String municipio) { this.municipio = municipio; }

    public UF getUf() { return uf; }
    public void setUf(UF uf) { this.uf = uf; }
}
