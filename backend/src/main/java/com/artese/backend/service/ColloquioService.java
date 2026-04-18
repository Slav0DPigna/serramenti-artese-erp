package com.artese.backend.service;
import com.artese.backend.model.Colloquio;
import com.artese.backend.repository.ColloquioRepository;
import org.springframework.stereotype.Service;
import java.util.List;
@Service
public class ColloquioService {
    private final ColloquioRepository repository;
    public ColloquioService(ColloquioRepository repository) { this.repository = repository; }
    public List<Colloquio> findAll() { return repository.findAll(); }
    public Colloquio save(Colloquio colloquio) { return repository.save(colloquio); }
    public Colloquio findById(Long id) { return repository.findById(id).orElse(null); }
    public void deleteById(Long id) { repository.deleteById(id); }
}
