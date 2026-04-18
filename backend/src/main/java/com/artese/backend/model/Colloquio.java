package com.artese.backend.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Colloquio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime data;

    @ManyToOne
    @JoinColumn(name = "candidato_cf")
    private Candidato candidato;

    @ManyToOne
    @JoinColumn(name = "intervistatore_cf")
    private Dipendente intervistatore;

    public Colloquio() {}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public LocalDateTime getData() { return data; }
    public void setData(LocalDateTime data) { this.data = data; }

    public Candidato getCandidato() { return candidato; }
    public void setCandidato(Candidato candidato) { this.candidato = candidato; }

    public Dipendente getIntervistatore() { return intervistatore; }
    public void setIntervistatore(Dipendente intervistatore) { this.intervistatore = intervistatore; }
}
