package com.baufest.tennis.springtennis.service.util;

import com.baufest.tennis.springtennis.dto.PartidoDTO;

import java.util.Collection;

public interface CanchaServiceMethods {

    Collection<PartidoDTO> findAllTodayPartidosByCanchaId(Long id);

}
