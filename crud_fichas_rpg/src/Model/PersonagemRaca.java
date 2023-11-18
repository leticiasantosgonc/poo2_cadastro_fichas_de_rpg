
package Model;


public class PersonagemRaca {
    private int idPersonagem;
    private int idRaca;

    public PersonagemRaca(int idPersonagem, int idRaca) {
        this.idPersonagem = idPersonagem;
        this.idRaca = idRaca;
    }

    public int getIdPersonagem() {
        return idPersonagem;
    }

    public void setIdPersonagem(int idPersonagem) {
        this.idPersonagem = idPersonagem;
    }

    public int getIdRaca() {
        return idRaca;
    }

    public void setIdRaca(int idRaca) {
        this.idRaca = idRaca;
    }
    
    
}
