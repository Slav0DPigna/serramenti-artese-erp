package com.artese.backend.controller;

import com.artese.backend.model.Ordine;
import com.artese.backend.service.OrdineService;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.access.prepost.PreAuthorize;

import java.util.List;

import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/ordini")
@CrossOrigin(origins = "http://localhost:4200")
public class OrdineController {

    private final OrdineService service;

    public OrdineController(OrdineService service) {
        this.service = service;
    }

    @GetMapping
    @PreAuthorize("hasAnyRole('DIRETTORE', 'ADDETTO_VENDITE', 'MAGAZZINIERE', 'ADDETTO_PRODUZIONE', 'CLIENTE')")
    public List<Ordine> getAll(JwtAuthenticationToken auth) { 
        List<Ordine> all = service.findAll();
        
        if (auth != null) {
            boolean isCliente = auth.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ROLE_CLIENTE"));
            boolean isStaff = auth.getAuthorities().stream().anyMatch(a -> !a.getAuthority().equals("ROLE_CLIENTE"));
            
            if (isCliente && !isStaff) {
                String username = (String) auth.getTokenAttributes().get("preferred_username");
                return all.stream()
                          .filter(o -> o.getCliente() != null && 
                                     (username.equalsIgnoreCase(o.getCliente().getEmail()) || 
                                      username.equalsIgnoreCase(o.getCliente().getNome().replace(" ", ".").toLowerCase())))
                          .collect(Collectors.toList());
            }
        }
        return all;
    }

    @PostMapping
    @PreAuthorize("hasAnyRole('DIRETTORE', 'ADDETTO_VENDITE', 'MAGAZZINIERE', 'ADDETTO_PRODUZIONE')")
    public Ordine create(@RequestBody Ordine ordine) { return service.save(ordine); }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('DIRETTORE', 'ADDETTO_VENDITE', 'MAGAZZINIERE', 'ADDETTO_PRODUZIONE', 'CLIENTE')")
    public Ordine getById(@PathVariable Long id) { return service.findById(id); }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('DIRETTORE')")
    public void delete(@PathVariable Long id) { service.deleteById(id); }
}
