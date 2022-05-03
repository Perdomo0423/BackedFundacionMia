package com.SGA.entidades;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="etnia")
public class Etnia {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="idEtnia")
	private Long idEtnia;
	@Column(name="etCodigo")
	private Long codigo;
	@Column(name="etNombre", unique = true)
	private String nombre;
	public Long getIdEtnia() {
		return idEtnia;
	}
	public void setIdEtnia(Long idEtnia) {
		this.idEtnia = idEtnia;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Etnia() {
		super();
	}
	public Long getCodigo() {
		return codigo;
	}
	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}
	public Etnia(Long codigo, String nombre) {
		super();
		this.codigo = codigo;
		this.nombre = nombre;
	}
	
	
}
