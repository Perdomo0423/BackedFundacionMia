package com.SGA.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.SGA.entidades.TipoDocumento;

 
@Repository
public interface TipoDocumentoRepository extends JpaRepository<TipoDocumento, Integer>{
	TipoDocumento findByCodigo(long codigo);
}
