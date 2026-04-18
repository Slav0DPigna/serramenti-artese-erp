package com.artese.backend.model;

import jakarta.persistence.*;

@Entity
public class ProdottoVenduto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "ordine_id")
    private Ordine ordine;

    @ManyToOne
    @JoinColumn(name = "prodotto_nome")
    private Prodotto prodotto;

    private Integer quantita;
    private String misure;
    private Double prezzoPattuito;
    private String nomePersonalizzato;

    public ProdottoVenduto() {}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Ordine getOrdine() { return ordine; }
    public void setOrdine(Ordine ordine) { this.ordine = ordine; }

    public Prodotto getProdotto() { return prodotto; }
    public void setProdotto(Prodotto prodotto) { this.prodotto = prodotto; }

    public Integer getQuantita() { return quantita; }
    public void setQuantita(Integer quantita) { this.quantita = quantita; }

    public String getMisure() { return misure; }
    public void setMisure(String misure) { this.misure = misure; }

    public Double getPrezzoPattuito() { return prezzoPattuito; }
    public void setPrezzoPattuito(Double prezzoPattuito) { this.prezzoPattuito = prezzoPattuito; }

    public String getNomePersonalizzato() { return nomePersonalizzato; }
    public void setNomePersonalizzato(String nomePersonalizzato) { this.nomePersonalizzato = nomePersonalizzato; }
}
