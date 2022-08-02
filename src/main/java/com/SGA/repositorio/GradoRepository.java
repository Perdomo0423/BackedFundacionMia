package com.SGA.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;

import com.SGA.entidades.Grado;

public interface GradoRepository extends JpaRepository<Grado, Long> {
	
	Grado findByCodigo(Long codigo);

}
