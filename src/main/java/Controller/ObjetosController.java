package Controller;

import Modelo.Etakemon;
import Modelo.Objetos;
import Modelo.Usuario;

import javax.inject.Singleton;
import javax.ws.rs.*;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

/**
 * Created by ivanm on 05/06/2017.
 */

@Path("/objetos")
public class ObjetosController {

    @Singleton
    public ObjetosController(){

    }

    @GET
    @Path("/get_all")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllObjects(){

        try{

            Objetos objetos = new Objetos();
            List<Objetos> objetosList = objetos.getAllObjects();
            GenericEntity<List<Objetos>> entity = new GenericEntity<List<Objetos>>(objetosList){};
            return Response.status(201).entity(entity).build();
        }

        catch (Exception e){
            String noResult = "Error.";
            return Response.status(404).entity(noResult).build();
        }

    }

}
