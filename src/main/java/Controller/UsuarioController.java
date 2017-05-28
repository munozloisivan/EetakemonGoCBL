package Controller;

import javax.mail.MessagingException;
import javax.ws.rs.Path;

import Modelo.Captura;
import Modelo.Objetos;
import Modelo.Usuario;

import javax.inject.Singleton;
import javax.ws.rs.*;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by Roberto on 20/05/2017.
 */
@Path("/usuario")

public class UsuarioController {

    @Singleton
    public UsuarioController(){

    }

    @GET
    @Path("/get_all")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Usuario> getUsuarios(){

        Usuario lista = new Usuario();
        return lista.getAllUsers();
    }

    @GET
    @Path("/{id}/get_objetos")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Objetos> getObjetosUsuario(@PathParam("id") int idUsuario){

        Usuario lista = new Usuario();
        return lista.getObjetosUsuario(idUsuario);
    }

    @GET
    @Path("/{id}/get_capturas")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Captura> getCapturasUsuario(@PathParam("id") int idUsuario){

        Usuario lista = new Usuario();
        return lista.getCapturasUsuario(idUsuario);
    }

    @GET
    @Path("/got_id/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Usuario getUsuariobyId(@PathParam("id") int id){

        Usuario finded = new Usuario();
        finded.select(id);
        return finded;
    }

    @GET
    @Path("/got_email/{email}")
    @Produces(MediaType.APPLICATION_JSON)
    public Usuario getUsuarioByEmail(@PathParam("email") String email){

        Usuario finded = new Usuario();
        finded = finded.select(email);
        return finded;
    }

    @POST
    @Path("/login")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getLogin(Usuario usuario) throws SQLException {

        if (usuario.login(usuario.getEmail(),usuario.getContrasena())){
            Usuario loged = new Usuario();
            loged = loged.select(usuario.getEmail());
            return Response.status(201).entity(loged).build();

        }
        else
        {
            return Response.status(404).entity(usuario).build();
        }
    }

    @POST
    @Path("/new")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createUsuarioInJSON(Usuario usuario) {

        Usuario registred = new Usuario();
        if (usuario.insert(usuario)) {
            registred = registred.select(usuario.getEmail());
            return Response.status(201).entity(registred).build();
        }
        else{
            registred = registred.select(usuario.getEmail());
            return Response.status(400).entity(registred).build();
        }
    }

    @GET
    @Path("/delete/{id}")
    public Response deleteUsuarioInJSON(@PathParam("id") int id) {
        Usuario u = new Usuario();

        if (u.select(id)!=null){
            u.delete(id);
            String yesResult = "Usuario eliminado.";
            return Response.status(201).entity(yesResult).build();
        }
        else {
            String noResult = "El id no existe.";
            return Response.status(404).entity(noResult).build();
        }
    }

    @POST
    @Path("/edit")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response editUser(Usuario in) {
        Usuario u = new Usuario();

        if (u.select(in.getId())!=null){
            u.updateUsuarioData(in.getId(),in.getEmail(),in.getContrasena());
            String yesResult = "Usuario editado.";
            return Response.status(201).entity(yesResult).build();
        }
        else {
            String noResult = "El id no existe.";
            return Response.status(418).entity(noResult).build();
        }
    }

    @POST
    @Path("/datos")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response recuperardatos(Usuario usuario){
        String emailAEnviar = usuario.getEmail();
        if (emailAEnviar != null){
            EnviarMail enviarMail = new EnviarMail();
            try {
                enviarMail.enviarMensaje("Datos EetakemonGoCBL","estaestucontraseña");
            } catch (MessagingException e) {
                e.printStackTrace();
            }
            String yesResult = "Datos enviados.";
            return Response.status(201).entity(yesResult).build();
        }
        else {
            String noResult = "El email no existe.";
            return Response.status(418).entity(noResult).build();
        }

    }



}
