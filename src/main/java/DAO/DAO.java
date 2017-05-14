package DAO;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import Modelo.*;
import org.apache.log4j.Logger;

import javax.management.MalformedObjectNameException;
import javax.management.ObjectName;

import static org.apache.log4j.Logger.getLogger;

/**
 * Created by ivanm on 26/04/2017.
 */
public abstract class DAO {

    static DBConnection connection = new DBConnection();
    static Connection con = connection.getCon();

    final Logger logger = getLogger("DAO");



    // **********************************************************************************************
    //                              Funciones COMPLEMENTARIAS a las prinicpales

    private String getUpper(String m){
        String res = Character.toUpperCase(m.charAt(0)) + m.substring(1);
        return "get".concat(res);
    }

    public String getValues(Field field) {
        String val = null;
        try {
            Method method = this.getClass().getDeclaredMethod(getUpper(field.getName()), null);
            val = method.invoke(this, null).toString();
        } catch (NoSuchMethodException e) {
            logger.error(e.getMessage());
        } catch (IllegalAccessException e) {
            logger.error(e.getMessage());
        } catch (InvocationTargetException e) {
            logger.error(e.getMessage());
        }
        return val;
    }

    public void insertElements(PreparedStatement preparedStatement) throws SQLException {
        int i = 1;
        Field[] fields = this.getClass().getDeclaredFields();
        for (Field f : fields){
            String res = getValues(f);
            preparedStatement.setObject(i,res);
            i++;
        }
    }



    //*****************************************************************************************************

    //                                       funciones GENERICAS

    public void insert(){

        StringBuffer sb = new StringBuffer("INSERT INTO ");
        sb.append(this.getClass().getSimpleName()+ " ("); //Usuario
        Field[] atributes = this.getClass().getDeclaredFields();

        int i =0;
        for (Field f : atributes){
            sb.append(f.getName());
            i++;
            if (i!= atributes.length)
                sb.append(", ");
        }

        sb.append(") VALUES (");

        int j = 0;
        for (Field f: atributes){
            sb.append("?");
            j++;
            if (j!=atributes.length)
                sb.append(",");
        }
        sb.append(")");
        logger.info("Insert query: "+sb.toString());

        try {
            PreparedStatement preparedStatement = con.prepareStatement(sb.toString());
            insertElements(preparedStatement);
            preparedStatement.execute();
        }
        catch (SQLException e){
           logger.error(e.getMessage());
        }

        logger.info("Insert query: "+sb.toString());
    }

    //SELECT
    public Object select(int id)  {

        Object object = this.getClass().getSimpleName();
        System.out.println("OBJECT:  "+object);

        StringBuffer sb = new StringBuffer("SELECT *");

        sb.append(" FROM ");

        sb.append(this.getClass().getSimpleName());

        sb.append(" WHERE id = "+id+";");

        logger.info("SELECT query: "+sb.toString());

        try {
            Statement stat = con.createStatement();
            ResultSet rs = stat.executeQuery(sb.toString());
            ResultSetMetaData rsmd = rs.getMetaData();
            rs.next();

            if (object.equals("Usuario")){
                Usuario usuario = new Usuario();
                    usuario.setId(rs.getInt("id"));
                    logger.info(usuario.getId());
                    usuario.setNombre(rs.getString("nombre"));
                    logger.info(usuario.getNombre());
                    usuario.setNick(rs.getString("nick"));
                    logger.info(usuario.getNick());
                    usuario.setEmail(rs.getString("email"));
                    logger.info(usuario.getEmail());
                    usuario.setContrasena(rs.getString("contrasena"));
                    logger.info(usuario.getContrasena());
                    usuario.setNivel(rs.getInt("nivel"));
                    logger.info(usuario.getNivel());
                    usuario.setExperiencia(rs.getInt("experiencia"));
                    logger.info(usuario.getExperiencia());
                    usuario.setModified(rs.getInt("modified"));
                    logger.info(usuario.getModified());

              return usuario;
            }
            else if (object.equals("Captura")){
                Captura captura = new Captura();
                captura.setId(rs.getInt("id"));
                captura.setIdusuariosss(rs.getInt("idusuariosss"));
                captura.setIdetakemon(rs.getInt("idetakemon"));
                captura.setIdlocalizacion(rs.getInt("idlocalizacion"));
                captura.setNivel(rs.getInt("nivel"));
                captura.setExperiencia(rs.getInt("experiencia"));
                captura.setVida(rs.getInt("vida"));
                captura.setAtaque(rs.getInt("ataque"));
                captura.setDefensa(rs.getInt("defensa"));
                captura.setEstado(rs.getInt("estado"));
                captura.setFecha(rs.getDate("fecha"));

                return captura;
            }
            else if (object == "Cofre"){
                Cofre cofre = new Cofre();
                cofre.setId(rs.getInt("id"));
                cofre.setNombre(rs.getString("nombre"));
                cofre.setDescripcion(rs.getString("descripcion"));
                cofre.setTemporizador(rs.getDate("temporizador"));

                return cofre;
            }
            else if (object == "Etakemon"){
                Etakemon etakemon = new Etakemon();
                etakemon.setId(rs.getInt("id"));
                etakemon.setNombre(rs.getString("nombre"));
                etakemon.setHabilidad(rs.getString("habilidad"));
                etakemon.setTipo(rs.getInt("tipo"));

                return etakemon;
            }
            else if (object == "Localizacion"){
                Localizacion localizacion = new Localizacion();
                localizacion.setId(rs.getInt("id"));
                localizacion.setNombre(rs.getString("nombre"));
                localizacion.setLatitud(rs.getDouble("latitud"));
                localizacion.setLongitud(rs.getDouble("longitud"));

                return localizacion;
            }
            else if(object == "Logros"){
                Logros logros = new Logros();
                logros.setId(rs.getInt("id"));
                logros.setNombre(rs.getString("nombre"));
                logros.setDescripcion(rs.getString("descripcion"));
                logros.setExperiencia(rs.getInt("experiencia"));

                return logros;
            }
            else if (object == "Objetos"){
                Objetos objetos = new Objetos();
                objetos.setId(rs.getInt("id"));
                objetos.setNombre(rs.getString("nombre"));
                objetos.setDescripcion(rs.getString("descripcion"));

                return objetos;
            }
            else if (object == "Batalla"){
                Batalla batalla = new Batalla();
                batalla.setId(rs.getInt("id"));
                batalla.setIdcaptura(rs.getInt("idcaptura"));
                batalla.setResultado(rs.getInt("resultado"));
                batalla.setExperiencia(rs.getInt("experiencia"));
                batalla.setFecha(rs.getDate("fecha"));

                return batalla;
            }
        }

        catch (SQLException e){
            e.printStackTrace();
            logger.error(e.getMessage());
        }
        return object;
    }


