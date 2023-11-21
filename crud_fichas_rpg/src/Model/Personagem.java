
package Model;

public class Personagem {
    private int idPersonagem;
    private String nome;
    private int nivel;

    public Personagem(int idPersonagem, String nome, int nivel) {
        this.idPersonagem = idPersonagem;
        this.nome = nome;
        this.nivel = nivel;
    }

    public Personagem(String nome, int nivel) {
        this.idPersonagem = idPersonagem;
        this.nome = nome;
        this.nivel = nivel;
    } 
    

    public int getIdPersonagem() {
        return idPersonagem;
    }

    public void setIdPersonagem(int idPersonagem) {
        this.idPersonagem = idPersonagem;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getNivel() {
        return nivel;
    }

    public void setNivel(int nivel) {
        this.nivel = nivel;
    }
    
    
}
