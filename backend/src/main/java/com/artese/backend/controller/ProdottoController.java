package com.artese.backend.controller;
import com.artese.backend.model.Prodotto;
import com.artese.backend.service.ProdottoService;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.access.prepost.PreAuthorize;
import java.util.List;
@RestController
@RequestMapping("/api/prodotti")
@CrossOrigin(origins = "http://localhost:4200")
public class ProdottoController {
    private final ProdottoService service;
    public ProdottoController(ProdottoService service) { this.service = service; }
    
    @GetMapping 
    public List<Prodotto> getAll() { return service.findAll(); }
    
    @GetMapping("/{nome}") 
    public Prodotto getById(@PathVariable String nome) { return service.findById(nome); }

    @PostMapping 
    @PreAuthorize("hasAnyRole('DIRETTORE', 'ADDETTO_VENDITE')")
    public Prodotto create(@RequestBody Prodotto prodotto) { return service.save(prodotto); }
    
    @DeleteMapping("/{nome}") 
    @PreAuthorize("hasAnyRole('DIRETTORE', 'ADDETTO_VENDITE')")
    public void delete(@PathVariable String nome) { service.deleteById(nome); }
}
