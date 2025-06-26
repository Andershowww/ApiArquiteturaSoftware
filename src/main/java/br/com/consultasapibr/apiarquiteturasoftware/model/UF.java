package br.com.consultasapibr.apiarquiteturasoftware.model;

import jakarta.persistence.*;

@Entity
@Table(name = "UF")
public class UF {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_uf")
    private Integer id;

    @Column(name = "UF")
    private String uf;

    // Getters e Setters
    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public String getUF() { return uf; }
    public void setUF(String UF) { this.uf = UF;}
}
