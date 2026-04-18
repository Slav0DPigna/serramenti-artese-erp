package com.artese.backend.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
public class Ordine {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate dataAcquisto;
    private String indirizzoConsegna;
    private String stato;
    private Boolean installazionePrevista;
    private Double importoTotale;

    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;

    public Ordine() {}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public LocalDate getDataAcquisto() { return dataAcquisto; }
    public void setDataAcquisto(LocalDate dataAcquisto) { this.dataAcquisto = dataAcquisto; }

    public String getIndirizzoConsegna() { return indirizzoConsegna; }
    public void setIndirizzoConsegna(String indirizzoConsegna) { this.indirizzoConsegna = indirizzoConsegna; }

    public String getStato() { return stato; }
    public void setStato(String stato) { this.stato = stato; }

    public Boolean getInstallazionePrevista() { return installazionePrevista; }
    public void setInstallazionePrevista(Boolean installazionePrevista) { this.installazionePrevista = installazionePrevista; }

    public Double getImportoTotale() { return importoTotale; }
    public void setImportoTotale(Double importoTotale) { this.importoTotale = importoTotale; }

    public Cliente getCliente() { return cliente; }
    public void setCliente(Cliente cliente) { this.cliente = cliente; }
}
