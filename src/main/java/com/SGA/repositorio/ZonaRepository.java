package com.SGA.repositorio;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.SGA.entidades.Estudiante;
import com.SGA.entidades.Zona;

public interface ZonaRepository extends JpaRepository<Zona, Long>{
	
	@Query(value = "select * from zonas where id_secretaria = :id_secretaria", nativeQuery = true)
	List<Zona> listZonaSecretaria(@Param("id_secretaria") Long id_secretaria);
	
}
