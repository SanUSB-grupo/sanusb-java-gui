package org.sanusb.model;

import java.util.ArrayList;

/**
 *
 * @author Romulo Lopes - frutuoso.romulo@gmail.com
 * @author Átila Camurça - camurca.home@gmail.com
 */
public class Idiomas {

    private IdiomaGrupo botoesEspanhol;
    private IdiomaGrupo botoesPortugues;
    private IdiomaGrupo botoesIngles;
    private ArrayList<IdiomaGrupo> botoes;

    private IdiomaGrupo menuFileEspanhol;
    private IdiomaGrupo menuFilePortugues;
    private IdiomaGrupo menuFileIngles;
    private ArrayList<IdiomaGrupo> menuFile;

    public Idiomas() {
        init();
    }

    private void init() {
        botoes = new ArrayList<>();

        botoesEspanhol = new IdiomaGrupo();
        String[] strEspanhol = {"Gravar", "Escribir&Resetar", "Resetar", "Abrir"};
        botoesEspanhol.setIdioma(strEspanhol);
        // botoes.add(ControlIdioma.IDIOMA_ESPANHOL, botoesEspanhol);

        botoesIngles = new IdiomaGrupo();
        String[] strIngles = {"Programming", "Programming&Reset", "Reset", "Open"};
        botoesIngles.setIdioma(strIngles);
        // botoes.add(ControlIdioma.IDIOMA_INGLES, botoesIngles);

        botoesPortugues = new IdiomaGrupo();
        String[] strPortugues = {"Gravar", "Gravar&Resetar", "Resetar", "Abrir"};
        botoesPortugues.setIdioma(strPortugues);
        // botoes.add(ControlIdioma.IDIOMA_PORTUGUES, botoesPortugues);

        menuFile = new ArrayList<>();

        menuFileEspanhol = new IdiomaGrupo();
        String[] str1Espanhol = {"Arquibo", "Salir"};
        menuFileEspanhol.setIdioma(str1Espanhol);
        // menuFile.add(ControlIdioma.IDIOMA_ESPANHOL, menuFileEspanhol);

        menuFileIngles = new IdiomaGrupo();
        String[] str1Ingles = {"File", "Exit"};
        menuFileIngles.setIdioma(str1Ingles);
        // menuFile.add(ControlIdioma.IDIOMA_INGLES, menuFileIngles);

        menuFilePortugues = new IdiomaGrupo();
        String[] str1Portugues = {"Arquivo", "Sair"};
        menuFilePortugues.setIdioma(str1Portugues);
        // menuFile.add(ControlIdioma.IDIOMA_PORTUGUES, menuFilePortugues);
    }

    public ArrayList<IdiomaGrupo> getBotoes() {
        return botoes;
    }

    public ArrayList<IdiomaGrupo> getMenuFile() {
        return menuFile;
    }
}
