package com.SGA.servicio;

import com.SGA.entidades.Etnia;
import com.SGA.repositorio.EtniaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EtniaService {

    @Autowired
    private EtniaRepository etniarepository;

    public List<Etnia> listarEtnia(){
        return etniarepository.findAll();
    }
}
