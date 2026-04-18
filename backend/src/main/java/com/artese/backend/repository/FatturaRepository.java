package com.artese.backend.repository;

import com.artese.backend.model.Fattura;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FatturaRepository extends JpaRepository<Fattura, Long> {
}
