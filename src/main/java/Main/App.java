package Main;
import DAO.*;
import DAO.UsuarioDAO;
import Modelo.*;
import org.apache.log4j.BasicConfigurator;
import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.grizzly.http.server.StaticHttpHandler;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;

import java.io.IOException;
import java.net.URI;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Calendar;

/**
 * Created by ivanm on 26/04/2017.
 */
public class App {

    public static final String BASE_URI = "http://localhost:8080/myapp";

    public static HttpServer startServer() {
        //Crea un recurso que escanea peticiones
        final ResourceConfig rc = new ResourceConfig().packages("DAO", "Main", "Modelo", "Controller");
        // create and start a new instance of grizzly http server
        // exposing the Jersey application at BASE_URI
        return GrizzlyHttpServerFactory.createHttpServer(URI.create(BASE_URI), rc);
    }

    public static void main(String[] args) throws SQLException, IOException {

                                //      NO BORRAR
        //****************************************************************************************************
        BasicConfigurator.configure();    //es para el logger

        //     java.util.Date utilDate = new java.util.Date();
        //     java.sql.Date sq = new java.sql.Date(utilDate.getTime());

        //Establecer fecha
        java.sql.Date date = new java.sql.Date(Calendar.getInstance().getTime().getTime());
        // java.sql.Date date2 = new java.sql.Date(Calendar.getInstance().getTimeInMillis());

        final HttpServer server = startServer();

        StaticHttpHandler staticHttpHandler = new StaticHttpHandler("./public/");
        server.getServerConfiguration().addHttpHandler(staticHttpHandler, "/");

        System.out.println(String.format("Jersey app started and available at "
                +BASE_URI+ "\nHit enter to stop it..."));

        System.in.read();
        server.stop();

                                //^^^^^^^ NO BORRAR ^^^^^
        // **********************************************************************************************


        System.out.println("PRUEBA GITANAAAAAA");
        Captura captura = new Captura();
        captura.createCapturaSpawns();
        Captura captura1 = captura.createCapturaSpawns().get(1);
        captura.insertarCaptura(captura1,7);

      /*  Usuario usuario = new Usuario();
        System.out.println(usuario.getAtributosParaCaptura(7));


        int nivelX = usuario.getAtributosParaCaptura(7).get(0);
        int experienciaX = usuario.getAtributosParaCaptura(7).get(1);


        System.out.println("nivel:"+nivelX+" experiencia:"+experienciaX);

        */
    }
}
