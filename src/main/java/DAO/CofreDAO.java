package DAO;

import Modelo.Cofre;
import org.apache.log4j.Logger;

import javax.swing.plaf.nimbus.State;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import static org.apache.log4j.Logger.getLogger;

/**
 * Created by ivanm on 14/05/2017.
 */
public abstract class CofreDAO extends DAO {

    final Logger logger = getLogger("CofreDAO");


    public List<Cofre> getAllCofres(){
        List<Cofre> cofreList = new ArrayList<Cofre>();
        try {
            Statement stmt = con.createStatement();
            ResultSet resultset = stmt.executeQuery("SELECT * FROM Cofre");
            while (resultset.next()){
                Cofre cofre = new Cofre();
                cofre.setId(resultset.getInt("id"));
                cofre.setNombre(resultset.getString("nombre"));
                cofre.setDescripcion(resultset.getString("descripcion"));
                cofre.setTemporizador(resultset.getDate("temporizador"));
                cofreList.add(cofre);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            logger.error("getAllCofres: "+e.getMessage());
        }
        return cofreList;
    }
}
