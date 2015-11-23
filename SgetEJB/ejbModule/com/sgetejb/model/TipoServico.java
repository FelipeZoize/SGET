/**
 * 
 */
package com.sgetejb.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * @author FelipeZoize
 *
 */
@Entity
@Table(name="tipo_servico")
@SequenceGenerator(name = TipoServico.TIPO_SERVICO_SEQUENCE_NAME, sequenceName = TipoServico.TIPO_SERVICO_SEQUENCE_NAME, initialValue = 3, allocationSize = 53)
public class TipoServico {
	
    public static final String TIPO_SERVICO_SEQUENCE_NAME = "TIPO_SERVICO_SEQUENCE_ID";
	
	@Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = TIPO_SERVICO_SEQUENCE_NAME)
	private long id;
	
	private String nome;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	
	

}
