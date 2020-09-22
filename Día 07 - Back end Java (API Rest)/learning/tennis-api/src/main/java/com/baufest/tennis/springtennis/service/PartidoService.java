package com.baufest.tennis.springtennis.service;

import com.baufest.tennis.springtennis.dto.PartidoDTO;
import com.baufest.tennis.springtennis.mapper.PartidoMapper;
import com.baufest.tennis.springtennis.repository.PartidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
public class PartidoService implements CrudMethods<PartidoDTO, Long> {

    public static final String MATCH_WITH_ID = "Match with id = ";
    public static final String DOES_NOT_EXIST = " does not exist.";
    public static final String ALREADY_EXISTS = " already exists.";

    @Autowired
    private PartidoMapper partidoMapper;

    @Autowired
    private PartidoRepository partidoRepository;

    @Override
    public PartidoDTO findById(Long id) {
        return partidoRepository.findById(id)
                .map(partidoMapper::toDTO)
                .orElseThrow(() -> new NoSuchElementException(MATCH_WITH_ID + id + DOES_NOT_EXIST));
    }

    @Override
    public Collection<PartidoDTO> findAll() {
        return partidoRepository.findAll().stream()
                .map(partidoMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public PartidoDTO save(PartidoDTO partidoDTO) {
        boolean exists = partidoDTO.getId() != null && partidoRepository.existsById(partidoDTO.getId());
        if (exists) throw new IllegalArgumentException(MATCH_WITH_ID + partidoDTO.getId() + ALREADY_EXISTS);
        return partidoMapper.toDTO(partidoRepository.save(partidoMapper.fromDTO(partidoDTO)));
    }

    @Override
    public PartidoDTO update(PartidoDTO partidoDTO) {
        boolean exists = partidoRepository.existsById(partidoDTO.getId());
        if (!exists) throw new IllegalArgumentException(MATCH_WITH_ID + partidoDTO.getId() + DOES_NOT_EXIST);
        return partidoMapper.toDTO(partidoRepository.save(partidoMapper.fromDTO(partidoDTO)));
    }

    @Override
    public void deleteById(Long id) {
        boolean exists = partidoRepository.existsById(id);
        if (!exists) {
            throw new NoSuchElementException(MATCH_WITH_ID + id + DOES_NOT_EXIST);
        }
        partidoRepository.deleteById(id);
    }

}
