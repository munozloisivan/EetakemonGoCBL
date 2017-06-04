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
                etakemon.setImagen(rs.getString("imagen"));
                etakemon.setDescripcion(rs.getString("descripcion"));
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


    //generador aleatorio de Etakemons para generar capturas
    public int getRandomEtakemon(){
        int numEtakemon = (int) Math.floor(Math.random()*((getAllEtakemon().size())-1+1)+1);
        return  numEtakemon;
    }

    public int getTipoParaCaptura(int id){
        int tipo = 0;

        try {
            Statement stmt = null;
            stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT tipo FROM Etakemon WHERE id ="+id+";");
            while (rs.next()) {
                tipo = rs.getInt("tipo");
            }
        }
        catch (SQLException e){
            tipo = 0; //esto sera error
            e.printStackTrace();
            logger.error("getTipoParaCaptura: "+e.getMessage());
        }
        return tipo;
    }


}
