package com.SGA.entidades;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="departamento")
public class Departamento implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)

	@Column(name="idDeparmento")
	private Long idDepartamento;

	@Column(name="depCodigo", unique=true)
	private int codigo;
	@NotEmpty(message = "El campo nombre es requerido")

	@Column(name="depNombre")
	private String nombre;

	@ManyToOne
	@JoinColumn(name="idPais")
	private Pais unPais;

}
