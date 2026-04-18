package com.artese.backend.controller;
import com.artese.backend.model.Colloquio;
import com.artese.backend.service.ColloquioService;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.access.prepost.PreAuthorize;
import java.util.List;
@RestController
@PreAuthorize("hasRole('DIRETTORE')")
@RequestMapping("/api/colloqui")
@CrossOrigin(origins = "http://localhost:4200")
public class ColloquioController {
    private final ColloquioService service;
    public ColloquioController(ColloquioService service) { this.service = service; }
    @GetMapping public List<Colloquio> getAll() { return service.findAll(); }
    @PostMapping public Colloquio create(@RequestBody Colloquio colloquio) { return service.save(colloquio); }
    @GetMapping("/{id}") public Colloquio getById(@PathVariable Long id) { return service.findById(id); }
    @DeleteMapping("/{id}") public void delete(@PathVariable Long id) { service.deleteById(id); }
}
