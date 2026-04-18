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
    public Dipendente create(@RequestBody Dipendente dipendente) { return service.save(dipendente); }

    @GetMapping("/{cf}")
    @PreAuthorize("hasAnyRole('DIRETTORE', 'ADDETTO_VENDITE', 'MAGAZZINIERE', 'ADDETTO_PRODUZIONE')")
    public Dipendente getById(@PathVariable String cf) { return service.findById(cf); }

    @DeleteMapping("/{cf}")
    @PreAuthorize("hasRole('DIRETTORE')")
    public void delete(@PathVariable String cf) { service.deleteById(cf); }
}
