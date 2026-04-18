package com.artese.backend.service;

import com.artese.backend.model.Fornitore;
import com.artese.backend.repository.FornitoreRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FornitoreService {

    private final FornitoreRepository repository;

    public FornitoreService(FornitoreRepository repository) {
        this.repository = repository;
    }

    public List<Fornitore> findAll() { return repository.findAll(); }
    public Fornitore save(Fornitore fornitore) { return repository.save(fornitore); }
    public Fornitore findById(String piva) { return repository.findById(piva).orElse(null); }
    public void deleteById(String piva) { repository.deleteById(piva); }
}
