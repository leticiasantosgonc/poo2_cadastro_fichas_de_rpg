
package DAO;

import Main.ConexaoMySQL;
import Model.Raca;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;


public class RacaDAO {
    
     public int insert(Raca raca){ // inserir raca
        try{
            Connection conn = ConexaoMySQL.getConexaoMySQL();

            PreparedStatement ps = conn.prepareStatement("INSERT INTO raca(nome, descricao, fraqueza, classe) VALUES (?,?,?,?)", Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, raca.getNome());
            ps.setString(2, raca.getDescricao());
            ps.setInt(3, raca.getFraqueza());
            ps.setInt(4, raca.getClasse());

            int rowCount = ps.executeUpdate();
            ResultSet generatedKeys = ps.getGeneratedKeys();
            
            if(generatedKeys.next()){
                int generatedId = generatedKeys.getInt(1);
                raca.setIdRaca(generatedId);
            }else{
                System.out.println("Nenhuma chave foi gerada");
            }
            generatedKeys.close();
            ps.close();
            conn.close();
            return rowCount;
        }catch(SQLException ex){
            System.out.println(ex);
        return 0;
        }
    }
     
    public Raca read(int idRaca){ //procurar uma raca
        try{
            Connection conn = ConexaoMySQL.getConexaoMySQL();
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM raca WHERE idRaca=?");
            ps.setInt(1, idRaca);
            ResultSet rs = ps.executeQuery();
            
            if(rs.next()){
                String nome = rs.getString(2);
                String descricao = rs.getString(3);
                int fraqueza = rs.getInt(4);
                int classe = rs.getInt(5);
                Raca raca = new Raca (idRaca, nome, descricao, fraqueza, classe);
                return raca;
            }
            conn.close();
        }catch(SQLException ex){
            System.out.println(ex);
        }
        return null;
    }
    
    public ArrayList<Raca> list(){ //lista todas as racas
        ArrayList<Raca> racas = new ArrayList<Raca>();
        try{
            Connection conn = ConexaoMySQL.getConexaoMySQL();
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM raca");
            ResultSet rs = ps.executeQuery();
            
            while(rs.next()){
                int idRaca = rs.getInt(1);
                String nome = rs.getString(2);
                String descricao = rs.getString(3);
                int fraqueza = rs.getInt(4);
                int classe = rs.getInt(5);
                Raca raca = new Raca (idRaca, nome, descricao, fraqueza, classe);
                racas.add(raca);
            }
            conn.close();
        }catch(SQLException ex){
            System.out.println(ex);
        }
        return racas;
    }
    
    public int update(Raca raca){ //atualiza uma raca
        try{
            Connection conn = ConexaoMySQL.getConexaoMySQL();
            PreparedStatement ps = conn.prepareStatement("UPDATE raca SET nome=?, descricao=?, fraqueza=?, classe=? WHERE idRaca=?");
            ps.setString(1, raca.getNome());
            ps.setString(2, raca.getDescricao());
            ps.setInt(3, raca.getFraqueza());
            ps.setInt(4, raca.getClasse());
            ps.setInt(5, raca.getIdRaca());
            
            int rowCount = ps.executeUpdate();
            conn.close();
            return rowCount;
            
        }catch(SQLException ex){
            System.out.println(ex);
        }
        return 0;
    }
    public int delete(int idRaca){ //deleta uma raca
        try{
           Connection conn = ConexaoMySQL.getConexaoMySQL();
           PreparedStatement ps = conn.prepareStatement("DELETE FROM raca WHERE idRaca=?");
           ps.setInt(1, idRaca);
           
           int rowCount = ps.executeUpdate();
           conn.close();
           return rowCount;
           
        }catch(SQLException ex){
            System.out.println(ex);
        }
        return 0;
    }
}
