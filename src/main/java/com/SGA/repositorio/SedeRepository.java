package com.SGA.repositorio;

import lombok.val;
import org.springframework.data.jpa.repository.JpaRepository;

import com.SGA.entidades.Sede;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

public interface SedeRepository extends JpaRepository<Sede, Long>{
	
	boolean existsByCodDane(Long codigoDane);
	
	Sede findByCodDane(Long codDane);
	
	Sede findByConsecutivo(Long consecutivo);

	@Query(value = "SELECT * FROM sede where id_municipio = :id_municipio and sed_zona = :sed_zona ",nativeQuery = true)
	List<Sede> listSedeByIdmunicipio(@Param("id_municipio")Long id_municipio, @Param("sed_zona") String sed_zona);

//	@Query(value = "select distinct sed_zona from sede ",nativeQuery = true )
//	List<Sede> listSedeBymunicipio(@Param("sed_zona") String sed_zona);

}
