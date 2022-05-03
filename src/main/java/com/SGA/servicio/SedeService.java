package com.SGA.servicio;


import com.SGA.entidades.Sede;

import java.util.List;

public interface SedeService {

    List<Sede> listarSedes1(Long id_municipio, String sed_zona);


    public List<Sede> findAll();
}
