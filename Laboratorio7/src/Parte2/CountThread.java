package Parte2;
public class CountThread extends Thread{

    private static int contador = 0;

    public CountThread(){

    }

    @Override
    public void run(){
        for (int i =0; i<5000; i++){
            incrementar();
        }
    }

    private synchronized static void incrementar(){
        contador++;
    }

    // ¿Cuál debería ser el resultado final del contador si no hubiera interferencia entre los hilos?
    // El resultado final del contador debió haber sido 20000, gracias a que cada thread está incrementando el contador
    // en uno por 5000 iteraciones, al ser 4 threads, obtenemos que 5000*4 = 20000 incrementos finales.

    // ¿Qué valor obtiene realmente al ejecutar el programa varias veces?
    // El valor que obtengo realmente al ejecutar el programa varias veces no es constante, donde existen veces donde se
    // obtienen valores como 11882, 11949, 12690 y muy pocas veces 20000.

    // ¿Por qué el resultado no es el correcto? Responda definiendo el concepto de condición de carrera y explíquela con
    // base en el comportamiento observado.
    // El resultado no es correcto puesto que se espera que cada thread aumente en 1 el contador, llegando hasta el resultado
    // de 20.000. Ahora, una condición de carrera es cuando el comportamiento de un sistema depende del orden que tienen los hilos
    // a la hora de ejecutar y que acceden a un recurso compartido. En este caso, se produce un resultado inconsistente puesto que
    // se almacena temporalmente el valor en registro y luego se realiza el cambio, donde otros threads ya hicieron cambios a la variable en memoria
    // por lo que el valor en registro está desactualizado, dando lugar a los resultados inconsistentes.

    // ¿Qué cambio realizó para evitar la condición de carrera?
    // El cambió que realicé para evitar la condición de carrera fue crear un método estático y sincronizado titulado "incrementar"
    // el cual es sincronizado globalmente para todos los threads, donde se aplica un candado sobre la función, inhabilitando
    // al resto de threads para ejecutar esa función mientras que una lo esta ejecutando.

    // ¿Qué significa “sección crítica” y por qué es importante protegerla?
    // Una sección crítica es una sección del código donde varios threads hacen uso de recursos compartidos en el programa,
    // donde se busca garantizar que un único thread acceda a los datos. Esto debe protegerse puesto que puede llegar a producir
    // inconsistencias si no se protege, por lo que puede generar errores a la hora de usar esos datos posteriormente.
    public static void main(String[] args) throws InterruptedException {
        Thread[] threads = new CountThread[4];
        for (int i = 0; i<4; i++){
            threads[i] = new CountThread();
            threads[i].start();
        }

        for (int i =0; i<4; i++){
            threads[i].join();
        }

        System.out.println("El valor final del contador es: "+ contador);
    }
}
