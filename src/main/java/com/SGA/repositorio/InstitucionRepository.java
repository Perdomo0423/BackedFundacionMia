package com.SGA.repositorio;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.SGA.entidades.Institucion;
import com.SGA.entidades.Secretaria;
 
@Repository
public interface InstitucionRepository extends JpaRepository<Institucion, Long>  {
	boolean existsByCodDane(Long codDane);
	Institucion findByCodDane(Long codDane);
	
	@Query(value = "select * from  institucion where id_municipio= :id_municipio", nativeQuery = true)
	List<Institucion> listInstitucion( @Param("id_municipio") Long id_municipio);
}
