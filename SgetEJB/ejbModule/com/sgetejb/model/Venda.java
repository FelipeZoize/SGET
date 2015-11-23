/**
 * 
 */
package com.sgetejb.model;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * @author FelipeZoize
 *
 */
@Entity
@Table(name="venda")
public class Venda {
	
	public Venda(){
		this.tipoServico = new TipoServico();
	}

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
	
	@Column(name="data_venda")
	private Date dataVenda;
	
	private double valorTotal;
	
	@ManyToOne
    @JoinColumn(name="cliente_id")
	private Cliente cliente;
	
	@Transient
	private Estoque estoque;
	
	@ManyToMany
	@JoinTable(name="item_venda",joinColumns=@JoinColumn(name="venda_id"), inverseJoinColumns = @JoinColumn(name="estoque_id"))
	private List<Estoque> estoques;
	
	@OneToOne
	@JoinColumn(name="tipo_servico_id")
	private TipoServico tipoServico;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Date getDataVenda() {
		return dataVenda;
	}

	public void setDataVenda(Date dataVenda) {
		this.dataVenda = dataVenda;
	}

	public double getValorTotal() {
		return valorTotal;
	}

	public void setValorTotal(double valorTotal) {
		this.valorTotal = valorTotal;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Estoque getEstoque() {
		return estoque;
	}

	public void setEstoque(Estoque estoque) {
		this.estoque = estoque;
	}

	public List<Estoque> getEstoques() {
		return estoques;
	}

	public void setEstoques(List<Estoque> estoques) {
		this.estoques = estoques;
	}

	public TipoServico getTipoServico() {
		return tipoServico;
	}

	public void setTipoServico(TipoServico tipoServico) {
		this.tipoServico = tipoServico;
	}

	
	
}
