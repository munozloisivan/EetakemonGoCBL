package Main;
import DAO.DAO;
import Modelo.*;
import org.apache.log4j.BasicConfigurator;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.Calendar;

/**
 * Created by ivanm on 26/04/2017.
 */
public class App {

    public static void main(String[] args) throws SQLException {

                                //      NO BORRAR
        //****************************************************************************************************
        BasicConfigurator.configure();    //es para el logger

        //     java.util.Date utilDate = new java.util.Date();
        //      java.sql.Date sq = new java.sql.Date(utilDate.getTime());

        //Establecer fecha
        java.sql.Date date = new java.sql.Date(Calendar.getInstance().getTime().getTime());
        // java.sql.Date date2 = new java.sql.Date(Calendar.getInstance().getTimeInMillis());

                                //^^^^^^^ NO BORRAR ^^^^^
        // **********************************************************************************************





        // ------------------- PRUEBAS ---------------


        //  Usuario us = new Usuario();
        //      us.updateUsuarioData(0,"marionn3","password");


        //  Usuario u = new Usuario(5,"Daniel","Sobmod2","dani@gmail.com","password",3,200,0);
        //  u.update(5);




   /*     Logros logros1 = new Logros();
        logros1.delete(3);
        logros1.setNombre("etakecolector");
        logros1.setDescripcion("captura 10 etakemons");
        logros1.setExperiencia(100);
        logros1.insert();

        Logros logros2 = new Logros();
        logros2.setNombre("etakeAtakker");
        logros2.setDescripcion("Gana 2 batallas");
        logros2.setExperiencia(100);
        logros2.insert();
*/




     /*   Batalla batalla = new Batalla();
        batalla.setIdcaptura(1);
        batalla.setResultado(1);
        batalla.setExperiencia(100);
        batalla.setFecha(date);
        batalla.insert();

        */

    /*  Captura captur = new Captura();
        captur.setIdusuariosss(4);
        captur.setIdetakemon(0);
        captur.setIdlocalizacion(0);
        captur.setNivel(5);
        captur.setExperiencia(87);
        captur.setVida(64);
        captur.setAtaque(99);
        captur.setDefensa(65);
        captur.setEstado(1);
        captur.setFecha(sq);
        captur.insert();
        */


 /*       System.out.println("capturas usuario 1: \n");

        DAO.getCapturasUsuario(1);
        for (int i=0; i<DAO.getCapturasUsuario(1).size();i++){
                System.out.println("id: "+DAO.getCapturasUsuario(1).get(i).getId());
                System.out.println("idusuario: "+ DAO.getCapturasUsuario(1).get(i).getIdusuariosss());
                System.out.println("idetakemon:" +DAO.getCapturasUsuario(1).get(i).getIdetakemon());
                System.out.println("idlocalizacion: "+DAO.getCapturasUsuario(1).get(i).getIdlocalizacion());
                System.out.println("nivel: "+DAO.getCapturasUsuario(1).get(i).getNivel());
                System.out.println("experiencia: "+DAO.getCapturasUsuario(1).get(i).getExperiencia());
                System.out.println("vida: "+DAO.getCapturasUsuario(1).get(i).getVida());
                System.out.println("ataque: "+DAO.getCapturasUsuario(1).get(i).getAtaque());
                System.out.println("defensa: "+DAO.getCapturasUsuario(1).get(i).getDefensa());
                System.out.println("estado: "+DAO.getCapturasUsuario(1).get(i).getEstado());
                System.out.println("fecha: "+DAO.getCapturasUsuario(1).get(i).getFecha());
                System.out.println("\n");
            }
*/

  /*      System.out.println("Batallas ganadas X");
        //escoges una captura de las del usuario ---> getCapturasUsuario(int id)
        int idcaptura = 0;
        DAO.getBatallasGanadasUsuario(idcaptura);
        for(int i=0; i<DAO.getBatallasGanadasUsuario(idcaptura).size(); i++){
            System.out.println("id batalla: "+DAO.getBatallasGanadasUsuario(idcaptura).get(i).getId());
            System.out.println("idcaptura: "+DAO.getBatallasGanadasUsuario(idcaptura).get(i).getIdcaptura());
        }
*/

       //Usuario receive = new Usuario();
       //receive.select(4);




   /*   Usuario us1 = new Usuario();
        us1.setNombre("Marc");
        us1.setNick("mark33");
        us1.setEmail("mark@gmail.com");
        us1.setContrasena("password");
        us1.setNivel(3);
        us1.setExperiencia(300);
        us1.setModified(0);
        us1.insert();
*/
      /*  Etakemon et1 = new Etakemon(0,"Alakasals",300,"hipnosis",1);
        et1.insert();
        Etakemon et2 = new Etakemon(1,"Rinchorn",500,"dobleCreditoPaTi",2);
        et2.insert(); */

     /*   Etakemon et = new Etakemon();
        et.delete(1);
        */

     /*   Localizacion loc1 = new Localizacion(0,"Turbina",41.000,35.32);
        loc1.insert();

        Localizacion loc2 = new Localizacion(1,"Biblioteca",43.987,35.32);
        loc2.insert();
       */

    }
}