package com.SGA.servicio.Implemt;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

import com.SGA.entidades.Beneficiario;
import com.SGA.entidades.Sede;
import com.SGA.entidades.Usuario;
import com.SGA.repositorio.BeneficiarioRepository;
import com.SGA.servicio.BeneficiarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

 
@Service
public class BeneficiarioImp implements BeneficiarioService {

	@Autowired
	private BeneficiarioRepository beneficiarioRepository;

	@Override
	public List<Beneficiario> findAll() {
		return beneficiarioRepository.findAll();
	}

//	@Override
//	public List<Beneficiario> listarRangoFecha(Date fecha1, Date fecha2) {
//		return beneficiarioRepository.listRangoFecha(fecha1, fecha2);
//	}

	@Override
	public Beneficiario obtenerBeneficiariosPorId(Long id) {
		return beneficiarioRepository.findById(id).get();
	}

	@Override
	public void guardarBeneficiarios(Beneficiario beneficiario) {
		beneficiarioRepository.save(beneficiario);
	}

	@Override
	public Beneficiario save(Beneficiario beneficiario) {
		return beneficiarioRepository.save(beneficiario);
	}

	@Override
	public List<Beneficiario> listarRangoFecha(Date fecha1, Date fecha2) {
		// TODO Auto-generated method stub
		return null;
	}
}
