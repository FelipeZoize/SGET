/**
 * 
 */
package com.sgetejb.dao;

import java.util.List;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.persistence.Query;

import com.sgetejb.model.Estoque;

/**
 * @author FelipeZoize
 *
 */
@Stateless
@Local
public class EstoqueDAO extends GenericDAO<Estoque> {
	
	public EstoqueDAO(){
		super(Estoque.class);
	}

	public void delete(Estoque estoque){
		super.delete(estoque.getId(), Estoque.class);
	}
	
	@SuppressWarnings("unchecked")
	public List<Estoque> findEstoque(String parameterToSearch){
		Query query = super.getEm().createQuery("SELECT e FROM Estoque e INNER JOIN e.produto p WHERE p.nome LIKE :parameterToSearch ORDER BY p.nome ASC ",Estoque.class);		query.setParameter("parameterToSearch", parameterToSearch+"%");
		return query.getResultList();
	}
}
