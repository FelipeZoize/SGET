/**
 * 
 */
package com.sget.mb;

import java.util.ArrayList;
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
import com.sgetejb.dao.EstoqueDAO;
import com.sgetejb.model.Cliente;
import com.sgetejb.model.Estoque;
import com.sgetejb.model.Produto;

/**
 * @author FelipeZoize
 *
 */
@ManagedBean
@SessionScoped
public class EstoqueMB {
	
	public static String STAY_IN_THE_SAME_PAGE = "estoque";

	@EJB
	private EstoqueDAO estoqueDAO;
	
	@ManagedProperty( value = "#{currentStateMB}")
	private CurrentStateMB currentStateMB;
	
	@ManagedProperty( value = "#{produtoMB}")
	private ProdutoMB produtoMB;
	
	private Estoque estoque;
	private List<Estoque> listEstoque;	
	private List<Produto> listProduto;
	
	private JSFMessageUtil jsfMessageUtil = new JSFMessageUtil();
	
	@PostConstruct
	public void init(){
		this.listProduto = produtoMB.findAllProdutos();
	}


	public String createEstoque(){
		try {
			estoqueDAO.save(estoque);
		} catch (EJBException e) {
			jsfMessageUtil.sendErrorMessageToUser("Erro, verificar se todos os campos estão corretos!");			
		}

		jsfMessageUtil.sendInfoMessageToUser("Operação Realizada com Sucesso");

		this.setEstoque(null);
		
		return STAY_IN_THE_SAME_PAGE;

		//findAllClientes();
	}
	
	public List<Produto> produtoAutoComplete(String parameterToSearch){
		return produtoMB.findProdutos(parameterToSearch);
	}
	public List<Produto> autocomplete(String query) { 
        
        List<Produto> results = new ArrayList<Produto>();  
          
        for (Produto e : listProduto) { 
            if (e.getNome().toLowerCase().startsWith(query.toLowerCase())) {
                results.add(e);
            }
        }            
        return results;  
        
    }
	
	public String prepareFormCadastro(){
		setEstoque(null);
		currentStateMB.setCurrentState(CurrentStateIF.ADD_STATE);
		return STAY_IN_THE_SAME_PAGE;

	}
	public void findAllEstoque(){		
		this.setListEstoque(estoqueDAO.findAll());
		
		currentStateMB.setCurrentState(CurrentStateIF.LIST_STATE);
	}
	public String prepareList(){
		//findAll();
		return STAY_IN_THE_SAME_PAGE;
	}

	public Estoque getEstoque() {
		if (estoque == null){
			estoque = new Estoque();
		}
		return estoque;
	}

	public void setEstoque(Estoque estoque) {
		this.estoque = estoque;
	}
	public List<Estoque> getListEstoque() {
		return listEstoque;
	}
	public void setListEstoque(List<Estoque> listEstoque) {
		this.listEstoque = listEstoque;
	}
	
	
	public List<Produto> getListProduto() {
		return listProduto;
	}
	public void setListProduto(List<Produto> listProduto) {
		this.listProduto = listProduto;
	}
	public void setCurrentStateMB(CurrentStateMB currentStateMB) {
		this.currentStateMB = currentStateMB;
	}
	public void setProdutoMB(ProdutoMB produtoMB) {
		this.produtoMB = produtoMB;
	}

}
