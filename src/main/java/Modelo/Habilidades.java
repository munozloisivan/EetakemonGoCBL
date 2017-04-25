package Modelo;

/**
 * Created by ivanm on 24/04/2017.
 */
public class Habilidades {

    private int id;
    private String nombre, descripcion;
    private int potencia;


    public Habilidades(int id, String nombre, String descripcion, int potencia) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.potencia = potencia;
    }

    public Habilidades(){}

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

    public int getPotencia() {
        return potencia;
    }

    public void setPotencia(int potencia) {
        this.potencia = potencia;
    }
}


