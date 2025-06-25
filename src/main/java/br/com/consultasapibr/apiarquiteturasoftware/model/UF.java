package br.com.consultasapibr.apiarquiteturasoftware.model;
import jakarta.persistence.*;

@Entity
@Table(name = "UF")
public class UF {
    @Id
    @Column(name = "id_uf")
    private Integer id;

    @Column(name = "sigla")
    private String sigla;

    // Getters e setters
    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public String getSigla() { return sigla; }
    public void setSigla(String sigla) { this.sigla = sigla; }
}
