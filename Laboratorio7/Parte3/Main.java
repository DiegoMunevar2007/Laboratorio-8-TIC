public class Main {
    public static void main(String[] args) {
        System.out.println("=== EJEMPLO DE ESPERA SEMI-ACTIVA CON MONITOR ===\n");
        
        MonitorContador monitor = new MonitorContador(5);

        Thread oyente1 = new Oyente("Oyente", monitor);
        Thread notificador = new Notificador("Notificador", monitor, 2000);
        
        oyente1.start();
        notificador.start();
        
        try {
            notificador.join();
            oyente1.join();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        
        System.out.println("\n=== PROGRAMA TERMINADO ===");
    }
}