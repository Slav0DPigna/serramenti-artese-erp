package com.artese.backend.model;

import jakarta.persistence.*;

@Entity
public class Candidato {

    @Id
    @Column(length = 16)
    private String cf;

    @Column(nullable = false)
    private String nome;

    private String cv;
    private String email;
    private String telefono;

    public Candidato() {}

    public String getCf() { return cf; }
    public void setCf(String cf) { this.cf = cf; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getCv() { return cv; }
    public void setCv(String cv) { this.cv = cv; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getTelefono() { return telefono; }
    public void setTelefono(String telefono) { this.telefono = telefono; }
}
