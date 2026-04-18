package com.artese.backend.model;

import jakarta.persistence.*;

@Entity
public class Prodotto {

    @Id
    private String nome;

    private String descrizione;

    public Prodotto() {}

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getDescrizione() { return descrizione; }
    public void setDescrizione(String descrizione) { this.descrizione = descrizione; }
}
