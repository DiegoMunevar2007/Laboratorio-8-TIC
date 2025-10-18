public class MonitorContador {
    private int contador;
    
    public MonitorContador(int inicial) {
        this.contador = inicial;
    }
    
    public synchronized int getContador() {
        return contador;
    }
    
    public synchronized void decrementar(Thread thread) {
        contador--;
        System.out.println("[" + thread.getName() + "]: Contador decrementado a " + contador);
    }
}