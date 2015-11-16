package com.sgetejb.dao;

import java.util.List;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.persistence.Query;

import com.sgetejb.model.Agenda;

@Stateless
@Local
public class AgendaDAO extends GenericDAO<Agenda> {

	public AgendaDAO(){
		super(Agenda.class);
	}
	
	public void delete(Agenda agenda){
		super.delete(agenda.getId(), Agenda.class);
	}
	
	@SuppressWarnings("unchecked")
	public List<Agenda> findAgenda(String parameterToSearch){
		Query query = super.getEm().createQuery("SELECT a FROM Agenda a WHERE titulo LIKE :parameterToSearch ORDER BY a.titulo ASC ",Agenda.class);
		query.setParameter("parameterToSearch", parameterToSearch+"%");
		return query.getResultList();
	}
	

}
