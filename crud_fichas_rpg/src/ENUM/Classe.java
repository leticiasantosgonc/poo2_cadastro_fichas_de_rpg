
package ENUM;

public enum Classe {
    PALADINO(1),
    ARQUEIRO(2),
    MAGO(3),
    BRUXO(4),
    DRUIDA(5),
    LADINO(6),
    BARDO(7);
    
    private final int valor;

    private Classe(int valor) {
        this.valor = valor;
    }

    public int getValor() {
        return valor;
    }
}
