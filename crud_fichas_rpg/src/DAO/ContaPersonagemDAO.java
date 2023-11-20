
package DAO;

import Main.ConexaoMySQL;
import Model.Personagem;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ContaPersonagemDAO {
    
     public void associarPersonagemConta(int idConta, int idPersonagem){
        try{
            Connection conn = ConexaoMySQL.getConexaoMySQL();
            PreparedStatement ps = conn.prepareStatement("INSERT INTO contaPersonagem (idConta, idPersonagem) VALUES(?,?)");
            ps.setInt(1, idConta);
            ps.setInt(2, idPersonagem);

            ps.executeUpdate();
        }catch(SQLException ex){
            System.out.println(ex);
        }
    }

    public void desassociarPersonagemConta(int idConta, int idPersonagem){
        try{
            Connection conn = ConexaoMySQL.getConexaoMySQL();
            PreparedStatement ps = conn.prepareStatement("DELETE FROM contaPersonagem WHERE idConta =? AND idPersonagem=?");
            ps.setInt(1, idConta);
            ps.setInt(2, idPersonagem);

            ps.executeUpdate();
        }catch(SQLException ex){
            System.out.println(ex);
        }
    }

    public ArrayList<Personagem> buscarPersonagemConta(int idConta){
        ArrayList<Personagem> personagens = new ArrayList<Personagem>();

        try{
            Connection conn = ConexaoMySQL.getConexaoMySQL();
            PreparedStatement ps = conn.prepareStatement("SELECT personagem.*, contaPersonagem.idConta FROM contaPersonagem "
            + "JOIN personagem ON contaPersonagem.idPersonagem = Personagem.idPersonagem WHERE contaPersonagem.idConta = ?");
            ps.setInt(1, idConta);

            ResultSet rs = ps.executeQuery();

            while(rs.next()){
                Personagem personagem = new Personagem(rs.getInt(1), rs.getString(2), rs.getInt(3));
                personagens.add(personagem);
            }
        }catch(SQLException ex){
            System.out.println(ex);
        }
        return personagens;
    }
    
}
