package Modelo;

import DAO.EtakemonDAO;

/**
 * Created by ivanm on 24/04/2017.
 */
public class Etakemon extends EtakemonDAO{

    private int id;
    private String nombre, habilidad, imagen, descripcion;
    private int tipo;

    public Etakemon(int id, String nombre, String habilidad, String imagen, String descripcion, int tipo) {
        this.id = id;
        this.nombre = nombre;
        this.habilidad = habilidad;
        this.imagen = imagen;
        this.descripcion = descripcion;
        this.tipo = tipo;
    }

    public Etakemon(int id, String nombre, String habilidad, int tipo) {
        this.id = id;
        this.nombre = nombre;
        this.habilidad = habilidad;
        this.tipo = tipo;
    }

    public Etakemon(String nombre, String habilidad, int tipo){
        this.nombre = nombre;
        this.habilidad = habilidad;
        this.tipo = tipo;
    }

    public Etakemon(){}

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

    public String getHabilidad() {
        return habilidad;
    }

    public void setHabilidad(String habilidad) {
        this.habilidad = habilidad;
    }

    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
