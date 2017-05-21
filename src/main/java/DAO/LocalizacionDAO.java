package DAO;

import Modelo.Localizacion;
import org.apache.log4j.Logger;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import static org.apache.log4j.Logger.getLogger;

/**
 * Created by ivanm on 14/05/2017.
 */
public abstract class LocalizacionDAO extends DAO {

    final Logger logger = getLogger("LocalizacionDAO");


    public List<Localizacion> getAllLocalizaciones()  {
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
        return listaLocalizaciones;
    }


    //generador aleatorio de Localizaciones para generar capturas
    public int getRandomLocalizacion(){
        int whichLocalizacion = (int) Math.floor(Math.random()*((getAllLocalizaciones().size())-1+1)+1);
        return whichLocalizacion;
    }
}
