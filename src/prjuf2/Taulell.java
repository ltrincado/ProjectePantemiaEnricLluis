package prjuf2;

import java.util.Random;

public class Taulell
{
    public float[][] getT() {
        return t;
    }

    private int files;
    private int columnes;
    private float[][] t;

    public int getFiles() {
        return files;
    }

    public void setFiles(int files) {
        this.files = files;
    }

    public int getColumnes() {
        return columnes;
    }

    public void setColumnes(int columnes) {
        this.columnes = columnes;
    }


    public void setCasella(int i, int j, float value) {

        this.t[i][j] = value;
    }

    public void createTaulellRandom(){
        this.t = new float[files][columnes];
        Random r = new Random();
        for (int i = 0; i < files; i++) {
            for (int j = 0; j < columnes; j++) {
                int random = (int) (Math.random()*(1-13)+10);
                if (random < 0){
                    t[i][j] = -1;
                }else {
                    t[i][j] = random;
                }


            }
        }
    }
    public void createTaulellBuit(){
        this.t = new float[files][columnes];
        for (int i = 0; i < files; i++) {
            for (int j = 0; j < columnes; j++) {
                t[i][j] = 0;
            }
        }
    }


    @Override
    public String toString() {
        StringBuilder cad = new StringBuilder();
        for (int i = 0; i < files; i++) {
            for (int j = 0; j < columnes; j++) {
                if (t[i][j]==-1){
                    cad.append(Interficie.red+"X\t"+Interficie.reset);
                }else{
                    cad.append(Interficie.yellow).append((int) t[i][j]).append("\t").append(Interficie.reset);
                }
            }
            cad.append("\n");
        }
        return cad.toString();
    }
}
