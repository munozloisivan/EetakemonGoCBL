package Controller;



import DAO.UsuarioDAO;
import Modelo.Usuario;

import javax.inject.Singleton;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by Roberto on 21/03/2017.
 */

@Path("/json")
public class JSONController {

    protected List<Usuario> usuarios;

  /*  @Singleton
    public JSONController() throws SQLException {
        usuarios = Usuario.getAllUsers();
    }
*/

    @GET
    @Path("/usuario/got_id/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Usuario getUsuariobyId(@PathParam("id") int id){

        Usuario finded = new Usuario();

        finded.select(id);
        /*for (int i = 0; i<usuarios.size(); i++){
            if (usuarios.get(i).getId() == id){
                finded = usuarios.get(i);
            }
        }*/
        return finded;
    }

    @GET
    @Path("/usuario/got_email/{email}")
    @Produces(MediaType.APPLICATION_JSON)
    public Usuario getUsuarioByEmail(@PathParam("email") String email){

        Usuario finded = new Usuario();

        for (int i = 0; i<usuarios.size(); i++){
            if (usuarios.get(i).getEmail().equalsIgnoreCase(email)){
                finded = usuarios.get(i);
            }
        }
        return finded;
    }

    @POST
    @Path("/usuario/login")
    @Produces(MediaType.APPLICATION_JSON)
    public Usuario getLogin(Usuario usuario){

        Usuario finded = new Usuario();

        for (int i = 0; i<usuarios.size(); i++){
            if ((usuarios.get(i).getEmail().equalsIgnoreCase(usuario.getEmail())&&(usuarios.get(i).getContrasena()).equals(usuario.getContrasena()))){
                finded = usuarios.get(i);
                return null;
            }
        }
        return finded;
    }

    @GET
    @Path("/usuario/get_all")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Usuario> getUsuarios(){
        return this.usuarios;
    }

    @POST
    @Path("/usuario/new")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createUsuarioInJSON(Usuario usuario) {

        if (validateRegister(usuario.getEmail())){
            usuario.insert();
            String yesResult = "Usuario guardado: " + usuario.getNombre();
            return Response.status(201).entity(yesResult).build();
        }
            String noResult = "El email ya esta registrado";
            return Response.status(418).entity(noResult).build();
    }

    /*@POST
    @Path("/usuario/{idUsuario}/hire/oficina/{idOficina}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response assignUsuarioToOficina(@PathParam("idUsuario") int idUsuario,@PathParam("idOficina") int idOficina, Usuariosoficina usuariosoficina) {

        if (searchIDOficina(idOficina)){

            if (searchIDUsuario(idUsuario)){
            usuariosoficina.setIdoficina(idOficina);
            usuariosoficina.setIdusuario(idUsuario);
            usuariosoficina.insert();
                String yesResult = "Usuario " + getUsuariobyId(idUsuario).getNombre()+ " asociado a la Oficina " + getOficinaById(idOficina).getNombre();
                return Response.status(201).entity(yesResult).build();
            }
        }
            String noResult = "La oficina o el usuario no existen";
            return Response.status(418).entity(noResult).build();
    }*/

    @POST
    @Path("/usuario/delete/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response deleteUsuarioInJSON(@PathParam("id") int id) {

        if (searchIDUsuario(id)){
            Usuario u = new Usuario();
            u.delete(id);
            String yesResult = "Usuario eliminado.";
            return Response.status(201).entity(yesResult).build();
        }
        String noResult = "El id no existe.";
        return Response.status(418).entity(noResult).build();
    }

    /*@GET
    @Path("/oficina/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Oficina getOficinaById(@PathParam("id") int id){

        Oficina finded = new Oficina();

        for (int i = 0; i<oficinas.size(); i++){
            if (oficinas.get(i).getId() == id){
                finded = oficinas.get(i);
            }
        }
        return finded;
    }

    @GET
    @Path("/oficina/get_all")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Oficina> getOficinas(){
        return this.oficinas;
    }

    @POST
    @Path("/oficina/new")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createOficinaInJSON(Oficina oficina) {

        oficina.insert();
        String yesResult = "Oficina guardada: " + oficina.getNombre();
        return Response.status(201).entity(yesResult).build();
    }*/

    public boolean validateRegister(String email){

        for (int i = 0; i<usuarios.size(); i++)
        {
            if (usuarios.get(i).getEmail().equalsIgnoreCase(email))
            {
                return false;
            }
        }
        return true;
    }

    public boolean searchIDUsuario(int id){
        for (int i = 0; i<usuarios.size(); i++)
        {
            if (usuarios.get(i).getId() ==id)
            {
                return true;
            }
        }
        return false;
    }

    /*public boolean searchIDOficina(int id){
        for (int i = 0; i<oficinas.size(); i++)
        {
            if (oficinas.get(i).getId()==id)
            {
                return true;
            }
        }
        return false;
    }*/
}
