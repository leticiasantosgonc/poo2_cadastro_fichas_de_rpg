
package DAO;

import Main.ConexaoMySQL;
import Model.Personagem;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;


public class PersonagemDAO {
    
    public int insert(Personagem personagem){ // inserir personagem
        try{
            Connection conn = ConexaoMySQL.getConexaoMySQL();

            PreparedStatement ps = conn.prepareStatement("INSERT INTO personagem(nome, nivel) VALUES (?,?)", Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, personagem.getNome());
            ps.setInt(2, personagem.getNivel());

            int rowCount = ps.executeUpdate();
            conn.close();
            return rowCount;
        }catch(SQLException ex){
            System.out.println(ex);
        return 0;
        }
    }
    
    public Personagem read(int idPersonagem){ //procurar um personagem
        try{
            Connection conn = ConexaoMySQL.getConexaoMySQL();
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM personagem WHERE idPersonagem=?");
            ps.setInt(1, idPersonagem);
            ResultSet rs = ps.executeQuery();
            
            if(rs.next()){
                String nome = rs.getString(2);
                int nivel = rs.getInt(3);
                Personagem personagem = new Personagem (idPersonagem, nome, nivel);
                return personagem;
            }
            conn.close();
        }catch(SQLException ex){
            System.out.println(ex);
        }
        return null;
    }
    
    public ArrayList<Personagem> list(){ //lista todas os personagens
        ArrayList<Personagem> personagens = new ArrayList<Personagem>();
        try{
            Connection conn = ConexaoMySQL.getConexaoMySQL();
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM personagem");
            ResultSet rs = ps.executeQuery();
            
            while(rs.next()){
                int idPersonagem = rs.getInt(1);
                String nome = rs.getString(2);
                int nivel = rs.getInt(3);
                Personagem personagem = new Personagem(idPersonagem, nome, nivel);
                personagens.add(personagem);
            }
            conn.close();
        }catch(SQLException ex){
            System.out.println(ex);
        }
        return personagens;
    }
    
    public int update(Personagem personagem){ //atualiza um personagem
        try{
            Connection conn = ConexaoMySQL.getConexaoMySQL();
            PreparedStatement ps = conn.prepareStatement("UPDATE personagem SET nome=?, nivel=? WHERE idPersonagem=?");
            ps.setString(1, personagem.getNome());
            ps.setInt(2, personagem.getNivel());
            ps.setInt(3, personagem.getIdPersonagem());
            
            int rowCount = ps.executeUpdate();
            conn.close();
            return rowCount;
            
        }catch(SQLException ex){
            System.out.println(ex);
        }
        return 0;
    }
    public int delete(int idPersonagem){ //deleta uma conta
        try{
           Connection conn = ConexaoMySQL.getConexaoMySQL();
           PreparedStatement ps = conn.prepareStatement("DELETE FROM personagem WHERE idPersonagem=?");
           ps.setInt(1, idPersonagem);
           
           int rowCount = ps.executeUpdate();
           conn.close();
           return rowCount;
           
        }catch(SQLException ex){
            System.out.println(ex);
        }
        return 0;
    }
    
}
