package DAO;

import Modelo.Batalla;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import static DAO.DAO.con;
import static org.apache.log4j.Logger.getLogger;

/**
 * Created by ivanm on 14/05/2017.
 */
public abstract class BatallaDAO extends DAO{

    static final Logger logger = getLogger("BatallaDAO");


    public List<Batalla> getAllBatalla(){
        List<Batalla> listaBatalla = new ArrayList<Batalla>();

        try {
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM batalla");
            while(rs.next()){
                Batalla batalla = new Batalla();
                batalla.setId(rs.getInt("id"));
                batalla.setIdcaptura(rs.getInt("idcaptura"));
                batalla.setResultado(rs.getInt("resultado"));
                batalla.setExperiencia(rs.getInt("experiencia"));
                batalla.setFecha(rs.getDate("fecha"));
                listaBatalla.add(batalla);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            logger.error("getAllBatalla: "+e.getMessage());
        }

        return listaBatalla;
    }

    public List<Batalla> getBatallaByResult(int result){
        List<Batalla> batallaList = new ArrayList<Batalla>();
        try {
            Statement statement = con.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM batalla WHERE resultado="+result+";");
            while (resultSet.next()){
                Batalla batalla = new Batalla();
                batalla.setId(resultSet.getInt("id"));
                batalla.setIdcaptura(resultSet.getInt("idcaptura"));
                batalla.setResultado(resultSet.getInt("resultado"));
                batalla.setExperiencia(resultSet.getInt("experiencia"));
                batalla.setFecha(resultSet.getDate("fecha"));
                batallaList.add(batalla);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            logger.error("getBatallaByResult: "+e.getMessage());
        }
        return batallaList;
    }
}
