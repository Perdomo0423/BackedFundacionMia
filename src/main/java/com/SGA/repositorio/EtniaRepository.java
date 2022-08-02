package com.SGA.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.SGA.entidades.Etnia;

@Repository
public interface EtniaRepository extends JpaRepository<Etnia, Long>{
	Etnia findByCodigo(Long codigo);
}