    //DELETE
    public void delete(int id){
        StringBuffer sb = new StringBuffer("DELETE FROM ");
        sb.append(this.getClass().getSimpleName());
        sb.append(" WHERE id = " + id);
        logger.info("DELETE query: "+sb.toString());

        try {
            PreparedStatement preparedStatement = con.prepareStatement(sb.toString());
            preparedStatement.execute();
        }
        catch (SQLException e){
            e.printStackTrace();
            logger.error(e.getMessage());
        }

    }

    //UPDATE
   public void update(int id) {
        StringBuffer sb = new StringBuffer("UPDATE ").append(this.getClass().getSimpleName()).append(" SET ");
        Field[] fields = this.getClass().getDeclaredFields();
        int i = 0;
        for (Field f : fields) {
            sb.append(f.getName());
            sb.append("=?");
            //sb.append("'");
            i++;
            if (i != fields.length)
                sb.append(", ");
        }
       sb.append(" where id=").append(id+";");
        logger.info("antes del preparedstatement:    "+sb.toString());
        try{
            PreparedStatement preparedStatement = con.prepareStatement(sb.toString());
            insertElements(preparedStatement);
            preparedStatement.execute();
        }
        catch (SQLException e){
            e.printStackTrace();
            logger.error(e.getMessage());

        }
        //sb.append(" where id=").append(id+";");
        logger.info("Update query: " + sb.toString());

    }


    //                                      LISTAS



     public static List<Objetos> getAllObjects() throws SQLException {
        List<Objetos> listaObjetos = new ArrayList<Objetos>();
        Statement stmt = null;
        stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM Objetos");
        while(rs.next()){
            Objetos ob = new Objetos();
            ob.setId(rs.getInt("id"));
            ob.setNombre(rs.getString("nombre"));
            ob.setDescripcion(rs.getString("descripcion"));
            listaObjetos.add(ob);
        }
        return listaObjetos;
    }


    public static List<Etakemon> getAllEtakemon() throws SQLException {
        List<Etakemon> listaEtakemon = new ArrayList<Etakemon>();
        Statement stmt = null;
        stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM Etakemon");
        while(rs.next()){
            Etakemon etakemon = new Etakemon();
            etakemon.setId(rs.getInt("id"));
            etakemon.setNombre(rs.getString("nombre"));
            etakemon.setHabilidad(rs.getString("habilidad"));
            etakemon.setTipo(rs.getInt("tipo"));
            listaEtakemon.add(etakemon);
        }
        return listaEtakemon;
    }

    public static List<Captura> getAllCapturas() throws SQLException {
        List<Captura> listaCaptura = new ArrayList<Captura>();
        Statement stmt = null;
        stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM Captura");
        while(rs.next()){
            Captura captura = new Captura();
            captura.setId(rs.getInt("id"));
            captura.setIdusuariosss(rs.getInt("idusuariosss"));
            captura.setIdetakemon(rs.getInt("idetakemon"));
            captura.setNivel(rs.getInt("nivel"));
            captura.setExperiencia(rs.getInt("experiencia"));
            captura.setVida(rs.getInt("vida"));
            captura.setAtaque(rs.getInt("ataque"));
            captura.setDefensa(rs.getInt("defensa"));
            captura.setEstado(rs.getInt("estado"));
            captura.setFecha(rs.getDate("fecha"));
            listaCaptura.add(captura);
        }
        return listaCaptura;
    }

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

    public static List<Logros> getAllLogros() throws SQLException {
        List<Logros> listaLogros = new ArrayList<Logros>();
        Statement stmt = null;
        stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM Logros");
        while(rs.next()){
            Logros logros = new Logros();
            logros.setId(rs.getInt("id"));
            logros.setNombre(rs.getString("nombre"));
            logros.setDescripcion(rs.getString("descripcion"));
            logros.setExperiencia(rs.getInt("experiencia"));
            listaLogros.add(logros);
        }
        return listaLogros;
    }

    public static List<Localizacion> getAllLocalizaciones() throws SQLException {
        List<Localizacion> listaLocalizaciones = new ArrayList<Localizacion>();
        Statement stmt = null;
        stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM Localizacion");
        while(rs.next()){
            Localizacion localizacion = new Localizacion();
            localizacion.setId(rs.getInt("id"));
            localizacion.setNombre(rs.getString("nombre"));
            localizacion.setLatitud(rs.getDouble("latitud"));
            localizacion.setLongitud(rs.getDouble("longitud"));
            listaLocalizaciones.add(localizacion);
        }
        return listaLocalizaciones;
    }








}
