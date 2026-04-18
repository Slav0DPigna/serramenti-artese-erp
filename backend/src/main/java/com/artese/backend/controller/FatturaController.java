package com.artese.backend.controller;

import com.artese.backend.model.Fattura;
import com.artese.backend.service.FatturaService;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.access.prepost.PreAuthorize;

import java.util.List;

@RestController
@PreAuthorize("hasAnyRole('DIRETTORE', 'ADDETTO_VENDITE', 'MAGAZZINIERE')")
@RequestMapping("/api/fatture")
@CrossOrigin(origins = "http://localhost:4200")
public class FatturaController {

    private final FatturaService service;

    public FatturaController(FatturaService service) {
        this.service = service;
    }

    @GetMapping
    public List<Fattura> getAll() { return service.findAll(); }

    @PostMapping
    public Fattura create(@RequestBody Fattura fattura) { return service.save(fattura); }

    @GetMapping("/{id}")
    public Fattura getById(@PathVariable Long id) { return service.findById(id); }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) { service.deleteById(id); }
}
