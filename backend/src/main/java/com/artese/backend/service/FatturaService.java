package com.artese.backend.service;

import com.artese.backend.model.Fattura;
import com.artese.backend.repository.FatturaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FatturaService {

    private final FatturaRepository repository;

    public FatturaService(FatturaRepository repository) {
        this.repository = repository;
    }

    public List<Fattura> findAll() { return repository.findAll(); }
    public Fattura save(Fattura fattura) { return repository.save(fattura); }
    public Fattura findById(Long id) { return repository.findById(id).orElse(null); }
    public void deleteById(Long id) { repository.deleteById(id); }
}
