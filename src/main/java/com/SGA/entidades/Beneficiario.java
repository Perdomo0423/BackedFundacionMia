package com.SGA.entidades;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "beneficiario")
public class Beneficiario implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) // le decimos que va a ser de tipo autoincrementable
	private Long idBeneficiario;

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "numero_Documento", referencedColumnName = "numero_Documento")
	private Estudiante numeroDocumento;
	


	@Column(name = "nombre_estrategia")
	private String nombreEstrategia;

	@Column(name = "tipo_estrategia")
	private String tipoEstrategia;

	@Column(name = "estrategia_subtipo")
	private String estrategiaSubtipo;

	@Column(name = "fecha_inicio_estrategia")
	private Date fechaInicioEstrategia;

	@Column(name = "fecha_fin_estrategia")
	private Date fechaFinEstrategia;

	@Column(name = "fecha_inicio_estrategia_alumno")
	private Date fechaInicioEstrategiaAlumno;

	@Column(name = "fecha_fin_estrategia_alumno")
	private Date fechaFinEstrategiaAlumno;

}
