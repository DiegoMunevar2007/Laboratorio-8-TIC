public class MonitorContador {
    private int contador;

    public MonitorContador(int inicial) {
        this.contador = inicial;
    }

    public synchronized int getContador() {
        return contador;
    }

    public synchronized void esperarCambio(Thread thread) {
        int valorActual = contador;
        System.out.println("[" + thread.getName() + "]: Dormido en espera pasiva");
        try {
            wait();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        int valorNuevo = contador;
        System.out.println("[" + thread.getName() + "]: Despierto. Cambio detectado: " + valorActual + " -> " + valorNuevo);
    }

    public synchronized void decrementarYNotificar(Thread thread) {
        contador--;
        System.out.println("[" + thread.getName() + "]: Contador decrementado a " + contador);
        notify();
    }
}

/*
public class MonitorContador {
    private int contador;
    
    public MonitorContador(int inicial) {
        this.contador = inicial;
    }
    
    public synchronized int getContador() {
        return contador;
    }
    
    public synchronized void esperarCambio(Thread thread) {
        // TO DO
    }
    
    public synchronized void decrementarYNotificar(Thread thread) {
        // TO DO
    }
}
*/