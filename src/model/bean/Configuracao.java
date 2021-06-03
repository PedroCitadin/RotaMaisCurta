
package model.bean;

import java.io.IOException;
import java.util.List;
import model.Arquivo;

/**
 *
 * @author Pedro Citadin Coelho
 */
public class Configuracao {
    private String pasta;
    private String sucesso;
    private String erro;
    private boolean rotaAuto;
    private Arquivo arq;

    public Configuracao(String pasta, String sucesso, String erro, boolean rotaAuto) {
        this.pasta = pasta;
        this.sucesso = sucesso;
        this.erro = erro;
        this.rotaAuto = rotaAuto;
        arq = new Arquivo("config.txt", "config//");
        
    }

    public Configuracao() {
    }

    public String getPasta() {
        return pasta;
    }

    public void setPasta(String pasta) {
        this.pasta = pasta;
    }

    public String getSucesso() {
        return sucesso;
    }

    public void setSucesso(String sucesso) {
        this.sucesso = sucesso;
    }

    public String getErro() {
        return erro;
    }

    public void setErro(String erro) {
        this.erro = erro;
    }

    public boolean isRotaAuto() {
        return rotaAuto;
    }

    public void setRotaAuto(boolean rotaAuto) {
        this.rotaAuto = rotaAuto;
    }

    public Arquivo getArq() {
        return arq;
    }

    public void setArq(Arquivo arq) {
        this.arq = arq;
    }
    
    public void configuraSistema() throws IOException{
        
        arq.criaDiretorio(pasta);
        arq.criaArquivo(arq);
        arq.criaDiretorio(sucesso);
        arq.criaDiretorio(erro);
        arq.escreveNoArquivo(arq, "Pasta="+pasta);
        arq.escreveNaProxiLinhaDoArquivo(arq, "Sucesso="+sucesso);
        arq.escreveNaProxiLinhaDoArquivo(arq, "Erro="+erro);
        arq.escreveNaProxiLinhaDoArquivo(arq, "Rota Automatica="+rotaAuto);
    }
    public void pegaConfiguracao() throws IOException{
        arq = new Arquivo("config.txt", "config//");
        List<String> linhas = arq.pegalistaLinhas(arq);
        this.pasta = linhas.get(0).split("=")[1];
        this.sucesso = linhas.get(1).split("=")[1];
        this.erro = linhas.get(2).split("=")[1];
        this.rotaAuto =Boolean.parseBoolean(linhas.get(3).split("=")[1]);
    }
}
