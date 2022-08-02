package com.SGA.servicio.Implemt;

import com.SGA.repositorio.DepartamentoRepository;
import com.SGA.servicio.DepartamentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartamentoServiceImp implements DepartamentoService {

    @Autowired
    private DepartamentoRepository departamentoRepository;

    @Override
    public List<com.SGA.entidades.Departamento> findAll() {
        return departamentoRepository.findAll();
    }
}
