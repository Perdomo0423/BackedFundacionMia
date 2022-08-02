package com.SGA.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;

import com.SGA.entidades.Especialidad;

public interface EspecialidadRepositroy extends JpaRepository<Especialidad, Long> {
	
	Especialidad findByCodigo(Long codigo);

}
