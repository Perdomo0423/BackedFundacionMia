package com.SGA.servicio;

import java.util.List;
import java.util.Optional;

import com.SGA.entidades.OtroSi;

public interface OtroSiService {
	
	public List<OtroSi> all();
	
	public Optional<OtroSi> findById(Long id);
	
	public OtroSi save(OtroSi otro);
	
	public void delete(Long id);
}
