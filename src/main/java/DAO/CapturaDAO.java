package DAO;

import DAO.LocalizacionDAO;
import DAO.EtakemonDAO;
import Modelo.Captura;
import Modelo.Etakemon;
import Modelo.Localizacion;
import org.apache.log4j.Logger;

import java.lang.reflect.Field;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

import static org.apache.log4j.Logger.getLogger;

/**
 * Created by ivanm on 14/05/2017.
 */


public abstract class CapturaDAO extends DAO {

  /*  final Logger logger = getLogger("CapturaDAO");

    // crear capturas para mostrar (cogiendo localizaciones y etakemons)
    public List<Captura> createCapturaSpawns(){
        List<Captura> capturaList = new ArrayList<Captura>();
        LocalizacionDAO localizacionDAO = new LocalizacionDAO() {};
        EtakemonDAO etakemonDAO = new EtakemonDAO(){};

        Random random = new Random(){};

        List<Localizacion> listaLocalizaciones = new ArrayList<Localizacion>();

        try {
            Statement stmt = null;
            stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM Localizacion");
            while (rs.next()) {
                Localizacion localizacion = new Localizacion();
                localizacion.setId(rs.getInt("id"));
                localizacion.setNombre(rs.getString("nombre"));
                localizacion.setLatitud(rs.getDouble("latitud"));
                localizacion.setLongitud(rs.getDouble("longitud"));
                listaLocalizaciones.add(localizacion);
            }
        }
        catch (SQLException e){
            e.printStackTrace();
            logger.error("getAllLocalizaciones: " +e.getMessage());
        }



        for (int i=0; i< 20; i++){
            Captura captura = new Captura();

            //coger ids de etakemons al azar
            int whichEtakemon = (int) Math.floor(Math.random()*(etakemonDAO.getAllEtakemon()).size());

            int whichLocalizacion = (int) Math.floor(Math.random()*(listaLocalizaciones).size());
            listaLocalizaciones.remove(whichLocalizacion);
            //coger id de localizacion al azar
            System.out.println("etakemon: "+whichEtakemon+"    localizacion: "+whichLocalizacion);


        }


      /*  for (int x=0; x<localizacionDAO.getAllLocalizaciones().size(); x++){
            localizacionList.add(localizacionDAO.getAllLocalizaciones().get(x));
        }
        for (int i=0; i< 20; i++){
            Captura captura = new Captura();

            //coger ids de etakemons al azar
            int whichEtakemon = (int) Math.floor(Math.random()*(etakemonDAO.getAllEtakemon()).size());

            //coger id de localizacion al azar
            int whichLocalizacion = (int) Math.floor(Math.random()*(localizacionList).size());
            System.out.println("etakemon: "+whichEtakemon+"    localizacion: "+whichLocalizacion);


        }

        return capturaList;
    }

    */


/*

    //añadirlas al usuario (calculando los atributos)
    public boolean insert(Captura captura){
        boolean capturado = false;

        //añadir algoritmos para calcular nivel, ataque etc



        return capturado;
    }

    */

    public List<Captura> getAllCapturas()  {
        List<Captura> listaCaptura = new ArrayList<Captura>();

        try {
            Statement stmt = null;
            stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM Captura");
            while (rs.next()) {
                Captura captura = new Captura();
                captura.setId(rs.getInt("id"));
                captura.setIdusuariosss(rs.getInt("idusuariosss"));
                captura.setIdetakemon(rs.getInt("idetakemon"));
                captura.setNivel(rs.getInt("nivel"));
                captura.setExperiencia(rs.getInt("experiencia"));
                captura.setVida(rs.getInt("vida"));
                captura.setAtaque(rs.getInt("ataque"));
                captura.setDefensa(rs.getInt("defensa"));
                captura.setEstado(rs.getInt("estado"));
                captura.setFecha(rs.getDate("fecha"));
                listaCaptura.add(captura);
            }
        }
        catch (SQLException e){
            e.printStackTrace();
            logger.error("getAllCapturas: "+e.getMessage());
        }
        return listaCaptura;
    }
}
