/**
 * 
 */
package com.sgetejb.dao;

import java.util.List;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.persistence.Query;

import com.sgetejb.model.TipoServico;
import com.sgetejb.model.Venda;

/**
 * @author FelipeZoize
 *
 */
@Stateless
@Local
public class VendaDAO extends GenericDAO<Venda>{

	public VendaDAO(){
		super(Venda.class);
	}
	
	public void delete(Venda venda){
		super.delete(venda.getId(), Venda.class);
	}	
	
	@SuppressWarnings("unchecked")
	public List<Venda> findVenda(String parameterToSearch){
		Query query = super.getEm().createQuery("SELECT v FROM Venda v INNER JOIN v.cliente c WHERE c.nome LIKE :parameterToSearch ORDER BY c.nome ASC ",Venda.class);
		query.setParameter("parameterToSearch", parameterToSearch+"%");
		return query.getResultList();
	}
	
	@SuppressWarnings("unchecked")
	public List<TipoServico> findServicos(){
		Query query = super.getEm().createQuery("SELECT ts FROM TipoServico  ts  ORDER BY ts.nome ASC ",TipoServico.class);
		return query.getResultList();
	}
}
