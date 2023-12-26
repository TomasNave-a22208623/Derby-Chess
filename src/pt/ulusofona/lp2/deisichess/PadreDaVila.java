package pt.ulusofona.lp2.deisichess;

public class PadreDaVila extends Peca{

    public PadreDaVila(int id,int tipo, int equipa, String alcunha) {
        super(id,tipo, equipa, alcunha);
        definirPontos();
        defenirImagem();
    }

    @Override
    String toString(ContadorRondas contadorRondas) {

        if(!getEstado()) {
            return getID() + " | " +getTipoString()+ " | " +getValor()+ " | " + getEquipa() + " | " + getAlcunha() + " @ (n/a)";
        }
        return getID() + " | " +getTipoString()+ " | " +getValor()+ " | " + getEquipa() + " | " + getAlcunha() +  " @ (" + getX() + ", " + getY() + ")";
    }

    @Override
    String getTipoString() {
        return "Padre da Vila";
    } //testeeeeeeeeeeeee

    @Override
    void definirPontos() {
        this.valor = 3;
    }

    @Override
    void defenirImagem(){
        if(getEquipa()==10){
            this.imagem = "padre_vila_black.png";
        }else{
            this.imagem = "padre_vila_white.png";
        }
    }

    @Override
    boolean validMove(int xFinal, int yFinal, Tabuleiro tabuleiro) {
        // Verificar se a posição de destino está dentro do tabuleiro
        if (xFinal < 0 || xFinal >= tabuleiro.getTamanho() || yFinal < 0 || yFinal >= tabuleiro.getTamanho()) {
            return false;
        }

        // Verificar se a posição de destino está na diagonal
        int deltaX = Math.abs(xFinal - getX());
        int deltaY = Math.abs(yFinal - getY());
        if (deltaX != deltaY || deltaX > 3) { //Fazer retas no papel
            return false;
        }

        // Verificar se há peças no caminho
        int stepX = (xFinal - getX()) / Math.max(1, deltaX);
        int stepY = (yFinal - getY()) / Math.max(1, deltaY);

        for (int i = 1; i < deltaX; i++) {
            int currentX = getX() + i * stepX;
            int currentY = getY() + i * stepY;

            // Verificar se há uma peça na posição atual do caminho
            if (tabuleiro.getPeca(currentX, currentY) != null) {
                return false; // Movimento inválido devido a uma peça no caminho
            }
        }

        // Verificar se a posição de destino está vazia ou contém uma peça adversária
        Peca pecaDestino = tabuleiro.getPeca(xFinal, yFinal);
        return pecaDestino == null || pecaDestino.getEquipa() != getEquipa();
    }
}
