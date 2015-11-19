
package com.sgetejb.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * @author FelipeZoize
 *
 */
@Entity
@Table(name="estoque")
public class Estoque {
	
//	public Estoque(){
//		this.produto = new Produto();
//		this.produto.setVenda(false);
//	}

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	
	@OneToOne
    @JoinColumn(name="produto_id")
	private Produto produto;
	
	private int quantidade;	
	
	@Column(name="qtd_vendidos")
	private int qtdVendidos;
	
	@Column(name="preco_compra")
	private Double precoCompra;
	
	@Column(name="preco_venda")
	private Double precoVenda;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Produto getProduto() {
		return produto;
	}
	public void setProduto(Produto produto) {
		this.produto = produto;
	}
	public int getQuantidade() {
		return quantidade;
	}
	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}
	public int getQtdVendidos() {
		return qtdVendidos;
	}
	public void setQtdVendidos(int qtdVendidos) {
		this.qtdVendidos = qtdVendidos;
	}
	public Double getPrecoCompra() {
		return precoCompra;
	}
	public void setPrecoCompra(Double precoCompra) {
		this.precoCompra = precoCompra;
	}
	public Double getPrecoVenda() {
		return precoVenda;
	}
	public void setPrecoVenda(Double precoVenda) {
		this.precoVenda = precoVenda;
	}
	
	
	
}
