package com.artese.backend.service;

import com.artese.backend.model.Candidato;
import com.artese.backend.repository.CandidatoRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class CandidatoService {
    private final CandidatoRepository repository;

    public CandidatoService(CandidatoRepository repository) {
        this.repository = repository;
    }

    public List<Candidato> findAll() {
        return repository.findAll();
    }

    public Candidato save(Candidato candidato) {
        return repository.save(candidato);
    }

    public Candidato findById(String cf) {
        return repository.findById(cf).orElse(null);
    }

    public void deleteById(String cf) {
        repository.deleteById(cf);
    }
}
