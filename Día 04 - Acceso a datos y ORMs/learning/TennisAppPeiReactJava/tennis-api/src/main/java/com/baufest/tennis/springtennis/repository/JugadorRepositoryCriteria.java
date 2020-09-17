package com.baufest.tennis.springtennis.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaDelete;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.CriteriaUpdate;
import javax.persistence.criteria.ParameterExpression;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.baufest.tennis.springtennis.model.Jugador;

@Repository
public class JugadorRepositoryCriteria{
	
	@PersistenceContext
	EntityManager entityMgrObj;
	
	@Transactional
	public List<Jugador> findAllCriteria() {
		
//		List<Jugador> jugadorList = (List<Jugador>) entityMgrObj.createNativeQuery("SELECT * FROM jugador", Jugador.class).getResultList();

        CriteriaBuilder criteriaBuilderObj = entityMgrObj.getCriteriaBuilder();
        CriteriaQuery<Jugador> queryObj = criteriaBuilderObj.createQuery(Jugador.class);
        Root<Jugador> from = queryObj.from(Jugador.class);
        CriteriaQuery<Jugador> selectQuery = queryObj.select(from);
        TypedQuery<Jugador> typedQuery = entityMgrObj.createQuery(selectQuery);
        List<Jugador> jugadorList = typedQuery.getResultList();
        
        return jugadorList;
	}
	
	public Jugador findByIdCriteria(Long id) {
		
//		Jugador jugador = (Jugador) entityMgrObj.createNativeQuery("SELECT * FROM jugador WHERE id=?", Jugador.class)
//		.setParameter(1, id)
//		.getSingleResult();
		
		CriteriaBuilder criteriaBuilderObj = entityMgrObj.getCriteriaBuilder();

		CriteriaQuery<Jugador> queryObj = criteriaBuilderObj.createQuery(Jugador.class);
		Root<Jugador> from = queryObj.from(Jugador.class);
		CriteriaQuery<Jugador> selectQuery = queryObj.select(from).where(criteriaBuilderObj.equal(from.get("id"), id));
		TypedQuery<Jugador> typedQuery = entityMgrObj.createQuery(selectQuery);
        Jugador jugador = typedQuery.getSingleResult();
        
        return jugador;
	}
	
	@Transactional
	public void saveCriteria(Jugador jugador) {
		
//		entityMgrObj.createNativeQuery("INSERT INTO jugador (nombre, puntos) VALUES (?,?)")
//		.setParameter(1, jugador.getNombre())
//		.setParameter(2, jugador.getPuntos())
//		.executeUpdate();
		
		entityMgrObj.persist(jugador);

	}
	
	@Transactional
	public void deleteCriteria(Long id) {
		
//		entityMgrObj.createNativeQuery("DELETE FROM jugador WHERE id = ?")
//		.setParameter(1, id)
//		.executeUpdate();
		
		CriteriaBuilder criteriaBuilderObj = entityMgrObj.getCriteriaBuilder();
		CriteriaDelete<Jugador> deleteQuery = criteriaBuilderObj.createCriteriaDelete(Jugador.class);
		Root<Jugador> from = deleteQuery.from(Jugador.class);
		deleteQuery.where(criteriaBuilderObj.equal(from.get("id"), id));
		Query query = entityMgrObj.createQuery(deleteQuery);
		query.executeUpdate();
		
	}
	
	@Transactional
	public void updateCriteria(Long id, Jugador jugador) {
		
//		entityMgrObj.createNativeQuery("UPDATE jugador SET (nombre, puntos) = (?,?) WHERE id = ?")
//		.setParameter(1, jugador.getNombre())
//		.setParameter(2, jugador.getPuntos())
//		.setParameter(3, id)
//		.executeUpdate();
		
		CriteriaBuilder criteriaBuilderObj = entityMgrObj.getCriteriaBuilder();
		CriteriaUpdate<Jugador> updateQuery = criteriaBuilderObj.createCriteriaUpdate(Jugador.class);
		Root<Jugador> from = updateQuery.from(Jugador.class);
		updateQuery.set("nombre", jugador.getNombre());
		updateQuery.set("puntos", jugador.getPuntos());
		updateQuery.where(criteriaBuilderObj.equal(from.get("id"), id));
		Query query = entityMgrObj.createQuery(updateQuery);
		query.executeUpdate();
		
	}
	
	@Transactional
	public List<Jugador> getTestCriteria() {	

		
		List<Jugador> jugadores =  entityMgrObj.createNativeQuery("SELECT * FROM jugador WHERE nombre=?", Jugador.class)
		.setParameter(1, "facu")
		.getResultList();
		
		return jugadores;
	}
	
	@Transactional
	public void postTestCriteria() {
		// TODO
	}
	
	@Transactional
	public void putTestCriteria() {
		// TODO
	}
	
	@Transactional
	public void deleteTestCriteria() {
		// TODO
	}
	

}
