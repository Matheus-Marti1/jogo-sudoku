package model;

public class Celula {
    private int valor;
    private boolean isFixo;

    public Celula(int valor, boolean isFixo) {
        this.valor = valor;
        this.isFixo = isFixo;
    }

    public Celula() {
        this.valor = 0; // 0 representa uma célula vazia
        this.isFixo = false;
    }

    public int getValor() {
        return valor;
    }

    public void setValor(int valor) {
        this.valor = valor;
    }

    public boolean isFixo() {
        return isFixo;
    }

    public void setFixo(boolean fixo) {
        isFixo = fixo;
    }
}
