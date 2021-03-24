package prjuf2;

import java.util.Scanner;

public class Utils {

    public static int validarEnter(String missatge, String missatgeError){
        Scanner scanner = new Scanner(System.in);
        int ret;
        boolean correcte;
        do{
            System.out.println(missatge);
            correcte=scanner.hasNextInt();
            if(!correcte){
                scanner.next();
                System.out.println(missatgeError);
            }
        }while(!correcte);
        ret = scanner.nextInt();
        scanner.nextLine();
        return ret;
    }
    public static int validarEnterSol(){
        Scanner scanner = new Scanner(System.in);
        int ret;
        boolean correcte;
        do{
            correcte=scanner.hasNextInt();
            if(!correcte){
                scanner.next();
                System.out.println("You have to insert a numeric value");
            }
        }while(!correcte);
        ret = scanner.nextInt();
        scanner.nextLine();
        return ret;
    }
    public static int validarRangFiles(Taulell t, String missatgeError) {
        int ret = validarEnter("Insert the row you want","You have to insert a numeric value");
        ret = ret-1;
        while (ret >= t.getFiles() || ret<0) {
            System.out.println(missatgeError);
            ret = validarEnterSol()-1;
        }
        return ret;
    }
    public static int validarRangColumnes(Taulell t, String missatgeError) {
        int ret = validarEnter("Insert the column you want","You have to insert a numeric value");
        ret = ret-1;
        while (ret >= t.getColumnes() || ret<0) {
            System.out.println(missatgeError);
            ret = validarEnterSol()-1;
        }
        return ret;
    }
    public static int validarRangMalalts(String missatgeError) {
        int ret = validarEnter("Number of sick person you want to insert","You have to insert a numeric value");
        while (ret<=0) {
            System.out.println(missatgeError);
            ret = validarEnterSol();
        }
        return ret;
    }
    public static int validarSortida(String missatge) {
        System.out.println(missatge);
        int ret = validarEnterSol();
        while (ret < 0 || ret > 1) {
            System.out.println("Error, try again");
            ret = validarEnterSol();
        }
        return ret;
    }
    public static float validarFloat(String missatge, String missatgeError){
        Scanner scanner = new Scanner(System.in);
        float ret = 0;
        boolean correcte;
        boolean validacioLimits= false;
        while (!validacioLimits) {
            do {
                System.out.println(missatge);
                correcte = scanner.hasNextFloat();
                if (!correcte) {
                    scanner.next();
                    System.out.println(missatgeError);
                }
            } while (!correcte);
            ret = scanner.nextFloat();
            scanner.nextLine();
            validacioLimits=true;
            if (ret <= 0) {
                System.out.println("Valor no valid");
                validacioLimits=false;
            }
        }
        return ret;
    }
    public static float validarFloatSol(String missatge){
        Scanner scanner = new Scanner(System.in);
        float ret;
        boolean correcte;
        do{
            System.out.println(missatge);
            correcte=scanner.hasNextFloat();
            if(!correcte){
                scanner.next();
                System.out.println("You have to insert a numeric value");
            }
        }while(!correcte);
        ret = scanner.nextFloat();
        scanner.nextLine();
        return ret;
    }
    public static float validarRt(String missatgeError) {
        float ret = validarFloat("Insert the RT (Transmission ratio)","You have to enter a numeric value");
        while (ret<=0) {
            System.out.println(missatgeError);
            ret = validarFloatSol(null);
        }
        return ret;
    }
    public static float validarPercentatge(String missatgeError) {
        float ret = validarFloat("You have selected globally heal in specifically '%'\nInsert the '%' that you want","You have to enter a numeric value");
        while (ret<=0) {
            System.out.println(missatgeError);
            ret = validarFloatSol("");
        }
        return ret;
    }
}
