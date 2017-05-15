package DAO;


import Modelo.*;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static DAO.DAO.con;
import static org.apache.log4j.Logger.getLogger;

/**
 * Created by ivanm on 14/05/2017.
 */
public abstract class UsuarioDAO extends DAO {

    final Logger logger = getLogger("UsuarioDAO");

    public boolean login(String email, String contrasena){         //logeado devuelve true     no logeado devuelve false
        boolean loged = Boolean.parseBoolean(null);
        StringBuffer sb = new StringBuffer("SELECT nombre,contrasena FROM Usuario WHERE email='");
        sb.append(email + "'" + " AND contrasena ='" + contrasena + "';");

        try {
            PreparedStatement stmt = con.prepareStatement(sb.toString());
            ResultSet rs = stmt.executeQuery();

            if (!rs.next()) {
                logger.error("No se ha podido loggear");
                loged = false;
            } else {
                if (rs.getString("contrasena").equals(contrasena))
                    logger.info("usuario loggeado correctamente con email:" + email);
                loged = true;
            }

        } catch (SQLException e) {
            e.printStackTrace();
            logger.error(e.toString());
        }
        return loged;
    }


    public Usuario select(String email) {
        Usuario usuario = new Usuario();

        StringBuffer stringBuffer = new StringBuffer("SELECT * FROM Usuario WHERE email ='" + email + "';");
        try {
            Statement statement = con.createStatement();
            ResultSet resultSet = statement.executeQuery(stringBuffer.toString());
            resultSet.next();

            usuario.setId(resultSet.getInt("id"));
            usuario.setNombre(resultSet.getString("nombre"));
            usuario.setNick(resultSet.getString("nick"));
            usuario.setEmail(resultSet.getString("email"));
            usuario.setContrasena(resultSet.getString("contrasena"));
            usuario.setNivel(resultSet.getInt("nivel"));
            usuario.setExperiencia(resultSet.getInt("experiencia"));
            usuario.setModified(resultSet.getInt("modified"));
        } catch (SQLException e) {
            e.printStackTrace();
            logger.error(e.getMessage());
        }
        return usuario;
    }

    //aplica solo a nick y password que se podran cambiar solo una vez
    public int selectModified(int id) {
        StringBuffer sb = new StringBuffer("SELECT modified from Usuario where id = " + id);
        int res = 2;
        int comparador = 2;
        try {
            Statement stat = con.createStatement();
            ResultSet resultSet = stat.executeQuery(sb.toString());
            ResultSetMetaData rsmd = resultSet.getMetaData();

            while (resultSet.next()) {
                comparador = resultSet.getInt("modified");
                logger.info("El campo 'modified' tiene el valor: " + comparador);

            }
            if (comparador == 0) {
                res = 0;
            } else if (comparador == 1) {
                res = 1;
            } else {
                res = 2;            //pensar que pasa ( no existira ese usuario)
            }
        } catch (SQLException e) {
            e.printStackTrace();
            logger.error(e.getMessage());
        }
        return res;
    }

    //modificar nick y password
    public boolean updateUsuarioData(int id, String nick, String password) {             //actualizado devuelve true     no actualizado devuelve false
        boolean updated = false;
        selectModified(id);
        if (selectModified(id) == 0) {           //si es 0 no se ha modificado previamente y se puede actualizar
            try {
                StringBuffer sb = new StringBuffer("UPDATE Usuario SET ");
                sb.append("nick='" + nick + "', contrasena='" + password + "' where id = " + id + ";");

                PreparedStatement preparedStatement = con.prepareStatement(sb.toString());
                preparedStatement.execute();
                logger.info("nick y password modificados");
                updated = true;

            } catch (SQLException e) {
                e.printStackTrace();
                logger.error(e.getMessage());
            }
        } else {
            logger.info("Ya se ha modificado previamente. NO se puede modificar");
            updated = false;
        }
        return updated;
    }

    public boolean emailDisponible(String email) {           //disponible devuelve true          no disponible devuelve false
        boolean disponible = false;
        try {
            StringBuffer stringBuffer = new StringBuffer("SELECT count(email) from usuario where email='" + email + "';");

            PreparedStatement preparedStatement = con.prepareStatement(stringBuffer.toString());
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();

            if (resultSet.getInt("count(email)") == 0) {
                disponible = true;
                logger.info("se puede usar este email para registrarse");
            } else
                disponible = false;

        } catch (SQLException e) {
            e.printStackTrace();
            logger.error(e.getMessage());
        }
        return disponible;
    }

