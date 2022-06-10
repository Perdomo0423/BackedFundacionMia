package com.SGA.dto;

import com.SGA.entidades.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StandarEstudianteDto {
    private Long numeroDocumento;
    private String nombre1;
    private String nombre2;
    private String apellido1;
    private String apellido2;
    private String direccionRecidencia;
    private String telefono;
    private String municipioRecidencia;
    private String fechaNacimiento;
    private String nacimientoDepartamento;
    private String nacimientoMunicipio;
    private String genero;
    private String acudiente;
    private String telefonoAcudiente;
    private String facial;
    private String huella;
    private Date fechaCreacion;
    private Institucion unaInstitucion;
    private Sede unaSede;
    private Municipio unMunicipio;
    private TipoDocumento unTipoDocumento;
    private TipoDiscapacidad unaDiscapacidad;
    private Etnia unaEtnia;
    private Jornada unaJornada;
    private String grado;
    private Pais paisOrigen;
    private Usuario director;
}
