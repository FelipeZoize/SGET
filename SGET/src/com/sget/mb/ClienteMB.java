package com.sget.mb;

import java.io.IOException;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import com.sgetejb.dao.ClienteDAO;
import com.sgetejb.model.Cliente;

@ManagedBean
@SessionScoped
public class ClienteMB {

	@EJB
	private ClienteDAO clienteDAO;

	private static final String ADD_STATE = "adicionar";
	private static final String UPDATE_STATE = "atualizar";
	private static final String LIST_STATE = "listar";
	
	private static final String STAY_IN_THE_SAME_PAGE = null;
	private static final String FORM_CLIENTE = "createCliente";

	/** diz qual o estado da pagina **/
	private String currentState = ADD_STATE;  	

	private Cliente cliente;
	private List<Cliente> listClientes;

	/** parametros para busca de clientes **/
	private String searchParameter;

	@PostConstruct	
	public void init(){
		//listClientes = clienteDAO.findAll();
	}	

	public void createCliente(){
		try {
			clienteDAO.save(cliente);
		} catch (EJBException e) {
			sendErrorMessageToUser("Erro, verificar se todos os campos estão corretos!");			
		}

		sendInfoMessageToUser("Operação Realizada com Sucesso");

		this.setCliente(null);

		findAllClientes();
	}

	public void updateCliente(){
		try {
			clienteDAO.update(cliente);
		} catch (EJBException e) {
			sendErrorMessageToUser("Erro, verificar se todos os campos estão corretos!");
		}

		sendInfoMessageToUser("Operação Realizada com Sucesso");

		//cliente = null;
		this.setCurrentState(ADD_STATE);

		findAllClientes();
	}

	public void findAllClientes(){
		this.setCurrentState(LIST_STATE);
		this.setListClientes(clienteDAO.findAll());
	}

	public void findClientes(){
		listClientes = clienteDAO.findClientes(searchParameter);
	}

	public void deleteCliente(){
		try {
			clienteDAO.delete(cliente);
		} catch (EJBException e) {
			sendErrorMessageToUser("Erro ao deletar o cliente!");

		}

		sendInfoMessageToUser("Operação Realizada com Sucesso");
		findAllClientes();
		
	}

	public void prepareUpdate(){
		this.setCurrentState(UPDATE_STATE);
	}

	public void sendInfoMessageToUser(String message){
		FacesContext context = getContext();
		context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,message,message));
	}
	public void sendErrorMessageToUser(String message){
		FacesContext context = getContext();
		context.addMessage(null, new FacesMessage (FacesMessage.SEVERITY_ERROR, message, message));
	}

	public FacesContext getContext(){
		FacesContext context = FacesContext.getCurrentInstance();
		return context;
	}

	public Cliente getCliente() {
		if (cliente == null){
			cliente = new Cliente();
		}
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	
	public List<Cliente> getListClientes() {
		return listClientes;
	}

	public void setListClientes(List<Cliente> listClientes) {
		this.listClientes = listClientes;
	}

	public String getSearchParameter() {
		return searchParameter;
	}

	public void setSearchParameter(String searchParameter) {
		this.searchParameter = searchParameter;
	}

	public boolean isAddState() {  
		return ADD_STATE.equals(this.getCurrentState());  
	}  
	public boolean isUpdateState() {  
		return UPDATE_STATE.equals(this.getCurrentState());  
	}
	public boolean isListState() {  
		return LIST_STATE.equals(this.getCurrentState());  
	}
	
	public String getCurrentState() {
		return currentState;
	}

	public void setCurrentState(String currentState) {
		this.currentState = currentState;
	}
	public void mudar(){
		this.setCurrentState(LIST_STATE);
	}

}
