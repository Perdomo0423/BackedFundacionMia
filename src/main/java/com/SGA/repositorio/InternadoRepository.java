package com.SGA.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;

import com.SGA.entidades.Internado;

public interface InternadoRepository extends JpaRepository<Internado, Long> {
	
	Internado findByCodigo(Long codigo);

}
