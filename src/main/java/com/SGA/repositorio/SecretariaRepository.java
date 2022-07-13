package com.SGA.repositorio;

import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.SGA.entidades.Secretaria;

@Repository
public interface SecretariaRepository extends JpaRepository<Secretaria, Long> {
	
	@Query(value = "select * from secretaria where id_departamento = :id_departamento ", nativeQuery = true)
	List<Secretaria> listSecretaria( @Param("id_departamento") Long id_departamento);

}
