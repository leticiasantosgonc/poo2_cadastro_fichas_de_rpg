
package DAO;

import Model.Conta;
import Main.ConexaoMySQL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class ContaDAO {
    public int insert(Conta conta){ // inserir conta
        try{
            Connection conn = ConexaoMySQL.getConexaoMySQL();

            PreparedStatement ps = conn.prepareStatement("INSERT INTO conta(login, senha) VALUES (?,?)", Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, conta.getLogin());
            ps.setString(2, conta.getSenha());

            int rowCount = ps.executeUpdate();
            conn.close();
            return rowCount;
        }catch(SQLException ex){
            System.out.println(ex);
        return 0;
        }
    }
    
    public Conta readLogin(String login){ //procurar uma conta pelo login
        try{
            Connection conn = ConexaoMySQL.getConexaoMySQL();
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM conta WHERE login=?");
            ps.setString(1, login);
            ResultSet rs = ps.executeQuery();
            
            if(rs.next()){
                String senha = rs.getString(3);
                int idConta = rs.getInt(1);
                Conta conta = new Conta (idConta, login, senha);
                return conta;
            }
            conn.close();
        }catch(SQLException ex){
            System.out.println(ex);
        }
        return null;
    }
    
    public Conta read(int idConta){ //procurar uma conta
        try{
            Connection conn = ConexaoMySQL.getConexaoMySQL();
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM conta WHERE idConta=?");
            ps.setInt(1, idConta);
            ResultSet rs = ps.executeQuery();
            
            if(rs.next()){
                String login = rs.getString(2);
                String senha = rs.getString(3);
                Conta conta = new Conta (idConta, login, senha);
                return conta;
            }
            conn.close();
        }catch(SQLException ex){
            System.out.println(ex);
        }
        return null;
    }
    
    public ArrayList<Conta> list(){ //lista todas as contas
        ArrayList<Conta> contas = new ArrayList<Conta>();
        try{
            Connection conn = ConexaoMySQL.getConexaoMySQL();
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM conta");
            ResultSet rs = ps.executeQuery();
            
            while(rs.next()){
                int idConta = rs.getInt(1);
                String login = rs.getString(2);
                Conta conta = new Conta(idConta, login);
                contas.add(conta);
            }
            conn.close();
        }catch(SQLException ex){
            System.out.println(ex);
        }
        return contas;
    }
    
    public int update(Conta conta){ //atualiza uma conta
        try{
            Connection conn = ConexaoMySQL.getConexaoMySQL();
            PreparedStatement ps = conn.prepareStatement("UPDATE conta SET login=?, senha=? WHERE idConta=?");
            ps.setString(1, conta.getLogin());
            ps.setString(2, conta.getSenha());
            ps.setInt(3, conta.getIdConta());
            
            int rowCount = ps.executeUpdate();
            conn.close();
            return rowCount;
            
        }catch(SQLException ex){
            System.out.println(ex);
        }
        return 0;
    }
    public int delete(int idConta){ //deleta uma conta
        try{
           Connection conn = ConexaoMySQL.getConexaoMySQL();
           PreparedStatement ps = conn.prepareStatement("DELETE FROM conta WHERE idConta=?");
           ps.setInt(1, idConta);
           
           int rowCount = ps.executeUpdate();
           conn.close();
           return rowCount;
           
        }catch(SQLException ex){
            System.out.println(ex);
        }
        return 0;
    }
}
