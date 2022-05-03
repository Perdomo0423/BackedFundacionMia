package com.SGA.servicio;

import com.SGA.entidades.Jornada;
import com.SGA.repositorio.JornadaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JornadaService {

    @Autowired

    private JornadaRepository jornadarepository;

    public List<Jornada> listarJornada(){
        return jornadarepository.findAll();
    }
}
