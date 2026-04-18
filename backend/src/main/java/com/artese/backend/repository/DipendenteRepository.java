package com.artese.backend.repository;

import com.artese.backend.model.Dipendente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DipendenteRepository extends JpaRepository<Dipendente, String> {
    boolean existsByEmail(String email);
}
