package com.artese.backend.repository;

import com.artese.backend.model.ProdottoVenduto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProdottoVendutoRepository extends JpaRepository<ProdottoVenduto, Long> {
}