    public static List<Usuario> getAllUsers() throws SQLException {
        List<Usuario> listaUsuarios = new ArrayList<Usuario>();
        Statement stmt = null;
        stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM Usuario");
        while (rs.next()) {
            Usuario us = new Usuario();
            us.setId(rs.getInt("id"));
            us.setNombre(rs.getString("nombre"));
            us.setNick(rs.getString("nick"));
            us.setEmail(rs.getString("email"));
            us.setContrasena(rs.getString("contrasena"));
            us.setNivel(rs.getInt("nivel"));
            us.setExperiencia(rs.getInt("experiencia"));
            us.setModified(rs.getInt("modified"));
            listaUsuarios.add(us);
        }
        return listaUsuarios;
    }


    public static List<Captura> getCapturasUsuario(int id) throws SQLException {
        List<Captura> listaCapturaUsuario = new ArrayList<Captura>();
        Statement stmt = null;
        stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM Captura WHERE captura.idusuariosss = " + id);
        while (rs.next()) {
            Captura capturaUsuario = new Captura();
            capturaUsuario.setId(rs.getInt("id"));
            capturaUsuario.setIdusuariosss(rs.getInt("idusuariosss"));
            capturaUsuario.setIdetakemon(rs.getInt("idetakemon"));
            capturaUsuario.setNivel(rs.getInt("nivel"));
            capturaUsuario.setExperiencia(rs.getInt("experiencia"));
            capturaUsuario.setVida(rs.getInt("vida"));
            capturaUsuario.setAtaque(rs.getInt("ataque"));
            capturaUsuario.setDefensa(rs.getInt("defensa"));
            capturaUsuario.setEstado(rs.getInt("estado"));
            capturaUsuario.setFecha(rs.getDate("fecha"));
            listaCapturaUsuario.add(capturaUsuario);
        }
        return listaCapturaUsuario;
    }


    public static List<Captura> getCapturasUsuarioToRevive(int id) throws SQLException {
        List<Captura> listaCapturaUsuarioToRevive = new ArrayList<Captura>();
        Statement stmt = null;
        stmt = con.createStatement();
        ResultSet resultSet = stmt.executeQuery("SELECT id, idetakemon FROM Captura WHERE captura.idusuariosss = " + id + " and estado = 0");
        while (resultSet.next()) {
            Captura capturaToRevive = new Captura();
            capturaToRevive.setId(resultSet.getInt("id"));
            capturaToRevive.setIdetakemon(resultSet.getInt("idetakemon"));
            listaCapturaUsuarioToRevive.add(capturaToRevive);
        }
        return listaCapturaUsuarioToRevive;
    }

    public static List<Objetos> getObjetosUsuario(int id) {
        List<Objetos> objetosList = new ArrayList<Objetos>();
        try {
            Statement stmt = con.createStatement();
            ResultSet resultSet = stmt.executeQuery("SELECT idobjeto from objetosusuario WHERE idusuario =" + id + ";");
            while (resultSet.next()) {
                Objetos objetos = new Objetos();
                objetos.setId(resultSet.getInt("idobjeto"));
                objetosList.add(objetos);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return objetosList;
    }

    public static List<Logros> getLogrosUsuario(int id) {
        List<Logros> logrosList = new ArrayList<Logros>();
        try {
            Statement stmt = con.createStatement();
            ResultSet resultSet = stmt.executeQuery("SELECT idlogros FROM logrosusuario WHERE idusuarios =" + id);
            while (resultSet.next()) {
                Logros logros = new Logros();
                logros.setId(resultSet.getInt("idlogros"));
                logrosList.add(logros);
            }
        } catch (SQLException e) {
            e.printStackTrace();
           // logger.error("getLogrosUsuario:" + e.getMessage());
        }
        return logrosList;
    }

    //Se le mostrara al usuario las batallas ganadas de ESE ETAKEMON(CAPTURA)
    public static List<Batalla> getBatallasGanadasUsuario(int idcaptura) throws SQLException {
        List<Batalla> listaBatallasGanadas = new ArrayList<Batalla>();
        Statement stmt = null;
        stmt = con.createStatement();
        ResultSet resultSet = stmt.executeQuery("SELECT * from Batalla WHERE batalla.idcaptura = " + idcaptura + " and batalla.resultado = 1");
        while (resultSet.next()) {
            Batalla batallaGanada = new Batalla();
            batallaGanada.setId(resultSet.getInt("id"));
            batallaGanada.setIdcaptura(resultSet.getInt("idcaptura"));
            batallaGanada.setResultado(resultSet.getInt("resultado"));
            batallaGanada.setExperiencia(resultSet.getInt("experiencia"));
            batallaGanada.setFecha(resultSet.getDate("fecha"));
            listaBatallasGanadas.add(batallaGanada);
        }
        return listaBatallasGanadas;
    }


 /*   public void updateUsuarioAtributes(int id, int experiencia){

        //pendiente de pensar algoritmos sobre como aumenta cada nivel y la experiencia asociada a cada cosa

    }
    */

}
