package com.SGA.repositorio;

import lombok.val;
import org.springframework.data.jpa.repository.JpaRepository;

import com.SGA.entidades.Secretaria;
import com.SGA.entidades.Sede;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

public interface SedeRepository extends JpaRepository<Sede, Long>{
	
	boolean existsByCodDane(Long codigoDane);
	
	Sede findByCodDane(Long codDane);
	
	Sede findByConsecutivo(Long consecutivo);

	@Query(value = "select * from sede where id_institucion = :id_institucion", nativeQuery = true)
	List<Sede> listSede( @Param("id_institucion") Long id_institucion);
	
//	@Query(value = "select distinct sed_zona from sede ",nativeQuery = true )
//	List<Sede> listSedeBymunicipio(@Param("sed_zona") String sed_zona);

}
