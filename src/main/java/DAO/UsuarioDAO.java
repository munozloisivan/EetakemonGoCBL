package DAO;


import Controller.EnviarMail;
import Controller.LeerMail;
import Controller.LogrosController;
import Modelo.*;
import org.apache.log4j.Logger;
import sun.rmi.runtime.Log;

import javax.mail.MessagingException;
import javax.swing.plaf.nimbus.State;
import java.io.IOException;
import java.lang.reflect.Field;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static DAO.DAO.con;
import static org.apache.log4j.Logger.getLogger;

/**
 * Created by ivanm on 14/05/2017.
 */
public abstract class UsuarioDAO extends DAO {

    static final Logger logger = getLogger("UsuarioDAO");

    //registro
    public boolean insert(Usuario usuario){
        boolean registrado = false;
        StringBuffer stringBuffer = new StringBuffer("INSERT into usuario (");
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
            if (emailDisponible(usuario.getEmail())) {
                PreparedStatement preparedStatement = con.prepareStatement(stringBuffer.toString());
                insertElements(preparedStatement);
                preparedStatement.execute();
                registrado = true;
            }
            else {
                logger.error("INSERT: El email ya esta en uso");
                registrado = false;
            }

        }
        catch (SQLException e){
            logger.error(e.getMessage());
        }

