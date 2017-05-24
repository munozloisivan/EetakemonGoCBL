package Controller;

import Modelo.Etakemon;

import javax.inject.Singleton;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

/**
 * Created by ivanm on 20/05/2017.
 */

@Path("/eetakemon")
public class EetakemonController {

    @Singleton
    public EetakemonController(){

    }

    @GET
    @Path("/got_id/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Etakemon getEtakemonbyId(@PathParam("id") int id){
        Etakemon etakemon = new Etakemon();
        etakemon.select(id);
        return etakemon;
    }


    @GET
    @Path("/get_all")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Etakemon> getEtakemons(){
        Etakemon etakemon = new Etakemon();
        return etakemon.getAllEtakemon();
    }

    @POST
    @Path("/new")
    @Consumes(MediaType.APPLICATION_JSON)
    public boolean createEtakemonInJSON(Etakemon etakemon){

        if (etakemon.insert()){
            return true;
        }
        else {
            return false;
        }
    }

    @POST
    @Path("/delete/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response deleteEtakemonInJSON(@PathParam("id") int id) {
        Etakemon etakemon = new Etakemon();

        if (etakemon.select(id)!=null){
            etakemon.delete(id);
            String yesResult = "Etakemon eliminado.";
            return Response.status(201).entity(yesResult).build();
        }
        else {
            String noResult = "El id no existe.";
            return Response.status(418).entity(noResult).build();
        }
    }

}
