package Modelo;

import java.sql.Date;

/**
 * Created by ivanm on 24/04/2017.
 */
public class Captura {

    private int id, idusuario, ideetakemon, nivel, experiencia, vida, ataque, defensa, estado;
    private Date fecha;

    public Captura(int id, int idusuario, int ideetakemon, int nivel, int experiencia, int vida, int ataque, int defensa, int estado, Date fecha) {
        this.id = id;
        this.idusuario = idusuario;
        this.ideetakemon = ideetakemon;
        this.nivel = nivel;
        this.experiencia = experiencia;
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

    public int getIdeetakemon() {
        return ideetakemon;
    }

    public void setIdeetakemon(int ideetakemon) {
        this.ideetakemon = ideetakemon;
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
