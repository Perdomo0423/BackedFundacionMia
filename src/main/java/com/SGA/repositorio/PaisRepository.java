package com.SGA.repositorio;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.SGA.entidades.Pais;

@Repository
public interface PaisRepository extends JpaRepository<Pais, Long>{
	boolean existsByCodigo(int codigo);
	
	Pais findByCodigo(int codigo);
}
