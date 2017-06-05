package Controller;

import Modelo.Etakemon;
import Modelo.Usuario;

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
        etakemon = etakemon.selectbyID(id);
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
    @Path("/admin_edit")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response editEetakemonByAdmin(Etakemon in) {
        Etakemon u = new Etakemon();

        if (u.selectbyID(in.getId())!=null){
            u.updateEetakemonDataByAdmin(in.getId(),in.getNombre(),in.getHabilidad(),in.getDescripcion(),in.getImagen(),in.getTipo());
            String yesResult = "Usuario editado.";
            return Response.status(201).entity(yesResult).build();
        }
        else {
            String noResult = "El id no existe.";
            return Response.status(418).entity(noResult).build();
        }
    }

//Funcion para insertar Etakemon
    @POST
    @Path("/new")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createEetakemonInJSON(Etakemon etakemon) {

        if (etakemon.insert(etakemon)) {
            String yesResult = "Eetakemon insertado.";
            return Response.status(201).entity(yesResult).build();
        }
        else{
            String noResult = "Error no existe.";
            return Response.status(400).entity(noResult).build();
        }
    }

//Funcion para eliminar Etakemon
    @GET
    @Path("/delete/{id}")
    public Response deleteEetakemonInJSON(@PathParam("id") int id) {
        Etakemon u = new Etakemon();

        if (u.selectbyID(id)!=null){
            u.delete(id);
            String yesResult = "Eetakemon eliminado.";
            return Response.status(201).entity(yesResult).build();
        }
        else {
            String noResult = "El id no existe.";
            return Response.status(404).entity(noResult).build();
        }
    }

}
