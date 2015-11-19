/**
 * 
 */
package com.sgetejb.dao;

import java.util.List;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.persistence.Query;

import com.sgetejb.model.Cliente;
import com.sgetejb.model.Produto;

/**
 * @author FelipZoize
 *
 */

@Stateless
@Local
public class ProdutoDAO extends GenericDAO<Produto>{

	public ProdutoDAO(){
		super(Produto.class);
	}

	public void delete(Produto produto){
		super.delete(produto.getId(), Produto.class);
	}

	@SuppressWarnings("unchecked")
	public List<Produto> findProdutos(String parameterToSearch){
		Query query = super.getEm().createQuery("SELECT p FROM Produto p WHERE nome LIKE :parameterToSearch ORDER BY p.nome ASC ",Produto.class);
		query.setParameter("parameterToSearch", parameterToSearch+"%");
		return query.getResultList();
	}

	public Produto findi(int entityID){
		return getEm().find(Produto.class, entityID);
	}
	public  Produto findProduto(int id) {
		Query query = super.getEm().createQuery("select p from Produto p where p.id = :id");
		query.setParameter("id", id);
		return (Produto) query.getSingleResult();
	}
}
