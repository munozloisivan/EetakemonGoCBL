package DAO;

import Modelo.Captura;
import org.apache.log4j.Logger;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import static org.apache.log4j.Logger.getLogger;

/**
 * Created by ivanm on 14/05/2017.
 */
public abstract class CapturaDAO extends DAO {

    final Logger logger = getLogger("CapturaDAO");

    public List<Captura> getAllCapturas()  {
        List<Captura> listaCaptura = new ArrayList<Captura>();

        try {
            Statement stmt = null;
            stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM Captura");
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
