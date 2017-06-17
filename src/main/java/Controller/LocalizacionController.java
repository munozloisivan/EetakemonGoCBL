package Controller;

import Modelo.Captura;
import Modelo.Localizacion;

import javax.inject.Singleton;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ivanm on 17/06/2017.
 */

@Path("/localizacion")
public class LocalizacionController {

    @Singleton
    public  LocalizacionController(){

    }

    @GET
    @Path("/get_all")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllLocalizaciones(){
        try{
            List<Localizacion> localizacionList;
            Localizacion localizacion = new Localizacion();
            localizacionList = localizacion.getAllLocalizaciones();

            GenericEntity<List<Localizacion>> entity = new GenericEntity<List<Localizacion>>(localizacionList) {
            };
            return Response.status(201).entity(entity).build();

        }
        catch (Exception ex){
            String noResult = "Error.";
            return Response.status(404).entity(noResult).build();
        }
    }

}
