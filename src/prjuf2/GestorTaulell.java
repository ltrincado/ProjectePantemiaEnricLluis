package prjuf2;

import java.util.Scanner;

public class GestorTaulell {
    Scanner in = new Scanner(System.in);
    int numMalalts = 0;
    int perDesplacades = 0;
    int perFugides = 0;
    int totalMalats = 0;

    public void carregar(Taulell t) {
        int files = Utils.validarEnter("Insert the number of rows","You have to insert a numeric value");
        int columnes = Utils.validarEnter("Insert the number of columns","You have to insert a numeric value");
        t.setFiles(files);
        t.setColumnes(columnes);
        t.createTaulellBuit();
    }
    public void carregarR(Taulell t) {
        int files = Utils.validarEnter("Insert the number of rows","You have to insert a numeric value");
        int columnes = Utils.validarEnter("Insert the number of columns","You have to insert a numeric value");
        t.setFiles(files);
        t.setColumnes(columnes);
        t.createTaulellRandom();
    }

    public void introduirMalalts(Taulell t) {
        int n = 1;
        while (n == 1) {
            int x = Utils.validarRangFiles(t,"Incorrect row, try again");
            int y = Utils.validarRangColumnes(t,"Incorrect column, try again");
            numMalalts += Utils.validarRangMalalts("You have to insert a number higher than 0");
            totalMalats += numMalalts;
            t.setCasella(x, y, numMalalts);
            Interficie.mostrarMissatge("Do you want to insert more sick persons?\n1. Insert more sick persons\n0. Back to menu");
            n = Utils.validarSortida("");
        }
    }

    public void transmetreVirus(Taulell t) {
        float rt = Utils.validarRt("You have to insert a number higher than 0");
        for (int i = 0; i < t.getFiles(); i++) {
            for (int j = 0; j < t.getColumnes(); j++) {
                t.getT()[i][j] += t.getT()[i][j] * rt;
            }
        }
        totalMalats=sumArray(t);
    }

    public void curarMalalts(Taulell t) {
        float percentatge;
        int n;
        int r;
        int f;
        n = Utils.validarSortida("Do you want to heal globally or specifically?\n0. Globally\n1. Specifically");
        if (n == 0) {
            r = Utils.validarSortida("You have selected the option of globally\nDo you want to heal in specifically or random '%'" +
                    "\n0. Specifically\n1. Random");
            if (r == 0) {
                percentatge = Utils.validarPercentatge("You have to insert a number higher than 0");
                for (int i = 0; i < t.getFiles(); i++) {
                    for (int j = 0; j < t.getColumnes(); j++) {
                        t.getT()[i][j] = t.getT()[i][j] * (percentatge / 100);
                    }
                }
            } else {
                Interficie.mostrarMissatge("You have selected globally heal in random '%'");
                percentatge = (int) (Math.random() * 100) + 1;
                for (int i = 0; i < t.getFiles(); i++) {
                    for (int j = 0; j < t.getColumnes(); j++) {
                        t.getT()[i][j] = t.getT()[i][j] * (percentatge / 100);
                    }
                }
            }
        } else {
            int x = Utils.validarRangFiles(t,"Incorrect row, try again");
            int y = Utils.validarRangColumnes(t,"Incorrect column, try again");
            r = Utils.validarSortida("Do you want to heal in '%' or specifically value\n0. %\n1. Specifically");
            if (r == 0) {
                System.out.println("Has seleccionat curar en %\nVols introduir un % concret o random?" +
                        "\n0. Concret\n1. Random");
                f = in.nextInt();
                if (f == 0) {
                    System.out.println("Has seleccionat curar en una posició concreta en % concret\nIntrodueix el % desitjat");
                    percentatge = in.nextFloat();
                    t.getT()[x][y] = (t.getT()[x][y]) * (percentatge / 100);
                } else {
                    System.out.println("Has seleccionat curar globalment en % random");
                    percentatge = (int) (Math.random() * 100) + 1;
                    for (int i = 0; i < t.getFiles(); i++) {
                        for (int j = 0; j < t.getColumnes(); j++) {
                            t.getT()[i][j] = t.getT()[i][j] * (percentatge / 100);
                        }
                    }
                }
            } else {
                System.out.println("Has seleccionat curar per un valor concret");
                int valorConcret = in.nextInt();
                while (t.getT()[x][y] < valorConcret) {
                    System.out.println("El número de persones que vols curar es superior a les malaltes, torna a probar");
                    valorConcret = in.nextInt();
                }
                t.getT()[x][y] = t.getT()[x][y] - valorConcret;
            }
        }
    }

