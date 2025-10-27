package Parte2;
import java.util.Scanner;

public class SumMatrixComparison {

    public static void main(String[] args) throws InterruptedException {
        Scanner sc = new Scanner(System.in);
        System.out.println("Ingrese el número de filas ");
        int numFilas = sc.nextInt();
        System.out.println("Ingrese el número de columnas ");
        int numColumnas = sc.nextInt();
        System.out.println("Ingrese el numero de threads ");
        int numThreads = sc.nextInt();

        int[][] matriz = MatrixBuilder.createMatrix(numFilas, numColumnas);
        long tiempoInicialSecuencial = System.nanoTime();
        int resultadoSecuencial = SumMatrixSequential.sumarMatriz(matriz);
        long tiempoFinalSecuencial = System.nanoTime();
        double duracionFinalSecuencial = (tiempoFinalSecuencial-tiempoInicialSecuencial);
        System.out.println("\nLa suma secuencial terminó:");
        System.out.println("Resultado = " + resultadoSecuencial);
        System.out.printf("Tiempo = %.6f ms%n", duracionFinalSecuencial/1000000);

        long tiempoInicialConcurrente = System.nanoTime();
        int resultadoConcurrente = SumMatrixConcurrent.correrConcurrente(matriz, numThreads);
        long tiempoFinalConcurrente = System.nanoTime();
        double duracionFinalConcurrente = (tiempoFinalConcurrente- tiempoInicialConcurrente);
        System.out.println("\nLa suma concurrente terminó:");
        System.out.println("Resultado = " + resultadoConcurrente);
        System.out.printf("Tiempo = %.6f ms%n", duracionFinalConcurrente/1000000);

        // **MATRICES PEQUEÑAS**
        // Compare el tiempo entre la versión secuencial y la concurrente, y escriba su
        // conclusión sobre cuál resulta más eficiente y por qué.
        // Con matrices pequeñas, se puede observar que la versión secuencial es más rápida debido al overhead que tiene
        // el crear y gestionar múltiples threads en la versión concurrente. Esto se evidencia en que el tiempo de ejecución
        // de la versión concurrente es mayor que el de la secuencial. Por ejemplo, al sumar una matriz de 3x3,
        // la versión secuencial tomó 0.25 ms, mientras que la concurrente tomó 0.55ms usando 3 threads.
        // Por esta razón, podríamos decir que para matrices pequeñas, la versión secuencial es más eficiente.

        // **MATRICES GRANDES**
        //Analice si la versión concurrente presenta una mejora significativa en el tiempo
        //de ejecución respecto a la secuencial, y explique por qué sucede esto.
        // Con matrices grandes, la versión concurrente muestra una mejora significativa en el tiempo de ejecución
        // en comparación con la versión secuencial. Esto se debe a que el trabajo de sumar los elementos de la matriz
        // se divide entre los múltiples threads, permitiendo que se realicen varias sumas en paralelo.
        // Por ejemplo, al sumar una matriz de 10000x10000, la versión secuencial tomó 31,57ms mientras que la concurrente
        // tomó 22.88ms usando 10 threads. Esta mejora se debe a que el overhead de gestionar los threads
        // se compensa con la reducción del tiempo total de cálculo al aprovechar mejor los recursos del computador.

        // **MATRICES GRANDES Y MUCHOS THREADS**
        // Al experimentar con un número elevado de threads, se puede observar que el tiempo de ejecución al usar
        // muchos threads no siempre mejora significativamente el rendimiento. Esto se debe a que, aunque
        // dividir el trabajo entre más threads puede reducir el tiempo de cálculo, también introduce un overhead
        // adicional en la gestión de estos threads, por lo que si se agregan demasiados threads, el overhead puede superar
        // los beneficios de la concurrencia. Por ejemplo, al sumar una matriz de 100x100 usando 100 threads,
        // el tiempo de ejecución fue peor que al usar 10 threads, ya que el overhead de gestionar tantos threads
        // superó la ventaja de la concurrencia por la limitación de recursos del computador.
    }

}
