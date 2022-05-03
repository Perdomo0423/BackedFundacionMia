
package com.SGA.controlador;


import java.sql.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import com.SGA.entidades.Beneficiario;
import com.SGA.entidades.Persona;
import com.SGA.entidades.Sede;
import com.SGA.servicio.BeneficiarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
 



@RestController

@RequestMapping("/api/beneficiario")

public class BeneficiarioController {

    @Autowired
    private BeneficiarioService beneficiarioService;

    @GetMapping(value = {"/listar"})
    public List<Beneficiario> listarSede(){
        return beneficiarioService.findAll();
    }


    @GetMapping(value = {"/listar/{id}"})
    public ResponseEntity<Beneficiario> obetenerBeneficiario (@PathVariable Long id){
        try {
            Beneficiario beneficiario = beneficiarioService.obtenerBeneficiariosPorId(id);
            return new ResponseEntity<Beneficiario>(beneficiario, HttpStatus.OK);
        }catch (Exception exception){
            return new ResponseEntity<Beneficiario>(HttpStatus.NOT_FOUND);
        }
    }



    @GetMapping(value = {"/listarFecha/{fecha1}/{fecha2}"})
    public List<Beneficiario> listarRangoFecha(@PathVariable("fecha1")Date fecha1, @PathVariable("fecha2") Date fecha2){
        System.out.println(fecha1+" "+fecha2);
        return beneficiarioService.listarRangoFecha(fecha1 , fecha2);
    }

//    @PostMapping("/crearBeneficiario")
//    public void save(@RequestBody Beneficiario beneficiario){
//        beneficiarioService.save(beneficiario);
//    }

    @PutMapping(value = {"/actualizar/{id}"})
    public ResponseEntity<?> actualizarBeneficiarios(@RequestBody Beneficiario beneficiario, @PathVariable Long id){
        try{
            Beneficiario beneficiario1 = beneficiarioService.obtenerBeneficiariosPorId(id);
            beneficiario1.setEstado(beneficiario.getEstado());
            beneficiario1.setFechaModificacion(new java.util.Date());
            beneficiarioService.guardarBeneficiarios(beneficiario1);
            return new ResponseEntity<Beneficiario>(HttpStatus.OK);

        }catch (Exception e){
            return new ResponseEntity<Beneficiario>(HttpStatus.NOT_FOUND);
        }

    }
}
