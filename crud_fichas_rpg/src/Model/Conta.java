
package Model;


public class Conta {
    
    private int idConta;
    private String login;
    private String senha;
    
    public Conta(int idConta, String login, String senha){
        this.idConta = idConta;
        this.login = login;
        this.senha = senha;
    }

    public Conta(int idConta, String login) {
        this.idConta = idConta;
        this.login = login;
    }   

    public Conta(String login, String senha) {
        this.login = login;
        this.senha = senha;
    }

    public int getIdConta() {
        return idConta;
    }

    public void setIdConta(int idConta) {
        this.idConta = idConta;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }    
    
}
