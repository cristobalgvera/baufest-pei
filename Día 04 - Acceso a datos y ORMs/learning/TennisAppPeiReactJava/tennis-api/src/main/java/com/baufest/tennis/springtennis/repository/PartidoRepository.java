package com.baufest.tennis.springtennis.repository;

import com.baufest.tennis.springtennis.model.Partido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PartidoRepository extends JpaRepository<Partido, Long> {
}
