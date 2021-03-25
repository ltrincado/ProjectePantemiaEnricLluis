package prjuf2;

public class BoardManager {
    int sickPeople = 0;
    int movedPeople = 0;
    int runawayPeople = 0;
    int sickTotal = 0;
    int totalPersons = 0;
    int healedPersons = 0;


    public void loadEmpty(Taulell t) {
        int rows = Utils.validateInt(t,"m","Insert the number of rows","You have to insert a numeric value","You must enter a number higher than 0",0,0);
        int columns = Utils.validateInt(t,"m","Insert the number of columns","You have to insert a numeric value","You must enter a number higher than 0",0,0);
        t.setFiles(rows);
        t.setColumnes(columns);
        t.createTaulellBuit();
        sickTotal = sumArray(t);
    }
    public void loadRandom(Taulell t) {
        int rows = Utils.validateInt(t,"m","Insert the number of rows","You have to insert a numeric value","You must enter a number higher than 0",0,0);
        int columns = Utils.validateInt(t,"m","Insert the number of columns","You have to insert a numeric value","You must enter a number higher than 0",0,0);
        t.setFiles(rows);
        t.setColumnes(columns);
        t.createTaulellRandom();
        sickTotal = sumArray(t);
    }

    public void addSickPeople(Taulell t) {
        int n = 1;
        while (n == 1) {
            int x = Utils.validateInt(t,"f","Insert the row you want","You have to insert a numeric value",
                    "Incorrect row, try again",0,0)-1;
            int y = Utils.validateInt(t,"c","Insert the column you want","You have to insert a numeric value",
                    "Incorrect column, try again",0,0)-1;
            sickPeople =(int)t.getT()[x][y] + Utils.validateInt(t,"m","Number of sick person you want to insert","You have to insert a numeric value","You must enter a number higher than 0",0,0);
            sickTotal += sickPeople;
            t.setCasella(x, y, sickPeople);
            Interficie.purpleMessage("Do you want to insert more sick persons?");
            n = Utils.validateInt(t,"v","1. Insert more sick persons\n0. Back to menu","You must enter a numeric value","You must enter '0' or '1'",0,0);
        }
    }

    public void transmitVirus(Taulell t) {
        float rt = Utils.validateFloat("Insert the RT (Transmission ratio)","You have to enter a numeric value",
                "You have to insert a number higher than 0");
        for (int i = 0; i < t.getFiles(); i++) {
            for (int j = 0; j < t.getColumnes(); j++) {
                t.getT()[i][j] += t.getT()[i][j] * rt;
            }
        }
        sickTotal = sumArray(t) + runawayPeople;
    }

    public void healSickPeople(Taulell t) {
        float percentage;
        int n;
        int r;
        int f;
        totalPersons = BoardManager.sumArray(t);
        Interficie.purpleMessage("Do you want to heal globally or specifically?");
        n = Utils.validateInt(t,"v","0. Globally\n1. Specifically","You must enter a numeric value","You must enter '0' or '1'",0,0);
        if (n == 0) {
            Interficie.purpleMessage("You have selected heal globally\nDo you want to heal in specifically or random '%'");
            r = Utils.validateInt(t,"v","0. Specifically\n1. Random","You must enter a numeric value","You must enter '0' or '1'",0,0);
            if (r == 0) {
                percentage = Utils.validateFloat("Insert the '%' that you want",
                        "You must enter a numeric value","You must enter a number higher than 0");
                for (int i = 0; i < t.getFiles(); i++) {
                    for (int j = 0; j < t.getColumnes(); j++) {
                        t.getT()[i][j] = t.getT()[i][j] - (t.getT()[i][j] * (percentage / 100));
                    }
                }
            } else if (r==1){
                Interficie.purpleMessage("You have selected globally heal in random '%'");
                percentage = (int) (Math.random() * 100) + 1;
                for (int i = 0; i < t.getFiles(); i++) {
                    for (int j = 0; j < t.getColumnes(); j++) {
                        t.getT()[i][j] = t.getT()[i][j] - (t.getT()[i][j] * (percentage / 100));
                    }
                }
                Interficie.yellowMessage("The random percentage have been: " + (int)percentage + "%");
            }
        } else if (n==1){
            Interficie.purpleMessage("You have selected heal specifically");
            int x = Utils.validateInt(t,"f","Insert the row you want","You have to insert a numeric value","Incorrect row, try again",0,0)-1;
            int y = Utils.validateInt(t,"c","Insert the column you want","You have to insert a numeric value","Incorrect column, try again",0,0)-1;
            Interficie.purpleMessage("Do you want to heal in '%' or specifically value");
            r = Utils.validateInt(t,"v","0. %\n1. Specifically value","You must enter a numeric value","You must enter '0' or '1'",0,0);
            if (r == 0) {
                Interficie.purpleMessage("You have selected heal in '%'\nDo you want a specifically or random '%'?");
                f = Utils.validateInt(t,"v","0. Specifically\n1. Random","You must enter a numeric value","You must enter '0' or '1'",0,0);
                if (f == 0) {
                    Interficie.purpleMessage("You have selected heal in a specifically position with an specifically '%'");
                    percentage = Utils.validateFloat("Insert the '%' that you want","You must enter a numeric value","You must enter a number higher than 0");
                    t.getT()[x][y] = t.getT()[x][y] - (t.getT()[x][y] * (percentage / 100));
                } else if (f==1){
                    Interficie.purpleMessage("You have selected heal in a random '%'");
                    percentage = (int) (Math.random() * 100) + 1;
                    for (int i = 0; i < t.getFiles(); i++) {
                        for (int j = 0; j < t.getColumnes(); j++) {
                            t.getT()[i][j] = t.getT()[i][j] - (t.getT()[i][j] * (percentage / 100));
                        }
                    }
                    Interficie.yellowMessage("The random percentage have been: " + (int)percentage + "%");
                }
            } else if (r==1){
                int specificallyValue = Utils.validateInt(t,"z","You have selected heal by an specifically value",
                        "You have to insert a numeric value",
                        "You must enter a number higher than 0 or not higher than the people on this position",x,y);
                t.getT()[x][y] = t.getT()[x][y] - specificallyValue;
            }
        }
        healedPersons += totalPersons - BoardManager.sumArray(t);
    }

