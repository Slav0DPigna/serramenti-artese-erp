package com.artese.backend.controller;

import com.artese.backend.model.ProdottoVenduto;
import com.artese.backend.service.ProdottoVendutoService;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.access.prepost.PreAuthorize;

import java.util.List;

@RestController
@PreAuthorize("hasAnyRole('DIRETTORE', 'ADDETTO_VENDITE')")
@RequestMapping("/api/prodottivenduti")
@CrossOrigin(origins = "http://localhost:4200")
public class ProdottoVendutoController {

    private final ProdottoVendutoService service;

    public ProdottoVendutoController(ProdottoVendutoService service) {
        this.service = service;
    }

    @GetMapping
    public List<ProdottoVenduto> getAll() { return service.findAll(); }

    @PostMapping
    public ProdottoVenduto create(@RequestBody ProdottoVenduto pv) { return service.save(pv); }

    @GetMapping("/{id}")
    public ProdottoVenduto getById(@PathVariable Long id) { return service.findById(id); }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) { service.deleteById(id); }
}
