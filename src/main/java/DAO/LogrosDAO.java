package DAO;

import Modelo.Logros;
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
public abstract class LogrosDAO extends DAO {

    final Logger logger = getLogger("LogrosDAO");

    public List<Logros> getAllLogros(){
        List<Logros> listaLogros = new ArrayList<Logros>();
        try {
            Statement stmt = null;
            stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM logros");
            while (rs.next()) {
                Logros logros = new Logros();
                logros.setId(rs.getInt("id"));
                logros.setNombre(rs.getString("nombre"));
                logros.setDescripcion(rs.getString("descripcion"));
                logros.setExperiencia(rs.getInt("experiencia"));
                listaLogros.add(logros);
            }
        }
        catch (SQLException e){
            e.printStackTrace();
            logger.error("getAllLogros: "+e.getMessage());
        }
        return listaLogros;
    }
}
