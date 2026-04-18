package com.artese.backend.repository;

import com.artese.backend.model.Permesso;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PermessoRepository extends JpaRepository<Permesso, Long> {
}
