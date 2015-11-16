package com.sgetejb.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="agenda")
public class Agenda {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	private String titulo; 
	private String descricao;
	private int aviso;
			
	@Column(name="data_cadastro")
	private Date dataCadastro;
	
	@Column(name="data_inicio")	
	private Date dataInicio;
	
	@Column(name="data_fim")
	private Date dataFim;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public int getAviso() {
		return aviso;
	}
	public void setAviso(int aviso) {
		this.aviso = aviso;
	}
	public Date getDataCadastro() {
		return dataCadastro;
	}
	public void setDataCadastro(Date dataCadastro) {
		this.dataCadastro = dataCadastro;
	}
	public Date getDataInicio() {
		return dataInicio;
	}
	public void setDataInicio(Date dataInicio) {
		this.dataInicio = dataInicio;
	}
	public Date getDataFim() {
		return dataFim;
	}
	public void setDataFim(Date dataFim) {
		this.dataFim = dataFim;
	}
		
}
