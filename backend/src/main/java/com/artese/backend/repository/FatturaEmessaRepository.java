package com.artese.backend.repository;

import com.artese.backend.model.FatturaEmessa;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FatturaEmessaRepository extends JpaRepository<FatturaEmessa, Long> {
}
