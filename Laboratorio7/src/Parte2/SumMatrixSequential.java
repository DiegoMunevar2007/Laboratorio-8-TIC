package Parte2;
public class SumMatrixSequential {


    public static int sumarMatriz(int[][] matriz){
        int resultado = 0;
        for (int i=0; i< matriz.length; i++){
            for (int j=0; j<matriz[i].length; j++){
                resultado += matriz[i][j];
            }
        }
        return resultado;
    }

    public static void main(String[] args) {
        int[][] matrizASumar = MatrixBuilder.createMatrix(3, 3);
        int resultado = sumarMatriz(matrizASumar);
        System.out.println("El resultado para la suma secuencial fue de: "+ resultado);
    }
}
