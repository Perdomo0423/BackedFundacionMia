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
@Table(name="beneficiario")
public class Beneficiario<contadorBeneficios> implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY) //le decimos que va a ser de tipo autoincrementable

	@Column(name="idBeneficiaro")
	private Long idBeneficiario;

	@Column(name = "contadorBeneficios")
	private String contadorBeneficios;

	@Column(name="benFechaActual")
	@NotNull
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "yyyy-mm-dd")
	private Date fechaActual;

	@Column(name="benFechaModificacion")
	@NotNull
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "yyyy-mm-dd")
	private Date fechaModificacion;

	@Column(length = 50, name="benEstado")
	private String estado;

	@OneToOne
	@JoinColumn(name="idEstudiante")
	private Estudiante unEstudiante;

	@ManyToOne
	@JoinColumn(name="idTipoBeneficio")
	private TipoBeneficio tipoBeneficio;

}
