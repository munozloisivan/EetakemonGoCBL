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
public class BatallaDAO extends DAO{

    public static List<Batalla> getAllBatalla() throws SQLException {
        List<Batalla> listaBatalla = new ArrayList<Batalla>();
        Statement stmt = null;
        stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM Batalla");
        while(rs.next()){
            Batalla batalla = new Batalla();
            batalla.setId(rs.getInt("id"));
            batalla.setIdcaptura(rs.getInt("idcaptura"));
            batalla.setResultado(rs.getInt("resultado"));
            batalla.setExperiencia(rs.getInt("experiencia"));
            batalla.setFecha(rs.getDate("fecha"));
            listaBatalla.add(batalla);
        }
        return listaBatalla;
    }
}
