package com.artese.backend.controller;

import com.artese.backend.model.Dipendente;
import com.artese.backend.service.DipendenteService;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.access.prepost.PreAuthorize;

import java.util.List;

@RestController
@RequestMapping("/api/dipendenti")
@CrossOrigin(origins = "http://localhost:4200")
public class DipendenteController {

    private final DipendenteService service;

    public DipendenteController(DipendenteService service) {
        this.service = service;
    }

    @GetMapping
    @PreAuthorize("hasAnyRole('DIRETTORE', 'ADDETTO_VENDITE', 'MAGAZZINIERE', 'ADDETTO_PRODUZIONE')")
    public List<Dipendente> getAll() { return service.findAll(); }

    @PostMapping
    @PreAuthorize("hasRole('DIRETTORE')")
    public Dipendente create(@RequestBody Dipendente dipendente) {
        if (dipendente.getCf() == null) {
            throw new IllegalArgumentException("Codice Fiscale mancante.");
        }
        String cfUpper = dipendente.getCf().toUpperCase().trim();
        if (!cfUpper.matches("^[A-Z]{6}[0-9]{2}[A-Z][0-9]{2}[A-Z][0-9]{3}[A-Z]$")) {
            throw new IllegalArgumentException("Codice Fiscale malformato.");
        }
        if (service.existsById(cfUpper)) {
            throw new IllegalArgumentException("Esiste già un dipendente con questo Codice Fiscale.");
        }
        if (dipendente.getEmail() != null && service.existsByEmail(dipendente.getEmail())) {
            throw new IllegalArgumentException("Questa email (Username Keycloak) è già assegnata ad un altro dipendente.");
        }
        
        dipendente.setCf(cfUpper);
        return service.save(dipendente);
    }

    @GetMapping("/{cf}")
    @PreAuthorize("hasAnyRole('DIRETTORE', 'ADDETTO_VENDITE', 'MAGAZZINIERE', 'ADDETTO_PRODUZIONE')")
    public Dipendente getById(@PathVariable String cf) { return service.findById(cf); }

    @PutMapping("/{cf}/stipendio")
    @PreAuthorize("hasRole('DIRETTORE')")
    public Dipendente updateStipendio(@PathVariable String cf, @RequestBody java.util.Map<String, Double> payload) {
        Dipendente d = service.findById(cf);
        if (d != null && payload.containsKey("stipendio")) {
            d.setStipendio(payload.get("stipendio"));
            return service.save(d);
        }
        throw new IllegalArgumentException("Dipendente non trovato o stipendio mancante.");
    }

    @DeleteMapping("/{cf}")
    @PreAuthorize("hasRole('DIRETTORE')")
    public void delete(@PathVariable String cf) { service.deleteById(cf); }
}
