/**
 * 
 */
package com.sget.mb;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import com.sget.utils.JSFMessageUtil;
import com.sgetejb.dao.ProdutoDAO;
import com.sgetejb.model.Produto;

/**
 * @author FelipeZoize
 *
 */

@ManagedBean
@SessionScoped
public class ProdutoMB {

	public static String STAY_IN_THE_SAME_PAGE = "estoque";

	@EJB
	private ProdutoDAO produtoDAO;

	private Produto produto;

	private JSFMessageUtil jsfMessageUtil = new JSFMessageUtil();

	public String createProduto(){
		try {
			produtoDAO.save(produto);
		} catch (EJBException e) {
			jsfMessageUtil.sendErrorMessageToUser("Erro, verificar se todos os campos estão corretos!");			
		}

		jsfMessageUtil.sendInfoMessageToUser("Operação Realizada com Sucesso");

		this.setProduto(null);
		 
		return STAY_IN_THE_SAME_PAGE+"?faces-redirect=true";
	}
	
	public List<Produto> findProdutos(String parameterToSearch){
		return produtoDAO.findProdutos(parameterToSearch);		
		
	}
	
	public Produto findProduto(int id){
		return produtoDAO.find(id);
	}
	
	public List<Produto> findAllProdutos(){
		return produtoDAO.findAll();
	}

	public Produto getProduto() {
		if(produto == null){
			produto = new Produto();
			produto.setVenda(false);
		}
		return produto;

	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}


}
