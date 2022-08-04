package com.SGA.entidades;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import org.apache.poi.ss.usermodel.Cell;
import org.hibernate.annotations.NaturalId;
import org.springframework.format.annotation.DateTimeFormat;

import io.swagger.models.auth.In;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;


@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "estudiante")
public class Estudiante implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idEstudainte;

	@NaturalId
	@Column(name="numero_documento", unique = true)
	private String  numeroDocumento;

    @Column(name = "ano_inf")
    private int anoInf;

    @Column(name = "mun_codigo")
    private int munCodigo;
   
    
    @Column(name = "municipio")
    private String municipio;
    
    @Column(name = "codigo_dane")
    private int codigoDane;
    
    @Column(name = "cons_sede")
    private int consSede;
    
    @Column(name = "res_depto")
    private int resDepto;
    
    @Column(name = "res_mun")
    private int resMun;
    
    @Column(name = "estrato")
    private int estrato;
    
    
    @Column(name = "res")
    private int res;
    
    @Column(name = "grupo")
    private int grupo;
    
    @Column(name = "estNombre1")
    private String nombre1;

    @Column(name = "est_nombre2")
    private String nombre2;

    @Column(name = "estApellido1")
    private String apellido1;
 
    @Column(name = "estApellido2")
    private String apellido2;

    @Column(name = "estDireccionRecidencia")
    private String direccionRecidencia;
    
    @Column(name = "sisben_IV",length = 100)
    private String sisben;

    @Column(name = "estTelefono",length = 100)
    private String telefono;

    @Column(name = "estFechaNacimiento")
    private Date fechaNacimiento;

    @Column(name = "estGenero")
    private String genero;
    
    @Column(name = "institucion")
    private String institucion;
    
    @Column(name = "sede")
    private String sede;
    
    @OneToOne(fetch = FetchType.EAGER, optional = true)
    @JoinColumn(name = "id_especialidad", nullable = true)
	private Especialidad  idEspecialidad;
    
    
    @OneToOne(fetch = FetchType.EAGER, optional = true)
    @JoinColumn(name = "id_caracter", nullable = true)
	private Caracter  idCaracter;
    
    @OneToOne(fetch = FetchType.EAGER, optional = true)
    @JoinColumn(name = "id_internado", nullable = true)
	private Internado  idInternado;
    
    @OneToOne(fetch = FetchType.EAGER, optional = true)
    @JoinColumn(name = "id_grado", nullable = true)
	private Grado  idGrado;
    
    @OneToOne(fetch = FetchType.EAGER, optional = true)
    @JoinColumn(name = "id_tipo_documento", nullable = true)
	private TipoDocumento  idTipoDocumento;
    
    @OneToOne(fetch = FetchType.EAGER, optional = true)
    @JoinColumn(name = "id_discapacidad", nullable = true)
	private TipoDiscapacidad  idDiscapacidad;
    
    @OneToOne(fetch = FetchType.EAGER, optional = true)
    @JoinColumn(name = "id_etnia", nullable = true)
	private Etnia  idEtnia;
    
    @OneToOne(fetch = FetchType.EAGER, optional = true)
    @JoinColumn(name = "id_jornada", nullable = true)
	private Jornada  idJornada;
    
    @OneToOne(fetch = FetchType.EAGER, optional = true)
    @JoinColumn(name = "id_pob_vic_conf", nullable = true)
	private PobVicConf  idPobVicConf;

    
    
   
}