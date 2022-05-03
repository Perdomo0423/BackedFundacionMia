package com.SGA.repositorio;

import com.SGA.entidades.Sede;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.SGA.entidades.Beneficiario;

import java.sql.Date;
import java.util.List;

@Repository
public interface BeneficiarioRepository extends JpaRepository<Beneficiario, Long>{
	boolean existsByUnEstudianteNumeroDocumento(long numeroDocumento);
	Beneficiario findByUnEstudianteNumeroDocumento(long numeroDocumento);


	@Query(value = "select * from beneficiario where ben_fecha_actual between :fecha1 and :fecha2", nativeQuery = true)
	List<Beneficiario> listRangoFecha(@Param("fecha1")Date fecha1, @Param("fecha2")Date fecha2);

}
