package com.baufest.tennis.springtennis.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baufest.tennis.springtennis.model.Jugador;
import com.baufest.tennis.springtennis.repository.JugadorRepositoryCriteria;

@Service
public class JugadorServiceCriteria {

    @Autowired
    private JugadorRepositoryCriteria jugadorRepositoryCriteria;
    

    public List<Jugador> listAllCriteria(){
    	return jugadorRepositoryCriteria.findAllCriteria();
    }

    public Jugador findByIdCriteria(Long id){
    	return jugadorRepositoryCriteria.findByIdCriteria(id);
    }

    public void saveCriteria(Jugador jugador){
    	jugadorRepositoryCriteria.saveCriteria(jugador);
    }

	public void deleteCriteria(Long id) {
		jugadorRepositoryCriteria.deleteCriteria(id);
	}

	public void updateCriteria(Long id, Jugador jugador) {
		jugadorRepositoryCriteria.updateCriteria(id, jugador);
	}

	public List<Jugador> getTestCriteria() {	
		return jugadorRepositoryCriteria.getTestCriteria();
	}
	
	public void postTestCriteria() {
		jugadorRepositoryCriteria.postTestCriteria();
	}
	
	
	public void putTestCriteria() {
		jugadorRepositoryCriteria.putTestCriteria();
	}
	
	
	public void deleteTestCriteria() {
		jugadorRepositoryCriteria.deleteTestCriteria();
	}
	
}
