package Controller;

import javax.mail.MessagingException;
import javax.ws.rs.Path;

import Modelo.Captura;
import Modelo.Logros;
import Modelo.Objetos;
import Modelo.Usuario;
import javax.inject.Singleton;
import javax.ws.rs.*;
import javax.ws.rs.core.GenericEntity;
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

//    @GET
//    @Path("/{id}/get_objetos")
//    @Produces(MediaType.APPLICATION_JSON)
//    public List<Objetos> getObjetosUsuario(@PathParam("id") int idUsuario){
//
//        Usuario lista = new Usuario();
//        return lista.getObjetosUsuario(idUsuario);
//    }

//    @GET
//    @Path("/{id}/get_capturas")
//    @Produces(MediaType.APPLICATION_JSON)
//    public List<Captura> getCapturasUsuario(@PathParam("id") int idUsuario){
//
//        Usuario lista = new Usuario();
//        return lista.getCapturasUsuario(idUsuario);
//    }

    @GET
    @Path("/{id}/get_capturas")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCapturasUsuario(@PathParam("id") int idUsuario){

        Usuario usuario = new Usuario();
        if (idUsuario != 0){
            Usuario usuario2 = new Usuario();
            List<Captura> capturaList =  usuario2.getCapturasUsuario(idUsuario);
            for (int i = 0; i < capturaList.size(); i++){
                System.out.println(capturaList.get(i).getNombreetakemon());
            }
            GenericEntity<List<Captura>> entity = new GenericEntity<List<Captura>>(capturaList) {};
            return Response.status(201).entity(entity).build();
        }
        else
        {
            String noResult = "No tiene capturas.";
            return Response.status(404).entity(noResult).build();
        }
    }

    @GET
    @Path("/{email}/logros")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getLogrosUsuario(@PathParam("email") String email ){
        Usuario usuario = new Usuario();
        if (email!=null){
           List<Logros> logrosList = usuario.getLogrosUsuario(email);
           GenericEntity<List<Logros>> entity = new GenericEntity<List<Logros>>(logrosList){};
            return Response.status(201).entity(entity).build();
        }
        else {
            String noResult = "Email incorrecto.";
            return Response.status(404).entity(noResult).build();
        }
    }

    @GET
    @Path("/got_id/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Usuario getUsuariobyId(@PathParam("id") int id){

        Usuario finded = new Usuario();
        finded = finded.selectbyID(id);
        return finded;
    }

    @GET
    @Path("/got_email/{email}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUsuarioByEmail(@PathParam("email") String email){

        if (email!=null){
        Usuario finded = new Usuario();
        finded = finded.select(email);
        return Response.status(201).entity(finded).build();
        }
        else {
            String noResult = "Email incorrecto.";
            return Response.status(404).entity(noResult).build();
        }
    }

    @GET
    @Path("/{email}/objetos")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getObjetosUsuario(@PathParam("email") String email){
        if (email!=null){
            Usuario usuario = new Usuario();

            List<Objetos> objList = usuario.getObjetosUsuario(email);
            GenericEntity<List<Objetos>> entity = new GenericEntity<List<Objetos>>(objList){};
            return Response.status(201).entity(entity).build();
        }
        else {
            String noResult = "Email incorrecto.";
            return Response.status(404).entity(noResult).build();
        }

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

        if (u.selectbyID(in.getId())!=null){
            u.updateUsuarioData(in.getId(),in.getNick(),in.getContrasena());
            String yesResult = "Usuario editado.";
            return Response.status(201).entity(yesResult).build();
        }
        else {
            String noResult = "El id no existe.";
            return Response.status(418).entity(noResult).build();
        }
    }

    @POST
    @Path("/admin_edit")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response editUserByAdmin(Usuario in) {
        Usuario u = new Usuario();

        if (u.select(in.getId())!=null){
            u.updateUsuarioDataByAdmin(in.getId(),in.getNick(),in.getContrasena(),in.getEmail(),in.getNombre());
            String yesResult = "Usuario editado.";
            return Response.status(201).entity(yesResult).build();
        }
        else {
            String noResult = "El id no existe.";
            return Response.status(418).entity(noResult).build();
        }
    }

    @POST
    @Path("/{email}/datos")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response recuperardatos(@PathParam("email") String email){
        String emailAEnviar = email;
        Usuario prueba = new Usuario();
        Boolean finded = false;
        for (int i=0; i< prueba.getAllUsers().size(); i++){
            if (emailAEnviar.equals(prueba.getAllUsers().get(i).getEmail())){
                finded = true;
            }
        }
        if (finded){
            EnviarMail enviarMail = new EnviarMail();
            try {
                Usuario usuario = new Usuario();
                String datosPersonales = usuario.datosRecuperados(email);
                enviarMail.enviarMensaje("Datos EetakemonGoCBL",datosPersonales,email);
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
