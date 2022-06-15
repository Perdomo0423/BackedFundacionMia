package com.SGA.servicio.Implemt;

import com.SGA.entidades.*;
import com.SGA.repositorio.*;
import com.SGA.servicio.ExcelService;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Service
public class ExcelImpl implements ExcelService {
    private Path tempDir;
    private MultipartFile file;
    private File tempFile;
    private Workbook workbook;

    @Autowired
    private PaisRepository paisRepository;
    @Autowired
    private DepartamentoRepository departamentoRepository;
    @Autowired
    private MunicipioRepository municipioRepository;
    @Autowired
    private InstitucionRepository institucionRepository;
    @Autowired
    private SedeRepository sedeRepository;
    @Autowired
    private EstudianteRepository estudianteRepository;
    @Autowired
    private TipoDocumentoRepository tipDocRepository;
    @Autowired
    private TipoDiscapacidadRepository tipoDiscapacidadRepository;
    @Autowired
    private EtniaRepository etniaRepository;
    @Autowired
    private JornadaRepository jornadaRepository;

    public ExcelImpl() throws IOException {
        this.tempDir = Files.createTempDirectory("");
    }

    private void copiarData() throws IOException, InvalidFormatException {
        Arrays.asList(file).stream().forEach(fil -> {
            try {
                Files.copy(fil.getInputStream(), this.tempDir.resolve(fil.getOriginalFilename()));
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        this.tempFile = this.tempDir.resolve(this.file.getOriginalFilename()).toFile();
        this.workbook = WorkbookFactory.create(tempFile);
    }

    private void guardarDataPais(Sheet sheet) {
        for (Row row : sheet) {
            if (row.getRowNum() > 2) {
                double codigo = row.getCell(0).getNumericCellValue();
                String pais = row.getCell(1).getStringCellValue();
                Boolean nombre = paisRepository.existsByCodigo((int) codigo);
                if (!nombre) {
                    Pais p = new Pais();
                    p.setCodigo((int) codigo);
                    p.setNombre(pais);
                    paisRepository.save(p);
                } else {
                    Pais unPais = paisRepository.findByCodigo((int) codigo);
                    unPais.setNombre(pais);
                    paisRepository.save(unPais);
                }

            }
        }
    }

    private void guardarDataDepartamentos(Sheet sheet) {
        for (Row row : sheet) {
            if (row.getRowNum() > 0) {
                double codigo = row.getCell(0).getNumericCellValue();
                String depar = row.getCell(1).getStringCellValue();
                double codigoPais = row.getCell(2).getNumericCellValue();
                Boolean bol = departamentoRepository.existsByCodigo((int) codigo);
                System.out.print("----->" + bol);
                if (bol != true) {
                    Departamento dep = new Departamento();
                    dep.setCodigo((int) codigo);
                    dep.setNombre(depar);
                    Pais unPais = paisRepository.findByCodigo((int) codigoPais);
                    dep.setUnPais(unPais);
                    departamentoRepository.save(dep);
                } else {
                    Departamento unDepartamento = departamentoRepository.findByCodigo((int) codigo);
                    Pais unPais = paisRepository.findByCodigo((int) codigoPais);
                    unDepartamento.setNombre(depar);
                    unDepartamento.setUnPais(unPais);
                    System.out.print("nombrepais" + unPais.getNombre());
                    departamentoRepository.save(unDepartamento);
                }

            }
        }
    }

    private void guardarDataMunicipios(Sheet sheet) {
        for (Row row : sheet) {
            if (row.getRowNum() > 0) {
                double codigo = row.getCell(0).getNumericCellValue();
                String municipio = row.getCell(1).getStringCellValue();
                double codigoDep = row.getCell(2).getNumericCellValue();
                Boolean bol = municipioRepository.existsByCodigoAndUnDepartamentoCodigo((int) codigo, (int) codigoDep);
                if (bol != true) {
                    Municipio mun = new Municipio();
                    mun.setCodigo((int) codigo);
                    mun.setNombre(municipio);
                    Departamento unDepartamento = departamentoRepository.findByCodigo((int) codigoDep);
                    mun.setUnDepartamento(unDepartamento);
                    municipioRepository.save(mun);
                } else {
                    Municipio unMun = municipioRepository.findByCodigoAndUnDepartamentoCodigo((int) codigo,
                            (int) codigoDep);
                    unMun.setNombre(municipio);
                    Departamento unDepartamento = departamentoRepository.findByCodigo((int) codigoDep);
                    unMun.setUnDepartamento(unDepartamento);
                    municipioRepository.save(unMun);
                }

            }
        }
    }

    private void guardarDataInstituciones(Sheet sheet) {
        List<String> tituloList = new ArrayList<String>();
        List iteracion = new ArrayList<>();
        for (Row row : sheet) {

            DataFormatter formatter = new DataFormatter();
            if (row.getRowNum() == 0) {
                for (int x = 0; x < 7; x++) {
                    System.out.println("Entro");
                    String titulo = formatter.formatCellValue(row.getCell(x));
                    if (("DANE_ESTABLECIMIENTO").equals(titulo)) {
                        tituloList.add(titulo);
                        System.out.println(titulo);
                        iteracion.add(x);
                    }
                    if (("ESTABLECIMIENTO").equals(titulo)) {
                        tituloList.add(titulo);
                        System.out.println(titulo);
                        iteracion.add(x);
                    }
                    if (("NATURALEZA").equals(titulo)) {
                        tituloList.add(titulo);
                        System.out.println(titulo);
                        iteracion.add(x);
                    }
                    if (("MUNICIPIO").equals(titulo)) {
                        tituloList.add(titulo);
                        System.out.println(titulo);
                        iteracion.add(x);
                    }
                    if (("DEPARTAMENTO").equals(titulo)) {
                        tituloList.add(titulo);
                        System.out.println(titulo);
                        iteracion.add(x);
                    }

                }
            }
            Cell codigoDaneCell = null;
            Cell establecimientoCell = null;
            Cell naturalezaCell = null;
            Cell municipioCell = null;
            Cell departamentoCell = null;

            int x = 0;

            if (row.getRowNum() > 0) {
                for (String titulo : tituloList) {

                    if (("DANE_ESTABLECIMIENTO").equals(titulo)) {
                        codigoDaneCell = CellUtil.getCell(row, (int) iteracion.get(x));
                    }
                    if (("ESTABLECIMIENTO").equals(titulo)) {
                        establecimientoCell = CellUtil.getCell(row, (int) iteracion.get(x));
                    }
                    if (("NATURALEZA").equals(titulo)) {
                        naturalezaCell = CellUtil.getCell(row, (int) iteracion.get(x));
                    }
                    if (("MUNICIPIO").equals(titulo)) {
                        municipioCell = CellUtil.getCell(row, (int) iteracion.get(x));
                    }
                    if (("DEPARTAMENTO").equals(titulo)) {
                        departamentoCell = CellUtil.getCell(row, (int) iteracion.get(x));
                    }
                    x++;
                }
                Long codigoDane = ((long) codigoDaneCell.getNumericCellValue());
                System.out.println("--->" + codigoDane);
                String establecimiento = establecimientoCell.getStringCellValue(); // instititucion
                System.out.println("--->" + establecimiento);
                String naturaleza = naturalezaCell.getStringCellValue();
                System.out.println("--->" + naturaleza);
                String municipio = municipioCell.getStringCellValue();
                System.out.println("--->" + municipio);
                String departamento = departamentoCell.getStringCellValue();
                System.out.println("--->" + departamento);
                Boolean bol = institucionRepository.existsByCodDane(codigoDane);
                if (bol != true) {
                    Institucion ins = new Institucion();
                    ins.setCodDane(codigoDane);
                    ins.setNombre(establecimiento);
                    ins.setNaturaleza(naturaleza);
                    ins.setFechaCreacion(new Date().toString());
                    ins.setFechaModificacion(new Date().toString());
                    Departamento unDepartamento = departamentoRepository.findByNombreAndUnPaisIdPais(departamento,
                            (long) 41);
                    Municipio unMunicipio = municipioRepository.findByNombreAndUnDepartamentoCodigo(municipio,
                            ((int) unDepartamento.getCodigo()));
                    ins.setUnMunicipio(unMunicipio);

                    ins.setDepartamento(unDepartamento);
                    institucionRepository.save(ins);
                } else {
                    Institucion ins = institucionRepository.findByCodDane(codigoDane);
                    System.out.println("1");
                    ins.setNombre(establecimiento);
                    System.out.println("2");
                    ins.setNaturaleza(naturaleza);
                    System.out.println("3");
                    ins.setFechaCreacion(new Date().toString());
                    System.out.println("4");
                    ins.setFechaModificacion(new Date().toString());
                    System.out.println("5");
                    Departamento unDepartamento = departamentoRepository.findByNombreAndUnPaisIdPais(departamento,
                            (long) 41);
                    System.out.println("6");
                    Municipio unMunicipio = municipioRepository.findByNombreAndUnDepartamentoCodigo(municipio,
                            ((int) unDepartamento.getCodigo()));
                    System.out.println("7");
                    ins.setUnMunicipio(unMunicipio);
                    System.out.println("8");
                    ins.setDepartamento(unDepartamento);
                    System.out.println("9");
                    institucionRepository.save(ins);
                    System.out.println("10");
                }

            }

        }
    }

    private void guardarDataSedes(Sheet sheet) {
        DataFormatter formatter = new DataFormatter();
        List<String> tituloList = new ArrayList<String>();
        List iteracion = new ArrayList<>();
        for (Row row : sheet) {

            if (row.getRowNum() == 4) {
                for (int x = 0; x < 6; x++) {
                    String titulo1 = formatter.formatCellValue(row.getCell(x));
                    if(("MUNICIPIO").equals(titulo1)) {
                        tituloList.add(titulo1);
                        iteracion.add((int)x);
                        System.out.println(tituloList.get(x)+" "+iteracion.get(x));
                    }
                    if(("INSTITUCION").equals(titulo1)) {
                        tituloList.add(titulo1);
                        iteracion.add((int)x);
                        System.out.println(tituloList.get(x)+" "+iteracion.get(x));
                    }
                    if(("SEDE").equals(titulo1)) {
                        tituloList.add(titulo1);
                        iteracion.add((int)x);
                        System.out.println(tituloList.get(x)+" "+iteracion.get(x));
                    }
                    if(("CONSECUTIVO").equals(titulo1)) {
                        tituloList.add(titulo1);
                        iteracion.add((int)x);
                        System.out.println(titulo1+" "+iteracion.get(x));
                    }
                    if(("NOMBRE_SEDE").equals(titulo1)) {
                        tituloList.add(titulo1);
                        iteracion.add((int)x);
                        System.out.println(tituloList.get(x)+" "+iteracion.get(x));
                    }
                    if(("ZONA").equals(titulo1)) {
                        tituloList.add(titulo1);
                        iteracion.add((int)x);
                        System.out.println(tituloList.get(x)+" "+iteracion.get(x));
                    }

                }
            }
            Cell codigoDaneSedCell = null;
            Cell codigoDaneInstitucionCell=null;
            Cell consecutivoCell = null;
            Cell nombreSedeCell = null;
            Cell zonaCell = null;
            Cell municipioCell = null;


            if (row.getRowNum() > 4) {

                int contador =0;
                for (String titulo : tituloList) {
                    System.out.println("Hola");
                    if(("MUNICIPIO").equals(titulo)) {
                        municipioCell = CellUtil.getCell(row, (int) iteracion.get(contador));
                        System.out.println(municipioCell);
                    }
                    if(("INSTITUCION").equals(titulo)) {
                        codigoDaneInstitucionCell = CellUtil.getCell(row,(int) iteracion.get(contador));
                        System.out.println(codigoDaneInstitucionCell);
                    }
                    if(("SEDE").equals(titulo)) {
                        codigoDaneSedCell = CellUtil.getCell(row,(int) iteracion.get(contador));
                        System.out.println(codigoDaneSedCell);
                    }
                    if(("CONSECUTIVO").equals(titulo)) {
                        consecutivoCell = CellUtil.getCell(row, (int) iteracion.get(contador));
                        System.out.println(consecutivoCell);
                    }
                    if(("NOMBRE_SEDE").equals(titulo)) {
                        nombreSedeCell = CellUtil.getCell(row, (int) iteracion.get(contador));
                        System.out.println(nombreSedeCell);
                    }
                    if(("ZONA").equals(titulo)) {
                        zonaCell = CellUtil.getCell(row, (int) iteracion.get(contador));
                        System.out.println(zonaCell);
                    }
                    contador++;
                }

            }
            if (row.getRowNum() > 4) {
                double codigoDaneSede = codigoDaneSedCell.getNumericCellValue();
                System.out.println("1--->" + codigoDaneSede);
                double codigoDaneInstitucion = codigoDaneInstitucionCell.getNumericCellValue(); // instititucion
                System.out.println("2--->" + codigoDaneInstitucion);
                double consecutivo = consecutivoCell.getNumericCellValue();
                System.out.println("3--->" + consecutivo);
                String nombreSede = nombreSedeCell.getStringCellValue();
                System.out.println("4--->" + nombreSede);
                String zona = zonaCell.getStringCellValue();
                System.out.println("5--->" + zona);
                String municipio = municipioCell.getStringCellValue();
                long codDaneSedeLong = (new Double(codigoDaneSede)).longValue();
                long codDaneInstitucionLong = (new Double(codigoDaneInstitucion)).longValue();
                long consecutivoLong = (new Double(consecutivo)).longValue();
                Boolean bol = sedeRepository.existsByCodDane(codDaneSedeLong);
                System.out.println("----->" + bol);
                if (bol != true) {
                    System.out.print("holiwissssssssssssssssssssssss");
                    Sede sed = new Sede();
                    sed.setCodDane(codDaneSedeLong);
                    sed.setNombre(nombreSede);
                    sed.setConsecutivo(consecutivoLong);
                    sed.setZona(zona);
                    Institucion unaInstitucion = institucionRepository.findByCodDane(codDaneInstitucionLong);
                    System.out.println("---------------> institucion" + unaInstitucion.getNombre());
                    sed.setUnaInstitucion(unaInstitucion);
                    Municipio unMunicipio = municipioRepository.findByNombre(municipio);
                    System.out.println("---------------> municipio" + unMunicipio.getNombre());
                    sed.setMunicipio(unMunicipio);
                    sedeRepository.save(sed);
                } else {
                    Sede sed = sedeRepository.findByCodDane(codDaneSedeLong);
                    sed.setCodDane(codDaneSedeLong);
                    sed.setNombre(nombreSede);
                    sed.setConsecutivo(consecutivoLong);
                    sed.setZona(zona);
                    Institucion unaInstitucion = institucionRepository.findByCodDane(codDaneInstitucionLong);
                    System.out.println("---------------> institucion" + unaInstitucion.getNombre());
                    sed.setUnaInstitucion(unaInstitucion);
                    Municipio unMunicipio = municipioRepository.findByNombre(municipio);
                    System.out.println("---------------> municipio" + unMunicipio.getNombre());
                    sed.setMunicipio(unMunicipio);
                    sedeRepository.save(sed);
                }

            }
        }
    }

    private void guardarDataEstudiantes(Sheet sheet) {
        List<String> tituloList = new ArrayList<String>();
        List iteracion = new ArrayList<>();
        for (Row row : sheet) {
            DataFormatter formatter = new DataFormatter();
            if (row.getRowNum() == 0) {
                for (int x = 0; x < 70; x++) {
                    String titulo = formatter.formatCellValue(row.getCell(x));
                    if (("CODIGO_DANE").equals(titulo)) {
                        tituloList.add(titulo);
                        System.out.println(titulo);
                        iteracion.add(x);
                    }
                    if (("CONS_SEDE").equals(titulo)) {
                        tituloList.add(titulo);
                        System.out.println(titulo);
                        iteracion.add(x);
                    }
                    if (("MUN_CODIGO").equals(titulo)) {
                        tituloList.add(titulo);
                        System.out.println(titulo);
                        iteracion.add(x);
                    }
                    if (("TIPO_DOCUMENTO").equals(titulo)) {
                        tituloList.add(titulo);
                        System.out.println(titulo);
                        iteracion.add(x);
                    }
                    if (("NRO_DOCUMENTO").equals(titulo)) {
                        tituloList.add(titulo);
                        System.out.println(titulo);
                        iteracion.add(x);
                    }
                    if (("NOMBRE1").equals(titulo)) {
                        tituloList.add(titulo);
                        System.out.println(titulo);
                        iteracion.add(x);
                    }
                    if (("NOMBRE2").equals(titulo)) {
                        tituloList.add(titulo);
                        System.out.println(titulo);
                        iteracion.add(x);
                    }
                    if (("APELLIDO1").equals(titulo)) {
                        tituloList.add(titulo);
                        System.out.println(titulo);
                        iteracion.add(x);
                    }
                    if (("APELLIDO2").equals(titulo)) {
                        tituloList.add(titulo);
                        System.out.println(titulo);
                        iteracion.add(x);
                    }
                    if (("DIRECCION_RESIDENCIA").equals(titulo)) {
                        tituloList.add(titulo);
                        System.out.println(titulo);
                        iteracion.add(x);
                    }
                    if (("RES_MUN").equals(titulo)) {
                        tituloList.add(titulo);
                        System.out.println(titulo);
                        iteracion.add(x);
                    }
                    if (("RES_DEPTO").equals(titulo)) {
                        tituloList.add(titulo);
                        System.out.println(titulo);
                        iteracion.add(x);
                    }
                    if (("NAC_DEPTO").equals(titulo)) {
                        tituloList.add(titulo);
                        System.out.println(titulo);
                        iteracion.add(x);
                    }
                    if (("NAC_MUN").equals(titulo)) {
                        tituloList.add(titulo);
                        System.out.println(titulo);
                        iteracion.add(x);
                    }
                    if (("GENERO").equals(titulo)) {
                        tituloList.add(titulo);
                        System.out.println(titulo);
                        iteracion.add(x);
                    }
                    if (("TIPO_DISCAPACIDAD").equals(titulo)) {
                        tituloList.add(titulo);
                        System.out.println(titulo);
                        iteracion.add(x);
                    }
                    if (("ETNIA").equals(titulo)) {
                        tituloList.add(titulo);
                        System.out.println(titulo);
                        iteracion.add(x);
                    }
                    if (("TIPO_JORNADA").equals(titulo)) {
                        tituloList.add(titulo);
                        System.out.println(titulo);
                        iteracion.add(x);
                    }
                    if (("GRADO").equals(titulo)) {
                        tituloList.add(titulo);
                        System.out.println(titulo);
                        iteracion.add(x);
                    }
                    if (("PAIS_ORIGEN").equals(titulo)) {
                        tituloList.add(titulo);
                        System.out.println(titulo);
                        iteracion.add(x);
                    }
                    if (("FECHA_NACIMIENTO").equals(titulo)) {
                        tituloList.add(titulo);
                        System.out.println(titulo);
                        iteracion.add(x);

                    }
                    if (("TEL").equals(titulo)) {
                        tituloList.add(titulo);
                        System.out.println(titulo);
                        iteracion.add(x);
                    }
                }
            }

            Cell codigoDaneInstitucionCell = null;
            Cell consecutivoSedeCell = null;
            Cell codMunicipioCell = null;
            Cell codTipoDocumentoCell = null;
            Cell numeroDocumentoCell = null;
            Cell nombre1Cell = null;
            Cell nombre2Cell = null;
            Cell apellido1Cell = null;
            Cell apellido2Cell = null;
            Cell direccionRecidenciaCell = null;
            Cell codMunicipioRecidenciaCell = null;
            Cell codDepartamentoResCell = null;
            Cell codNacimientoDepartamentoCell = null;
            Cell codNacimientoMunicipioCell = null;
            Cell generoCell = null;
            Cell CellcodDiscapacidad = null;
            Cell codEtniaCell = null;
            Cell codUnaJornadaCell = null;
            Cell gradoCell = null;
            Cell codPaisCell = null;
            String fechaNacimiento = "";
            String telefono = "";
            int contador = 0;

            if (row.getRowNum() > 0) {
                for (String titulo : tituloList) {
                    if (("CODIGO_DANE").equals(titulo)) {
                        codigoDaneInstitucionCell = CellUtil.getCell(row, (int) iteracion.get(contador));
                        System.out.println("1. " + codigoDaneInstitucionCell);
                    }
                    if (("CONS_SEDE").equals(titulo)) {
                        consecutivoSedeCell = CellUtil.getCell(row, (int) iteracion.get(contador));
                        System.out.println("2. " + consecutivoSedeCell);
                    }
                    if (("MUN_CODIGO").equals(titulo)) {
                        codMunicipioCell = CellUtil.getCell(row, (int) iteracion.get(contador));
                        System.out.println("3. " + codMunicipioCell);
                    }
                    if (("TIPO_DOCUMENTO").equals(titulo)) {
                        codTipoDocumentoCell = CellUtil.getCell(row, (int) iteracion.get(contador));
                        System.out.println("4. " + codTipoDocumentoCell);
                    }
                    if (("NRO_DOCUMENTO").equals(titulo)) {
                        numeroDocumentoCell = CellUtil.getCell(row, (int) iteracion.get(contador));
                        System.out.println("5. " + numeroDocumentoCell);
                    }
                    if (("NOMBRE1").equals(titulo)) {
                        nombre1Cell = CellUtil.getCell(row, (int) iteracion.get(contador));
                        System.out.println("6. " + nombre1Cell);
                    }
                    if (("NOMBRE2").equals(titulo)) {
                        nombre2Cell = CellUtil.getCell(row, (int) iteracion.get(contador));
                        System.out.println("7. " + nombre2Cell);
                    }
                    if (("APELLIDO1").equals(titulo)) {
                        apellido1Cell = CellUtil.getCell(row, (int) iteracion.get(contador));
                        System.out.println("8. " + apellido1Cell);
                    }
                    if (("APELLIDO2").equals(titulo)) {
                        apellido2Cell = CellUtil.getCell(row, (int) iteracion.get(contador));
                        System.out.println("9. " + apellido2Cell);
                    }
                    if (("DIRECCION_RESIDENCIA").equals(titulo)) {
                        direccionRecidenciaCell = CellUtil.getCell(row, (int) iteracion.get(contador));
                        System.out.println("10. " + direccionRecidenciaCell);
                    }
                    if (("RES_MUN").equals(titulo)) {
                        codMunicipioRecidenciaCell = CellUtil.getCell(row, (int) iteracion.get(contador));
                        System.out.println("11. " + codMunicipioRecidenciaCell);
                    }
                    if (("RES_DEPTO").equals(titulo)) {
                        codDepartamentoResCell = CellUtil.getCell(row, (int) iteracion.get(contador));
                        System.out.println("12. " + codDepartamentoResCell);
                    }
                    if (("NAC_DEPTO").equals(titulo)) {
                        codNacimientoDepartamentoCell = CellUtil.getCell(row, (int) iteracion.get(contador));
                        System.out.println("13. " + codNacimientoDepartamentoCell);
                    }
                    if (("NAC_MUN").equals(titulo)) {
                        codNacimientoMunicipioCell = CellUtil.getCell(row, (int) iteracion.get(contador));
                        System.out.println("14. " + codNacimientoMunicipioCell);
                    }
                    if (("GENERO").equals(titulo)) {
                        generoCell = CellUtil.getCell(row, (int) iteracion.get(contador));
                        System.out.println("15. " + generoCell);
                    }
                    if (("TIPO_DISCAPACIDAD").equals(titulo)) {
                        CellcodDiscapacidad = CellUtil.getCell(row, (int) iteracion.get(contador));
                        System.out.println("16. " + CellcodDiscapacidad);
                    }
                    if (("ETNIA").equals(titulo)) {
                        codEtniaCell = CellUtil.getCell(row, (int) iteracion.get(contador));
                        System.out.println("17. " + codEtniaCell);
                    }
                    if (("TIPO_JORNADA").equals(titulo)) {
                        codUnaJornadaCell = CellUtil.getCell(row, (int) iteracion.get(contador));
                        System.out.println("18. " + codUnaJornadaCell);
                    }
                    if (("GRADO").equals(titulo)) {
                        gradoCell = CellUtil.getCell(row, (int) iteracion.get(contador));
                        System.out.println("19. " + gradoCell);
                    }
                    if (("PAIS_ORIGEN").equals(titulo)) {
                        codPaisCell = CellUtil.getCell(row, (int) iteracion.get(contador));
                        System.out.println("20. " + codPaisCell);
                    }
                    if (("FECHA_NACIMIENTO").equals(titulo)) {
                        fechaNacimiento = formatter.formatCellValue(row.getCell((int) iteracion.get(contador)));
                        System.out.println("21. " + fechaNacimiento);
                    }
                    if (("TEL").equals(titulo)) {
                        telefono = formatter.formatCellValue(row.getCell((int) iteracion.get(contador)));
                        System.out.println("22. " + telefono);
                    }
                    contador++;
                }
            }
            if (row.getRowNum() > 0) {
                System.out.println("....");
                double codigoDaneInstitucion = codigoDaneInstitucionCell.getNumericCellValue();
                double consecutivoSede = consecutivoSedeCell.getNumericCellValue();
                double codMunicipio = codMunicipioCell.getNumericCellValue();
                double codTipoDocumento = codTipoDocumentoCell.getNumericCellValue();
                double numeroDocumento = numeroDocumentoCell.getNumericCellValue();
                String nombre1 = nombre1Cell.getStringCellValue();
                String nombre2 = nombre2Cell.getStringCellValue();
                String apellido1 = apellido1Cell.getStringCellValue();
                String apellido2 = apellido2Cell.getStringCellValue();
                String direccionRecidencia = direccionRecidenciaCell.getStringCellValue();
                double codMunicipioRecidencia = codMunicipioRecidenciaCell.getNumericCellValue();
                double codNacimientoDepartamento = codNacimientoDepartamentoCell.getNumericCellValue();
                double codNacimientoMunicipio = codNacimientoMunicipioCell.getNumericCellValue();
                String genero = generoCell.getStringCellValue();
                double codDiscapacidad = CellcodDiscapacidad.getNumericCellValue();
                double codEtnia = codEtniaCell.getNumericCellValue();
                double codUnaJornada = codUnaJornadaCell.getNumericCellValue();
                double grado = gradoCell.getNumericCellValue();
                double codPais = codPaisCell.getNumericCellValue();
                double codDepartamentoRes = codDepartamentoResCell.getNumericCellValue();
                long codigoDaneInstitucionLong = (new Double(codigoDaneInstitucion)).longValue();
                long numeroDocumentoLong = (new Double(numeroDocumento)).longValue();
                long consecutivoSedeLong = (new Double(consecutivoSede)).longValue();
                long codMunicipioLong = (new Double(codMunicipio)).longValue();
                long codTipoDocumentoLong = (new Double(codTipoDocumento)).longValue();
                long codMunicipioRecidenciaLong = (new Double(codMunicipioRecidencia)).longValue();
                long codNacimientoDepartamentoLong = (new Double(codNacimientoDepartamento)).longValue();
                long codNacimientoMunicipioLong = (new Double(codNacimientoMunicipio)).longValue();
                long codDiscapacidadLong = (new Double(codDiscapacidad)).longValue();
                long codEtniaLong = (new Double(codEtnia)).longValue();
                long codUnaJornadaLong = (new Double(codUnaJornada)).longValue();
                long codPaisLong = (new Double(codPais)).longValue();
                long codDepartamentoLong = (new Double(codDepartamentoRes)).longValue();
                Boolean bol = estudianteRepository.existsByNumeroDocumento(numeroDocumentoLong);
                if (bol != true) {
                    Estudiante unEstudiante = new Estudiante();
                    Institucion unaInstitucion = institucionRepository.findByCodDane(codigoDaneInstitucionLong);
                    unEstudiante.setUnaInstitucion(unaInstitucion);
                    Sede unaSede = sedeRepository.findByConsecutivo(consecutivoSedeLong);
                    unEstudiante.setUnaSede(unaSede);
                    Municipio unMunicipio = municipioRepository
                            .findByCodigoAndUnDepartamentoCodigo((int) codMunicipioLong, (int) codDepartamentoLong);
                    unEstudiante.setUnMunicipio(unMunicipio);
                    TipoDocumento unTipoDocumento = tipDocRepository.findByCodigo((int) codTipoDocumentoLong);
                    unEstudiante.setUnTipoDocumento(unTipoDocumento);
                    unEstudiante.setNumeroDocumento(numeroDocumentoLong);
                    unEstudiante.setNombre1(nombre1);
                    unEstudiante.setNombre2(nombre2);
                    unEstudiante.setApellido1(apellido1);
                    unEstudiante.setApellido2(apellido2);
                    unEstudiante.setGenero(genero);
                    unEstudiante.setDireccionRecidencia(direccionRecidencia);
                    unEstudiante.setTelefono("" + telefono);
                    Municipio unMunicipioRecidencia = municipioRepository.findByCodigoAndUnDepartamentoCodigo(
                            (int) codMunicipioRecidenciaLong, (int) codDepartamentoLong);
                    unEstudiante.setMunicipioRecidencia(unMunicipioRecidencia.getNombre());
                    unEstudiante.setFechaNacimiento(fechaNacimiento);
                    Municipio unMunicipioNacimiento = municipioRepository.findByCodigoAndUnDepartamentoCodigo(
                            (int) codNacimientoMunicipioLong, (int) codNacimientoDepartamentoLong);
                    unEstudiante.setNacimientoMunicipio(unMunicipioNacimiento.getNombre());
                    Departamento unDepartamento = departamentoRepository
                            .findByCodigo((int) codNacimientoDepartamentoLong);
                    unEstudiante.setNacimientoDepartamento(unDepartamento.getNombre());
                    TipoDiscapacidad unTipoDiscapacidad = tipoDiscapacidadRepository
                            .findByCodigo(codDiscapacidadLong);
                    unEstudiante.setUnaDiscapacidad(unTipoDiscapacidad);
                    Etnia unaEtnia = etniaRepository.findByCodigo(codEtniaLong);
                    unEstudiante.setUnaEtnia(unaEtnia);
                    unEstudiante.setGrado("" + (int) grado);
                    Jornada unaJornada = jornadaRepository.findByCodigo(codUnaJornadaLong);
                    unEstudiante.setUnaJornada(unaJornada);
                    Pais unPais = paisRepository.findByCodigo((int) codPaisLong);
                    unEstudiante.setPaisOrigen(unPais);
                    estudianteRepository.save(unEstudiante);
                } else {
                    Estudiante unEstudiante = estudianteRepository.findByNumeroDocumento(numeroDocumentoLong);
                    Institucion unaInstitucion = institucionRepository.findByCodDane(codigoDaneInstitucionLong);
                    unEstudiante.setUnaInstitucion(unaInstitucion);
                    Sede unaSede = sedeRepository.findByConsecutivo(consecutivoSedeLong);
                    unEstudiante.setUnaSede(unaSede);
                    Municipio unMunicipio = municipioRepository
                            .findByCodigoAndUnDepartamentoCodigo((int) codMunicipioLong, (int) codDepartamentoLong);
                    unEstudiante.setUnMunicipio(unMunicipio);
                    TipoDocumento unTipoDocumento = tipDocRepository.findByCodigo((int) codTipoDocumentoLong);
                    unEstudiante.setUnTipoDocumento(unTipoDocumento);
                    unEstudiante.setNombre1(nombre1);
                    unEstudiante.setNombre2(nombre2);
                    unEstudiante.setApellido1(apellido1);
                    unEstudiante.setApellido2(apellido2);
                    unEstudiante.setGenero(genero);
                    unEstudiante.setDireccionRecidencia(direccionRecidencia);
                    unEstudiante.setTelefono("" + telefono);
                    Municipio unMunicipioRecidencia = municipioRepository.findByCodigoAndUnDepartamentoCodigo(
                            (int) codMunicipioRecidenciaLong, (int) codDepartamentoLong);
                    unEstudiante.setMunicipioRecidencia(unMunicipioRecidencia.getNombre());
                    unEstudiante.setFechaNacimiento(fechaNacimiento);
                    System.out.println((int) codNacimientoMunicipioLong + "- " + (int) codDepartamentoLong);
                    Municipio unMunicipioNacimiento = municipioRepository.findByCodigoAndUnDepartamentoCodigo(
                            (int) codNacimientoMunicipioLong, (int) codNacimientoDepartamentoLong);

                    unEstudiante.setNacimientoMunicipio(unMunicipioNacimiento.getNombre());
                    Departamento unDepartamento = departamentoRepository
                            .findByCodigo((int) codNacimientoDepartamentoLong);
                    unEstudiante.setNacimientoDepartamento(unDepartamento.getNombre());
                    TipoDiscapacidad unTipoDiscapacidad = tipoDiscapacidadRepository
                            .findByCodigo(codDiscapacidadLong);
                    unEstudiante.setUnaDiscapacidad(unTipoDiscapacidad);
                    Etnia unaEtnia = etniaRepository.findByCodigo(codEtniaLong);
                    unEstudiante.setUnaEtnia(unaEtnia);
                    unEstudiante.setGrado("" + (int) grado);
                    Jornada unaJornada = jornadaRepository.findByCodigo(codUnaJornadaLong);
                    unEstudiante.setUnaJornada(unaJornada);
                    Pais unPais = paisRepository.findByCodigo((int) codPaisLong);
                    unEstudiante.setPaisOrigen(unPais);
                    estudianteRepository.save(unEstudiante);
                }

            }
        }
    }

    @Override
    public ResponseEntity<?> saveExcel(MultipartFile files, String type) throws IOException, InvalidFormatException {
        this.file = files;
        this.copiarData();
        for (Sheet sheet : workbook) {
            switch (type) {
                case "pais":
                    this.guardarDataPais(sheet);
                    break;

                case "departamentos":
                    this.guardarDataDepartamentos(sheet);
                    break;

                case "municipios":
                    this.guardarDataMunicipios(sheet);
                    break;

                case "instituciones":
                    this.guardarDataInstituciones(sheet);
                    break;

                case "sedes":
                    this.guardarDataSedes(sheet);
                    break;

                case "estudiantes":
                    this.guardarDataEstudiantes(sheet);
                    break;
            }
        }
        return new ResponseEntity<Object>("Archivo creado correctamente", HttpStatus.CREATED);
    }
}
