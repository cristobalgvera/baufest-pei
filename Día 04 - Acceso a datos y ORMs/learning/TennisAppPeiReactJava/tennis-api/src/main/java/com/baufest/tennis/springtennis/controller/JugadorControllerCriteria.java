package com.baufest.tennis.springtennis.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.baufest.tennis.springtennis.model.Jugador;
import com.baufest.tennis.springtennis.service.JugadorServiceCriteria;

@RestController
@CrossOrigin
@RequestMapping("springtennis/api/v1/jugadores/criteria")
public class JugadorControllerCriteria {

	@Autowired
	JugadorServiceCriteria jugadorServiceCriteria;
	
    @GetMapping(value = "/listAll")
    public ResponseEntity<List<Jugador>> listAllCriteria() {
        List<Jugador> jugadores = jugadorServiceCriteria.listAllCriteria();
        return ResponseEntity.ok(jugadores);
    }
	
    @GetMapping(value = "/{id}")
    public ResponseEntity<Jugador> findByIdCriteria(@PathVariable Long id) {
    	Jugador jugador = jugadorServiceCriteria.findByIdCriteria(id);
        return ResponseEntity.ok(jugador);
    }
    
    @PostMapping(value = "/save")
    public ResponseEntity<Void> saveCriteria(@RequestBody Jugador jugador) {
    	jugadorServiceCriteria.saveCriteria(jugador);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    
    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity<Void> deleteCriteria(@PathVariable Long id) {
    	jugadorServiceCriteria.deleteCriteria(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    
    @PutMapping(value = "/update/{id}")
    public ResponseEntity<Void> saveCriteria(@PathVariable Long id, @RequestBody Jugador jugador) {
    	jugadorServiceCriteria.updateCriteria(id, jugador);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    
    @GetMapping(value = "/test/getCriteriaTest")
    public ResponseEntity<List<Jugador>> getCriteriaTest() {
    	List<Jugador> jugadores = jugadorServiceCriteria.getTestCriteria();
        return ResponseEntity.ok(jugadores);
    }
    
    @PostMapping(value = "/test/postCriteriaTest")
    public ResponseEntity<Void> postCriteriaTest() {
    	jugadorServiceCriteria.postTestCriteria();
        return new ResponseEntity<>(HttpStatus.OK);
    }
    
    @DeleteMapping(value = "/test/deleteCriteriaTest")
    public ResponseEntity<Void> deleteCriteriaTest() {
    	jugadorServiceCriteria.deleteTestCriteria();
        return new ResponseEntity<>(HttpStatus.OK);
    }
    
    @PutMapping(value = "/test/putCriteriaTest")
    public ResponseEntity<Void> putCriteriaTest() {
    	jugadorServiceCriteria.putTestCriteria();
        return new ResponseEntity<>(HttpStatus.OK);
    }
	
}
