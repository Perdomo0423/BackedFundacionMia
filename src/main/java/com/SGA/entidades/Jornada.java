package com.SGA.entidades;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.annotation.Generated;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="jornada")
public class Jornada {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)

	@Column(name="idJornada")
	private Long idJornada;

	@Column(name="jorCodigo")
	private Long codigo;

	@Column(name="nombre", unique = true)
	@NotEmpty(message ="El campo no debe de ser vacio")
	private String nombre;

}
