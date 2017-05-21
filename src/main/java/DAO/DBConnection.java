package DAO;

import org.apache.log4j.Logger;

import javax.inject.Singleton;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

import static org.apache.log4j.Logger.getLogger;

/**
 * Created by ivanm on 26/04/2017.
 */
public class DBConnection {
    final Logger logger = getLogger("DBConnection");

    private Connection con;

    @Singleton
    public DBConnection(){
        Connection con = null;
        try{
            String host = "localhost", database = "juego";
            int port = 3306;
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url = "jdbc:mysql://" + host + ":" + port + "/" + database;
            Properties info = new Properties();
            info.setProperty("user", "root");
            info.setProperty("password", "mysql");
            info.setProperty("useSSL", "false");
            info.setProperty("serverTimezone", "UTC");
            con = DriverManager.getConnection(url, info);
            logger.info("Conexión BBDD creada \n");
        }
        catch (Exception e){
            logger.error(e.getMessage());
            e.printStackTrace();
        }
        setCon(con);
    }

    public Connection getCon() {
        return con;
    }

    public void setCon(Connection con) {
        this.con = con;
    }
}
