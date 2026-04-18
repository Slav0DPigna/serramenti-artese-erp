package com.artese.backend.controller;
import com.artese.backend.model.Candidato;
import com.artese.backend.service.CandidatoService;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.access.prepost.PreAuthorize;
import java.util.List;
@RestController
@PreAuthorize("hasRole('DIRETTORE')")
@RequestMapping("/api/candidati")
@CrossOrigin(origins = "http://localhost:4200")
public class CandidatoController {
    private final CandidatoService service;
    public CandidatoController(CandidatoService service) { this.service = service; }
    @GetMapping public List<Candidato> getAll() { return service.findAll(); }
    @PostMapping public Candidato create(@RequestBody Candidato candidato) { return service.save(candidato); }
    @GetMapping("/{cf}") public Candidato getById(@PathVariable String cf) { return service.findById(cf); }
    @DeleteMapping("/{cf}") public void delete(@PathVariable String cf) { service.deleteById(cf); }
}
