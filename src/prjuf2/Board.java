package prjuf2;

public class Board {
    /**
     * L'utilitzem per obtenir una posició concreta del taulell.
     */
    public float[][] getT() {
        return t;
    }

    private int rows;
    private int columns;
    private float[][] t;

    /**
     * L'utilitzem per obtenir el valor de les files del taulell.
     */
    public int getRows() {
        return rows;
    }

    /**
     * L'utilitzem per donar valor a les files del taulell.
     */
    public void setRows(int rows) {
        this.rows = rows;
    }

    /**
     * L'utilitzem per obtenir el valor de les columnes del taulell.
     */
    public int getColumns() {
        return columns;
    }

    /**
     * L'utilitzem per donar valor a les columnes del taulell.
     */
    public void setColumns(int columns) {
        this.columns = columns;
    }

    /**
     * L'utilitzem per donar valors a una posició concreta del taulell.
     *
     * @param i     l'utilitzem per donar la posició de les files.
     * @param j     l'utilitzem per donar la posició de les columnes.
     * @param value l'utilitzem per assignar valor a la posició donada anteriorment.
     */
    public void setPosition(int i, int j, float value) {

        this.t[i][j] = value;
    }

    /**
     * L'utilitzarem per a crear el taulell aleatori.
     */
    public void createRandomBoard() {
        this.t = new float[rows][columns];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                int random = (int) (Math.random() * (1 - 13) + 10);
                if (random < 0) {
                    t[i][j] = -1;
                } else {
                    t[i][j] = random;
                }


            }
        }
    }

    /**
     * L'utilitzarem per a crear el taulell buit.
     */
    public void createEmptyBoard() {
        this.t = new float[rows][columns];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                t[i][j] = 0;
            }
        }
    }

    /**
     * L'utilitzem pel printat del taulell.
     */
    @Override
    public String toString() {
        StringBuilder cad = new StringBuilder();
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                if (t[i][j] == -1) {
                    cad.append(Interficie.red + "X\t" + Interficie.reset);
                } else {
                    cad.append(Interficie.yellow).append((int) t[i][j]).append("\t").append(Interficie.reset);
                }
            }
            cad.append("\n");
        }
        return cad.toString();
    }
}
