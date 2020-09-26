package com.baufest.tennis.springtennis.service.util;

import com.baufest.tennis.springtennis.dto.JugadorDTO;

import java.util.List;

public interface JugadorService {
	List<JugadorDTO> listAll();

	JugadorDTO getById(Long id);

	JugadorDTO save(JugadorDTO jugador);

	JugadorDTO update(JugadorDTO jugador);

	void delete(Long id);

	void calculateRankingById(Long id);

	void calculateRankingForAll();

}
