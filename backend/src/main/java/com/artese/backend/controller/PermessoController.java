package com.artese.backend.controller;
import com.artese.backend.model.Permesso;
import com.artese.backend.service.PermessoService;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.access.prepost.PreAuthorize;
import java.util.List;
@RestController
@RequestMapping("/api/permessi")
@CrossOrigin(origins = "http://localhost:4200")
public class PermessoController {
    
    private final PermessoService service;
    
    public PermessoController(PermessoService service) { this.service = service; }
    
    @GetMapping 
    @PreAuthorize("hasAnyRole('DIRETTORE', 'ADDETTO_VENDITE', 'MAGAZZINIERE', 'ADDETTO_PRODUZIONE')")
    public List<Permesso> getAll() { return service.findAll(); }
    
    @PostMapping 
    @PreAuthorize("hasAnyRole('DIRETTORE', 'ADDETTO_VENDITE', 'MAGAZZINIERE', 'ADDETTO_PRODUZIONE')")
    public Permesso create(@RequestBody Permesso permesso) { return service.save(permesso); }
    
    @PutMapping("/{id}/valuta")
    @PreAuthorize("hasRole('DIRETTORE')")
    public Permesso valuta(@PathVariable Long id, @RequestBody java.util.Map<String, Object> payload) {
        Permesso p = service.findById(id);
        if (p != null && payload.containsKey("validato")) {
            p.setValidato((Boolean) payload.get("validato"));
            return service.save(p);
        }
        return p;
    }

    @GetMapping("/{id}") 
    @PreAuthorize("hasAnyRole('DIRETTORE', 'ADDETTO_VENDITE', 'MAGAZZINIERE', 'ADDETTO_PRODUZIONE')")
    public Permesso getById(@PathVariable Long id) { return service.findById(id); }
    
    @DeleteMapping("/{id}") 
    @PreAuthorize("hasRole('DIRETTORE')")
    public void delete(@PathVariable Long id) { service.deleteById(id); }
}
