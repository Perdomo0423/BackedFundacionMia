package com.SGA.controlador;

import com.SGA.entidades.Etnia;
import com.SGA.servicio.EtniaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/etnia")
public class EtniaController {

    @Autowired

    private EtniaService etniaservice;
    @GetMapping("/listar")
    public List<Etnia> listarEtnia(){
        return etniaservice.listarEtnia();
    }
}
