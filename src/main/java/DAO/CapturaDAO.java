package DAO;

import DAO.LocalizacionDAO;
import DAO.EtakemonDAO;
import Modelo.Captura;
import Modelo.Etakemon;
import Modelo.Localizacion;
import Modelo.Usuario;
import org.apache.log4j.Logger;

import java.lang.reflect.Field;
import java.sql.*;
import java.sql.Date;
import java.util.*;

import static org.apache.log4j.Logger.getLogger;

/**
 * Created by ivanm on 14/05/2017.
 */


public abstract class CapturaDAO extends DAO {

    final Logger logger = getLogger("CapturaDAO");

    // crear capturas para mostrar (cogiendo localizaciones y etakemons)
    public List<Captura> createCapturaSpawns() {

        List<Captura> capturaList = new ArrayList<Captura>();
        Captura captura;
        Etakemon etakemon = new Etakemon();
        Localizacion localizacion = new Localizacion();

        for (int i=0; i<20;i++) {
            int idetakemon = etakemon.getRandomEtakemon();
            int idlocalizacion = localizacion.getRandomLocalizacion();
            captura = new Captura(idetakemon,idlocalizacion);
            capturaList.add(captura);
        }
        return capturaList;
    }


    /*public boolean insertarCaptura(Captura captura, int idusuario){
        boolean capturaInsertada = false;

        Captura capturaInsertar = new Captura();


        //la captura que nos envian tiene el idetakemon y el idlocalizacion fijados al crear el spawn
        //recibimos el idusuario para saber a que usuario se lo insertamos ( sera el que esta logeado)
        //hacer sets en captura insertar

        int nivel,experiencia,vida,ataque,defensa,estado=1;
        int tipo;
        Date fecha = new java.sql.Date(Calendar.getInstance().getTime().getTime());

        //definir que metodologia se usará para asignar
        //nivel, experiencia, vida, ataque, defensa
        // vida, ataque, defensa --> en funcion del tipo (1=Ataque+; 2=Defensa+; 3=Salud+)
        //experiencia podria ser x ej 100 por cada captura
        //nivel en funcion del nivel del usuario +- unos niveles de dif random


        //recoger los datos del usuario en cuestion y analizarlos
        //para asignar sus atributos en funcion de su NIVEL y EXPERIENCIA

        Usuario usuario = new Usuario();
        usuario.select(idusuario);


        //recoger los datos del etakemon en cuestion y analizarlos
        //para definir atributos en funcion de su TIPO

        Etakemon etakemon = new Etakemon();


        capturaInsertar.setIdusuariosss(idusuario);
        capturaInsertar.setIdetakemon(captura.getIdetakemon());
        capturaInsertar.setIdlocalizacion(captura.getIdlocalizacion());
        capturaInsertar.setNivel(nivel);
        capturaInsertar.setExperiencia(experiencia);
        capturaInsertar.setVida(vida);
        capturaInsertar.setAtaque(ataque);
        capturaInsertar.setDefensa(defensa);
        capturaInsertar.setEstado(estado);


            StringBuffer stringBuffer = new StringBuffer("INSERT into Captura (idetakemon,idlocalizacion) VALUES (");



            try {
                PreparedStatement preparedStatement = con.prepareStatement(stringBuffer.toString());
                preparedStatement.execute();
                capturaInsertada = true;
            } catch (SQLException e) {
                capturaInsertada = false;
                e.printStackTrace();
                logger.error(e.getMessage());
            }

        return capturaInsertada;
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

/*
    //añadirlas al usuario (calculando los atributos)
    public boolean insert(Captura captura){
        boolean capturado = false;

        //añadir algoritmos para calcular nivel, ataque etc



        return capturado;
    }
    */


}
