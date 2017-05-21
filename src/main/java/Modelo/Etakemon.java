package Modelo;

import DAO.EtakemonDAO;

/**
 * Created by ivanm on 24/04/2017.
 */
public class Etakemon extends EtakemonDAO{

    private int id;
    private String nombre;
    private String habilidad;
    private int tipo;

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
}
