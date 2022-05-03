package com.SGA.entidades;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

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
public class Estudiante implements Serializable {

    private static final long serialVersionUID = 2780027400134309881L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idEstudiante")
    private Long idEstudiante;

    @Column(name = "estNumeroDocumento")
    private Long numeroDocumento;

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

    @Column(name = "estTelefono")
    private String telefono;

    @Column(name = "estMunicipioRecidencia")
    private String municipioRecidencia;

    @Column(name = "estFechaNacimiento")
    private String fechaNacimiento;

    @Column(name = "estNacimientoDepartamento")
    private String nacimientoDepartamento;

    @Column(name = "estNacimientoMunicipio")
    private String nacimientoMunicipio;

    @Column(name = "estGenero")
    private String genero;

    @Column(name = "estAcudiente")
    private String acudiente;

    @Column(name = "estTelefonoAcudiente")
    private String telefonoAcudiente;

    @Column(name="reco_Facial")
    private String facial;

    @Column(name="reco_Huella")
    private String huella;

    @Column(name = "fechaCreacio")
    @NotNull
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-mm-dd")
    private Date fechaCreacion;


    @ManyToOne
    @JoinColumn(name = "idInstitucion")
    private Institucion unaInstitucion;

    @ManyToOne
    @JoinColumn(name = "idSede")
    private Sede unaSede;

    @ManyToOne
    @JoinColumn(name = "idMunicipio")
    private Municipio unMunicipio;


    @ManyToOne
    @JoinColumn(name = "idTipoDocumento")
    private TipoDocumento unTipoDocumento;


    @ManyToOne
    @JoinColumn(name = "idTipoDiscapacidad")
    private TipoDiscapacidad unaDiscapacidad;

    @ManyToOne
    @JoinColumn(name = "idEtnia")
    private Etnia unaEtnia;

    @ManyToOne()
    @JoinColumn(name = "idJornada")
    private Jornada unaJornada;

    @Column(name = "estGrado")
    private String grado;


    @ManyToOne
    @JoinColumn(name = "idPais")
    private Pais paisOrigen;

    @ManyToOne
    @JoinColumn(name = "idDirector")
    private Usuario director;


}