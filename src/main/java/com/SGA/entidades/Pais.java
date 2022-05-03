package com.SGA.entidades;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Getter
@Setter
@Entity
@Table(name="pais")
public class Pais {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idPais;

	@Column(name="paiCodigo", unique = true)
	private int codigo;

	@Column(name="paiNombre", unique = true)
	private String nombre;


	public Pais() {
		super();
	}
	public Pais(int codigo, String nombre) {
		super();
		this.codigo = codigo;
		this.nombre = nombre;
	}
	
}
