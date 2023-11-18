
package Main;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexaoMySQL {
    
    public static java.sql.Connection getConexaoMySQL(){
        
        Connection conn = null;
        
        String serverName = "localhost";
        String myDatabase = "rpg";
        String url = "jdbc:mysql://" + serverName + "/" + myDatabase;
        String username = "root";
        String password = "";
        
        try {
            conn = DriverManager.getConnection(url, username, password);
        } catch (SQLException e){
            e.printStackTrace();
        }
        return conn;
    }
}