    public void desplacarMalalts(Taulell t) {
        System.out.println("Selecciona una fila");
        int x = in.nextInt() - 1;
        System.out.println("Selecciona una columna");
        int y = in.nextInt() - 1;
        System.out.println("Introdueix la quantitat de malalts que vols desplaçar");
        int quantitatMalalts = in.nextInt();
        while (quantitatMalalts > t.getT()[x][y] || quantitatMalalts <= 0) {
            System.out.println("El número de persones que vols desplaçar és superior a les persones de la casella, torna a probar");
            quantitatMalalts = in.nextInt();
        }
        perDesplacades += quantitatMalalts;
        System.out.println("En quina direcció els vols desplaçar?\nQ.Dalt esquerra\nW.Endavant\nE.Dalt dreta\nA.Esquerra\nD.Dreta\nZ.Baix esquerra\nX.Baix)\nC.Baix dreta");
        String direccio = in.next().toLowerCase();
        switch (direccio) {
            case "q" -> {
                t.getT()[x][y] -= quantitatMalalts;
                if (x - 1>= t.getFiles() || y - 1>= t.getColumnes()){
                    perFugides += quantitatMalalts;
                } else {
                    t.getT()[x - 1][y - 1] += quantitatMalalts;
                }
            }
            case "w" -> {
                t.getT()[x][y] -= quantitatMalalts;
                if (x - 1 >= t.getFiles() || y >= t.getColumnes()){
                    perFugides += quantitatMalalts;
                }else{
                    t.getT()[x - 1][y] += quantitatMalalts;
                }
            }
            case "e" -> {
                t.getT()[x][y] -= quantitatMalalts;
                if (x - 1>= t.getFiles() || y + 1>= t.getColumnes()){
                    perFugides += quantitatMalalts;
                }else{
                    t.getT()[x - 1][y + 1] += quantitatMalalts;
                }
            }
            case "a" -> {
                t.getT()[x][y] -= quantitatMalalts;
                if (x >= t.getFiles() || y - 1>= t.getColumnes()){
                    perFugides += quantitatMalalts;
                } else{
                    t.getT()[x][y - 1] += quantitatMalalts;
                }
            }
            case "d" -> {
                t.getT()[x][y] -= quantitatMalalts;
                if (x >= t.getFiles() || y + 1>= t.getColumnes()){
                    perFugides += quantitatMalalts;
                }else{
                    t.getT()[x][y + 1] += quantitatMalalts;
                }
            }
            case "z" -> {
                t.getT()[x][y] -= quantitatMalalts;
                if (x + 1>= t.getFiles() || y -1>= t.getColumnes()){
                    perFugides += quantitatMalalts;
                } else{
                    t.getT()[x + 1][y - 1] += quantitatMalalts;
                }
            }
            case "x" -> {
                t.getT()[x][y] -= quantitatMalalts;
                if (x + 1 >= t.getFiles() || y >= t.getColumnes()){
                    perFugides += quantitatMalalts;
                }else {
                    t.getT()[x + 1][y] += quantitatMalalts;
                }
            }
            case "c" -> {
                t.getT()[x][y] -= quantitatMalalts;
                if (x + 1>= t.getFiles() || y + 1>= t.getColumnes()){
                    perFugides += quantitatMalalts;
                } else {
                    t.getT()[x + 1][y + 1] += quantitatMalalts;
                }
            }
        }
    }
    public static int sumArray(Taulell t){
        int sum=0;
        for (int i = 0; i < t.getFiles(); i++){
            for (int j = 0; j < t.getColumnes(); j ++){
                sum += t.getT()[i][j];
            }
        }
        return (sum);
    }
    public static int sumDesplaçats(Taulell t, int perDesplaçades,int totalMalats){
        return ((perDesplaçades * 100)/totalMalats);
    }
}