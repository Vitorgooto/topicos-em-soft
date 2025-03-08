package com.campeonato.campeonato.repository;

import com.campeonato.campeonato.model.Campeonato;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CampeonatoRepository extends JpaRepository<Campeonato, Long> {
}
