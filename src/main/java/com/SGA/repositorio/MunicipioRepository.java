package com.SGA.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;

import com.SGA.entidades.Municipio;

public interface MunicipioRepository extends JpaRepository<Municipio, Long>{
	
	Municipio findByNombre(String nombre);
	
	Municipio findByCodigoAndUnDepartamentoCodigo(int codigo, int codigoDep);
	
	Municipio findByNombreAndUnDepartamentoCodigo(String municipio, int codigoDep);

	boolean existsByCodigo(int codigo);
	
	boolean existsByCodigoAndUnDepartamentoCodigo(int codigo, int codigoDep);
}
