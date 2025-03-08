package com.campeonato.campeonato.repository;

import com.campeonato.campeonato.model.Time;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TimeRepository extends JpaRepository<Time, Long> {
}