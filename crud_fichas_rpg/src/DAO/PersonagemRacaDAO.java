
package DAO;

import Main.ConexaoMySQL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class PersonagemRacaDAO {
    
    public void associarRacaPersonagem(int idPersonagem, int idRaca){
        try{
            Connection conn = ConexaoMySQL.getConexaoMySQL();
            PreparedStatement ps = conn.prepareStatement("INSERT INTO personagemRaca (idPersonagem, idRaca) VALUES(?,?)");
            ps.setInt(1, idPersonagem);
            ps.setInt(2, idRaca);

            ps.executeUpdate();
        }catch(SQLException ex){
            System.out.println(ex);
        }
    }

    public void desassociarRacaPersonagem(int idPersonagem, int idRaca){
        try{
            Connection conn = ConexaoMySQL.getConexaoMySQL();
            PreparedStatement ps = conn.prepareStatement("DELETE FROM personagemRaca WHERE idPersonagem =? AND idRaca=?");
            ps.setInt(1, idPersonagem);
            ps.setInt(2, idRaca);

            ps.executeUpdate();
        }catch(SQLException ex){
            System.out.println(ex);
        }
    }
    
}
