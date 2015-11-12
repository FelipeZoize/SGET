package com.sgetejb.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaQuery;

public abstract class GenericDAO<T> {

	private final static String UNIT_NAME = "CRUD";
	
	@PersistenceContext(unitName = UNIT_NAME)
	private EntityManager em;
	
	private Class<T> entityClass;
	
	public GenericDAO(Class<T> entityClass){
		this.entityClass = entityClass;
	}
	
	public void save(T entity){
		em.persist(entity);
	}
	
	protected void delete(Object id,Class<T> classe ){
		T entityToBeRemoved = em.getReference(classe, id);
		
		em.remove(entityToBeRemoved);
	}
	
	public T update(T entity){
		return em.merge(entity);
	}
	
	public T find(int entityID){
		return em.find(entityClass, entityID);
	}
		
	@SuppressWarnings({"rawtypes","unchecked"})
	public List<T> findAll() {        
		CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
        cq.select(cq.from(entityClass));
        return em.createQuery(cq).getResultList();
    }

	public EntityManager getEm() {
		return em;
	}
	
	
	
}
