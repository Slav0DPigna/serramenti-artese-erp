package com.artese.backend.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
public class Permesso {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate dataInizio;
    private LocalDate dataFine;
    private Boolean validato;
    
    @Column(length = 500)
    private String motivazione;

    @ManyToOne
    @JoinColumn(name = "richiedente_cf")
    private Dipendente richiedente;

    @ManyToOne
    @JoinColumn(name = "validatore_cf")
    private Dipendente validatore;

    public Permesso() {}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public LocalDate getDataInizio() { return dataInizio; }
    public void setDataInizio(LocalDate dataInizio) { this.dataInizio = dataInizio; }

    public LocalDate getDataFine() { return dataFine; }
    public void setDataFine(LocalDate dataFine) { this.dataFine = dataFine; }

    public Boolean getValidato() { return validato; }
    public void setValidato(Boolean validato) { this.validato = validato; }

    public Dipendente getRichiedente() { return richiedente; }
    public void setRichiedente(Dipendente richiedente) { this.richiedente = richiedente; }

    public Dipendente getValidatore() { return validatore; }
    public void setValidatore(Dipendente validatore) { this.validatore = validatore; }

    public String getMotivazione() { return motivazione; }
    public void setMotivazione(String motivazione) { this.motivazione = motivazione; }
}
