package com.artese.backend.controller;
import com.artese.backend.model.MateriaPrima;
import com.artese.backend.service.MateriaPrimaService;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.access.prepost.PreAuthorize;
import java.util.List;
@RestController
@PreAuthorize("hasAnyRole('DIRETTORE', 'MAGAZZINIERE', 'ADDETTO_PRODUZIONE')")
@RequestMapping("/api/materieprime")
@CrossOrigin(origins = "http://localhost:4200")
public class MateriaPrimaController {
    private final MateriaPrimaService service;
    public MateriaPrimaController(MateriaPrimaService service) { this.service = service; }
    @GetMapping public List<MateriaPrima> getAll() { return service.findAll(); }
    @PostMapping public MateriaPrima create(@RequestBody MateriaPrima materia) { return service.save(materia); }
    @GetMapping("/{nome}") public MateriaPrima getById(@PathVariable String nome) { return service.findById(nome); }
    @DeleteMapping("/{nome}") public void delete(@PathVariable String nome) { service.deleteById(nome); }
}
