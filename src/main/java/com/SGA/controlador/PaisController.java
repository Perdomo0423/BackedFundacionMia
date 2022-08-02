package com.SGA.controlador;

import com.SGA.entidades.Pais;
import com.SGA.servicio.PaisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/paises")
public class PaisController {

    @Autowired
    private PaisService paisservice;

    @GetMapping("/listar")
    public List<Pais> listarPais(){
        return paisservice.listarPais();
    }
}
