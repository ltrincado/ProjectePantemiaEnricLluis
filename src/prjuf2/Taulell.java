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

    public float getCasella(int i, int j) {

        return t[i][j];
    }

    public void setCasella(int i, int j, float value) {

        this.t[i][j] = value;
    }

    public void createTaulellRandom(){
        this.t = new float[files][columnes];
        Random r = new Random();
        for (int i = 0; i < files; i++) {
            for (int j = 0; j < columnes; j++) {
                t[i][j] = r.nextInt(10);
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
                cad.append((int)t[i][j] + "\t");
            }
            cad.append("\n");
        }
        return cad.toString();
    }
}
