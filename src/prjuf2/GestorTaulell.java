package prjuf2;

import java.util.Scanner;

public class GestorTaulell {
    Scanner in = new Scanner(System.in);

    public void carregar(Taulell t) {
        System.out.println("Introdueix el numero de files");
        int files = in.nextInt();
        System.out.println("Introdueix el numero de columnes");
        int columnes = in.nextInt();
        t.setFiles(files);
        t.setColumnes(columnes);
        t.createTaulellBuit();
    }

    public void introduirMalalts(Taulell t) {
        int n = 1;
        while (n == 1) {
            System.out.println("Introdueix en quina fila el/s vols situar");
            int x = in.nextInt() - 1;
            while (x <= 0 || x >= t.getFiles()) {
                System.out.println("Fila incorrecta, torna a probar");
                x = in.nextInt() - 1;
            }
            System.out.println("Introdueix en quina columna el/s vols situar");
            int y = in.nextInt() - 1;
            while (y <= 0 || y >= t.getColumnes()) {
                System.out.println("Fila incorrecta, torna a probar");
                y = in.nextInt() - 1;
            }
            System.out.println("Quantitat de malalts que vols introduir");
            int numMalalts = in.nextInt();
            while (numMalalts <= 0) {
                System.out.println("Número no vàlid, torna a probar");
                numMalalts = in.nextInt();
            }
            t.setCasella(x, y, numMalalts);
            System.out.println("Vols introduir un altre/s malalt/s?\n1.Introduir més malalts\n0.Sortir");
            n = in.nextInt();
        }
    }

    public void transmetreVirus(Taulell t) {
        System.out.println("Introdueix el rt(ratio de transmisió)");
        float rt = in.nextFloat();
        for (int i = 0; i < t.getFiles(); i++) {
            for (int j = 0; j < t.getColumnes(); j++) {
                t.getT()[i][j] += t.getT()[i][j] * rt;
            }
        }
    }

    public void curarMalalts(Taulell t) {
        float percentatge;
        int n;
        int r;
        int f;
        System.out.println("Vols curar malalts globalment o concretament?\n1.Globalment\n2.Concretament");
        n = in.nextInt();
        if (n == 1) {
            System.out.println("Has seleccionat l'opció de globalment\nVols curar en '%' concret o random?" +
                    "\n1.Concret\n2.Random");
            r = in.nextInt();
            if (r == 1) {
                System.out.println("Has seleccionat curar globalment en % concret\nIntrodueix el % desitjat");
                percentatge = in.nextFloat();
                for (int i = 0; i < t.getFiles(); i++) {
                    for (int j = 0; j < t.getColumnes(); j++) {
                        t.getT()[i][j] = t.getT()[i][j] * (percentatge / 100);
                    }
                }
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
            System.out.println("Has seleccionat l'opció de concretament\nIntrodueix la fila desitjada");
            int x = in.nextInt() - 1;
            System.out.println("Introdueix la columna desitjada");
            int y = in.nextInt() - 1;
            System.out.println("Vols curar en '%' o per un valor concret?\n1.%\n2.Valor concret");
            r = in.nextInt();
            if (r == 1) {
                System.out.println("Has seleccionat curar en %\nVols introduir un % concret o random?" +
                        "\n1.Concret\n2.Random");
                f = in.nextInt();
                if (f == 1) {
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
        int count = 0;
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
        System.out.println("En quina direcció els vols desplaçar?\nQ.Dalt esquerra\nW.Endavant\nE.Dalt dreta\nA.Esquerra\nD.Dreta\nZ.Baix esquerra\nX.Baix)\nC.Baix dreta");
        String direccio = in.next().toLowerCase();
        switch (direccio) {
            case "q" -> {
                t.getT()[x][y] -= quantitatMalalts;
                t.getT()[x - 1][y - 1] += quantitatMalalts;
            }
            case "w" -> {
                t.getT()[x][y] -= quantitatMalalts;
                t.getT()[x - 1][y] += quantitatMalalts;
            }
            case "e" -> {
                t.getT()[x][y] -= quantitatMalalts;
                t.getT()[x - 1][y + 1] += quantitatMalalts;
            }
            case "a" -> {
                t.getT()[x][y] -= quantitatMalalts;
                t.getT()[x][y - 1] += quantitatMalalts;
            }
            case "d" -> {
                t.getT()[x][y] -= quantitatMalalts;
                t.getT()[x][y + 1] += quantitatMalalts;
            }
            case "z" -> {
                t.getT()[x][y] -= quantitatMalalts;
                t.getT()[x + 1][y - 1] += quantitatMalalts;
            }
            case "x" -> {
                t.getT()[x][y] -= quantitatMalalts;
                t.getT()[x + 1][y] += quantitatMalalts;
            }
            case "c" -> {
                t.getT()[x][y] -= quantitatMalalts;
                t.getT()[x + 1][y + 1] += quantitatMalalts;
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
    public static int sumDesplaçats(Taulell t,int quantitatMalalts){
        int count=0;
        count += quantitatMalalts;
        int perPerMogudes = (count * 100)/sumArray(t);
        return (perPerMogudes);
    }
}