package com.SGA.servicio;

import com.SGA.entidades.Pais;
import com.SGA.repositorio.PaisRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PaisService {

    @Autowired
    private PaisRepository paisrepository;

    public List<Pais> listarPais(){
        return paisrepository.findAll();
    }


}
