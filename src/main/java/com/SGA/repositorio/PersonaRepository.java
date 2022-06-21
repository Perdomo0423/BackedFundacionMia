package com.SGA.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;


import com.SGA.entidades.Persona;


public interface PersonaRepository extends JpaRepository<Persona, Long>{

}
