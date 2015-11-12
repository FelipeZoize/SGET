package com.sgetejb.dao;

import javax.ejb.Stateless;

import com.sgetejb.model.Agenda;

@Stateless
public class AgendaDAO extends GenericDAO<Agenda> {

	public AgendaDAO(){
		super(Agenda.class);
	}
	

}
