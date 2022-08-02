package com.SGA.servicio.Implemt;

import com.SGA.EnumsJasper.TipoReporteEnum;
import com.SGA.JasperCommons.JasperReportManager;
import com.SGA.dto.JasperReportes;
import com.SGA.servicio.ReporteService;
import net.sf.jasperreports.engine.JRException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Map;

@Service
public class JasperReporteImp implements ReporteService {

    @Autowired
    private JasperReportManager jasperReportManager;

    @Autowired
    private DataSource dataSource;


    @Override
    public JasperReportes obtenerReporte(Map<String, Object> params) throws SQLException, JRException, IOException {
        JasperReportes dto = new JasperReportes();
        String reporteFundacionMia = "ReporteFundacionMia";
        String tipo = params.get("tipo").toString().equalsIgnoreCase(TipoReporteEnum.EXCEL.name()) ? ".xlsx" : ".pdf";
        dto.setFileName(reporteFundacionMia + tipo);
        ByteArrayOutputStream stream = jasperReportManager.export(reporteFundacionMia, params.get("tipo").toString(), params, dataSource.getConnection());

        byte[] bs = stream.toByteArray();
        dto.setStream(new ByteArrayInputStream(bs));
        dto.setLength(bs.length);
        return dto;
    }
}
