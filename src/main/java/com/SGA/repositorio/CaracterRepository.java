package com.SGA.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;

import com.SGA.entidades.Caracter;

public interface CaracterRepository extends JpaRepository<Caracter, Long>{
	
	Caracter findByCodigo(Long codigo);

}
