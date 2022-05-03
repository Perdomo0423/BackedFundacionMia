package com.SGA.controlador;

import com.SGA.entidades.Jornada;
import com.SGA.servicio.JornadaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/jornada")
public class JornadaController {

    @Autowired

    private JornadaService jornadaservice;

    @GetMapping("/listar")
    public List<Jornada> listarJornada(){
        return jornadaservice.listarJornada();
    }
}
