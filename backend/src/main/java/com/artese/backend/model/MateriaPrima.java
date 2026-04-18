package com.artese.backend.model;

import jakarta.persistence.*;

@Entity
public class MateriaPrima {

    @Id
    private String nome;

    private String descrizione;
    private Integer quantitaDisponibile;
    private Integer sogliaMinima;

    public MateriaPrima() {}

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getDescrizione() { return descrizione; }
    public void setDescrizione(String descrizione) { this.descrizione = descrizione; }

    public Integer getQuantitaDisponibile() { return quantitaDisponibile; }
    public void setQuantitaDisponibile(Integer quantitaDisponibile) { this.quantitaDisponibile = quantitaDisponibile; }

    public Integer getSogliaMinima() { return sogliaMinima; }
    public void setSogliaMinima(Integer sogliaMinima) { this.sogliaMinima = sogliaMinima; }
}
