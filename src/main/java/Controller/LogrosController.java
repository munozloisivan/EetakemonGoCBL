package Controller;

import Modelo.Logros;

import javax.inject.Singleton;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

/**
 * Created by ivanm on 20/05/2017.
 */

@Path("/logros")
public class LogrosController {

    @Singleton
    public LogrosController(){

    }

    @GET
    @Path("/got_id/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Logros getLogroById(@PathParam("id") int id){
        Logros logros = new Logros();
        logros.select(id);
        return logros;
    }


    @GET
    @Path("/get_all")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Logros> getLogros(){
        Logros logros = new Logros();
        return logros.getAllLogros();
    }

    @POST
    @Path("/new")
    @Consumes(MediaType.APPLICATION_JSON)
    public boolean createLogroInJSON(Logros logros){

        if (logros.insert()){
            return true;
        }
        else {
            return false;
        }
    }

    @POST
    @Path("/delete/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response deleteLogroInJSON(@PathParam("id") int id) {
        Logros logros = new Logros();

        if (logros.select(id)!=null){
            logros.delete(id);
            String yesResult = "Logro eliminado.";
            return Response.status(201).entity(yesResult).build();
        }
        else {
            String noResult = "El id no existe.";
            return Response.status(418).entity(noResult).build();
        }
    }

}
