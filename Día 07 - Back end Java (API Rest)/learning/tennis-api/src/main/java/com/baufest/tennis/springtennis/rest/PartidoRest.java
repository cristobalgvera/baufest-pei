package com.baufest.tennis.springtennis.rest;

import com.baufest.tennis.springtennis.dto.PartidoDTO;
import com.baufest.tennis.springtennis.service.PartidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/springtennis/api/v1/partidos")
public class PartidoRest {

    @Autowired
    private PartidoService partidoService;

    @GetMapping
    public ResponseEntity<Collection<PartidoDTO>> findAll() {
        return ResponseEntity.ok(partidoService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PartidoDTO> findOne(@PathVariable Long id) {
        return ResponseEntity.ok(partidoService.findById(id));
    }

    @PostMapping
    public ResponseEntity<Long> saveOne(@RequestBody PartidoDTO partidoDto) {
        return new ResponseEntity<>(partidoService.save(partidoDto).getId(), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PartidoDTO> update(@PathVariable Long id,
                                             @RequestBody PartidoDTO partidoDTO) {
        partidoDTO.setId(id);
        return ResponseEntity.ok(partidoService.update(partidoDTO));
    }

    @DeleteMapping("/{id}")
    public HttpStatus deleteOne(@PathVariable Long id) {
        partidoService.deleteById(id);
        return HttpStatus.OK;
    }

}
