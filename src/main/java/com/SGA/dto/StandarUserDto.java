package com.SGA.dto;

import com.SGA.entidades.Departamento;
import com.SGA.entidades.Municipio;
import com.SGA.entidades.Persona;
import com.SGA.entidades.Rol;
import com.SGA.entidades.TipoDocumento;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StandarUserDto {
    private String username;
    private String email;
    private Set<Rol> roles = new HashSet<>();
    private Persona unaPersona;
    private TipoDocumento unTipoDocumento;
    private Departamento unDepartamento;
    private Municipio unMunicipio;
}
