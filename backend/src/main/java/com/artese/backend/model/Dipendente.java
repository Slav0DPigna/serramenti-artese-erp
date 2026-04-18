package com.artese.backend.model;

import jakarta.persistence.*;

@Entity
public class Dipendente {

    @Id
    @Column(length = 16)
    private String cf;

    @Column(nullable = false)
    private String nome;

    private String ruolo;
    private String email;
    private String telefono;
    private Double stipendio;
    private Integer oreLavorate;
    private Integer ferieResidue;

    public Dipendente() {}

    public String getCf() { return cf; }
    public void setCf(String cf) { this.cf = cf; }
    
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    
    public String getRuolo() { return ruolo; }
    public void setRuolo(String ruolo) { this.ruolo = ruolo; }
    
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    
    public String getTelefono() { return telefono; }
    public void setTelefono(String telefono) { this.telefono = telefono; }
    
    public Double getStipendio() { return stipendio; }
    public void setStipendio(Double stipendio) { this.stipendio = stipendio; }
    
    public Integer getOreLavorate() { return oreLavorate; }
    public void setOreLavorate(Integer oreLavorate) { this.oreLavorate = oreLavorate; }
    
    public Integer getFerieResidue() { return ferieResidue; }
    public void setFerieResidue(Integer ferieResidue) { this.ferieResidue = ferieResidue; }
}
