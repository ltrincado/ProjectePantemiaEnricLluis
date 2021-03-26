package prjuf2;

public class BoardManager {
    int sickPeople = 0;
    int movedPeople = 0;
    int runawayPeople = 0;
    int sickTotal = 0;
    int totalPersons = 0;
    int healedPersons = 0;

    /**
     * Genera un taulell buit amb valors 0, a partir de les files i columnes que decideix l'usuari.
     *
     * @param t Ve de la classe Board i l'utilitzem per a guardar el valor de les files, les columnes i l'espai reservat del taulell buit.
     */
    public void loadEmpty(Board t) {
        int rows = Utils.validateInt(t, "m", "Insert the number of rows", "You have to insert a numeric value", "You must enter a number higher than 0", 0, 0);
        int columns = Utils.validateInt(t, "m", "Insert the number of columns", "You have to insert a numeric value", "You must enter a number higher than 0", 0, 0);
        t.setRows(rows);
        t.setColumns(columns);
        t.createEmptyBoard();
        sickTotal = sumArray(t);
    }

    /**
     * Genera un taulell amb valors aleatoris a partir de les files i columnes que decideix l'usuari.
     *
     * @param t Ve de la classe Board i l'utilitzem per a guardar el valor de les files, les columnes i l'espai reservat del taulell aleatori.
     */
    public void loadRandom(Board t) {
        int rows = Utils.validateInt(t, "m", "Insert the number of rows", "You have to insert a numeric value", "You must enter a number higher than 0", 0, 0);
        int columns = Utils.validateInt(t, "m", "Insert the number of columns", "You have to insert a numeric value", "You must enter a number higher than 0", 0, 0);
        t.setRows(rows);
        t.setColumns(columns);
        t.createRandomBoard();
        sickTotal = sumArray(t);
    }

    /**
     * Introducció de malalts a la posició concreta que l'usuari entri per paràmetres.
     *
     * @param t Ve de la classe Board i l'utilitzem per a enmagatzemar el valor concret d'una posició concreta donada per l'usuari.
     */
    public void addSickPeople(Board t) {
        int x = 0;
        int y = 0;
        int n = 1;
        while (n == 1) {
            int option = 1;
            while (option == 1) {
                x = Utils.validateInt(t, "f", "Insert the row you want", "You have to insert a numeric value", "Incorrect row, try again", 0, 0) - 1;
                y = Utils.validateInt(t, "c", "Insert the column you want", "You have to insert a numeric value", "Incorrect column, try again", 0, 0) - 1;
                if (t.getT()[x][y] >= 0) {
                    option = 0;
                } else {
                    Interficie.redMessage("This position is blocked, try again");
                }
            }
            Interficie.yellowMessage("If you want to block the position enter '-1'");
            sickPeople = (int) t.getT()[x][y] + Utils.validateInt(t, "m", "Number of sick person you want to insert", "You have to insert a numeric value", "You must enter a number higher than 0", 0, 0);
            sickTotal += sickPeople;
            t.setPosition(x, y, sickPeople);
            Interficie.purpleMessage("Do you want to insert more sick persons?");
            n = Utils.validateInt(t, "v", "1. Insert more sick persons\n0. Back to menu", "You must enter a numeric value", "You must enter '0' or '1'", 0, 0);
        }
    }

    /**
     * Multiplica les persones infectades per el número de ratio de transmissió que l'usuari decideix.
     *
     * @param t Ve de la classe Board i l'utilitzem per a actualitzar les caselles del taulell en funció dels valors que entri l'usuari.
     */
    public void transmitVirus(Board t) {
        float rt = Utils.validateFloat("Insert the RT (Transmission ratio)", "You have to enter a numeric value",
                "You have to insert a number higher than 0");
        for (int i = 0; i < t.getRows(); i++) {
            for (int j = 0; j < t.getColumns(); j++) {
                if (t.getT()[i][j] > 0) {
                    t.getT()[i][j] += t.getT()[i][j] * rt;
                }
            }
        }
        sickTotal = sumArray(t) + runawayPeople;
    }

