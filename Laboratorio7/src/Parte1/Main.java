package Parte1;

public class Main {
    public static void main(String[] args) {
        System.out.println("=== INICIO DEL PROGRAMA PRODUCTOR-CONSUMIDOR ===\n");

        int N = 3;
        int M = 2;
        int bufferCapacity = 2;
        int totalItems = 5;

        System.out.println("Configuración:");
        System.out.println("- Productores: " + N);
        System.out.println("- Consumidores: " + M);
        System.out.println("- Capacidad del búfer: " + bufferCapacity);
        System.out.println("- Total de ítems a producir y consumir: " + totalItems);
        System.out.println();

        Buffer buffer = new Buffer(bufferCapacity);

        Producer.setTotalToProduce(totalItems);
        Producer[] producers = new Producer[N];
        for (int i = 0; i < N; i++) {
            producers[i] = new Producer("Productor " + (i + 1), buffer);
            producers[i].start();
        }

        Consumer.setTotalToConsume(totalItems);
        Consumer[] consumers = new Consumer[M];
        for (int i = 0; i < M; i++) {
            consumers[i] = new Consumer("Consumidor " + (i + 1), buffer);
            consumers[i].start();
        }

        for (Producer producer : producers) {
            try {
                producer.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        for (Consumer consumer : consumers) {
            try {
                consumer.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println("\n=== PROGRAMA FINALIZADO ===");
    }
}