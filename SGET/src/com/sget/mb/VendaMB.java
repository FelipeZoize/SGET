/**
 * 
 */
package com.sget.mb;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import com.sget.utils.CurrentStateIF;
import com.sget.utils.CurrentStateMB;
import com.sget.utils.JSFMessageUtil;
import com.sgetejb.dao.VendaDAO;
import com.sgetejb.model.Cliente;
import com.sgetejb.model.Estoque;
import com.sgetejb.model.Produto;
import com.sgetejb.model.TipoServico;
import com.sgetejb.model.Venda;

/**
 * @author FelipeZoize
 *
 */
@ManagedBean
@SessionScoped
public class VendaMB {

	public static String STAY_IN_THE_SAME_PAGE = "venda";
	
	@EJB
	private VendaDAO vendaDAO;

	@ManagedProperty( value = "#{currentStateMB}")
	private CurrentStateMB currentStateMB;
	
	@ManagedProperty( value = "#{clienteMB}")
	private ClienteMB clienteMB;
	
	@ManagedProperty( value = "#{estoqueMB}")
	private EstoqueMB estoqueMB;

	private Venda venda;
	private List<Venda> listVenda;	
	
	private List<Cliente> listCliente;	
	private List<Estoque> listEstoque;
	private List<TipoServico> listTipoServico;
    private Map<String,Integer> tipoServico;
    private String tipo;

	
	private JSFMessageUtil jsfMessageUtil = new JSFMessageUtil();

	private String searchParameter;
	
	@PostConstruct
	public void init(){
		tipoServico  = new HashMap<String,Integer>();
		tipoServico.put("Produto",1);
		tipoServico.put("Tatuagem",2);
		this.listCliente = clienteMB.findAll();
		this.listEstoque = estoqueMB.findAll();
		this.listTipoServico = vendaDAO.findServicos();
	}
	
	public void createVenda(){
		try {
			venda.setDataVenda(new Date());
			venda.getTipoServico().setId(Integer.parseInt(tipo));
			vendaDAO.save(venda);
		} catch (EJBException e) {
			jsfMessageUtil.sendErrorMessageToUser("Erro, verificar se todos os campos estão corretos!");			
		}

		jsfMessageUtil.sendInfoMessageToUser("Operação Realizada com Sucesso");

		this.setVenda(null);

		findAllVendas();
	}

	public void updateVenda(){
		try {
			vendaDAO.update(venda);
		} catch (EJBException e) {
			jsfMessageUtil.sendErrorMessageToUser("Erro, verificar se todos os campos estão corretos!");
		}

		jsfMessageUtil.sendInfoMessageToUser("Operação Realizada com Sucesso");
		
		currentStateMB.setCurrentState(CurrentStateIF.ADD_STATE);
		
		findAllVendas();
	}
	public void deleteVenda(){
		try {
			vendaDAO.delete(venda);
		} catch (EJBException e) {
			jsfMessageUtil.sendErrorMessageToUser("Erro ao deletar o cliente!");
		}

		jsfMessageUtil.sendInfoMessageToUser("Operação Realizada com Sucesso");
		findAllVendas();
		
	}

	public String prepareFormCadastro(){
		this.setVenda(null);
		currentStateMB.setCurrentState(CurrentStateIF.ADD_STATE);
		return STAY_IN_THE_SAME_PAGE;
	}
	public String prepareList(){
		findAllVendas();
		return STAY_IN_THE_SAME_PAGE;
	}
	public String prepareIndex(){
		currentStateMB.setCurrentState(CurrentStateIF.ADD_STATE);
		setVenda(null);
		return "index?faces-redirect=true";
	}
	
	public void findAllVendas(){		
		this.setListVenda(vendaDAO.findAll());
		
		currentStateMB.setCurrentState(CurrentStateIF.LIST_STATE);
	}
	public void findVenda(){
		listVenda = vendaDAO.findVenda(searchParameter);
	}

	public List<Cliente> autocomplete(String query) { 

		List<Cliente> results = new ArrayList<Cliente>();  

		for (Cliente e : listCliente) { 
			if (e.getNome().toLowerCase().startsWith(query.toLowerCase())) {
				results.add(e);
			}
		}            
		return results;  

	}

	public List<Produto> autocompleteEstoque(String query) { 

		List<Produto> results = new ArrayList<Produto>();  

		for (Estoque e : listEstoque) { 
			if (e.getProduto().getNome().toLowerCase().startsWith(query.toLowerCase())) {
				results.add(e.getProduto());
			}
		}            
		return results;  

	}
	public Venda getVenda() {
		if(venda == null){
			venda = new Venda();
		}
		return venda;
	}

	public void setVenda(Venda venda) {
		this.venda = venda;
	}

	public List<Venda> getListVenda() {
		return listVenda;
	}

	public void setListVenda(List<Venda> listVenda) {
		this.listVenda = listVenda;
	}

	public List<Cliente> getListCliente() {
		return listCliente;
	}
	public void setListCliente(List<Cliente> listCliente) {
		this.listCliente = listCliente;
	}
	public List<Estoque> getListEstoque() {
		return listEstoque;
	}

	public void setListEstoque(List<Estoque> listEstoque) {
		this.listEstoque = listEstoque;
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

	public void setClienteMB(ClienteMB clienteMB) {
		this.clienteMB = clienteMB;
	}

	public void setEstoqueMB(EstoqueMB estoqueMB) {
		this.estoqueMB = estoqueMB;
	}

	public List<TipoServico> getListTipoServico() {
		return listTipoServico;
	}

	public void setListTipoServico(List<TipoServico> listTipoServico) {
		this.listTipoServico = listTipoServico;
	}

	public Map<String,Integer> getTipoServico() {
		return tipoServico;
	}

	public void setTipoServico(Map<String,Integer> tipoServico) {
		this.tipoServico = tipoServico;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	
	public String stay(){
		return "venda";
	}

}
