package com.SGA.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.SGA.entidades.Institucion;
 
@Repository
public interface InstitucionRepository extends JpaRepository<Institucion, Long>  {
	boolean existsByCodDane(Long codDane);
	Institucion findByCodDane(Long codDane);
}
