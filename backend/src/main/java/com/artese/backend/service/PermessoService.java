package com.artese.backend.service;
import com.artese.backend.model.Permesso;
import com.artese.backend.repository.PermessoRepository;
import org.springframework.stereotype.Service;
import java.util.List;
@Service
public class PermessoService {
    private final PermessoRepository repository;
    public PermessoService(PermessoRepository repository) { this.repository = repository; }
    public List<Permesso> findAll() { return repository.findAll(); }
    public Permesso save(Permesso permesso) { return repository.save(permesso); }
    public Permesso findById(Long id) { return repository.findById(id).orElse(null); }
    public void deleteById(Long id) { repository.deleteById(id); }
}
