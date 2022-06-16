package com.SGA.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;

import com.SGA.entidades.Contratista;

public interface ContratistaRepository extends JpaRepository<Contratista, Long> {
	
}
