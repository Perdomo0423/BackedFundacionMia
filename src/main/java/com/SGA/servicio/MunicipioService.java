package com.SGA.servicio;

import com.SGA.entidades.Municipio;
import com.SGA.entidades.Secretaria;
import com.SGA.repositorio.MunicipioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MunicipioService {

    @Autowired
    private MunicipioRepository municipiorepository;
    


    public List<Municipio> listarMunicipio(){
        return municipiorepository.findAll();
    }
    
	
	

}
