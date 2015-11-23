/**
 * 
 */
package com.sgetejb.dao;

import javax.ejb.Local;
import javax.ejb.Stateless;

import com.sgetejb.model.TipoServico;

/**
 * @author FelipeZoize
 *
 */
@Stateless
@Local
public class TipoServicoDAO extends GenericDAO<TipoServico>{

	public TipoServicoDAO() {
		super(TipoServico.class);
		
	}

}
