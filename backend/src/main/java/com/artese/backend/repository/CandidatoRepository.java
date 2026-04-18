package com.artese.backend.repository;

import com.artese.backend.model.Candidato;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CandidatoRepository extends JpaRepository<Candidato, String> {
}
