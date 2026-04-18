package com.artese.backend.repository;

import com.artese.backend.model.Colloquio;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ColloquioRepository extends JpaRepository<Colloquio, Long> {
}
