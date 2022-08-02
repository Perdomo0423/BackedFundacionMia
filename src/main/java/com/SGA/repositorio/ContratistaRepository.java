package com.SGA.repositorio;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.SGA.entidades.Contratista;
import com.SGA.entidades.Secretaria;

public interface ContratistaRepository extends JpaRepository<Contratista, Long> {
	
	@Query(value = "select * from contratista where id_zona = :id_zona ", nativeQuery = true)
	List<Contratista> listZona( @Param("id_zona") Long id_zona);
//	
}
