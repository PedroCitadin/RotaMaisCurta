package model.view;

import model.bean.Grafo;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.security.InvalidAlgorithmParameterException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import model.Arquivo;
import model.bean.Configuracao;

import model.bean.Rota;

/**
 *
 * @author Pedro Citadin Coelho
 */
public class TelaBusca extends JFrame {
    private Arquivo arq;
    private JLabel lblBuscar;
    private JTextField txtBuscar;
    private JButton buscar;
    private JButton buscarArq;
    private JLabel lblCodOrigem;
    private JLabel lblCodDestino;
    private JLabel lblCidadeOrigem;
    private JLabel lblCidadeDestino;
    private JLabel lblOrigem;
    private JLabel lblDestino;
    private JTextField txtCodOrigem;
    private JTextField txtCodDestino;
    private JTextField txtCidadeOrigem;
    private JTextField txtCidadeDestino;
    private JLabel lblkm;
    private JTextField txtKm;
    private JButton adicionar;
    private JTable tabelaRotas;
    private JScrollPane scrollpane;
    private JButton salvar;
    private JButton processar;

    public TelaBusca() {
        this.setSize(800, 570);
        setResizable(false);
        setTitle("Buscar Melhor Caminho");
        setLayout(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        criaComponentes();
    }

    public void criaComponentes() {
        //// "Linha" 1
        lblBuscar = new JLabel("Buscar");
        lblBuscar.setFont(new Font("", 1, 16));
        lblBuscar.setBounds(10, 18, 60, 20);
        getContentPane().add(lblBuscar);
        txtBuscar = new JTextField();
        txtBuscar.setBounds(80, 15, 350, 30);
        this.getContentPane().add(txtBuscar);
        buscar = new JButton("Buscar");
        buscar.setFont(new Font("", 1, 14));
        buscar.setBounds(435, 15, 120, 30);
        buscar.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent evt){
                try {
                    buscarActionPerformed(evt);
                } catch (IOException ex) {
                    Logger.getLogger(TelaBusca.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        this.getContentPane().add(buscar);
        buscarArq = new JButton("...");
        buscarArq.setFont(new Font("", 1, 14));
        buscarArq.setBounds(565, 15, 30, 30);
        buscarArq.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                try {
                    buscarAqrActionPerformed(evt);
                } catch (IOException ex) {
                    Logger.getLogger(TelaBusca.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        this.getContentPane().add(buscarArq);
        //// "Linha" 2
        lblCodOrigem = new JLabel("Código:");
        lblCodOrigem.setFont(new Font("", 1, 16));
        lblCodOrigem.setBounds(10, 68, 80, 20);
        getContentPane().add(lblCodOrigem);
        txtCodOrigem = new JTextField();
        txtCodOrigem.setBounds(80, 65, 90, 30);
        this.getContentPane().add(txtCodOrigem);
        //
        lblCidadeOrigem = new JLabel("Cidade:");
        lblCidadeOrigem.setFont(new Font("", 1, 16));
        lblCidadeOrigem.setBounds(190, 68, 80, 20);
        getContentPane().add(lblCidadeOrigem);
        txtCidadeOrigem = new JTextField();
        txtCidadeOrigem.setBounds(260, 65, 170, 30);
        this.getContentPane().add(txtCidadeOrigem);
        lblOrigem = new JLabel("(ORIGEM)");
        lblOrigem.setFont(new Font("", 1, 16));
        lblOrigem.setBounds(435, 68, 80, 20);
        this.getContentPane().add(lblOrigem);
        /// "Linha" 3
        lblCodDestino = new JLabel("Código:");
        lblCodDestino.setFont(new Font("", 1, 16));
        lblCodDestino.setBounds(10, 108, 80, 20);
        getContentPane().add(lblCodDestino);
        txtCodDestino = new JTextField();
        txtCodDestino.setBounds(80, 105, 90, 30);
        this.getContentPane().add(txtCodDestino);
        //
        lblCidadeDestino = new JLabel("Cidade:");
        lblCidadeDestino.setFont(new Font("", 1, 16));
        lblCidadeDestino.setBounds(190, 108, 80, 20);
        getContentPane().add(lblCidadeDestino);
        txtCidadeDestino = new JTextField();
        txtCidadeDestino.setBounds(260, 105, 170, 30);
        this.getContentPane().add(txtCidadeDestino);
        lblDestino = new JLabel("(DESTINO)");
        lblDestino.setFont(new Font("", 1, 16));
        lblDestino.setBounds(435, 108, 90, 20);
        this.getContentPane().add(lblDestino);
        ////Linha 4 
        lblkm = new JLabel("KM:");
        lblkm.setFont(new Font("", 1, 16));
        lblkm.setBounds(40, 148, 40, 20);
        this.getContentPane().add(lblkm);
        txtKm = new JTextField();
        txtKm.setBounds(80, 145, 60, 30);
        this.getContentPane().add(txtKm);
        //
        adicionar = new JButton("+");
        adicionar.setBounds(730, 145, 50, 30);
        adicionar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                try {
                    adicionarActionPerformed(evt);
                } catch (IOException ex) {
                    System.out.println("ERRO!!!");
                    System.out.println(ex);
                }
            }
        });
        this.getContentPane().add(adicionar);
        /////Tabela
        tabelaRotas = new JTable();

        tabelaRotas.setModel(new DefaultTableModel(
                new Object[][]{},
                new String[]{
                    "Código Origem", "Cidade Origem", "Código Destino", "Cidade Destino", "Distância"

                }));
        scrollpane = new JScrollPane(tabelaRotas);
        scrollpane.setBounds(10, 185, 770, 310);
        this.getContentPane().add(scrollpane);
        ////
        salvar = new JButton("SALVAR");
        salvar.setBounds(550, 500, 110, 30);
        salvar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                try {
                    salvarActionPerformed(evt);
                } catch (IOException ex) {
                    System.out.println("ERRO!!!");
                    System.out.println(ex);
                }
            }

        });
        this.getContentPane().add(salvar);
        processar = new JButton("PROCESSAR");
        processar.setBounds(670, 500, 110, 30);
        processar.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent evt){
                try {
                    processarActionPerformed(evt);
                } catch (InvalidAlgorithmParameterException ex) {
                    Logger.getLogger(TelaBusca.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IOException ex) {
                    Logger.getLogger(TelaBusca.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        this.getContentPane().add(processar);
    }

    private void adicionarActionPerformed(ActionEvent evt) throws IOException {
        String cdOrigem, cdDestino, origem, destino, distancia;
        if (txtCodOrigem.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Campo Código Origem deve ser preenchido", "", JOptionPane.ERROR_MESSAGE);
        } else if (txtCodDestino.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Campo Código Destino deve ser preenchido", "", JOptionPane.ERROR_MESSAGE);
        } else if (txtCidadeOrigem.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Campo Cidade Origem deve ser preenchido", "", JOptionPane.ERROR_MESSAGE);
        } else if (txtCidadeDestino.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Campo Cidade Destino deve ser preenchido", "", JOptionPane.ERROR_MESSAGE);
        } else if (txtKm.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Campo KM deve ser preenchido", "", JOptionPane.ERROR_MESSAGE);
        } else {
            cdOrigem = txtCodOrigem.getText();
            cdDestino = txtCodDestino.getText();
            origem = txtCidadeOrigem.getText();
            destino = txtCidadeDestino.getText();
            distancia = txtKm.getText();
            DefaultTableModel dtm = (DefaultTableModel) tabelaRotas.getModel();
            dtm.addRow(new Object[]{cdOrigem, origem, cdDestino, destino, distancia});
            txtCodOrigem.setText("");
            txtCodDestino.setText("");
            txtCidadeOrigem.setText("");
            txtCidadeDestino.setText("");
            txtKm.setText("");

        }
    }
    private void buscarAqrActionPerformed(ActionEvent evt) throws IOException{
         Configuracao config = new Configuracao();
         config.pegaConfiguracao();
         JFileChooser fc = new JFileChooser();
         fc.setCurrentDirectory(new File(config.getPasta()));
         fc.showOpenDialog(this);
        
         if (fc.getSelectedFile()!=null) {
              txtBuscar.setText(fc.getSelectedFile().getAbsolutePath());
        }
    }
    
    private void buscarActionPerformed(ActionEvent evt) throws IOException{
        if (!txtBuscar.getText().isEmpty()) {
            File f = new File(txtBuscar.getText());
            arq = new Arquivo(f);
            List<String> lr = arq.pegalistaLinhas(arq);
            for (int i = 0; i < lr.size(); i++) {
            String linha[] = lr.get(i).split(";;;;;");
            DefaultTableModel dtm = (DefaultTableModel) tabelaRotas.getModel();
            dtm.addRow(new Object[]{linha[0], linha[1], linha[2], linha[3], linha[4]});
        }
        }else{
            JOptionPane.showMessageDialog(null, "Por favor informe o caminho desejado", "Alerta", JOptionPane.WARNING_MESSAGE);
        }
        
    }
    private void salvarActionPerformed(ActionEvent evt) throws IOException {
        Configuracao config = new Configuracao();
        config.pegaConfiguracao();

        Arquivo r = new Arquivo(JOptionPane.showInputDialog("Digite o nome da Rota: ") + ".txt", config.getPasta());
        List<Rota> rotas = new ArrayList();
        for (int i = 0; i < tabelaRotas.getRowCount(); i++) {
            Rota rr = new Rota();
            rr.setCodOrigem(Integer.parseInt((String) tabelaRotas.getValueAt(i, 0)));
            rr.setCodDestino(Integer.parseInt((String) tabelaRotas.getValueAt(i, 2)));
            rr.setCidadeOrigem(((String) tabelaRotas.getValueAt(i, 1)));
            rr.setCidadeDestino((String) tabelaRotas.getValueAt(i, 3));
            rr.setKm(Integer.parseInt((String) tabelaRotas.getValueAt(i, 4)));
            rotas.add(rr);
        }
        r.criaArquivo(r);
        for (int i = 0; i < rotas.size(); i++) {
            if (i==0) {
                r.escreveNoArquivo(r, rotas.get(i).retornaEscrita());
            }else{
            r.escreveNaProxiLinhaDoArquivo(r, rotas.get(i).retornaEscrita());
            }
        }
    }
    
    private void processarActionPerformed(ActionEvent evt) throws InvalidAlgorithmParameterException, IOException{
        Configuracao config = new Configuracao();
        config.pegaConfiguracao();
        Map<Integer, String> vertices = new HashMap<Integer, String>();
        for (int i = 0; i < tabelaRotas.getRowCount(); i++) {
            if (!vertices.containsKey(Integer.parseInt((String) tabelaRotas.getValueAt(i, 0)))) {
                vertices.put(Integer.parseInt((String) tabelaRotas.getValueAt(i, 0)),(String) tabelaRotas.getValueAt(i, 1));
            }
            if(!vertices.containsKey(Integer.parseInt((String) tabelaRotas.getValueAt(i, 2)))){
                 vertices.put(Integer.parseInt((String) tabelaRotas.getValueAt(i, 2)),(String) tabelaRotas.getValueAt(i, 3));
            }
          
        }
        
        Grafo gr = new Grafo(vertices.size());
        for (int i = 0; i < tabelaRotas.getRowCount(); i++) {
            gr.criaAresta(Integer.parseInt((String) tabelaRotas.getValueAt(i, 0)), Integer.parseInt((String)tabelaRotas.getValueAt(i, 2)),Integer.parseInt((String) tabelaRotas.getValueAt(i, 4)));
        }
        int inicio = Integer.parseInt(JOptionPane.showInputDialog("Informe o código da cidade de partida: "));
        int fim = Integer.parseInt(JOptionPane.showInputDialog("Informe o código da cidade de destino: "));
        List<Integer> caminho = gr.caminhoMinimo(inicio, fim);
        String trilha = "";
        if (caminho.isEmpty()) {
            arq.moveArquivo(arq, config.getErro());
        }else{
        for (int i = 0; i < caminho.size(); i++) {
            if(i!=caminho.size()-1){
                trilha += vertices.get(caminho.get(i)) +" --> ";
            }else{
                 trilha += vertices.get(caminho.get(i));
            }
               
            
            
        }
        trilha += " - Distância total: "+gr.getCustoTotal(caminho)+" Km";
           UIManager.put("OptionPane.minimumSize", new Dimension(600, 50));

          JOptionPane.showMessageDialog(null, trilha, "Caminho mais curto", JOptionPane.INFORMATION_MESSAGE);
          arq.moveArquivo(arq, config.getSucesso());
    }
    }
}
