package com.artese.backend.model;

import jakarta.persistence.*;

@Entity
public class FatturaEmessa extends Fattura {

    private String estremiIntestatario;

    @ManyToOne
    @JoinColumn(name = "ordine_id")
    @org.hibernate.annotations.OnDelete(action = org.hibernate.annotations.OnDeleteAction.CASCADE)
    private Ordine ordine;

    public FatturaEmessa() {}

    public String getEstremiIntestatario() { return estremiIntestatario; }
    public void setEstremiIntestatario(String estremiIntestatario) { this.estremiIntestatario = estremiIntestatario; }

    public Ordine getOrdine() { return ordine; }
    public void setOrdine(Ordine ordine) { this.ordine = ordine; }
}
