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
import com.sgetejb.dao.ClienteDAO;
import com.sgetejb.model.Cliente;

@ManagedBean
@SessionScoped
public class ClienteMB {
	
	public static String STAY_IN_THE_SAME_PAGE = "cliente";

	@EJB
	private ClienteDAO clienteDAO;
	
	@ManagedProperty( value = "#{currentStateMB}")
	private CurrentStateMB currentStateMB;
	
	private JSFMessageUtil jsfMessageUtil = new JSFMessageUtil();
	private Cliente cliente;
	private List<Cliente> listClientes;	

	/** parametros para busca de clientes **/
	private String searchParameter;

	@PostConstruct	
	public void init(){
		//currentStateMB.setCurrentState(CurrentStateIF.ADD_STATE);
		//listClientes = clienteDAO.findAll();
	}	
	
	public void createCliente(){
		try {
			clienteDAO.save(cliente);
		} catch (EJBException e) {
			jsfMessageUtil.sendErrorMessageToUser("Erro, verificar se todos os campos estão corretos!");			
		}

		jsfMessageUtil.sendInfoMessageToUser("Operação Realizada com Sucesso");

		this.setCliente(null);

		findAllClientes();
	}

	public void updateCliente(){
		try {
			clienteDAO.update(cliente);
		} catch (EJBException e) {
			jsfMessageUtil.sendErrorMessageToUser("Erro, verificar se todos os campos estão corretos!");
		}

		jsfMessageUtil.sendInfoMessageToUser("Operação Realizada com Sucesso");
		
		currentStateMB.setCurrentState(CurrentStateIF.ADD_STATE);
		
		findAllClientes();
	}

	public void findAllClientes(){		
		this.setListClientes(clienteDAO.findAll());
		
		currentStateMB.setCurrentState(CurrentStateIF.LIST_STATE);
	}

	public void findClientes(){
		listClientes = clienteDAO.findClientes(searchParameter);
	}

	public void deleteCliente(){
		try {
			clienteDAO.delete(cliente);
		} catch (EJBException e) {
			jsfMessageUtil.sendErrorMessageToUser("Erro ao deletar o cliente!");
		}

		jsfMessageUtil.sendInfoMessageToUser("Operação Realizada com Sucesso");
		findAllClientes();
		
	}
	
	public String prepareFormCadastro(){
		setCliente(null);
		currentStateMB.setCurrentState(CurrentStateIF.ADD_STATE);
		return STAY_IN_THE_SAME_PAGE;

	}
	public String prepareList(){
		findAllClientes();
		return STAY_IN_THE_SAME_PAGE;
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

	public void setCurrentStateMB(CurrentStateMB currentStateMB) {
		this.currentStateMB = currentStateMB;
	}

}