        return registrado;
    }

    public boolean insertLogroToUser(Logros logros, int idusuario){
        boolean insertado = false;


        return insertado;
    }


    public boolean login(String email, String contrasena){         //logeado devuelve true     no logeado devuelve false
        boolean loged = Boolean.parseBoolean(null);
        StringBuffer sb = new StringBuffer("SELECT nombre,contrasena FROM usuario WHERE email='");
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

        StringBuffer stringBuffer = new StringBuffer("SELECT * FROM usuario WHERE email ='" + email + "';");
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
            usuario.setAdmin(resultSet.getInt("admin"));
        } catch (SQLException e) {
            e.printStackTrace();
            logger.error(e.getMessage());
        }
        return usuario;
    }

    public Usuario selectbyID(int id) {
        Usuario usuario = new Usuario();

        StringBuffer stringBuffer = new StringBuffer("SELECT * FROM usuario WHERE id ='" + id + "';");
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
            usuario.setAdmin(resultSet.getInt("admin"));
        } catch (SQLException e) {
            e.printStackTrace();
            logger.error(e.getMessage());
        }
        return usuario;
    }

    //aplica solo a nick y password que se podran cambiar solo una vez
    public int selectModified(int id) {
        StringBuffer sb = new StringBuffer("SELECT modified from usuario where id = " + id);
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
                StringBuffer sb = new StringBuffer("UPDATE usuario SET ");
                sb.append("nick='" + nick + "', contrasena='" + password + "', modified=1 where id = " + id + ";");

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

    public boolean updateUsuarioDataByAdmin(int id, String nick, String password, String email, String nombre) {             //actualizado devuelve true     no actualizado devuelve false
        boolean updated = false;

            try {
                StringBuffer sb = new StringBuffer("UPDATE usuario SET ");
                sb.append("nombre = '"+nombre+ "', email= '"+email+"', nick='" + nick + "', contrasena='" + password + "', modified=1 where id = " + id + ";");

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

    public boolean updateUsuarioExp(int id, int nivelXUsuario, int experienciaXUsuario, int experienciaAdd) {
        boolean updated = false;
        int nivelActual = nivelXUsuario;
        int experienciaToNextLevel;
        int experienciaActual;
        if (nivelXUsuario<=5){

            experienciaToNextLevel = 1000;
            experienciaActual = experienciaXUsuario + experienciaAdd;
            if (experienciaActual >= experienciaToNextLevel){
                nivelActual = nivelActual +1;
                experienciaActual = experienciaActual - experienciaToNextLevel;
            }

                try {
                    StringBuffer sb = new StringBuffer("UPDATE usuario SET ");
                    sb.append("nivel=" + nivelActual + ",experiencia=" + experienciaActual + " where id = " + id + ";");

                    PreparedStatement preparedStatement = con.prepareStatement(sb.toString());
                    preparedStatement.execute();
                    logger.info("updateUsuarioEXP:   Nivel / experiencia modificados");
                    updated = true;

                } catch (SQLException e) {
                    e.printStackTrace();
                    logger.error(e.getMessage());
                }
        }
        else if (nivelXUsuario>5 && nivelXUsuario<=10){
            experienciaToNextLevel = 1500;
            experienciaActual = experienciaXUsuario + experienciaAdd;
            if (experienciaActual >= experienciaToNextLevel){
                nivelActual = nivelActual +1;
                experienciaActual = experienciaActual - experienciaToNextLevel;
            }
                try {
                    StringBuffer sb = new StringBuffer("UPDATE usuario SET ");
                    sb.append("nivel=" + nivelActual + ",experiencia=" + experienciaActual + " where id = " + id + ";");

                    PreparedStatement preparedStatement = con.prepareStatement(sb.toString());
                    preparedStatement.execute();
                    logger.info("updateUsuarioEXP:   Nivel / experiencia modificados");
                    updated = true;

                } catch (SQLException e) {
                    e.printStackTrace();
                    logger.error(e.getMessage());
                }
        }
        else {
            experienciaToNextLevel = 2000;
            experienciaActual = experienciaXUsuario + experienciaAdd;
            if (experienciaActual >= experienciaToNextLevel){
                nivelActual = nivelActual +1;
                experienciaActual = experienciaActual - experienciaToNextLevel;
            }
                try {
                    StringBuffer sb = new StringBuffer("UPDATE usuario SET ");
                    sb.append("nivel=" + nivelActual + ",experiencia=" + experienciaActual + " where id = " + id + ";");

                    PreparedStatement preparedStatement = con.prepareStatement(sb.toString());
                    preparedStatement.execute();
                    logger.info("updateUsuarioEXP:   Nivel / experiencia modificados");
                    updated = true;

                } catch (SQLException e) {
                    e.printStackTrace();
                    logger.error(e.getMessage());
                }
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
            }
            else {
                logger.error("no se puede usar este email para el registro");
                disponible = false;
            }

        } catch (SQLException e) {
            e.printStackTrace();
            logger.error(e.getMessage());
        }
        return disponible;
    }

    public List<Usuario> getAllUsers()  {
        List<Usuario> listaUsuarios = new ArrayList<Usuario>();

        try {
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM usuario");
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
        } catch (SQLException e) {
            e.printStackTrace();
            logger.error("getAllUsers: "+e.getMessage());
        }

        return listaUsuarios;
    }


    public List<Captura> getCapturasUsuario(int id) {
        List<Captura> listaCapturaUsuario = new ArrayList<Captura>();
        try {
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM captura WHERE captura.idusuariosss = " + id);
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
                capturaUsuario.setNombreetakemon(rs.getString("nombreetakemon"));
                capturaUsuario.setHabilidadetakemon(rs.getString("habilidadetakemon"));
                capturaUsuario.setTipoetakemon(rs.getInt("tipoetakemon"));
                capturaUsuario.setImagen(rs.getString("imagen"));
                listaCapturaUsuario.add(capturaUsuario);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            logger.error("getCapturasUsuario: "+e.getMessage());
        }

        return listaCapturaUsuario;
    }



    public List<Captura> getCapturasUsuarioToRevive(int id) {
        List<Captura> listaCapturaUsuarioToRevive = new ArrayList<Captura>();
        try {
            Statement stmt = con.createStatement();
            ResultSet resultSet = stmt.executeQuery("SELECT id, idetakemon FROM captura WHERE captura.idusuariosss = " + id + " and estado = 0");
            while (resultSet.next()) {
                Captura capturaToRevive = new Captura();
                capturaToRevive.setId(resultSet.getInt("id"));
                capturaToRevive.setIdetakemon(resultSet.getInt("idetakemon"));
                listaCapturaUsuarioToRevive.add(capturaToRevive);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            logger.error("getCapturasUsuarioToRevive: "+e.getMessage());

        }

        return listaCapturaUsuarioToRevive;
    }


    public List<Objetos> getObjetosUsuario(String email) {
        Usuario u = new Usuario();
        int idusuario = u.select(email).getId();

        List<Objetos> objetosList = new ArrayList<Objetos>();
        try {
            Statement stmt = con.createStatement();
            ResultSet resultSet = stmt.executeQuery("SELECT idobjeto from objetosusuario WHERE idusuario =" + idusuario + ";");
            while (resultSet.next()) {
                Objetos objetos = new Objetos();
                objetos.setId(resultSet.getInt("idobjeto"));
                objetosList.add(objetos);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            logger.error("getObjetosUsuario: "+e.getMessage());
        }
        return objetosList;
    }
//    public List<Objetos> getObjetosUsuario(String email) {
//
//        Usuario u = new Usuario();
//        int idusuario = u.select(email).getId();
//
//        Objetos objetos = new Objetos();
//        List<Objetos> objetosList1 = objetos.getAllObjects();
//
//        List<Objetos> objetosList = new ArrayList<Objetos>();
//        try {
//            Statement stmt = con.createStatement();
//            ResultSet resultSet = stmt.executeQuery("SELECT idobjeto from objetosusuario WHERE idusuario =" + idusuario + ";");
//            while (resultSet.next()) {
//                Objetos objetos2 = new Objetos();
//                if (resultSet.getInt("idobjeto") == 1){
//
//                }
//                else if (resultSet.getInt("idobjeto") ==2){
//
//                }
//                else if (resultSet.getInt("idobjeto") ==3){
//                    objetos.setNombre("etakeball");
//                    objetos.setDescripcion("úsala para atrapar a los etakemons salvajes");
//                    objetosList.add(objetos);
//                }
//                else if (resultSet.getInt("idobjeto") ==4){
//                    objetos.setNombre("revivir");
//                    objetos.setDescripcion(" trae de vuelta a tu etakemon");
//                    objetosList.add(objetos);
//                }
//                else if (resultSet.getInt("idobjeto") ==5){
//                    objetos.setNombre("pocion1");
//                    objetos.setDescripcion("cura a tu etakemon");
//                    objetosList.add(objetos);
//                }
//                else if (resultSet.getInt("idobjeto") ==6){
//                    objetos.setNombre("pocion2");
//                    objetos.setDescripcion(" cura media vida a tu etakemon");
//                    objetosList.add(objetos);
//                }
//                else if (resultSet.getInt("idobjeto") ==7){
//                    objetos.setNombre("pocion3");
//                    objetos.setDescripcion("cura al maximo a tu etakemon");
//                    objetosList.add(objetos);
//                }
//
//            }
//
//
//
////            int count1=0, count2=0, count3=0, count4=0, count5=0, count6=0, count7=0;
////
////            for (int i = 0; i < objetosList.size(); i++){
////                if (objetosList.get(i).getId() == 1){
////                    count1++;
////                }
////                else if (objetosList.get(i).getId() == 2){
////                    count2++;
////                }
////                else if (objetosList.get(i).getId() == 3){
////                    count3++;
////                }
////                else if (objetosList.get(i).getId() == 4){
////                    count4++;
////                }
////                else if (objetosList.get(i).getId() == 5){
////                    count5++;
////                }
////                else if (objetosList.get(i).getId() == 6){
////                    count6++;
////                }
////                else if (objetosList.get(i).getId() == 7){
////                    count7++;
////                }
////            }
////
////                if (count1 >= 0){
////
////                }
////                if (count2 >= 0){
////
////                }
////                if (count3 >= 0){
////
////                }
////                if (count4 >= 0){
////
////                }
////                if (count5 >= 0){
////
////                }
////                if (count6 >= 0){
////
////                }
////                if (count7 >= 0){}
//
//
//
//        } catch (SQLException e) {
//            e.printStackTrace();
//            logger.error("getObjetosUsuario: "+e.getMessage());
//        }
//        return objetosList;
//    }

    public List<Logros> getLogrosUsuario(int id) {
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
           logger.error("getLogrosUsuario:" + e.getMessage());
        }
        return logrosList;
    }

    //Se le mostrara al usuario las batallas ganadas de ESE ETAKEMON(CAPTURA)
    public List<Batalla> getBatallasGanadasUsuario(int idcaptura)  {
        List<Batalla> listaBatallasGanadas = new ArrayList<Batalla>();

        try {
            Statement stmt = con.createStatement();
            ResultSet resultSet = stmt.executeQuery("SELECT * from batalla WHERE batalla.idcaptura = " + idcaptura + " and batalla.resultado = 1");
            while (resultSet.next()) {
                Batalla batallaGanada = new Batalla();
                batallaGanada.setId(resultSet.getInt("id"));
                batallaGanada.setIdcaptura(resultSet.getInt("idcaptura"));
                batallaGanada.setResultado(resultSet.getInt("resultado"));
                batallaGanada.setExperiencia(resultSet.getInt("experiencia"));
                batallaGanada.setFecha(resultSet.getDate("fecha"));
                listaBatallasGanadas.add(batallaGanada);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            logger.error(("getBatallasGanadasUsuario: "+e.getMessage()));
        }

        return listaBatallasGanadas;
    }

    public List<Logros> getLogrosUsuario(String email){
        Usuario usuario = new Usuario(email);
        Logros logros = new Logros();
        Logros logrosadd = new Logros();
        int idususario = usuario.select(email).getId();
        List<Logros> logrosList = new ArrayList<Logros>();
        List<Logros> logrosEnviar = new ArrayList<Logros>();
        logrosList = logros.getAllLogros();

        try {
            Statement stmt = con.createStatement();
            ResultSet resultSet = stmt.executeQuery("SELECT * from logrosusuario WHERE idusuarios = " +idususario + ";");
            while (resultSet.next()){
                int idlogro = resultSet.getInt("idlogros");
                for (int i = 0; i < logrosList.size(); i++){
                    if (idlogro == logrosList.get(i).getId()){
                        logrosadd.setNombre(logrosList.get(i).getNombre());
                        logrosadd.setDescripcion(logrosList.get(i).getDescripcion());
                        logrosadd.setId(logrosList.get(i).getId());
                        logrosadd.setExperiencia(logrosList.get(i).getExperiencia());
                        logrosEnviar.add(logrosadd);
                    }
                }
            }
        }
        catch (SQLException e){
            e.printStackTrace();
            logger.error(e.getMessage());
        }
        return logrosEnviar;

    }

    public List<Usuario> getUsersByNameAprroach(String aprox){
        List<Usuario>  usuarioList = new ArrayList<Usuario>();

        try {
            Statement statement = con.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM usuario where nombre LIKE '%"+aprox+"%';");
            Usuario usuario = new Usuario();

            while (resultSet.next()) {
                usuario.setId(resultSet.getInt("id"));
                usuario.setNombre(resultSet.getString("nombre"));
                usuario.setNick(resultSet.getString("nick"));
                usuario.setEmail(resultSet.getString("email"));
                usuario.setContrasena(resultSet.getString("contrasena"));
                usuario.setNivel(resultSet.getInt("nivel"));
                usuario.setExperiencia(resultSet.getInt("experiencia"));
                usuario.setModified(resultSet.getInt("modified"));
                usuarioList.add(usuario);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            logger.error("getUsersByNameApproach: "+e.getMessage());
        }
        return usuarioList;
    }

    public ArrayList<Integer> getAtributosParaCaptura(int idusuario){
        ArrayList<Integer> atributos = new ArrayList<Integer>();
        try {
            Statement statement = con.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT nivel,experiencia FROM usuario where id ="+idusuario+";");
            resultSet.next();
                atributos.add(resultSet.getInt("nivel"));
                atributos.add(resultSet.getInt("experiencia"));
        }
        catch (SQLException e) {
            atributos.add(null);
            atributos.add(null);
            e.printStackTrace();
            logger.error("getUsersByNameApproach: "+e.getMessage());
        }
        return atributos;
    }

 /*   public void updateUsuarioAtributes(int id, int experiencia){

        //pendiente de pensar algoritmos sobre como aumenta cada nivel y la experiencia asociada a cada cosa

    }
    */

    public String datosRecuperados(String email){
        String datos = null;
        try {
            Statement statement = con.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT contrasena FROM usuario where email ='"+email+"';");
            resultSet.next();
            datos = ("e-mail: "+email+" || contraseña: "+resultSet.getString("contrasena")+"\n Atentamente, \n El equipo de EtakeonGOCBL \n\n No dudes en ponerte en contacto con nosotros para cualquier duda!");

        } catch (SQLException e) {
            e.printStackTrace();
            logger.error("datosRecuperados: "+e.getMessage());
        }
        return datos;
    }

 public void mensajesCorreo(){
     LeerMail leerMail = new LeerMail();
     try {
         leerMail.obtenerMensajes();
         //leerMail.imprimirMensajes();
     } catch (MessagingException e) {
         e.printStackTrace();
     } catch (IOException e) {
         e.printStackTrace();
     }
 }

}
