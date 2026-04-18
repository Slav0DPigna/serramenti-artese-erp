package com.artese.backend.service;

import com.artese.backend.model.ProdottoVenduto;
import com.artese.backend.repository.ProdottoVendutoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProdottoVendutoService {

    private final ProdottoVendutoRepository repository;

    public ProdottoVendutoService(ProdottoVendutoRepository repository) {
        this.repository = repository;
    }

    public List<ProdottoVenduto> findAll() { return repository.findAll(); }
    public ProdottoVenduto save(ProdottoVenduto pv) { return repository.save(pv); }
    public ProdottoVenduto findById(Long id) { return repository.findById(id).orElse(null); }
    public void deleteById(Long id) { repository.deleteById(id); }
}
