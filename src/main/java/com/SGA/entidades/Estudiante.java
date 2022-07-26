
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
    private static final long serialVersionUID = 1L;
    
    @Id
    private Long numeroDocumento;
    
   
    
    @Column(name = "ano_inf")
    private String anoInf;
    
    
    @Column(name = "mun_codigo")
    private String munCodigo;
    
    @Column(name = "municipio")
    private String municipio;
    
    @Column(name = "codigo_dane")
    private String codigoDane;
    
    @Column(name = "cons_sede")
    private String consSede;
    
    @Column(name = "res_depto")
    private String resDepto;
    
    @Column(name = "res_mun")
    private String resMun;
    
    @Column(name = "estrato")
    private String estrato;
    
    @Column(name = "sisben_IV",length = 10)
    private String sisben;
    
    @Column(name = "pob_vict_conf")
    private String pobVictConf;
    
    @Column(name = "res")
    private String res;
    
    @Column(name = "caracter")
    private String caracter;
    
    @Column(name = "especialidad",length = 50)
    private String especialidad;
    
    @Column(name = "grupo")
    private String grupo;
    
    @Column(name = "codigo_internado")
    private String codigoInternado;
    

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


    @Column(name = "estFechaNacimiento")
    private String fechaNacimiento;



    @Column(name = "estGenero")
    private String genero;


    @Column(name = "fechaCreacio")
    @NotNull
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-mm-dd")
    private Date fechaCreacion;
    
    
    @Column(name = "id_institucion")
    private String idInstitucion;
    
    @Column(name = "id_tipo_documento")
    private String idTipoDocumento;
    
    @Column(name = "id_discapcidad")
    private String idDiscapacidad;
    
    @Column(name = "id_etnia")
    private String idEtnia;
    
    @Column(name = "id_jornada")
    private String idJornada;
    
    @Column(name = "estGrado")
    private String grado;
    
   
}
