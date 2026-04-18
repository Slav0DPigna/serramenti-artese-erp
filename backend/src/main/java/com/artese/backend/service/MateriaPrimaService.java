package com.artese.backend.service;
import com.artese.backend.model.MateriaPrima;
import com.artese.backend.repository.MateriaPrimaRepository;
import org.springframework.stereotype.Service;
import java.util.List;
@Service
public class MateriaPrimaService {
    private final MateriaPrimaRepository repository;
    public MateriaPrimaService(MateriaPrimaRepository repository) { this.repository = repository; }
    public List<MateriaPrima> findAll() { return repository.findAll(); }
    public MateriaPrima save(MateriaPrima materia) { return repository.save(materia); }
    public MateriaPrima findById(String nome) { return repository.findById(nome).orElse(null); }
    public void deleteById(String nome) { repository.deleteById(nome); }
}
