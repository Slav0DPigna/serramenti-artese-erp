package com.artese.backend.service;

import com.artese.backend.model.Dipendente;
import com.artese.backend.repository.DipendenteRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DipendenteService {

    private final DipendenteRepository repository;

    public DipendenteService(DipendenteRepository repository) {
        this.repository = repository;
    }

    public List<Dipendente> findAll() { return repository.findAll(); }
    public Dipendente save(Dipendente dipendente) { return repository.save(dipendente); }
    public Dipendente findById(String cf) { return repository.findById(cf).orElse(null); }
    public boolean existsById(String cf) { return repository.existsById(cf); }
    public boolean existsByEmail(String email) { return repository.existsByEmail(email); }
    public void deleteById(String cf) { repository.deleteById(cf); }
}
