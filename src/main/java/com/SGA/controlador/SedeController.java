package com.SGA.controlador;

import com.SGA.entidades.Estudiante;
import com.SGA.entidades.Sede;
import com.SGA.servicio.SedeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/sede")
public class SedeController {

    @Autowired
    private SedeService sedeservice;

    @GetMapping("/listar")
    public List<Sede> listarSede(){
        return sedeservice.findAll();
    }
    
    @GetMapping(value ={"/institucion/{id_institucion}"})
	public List<Sede> listarSede(@PathVariable("id_institucion") Long id_institucion){
		return sedeservice.listarSede(id_institucion);
	}
}
