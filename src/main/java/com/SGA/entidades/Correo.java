package com.SGA.entidades;

import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author Usuario
 */

public class Correo {
     private static final String remitente = "fundacionmiaconstruyendofuturo";
     private static final String clave = "vswioboirynfpxcz";

    public static String mensaje;
   
    public static boolean enviarCorreo(String destinatario, String asunto, String cuerpo) {  
        boolean enviado = false ;
        Properties props = System.getProperties();
        props.put("mail.smtp.host", "smtp.gmail.com");  //El servidor SMTP de Google
        props.put("mail.smtp.user", remitente);
        props.put("mail.smtp.clave", clave);    //La clave de la cuenta
        props.put("mail.smtp.auth", "true");    //Usar autenticación mediante usuario y clave
        props.put("mail.smtp.starttls.enable", "true"); //Para conectar de manera segura al servidor SMTP
        props.put("mail.smtp.port", "587"); //El puerto SMTP seguro de Google
        props.put("mail.smtp.ssl.trust", "smtp.gmail.com");
       
        Session session = Session.getDefaultInstance(props);
        MimeMessage message = new MimeMessage(session);

        try {
            message.setFrom(new InternetAddress(remitente));
            //se podria elegir varios destinatarios 
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(destinatario));  //Se podrían añadir varios de la misma manera
            message.addRecipient(Message.RecipientType.TO, new InternetAddress("fundacionmiaconstruyendofuturo@gmail.com"));
            message.setSubject(asunto);
            message.setContent(cuerpo, "text/html");
            //message.setText(cuerpo);
             Transport transport = session.getTransport("smtp");
                transport.connect("smtp.gmail.com", remitente, clave);
                transport.sendMessage(message, message.getAllRecipients());
            enviado=true;
        }catch (MessagingException me) {
        	System.out.println(me);
            //Si se produce un error  
            mensaje=me.getMessage();            
        }
        return enviado;
    }
}