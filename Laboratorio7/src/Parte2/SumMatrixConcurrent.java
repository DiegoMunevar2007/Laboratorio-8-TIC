package Parte2;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class SumMatrixConcurrent extends Thread{
    private static int contador;
    private static int[][] matriz;
    private static CyclicBarrier barrera;
    private int filaInicio;
    private int filaFin;
    public SumMatrixConcurrent(int filaInicio, int filaFin){
        this.filaInicio = filaInicio;
        this.filaFin = filaFin;
    }

    @Override
    public void run() {
        int sumaParcial = 0;
        for (int i = filaInicio; i < filaFin; i++) {
            for (int num : matriz[i]) {
                sumaParcial += num;
            }
        }
        incrementarContador(sumaParcial);
        try {
            barrera.await();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (BrokenBarrierException e) {
            throw new RuntimeException(e);
        }
    }

    public static int correrConcurrente(int[][] matriz, int cantidadThreads) throws InterruptedException {
        SumMatrixConcurrent.matriz = matriz;
        SumMatrixConcurrent.barrera = new CyclicBarrier(cantidadThreads, new Runnable() {
            @Override
            public void run() {
                System.out.println("Todos los hilos han terminado :D");
            }
        });
        contador = 0;

        int filas = matriz.length;
        Thread[] threads = new Thread[cantidadThreads];
        int filasPorThread = filas / cantidadThreads;
        int resto = filas % cantidadThreads;

        int inicio = 0;
        for (int i = 0; i < cantidadThreads; i++) {
            int fin = inicio + filasPorThread;
            // Si hay resto, significa que no fue division entera, por lo que añado 1 fila más a este thread.
            if (resto > 0) {
                fin += 1;
                resto -= 1;
            }
            threads[i] = new SumMatrixConcurrent(inicio, fin);
            threads[i].start();
            inicio = fin;
        }

        return contador;
    }
    private synchronized static void incrementarContador(int cantidad){
        contador+=cantidad;
    }


    public static void main(String[] args) throws InterruptedException {
        int[][] matrizASumar = MatrixBuilder.createMatrix(3, 3);
        int resultado = correrConcurrente(matrizASumar, 1);
        System.out.println("El resultado para la suma concurrente fue de: "+ resultado);
    }

}
