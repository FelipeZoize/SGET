package com.sget.mb;

import javax.annotation.ManagedBean;
import javax.ejb.EJB;
import javax.faces.bean.RequestScoped;

import com.sgetejb.dao.AgendaDAO;
import com.sgetejb.model.Agenda;

@ManagedBean
@RequestScoped
public class AgendaMB {
	
	@EJB
	AgendaDAO agendaDAO;
	Agenda agenda;
	
	public Agenda getAgenda(){
		if(agenda == null){
			agenda = new Agenda();
		}
		return agenda;
		
	}
	
	public void setAgenda(Agenda agenda){
		this.agenda = agenda;
	}
	

}
