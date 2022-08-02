package com.SGA.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.SGA.entidades.TipoDiscapacidad;

@Repository
public interface TipoDiscapacidadRepository extends JpaRepository<TipoDiscapacidad, Long>{
	TipoDiscapacidad findByCodigo(Long codigo);
}
