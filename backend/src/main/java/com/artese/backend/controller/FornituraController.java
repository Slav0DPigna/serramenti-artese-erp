package com.artese.backend.controller;
import com.artese.backend.model.Fornitura;
import com.artese.backend.service.FornituraService;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.access.prepost.PreAuthorize;
import java.util.List;
@RestController
@PreAuthorize("hasAnyRole('DIRETTORE', 'MAGAZZINIERE', 'ADDETTO_VENDITE')")
@RequestMapping("/api/forniture")
@CrossOrigin(origins = "http://localhost:4200")
public class FornituraController {
    private final FornituraService service;
    public FornituraController(FornituraService service) { this.service = service; }
    @GetMapping public List<Fornitura> getAll() { return service.findAll(); }
    @PostMapping public Fornitura create(@RequestBody Fornitura fornitura) { return service.save(fornitura); }
    @GetMapping("/{id}") public Fornitura getById(@PathVariable Long id) { return service.findById(id); }
    @DeleteMapping("/{id}") public void delete(@PathVariable Long id) { service.deleteById(id); }
}
