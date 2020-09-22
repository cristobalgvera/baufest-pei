package com.baufest.tennis.springtennis.repository;

import com.baufest.tennis.springtennis.model.Partido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface PartidoRepository extends JpaRepository<Partido, Long> {
    Collection<Partido> findAllOrderByJugadorLocal_Nombre();
}
