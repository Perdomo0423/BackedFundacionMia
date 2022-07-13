package com.SGA.servicio;



import com.SGA.entidades.Secretaria;
import com.SGA.entidades.Sede;

import java.util.List;

public interface SedeService {


//    List<Estudiante> listarGenero(String est_genero, Long id_sede);

    public List<Sede> findAll();
    List<Sede> listarSede(Long id_institucion);
}
