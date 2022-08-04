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

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "otro_si")
public class OtroSi implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
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
	
	@Column(name = "fecha_inicio_otro",  length = 100, nullable = false)
	private String fechaInicioOtro;
	
 	@Column(name = "dias_atender", nullable = false)
	private int diasAtender;

 	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "nit_contratista", referencedColumnName = "nit")
	private Contratista contratista;

	
	


}
