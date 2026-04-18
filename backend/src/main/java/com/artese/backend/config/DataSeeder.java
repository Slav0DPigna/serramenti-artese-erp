package com.artese.backend.config;

import com.artese.backend.model.*;
import com.artese.backend.repository.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Configuration
public class DataSeeder {

    @Bean
    public CommandLineRunner loadData(
            ProdottoRepository prodottoRepository,
            DipendenteRepository dipendenteRepository,
            MateriaPrimaRepository materiaPrimaRepository,
            ClienteRepository clienteRepository,
            FornitoreRepository fornitoreRepository,
            FornituraRepository fornituraRepository,
            CandidatoRepository candidatoRepository,
            ColloquioRepository colloquioRepository,
            PermessoRepository permessoRepository,
            OrdineRepository ordineRepository,
            ProdottoVendutoRepository prodottoVendutoRepository,
            FatturaEmessaRepository fatturaEmessaRepository,
            FatturaRicevutaRepository fatturaRicevutaRepository
    ) {
        return args -> {
            // Prodotti
            Prodotto p1 = null, p2 = null, p3 = null;
            if (prodottoRepository.count() == 0) {
                p1 = new Prodotto(); p1.setNome("Finestra in Alluminio Taglio Termico"); p1.setDescrizione("Ideale per isolamento termico e acustico. Profilo a 5 camere.");
                p2 = new Prodotto(); p2.setNome("Porta Finestra PVC scorrevole"); p2.setDescrizione("Doppio vetro camera antisfondamento con gas argon.");
                p3 = new Prodotto(); p3.setNome("Persiana in Alluminio Orientabile"); p3.setDescrizione("Alette regolabili per ottimizzare la luce.");
                prodottoRepository.saveAll(List.of(p1, p2, p3));
            } else {
                p1 = prodottoRepository.findById("Finestra in Alluminio Taglio Termico").orElse(null);
                p2 = prodottoRepository.findById("Porta Finestra PVC scorrevole").orElse(null);
            }

            // Clienti
            Cliente c1 = null, c2 = null;
            if (clienteRepository.count() == 0) {
                c1 = new Cliente(); c1.setNome("Giulia Cliente"); c1.setEmail("giulia.cliente"); c1.setTelefono("3331234567"); c1.setIndirizzo("Via Dante 10, Crotone"); c1.setPiva("IT01234567890");
                c2 = new Cliente(); c2.setNome("Azienda EdilCostruzioni SRL"); c2.setEmail("info@edilcostruzioni.it"); c2.setTelefono("0962123456"); c2.setIndirizzo("Zona Ind. Passovecchio, Crotone");
                clienteRepository.saveAll(List.of(c1, c2));
            } else {
                c1 = clienteRepository.findAll().get(0);
                c2 = clienteRepository.findAll().get(1);
            }

            // Dipendenti
            Dipendente d1 = null, d2 = null, d3 = null, d4 = null;
            if (dipendenteRepository.count() == 0) {
                d1 = new Dipendente(); d1.setCf("RSSMRA80A01H501Z"); d1.setNome("Marco Produzione"); d1.setEmail("marco.produzione"); d1.setRuolo("Addetto Produzione"); d1.setStipendio(1600.0);
                d2 = new Dipendente(); d2.setCf("BNCGLA85A41H501Z"); d2.setNome("Giovanni Vendite"); d2.setEmail("giovanni.vendite"); d2.setRuolo("Addetto alle Vendite"); d2.setStipendio(1500.0);
                d3 = new Dipendente(); d3.setCf("DRTPTR70A01H501Z"); d3.setNome("Pietro Artese"); d3.setEmail("admin"); d3.setRuolo("Direttore"); d3.setStipendio(3500.0);
                d4 = new Dipendente(); d4.setCf("LGGZNR85A41H501Z"); d4.setNome("Luigi Magazziniere"); d4.setEmail("luigi.magazziniere"); d4.setRuolo("Magazziniere"); d4.setStipendio(1400.0);
                dipendenteRepository.saveAll(List.of(d1, d2, d3, d4));
            } else {
                d1 = dipendenteRepository.findById("RSSMRA80A01H501Z").orElse(null);
                if (d1 != null) { d1.setEmail("marco.produzione"); d1.setNome("Marco Produzione"); dipendenteRepository.save(d1); }
                
                d2 = dipendenteRepository.findById("BNCGLA85A41H501Z").orElse(null);
                if (d2 != null) { d2.setEmail("giovanni.vendite"); d2.setNome("Giovanni Vendite"); dipendenteRepository.save(d2); }
                
                d3 = dipendenteRepository.findById("DRTPTR70A01H501Z").orElse(null);
                if (d3 != null) { d3.setEmail("admin"); dipendenteRepository.save(d3); }

                d4 = dipendenteRepository.findById("LGGZNR85A41H501Z").orElse(null);
                if (d4 == null) {
                    d4 = new Dipendente(); d4.setCf("LGGZNR85A41H501Z"); d4.setNome("Luigi Magazziniere"); d4.setEmail("luigi.magazziniere"); d4.setRuolo("Magazziniere"); d4.setStipendio(1400.0);
                    dipendenteRepository.save(d4);
                }
            }

            // Candidati & Colloqui
            if (candidatoRepository.count() == 0 && d3 != null) {
                Candidato cand1 = new Candidato(); cand1.setCf("LNZMRC95B01H501X"); cand1.setNome("Marco Lanzi"); cand1.setTelefono("3911234567"); cand1.setEmail("marco.lanzi@gmail.com"); cand1.setCv("Ottima esperienza come saldatore PVC.");
                candidatoRepository.save(cand1);
                
                Colloquio col = new Colloquio(); col.setData(LocalDateTime.now().plusDays(3)); col.setCandidato(cand1); col.setIntervistatore(d3);
                colloquioRepository.save(col);
            }

            // Permessi (rimossi da seed per demo pulita)

            // Fornitori & Materie Prime & Forniture
            Fornitore f1 = null;
            if (fornitoreRepository.count() == 0) {
                f1 = new Fornitore(); f1.setPiva("IT99988877766"); f1.setNome("Alluminio Italia SPA"); f1.setTelefono("021345678"); f1.setEmail("ordini@alluminioitalia.it"); f1.setIndirizzo("Via Roma 100, Milano");
                fornitoreRepository.save(f1);
            } else {
                f1 = fornitoreRepository.findAll().get(0);
            }

            MateriaPrima mp1 = null;
            if (materiaPrimaRepository.count() == 0) {
                mp1 = new MateriaPrima(); mp1.setNome("Profilo Alluminio Anodizzato"); mp1.setDescrizione("Barre 6m per infissi"); mp1.setQuantitaDisponibile(20); mp1.setSogliaMinima(5);
                MateriaPrima mp2 = new MateriaPrima(); mp2.setNome("Guarnizione EPDM"); mp2.setDescrizione("Rotoli 50m isolante"); mp2.setQuantitaDisponibile(10); mp2.setSogliaMinima(12);
                materiaPrimaRepository.saveAll(List.of(mp1, mp2));
            } else {
                mp1 = materiaPrimaRepository.findAll().get(0);
            }

            if (fornituraRepository.count() == 0 && f1 != null && mp1 != null) {
                Fornitura frn = new Fornitura(); frn.setDataOrdine(LocalDate.now().minusDays(10)); frn.setProdottoOrdinato("Lotto 50 barre alluminio");
                frn.setFornitore(f1); frn.setMateria(mp1);
                fornituraRepository.save(frn);
            }

            // Ordini & Venduti & Fatture
            if (ordineRepository.count() == 0 && c1 != null && p1 != null && p2 != null) {
                Ordine o1 = new Ordine(); o1.setDataAcquisto(LocalDate.now().minusDays(5)); o1.setIndirizzoConsegna("Via Dante 10, Crotone");
                o1.setImportoTotale(2300.0); o1.setStato("In lavorazione"); o1.setInstallazionePrevista(true); o1.setCliente(c1);
                ordineRepository.save(o1);

                ProdottoVenduto pv1 = new ProdottoVenduto(); pv1.setOrdine(o1); pv1.setProdotto(p1); pv1.setMisure("120x150"); pv1.setQuantita(2); pv1.setPrezzoPattuito(650.0);
                ProdottoVenduto pv2 = new ProdottoVenduto(); pv2.setOrdine(o1); pv2.setProdotto(p2); pv2.setMisure("90x210"); pv2.setQuantita(1); pv2.setPrezzoPattuito(1000.0);
                prodottoVendutoRepository.saveAll(List.of(pv1, pv2));

                FatturaEmessa fe = new FatturaEmessa(); fe.setEmittente("Serramenti Artese"); fe.setImporto(2300.0); fe.setData(LocalDate.now()); fe.setEstremiIntestatario("Luigi Verdi - Crotone"); fe.setOrdine(o1); fe.setStatoApprovazione("Pagata");
                fatturaEmessaRepository.save(fe);
            }

            if (fatturaRicevutaRepository.count() == 0 && f1 != null) {
                FatturaRicevuta fr = new FatturaRicevuta(); fr.setEmittente(f1.getNome()); fr.setImporto(4500.0); fr.setData(LocalDate.now().minusDays(2)); fr.setFornitore(f1); fr.setStatoApprovazione("Da pagare");
                fatturaRicevutaRepository.save(fr);
            }
        };
    }
}
