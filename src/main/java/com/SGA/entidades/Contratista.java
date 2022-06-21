package com.SGA.entidades;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "contratista")
public class Contratista implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	private Long nit;

	
	@ManyToOne(fetch = FetchType.EAGER, optional = false)
	@JoinColumn(name = "id_zona", nullable = false)
	private Zona idZona;
	
	
	@Column(name = "nombre_zona", length = 30, nullable = false)
	private String nombreZona;
	
	@Column(name = "representante_legal", length = 30, nullable = false)
	private String representanteLegal;
	
	@Column(name = "numero_documento", length = 30, nullable = false)
	private int numeroDocumento;
	
	@Column(name = "numero_contrato", length = 30, nullable = false)
	private int numeroContrato;
	
	
	
	
	@Column(name = "fecha_inicio",  length = 20, nullable = false)
	private Date fechaInicio;
	
	@Column(name = "fecha_suscripcion",  length = 20, nullable = false)
	private Date fechaSuscripcion;
	
//	@Column(name = "fecha_suscripcion", length = 30, nullable = false)
//	private Date fechaSuscripcion;
//	
//	@Column(name = "fecha_inicio", length = 30, nullable = false)
//	private Date fechaInicio;
//	
	@Column(name = "cantidad_complemento", length = 30, nullable = false)
	private int cantidadComplemento;
	 
	@Column(name = "costo_complemento", length = 30, nullable = false)
	private int costoComplemento;
	
	@Column(name = "cantidad_almuerzo", length = 30, nullable = false)
	private int cantidadAlmuerzo;
	
	@Column(name = "costo_almuerzo", length = 30, nullable = false)
	private int costoAlmuerzo;
	
	@Column(name = "cantidades_diarias", length = 30, nullable = false)
	private int cantidadesDiarias;
	
	@Column(name = "dias_atender", length = 30, nullable = false)
	private int diasAtender;
	
	
	
	
}
