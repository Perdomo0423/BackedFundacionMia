package com.SGA.servicio.Implemt;

import com.SGA.entidades.Sede;
import com.SGA.repositorio.SedeRepository;
import com.SGA.servicio.SedeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SedeServiceImpl implements SedeService {

    @Autowired
    private SedeRepository sederepository;


//    @Override
//    public List<Sede> listarSedes1(Long id_municipio, String sed_zona) {
//        return sederepository.listSedeByIdmunicipio(id_municipio, sed_zona);
//    }

    @Override
    public List<Sede> findAll() {
        return sederepository.findAll();
    }


	@Override
	public List<Sede> listarSede(Long id_institucion) {
		return sederepository.listSede(id_institucion);

	}





}
