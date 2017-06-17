package DAO;

import DAO.LocalizacionDAO;
import DAO.EtakemonDAO;
import Modelo.Captura;
import Modelo.Etakemon;
import Modelo.Localizacion;
import Modelo.Usuario;
import org.apache.log4j.Logger;

import java.lang.reflect.Field;
import java.sql.*;
import java.sql.Date;
import java.util.*;

import static org.apache.log4j.Logger.getLogger;

/**
 * Created by ivanm on 14/05/2017.
 */


public abstract class CapturaDAO extends DAO {

    final Logger logger = getLogger("CapturaDAO");

    // crear capturas para mostrar (cogiendo localizaciones y etakemons)

    public List<Captura> createCapturaSpawns() {

        List<Captura> capturaList = new ArrayList<Captura>();
        Captura captura;
        Etakemon etakemon = new Etakemon();
        Localizacion localizacion = new Localizacion();

        for (int i=0; i<20;i++) {
            int idetakemon = etakemon.getRandomEtakemon();
            int idlocalizacion = localizacion.getRandomLocalizacion();
            captura = new Captura(idetakemon,idlocalizacion);
            capturaList.add(captura);
        }
        return capturaList;
    }


    public boolean insertarCaptura(Captura captura, int idusuario){
        boolean capturaInsertada = false;

        //la captura que nos envian tiene el idetakemon y el idlocalizacion fijados al crear el spawn
        //recibimos el idusuario para saber a que usuario se lo insertamos ( sera el que esta logeado)
        //hacer sets en captura insertar

        int nivelXUsuario, experienciaXUsuario, tipoX;
        int nivel,experiencia,vida,ataque,defensa,estado=1;

        Date fecha = new java.sql.Date(Calendar.getInstance().getTime().getTime());

        //recoger los datos del usuario en cuestion y analizarlos
        //para asignar sus atributos en funcion de su NIVEL y EXPERIENCIA

        Usuario usuario = new Usuario();
        nivelXUsuario = usuario.getAtributosParaCaptura(idusuario).get(0);
        experienciaXUsuario = usuario.getAtributosParaCaptura(idusuario).get(1);

        //recoger los datos del etakemon en cuestion y analizarlos
        //para definir atributos en funcion de su TIPO

        Etakemon etakemon = new Etakemon();
        //si tipo devuelve 0 es que no existe, no se ha encontrado
        tipoX = etakemon.getTipoParaCaptura(captura.getIdetakemon());

        if (nivelXUsuario<=5){
            experiencia = 100;
            nivel = (int) Math.floor(Math.random() * 5 + 1);

            if (tipoX == 1){ //ataque mejor
                ataque = (int) Math.floor(Math.random()*((80-70)+70));
                defensa = (int) Math.floor(Math.random()*((50-20)+20));
                vida = (int) Math.floor(Math.random()*((50-20)+20));
            }
            else if (tipoX == 2){ //defensa mejor
                ataque = (int) Math.floor(Math.random()*((50-20)+20));
                defensa = (int) Math.floor(Math.random()*((80-70)+70));
                vida = (int) Math.floor(Math.random()*((50-20)+20));
            }
            else{ //salud mejor
                ataque = (int) Math.floor(Math.random()*((60-20)+20));
                defensa = (int) Math.floor(Math.random()*((60-20)+20));
                vida = (int) Math.floor(Math.random()*((80-70)+70));
            }
        }
        else if (nivelXUsuario>5 && nivelXUsuario<=10){
            experiencia = 150;
            nivel = (int) Math.floor(Math.random() * 10 + 1);

            if (tipoX == 1){ //ataque mejor
                ataque = (int) Math.floor(Math.random()*((90-70)+70));
                defensa = (int) Math.floor(Math.random()*((60-20)+20));
                vida = (int) Math.floor(Math.random()*((60-20)+20));
            }
            else if (tipoX == 2){ //defensa mejor
                ataque = (int) Math.floor(Math.random()*((60-20)+20));
                defensa = (int) Math.floor(Math.random()*((90-70)+70));
                vida = (int) Math.floor(Math.random()*((60-20)+20));
            }
            else{ //salud mejor
                ataque = (int) Math.floor(Math.random()*((60-20)+20));
                defensa = (int) Math.floor(Math.random()*((60-20)+20));
                vida = (int) Math.floor(Math.random()*((90-70)+70));
            }

        }
        else {
            experiencia = 200;
            nivel = (int) Math.floor(Math.random() * 15 + 1);
            if (tipoX == 1){ //ataque mejor
                ataque = (int) Math.floor(Math.random()*((100-80)+70));
                defensa = (int) Math.floor(Math.random()*((65-50)+50));
                vida = (int) Math.floor(Math.random()*((65-50)+50));
            }
            else if (tipoX == 2){ //defensa mejor
                ataque = (int) Math.floor(Math.random()*((65-50)+50));
                defensa = (int) Math.floor(Math.random()*((100-80)+70));
                vida = (int) Math.floor(Math.random()*((65-50)+50));
            }
            else{ //salud mejor
                ataque = (int) Math.floor(Math.random()*((65-50)+50));
                defensa = (int) Math.floor(Math.random()*((65-50)+50));
                vida = (int) Math.floor(Math.random()*((100-80)+70));
            }
        }

        //y finalmente la insertamos

        StringBuffer stringBuffer = new StringBuffer("INSERT into captura (idusuariosss,idetakemon,idlocalizacion,nivel,experiencia,vida,ataque,defensa,estado,fecha) VALUES (");
        stringBuffer.append(idusuario+",");
        stringBuffer.append(captura.getIdetakemon()+",");
        stringBuffer.append(captura.getIdlocalizacion()+",");
        stringBuffer.append(nivel+",");
        stringBuffer.append(experiencia+",");
        stringBuffer.append(vida+",");
        stringBuffer.append(ataque+",");
        stringBuffer.append(defensa+",");
        stringBuffer.append(estado+",");
        stringBuffer.append("'"+fecha+"');");

            try {
                PreparedStatement preparedStatement = con.prepareStatement(stringBuffer.toString());
                System.out.println(stringBuffer.toString());
                preparedStatement.execute();
                capturaInsertada = true;

                //si se inserta, hay que sumarle la experiencia al usuario
                usuario.updateUsuarioExp(idusuario,nivelXUsuario,experienciaXUsuario,experiencia);
            }
            catch (SQLException e) {
                capturaInsertada = false;
                e.printStackTrace();
                logger.error(e.getMessage());
            }

        return capturaInsertada;
    }

    public List<Captura> getAllCapturas()  {
        List<Captura> listaCaptura = new ArrayList<Captura>();

        try {
            Statement stmt = null;
            stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM captura");
            while (rs.next()) {
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
        }
        catch (SQLException e){
            e.printStackTrace();
            logger.error("getAllCapturas: "+e.getMessage());
        }
        return listaCaptura;
    }

}
