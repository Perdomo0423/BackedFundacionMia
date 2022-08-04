package com.SGA.entidades;

import java.sql.Blob;

import javax.annotation.Generated;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="huella")
public class Huella {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name="huella_estudiante", length = 100000)
	private  Blob huella;
	
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_estudiante", referencedColumnName = "numero_Documento")
	private Estudiante numeroDocumento;
	
	public Huella() {
		// TODO Auto-generated constructor stub
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}


	public  Blob getHuella() {
		return huella;
	}

	public void setHuella(Object setBinaryStream) {
		// TODO Auto-generated method stub
		
	}



 




	
	
}
