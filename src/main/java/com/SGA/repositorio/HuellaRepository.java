package com.SGA.repositorio;


import java.io.ByteArrayInputStream;
import java.sql.Blob;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.SGA.entidades.Huella;

public interface HuellaRepository extends JpaRepository<Huella, Long>{
  /*
    @Query("select huella FROM huella WHERE nombre=?1")
  */ 
  Optional<Huella> findByHuella(String username);
    
//    Optional<Huella>  findByNombreAndHuella(String nombre, Blob huella);

    List<Huella> findAll();
}
