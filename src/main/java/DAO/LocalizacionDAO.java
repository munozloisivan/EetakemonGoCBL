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
            ResultSet rs = stmt.executeQuery("SELECT * FROM localizacion");
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

    public Localizacion selectbyID(int id) {
        Localizacion localizacion = new Localizacion();

        StringBuffer stringBuffer = new StringBuffer("SELECT * FROM etakemon WHERE id ='" + id + "';");
        try {
            Statement statement = con.createStatement();
            ResultSet resultSet = statement.executeQuery(stringBuffer.toString());
            resultSet.next();

            localizacion.setId(resultSet.getInt("id"));
            localizacion.setNombre(resultSet.getString("nombre"));
            localizacion.setLatitud(resultSet.getDouble("latitud"));
            localizacion.setLongitud(resultSet.getDouble("longitud"));

        } catch (SQLException e) {
            e.printStackTrace();
            logger.error(e.getMessage());
        }
        return localizacion;
    }

    public int getRandomIDLocation(){
        int whichLocalizacion = (int) Math.floor(Math.random()*((getAllLocalizaciones().size())-1+1)+1);
        return whichLocalizacion;
    }

    //generador aleatorio de Localizaciones para generar capturas
    public Localizacion getRandomLocalizacion(){
        Localizacion loca = new Localizacion();
        loca = loca.selectbyID(getRandomIDLocation());

        return loca;
    }
}
