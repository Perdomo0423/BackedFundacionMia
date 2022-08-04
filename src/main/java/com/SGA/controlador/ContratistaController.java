package com.SGA.controlador;

import com.SGA.entidades.Contratista;
import com.SGA.servicio.ContratistaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@CrossOrigin(origins = "*")
@RestController
@RequestMapping("api/contratista")

public class ContratistaController {

    @Autowired
    private ContratistaService service;

    @GetMapping("listar")
    public List<Contratista> all() {
        return service.all();
    }

    @GetMapping("listar/{nit}")
    public Optional<Contratista> show(@PathVariable Long nit) {
        return service.findById(nit);
    }

    @PostMapping("guardar")
    @ResponseStatus(code = HttpStatus.CREATED)
    public Contratista save(@RequestBody Contratista contratista) {
        return service.save(contratista);
    }


    @PutMapping("actualizar/{nit}")
    @ResponseStatus(code = HttpStatus.CREATED)
    public Contratista update(@PathVariable Long nit, @RequestBody Contratista contratista) {
        Optional<Contratista> op = service.findById(nit);

        Contratista ContratistasUpdate = new Contratista();
        if (op.isPresent()) {
            ContratistasUpdate = op.get();
            ContratistasUpdate = contratista;
            ContratistasUpdate.setIdContratista(nit);

        }
        return service.save(ContratistasUpdate);

    }

    @DeleteMapping("eliminar/{nit}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long nit) {
        service.delete(nit);
    }


    @GetMapping(value = {"/zona/{id_zona}"})
    public List<Contratista> listarZona(@PathVariable("id_zona") Long id_zona) {
        return service.listarZona(id_zona);
    }

}
