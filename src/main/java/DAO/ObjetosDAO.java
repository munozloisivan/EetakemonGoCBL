package DAO;

import Modelo.Objetos;
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
public class ObjetosDAO extends DAO {

    final Logger logger = getLogger("LogrosDAO");


    public List<Objetos> getAllObjects() {
        List<Objetos> listaObjetos = new ArrayList<Objetos>();
        try {
            Statement stmt = null;
            stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM Objetos");
            while (rs.next()) {
                Objetos ob = new Objetos();
                ob.setId(rs.getInt("id"));
                ob.setNombre(rs.getString("nombre"));
                ob.setDescripcion(rs.getString("descripcion"));
                listaObjetos.add(ob);
            }
        }
        catch (SQLException e){
            e.printStackTrace();
            logger.error("getAllObjects: "+e.getMessage());
        }
        return listaObjetos;
    }
}
