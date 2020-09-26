package com.baufest.tennis.springtennis.controller;

import com.baufest.tennis.springtennis.dto.CanchaDTO;
import com.baufest.tennis.springtennis.service.CanchaServiceImpl;
import com.baufest.tennis.springtennis.service.PartidoServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;

@RestController
@CrossOrigin
@RequestMapping("/springtennis/api/v1/canchas")
public class CanchaController {

    @Autowired
    private CanchaServiceImpl canchaService;

    @Autowired
    private PartidoServiceImpl partidoService;

    @GetMapping
    public ResponseEntity<Collection<CanchaDTO>> findAll() {
        Collection<CanchaDTO> canchasDTO = canchaService.findAll();
        for (CanchaDTO canchaDTO : canchasDTO) {
            canchaDTO.setPartidos(new HashSet<>(
                    canchaService.findAllTodayPartidosByCanchaId(canchaDTO.getId()))
            );
        }
        return ResponseEntity.ok(canchasDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CanchaDTO> findById(@PathVariable Long id) {
        return ResponseEntity.ok(canchaService.findById(id));
    }

    @PostMapping
    public ResponseEntity<Long> save(@RequestBody CanchaDTO canchaDTO) {
        return new ResponseEntity<>(canchaService.save(canchaDTO).getId(),
                HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Long> update(@PathVariable Long id, @RequestBody CanchaDTO canchaDTO) {
        canchaDTO.setId(id);
        return ResponseEntity.ok(canchaService.update(canchaDTO).getId());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        canchaService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/{id}/actions/validar-horario")
    public ResponseEntity<Boolean> validateOccupancy(@PathVariable Long id, @RequestParam String date) throws ParseException {
        Date formattedDate;
        boolean response = false;
        try {
            if (date.length() < 11)
                formattedDate = new SimpleDateFormat("dd/MM/yyyy").parse(date);
            else
                formattedDate = new SimpleDateFormat("dd/MM/yyyy HH:mm").parse(date);

            response = partidoService.validateCanchaOccupancy(id, formattedDate);
        } catch (Exception e) {
            return new ResponseEntity<>(false, HttpStatus.OK);
        }

        System.out.println(date + " - " + formattedDate);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
