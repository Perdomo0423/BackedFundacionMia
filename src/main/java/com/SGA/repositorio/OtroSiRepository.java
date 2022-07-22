package com.SGA.repositorio;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.SGA.entidades.OtroSi;
import com.SGA.entidades.Secretaria;

public interface OtroSiRepository extends JpaRepository<OtroSi, Long> {
	
	@Query(value = "select * from otro_si where id_contratista = :id_contratista ", nativeQuery = true)
	List<OtroSi> listOtroSi( @Param("id_contratista") Long id_contratista);

	
}
