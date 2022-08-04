package com.SGA.entidades;

import java.io.Serializable;
import java.util.Date;

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

import org.hibernate.annotations.NaturalId;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "contratista")
public class Contratista implements Serializable {
	
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idContratista;
	
	
	@NaturalId
	@Column(name="nit", unique = true)
	private String  nit;
	
	@Column(name = "nombre_zona", length = 100, nullable = false)
	private String nombreZona;
	
	@Column(name = "representante_legal", length = 100, nullable = false)
	private String representanteLegal;
	
	@Column(name = "numero_documento", length = 100, nullable = false)
	private int numeroDocumento;
	
	@Column(name = "numero_contrato", length = 100, nullable = false)
	private int numeroContrato;
	
	@Column(name = "fecha_inicio", nullable = false)
	private Date fechaInicio;
	
	@Column(name = "fecha_suscripcion",nullable = false)
	private Date fechaSuscripcion;
	
	@Column(name = "cantidad_complemento_am", length = 100, nullable = false)
	private int cantidadComplementoAm;
	 
	@Column(name = "costo_complemento_am", length = 100, nullable = false)
	private int costoComplementoAm;
	
	@Column(name = "cantidad_complemento_pm", length = 100, nullable = false)
	private int cantidadComplementoPm;
	 
	@Column(name = "costo_complemento_pm", length = 100, nullable = false)
	private int costoComplementoPm;
	
	@Column(name = "cantidad_almuerzo", length = 100, nullable = false)
	private int cantidadAlmuerzo;
	
	@Column(name = "costo_almuerzo", length = 100, nullable = false)
	private int costoAlmuerzo;
	
	@Column(name = "cantidad_desayuno", length = 100, nullable = false)
	private int cantidadDesayuno;
	
	@Column(name = "costo_desayuno", length = 100, nullable = false)
	private int costoDesayuno;
	
	@Column(name = "cantidad_comida", length = 100, nullable = false)
	private int cantidadComida;
	
	@Column(name = "costo_comida", length = 100, nullable = false)
	private int costoComida;
	
	@Column(name = "cantidad_bono", length = 100, nullable = false)
	private int cantidadBono;
	
	@Column(name = "costo_bono", length = 100, nullable = false)
	private int costobono;
	
	@Column(name = "cantidad_racion_transportada_caliente", length = 100, nullable = false)
	private int cantidadRacionCaliente;
	
	@Column(name = "costo_racion_transportada_caliente", length = 100, nullable = false)
	private int costoRacionCaliente;
	
	@Column(name = "cantidad_racion_industrializada", length = 100, nullable = false)
	private int cantidadRacionIndustrializada;
	
	@Column(name = "costo_racion_industrializada", length = 100, nullable = false)
	private int costoRacionIndustrializada;
	
	@Column(name = "cantidad_racion_casa", length = 100, nullable = false)
	private int cantidadRacionRacionCasa;
	
	@Column(name = "costo_racion_casa", length = 100, nullable = false)
	private int costoRacionCasa;
	
	@Column(name = "dias_atender", length = 100, nullable = false)
	private int diasAtender;
	
	@Column(name = "cantidades_diarias", length = 100, nullable = false)
	private int cantidadesDiarias;
	
	@Column(name = "estado", nullable = false)
	private String estado;

	
	@OneToOne(fetch = FetchType.EAGER, optional = false)
	@JoinColumn(name = "id_persona", nullable = false)
	private Persona idPersona;
	
	@OneToOne(fetch = FetchType.EAGER, optional = false)
	@JoinColumn(name = "id_zona", nullable = false)
	private Zona idZona;
	
	
	
}
