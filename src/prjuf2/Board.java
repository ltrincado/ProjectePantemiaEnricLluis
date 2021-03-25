package prjuf2;

public class Board
{
    public float[][] getT() {
        return t;
    }

    private int rows;
    private int columns;
    private float[][] t;

    public int getRows() {
        return rows;
    }

    public void setRows(int rows) {
        this.rows = rows;
    }

    public int getColumns() {
        return columns;
    }

    public void setColumns(int columns) {
        this.columns = columns;
    }


    public void setPosition(int i, int j, float value) {

        this.t[i][j] = value;
    }

    public void createRandomBoard(){
        this.t = new float[rows][columns];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                int random = (int) (Math.random()*(1-13)+10);
                if (random < 0){
                    t[i][j] = -1;
                }else {
                    t[i][j] = random;
                }


            }
        }
    }
    public void createEmptyBoard(){
        this.t = new float[rows][columns];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                t[i][j] = 0;
            }
        }
    }


    @Override
    public String toString() {
        StringBuilder cad = new StringBuilder();
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
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
