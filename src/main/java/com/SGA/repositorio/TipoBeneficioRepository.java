package com.SGA.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;

import com.SGA.entidades.TipoBeneficio;

public interface TipoBeneficioRepository extends JpaRepository<TipoBeneficio, Long>{
	TipoBeneficio findByNombre(String nombre);
}
