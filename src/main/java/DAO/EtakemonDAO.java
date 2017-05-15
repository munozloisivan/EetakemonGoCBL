package DAO;

import Modelo.Etakemon;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import static org.apache.log4j.Logger.getLogger;

/**
 * Created by ivanm on 14/05/2017.
 */
public abstract class EtakemonDAO extends DAO{

    final Logger logger = getLogger("EtakemonDAO");

    public List<Etakemon> getAllEtakemon() {
        List<Etakemon> listaEtakemon = new ArrayList<Etakemon>();

        try {
            Statement stmt = null;
            stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM Etakemon");
            while (rs.next()) {
                Etakemon etakemon = new Etakemon();
                etakemon.setId(rs.getInt("id"));
                etakemon.setNombre(rs.getString("nombre"));
                etakemon.setHabilidad(rs.getString("habilidad"));
                etakemon.setTipo(rs.getInt("tipo"));
                listaEtakemon.add(etakemon);
            }
        }
        catch (SQLException e){
            e.printStackTrace();
            logger.error("getAllEtakemon: "+e.getMessage());
        }

        return listaEtakemon;
    }

    public List<Etakemon> getEtakemonTipo(int tipo){
        List<Etakemon> etakemonList = new ArrayList<Etakemon>();

        try {
            Statement stmt = con.createStatement();
            ResultSet resultSet = stmt.executeQuery("SELECT * FROM Etakemon WHERE tipo ="+tipo+";");
            while(resultSet.next()){
                Etakemon etakemon = new Etakemon();
                etakemon.setId(resultSet.getInt("id"));
                etakemon.setNombre(resultSet.getString("nombre"));
                etakemon.setHabilidad(resultSet.getString("habilidad"));
                etakemon.setTipo(resultSet.getInt("tipo"));
                etakemonList.add(etakemon);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            logger.error("getEtakemonTipo: "+e.getMessage());
        }

        return etakemonList;
    }




}
