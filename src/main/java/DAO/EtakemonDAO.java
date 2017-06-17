package DAO;

import Modelo.Etakemon;
import Modelo.Usuario;
import org.apache.log4j.Logger;

import java.lang.reflect.Field;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static org.apache.log4j.Logger.getLogger;

/**
 * Created by ivanm on 14/05/2017.
 */
public abstract class EtakemonDAO extends DAO{

    final Logger logger = getLogger("EtakemonDAO");

    public boolean insert(Etakemon etakemon){
        boolean added = false;
        StringBuffer stringBuffer = new StringBuffer("INSERT into Etakemon (");
        Field[] atributes = this.getClass().getDeclaredFields();
        int i =0;
        for (Field f : atributes){
            stringBuffer.append(f.getName());
            i++;
            if (i!= atributes.length)
                stringBuffer.append(", ");
        }

        stringBuffer.append(") VALUES (");

        int j = 0;
        for (Field f: atributes){
            stringBuffer.append("?");
            j++;
            if (j!=atributes.length)
                stringBuffer.append(",");
        }
        stringBuffer.append(")");
        logger.info("Insert query: "+stringBuffer.toString());

        try {
                PreparedStatement preparedStatement = con.prepareStatement(stringBuffer.toString());
                insertElements(preparedStatement);
                preparedStatement.execute();
                added = true;
        }
        catch (SQLException e){
            logger.error(e.getMessage());
        }

        return added;
    }

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

    public Etakemon selectbyID(int id) {
        Etakemon etakemon = new Etakemon();

        StringBuffer stringBuffer = new StringBuffer("SELECT * FROM Etakemon WHERE id ='" + id + "';");
        try {
            Statement statement = con.createStatement();
            ResultSet resultSet = statement.executeQuery(stringBuffer.toString());
            resultSet.next();

            etakemon.setId(resultSet.getInt("id"));
            etakemon.setNombre(resultSet.getString("nombre"));
            etakemon.setHabilidad(resultSet.getString("habilidad"));
            etakemon.setDescripcion(resultSet.getString("descripcion"));
            etakemon.setImagen(resultSet.getString("imagen"));
            etakemon.setTipo(resultSet.getInt("tipo"));

        } catch (SQLException e) {
            e.printStackTrace();
            logger.error(e.getMessage());
        }
        return etakemon;
    }

    public boolean updateEetakemonDataByAdmin(int id, String nombre, String habilidad, String descripcion, String imagen, int tipo) {             //actualizado devuelve true     no actualizado devuelve false
        boolean updated = false;

        try {
            StringBuffer sb = new StringBuffer("UPDATE Etakemon SET ");
            sb.append("nombre = '"+nombre+ "', habilidad= '"+habilidad+"', descripcion='" + descripcion + "', imagen='" + imagen + "', tipo="+tipo+" where id = " + id + ";");

            PreparedStatement preparedStatement = con.prepareStatement(sb.toString());
            preparedStatement.execute();
            logger.info("campos modificados por el administrador");
            updated = true;

        } catch (SQLException e) {
            e.printStackTrace();
            logger.error(e.getMessage());
        }
        return updated;
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
    public int getRandomIDEtakemon(){
        int numEtakemon = (int) Math.floor(Math.random()*((getAllEtakemon().size())-1+1)+1);
        return  numEtakemon;
    }

    public Etakemon getRandomEtakemon(){
        Etakemon random = new Etakemon();
        random = random.selectbyID(getRandomIDEtakemon());

        return random;
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
