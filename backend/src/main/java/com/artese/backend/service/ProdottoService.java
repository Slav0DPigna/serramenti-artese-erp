package com.artese.backend.service;
import com.artese.backend.model.Prodotto;
import com.artese.backend.repository.ProdottoRepository;
import org.springframework.stereotype.Service;
import java.util.List;
@Service
public class ProdottoService {
    private final ProdottoRepository repository;
    public ProdottoService(ProdottoRepository repository) { this.repository = repository; }
    public List<Prodotto> findAll() { return repository.findAll(); }
    public Prodotto save(Prodotto prodotto) { return repository.save(prodotto); }
    public Prodotto findById(String nome) { return repository.findById(nome).orElse(null); }
    public void deleteById(String nome) { repository.deleteById(nome); }
}
