package org.sanusb.view;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.Locale;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import net.jhorstmann.i18n.I18N;
import org.apache.commons.lang3.StringUtils;

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

    protected static Main main;

    public static void main(String[] args) {
        try {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("GTK+".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception e) {
            System.out.println("[ERROR] Unsupported Look And Feel.");
        }

        File sanUSBDir = new File(SANUSB_DIR);
        if (!sanUSBDir.exists() || !sanUSBDir.isDirectory()) {
            String message = I18N.tr("Directory '%s' not found. Please, check if the program "
                    + "SanUSB is installed correctly.", SANUSB_DIR);
            JOptionPane.showMessageDialog(null, message);
        }

        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                createInstance();
            }
        });
    }

    public static void createInstance() {
        main = new Main();
        main.setVisible(true);
    }

    public Main() {
        init();
    }

    private void init() {
        this.add(janela);
        addMenu();
        this.setTitle(I18N.tr("SanUSB Microcontroller Programming"));
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
        menuFile = new JMenu(I18N.tr("File"));

        // item do menu
        menuItemExit = new JMenuItem(I18N.tr("Exit"));
        menuItemExit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        menuFile.add(menuItemExit);

        JMenu menuHelp = new JMenu(I18N.tr("Help"));

        JMenuItem menuItemTutorial = new JMenuItem(I18N.tr("Tutorial"));
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

        JMenuItem menuItemGerenciador = new JMenuItem(I18N.tr("Manager"));
        menuItemGerenciador.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Thread() {
                    @Override
                    public void run() {
                        String[] comando = { "firefox", urlGerenciador };
                        janela.startComando(comando);
                    }
                }.start();
            }
        });
        menuHelp.add(menuItemGerenciador);

        JMenuItem menuItemAbout = new JMenuItem(I18N.tr("About"));
        menuItemAbout.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                final StringBuilder message = new StringBuilder();
                message.append("SanUSB desenvolvido por: Sandro Jucá\n");
                message.append("sandro_juca@yahoo.com.br\n");
                message.append("Web : tinyurl.com/SanUSB\n");
                message.append("-------------------------------------------------\n");
                message.append("sanusb-java-gui desenvolvido por:\n");
                message.append("Rômulo Lopes - frutuoso.romulo@gmail.com\n");
                message.append("Átila Camurça - camurca.home@gmail.com\n");
                JOptionPane.showMessageDialog(null, message);
            }
        });
        menuHelp.add(menuItemAbout);

        JMenu menuLanguage = new JMenu(I18N.tr("Language"));

        JMenuItem menuItemPortugues = new JMenuItem("Português (Brasil)");
        menuItemPortugues.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Locale.setDefault(new Locale("pt", "BR"));
                createInstance();
            }
        });
        menuLanguage.add(menuItemPortugues);

        JMenuItem menuItemIngles = new JMenuItem("English");
        menuItemIngles.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Locale.setDefault(new Locale("en", "US"));
                createInstance();
            }
        });
        menuLanguage.add(menuItemIngles);

        JMenuItem menuItemEspanhol = new JMenuItem("Espanhol");
        menuItemEspanhol.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Locale.setDefault(new Locale("es", "ES"));
            }
        });
        menuLanguage.add(menuItemEspanhol);

        menuBar.add(menuFile);
        menuBar.add(menuLanguage);
        menuBar.add(menuHelp);
        super.setJMenuBar(menuBar);
    }
}
