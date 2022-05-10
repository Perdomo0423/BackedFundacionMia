package com.SGA.servicio;

import com.SGA.dto.JasperReportes;
import net.sf.jasperreports.engine.JRException;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Map;

public interface ReporteService {

    JasperReportes obtenerReporte(Map<String, Object> params) throws SQLException, JRException, IOException;

}
