package com.baufest.tennis.springtennis.service;

import com.baufest.tennis.springtennis.dto.CanchaDTO;
import com.baufest.tennis.springtennis.dto.PartidoDTO;
import com.baufest.tennis.springtennis.enums.Estado;
import com.baufest.tennis.springtennis.mapper.CanchaMapper;
import com.baufest.tennis.springtennis.mapper.CycleAvoidingMappingContext;
import com.baufest.tennis.springtennis.repository.CanchaRepository;
import com.baufest.tennis.springtennis.service.util.CrudMethods;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Date;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
public class CanchaService implements CrudMethods<CanchaDTO, Long> {

    public static final String CANCHA_WITH_ID = "Cancha with id = ";
    public static final String DOES_NOT_EXIST = " does not exist.";
    public static final String HAS_PARTIDOS_TO_PLAY = " has Partidos to play.";
    public static final String CANCHA_TO_SAVE_CANT_CONTAINS_ID = "Cancha to save can't contains id parameter.";
    public static final String CANCHA_TO_SAVE_OR_UPDATE_MUST_CONTAINS_NAME_AND_ADDRESS = "Cancha to save or update must contains name and address parameters.";
    public static final String CANCHA_TO_SAVE_OR_UPDATE_HAS_PARTIDOS_IN_OTHER_CANCHAS = "Cancha to save or update has almost one Partido in other Cancha.";

    private final CanchaRepository canchaRepository;
    private final CanchaMapper canchaMapper;

    @Autowired
    public CanchaService(CanchaRepository canchaRepository, CanchaMapper canchaMapper) {
        this.canchaRepository = canchaRepository;
        this.canchaMapper = canchaMapper;
    }

    @Override
    public CanchaDTO findById(Long id) {
        return canchaRepository.findById(id).map(cancha -> canchaMapper.toDTO(cancha, new CycleAvoidingMappingContext()))
                .orElseThrow(() -> new NoSuchElementException(CANCHA_WITH_ID + id + DOES_NOT_EXIST));

    }

    @Override
    public Collection<CanchaDTO> findAll() {
        return canchaRepository.findAll().stream()
                .map(cancha -> canchaMapper.toDTO(cancha, new CycleAvoidingMappingContext()))
                .collect(Collectors.toList());
    }

    @Override
    public CanchaDTO save(CanchaDTO canchaDTO) {
        validateSaveCancha(canchaDTO);
        return canchaMapper.toDTO(canchaRepository.save(canchaMapper.fromDTO(canchaDTO, new CycleAvoidingMappingContext())), new CycleAvoidingMappingContext());
    }

    private void validateSaveCancha(CanchaDTO canchaDTO) {
        // TODO: Review boolean expression
        boolean hasId = canchaDTO.getId() != null;
        boolean doesNotHaveNameOrAddress = canchaDTO.getNombre() == null || canchaDTO.getDireccion() == null;

        if (hasId)
            throw new IllegalArgumentException(CANCHA_TO_SAVE_CANT_CONTAINS_ID);
        else if (doesNotHaveNameOrAddress)
            throw new IllegalArgumentException(CANCHA_TO_SAVE_OR_UPDATE_MUST_CONTAINS_NAME_AND_ADDRESS);

        validateSaveCanchaPartidos(canchaDTO);
    }

    private void validateSaveCanchaPartidos(CanchaDTO canchaDTO) {
        for (PartidoDTO partido : canchaDTO.getPartidos())
            if (partido.getCancha() != null)
                throw new IllegalArgumentException(CANCHA_TO_SAVE_OR_UPDATE_HAS_PARTIDOS_IN_OTHER_CANCHAS);
    }

    @Override
    public CanchaDTO update(CanchaDTO canchaDTO) {
        validateUpdateCancha(canchaDTO);
        return null;
    }

    private void validateUpdateCancha(CanchaDTO canchaDTO) {
        boolean canchaExists = canchaRepository.existsById(canchaDTO.getId());
        if (!canchaExists) throw new IllegalArgumentException(CANCHA_WITH_ID + canchaDTO.getId() + DOES_NOT_EXIST);

        boolean doesNotHaveNameOrAddress = canchaDTO.getNombre() == null || canchaDTO.getDireccion() == null;
        if (doesNotHaveNameOrAddress)
            throw new IllegalArgumentException(CANCHA_TO_SAVE_OR_UPDATE_MUST_CONTAINS_NAME_AND_ADDRESS);

        validateUpdateCanchaPartidos(canchaDTO);
    }

    private void validateUpdateCanchaPartidos(CanchaDTO canchaDTO) {
        for (PartidoDTO partido : canchaDTO.getPartidos())
            if (!partido.getCancha().getId().equals(canchaDTO.getId()))
                throw new IllegalArgumentException(CANCHA_TO_SAVE_OR_UPDATE_HAS_PARTIDOS_IN_OTHER_CANCHAS);
    }

    @Override
    public void deleteById(Long id) {
        validateDeleteCanchaById(id);
        canchaRepository.deleteById(id);
    }

    @Override
    public void delete(CanchaDTO canchaDTO) {
        validateDeleteCanchaById(canchaDTO.getId());
        canchaRepository.delete(canchaMapper.fromDTO(canchaDTO, new CycleAvoidingMappingContext()));
    }

    private void validateDeleteCanchaById(Long id) {
        if (!canchaRepository.existsById(id))
            throw new NoSuchElementException(CANCHA_WITH_ID + id + DOES_NOT_EXIST);

        validateNotUsedCancha(id);
    }

    private void validateNotUsedCancha(Long id) {
        CanchaDTO canchaDTO = findById(id);
        boolean canchaHasPartidosToPlay = false;

        for (PartidoDTO partido : canchaDTO.getPartidos())
            if (partido.getFechaComienzo().after(new Date()) || partido.getEstado().equals(Estado.EN_CURSO)) {
                canchaHasPartidosToPlay = true;
                break;
            }

        if (canchaHasPartidosToPlay)
            throw new IllegalArgumentException(CANCHA_WITH_ID + id + HAS_PARTIDOS_TO_PLAY);
    }
}