    /**
     * Cura els malalts del taulell, en funció del que trii l'usuari,
     * ja sigui de manera glabal, específica, en un valor concret o en percentatge.
     *
     * @param t Ve de la classe Board i l'utilitzem per a actualitzar les caselles del taulell en funció dels valors que entri l'usuari.
     */
    public void healSickPeople(Board t) {
        float percentage = 0;
        int n;
        int r;
        int f;
        int x = 0;
        int y = 0;
        totalPersons = BoardManager.sumArray(t);
        if (totalPersons != 0) {
            Interficie.purpleMessage("Do you want to heal globally or specifically?");
            n = Utils.validateInt(t, "v", "0. Globally\n1. Specifically", "You must enter a numeric value", "You must enter '0' or '1'", 0, 0);
            if (n == 0) {
                Interficie.purpleMessage("You have selected heal globally\nDo you want to heal in specifically or random '%'");
                r = Utils.validateInt(t, "v", "0. Specifically\n1. Random", "You must enter a numeric value", "You must enter '0' or '1'", 0, 0);
                if (r == 0) {
                    percentage = Utils.validateFloat("Insert the '%' that you want",
                            "You must enter a numeric value", "You must enter a number higher than 0");
                    for (int i = 0; i < t.getRows(); i++) {
                        for (int j = 0; j < t.getColumns(); j++) {
                            if (t.getT()[i][j] > 0) {
                                t.getT()[i][j] = t.getT()[i][j] - (t.getT()[i][j] * (percentage / 100));
                            }
                        }
                    }
                } else if (r == 1) {
                    Interficie.purpleMessage("You have selected globally heal in random '%'");
                    percentage = (int) (Math.random() * 100) + 1;
                    for (int i = 0; i < t.getRows(); i++) {
                        for (int j = 0; j < t.getColumns(); j++) {
                            if (t.getT()[i][j] > 0) {
                                t.getT()[i][j] = t.getT()[i][j] - (t.getT()[i][j] * (percentage / 100));
                            }
                        }
                    }
                    Interficie.yellowMessage("The random percentage have been: " + (int) percentage + "%");
                }
            } else if (n == 1) {
                Interficie.purpleMessage("You have selected heal specifically");
                int option = 1;
                while (option == 1) {
                    x = Utils.validateInt(t, "f", "Insert the row you want", "You have to insert a numeric value", "Incorrect row, try again", 0, 0) - 1;
                    y = Utils.validateInt(t, "c", "Insert the column you want", "You have to insert a numeric value", "Incorrect column, try again", 0, 0) - 1;
                    if (t.getT()[x][y] > 0) {
                        option = 0;
                    } else {
                        Interficie.redMessage("This position may be blocked or have 0 people, try again");
                    }
                }
                Interficie.purpleMessage("Do you want to heal in '%' or specifically value");
                r = Utils.validateInt(t, "v", "0. %\n1. Specifically value", "You must enter a numeric value", "You must enter '0' or '1'", 0, 0);
                if (r == 0) {
                    Interficie.purpleMessage("You have selected heal in '%'\nDo you want a specifically or random '%'?");
                    f = Utils.validateInt(t, "v", "0. Specifically\n1. Random", "You must enter a numeric value", "You must enter '0' or '1'", 0, 0);
                    if (f == 0) {
                        Interficie.purpleMessage("You have selected heal in a specifically position with an specifically '%'");
                        percentage = Utils.validateFloat("Insert the '%' that you want", "You must enter a numeric value", "You must enter a number higher than 0");
                        if (t.getT()[x][y] > 0) {
                            t.getT()[x][y] = t.getT()[x][y] - (t.getT()[x][y] * (percentage / 100));
                        }
                    } else if (f == 1) {
                        Interficie.purpleMessage("You have selected heal in a random '%'");
                        percentage = (int) (Math.random() * 100) + 1;
                        if (t.getT()[x][y] > 0) {
                            t.getT()[x][y] = t.getT()[x][y] - (t.getT()[x][y] * (percentage / 100));
                        }
                    }
                    Interficie.yellowMessage("The random percentage have been: " + (int) percentage + "%");
                } else if (r == 1) {
                    int specificallyValue = Utils.validateInt(t, "z", "You have selected heal by an specifically value",
                            "You have to insert a numeric value",
                            "You must enter a number higher than 0 or not higher than the people on this position", x, y);
                    if (t.getT()[x][y] > 0) {
                        t.getT()[x][y] = t.getT()[x][y] - specificallyValue;
                    }
                }
            }
        } else {
            Interficie.redMessage("You may introduce some sick people first");
        }
        healedPersons += totalPersons - BoardManager.sumArray(t);
    }

