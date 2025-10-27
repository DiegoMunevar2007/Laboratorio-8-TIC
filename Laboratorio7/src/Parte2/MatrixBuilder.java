package Parte2;
public class MatrixBuilder {

    public static int[][] createMatrix(int n, int m){
        int[][] matriz = new int[n][m];
        for (int i =0; i<n; i++){
            for (int j=0; j<m; j++){
                matriz[i][j] = (int) (Math.random()*100);
            }
        }
        return matriz;
    }


}
