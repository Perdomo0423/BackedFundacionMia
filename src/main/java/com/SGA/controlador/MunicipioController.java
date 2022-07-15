package com.SGA.controlador;

import com.SGA.entidades.Estudiante;
import com.SGA.entidades.Municipio;
import com.SGA.servicio.MunicipioFiltroService;
import com.SGA.servicio.MunicipioService;
import com.SGA.servicio.PaisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/municipios")
public class MunicipioController {

    @Autowired
    private MunicipioService municipioservice;
    
    @Autowired
    private MunicipioFiltroService municipioService;

    @GetMapping("/listar")
    public List<Municipio> listarMunicipio(){
        return municipioservice.listarMunicipio();
    }
    
    @GetMapping(value ={"/zona/{id_zona}"})
	public List<Municipio> listarMunici(@PathVariable("id_zona") Long id_zona){
		return municipioService.listarMunici(id_zona);
	}

}
