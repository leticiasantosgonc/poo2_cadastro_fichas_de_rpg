
package DAO;

import Main.ConexaoMySQL;
import Model.Raca;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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

    public void desassociarRacaPersonagem(int idPersonagem){
        try{
            Connection conn = ConexaoMySQL.getConexaoMySQL();
            PreparedStatement ps = conn.prepareStatement("DELETE FROM personagemRaca WHERE idPersonagem =?");
            ps.setInt(1, idPersonagem);

            ps.executeUpdate();
        }catch(SQLException ex){
            System.out.println(ex);
        }
    }
    
    public Raca getRacaById(int idPersonagem) {
        try {
            Connection conn = ConexaoMySQL.getConexaoMySQL();
            String query = "SELECT raca.* FROM personagemRaca "
                         + "JOIN raca ON personagemRaca.idRaca = raca.idRaca "
                         + "WHERE personagemRaca.idPersonagem = ?";
            
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, idPersonagem);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                int idRaca = rs.getInt("idRaca");
                String nome = rs.getString("nome");
                String descricao = rs.getString("descricao");
                int fraqueza = rs.getInt("fraqueza");
                int classe = rs.getInt("classe");

                Raca raca = new Raca(idRaca, nome, descricao, fraqueza, classe);
                conn.close();
                return raca;
            } else {
                System.out.println("Relação entre Personagem e Raça não encontrada.");
            }
            conn.close();
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return null;
    }
}
