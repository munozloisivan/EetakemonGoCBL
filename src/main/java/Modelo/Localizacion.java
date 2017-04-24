package Modelo;

/**
 * Created by ivanm on 24/04/2017.
 */
public class Localizacion {

    private int id, idcaptura;
    private double latitud, longitud;

    public Localizacion(int id, int idcaptura, double latitud, double longitud) {
        this.id = id;
        this.idcaptura = idcaptura;
        this.latitud = latitud;
        this.longitud = longitud;
    }

    public Localizacion(){}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdcaptura() {
        return idcaptura;
    }

    public void setIdcaptura(int idcaptura) {
        this.idcaptura = idcaptura;
    }

    public double getLatitud() {
        return latitud;
    }

    public void setLatitud(double latitud) {
        this.latitud = latitud;
    }

    public double getLongitud() {
        return longitud;
    }

    public void setLongitud(double longitud) {
        this.longitud = longitud;
    }
}