    public void moveSickPeople(Taulell t) {
        int x = Utils.validateInt(t,"f","Insert the row you want","You have to insert a numeric value",
                "Incorrect row, try again",0,0)-1;
        int y = Utils.validateInt(t,"c","Insert the column you want","You have to insert a numeric value",
                "Incorrect column, try again",0,0)-1;
        int numberSickPeople = Utils.validateInt(t,"z","Insert the number of sick people you want to move",
                "You have to insert a numeric value",
                "You must enter a number higher than 0 or not higher than the people on this position",x,y);
        movedPeople += numberSickPeople;
        String direction = Utils.validateString("In which direction do you want to move them?" +
                "\nQ. Left Up\nW. Up\nE. Right Up\nA. Left\nD. Right\nZ. Left Down\nX. Down\nC. Right Down",
                "You must enter one of the following characters");
        switch (direction) {
            case "q" -> {
                t.getT()[x][y] -= numberSickPeople;
                if (x - 1>= t.getFiles() || y - 1>= t.getColumnes()){
                    runawayPeople += numberSickPeople;
                } else {
                    t.getT()[x - 1][y - 1] += numberSickPeople;
                }
            }
            case "w" -> {
                t.getT()[x][y] -= numberSickPeople;
                if (x - 1 >= t.getFiles() || y >= t.getColumnes()){
                    runawayPeople += numberSickPeople;
                }else{
                    t.getT()[x - 1][y] += numberSickPeople;
                }
            }
            case "e" -> {
                t.getT()[x][y] -= numberSickPeople;
                if (x - 1>= t.getFiles() || y + 1>= t.getColumnes()){
                    runawayPeople += numberSickPeople;
                }else{
                    t.getT()[x - 1][y + 1] += numberSickPeople;
                }
            }
            case "a" -> {
                t.getT()[x][y] -= numberSickPeople;
                if (x >= t.getFiles() || y - 1>= t.getColumnes()){
                    runawayPeople += numberSickPeople;
                } else{
                    t.getT()[x][y - 1] += numberSickPeople;
                }
            }
            case "d" -> {
                t.getT()[x][y] -= numberSickPeople;
                if (x >= t.getFiles() || y + 1>= t.getColumnes()){
                    runawayPeople += numberSickPeople;
                }else{
                    t.getT()[x][y + 1] += numberSickPeople;
                }
            }
            case "z" -> {
                t.getT()[x][y] -= numberSickPeople;
                if (x + 1>= t.getFiles() || y -1>= t.getColumnes()){
                    runawayPeople += numberSickPeople;
                } else{
                    t.getT()[x + 1][y - 1] += numberSickPeople;
                }
            }
            case "x" -> {
                t.getT()[x][y] -= numberSickPeople;
                if (x + 1 >= t.getFiles() || y >= t.getColumnes()){
                    runawayPeople += numberSickPeople;
                }else {
                    t.getT()[x + 1][y] += numberSickPeople;
                }
            }
            case "c" -> {
                t.getT()[x][y] -= numberSickPeople;
                if (x + 1>= t.getFiles() || y + 1>= t.getColumnes()){
                    runawayPeople += numberSickPeople;
                } else {
                    t.getT()[x + 1][y + 1] += numberSickPeople;
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
    public static int sumMovedPeople(int movedPeople, int sickTotal){
        return ((movedPeople * 100)/sickTotal);
    }
}