    /**
     * Desplaça els malalts situats en el taulell segons la posició
     * i cap a la direcció que l'usuari dessitgi.
     *
     * @param t Ve de la classe Board i l'utilitzem per a actualitzar les caselles del taulell en funció dels valors que entri l'usuari.
     */
    public void moveSickPeople(Board t) {
        int x = 0;
        int y = 0;
        if (sumArray(t) != 0) {
            int option = 1;
            while (option == 1) {
                x = Utils.validateInt(t, "f", "Insert the row you want", "You have to insert a numeric value",
                        "Incorrect row, try again", 0, 0) - 1;
                y = Utils.validateInt(t, "c", "Insert the column you want", "You have to insert a numeric value",
                        "Incorrect column, try again", x, 0) - 1;
                if (t.getT()[x][y] > 0) {
                    option = 0;
                } else {
                    Interficie.redMessage("This position may be blocked or have 0 people, try again");
                }
            }
            int numberSickPeople = Utils.validateInt(t, "z", "Insert the number of sick people you want to move",
                    "You have to insert a numeric value",
                    "You must enter a number higher than 0 or not higher than the people on this position", x, y);
            movedPeople += numberSickPeople;
            String direction = Utils.validateMove("In which direction do you want to move them?\nQ. Left Up\nW. Up\nE. Right Up\nA. Left\nD. Right\nZ. Left Down\nX. Down\nC. Right Down",
                    "You must enter one of the following characters");
            switch (direction) {
                case "q" -> {
                    t.getT()[x][y] -= numberSickPeople;
                    if (x - 1 >= t.getRows() || y - 1 >= t.getColumns() || x - 1 < 0 || y - 1 < 0 || t.getT()[x - 1][y - 1] < 0) {
                        runawayPeople += numberSickPeople;
                    } else {
                        t.getT()[x - 1][y - 1] += numberSickPeople;
                    }
                }
                case "w" -> {
                    t.getT()[x][y] -= numberSickPeople;
                    if (x - 1 >= t.getRows() || y >= t.getColumns() || x - 1 < 0 || t.getT()[x - 1][y] < 0) {
                        runawayPeople += numberSickPeople;
                    } else {
                        t.getT()[x - 1][y] += numberSickPeople;
                    }
                }
                case "e" -> {
                    t.getT()[x][y] -= numberSickPeople;
                    if (x - 1 >= t.getRows() || y + 1 >= t.getColumns() || x - 1 < 0 || t.getT()[x - 1][y + 1] < 0) {
                        runawayPeople += numberSickPeople;
                    } else {
                        t.getT()[x - 1][y + 1] += numberSickPeople;
                    }
                }
                case "a" -> {
                    t.getT()[x][y] -= numberSickPeople;
                    if (x >= t.getRows() || y - 1 >= t.getColumns() || y - 1 < 0 || t.getT()[x][y - 1] < 0) {
                        runawayPeople += numberSickPeople;
                    } else {
                        t.getT()[x][y - 1] += numberSickPeople;
                    }
                }
                case "d" -> {
                    t.getT()[x][y] -= numberSickPeople;
                    if (x >= t.getRows() || y + 1 >= t.getColumns() || t.getT()[x][y + 1] < 0) {
                        runawayPeople += numberSickPeople;
                    } else {
                        t.getT()[x][y + 1] += numberSickPeople;
                    }
                }
                case "z" -> {
                    t.getT()[x][y] -= numberSickPeople;
                    if (x + 1 >= t.getRows() || y - 1 >= t.getColumns() || y - 1 < 0 || t.getT()[x + 1][y - 1] < 0) {
                        runawayPeople += numberSickPeople;
                    } else {
                        t.getT()[x + 1][y - 1] += numberSickPeople;
                    }
                }
                case "x" -> {
                    t.getT()[x][y] -= numberSickPeople;
                    if (x + 1 >= t.getRows() || y >= t.getColumns() || t.getT()[x + 1][y] < 0) {
                        runawayPeople += numberSickPeople;
                    } else {
                        t.getT()[x + 1][y] += numberSickPeople;
                    }
                }
                case "c" -> {
                    t.getT()[x][y] -= numberSickPeople;
                    if (x + 1 >= t.getRows() || y + 1 >= t.getColumns() || t.getT()[x + 1][y + 1] < 0) {
                        runawayPeople += numberSickPeople;
                    } else {
                        t.getT()[x + 1][y + 1] += numberSickPeople;
                    }
                }
            }
        } else {
            Interficie.redMessage("You may introduce some sick people first");
        }
    }

    /**
     * Suma totes les persones malaltes del taulell per emmagatzemar-les dins d'una variable.
     *
     * @param t Ve de la classe Board i l'utilitzem per a enmagatzemar el valor de la suma total de les caselles del taulell.
     */
    public static int sumArray(Board t) {
        int sum = 0;
        for (int i = 0; i < t.getRows(); i++) {
            for (int j = 0; j < t.getColumns(); j++) {
                if (t.getT()[i][j] >= 0) {
                    sum += t.getT()[i][j];
                }
            }
        }
        return (sum);
    }

    /**
     * Genera un percentatge de totes les persones fugides
     * en funció de totes les persones malaltes que han passat pel taulell. Retorna un percentatge.
     *
     * @param movedPeople contador de persones que s'han desplaçat.
     * @param sickTotal   contador de totes les persones que han passat pel taulell.
     */
    public static int sumMovedPeople(int movedPeople, int sickTotal) {
        return ((movedPeople * 100) / sickTotal);
    }
}
