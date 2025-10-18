public class Oyente extends Thread {
    private MonitorContador monitor;
    
    public Oyente(String nombre, MonitorContador monitor) {
        super(nombre);
        this.monitor = monitor;
    }
    
    @Override
    public void run() {
        int valorActual = monitor.getContador();
        System.out.println("[" + this.getName() + "]: Iniciado. Valor inicial = " + valorActual);
        
        while (valorActual > 0) {
            int nuevoValor = monitor.getContador();
            
            if (nuevoValor != valorActual) {
                System.out.println("[" + this.getName() + "]: Cambio detectado: " + valorActual + " -> " + nuevoValor);
                valorActual = nuevoValor;
            }

            Thread.yield();
        }
        
        System.out.println("[" + this.getName() + "]: Finalizado");
    }
}