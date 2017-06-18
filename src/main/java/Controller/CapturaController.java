package Controller;

import Modelo.Captura;

import javax.inject.Singleton;
import javax.ws.rs.*;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ivanm on 17/06/2017.
 */

@Path("/captura")

public class CapturaController {

    @Singleton
    public CapturaController(){

    }

    @GET
    @Path("/get_generated")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getGeneratedCapturas(){

        try {
            List<Captura> randomList = new ArrayList<>();
            Captura captura = new Captura();
            randomList = captura.createCapturaSpawns();
            GenericEntity<List<Captura>> entity = new GenericEntity<List<Captura>>(randomList) {
            };
            return Response.status(201).entity(entity).build();
        }
        catch (Exception ex){
            String noResult = "No tiene capturas.";
            return Response.status(404).entity(noResult).build();
        }
    }

    @POST
    @Path("/{iduser}/capture")
    @Produces(MediaType.APPLICATION_JSON)
    public Response setCapturaToUsuario(@PathParam("iduser") int iduser, Captura captura)
    {
        Captura nuevaCaptura = new Captura();
        nuevaCaptura.insertarCaptura(captura,iduser);
        return Response.status(201).entity(nuevaCaptura).build();
    }
}
