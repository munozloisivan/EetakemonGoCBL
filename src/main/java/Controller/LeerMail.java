package Controller;

import javax.mail.*;
import java.io.IOException;
import java.util.Properties;

/**
 * Created by ivanm on 28/05/2017.
 */
public class LeerMail {

  public static Message[] messages;      // Array de message
  public static   Properties properties;

    public static void obtenerMensajes() throws MessagingException, IOException {

            // Properties

        // Ajustamos primero las properties
        properties = System.getProperties();
        properties.put("mail.pop.host", "pop.gmail.com");
        properties.put("mail.pop.port", "995");
        properties.put("mail.pop.starttls.enable", "true");

        String direccionCorreo = "eetakemongocbl@gmail.com";   // Dirección de correo
        String contrasenyaCorreo = "passwordcbl";                 // Contraseña

        // abrimos sesion de email y le pasamos su datos
        Session emailSession = Session.getDefaultInstance(properties);
        Store store = emailSession.getStore("pop3s");
        store.connect("pop.gmail.com", direccionCorreo, contrasenyaCorreo);

        // Extraemos los correos de la carpeta Inbox
        Folder emailFolder = store.getFolder("INBOX");
        emailFolder.open(Folder.READ_ONLY);
        messages = emailFolder.getMessages();

        // Mostramos la bienvenida al usuario y el menú

        System.out.println("---------------------------------------------------------------------");
        System.out.println("-----------------------------LISTA MAILS-----------------------------");
        System.out.println("---------------------------------------------------------------------");

        System.out.println("Bienvenido: " + direccionCorreo);
        System.out.println("Tienes " + messages.length + " mensajes en tu bandeja de entrada");

        // Imprimimos los correos de forma ordenada
        imprimirMensajes();

        // Cerramos conexion
        emailFolder.close(false);
        store.close();

    }

    public static void imprimirMensajes() throws MessagingException, IOException {
        for (int iterador = 0; iterador < messages.length; iterador++) {
            Message message = messages[iterador];   // Imprimimos todos los correos con un for

            System.out.println("\n---------------------------------------------------------------------");
            System.out.println((iterador + 1) + " - " + message.getSubject());
            System.out.println("De:                     " + message.getFrom()[0]);
            System.out.println("Cuerpo del correo:       " + message.getContent().toString());
            System.out.println("---------------------------------------------------------------------");
        }
    }
}
