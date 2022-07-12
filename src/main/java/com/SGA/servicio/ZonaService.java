package com.SGA.servicio;

import java.util.List;
import java.util.Optional;

import com.SGA.entidades.Estudiante;
import com.SGA.entidades.Zona;

public interface ZonaService {
	
	public List<Zona> all();
	
	public Optional<Zona> findById(Long id);
	
	public Zona save(Zona rol);
	
	public void delete(Long id);
	
	List<Zona> listarSecretaria(Long id_secretaria);
}
