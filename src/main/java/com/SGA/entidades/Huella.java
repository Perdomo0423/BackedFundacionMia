package com.SGA.entidades;

import java.sql.Blob;

import javax.annotation.Generated;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="huella")
public class Huella {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(name="nombre")
	private String nombre;
	
	@Column(name="huella", length = 10000)
	private  Blob huella;

	public Huella(String nombre,  Blob huella) {
		super();
		this.nombre = nombre;
		this.huella = huella;
	}

	public Huella() {
		// TODO Auto-generated constructor stub
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public  Blob getHuella() {
		return huella;
	}

	public void setHuella(Object setBinaryStream) {
		// TODO Auto-generated method stub
		
	}



 




	
	
}
