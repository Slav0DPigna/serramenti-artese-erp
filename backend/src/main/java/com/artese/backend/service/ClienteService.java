package com.artese.backend.service;

import com.artese.backend.model.Cliente;
import com.artese.backend.repository.ClienteRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClienteService {

    private final ClienteRepository repository;

    public ClienteService(ClienteRepository repository) {
        this.repository = repository;
    }

    public List<Cliente> findAll() { return repository.findAll(); }
    public Cliente save(Cliente cliente) { return repository.save(cliente); }
    public Cliente findById(Long id) { return repository.findById(id).orElse(null); }
    public void deleteById(Long id) { repository.deleteById(id); }
}
