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
import javax.persistence.Table;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="tipodiscapacidad")
public class TipoDiscapacidad {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)

	@Column(name="idTipoDiscapacidad")
	private Long idTipoDiscapacidad;

	@Column(name="tipCodigo")
	private Long codigo;

	@Column(name="tipNombre")
	private String nombre;

}
