package com.artese.backend.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
public class Fornitura {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate dataOrdine;
    
    // Can be null if it refers directly to MateriaPrima, but kept for legacy text
    private String prodottoOrdinato;

    @ManyToOne
    @JoinColumn(name = "fornitore_piva")
    private Fornitore fornitore;

    @ManyToOne
    @JoinColumn(name = "materia_nome")
    private MateriaPrima materia;

    public Fornitura() {}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public LocalDate getDataOrdine() { return dataOrdine; }
    public void setDataOrdine(LocalDate dataOrdine) { this.dataOrdine = dataOrdine; }

    public String getProdottoOrdinato() { return prodottoOrdinato; }
    public void setProdottoOrdinato(String prodottoOrdinato) { this.prodottoOrdinato = prodottoOrdinato; }

    public Fornitore getFornitore() { return fornitore; }
    public void setFornitore(Fornitore fornitore) { this.fornitore = fornitore; }

    public MateriaPrima getMateria() { return materia; }
    public void setMateria(MateriaPrima materia) { this.materia = materia; }
}
