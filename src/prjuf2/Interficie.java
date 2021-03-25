package prjuf2;

public class Interficie {

    public static final String black = "\u001B[30m";
    public static final String red = "\u001B[31m";
    public static final String green = "\u001B[32m";
    public static final String yellow = "\u001B[33m";
    public static final String blue = "\u001B[34m";
    public static final String purple = "\u001B[35m";
    public static final String cyan = "\u001B[36m";
    public static final String white = "\u001B[37m";
    public static final String reset = "\u001B[0m";

    public static void redMessage (String message){
        System.out.println(red + message + reset);
    }
    public static void purpleMessage (String message){
        System.out.println(purple + message + reset);
    }
    public static String purpleMessageReturn (String message){
        return (purple + message + reset);
    }
    public static void yellowMessage (String message){
        System.out.println(yellow + message + reset);
    }
    public static void cyanMessage (String message){
        System.out.println(cyan + message + reset);
    }
    public static void blueMessage (String message){
        System.out.println(blue + message + reset);
    }
    public static String blueMessageReturn (String message){
        return (blue + message + reset);
    }
    public static void mostrarDades(Taulell t,BoardManager g,String[] dades) {
        dades[0] = "Current number of sick people: " + BoardManager.sumArray(t);
        dades[1] = "Total number of healed people: " + g.healedPersons;
        dades[2] = "Percentage that did not complete the confinement: " + BoardManager.sumMovedPeople(g.movedPeople, g.sickTotal) + "%";
        dades[3] = "Total number of runaway people: " + g.runawayPeople;
            for (int i = 0; i < 4; i++){
                yellowMessage(dades[i]);
            }
    }

    /**
     * Mostra per pantalla les diferents opcions que formen el menú
     * que es rep com a paràmetre. La primera posició de l'array menú és el
     * títol del mateix mentre que la resta són les opcions. Aquesta variable
     * ja ve inicialitzada des del codi que en fa la crida.
     *
     * @param menu Conté les diferents opcions que es mostraran per pantalla a
     *             l'usuari.
     */
    public static void mostrarMenu(String[] menu) {
        blueMessage(menu[0]);
        purpleMessage("* * * * * * * * * * * * * * *");
        for (int i = 1; i < menu.length; i++) {
            purpleMessage("* " + (blueMessageReturn(menu[i])) + purpleMessageReturn("\t*"));
        }
        purpleMessage("* * * * * * * * * * * * * * *");
    }

    /**
     * Mostra la informació per pantalla de forma simple
     *
     * @param missatge Missatge a mostrar per pantalla
     */
    public static void mostrarMissatge(String missatge) {

        System.out.println(missatge);
    }


    /**
     * Mostra la informació per pantalla en format capçalera
     *
     * @param missatge Missatge a mostrar per pantalla en format capçalera
     */
    public static void mostrarCapçalera(String missatge) {

        yellowMessage("-------------------------------------------------");
        cyanMessage(missatge);
        yellowMessage("-------------------------------------------------");
    }

}

