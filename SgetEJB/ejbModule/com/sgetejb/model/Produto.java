/**
 * 
 */
package com.sgetejb.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author FelipeZoize
 *
 */
@Entity
@Table(name="produto")
public class Produto {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	private String nome;
	private boolean venda;

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public boolean isVenda() {
		return venda;
	}
	public void setVenda(boolean venda) {
		this.venda = venda;
	}

	@Override
	public int hashCode() {
		return getId();
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Produto) {
			Produto produto = (Produto) obj;
			return produto.getId() == id;
		}

		return false;
	}


}
