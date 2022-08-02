package com.SGA.repositorio;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.SGA.entidades.Municipio;
import com.SGA.entidades.Secretaria;

public interface MunicipioRepository extends JpaRepository<Municipio, Long>{
	
	Municipio findByNombre(String nombre);
	
	Municipio findByCodigoAndUnDepartamentoCodigo(int codigo, int codigoDep);
	
	Municipio findByNombreAndUnDepartamentoCodigo(String municipio, int codigoDep);

	boolean existsByCodigo(int codigo);
	
	boolean existsByCodigoAndUnDepartamentoCodigo(int codigo, int codigoDep);

	@Query(value = "SELECT * FROM municipio where id_zona= :id_zona ", nativeQuery = true)
	List<Municipio> listMunicipio( @Param("id_zona") Long id_secretaria);
}
