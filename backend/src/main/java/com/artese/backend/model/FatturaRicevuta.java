package com.artese.backend.model;

import jakarta.persistence.*;

@Entity
public class FatturaRicevuta extends Fattura {

    @ManyToOne
    @JoinColumn(name = "fornitore_piva")
    private Fornitore fornitore;

    public FatturaRicevuta() {}

    public Fornitore getFornitore() { return fornitore; }
    public void setFornitore(Fornitore fornitore) { this.fornitore = fornitore; }
}
