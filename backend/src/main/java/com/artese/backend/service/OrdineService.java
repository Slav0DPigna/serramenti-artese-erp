package com.artese.backend.service;

import com.artese.backend.model.Ordine;
import com.artese.backend.repository.OrdineRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrdineService {

    private final OrdineRepository repository;

    public OrdineService(OrdineRepository repository) {
        this.repository = repository;
    }

    public List<Ordine> findAll() { return repository.findAll(); }
    public Ordine save(Ordine ordine) { return repository.save(ordine); }
    public Ordine findById(Long id) { return repository.findById(id).orElse(null); }
    public void deleteById(Long id) { repository.deleteById(id); }
}
