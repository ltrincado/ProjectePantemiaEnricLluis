package prjuf2;

import java.util.Scanner;

public class Game {
    public static void main(String[] args) {
        Game game = new Game();
        game.inici();
    }
    public void inici(){
        Scanner in = new Scanner(System.in);
        String[] menu = {
                "MENÚ",
                "1. Crear taulell random", "2. Crear taulell manual", "3. Introduir malalts", "4. Transmetre virus",
                "5. Curar malalts", "6. Desplaçar malats", "7. Mostrar informació", "0. Sortir"
        };
        int opcio;
        String[] dades = new String[4];
        int perTotals;
        int perCurades = 0;
        GestorTaulell g = new GestorTaulell();
        Taulell t = new Taulell();
        do {
            Interficie.mostrarMenu(menu);
            opcio = in.nextInt();
            switch (opcio) {
                case 1:
                    Interficie.mostrarCapçalera("Has seleccionat l'opció de crear un taulell random");
                    g.carregarR(t);
                    g.totalMalats += g.sumArray(t);
                    System.out.println(t.toString());
                    break;
                case 2:
                    Interficie.mostrarCapçalera("Has seleccionat l'opció de crear el teu propi taulell");
                    g.carregar(t);
                    System.out.println(t.toString());
                    break;
                case 3:
                    Interficie.mostrarCapçalera("Has seleccionat l'opció d'introduir malalts");
                    g.introduirMalalts(t);
                    System.out.println(t.toString());
                    break;
                case 4:
                    Interficie.mostrarCapçalera("Has seleccionat l'opció de transmetre virus");
                    g.transmetreVirus(t);
                    System.out.println(t.toString());
                    break;
                case 5:
                    perTotals = GestorTaulell.sumArray(t);
                    Interficie.mostrarCapçalera("Has seleccionat l'opcio de curar malalts");
                    g.curarMalalts(t);
                    perCurades += perTotals - GestorTaulell.sumArray(t);
                    System.out.println(t.toString());
                    break;
                case 6:
                    Interficie.mostrarCapçalera("Has seleccionat l'opcio de desplaçar malalts");
                    g.desplacarMalalts(t);
                    System.out.println(t.toString());
                    break;
                case 7:
                    dades[0] = "Número total de malalts actualment: " + GestorTaulell.sumArray(t);
                    dades[1] = "Número total de persones curades: " + perCurades;
                    dades[2] = "% que no ha complert el confinament: " + GestorTaulell.sumDesplaçats(t, g.perDesplacades, g.totalMalats) + "%";
                    dades[3] = "Número total de persones fugides: " + g.perFugides;
                    Interficie.mostrarCapçalera("Has seleccionat l'opció de mostrar informació");
                    Interficie.mostrarDades(dades);
                    System.out.println(t.toString());
                    break;
                case 0:
                    System.out.println("GAME OVER");
                    break;
            }
        } while (opcio != 0);

    }
}
