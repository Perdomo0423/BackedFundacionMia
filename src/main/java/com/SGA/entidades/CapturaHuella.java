/**
 * 
 */
package com.SGA.entidades;
/*
import javax.sql.rowset.serial.SerialBlob;
import javax.sql.rowset.serial.SerialException;
import javax.swing.UIManager;

import org.hibernate.JDBCException;
import org.springframework.beans.factory.annotation.Autowired;

import com.SGA.excepciones.MensajeError;
import com.SGA.repositorio.HuellaRepository;
import com.SGA.repositorio.UsuarioRepositorio;
import com.digitalpersona.onetouch.DPFPDataPurpose;
import com.digitalpersona.onetouch.DPFPFeatureSet;
import com.digitalpersona.onetouch.DPFPGlobal;
import com.digitalpersona.onetouch.DPFPSample;
import com.digitalpersona.onetouch.DPFPTemplate;
import com.digitalpersona.onetouch.capture.DPFPCapture;
import com.digitalpersona.onetouch.capture.event.DPFPDataAdapter;
import com.digitalpersona.onetouch.capture.event.DPFPDataEvent;
import com.digitalpersona.onetouch.capture.event.DPFPErrorAdapter;
import com.digitalpersona.onetouch.capture.event.DPFPErrorEvent;
import com.digitalpersona.onetouch.capture.event.DPFPReaderStatusAdapter;
import com.digitalpersona.onetouch.capture.event.DPFPReaderStatusEvent;
import com.digitalpersona.onetouch.capture.event.DPFPSensorAdapter;
import com.digitalpersona.onetouch.capture.event.DPFPSensorEvent;
import com.digitalpersona.onetouch.processing.DPFPEnrollment;
import com.digitalpersona.onetouch.processing.DPFPFeatureExtraction;
import com.digitalpersona.onetouch.processing.DPFPImageQualityException;
import com.digitalpersona.onetouch.verification.DPFPVerification;
import com.digitalpersona.onetouch.verification.DPFPVerificationResult;

import java.awt.Image;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class CapturaHuella {
	
	@Autowired
	private HuellaRepository huellaRepositorio;
	
    public static void CapturaHuella() {
    
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            new MensajeError("Imposible modificar el tema visual Lookandfeel invalido");
                    
        }
    }
    

//Atributos necesarios para el manejo de huellas digitales
    private static DPFPCapture Lector = DPFPGlobal.getCaptureFactory().createCapture();
    private static DPFPEnrollment Reclutador = DPFPGlobal.getEnrollmentFactory().createEnrollment();
    private DPFPVerification Verificador = DPFPGlobal.getVerificationFactory().createVerification();
    private DPFPTemplate template;
    public static String TEMPLATE_PROPERTY = "template";
    public static DPFPFeatureSet featuresinscripcion;
    public static DPFPFeatureSet featuresverificacion;

//Metodo que ejecuta todas las acciones del lector de huellas
    public static void Iniciar() {
        Lector.addDataListener(new DPFPDataAdapter() {
            @Override
            public void dataAcquired(final DPFPDataEvent e) {
             
                
                        System.out.println("La huella digital ha sido capturada");
                        ProcesarCaptura(e.getSample());
                    }
            
        });

        Lector.addReaderStatusListener(new DPFPReaderStatusAdapter() {
            @Override
            public void readerConnected(final DPFPReaderStatusEvent e) {
            
                        System.out.println("El sensor de huella digital esta activado o conectado");
                 
            }

            @Override
            public void readerDisconnected(final DPFPReaderStatusEvent e) {
              
              
                
                        System.out.println("El sensor de huella dibgital esta desactivado o no conectado");
                   
               
            }
        });

        Lector.addSensorListener(new DPFPSensorAdapter() {
            @Override
            public void fingerTouched(final DPFPSensorEvent e) {
               
                    
                        System.out.println("El dedo ha sido colocado sobre el lectorde de huellas");
                    
               
            }

            @Override
            public void fingerGone(final DPFPSensorEvent e) {
             
                        System.out.println("El dedo ha sido quitado del lector de huellas");
                
            }
        });

        Lector.addErrorListener(new DPFPErrorAdapter() {
            public void errorReader(final DPFPErrorEvent e) {
            
                    
                        System.out.println("Error: " + e.getError());
              
             
            }
        });
    } //Fin metodo iniciar


	public DPFPCapture getLector() {
		return Lector;
	}

	public void setLector(DPFPCapture lector) {
		Lector = lector;
	}

	public DPFPEnrollment getReclutador() {
		return Reclutador;
	}

	public void setReclutador(DPFPEnrollment reclutador) {
		Reclutador = reclutador;
	}

	public DPFPVerification getVerificador() {
		return Verificador;
	}

	public void setVerificador(DPFPVerification verificador) {
		Verificador = verificador;
	}

	public static String getTEMPLATE_PROPERTY() {
		return TEMPLATE_PROPERTY;
	}

	public static void setTEMPLATE_PROPERTY(String tEMPLATE_PROPERTY) {
		TEMPLATE_PROPERTY = tEMPLATE_PROPERTY;
	}

	public DPFPFeatureSet getFeaturesinscripcion() {
		return featuresinscripcion;
	}

	public void setFeaturesinscripcion(DPFPFeatureSet featuresinscripcion) {
		this.featuresinscripcion = featuresinscripcion;
	}

	public DPFPFeatureSet getFeaturesverificacion() {
		return featuresverificacion;
	}

	public void setFeaturesverificacion(DPFPFeatureSet featuresverificacion) {
		this.featuresverificacion = featuresverificacion;
	}

	//Aqui se obtiene la informacion de la huella dgital
    public static DPFPFeatureSet extraerCaracteristicas(DPFPSample sample, DPFPDataPurpose purpose) {
        DPFPFeatureExtraction extractor = DPFPGlobal.getFeatureExtractionFactory().createFeatureExtraction();

        try {
            return extractor.createFeatureSet(sample, purpose);
        } catch (DPFPImageQualityException e) {
            return null;
        }
    }

     // Verifica la huella digital actual se encuentra en la base de datos
   
    public void verificarHuella(String nom)throws IOException{
        try {
            //Establece los valores para la sentencia SQL
        
            //Obtiene la plantilla correspondiente a la persona indicada
            //PreparedStatement verificarStmt = c.prepareStatement("SELECT huehuella FROM somhue WHERE hueNombre=?");
            //verificarStmt.setString(1, nom);
        	Optional<Huella> huella = huellaRepositorio.findByHuella(nom);
        	ByteArrayOutputStream convertido = null;
        	InputStream blobinstream = null;
        	
            // I obtain it from a result set DB query

        	if(huella!=null) {
        	    blobinstream = huella.get().getHuella().getBinaryStream();
        	    int chunk = 1024;
        	    byte[] buffer = new byte[chunk];
        	    int length = -1;

        	    convertido = new ByteArrayOutputStream();

        	    while ((length = blobinstream.read(buffer)) != -1) {
        	    	convertido.write(buffer, 0, length);
        	    }

        	    convertido.flush();
            //Si se encuentra el nombre en la base de datos
            if (huella!=null) {
                //Lee la plantilla de la base de datos
                byte templateBuffer[] =convertido.toByteArray();
                //Crea una nueva plantilla a partir de la guardada en la base de datos
                DPFPTemplate referenceTemplate
                        = DPFPGlobal.getTemplateFactory().createTemplate(templateBuffer);
                //Envia la plantilla creada al objeto contendor de Template del componente de huella digital
                setTemplate(referenceTemplate);
                
 // Compara las caracteriticas de la huella recientemente capturda con la
                // plantilla guardada al usuario especifico en la base de datos
                DPFPVerificationResult result = Verificador.verify(featuresverificacion, getTemplate());
                //compara las plantilas (actual vs bd)
                if (result.isVerified()) {
                   new MensajeError( "Las huella capturada coinciden con la de " + nom +"Verificacion de Huella" );
                } else {
                   new MensajeError("No corresponde la huella con " + nom+ "Verificacion de Huella");
                }

                //Si no encuentra alguna huella correspondiente al nombre lo indica con un mensaje
            } else {
                new MensajeError(  "No existe ningún registro que coincidacon la huella verificacion de Huella");
                setTemplate(null);
           
                }
            
    }
        }catch (Exception e) {
			// TODO: handle exception
		}
    }

    public static void ProcesarCaptura(DPFPSample sample) {
        featuresinscripcion = extraerCaracteristicas(sample, DPFPDataPurpose.DATA_PURPOSE_ENROLLMENT);
        featuresverificacion = extraerCaracteristicas(sample, DPFPDataPurpose.DATA_PURPOSE_VERIFICATION);

        if (featuresinscripcion != null) {
            try {
                System.out.println("Las caracteristicas de la huella");
                Reclutador.addFeatures(featuresinscripcion);

                Image image = CrearImagenHuella(sample);
                DibujarHuella(image);

                //btnVerificar.setEnabled(true);
                //btnIdentificar.setEnabled(true);

            } catch (DPFPImageQualityException ex) {
                System.err.println("Error: " + ex.getMessage());
            } finally {
                EstadoHuellas();

                switch (Reclutador.getTemplateStatus()) {
                    case TEMPLATE_STATUS_READY:
                        stop();
                        setTemplate(Reclutador.getTemplate());
                        System.out.println("La plantilla de la huella ha sido creada, ya puede verificarla o identificarla");
                        //btnIdentificar.setEnabled(false);
                        //btnVerificar.setEnabled(false);
                        //btnGuardar.setEnabled(true);
                        //btnGuardar.grabFocus();
                        //break;

                    case TEMPLATE_STATUS_FAILED:
                        Reclutador.clear();
                        stop();
                        EstadoHuellas();
                        setTemplate(null);
                        new MensajeError( "La plantilla de la huella no pudo ser creada, repita el proceso.");
                        start();
                        break;
                }
            }
        }
    }

    public static Image CrearImagenHuella(DPFPSample sample) {
        return DPFPGlobal.getSampleConversionFactory().createImage(sample);
    }

    public static void DibujarHuella(Image image) {
    
   
    }

    public static void EstadoHuellas() {
        System.out.println("Muestra de huellas necesarias para guardar template " + Reclutador.getFeaturesNeeded());
    }

    public static void Mensaje(String string) {
        System.out.print(string +"\n");
    }

    public static void start() {
        Lector.startCapture();
        System.out.println("Utilizando el lector de huella dactilar");
    }

    public static void stop() {
        Lector.stopCapture();
        System.out.println("No se esta usando el lector de huella dactilar");
    }

    public DPFPTemplate getTemplate() {
        return template;
    }

    public static void setTemplate(DPFPTemplate template) {
        DPFPTemplate old = template;
        template = template;
    }

    public void guardarHuella(String nombre) throws SerialException, SQLException   {
        ByteArrayInputStream datosHuella = new ByteArrayInputStream(template.serialize());
        Integer tamanoHuella = template.serialize().length;
        Blob blob = null;
        		if(datosHuella!=null) {
        		         byte[] myArray = datosHuella.readAllBytes();
        		         blob = new SerialBlob(myArray );
        		      }
        try {
           Huella unaHuella = new Huella();
           unaHuella.setNombre(nombre);
           unaHuella.setHuella(blob);
           huellaRepositorio.save(unaHuella);
        } catch (Exception ex) {
            System.err.println("Error al guardar los datos de la huella.");
        }
    }
    public void setBinaryStream( java.io.InputStream x,
            int length) throws SQLException{
    	
    }
    
    // Identifica a una persona registrada por medio de su huella digital
    
    public void identificarHuella() throws IOException {
        try {
            //Establece los valores para la sentencia SQL
           
            //Obtiene todas las huellas de la bd
        	List<Huella> huella = huellaRepositorio.findAll();
            //Si se encuentra el nombre en la base de datos
            for (Huella unaHuella :huella) {
            	ByteArrayOutputStream convertido = null;
            	InputStream blobinstream = null;

            	Blob blob = unaHuella.getHuella();    // I obtain it from a result set DB query

            	if(blob!=null) {
            	    blobinstream = blob.getBinaryStream();
            	    int chunk = 1024;
            	    byte[] buffer = new byte[chunk];
            	    int length = -1;

            	    convertido = new ByteArrayOutputStream();

            	    while ((length = blobinstream.read(buffer)) != -1) {
            	    	convertido.write(buffer, 0, length);
            	    }

            	    convertido.flush();
                //Lee la plantilla de la base de datos
                byte templateBuffer[] = convertido.toByteArray();
                String nombre = unaHuella.getNombre();
                //Crea una nueva plantilla a partir de la guardada en la base de datos
                DPFPTemplate referenceTemplate
                        = DPFPGlobal.getTemplateFactory().createTemplate(templateBuffer);
                //Envia la plantilla creada al objeto contendor de Template del componente de huella digital
                setTemplate(referenceTemplate);
 // Compara las caracteriticas de la huella recientemente capturda con la
                // alguna plantilla guardada en la base de datos que coincide con ese tipo
                DPFPVerificationResult result = Verificador.verify(featuresverificacion,
                        getTemplate());
 //compara las plantilas (actual vs bd)
                //Si encuentra correspondencia dibuja el mapa
                //e indica el nombre de la persona que coincidió.
                if (result.isVerified()) {
                    //crea la imagen de los datos guardado de las huellas guardadas en la base de datos
                   new MensajeError("Las huella capturada es de" + nombre+"Verificacion de Huella");
                    return;
                }
            }
            //Si no encuentra alguna huella correspondiente al nombre lo indica con un mensaje
           new MensajeError(  "No existe ningún registro que coincidacon la huella verificacion de Huella");
            setTemplate(null);
       
            }
        }catch (Exception e) {
			// TODO: handle exception
		}
    }
}
*/


