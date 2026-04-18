package com.artese.backend.model;

import jakarta.persistence.*;

@Entity
public class Fornitore {

    @Id
    @Column(length = 20)
    private String piva;

    @Column(nullable = false)
    private String nome;

    private String indirizzo;
    private String email;
    private String telefono;

    public Fornitore() {}

    public String getPiva() { return piva; }
    public void setPiva(String piva) { this.piva = piva; }
    
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    
    public String getIndirizzo() { return indirizzo; }
    public void setIndirizzo(String indirizzo) { this.indirizzo = indirizzo; }
    
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    
    public String getTelefono() { return telefono; }
    public void setTelefono(String telefono) { this.telefono = telefono; }
}
