package Modelo;

import java.sql.Time;

/**
 * Created by ivanm on 24/04/2017.
 */
public class Cofre {

    private int id;
    private String nombre, descripcion;
    private Time timer;                //habrá que modificarlo seguramente

    public Cofre(int id, String nombre, String descripcion, Time timer) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.timer = timer;
    }

    public Cofre(){}

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

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Time getTimer() {
        return timer;
    }

    public void setTimer(Time timer) {
        this.timer = timer;
    }
}
