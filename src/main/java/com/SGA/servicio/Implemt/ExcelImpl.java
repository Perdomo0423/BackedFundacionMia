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
    	DataFormatter formatter = new DataFormatter();
    	
    	try {
    		for (Row row: sheet) {
    			if(row.getRowNum()>0) {
    				 //String anoInf = formatter.formatCellValue(row.getCell(0));
    				 String anoInf =  String.valueOf( row.getCell(0).getNumericCellValue() );
    				 String munCodigo = String.valueOf( row.getCell(1).getNumericCellValue() );
    				 String municipio = row.getCell(2).getStringCellValue();
//    				 
    				 String codDane = String.valueOf( row.getCell(3).getNumericCellValue() );
    				 String institucion = row.getCell(4).getStringCellValue();
    				 String consSede = String.valueOf( row.getCell(5).getNumericCellValue() );
    				 String sede = row.getCell(6).getStringCellValue();
    				 String tipoDoc = String.valueOf( row.getCell(7).getNumericCellValue() );
    				 String numDocumento = String.valueOf( row.getCell(8).getNumericCellValue() );
    				 String apellido1 = row.getCell(9).getStringCellValue();
    				 String apellido2 = row.getCell(10).getStringCellValue();
    				 String nombre1 = row.getCell(11).getStringCellValue();
    				 String nombre2 = row.getCell(12).getStringCellValue();
    				 String direccion = row.getCell(13).getStringCellValue();
//    				 
    				 String tel = String.valueOf( row.getCell(14).getNumericCellValue() );
//    				 
//    				 
//    				 
//    				 
    				 String resDepto = String.valueOf( row.getCell(15).getNumericCellValue() );
    				 String resMun = String.valueOf( row.getCell(16).getNumericCellValue() );
    				 String estrato =  String.valueOf( row.getCell(17).getNumericCellValue() );
    				 String sisben = row.getCell(18).getStringCellValue(); 
    				 String fechaNacimiento = formatter.formatCellValue(row.getCell(19));
    				 String genero = row.getCell(20).getStringCellValue(); 
    				 String pobVictConf =String.valueOf( row.getCell(21).getNumericCellValue());
    				 String tipoDisca = String.valueOf( row.getCell(22).getNumericCellValue() );
    				 String etnia = String.valueOf( row.getCell(23).getNumericCellValue() );
    				 String res = String.valueOf( row.getCell(24).getNumericCellValue() );
    				 String TipoJornada = String.valueOf( row.getCell(25).getNumericCellValue() );
    				
    				 String caracter = String.valueOf( row.getCell(26).getNumericCellValue() );
    				 String especialidad = String.valueOf( row.getCell(27).getNumericCellValue() );
    				 String grado = String.valueOf( row.getCell(28).getNumericCellValue());
    				 String grupo = String.valueOf( row.getCell(29).getNumericCellValue() );
    				 String codInternado = String.valueOf( row.getCell(30).getNumericCellValue() );
//    				 
//    				 Estudiante estu = new Estudiante();
//    				 estu.setAnoInf(anoInf);
//    				 estu.setMunCodigo(munCodigo);
//    				 estu.setMunicipio(municipio);
//    				 estu.setCodigoDane(codDane);
//    				 Institucion institucio = new Institucion();
//    				 estu.setUnaInstitucion(institucio);
//    				 estu.setConsSede(sede);
//    				 Sede sedee = new Sede();
//    				 estu.setUnaSede(sedee); 
//    				 TipoDocumento tipoDocu = new TipoDocumento();
//    				 estu.setUnTipoDocumento(tipoDocu);
//    				 estu.setNumeroDocumento(Long.parseLong(numDocumento));
//    				 estu.setApellido1(apellido1);
//    				 estu.setApellido2(apellido2);
//    				 estu.setNombre1(nombre1);
//    				 estu.setNombre2(nombre2);
//    				 estu.setDireccionRecidencia(direccion);
//    				 estu.setTelefono(tel);
//    				 estu.setResDepto(resDepto);
//    				 estu.setResMun(resMun);
//    				 estu.setEstrato(estrato);
//    				 estu.setSisben(sisben);
//    				 estu.setFechaNacimiento(fechaNacimiento);
//    				 estu.setGenero(genero);
//    				 estu.setPobVictConf(pobVictConf);
//    				 TipoDiscapacidad discapacidadd = new TipoDiscapacidad();
//    				 estu.setUnaDiscapacidad(discapacidadd);
//    				 Etnia etniaa = new Etnia();
//    				 estu.setUnaEtnia(etniaa);
//    				 estu.setRes(res);
//    				 Jornada jornadaa = new Jornada();
//    				 estu.setUnaJornada(jornadaa);
//    				 estu.setCaracter(caracter);
//    				 estu.setEspecialidad(especialidad);
//    				 estu.setGrado(grado);
//    				 estu.setGrupo(grupo);
//    				 estu.setCodigoInternado(codInternado);
	 
    			}
    			
    		}
			
		} catch (Exception e) {
			System.out.println(e);
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


