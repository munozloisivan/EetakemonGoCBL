package Modelo;

/**
 * Created by ivanm on 24/04/2017.
 */
public class Eetakemon {

    private int id;
    private String nombre;
    private int experiencia, habilidad, tipo;

    public Eetakemon(int id, String nombre, int experiencia, int habilidad, int tipo) {
        this.id = id;
        this.nombre = nombre;
        this.experiencia = experiencia;
        this.habilidad = habilidad;
        this.tipo = tipo;
    }

    public Eetakemon(){};

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

    public int getExperiencia() {
        return experiencia;
    }

    public void setExperiencia(int experiencia) {
        this.experiencia = experiencia;
    }

    public int getHabilidad() {
        return habilidad;
    }

    public void setHabilidad(int habilidad) {
        this.habilidad = habilidad;
    }

    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }
}
