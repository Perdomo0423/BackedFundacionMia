package com.SGA.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;

import com.SGA.entidades.PobVicConf;

public interface PobVicConfRepository extends JpaRepository<PobVicConf, Long> {
	
	PobVicConf findByCodigo(Long codigo);

}
