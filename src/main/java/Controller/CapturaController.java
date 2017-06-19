package Controller;

import Modelo.Captura;
import retrofit2.http.POST;

import javax.inject.Singleton;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ivanm on 17/06/2017.
 */

@Path("/captura")

public class CapturaController {

    @Singleton
    public CapturaController() {

    }

    @GET
    @Path("/delete/{id}")
    public Response deleteCaptura(@PathParam("id") int id) {
        Captura c = new Captura();

        if (c.select(id) != null) {
            c.delete(id);
            String yesResult = "Captura eliminado.";
            return Response.status(201).entity(yesResult).build();
        } else {
            String noResult = "El id no existe.";
            return Response.status(404).entity(noResult).build();
        }
    }

    @GET
    @Path("/get_generated")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getGeneratedCapturas() {

        try {
            List<Captura> randomList = new ArrayList<>();
            Captura captura = new Captura();
            randomList = captura.createCapturaSpawns();
            GenericEntity<List<Captura>> entity = new GenericEntity<List<Captura>>(randomList) {
            };
            return Response.status(201).entity(entity).build();
        } catch (Exception ex) {
            String noResult = "No tiene capturas.";
            return Response.status(404).entity(noResult).build();
        }

    }


    @POST
    @Path("/{iduser}/capture")
    @Produces(MediaType.APPLICATION_JSON)
    public Response setCapturaToUsuario(@PathParam("iduser") int iduser, Captura captura) throws SQLException {
        if (captura.insertarCaptura(captura, iduser))
            return Response.status(201).entity("insertado").build();

        if (captura.insertarCaptura(captura, iduser)) {
            return Response.status(201).entity("insertado").build();
        } else {
            return Response.status(404).entity("error insertar captura").build();
        }
    }
}
