package Modelo;

import java.sql.Date;

/**
 * Created by ivanm on 24/04/2017.
 */
public class Captura {

    private int id;
    private int idusuario;
    private int idetakemon;
    private int vida;
    private int ataque;
    private int defensa;
    private int estado;
    private Date fecha;

    public Captura(int id, int idusuario, int idetakemon, int vida, int ataque, int defensa, int estado, Date fecha) {
        this.id = id;
        this.idusuario = idusuario;
        this.idetakemon = idetakemon;
        this.vida = vida;
        this.ataque = ataque;
        this.defensa = defensa;
        this.estado = estado;
        this.fecha = fecha;
    }

    public Captura(){}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdusuario() {
        return idusuario;
    }

    public void setIdusuario(int idusuario) {
        this.idusuario = idusuario;
    }

    public int getIdetakemon() {
        return idetakemon;
    }

    public void setIdetakemon(int idetakemon) {
        this.idetakemon = idetakemon;
    }

    public int getVida() {
        return vida;
    }

    public void setVida(int vida) {
        this.vida = vida;
    }

    public int getAtaque() {
        return ataque;
    }

    public void setAtaque(int ataque) {
        this.ataque = ataque;
    }

    public int getDefensa() {
        return defensa;
    }

    public void setDefensa(int defensa) {
        this.defensa = defensa;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }
}
