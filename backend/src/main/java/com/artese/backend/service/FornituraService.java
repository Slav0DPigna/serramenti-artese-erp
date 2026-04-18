package com.artese.backend.service;
import com.artese.backend.model.Fornitura;
import com.artese.backend.repository.FornituraRepository;
import org.springframework.stereotype.Service;
import java.util.List;
@Service
public class FornituraService {
    private final FornituraRepository repository;
    public FornituraService(FornituraRepository repository) { this.repository = repository; }
    public List<Fornitura> findAll() { return repository.findAll(); }
    public Fornitura save(Fornitura fornitura) { return repository.save(fornitura); }
    public Fornitura findById(Long id) { return repository.findById(id).orElse(null); }
    public void deleteById(Long id) { repository.deleteById(id); }
}
