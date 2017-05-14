package Modelo;

import DAO.UsuarioDAO;

/**
 * Created by ivanm on 24/04/2017.
 */
public class Usuario extends UsuarioDAO {

    private int id;
    private String nombre, nick, email, contrasena;
    private int nivel, experiencia, modified;

    public Usuario(int id, String nombre, String nick, String email, String contrasena, int nivel, int experiencia, int modified) {
        this.id = id;
        this.nombre = nombre;
        this.nick = nick;
        this.email = email;
        this.contrasena = contrasena;
        this.nivel = nivel;
        this.experiencia = experiencia;
        this.modified = modified;
    }

    public Usuario(String nombre, String nick, String email, String contrasena, int nivel, int experiencia, int modified){
        this.nombre = nombre;
        this.nick = nick;
        this.email = email;
        this.contrasena = contrasena;
        this.nivel = nivel;
        this.experiencia = experiencia;
        this.modified = modified;
    }

    public Usuario(){}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public int getNivel() {
        return nivel;
    }

    public void setNivel(int nivel) {
        this.nivel = nivel;
    }

    public int getExperiencia() {
        return experiencia;
    }

    public void setExperiencia(int experiencia) {
        this.experiencia = experiencia;
    }

    public int getModified() {
        return modified;
    }

    public void setModified(int modified) {
        this.modified = modified;
    }
}
