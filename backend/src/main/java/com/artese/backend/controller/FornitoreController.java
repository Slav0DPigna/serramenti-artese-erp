package com.artese.backend.controller;

import com.artese.backend.model.Fornitore;
import com.artese.backend.service.FornitoreService;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.access.prepost.PreAuthorize;

import java.util.List;

@RestController
@PreAuthorize("hasAnyRole('DIRETTORE', 'MAGAZZINIERE')")
@RequestMapping("/api/fornitori")
@CrossOrigin(origins = "http://localhost:4200")
public class FornitoreController {

    private final FornitoreService service;

    public FornitoreController(FornitoreService service) {
        this.service = service;
    }

    @GetMapping
    public List<Fornitore> getAll() { return service.findAll(); }

    @PostMapping
    public Fornitore create(@RequestBody Fornitore fornitore) { return service.save(fornitore); }

    @GetMapping("/{piva}")
    public Fornitore getById(@PathVariable String piva) { return service.findById(piva); }

    @DeleteMapping("/{piva}")
    public void delete(@PathVariable String piva) { service.deleteById(piva); }
}
