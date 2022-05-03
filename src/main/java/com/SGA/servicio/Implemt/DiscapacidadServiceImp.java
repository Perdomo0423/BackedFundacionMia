package com.SGA.servicio.Implemt;

import com.SGA.entidades.TipoDiscapacidad;
import com.SGA.repositorio.TipoDiscapacidadRepository;
import com.SGA.servicio.DiscapacidadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DiscapacidadServiceImp implements DiscapacidadService {

    @Autowired
    private TipoDiscapacidadRepository tipoDiscapacidadRepository;

    @Override
    public List<TipoDiscapacidad> findAll() {
        return tipoDiscapacidadRepository.findAll();
    }
}
