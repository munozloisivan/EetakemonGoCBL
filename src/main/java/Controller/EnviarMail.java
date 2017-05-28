package Controller;


import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;


/**
 * Created by ivanm on 28/05/2017.
 */
public class EnviarMail {

    // Correo al que enviaremos el mensaje
    //private static String destintatarioCorreo = "munozloisivan@gmail.com";

    public static void enviarMensaje(String subject, String content, String destintatarioCorreo) throws MessagingException {
        // Propiedades del cliente de correo
        Session session;         // Sesion de correo
        Properties properties;   // Propiedades de la sesion
        Transport transport;     // Envio del correo
        MimeMessage mensaje;     // Mensaje que enviaremos

        // Credenciales de usuario
         String direccionCorreo = "rovira.caliz@gmail.com";   // Dirección de correo
         String contrasenyaCorreo = "surtitpersempre";                 // Contraseña

        System.out.println("entra aqui");

        // Ajustamos primero las properties
        properties = System.getProperties();
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.socketFactory.port", "465");  //465
        properties.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.port", "465");

        //Configuramos la sesión
        session = Session.getDefaultInstance(properties, null);
        System.out.println("aqui tambien entra en la funcion ");

        // Configuramos los valores de nuestro mensaje
        mensaje = new MimeMessage(session);
        mensaje.addRecipient(Message.RecipientType.TO, new InternetAddress(destintatarioCorreo));
        mensaje.setSubject(subject);
        mensaje.setContent(content, "text/html");

        // Configuramos como sera el envio del correo
        transport = session.getTransport("smtp");
        transport.connect("smtp.gmail.com", direccionCorreo, contrasenyaCorreo);
        transport.sendMessage(mensaje, mensaje.getAllRecipients());
        transport.close();

        // Mostramos que el mensaje se ha enviado correctamente
        System.out.println("--------------------------");
        System.out.println("Mensaje enviado");
        System.out.println("---------------------------");
    }

    public EnviarMail() {
    }

}
