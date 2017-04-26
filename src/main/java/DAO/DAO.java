package DAO;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import Modelo.Logros;
import Modelo.Objetos;
import Modelo.Usuario;
import org.apache.log4j.Logger;
import static org.apache.log4j.Logger.getLogger;

/**
 * Created by ivanm on 26/04/2017.
 */
public abstract class DAO {

    static DBConnection connection = new DBConnection();
    static Connection con = connection.getCon();

    final Logger logger = getLogger("DAO");

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
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
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

        try {
            PreparedStatement preparedStatement = con.prepareStatement(sb.toString());
            insertElements(preparedStatement);
            preparedStatement.execute();
        }
        catch (SQLException e){
            e.printStackTrace();
            System.out.println(e.getMessage());
        }

        System.out.println(sb);
    }

    public void select(int id){

        StringBuffer sb = new StringBuffer("SELECT *");

        sb.append(" FROM ");

        sb.append(this.getClass().getSimpleName());

        sb.append(" WHERE id = "+id);

        try {
            Statement stat = con.createStatement();
            ResultSet rs = stat.executeQuery(sb.toString());
            ResultSetMetaData rsmd = rs.getMetaData();
            rs.next();

            for (int i=1; i<rsmd.getColumnCount() +1; i++){

            }

            for (int i=1; i<rsmd.getColumnCount() + 1; i++){

                if (rsmd.getColumnTypeName(i).equals("INT")){
                    System.out.println(rsmd.getColumnLabel(i)+ ": "+rs.getInt(i));
                }
                if (rsmd.getColumnTypeName(i).equals("VARCHAR")){
                    System.out.println(rsmd.getColumnLabel(i)+ ": " +rs.getString(i));
                }
                if (i==rsmd.getColumnCount()){
                    rs.next();
                    i = 0;
                }
            }
        }
        catch (SQLException e){
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
    }

    public void delete(int id){
        StringBuffer sb = new StringBuffer("DELETE FROM ");
        sb.append(this.getClass().getSimpleName());
        sb.append(" WHERE id = " + id);
        System.out.println(sb);

        try {
            PreparedStatement preparedStatement = con.prepareStatement(sb.toString());
            preparedStatement.execute();
        }
        catch (SQLException e){
            e.printStackTrace();
            System.out.println(e.getMessage());
        }

    }

    //update
    
}
