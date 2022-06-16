package com.SGA.servicio;

import java.util.List;
import java.util.Optional;

import com.SGA.entidades.Contratista;

public interface ContratistaService {
	
	public List<Contratista> all();
	
	public Optional<Contratista> findById(Long nit);
	
	public Contratista save(Contratista contratista);
	
	public void delete(Long nit);
}
