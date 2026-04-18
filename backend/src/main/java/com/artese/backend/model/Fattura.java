package com.artese.backend.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Fattura {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idProgressivo;

    private LocalDate data;
    private String emittente;
    private Double importo;
    private String statoApprovazione;

    public Fattura() {}

    public Long getIdProgressivo() { return idProgressivo; }
    public void setIdProgressivo(Long idProgressivo) { this.idProgressivo = idProgressivo; }

    public LocalDate getData() { return data; }
    public void setData(LocalDate data) { this.data = data; }

    public String getEmittente() { return emittente; }
    public void setEmittente(String emittente) { this.emittente = emittente; }

    public Double getImporto() { return importo; }
    public void setImporto(Double importo) { this.importo = importo; }

    public String getStatoApprovazione() { return statoApprovazione; }
    public void setStatoApprovazione(String statoApprovazione) { this.statoApprovazione = statoApprovazione; }
}
