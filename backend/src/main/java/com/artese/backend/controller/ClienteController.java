package com.artese.backend.controller;

import com.artese.backend.model.Cliente;
import com.artese.backend.service.ClienteService;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.access.prepost.PreAuthorize;

import java.util.List;

@RestController
@PreAuthorize("hasAnyRole('DIRETTORE', 'ADDETTO_VENDITE', 'MAGAZZINIERE')")
@RequestMapping("/api/clienti")
@CrossOrigin(origins = "http://localhost:4200")
public class ClienteController {

    private final ClienteService service;

    public ClienteController(ClienteService service) {
        this.service = service;
    }

    @GetMapping
    public List<Cliente> getAll() { return service.findAll(); }

    @PostMapping
    public Cliente create(@RequestBody Cliente cliente) { return service.save(cliente); }

    @GetMapping("/{id}")
    public Cliente getById(@PathVariable Long id) { return service.findById(id); }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) { service.deleteById(id); }
}
