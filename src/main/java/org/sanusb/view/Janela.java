package org.sanusb.view;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.LayoutStyle;
import javax.swing.filechooser.FileNameExtensionFilter;
import net.jhorstmann.i18n.I18N;
import org.apache.commons.lang3.StringUtils;

/**
 *
 * @author Romulo Lopes - frutuoso.romulo@gmail.com
 * @author Átila Camurça - camurca.home@gmail.com
 */
public class Janela extends JPanel {

    private JTextArea campoTexto;
    private JScrollPane jScrollPane1;
    private JLabel labelArquivo;
    private JTextField textDiretorio;
    private JButton botaoAbrir;
    private JButton botaoGravar;
    private JButton botaoResetar;
    private JButton botaoGravarResetar;
    private final String diretorioSanUsb;
    private final String nomeAplicativo = "sanusb";
    private final String dirOpenFile = System.getProperty("user.home");

    public Janela(String diretorioSanUsb) {
        this.diretorioSanUsb = diretorioSanUsb;
        initComponents();
    }

    private void initComponents() {
        botaoGravar = new JButton();
        botaoGravarResetar = new JButton();
        botaoResetar = new JButton();
        botaoAbrir = new JButton();
        labelArquivo = new JLabel();
        textDiretorio = new JTextField();
        jScrollPane1 = new JScrollPane();
        campoTexto = new JTextArea();

        setPreferredSize(new Dimension(380, 180));

        botaoGravar.setText(I18N.tr("Write"));
        botaoGravar.setIcon(new ImageIcon(Icone.getIcone("gravar.png")));
        botaoGravar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                botaoGravarActionPerformed(evt);
            }
        });

        botaoGravarResetar.setText(I18N.tr("Write and Reset"));
        botaoGravarResetar.setIcon(new ImageIcon(Icone.getIcone("gravar.png")));
        botaoGravarResetar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                botaoGravarResetarActionPerformed(evt);
            }
        });

        botaoResetar.setText(I18N.tr("Reset"));
        botaoResetar.setIcon(new ImageIcon(Icone.getIcone("resetar.png")));
        botaoResetar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                botaoResetarActionPerformed(evt);
            }
        });

        botaoAbrir.setText(I18N.tr("Open"));
        botaoAbrir.setIcon(new ImageIcon(Icone.getIcone("abrir.png")));
        botaoAbrir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                botaoAbrirActionPerformed(evt);
            }
        });

        labelArquivo.setText(I18N.tr("File") + ":");

        textDiretorio.setEditable(false);

        campoTexto.setColumns(20);
        campoTexto.setRows(5);
        campoTexto.setEditable(false);
        jScrollPane1.setViewportView(campoTexto);

        GroupLayout layout = new GroupLayout(this);
        this.setLayout(layout);

        layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                .addGroup(layout.createSequentialGroup()
                                        .addContainerGap()
                                        .addComponent(jScrollPane1, GroupLayout.DEFAULT_SIZE, 412, Short.MAX_VALUE))
                                .addGroup(layout.createSequentialGroup()
                                        .addGap(19, 19, 19)
                                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                                        .addComponent(botaoAbrir)
                                                        .addGap(20, 20, 20)
                                                        .addComponent(botaoGravar)
                                                        .addGap(10, 11, 12)
                                                        .addComponent(botaoResetar)
                                                        .addGap(10, 11, 12)
                                                        //.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 80, Short.MAX_VALUE)
                                                        .addComponent(botaoGravarResetar)
                                                        .addGap(21, 21, 21))
                                                .addGroup(layout.createSequentialGroup()
                                                        .addComponent(labelArquivo)
                                                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                        .addComponent(textDiretorio)))))
                        .addContainerGap())
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(textDiretorio)
                                .addComponent(labelArquivo))
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(botaoResetar)
                                .addComponent(botaoGravar)//, GroupLayout.PREFERRED_SIZE, 22, GroupLayout.PREFERRED_SIZE)
                                .addComponent(botaoGravarResetar)
                                .addComponent(botaoAbrir))
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1, GroupLayout.DEFAULT_SIZE, 181, Short.MAX_VALUE)
                        .addContainerGap())
        );
    }

    public void startComando(String[] comando) {
        String str;
        try {
            System.out.println(Arrays.toString(comando));
            Process process = Runtime.getRuntime().exec(comando);

            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            try {
                while ((str = reader.readLine()) != null) {
                    campoTexto.setText(campoTexto.getText() + str + "\n");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }

    private void botaoGravarActionPerformed(ActionEvent evt) {
        if (StringUtils.isNotBlank(textDiretorio.getText())) {
            String[] comando = {diretorioSanUsb + nomeAplicativo, "-w", getDiretorio()};
            startComando(comando);
        } else {
            JOptionPane.showMessageDialog(null, I18N.tr("No file selected"));
        }
    }

    private void botaoGravarResetarActionPerformed(ActionEvent evt) {
        if (StringUtils.isNotBlank(textDiretorio.getText())) {
            String[] comando = {diretorioSanUsb + nomeAplicativo, "-w", getDiretorio(), "-r"};
            startComando(comando);
        } else {
            JOptionPane.showMessageDialog(null, I18N.tr("No file selected"));
        }
    }

    private String getDiretorio() {
        String value = textDiretorio.getText();
        String result = "";
        for (int i = 0; i < value.length(); i++) {
            if (value.charAt(i) == ' ') {
                result += "\\";
            }
            result += value.charAt(i);
        }

        return result;
    }

    private void botaoResetarActionPerformed(ActionEvent evt) {
        String[] comando = {diretorioSanUsb + nomeAplicativo, "-r"};
        startComando(comando);
    }

    private void botaoAbrirActionPerformed(ActionEvent evt) {
        textDiretorio.setText(openFile("hex"));
    }

    public String openFile(String tipo) {
        JFileChooser fileChooser = new JFileChooser(dirOpenFile);

        FileNameExtensionFilter filter = new FileNameExtensionFilter("." + tipo, tipo);
        fileChooser.setFileFilter(filter);

        int option = fileChooser.showOpenDialog(null);
        if (option == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            return file.getAbsolutePath();
        }
        return null;
    }
}
