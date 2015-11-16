package com.sget.mb;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import com.sget.utils.CurrentStateIF;
import com.sget.utils.CurrentStateMB;
import com.sget.utils.JSFMessageUtil;
import com.sgetejb.dao.AgendaDAO;
import com.sgetejb.model.Agenda;

@ManagedBean
@SessionScoped
public class AgendaMB {
	
	public static String STAY_IN_THE_SAME_PAGE = "agenda";

	@EJB
	private AgendaDAO agendaDAO;
	
	@ManagedProperty( value = "#{currentStateMB}")
	private CurrentStateMB currentStateMB;	
	
	private JSFMessageUtil jsfMessageUtil = new JSFMessageUtil();
	
	private Agenda agenda;
	
	private List<Agenda> listAgenda;
			
	/** parametros para busca de clientes **/
	private String searchParameter;	
		
	@PostConstruct	
	public void init(){
	}	
	
	public void createAgenda(){
		try {
			agendaDAO.save(agenda);
		} catch (EJBException e) {
			jsfMessageUtil.sendErrorMessageToUser("Erro, verificar se todos os campos estão corretos!");			
		}

		jsfMessageUtil.sendInfoMessageToUser("Operação Realizada com Sucesso");

		this.setAgenda(null);

		findAllAgenda();
	}
	public void updateAgenda(){
		try {
			agendaDAO.update(agenda);
		} catch (EJBException e) {
			jsfMessageUtil.sendErrorMessageToUser("Erro, verificar se todos os campos estão corretos!");
		}

		jsfMessageUtil.sendInfoMessageToUser("Operação Realizada com Sucesso");
		currentStateMB.setCurrentState(CurrentStateIF.ADD_STATE);

		findAllAgenda();
	}

	public void findAllAgenda(){
		currentStateMB.setCurrentState(CurrentStateIF.LIST_STATE);
		this.setListAgenda(agendaDAO.findAll());
	}

	public void findAgenda(){
		listAgenda = agendaDAO.findAgenda(searchParameter);
	}

	public void deleteAgenda(){
		try {
			agendaDAO.delete(agenda);
		} catch (EJBException e) {
			jsfMessageUtil.sendErrorMessageToUser("Erro ao deletar o cliente!");

		}

		jsfMessageUtil.sendInfoMessageToUser("Operação Realizada com Sucesso");
		findAllAgenda();		
	}
	
	public String prepareFormCadastro(){
		setAgenda(null);
		currentStateMB.setCurrentState(CurrentStateIF.ADD_STATE);
		return STAY_IN_THE_SAME_PAGE;

	}
	public String prepareList(){
		findAllAgenda();
		return STAY_IN_THE_SAME_PAGE;
	}
	
	public Agenda getAgenda(){
		if(agenda == null){
			agenda = new Agenda();
		}
		return agenda;
		
	}
	
	public void setAgenda(Agenda agenda){
		this.agenda = agenda;
	}
	
	public List<Agenda> getListAgenda() {
		return listAgenda;
	}

	public void setListAgenda(List<Agenda> listAgenda) {
		this.listAgenda = listAgenda;
	}
	public String getSearchParameter() {
		return searchParameter;
	}

	public void setSearchParameter(String searchParameter) {
		this.searchParameter = searchParameter;
	}
		
	public void setCurrentStateMB(CurrentStateMB currentStateMB) {
		this.currentStateMB = currentStateMB;
	}
}
