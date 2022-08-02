package com.SGA.servicio;

import java.util.List;
import java.util.Optional;

import com.SGA.entidades.Rol;

public interface RolService {
	
	public List<Rol> all();
	
	public Optional<Rol> findById(Long id);
	
	public Rol save(Rol rol);
	
	public void delete(Long id);
}
