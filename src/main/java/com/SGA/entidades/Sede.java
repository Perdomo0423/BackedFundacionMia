package com.SGA.entidades;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;


@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="sede")
public class Sede {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)

	@Column(name="id_sede")
	private Long idSede;

	@Column(name="sedConDane", unique = true)
	private Long codDane;

	@Column(name="sedConsecutivo", unique = true)
	private Long consecutivo;
	@NotEmpty(message="El campo no debe ser vacio")
	@Size(max=100, message = "El campo debe tener un maximo de 50 caracteres")

	@Column(name="sedNombre", unique = true)
	private String nombre;
	@NotEmpty(message="El campo no debe ser vacio")
	@Size(max=50, message = "El campo debe tener un maximo de 50 caracteres")

	@Column(name="sedZona")
	private String zona;
	@JoinColumn(name="idUsuario_Coordinador")

	@ManyToOne
	private Usuario coordinador;
	@JoinColumn(name="id_municipio")

	@ManyToOne
	private Municipio municipio;
	@JoinColumn(name="idInstitucion")

	@ManyToOne
	private Institucion unaInstitucion;

}
