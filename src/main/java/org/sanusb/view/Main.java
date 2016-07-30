package org.sanusb.view;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.UIManager;

/**
 *
 * @author Romulo Lopes - frutuoso.romulo@gmail.com
 * @author Átila Camurça - camurca.home@gmail.com
 */
public class Main extends JFrame {

    private final static String SANUSB_DIR = "/usr/share/sanusb/";
    private final String urlTutorial = "http://www.4shared.com/document/y8KTpr3n/TutorialSanUSB.html";
    private final String urlGerenciador = "http://www.4shared.com/file/HGrf9nDz/Gerenciador.html";
    private final int largura = 650;
    private final int altura = 400;
    private final Janela janela = new Janela(SANUSB_DIR);

    private JMenu menuFile;
    JMenuItem menuItemExit;

    public static void main(String[] args) {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("GTK+".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception e) {
            System.out.println("[ERROR] Unsupported Look And Feel.");
            e.printStackTrace();
        }
        
        File sanUSBDir = new File(SANUSB_DIR);
        if (!sanUSBDir.exists() || !sanUSBDir.isDirectory()) {
            JOptionPane.showMessageDialog(null, String.format(
                    "Diretório '%s' não encontrado. Verifique se o programa "
                            + "SanUSB está instalado corretamente.", SANUSB_DIR));
        }

        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                Main main = new Main();
                main.setVisible(true);
            }
        });
    }

    public Main() {
        init();
    }

    private void init() {
        this.add(janela);

        addMenu();

        this.setTitle("SanUSB Microcontroller Programming");
        this.setSize(largura, altura);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Toolkit tk = Toolkit.getDefaultToolkit();
        Dimension d = tk.getScreenSize();
        int screenAltura = d.height;
        int screenLargura = d.width;
        setLocation(screenLargura / 2 - largura / 2, screenAltura / 2 - altura / 2);
    }

    private void addMenu() {
        // barra do menu
        JMenuBar menuBar = new JMenuBar();
        // novo Menu
        menuFile = new JMenu("File");//"File");

        // item do menu
        menuItemExit = new JMenuItem("Exit");
        menuItemExit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        menuFile.add(menuItemExit);

        JMenu menuHelp = new JMenu("Help");

        JMenuItem menuItemTutorial = new JMenuItem("Tutorial");
        menuItemTutorial.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Thread() {
                    @Override
                    public void run() {
                        String[] comando = {"firefox", urlTutorial};
                        janela.startComando(comando);
                    }
                }.start();
            }
        });
        menuHelp.add(menuItemTutorial);

        JMenuItem menuItemGerenciador = new JMenuItem("Gerenciador");
        menuItemGerenciador.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Thread() {
                    @Override
                    public void run() {
                        String texto = "Grave no microcontrolador, somente uma vez, com qualquer gravador para PIC,\n"
                                + " o Gerenciador.hex em anexo que irá gerenciar as gravações (descargas) de \n"
                                + "programas.hex pela interface USB. Mais detalhes no tutorial.";
                        JOptionPane.showMessageDialog(null, texto, "Informação", JOptionPane.INFORMATION_MESSAGE);

                        String[] comando = {"firefox", urlGerenciador};
                        janela.startComando(comando);
                    }
                }.start();
            }
        });
        menuHelp.add(menuItemGerenciador);

        JMenuItem menuItemAbout = new JMenuItem("About");
        menuItemAbout.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "       SanUSB desenvolvido por: Sandro Jucá\n"
                        + "             sandro_juca@yahoo.com.br\n"
                        + "              Web : tinyurl.com/SanUSB\n"
                        + "        -------------------------------------------------\n"
                        + "Interface Grafica desenvolvida por: Rômulo Lopes\n"
                        + "             frutuoso.romulo@gmail.com\n");
            }
        });
        menuHelp.add(menuItemAbout);

        JMenu menuLanguage = new JMenu("Language");

        JMenuItem menuItemPortugues = new JMenuItem("Português");
        menuItemPortugues.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // janela.setIdiomaBotoes(ControlIdioma.IDIOMA_PORTUGUES);
                // menuFileIdiomas = controle.getIdiomaMenuFile(ControlIdioma.IDIOMA_PORTUGUES);
                // menuFile.setText(menuFileIdiomas[0]);
                // menuItemExit.setText(menuFileIdiomas[1]);
            }
        });
        menuLanguage.add(menuItemPortugues);

        JMenuItem menuItemIngles = new JMenuItem("Inglês");
        menuItemIngles.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // janela.setIdiomaBotoes(ControlIdioma.IDIOMA_INGLES);
                // menuFileIdiomas = controle.getIdiomaMenuFile(ControlIdioma.IDIOMA_INGLES);
                // menuFile.setText(menuFileIdiomas[0]);
                // menuItemExit.setText(menuFileIdiomas[1]);
            }
        });
        menuLanguage.add(menuItemIngles);

        JMenuItem menuItemEspanhol = new JMenuItem("Espanhol");
        menuItemEspanhol.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // janela.setIdiomaBotoes(ControlIdioma.IDIOMA_ESPANHOL);
                // menuFileIdiomas = controle.getIdiomaMenuFile(ControlIdioma.IDIOMA_ESPANHOL);
                // menuFile.setText(menuFileIdiomas[0]);
                // menuItemExit.setText(menuFileIdiomas[1]);
            }
        });
        menuLanguage.add(menuItemEspanhol);

        menuBar.add(menuFile);
        menuBar.add(menuLanguage);
        menuBar.add(menuHelp);
        super.setJMenuBar(menuBar);
    }
}
