package com.baufest.tennis.springtennis.repository;

import com.baufest.tennis.springtennis.model.Jugador;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JugadorRepository extends JpaRepository<Jugador, Long> {

    List<Jugador> findAllByOrderByNombreAsc();

}
