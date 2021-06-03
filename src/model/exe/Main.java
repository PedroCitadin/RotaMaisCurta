package model.exe;

import model.Arquivo;
import model.view.TelaBusca;
import model.view.TelaConfiguracoes;

/**
 *
 * @author Pedro Citadin Coelho
 */
public class Main {

    public static void main(String[] args) {
        Arquivo arq = new Arquivo();
        arq.setNome("config.txt");
        arq.setEndereco("config//");
        if (arq.verificaExistencia()) {
            TelaBusca tb = new TelaBusca();
            tb.setVisible(true);
        } else {
            TelaConfiguracoes tc = new TelaConfiguracoes();
            tc.setVisible(true);
        }

    }
}
