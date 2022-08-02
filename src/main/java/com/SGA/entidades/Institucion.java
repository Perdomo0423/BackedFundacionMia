package com.SGA.entidades;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="institucion")
public class Institucion {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)

	@Column(name="idInstitucion")
	private Long idInstitucion;

	@Column(name="insCodDane")
	private Long  codDane;
	@NotEmpty(message="El campo no debe ser vacio")

	@Column(name="insNombre")
	@Size(max=200, message = "El campo debe tener un maximo de 50 caracteres")
	private String nombre;

	@Column(name="insNaturaleza")
	@Size(max=50, message = "El campo debe tener un maximo de 50 caracteres")
	private String naturaleza;

	@ManyToOne
	@JoinColumn(name="insRector")
	private Usuario rector;

	@ManyToOne
	@JoinColumn(name="idDepartamento")
    private Departamento departamento;

	@ManyToOne
	@JoinColumn(name="idMunicipio")
    private Municipio unMunicipio;

	@Column(length = 50, name="insFechaCreacion")
	private String fechaCreacion;

	@Column(length = 50, name="insFechaModificacion")
	private String fechaModificacion;

	@Column(length = 50, name="insEstado")
	private String estado;
}
