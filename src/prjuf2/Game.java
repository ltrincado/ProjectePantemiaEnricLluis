package prjuf2;

import java.util.Scanner;

public class Game {
    public static void main(String[] args) {
        Game game = new Game();
        game.beginning();
    }
    public void beginning(){
        Scanner in = new Scanner(System.in);
        String[] menu = {
                "\t       MENU",
                "1. Create a random board", "2. Create an empty board", "3. Add sick people      ", "4. Pass on the virus    ",
                "5. Heal sick people     ", "6. Move sick people    ", "7. Show information     ", "0. Quit                 "
        };
        int option;
        String[] data = new String[4];
        BoardManager g = new BoardManager();
        Taulell t = new Taulell();
        Interficie.mostrarMenu(menu);
        option = Utils.validateInt(t,"v1","Select '1' or '2' to start the game" ,"You must enter a numeric value",
                "You must enter '0' , '1' or '2'",0,0);
        while (option!=0){
            switch (option) {
                case 1 -> {
                    Interficie.mostrarCapçalera("You have selected create a random board");
                    g.loadRandom(t);
                    System.out.println(t.toString());
                }
                case 2 -> {
                    Interficie.mostrarCapçalera("You have selected create an empty board");
                    g.loadEmpty(t);
                    System.out.println(t.toString());
                }
                case 3 -> {
                    Interficie.mostrarCapçalera("You have selected add sick people");
                    g.addSickPeople(t);
                }
                case 4 -> {
                    Interficie.mostrarCapçalera("You have selected pass on the virus");
                    g.transmitVirus(t);
                }
                case 5 -> {
                    Interficie.mostrarCapçalera("You have selected heal sick people");
                    g.healSickPeople(t);
                    Interficie.mostrarMissatge(t.toString());
                }
                case 6 -> {
                    Interficie.mostrarCapçalera("You have selected move sick people");
                    g.moveSickPeople(t);
                }
                case 7 -> {
                    Interficie.mostrarCapçalera("Show information");
                    Interficie.mostrarDades(t, g, data);
                    System.out.println();
                    System.out.println(t.toString());
                }
            }
            Interficie.mostrarMenu(menu);
            option = in.nextInt();
        }
        Interficie.redMessage("GAME OVER");

    }
}
