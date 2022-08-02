package com.SGA.entidades;


import java.io.Serializable;
import java.util.Date;
import java.util.Optional;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.lang.NonNull;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity //le decimos a java que es una entidad
@Table(name="persona") // para que nos cree la tabla user en la base de datos
public class Persona implements Serializable {
	
	private static final long serialVersionUID = 3499113360580787796L;
	@Id 
	@GeneratedValue(strategy= GenerationType.IDENTITY) //le decimos que va a ser de tipo autoincrementable

	@Column(name="idPersona")
	private Long idPersona;
	
	@ManyToOne(fetch = FetchType.EAGER, optional = false)
	@JoinColumn(name = "id_tipo_dumento", nullable = false)
	private TipoDocumento unTipoDocumento;
	
	
	@NotEmpty(message="El campo no debe ser vacio")
	@Size(max=50, message = "El campo debe tener un maximo de 10 caracteres")

	@Column(length = 50, name="perNumeroDocumento")
	private String numeroDocumento;
	@NotEmpty(message="El campo no debe ser vacio")

	@Column(length = 50, name="perNombre")
	private String nombre;
	@NotEmpty(message="El campo no debe ser vacio")
	@Size(max=50, message = "El campo debe tener un maximo de 50 caracteres")

	@Column(length = 50, name="perApellido")
	private String apellido;
	@NotEmpty(message="El campo no debe ser vacio")
	
	@Column(length = 50,name="perFechaNacimiento")
	private String fechaNacimiento;

	@ManyToOne()
	@JoinColumn(name="idMunicipio_LugarNacimimiento" )
	private Municipio lugarDeNacimiento;
	@NotNull 
	@Size(max=50, message = "El campo debe tener un maximo de 50 caracteres")

	@Column(length = 10, name="perTelefono")
	private String telefono;
	@NotNull 
	@Size(max=50, message = "El campo debe tener un maximo de 50 caracteres")

	@Column(length = 50, name="perDireccion")
	private String direccion;
	@NotNull 
	@Size(max=50, message = "El campo debe tener un maximo de 50 caracteres")

	@Column(length = 50, name="perBarrio")
	private String barrio;

	@Column(length = 50, name="perFechaCreacion")
	private String fechaCreacion;

	@Column(length = 50, name="perFechaModificacion")
	private String fechaModificacion;

	@Column(length = 50, name="perEstado")
	private String estado;

}
