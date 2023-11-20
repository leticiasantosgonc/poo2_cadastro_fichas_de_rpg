
package Model;

public class Raca {
   private int idRaca;
   private String nome;
   private String descricao;
   private int fraqueza;
   private int classe;

    public Raca(int idRaca, String nome, String descricao, int fraqueza, int classe) {
        this.idRaca = idRaca;
        this.nome = nome;
        this.descricao = descricao;
        this.fraqueza = fraqueza;
        this.classe = classe;
    }

    public Raca(String nome, String descricao, int fraqueza, int classe) {
        this.idRaca = idRaca;
        this.nome = nome;
        this.descricao = descricao;
        this.fraqueza = fraqueza;
        this.classe = classe;
    }
    
    public int getIdRaca() {
        return idRaca;
    }

    public void setIdRaca(int idRaca) {
        this.idRaca = idRaca;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public int getFraqueza() {
        return fraqueza;
    }

    public void setFraqueza(int fraqueza) {
        this.fraqueza = fraqueza;
    }

    public int getClasse() {
        return classe;
    }

    public void setClasse(int classe) {
        this.classe = classe;
    }
   
   
   
}
