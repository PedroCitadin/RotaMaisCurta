package model.exe;

import java.awt.AWTException;
import java.awt.HeadlessException;
import java.awt.Menu;
import java.awt.MenuItem;
import java.awt.PopupMenu;
import java.awt.SystemTray;
import java.awt.TrayIcon;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import model.Arquivo;
import model.bean.Configuracao;
import model.view.TelaBusca;
import model.view.TelaConfiguracoes;

/**
 *
 * @author Pedro Citadin Coelho
 */
public class Main {
    public Main() throws AWTException {
           createTrayIcon();
    }
    private void createTrayIcon() throws AWTException {
        PopupMenu popupMenu = new PopupMenu();
        
        TrayIcon tray = new TrayIcon(new ImageIcon("img//icon.png").getImage());
        MenuItem menuConf = new MenuItem("Configuração");
        menuConf.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent evt){
                
                TelaConfiguracoes tc = null;
                try {
                    tc = new TelaConfiguracoes(new Configuracao());
                } catch (HeadlessException ex) {
                    Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IOException ex) {
                    Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                }
                tc.setVisible(true);
            }
        });
        MenuItem menuVisivel = new MenuItem("Visivel");
        menuVisivel.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent evt){
                TelaBusca tb = new TelaBusca();
                tb.setVisible(true);
            }
        });
        MenuItem menuSair = new MenuItem("Sair");
        menuSair.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent evt){
                System.exit(0);
            }
        
        
        });
        popupMenu.add(menuConf);
        popupMenu.add(menuVisivel);
        popupMenu.add(menuSair);   
        tray.setPopupMenu(popupMenu);
        SystemTray st = SystemTray.getSystemTray();
        st.add(tray);
    }
    
    
    public static void main(String[] args) throws IOException, AWTException {
        Arquivo arq = new Arquivo();
        arq.setNome("config.txt");
        arq.setEndereco("config//");
        if (arq.verificaExistencia()) {
           Configuracao cf = new Configuracao();
           cf.pegaConfiguracao();
           if(cf.isRotaAuto()){
               new Main();
               
           }else{
               TelaBusca tb = new TelaBusca();
               tb.setVisible(true);
               new Main();
           }
           
        } else {
            TelaConfiguracoes tc = new TelaConfiguracoes();
            tc.setVisible(true);
        }

    }
}
