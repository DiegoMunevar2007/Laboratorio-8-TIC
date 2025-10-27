package Parte2;
public class HelloThread extends Thread {

    private String name;
    private int sleepingTime;

    public HelloThread(String name, int sleepingTime) {
        this.name = name;
        this.sleepingTime = sleepingTime;
    }
    @Override
    //¿Por qué aparece la anotación @Override encima de la definición de run()?
    // Esta anotación aparece puesto que se necesita reemplazar la implementación por defecto de la clase Thread por una
    // implementación personalizada del método .run()

    // **¿Por qué el orden de los println de los hilos cambia en cada ejecución?**
    // El orden de los println cambia en cada ejecución puesto que existe una parte "no deterministica" dentro de la
    // ejecución a la hora de lanzar los hilos, puesto que se lanzan al tiempo a partir del thread main, dando lugar a cierta
    // aleatoreidad dentro de la ejecución

    // ¿Qué cambio implementó y por qué esa solución hace que la salida quede ordenada?
    // El cambió que implementé fue agregar un nuevo atributo "sleepingTime", el cual se crea a partir del contador i a la hora
    // de crear los threads. Luego, añadí una antelación al println dentro del método run usando .sleep(), donde el thread
    // duerme durante el tiempo en ms específicado a partir de sleepingTime.

    // ¿Cuál cree que es la desventaja de usar sleep() para ordenar la ejecución de hilos?
    // Creería que la principal desventaja de usar sleep es que estaríamos limitando un hilo a cierto tiempo, independientemente
    // de cuanto tiempo tome en realidad. Por ejemplo, si un thread hiciera una operación sencilla pero estuviera de últimas en la "cola"
    // añadiría un "overhead" de tiempo a la ejecución del programa.
    public void run() {
        try {
            sleep(sleepingTime);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Hola desde "+ name);
    }

    public static void main(String[] args) throws InterruptedException{
        Thread[] threads = new Thread[5];

        for (int i=0; i<5; i++){
            threads[i] = new HelloThread("T" + (i+1), i);
            threads[i].start();
        }
        for (int i =0; i<5; i++){
            threads[i].join();
        }
        System.out.println("Hilo principal terminado");
    }

}
