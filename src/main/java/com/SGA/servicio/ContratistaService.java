package com.SGA.servicio;

import java.util.List;
import java.util.Optional;

import com.SGA.entidades.Contratista;
import com.SGA.entidades.Secretaria;
import com.SGA.entidades.Zona;

public interface ContratistaService {
	
	public List<Contratista> all();
	
	public Optional<Contratista> findById(Long nit);
	
	public Contratista save(Contratista contratista);
	
	public void delete(Long nit);
	
	List<Contratista> listarZona(Long id_zona);
}
