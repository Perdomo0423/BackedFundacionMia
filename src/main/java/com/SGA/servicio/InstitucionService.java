package com.SGA.servicio;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.SGA.entidades.Institucion;
import com.SGA.entidades.Secretaria;

@Repository
public interface InstitucionService{ 
	public Iterable<Institucion> findAll();
	
	public Optional<Institucion> findById(Long id); 
	
	public Institucion save(Institucion institucion);
	
	public void deleteById(Long id); 
	
	List<Institucion> listarInstitucion(Long id_municipio);


}