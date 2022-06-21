package com.SGA.servicio;

import java.util.List;
import java.util.Optional;

import com.SGA.entidades.*;

public interface IVentaService {

	public List<Venta> all();
	
	public Optional<Venta> findById(Long id);
	
	public Venta save(Venta venta);
	
	public void delete(Long id);
}
