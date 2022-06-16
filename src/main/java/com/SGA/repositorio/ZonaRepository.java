package com.SGA.repositorio;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.SGA.entidades.Zona;

public interface ZonaRepository extends JpaRepository<Zona, Long>{
	
}
