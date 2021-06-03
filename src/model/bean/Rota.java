package model.bean;

/**
 *
 * @author Pedro Citadin Coelho
 */
public class Rota {

    private int codOrigem;
    private int codDestino;
    private int km;
    private String cidadeOrigem;
    private String cidadeDestino;

    public int getCodOrigem() {
        return codOrigem;
    }

    public void setCodOrigem(int codOrigem) {
        this.codOrigem = codOrigem;
    }

    public int getCodDestino() {
        return codDestino;
    }

    public void setCodDestino(int codDestino) {
        this.codDestino = codDestino;
    }

    public int getKm() {
        return km;
    }

    public void setKm(int km) {
        this.km = km;
    }

    public String getCidadeOrigem() {
        return cidadeOrigem;
    }

    public void setCidadeOrigem(String cidadeOrigem) {
        this.cidadeOrigem = cidadeOrigem;
    }

    public String getCidadeDestino() {
        return cidadeDestino;
    }

    public void setCidadeDestino(String cidadeDestino) {
        this.cidadeDestino = cidadeDestino;
    }

    public Rota(int codOrigem, int codDestino, int km, String cidadeOrigem, String cidadeDestino) {
        this.codOrigem = codOrigem;
        this.codDestino = codDestino;
        this.km = km;
        this.cidadeOrigem = cidadeOrigem;
        this.cidadeDestino = cidadeDestino;
    }

    public Rota() {
    }

    public String retornaEscrita() {
        return this.codOrigem + ";;;;;" + this.cidadeOrigem + ";;;;;" + this.codDestino + ";;;;;" + this.cidadeDestino + ";;;;;" + this.km;
    }
}
