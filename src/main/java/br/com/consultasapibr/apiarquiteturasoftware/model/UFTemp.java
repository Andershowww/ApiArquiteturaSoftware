package br.com.consultasapibr.apiarquiteturasoftware.model;

import jakarta.persistence.*;

@Entity
@Table(name = "UF")
public class UFTemp {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_uf")
    private Integer id;

    @Column(name = "UF")
    private String ufSigla;
    public UFTemp() {
    }
    public UFTemp(Integer id, String ufSigla) {
        this.id = id;
        this.ufSigla = ufSigla;
    }
 
    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public String getUf() { return ufSigla; }
    public void setUF(String uf) { this.ufSigla = uf;}
}
