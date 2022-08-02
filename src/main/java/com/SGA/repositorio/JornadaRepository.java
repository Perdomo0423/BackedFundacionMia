package com.SGA.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.SGA.entidades.Institucion;
import com.SGA.entidades.Jornada;
 

@Repository
public interface JornadaRepository extends JpaRepository<Jornada, Long>  {

	Jornada findByCodigo(Long codigo);
}