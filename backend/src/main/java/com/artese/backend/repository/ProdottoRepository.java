package com.artese.backend.repository;

import com.artese.backend.model.Prodotto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProdottoRepository extends JpaRepository<Prodotto, String> {
}
