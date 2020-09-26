package com.baufest.tennis.springtennis.service;

import com.baufest.tennis.springtennis.dto.JugadorDTO;
import com.baufest.tennis.springtennis.dto.PartidoDTO;
import com.baufest.tennis.springtennis.enums.Estado;
import com.baufest.tennis.springtennis.mapper.CycleAvoidingMappingContext;
import com.baufest.tennis.springtennis.mapper.JugadorMapper;
import com.baufest.tennis.springtennis.repository.JugadorRepository;
import com.baufest.tennis.springtennis.repository.PartidoRepository;
import com.baufest.tennis.springtennis.service.util.JugadorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
public class JugadorServiceImpl implements JugadorService {
    public static final String PLAYER_WITH_ID = "Player with id = ";
    public static final String DOES_NOT_EXIST = " does not exist.";
    public static final String ALREADY_EXISTS = " already exists.";
    private final JugadorRepository jugadorRepository;
    private final JugadorMapper jugadorMapper;

    @Autowired
    private PartidoRepository partidoRepository;

    @Autowired
    public JugadorServiceImpl(JugadorRepository jugadorRepository,
                              JugadorMapper jugadorMapper) {
        this.jugadorRepository = jugadorRepository;
        this.jugadorMapper = jugadorMapper;
    }

    @Override
    public List<JugadorDTO> listAll() {
        return jugadorRepository.findAllByOrderByNombreAsc().stream()
                .map(jugador -> jugadorMapper.toDTO(jugador, new CycleAvoidingMappingContext()))
                .collect(Collectors.toList());
    }

    @Override
    public JugadorDTO getById(Long id) {
        return jugadorRepository.findById(id).map(jugador -> jugadorMapper.toDTO(jugador, new CycleAvoidingMappingContext()))
                .orElseThrow(() -> new NoSuchElementException(PLAYER_WITH_ID + id + DOES_NOT_EXIST));
    }

    @Override
    public JugadorDTO save(JugadorDTO jugador) {
        boolean exists = jugador.getId() != null && jugadorRepository.existsById(jugador.getId());
        if (exists) {
            throw new IllegalArgumentException(PLAYER_WITH_ID + jugador.getId() + ALREADY_EXISTS);
        }
        return this.jugadorMapper.toDTO(jugadorRepository.save(this.jugadorMapper.fromDTO(jugador, new CycleAvoidingMappingContext())), new CycleAvoidingMappingContext());
    }

    @Override
    public JugadorDTO update(JugadorDTO jugador) {
        validatePlayerExists(jugador.getId());
        return this.jugadorMapper.toDTO(jugadorRepository.save(this.jugadorMapper.fromDTO(jugador, new CycleAvoidingMappingContext())), new CycleAvoidingMappingContext());
    }

    @Override
    public void delete(Long id) {
        validatePlayerExists(id);
        jugadorRepository.deleteById(id);
    }

    @Override
    public void calculateRankingById(Long id) {
//        validatePlayerExists(id);
        JugadorDTO jugadorDTO = getById(id);
        int ranking = realizeCalculations(jugadorDTO);
        jugadorDTO.setPuntos(ranking);

        update(jugadorDTO);
    }

    private int realizeCalculations(JugadorDTO jugadorDTO) {
        Collection<PartidoDTO> partidosLocal = jugadorDTO.getPartidosLocal();
        Collection<PartidoDTO> partidosVisita = jugadorDTO.getPartidosVisita();
        int ranking = 0;

        for (PartidoDTO partidoLocal : partidosLocal) {
            if (partidoLocal.getEstado().equals(Estado.FINALIZADO)) {
                if (partidoLocal.getCantidadGamesLocal() == 6)
                    ranking += 10;
                else ranking -= 5;
            }
        }

        for (PartidoDTO partidoVisita : partidosVisita) {
            if (partidoVisita.getEstado().equals(Estado.FINALIZADO)) {
                if (partidoVisita.getCantidadGamesVisitante() == 6)
                    ranking += 15;
            }
        }

        return Math.max(ranking, 0);
    }

    private void validatePlayerExists(Long id) {
        boolean playerExist = jugadorRepository.existsById(id);
        if (!playerExist)
            throw new NoSuchElementException(PLAYER_WITH_ID + id + DOES_NOT_EXIST);
    }

    @Override
    public void calculateRankingForAll() {
        Collection<JugadorDTO> jugadoresDTO = listAll();
        for (JugadorDTO jugadorDTO : jugadoresDTO) {
            jugadorDTO.setPuntos(realizeCalculations(jugadorDTO));
        }

        jugadorRepository.saveAll(
                jugadoresDTO.stream()
                        .map(jugadorDTO -> jugadorMapper.fromDTO(jugadorDTO, new CycleAvoidingMappingContext()))
                        .collect(Collectors.toList())
        );
    }

}
