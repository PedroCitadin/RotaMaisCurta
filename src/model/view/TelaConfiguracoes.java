package model.view;

import java.awt.Color;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import model.Arquivo;
import model.bean.Configuracao;

/**
 *
 * @author Pedro Citadin Coelho
 */
public class TelaConfiguracoes extends JFrame {

    private JLabel labelPasta;
    private JTextField txtPasta;
    private JLabel labelSucesso;
    private JTextField txtSucesso;
    private JLabel labelErro;
    private JTextField txtErro;
    private JButton botao;
    private JCheckBox caixaSelecao;
    private Configuracao config;

    public TelaConfiguracoes() throws HeadlessException {
        setSize(300, 200);
        setResizable(false);
        setTitle("Configuração");
        setLayout(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        criaComponentes();

    }
    public TelaConfiguracoes(Configuracao conf) throws HeadlessException, IOException {
        setSize(300, 200);
        setResizable(false);
        setTitle("Configuração");
        setLayout(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        criaComponentes();
        conf.pegaConfiguracao();
        txtPasta.setText(conf.getPasta());
        txtSucesso.setText(conf.getSucesso());
        txtErro.setText(conf.getErro());
        caixaSelecao.setSelected(conf.isRotaAuto());
    }
    public void criaComponentes() {
        labelPasta = new JLabel("Pasta:");
        labelPasta.setBounds(10, 13, 40, 15);
        getContentPane().add(labelPasta);
        txtPasta = new JTextField();

        txtPasta.setBounds(80, 10, 200, 20);
        getContentPane().add(txtPasta);
        labelSucesso = new JLabel("Sucesso:");
        labelSucesso.setBounds(10, 43, 70, 15);
        getContentPane().add(labelSucesso);
        txtSucesso = new JTextField();

        txtSucesso.setBounds(80, 40, 140, 20);
        getContentPane().add(txtSucesso);
        labelErro = new JLabel("Erro:");
        labelErro.setBounds(10, 73, 40, 15);
        getContentPane().add(labelErro);
        txtErro = new JTextField();

        txtErro.setBounds(80, 70, 140, 20);
        getContentPane().add(txtErro);
        caixaSelecao = new JCheckBox("Rota Automática");
        caixaSelecao.setBounds(80, 100, 130, 20);
        getContentPane().add(caixaSelecao);
        botao = new JButton("SALVAR");
        botao.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                try {
                    botaoActionPerformed(evt);
                } catch (IOException ex) {
                    System.out.println("ERRO!!!");
                    System.out.println(ex);
                }
            }
        });
        botao.setBounds(100, 130, 90, 25);
        getContentPane().add(botao);
    }

    private void botaoActionPerformed(ActionEvent evt) throws IOException {
        ///Evento do botão
        if (txtPasta.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Campo Pasta deve ser preenchido", "", JOptionPane.ERROR_MESSAGE);
        } else if (txtSucesso.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Campo Sucesso deve ser preenchido", "", JOptionPane.ERROR_MESSAGE);
        } else if (txtErro.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Campo Erro deve ser preenchido", "", JOptionPane.ERROR_MESSAGE);
        }
        config = new Configuracao(txtPasta.getText(), txtSucesso.getText(), txtErro.getText(), caixaSelecao.isSelected());
        config.configuraSistema();
    }

}
