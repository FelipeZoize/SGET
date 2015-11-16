package com.sgetejb.dao;

import java.util.List;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.persistence.Query;

import com.sgetejb.model.Cliente;

@Stateless
@Local
public class ClienteDAO extends GenericDAO<Cliente> {

	public ClienteDAO() {
		super(Cliente.class);
	}

	public void delete(Cliente cliente){
		super.delete(cliente.getId(), Cliente.class);
	}
	
	@SuppressWarnings("unchecked")
	public List<Cliente> findClientes(String parameterToSearch){
		Query query = super.getEm().createQuery("SELECT c FROM Cliente c WHERE nome LIKE :parameterToSearch ORDER BY c.nome ASC ",Cliente.class);		query.setParameter("parameterToSearch", parameterToSearch+"%");
		return query.getResultList();
	}
	
	private void isClienteWithAllData(Cliente cliente){
		boolean hasError = false;
		
		if(cliente == null){
			hasError = true;
		}
		
		if(cliente.getNome() == null || cliente.getNome().trim().isEmpty()){
			hasError = true;
		}		
		
		if(hasError){
			throw new IllegalArgumentException("Falta informações no Cliente!");
		}
		
		
	}
}
