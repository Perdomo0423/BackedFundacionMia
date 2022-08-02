package com.SGA.servicio;

import java.sql.Date;
import java.util.List;
import java.util.Optional;


import com.SGA.entidades.Persona;
import com.SGA.entidades.Sede;
import com.SGA.entidades.Usuario;
import org.apache.el.lang.ELArithmetic;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.SGA.entidades.Beneficiario;

@Repository
@Service
public interface BeneficiarioService{ 

    public List<Beneficiario> findAll();

    List<Beneficiario> listarRangoFecha(Date fecha1, Date fecha2);

    public Beneficiario obtenerBeneficiariosPorId(Long id);

    public void guardarBeneficiarios (Beneficiario beneficiario);

    Beneficiario save(Beneficiario beneficiario);
}