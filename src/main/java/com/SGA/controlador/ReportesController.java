package com.SGA.controlador;

import com.SGA.EnumsJasper.TipoReporteEnum;
import com.SGA.dto.JasperReportes;
import com.SGA.servicio.ReporteService;
import net.sf.jasperreports.engine.JRException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Map;

@RestController
@RequestMapping(value = {"/api/reportes"})
public class ReportesController {

    @Autowired
    private ReporteService reporteService;

    @GetMapping(value = {"/descarga"})
    public ResponseEntity<Resource> download(@RequestParam Map<String, Object> params) throws JRException, SQLException, IOException {
        JasperReportes dto = reporteService.obtenerReporte(params);

        InputStreamResource streamResource = new InputStreamResource(dto.getStream());
        MediaType mediaType = null;

        if(params.get("tipo").toString().equalsIgnoreCase(TipoReporteEnum.EXCEL.name())){
            mediaType = MediaType.APPLICATION_OCTET_STREAM;
        }else {
            mediaType = MediaType.APPLICATION_PDF;
        }
        return ResponseEntity.ok().header("Content-Disposition", "inline; filename=\""+ dto.getFileName() + "\"").contentLength(dto.getLength()).contentType(mediaType).body(streamResource);
    }
}
